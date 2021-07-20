package percytheperceptron

import percytheperceptron.ml.perceptron.perceptron
import percytheperceptron.ml.trainer.trainer
import spinal.core._

class predictor(bit_width: Int, feature_count: Int, table_size : Int, address_bit_width : Int, index_bit_width : Int, lower_bound: Int, upper_bound : Int, zero : Int) extends Component {
  val io = new Bundle {
    val taken = in UInt(1 bits)
    val prediction = out UInt(1 bits)
    val address = in UInt(address_bit_width bits)
  }
  def map_to_value(taken : UInt): SInt ={
    val scaled = SInt(bit_width bits)

    when(taken === 1) {
      scaled := upper_bound
    } otherwise {
      scaled := lower_bound
    }
    scaled
  }

  def unmap_from_value(taken : SInt): UInt ={
    val scaled = UInt(1 bits)

    when(taken === upper_bound) {
      scaled := 1
    } otherwise {
      scaled := 0
    }
    scaled
  }

  val indexer = new mod_read_write_index(address_bit_width = address_bit_width, index_bit_width = index_bit_width, table_size = table_size)
  val predictor_perceptron = new perceptron(bit_width = bit_width, feature_count = feature_count, lower_bound = lower_bound, upper_bound = upper_bound, zero = zero);
  val history = new history_table(bit_width = bit_width, feature_count = feature_count)
  val table = new weight_table(bit_width = bit_width, feature_count = feature_count, table_size = table_size, address_bit_width = index_bit_width)
  val trainer_perceptron = new trainer(bit_width = bit_width, feature_count = feature_count)

  val delayed_prediction = RegNext(predictor_perceptron.io.prediction) init(0)

  // History Wiring
  // Store History
  history.io.taken := io.taken
  // Give trainer the History for training
  trainer_perceptron.io.current_data := history.io.history
  predictor_perceptron.io.values := history.io.history

  // Index
  indexer.io.address := io.address

  table.io.address_write := indexer.io.index_write
  table.io.address_read := indexer.io.index_read

  // Table
  table.io.bias_in := trainer_perceptron.io.bias
  table.io.weights_in := trainer_perceptron.io.new_weigths
  predictor_perceptron.io.weights := table.io.weights_out
  trainer_perceptron.io.current_weights := table.io.weights_out
  predictor_perceptron.io.bias := table.io.bias_out

  // Trainer
  trainer_perceptron.io.eta := 1
  trainer_perceptron.io.actual := map_to_value(io.taken)
  // taken is delayed by at last 1 cycle, hence delay prediction as well
  trainer_perceptron.io.predicted := delayed_prediction

  io.prediction := unmap_from_value(predictor_perceptron.io.prediction)
}

