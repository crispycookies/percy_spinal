package percytheperceptron

import percytheperceptron.ml.perceptron.perceptron
import percytheperceptron.ml.trainer.trainer
import spinal.core._

class predictor(bit_width: Int, feature_count: Int, table_size : Int, address_bit_width : Int, index_bit_width : Int, lower_bound: Int, upper_bound : Int, zero : Int) extends Component {
  val io = new Bundle {
    val taken = in UInt(1 bits)
    val prediction = out UInt(1 bits)
    val address = out UInt(1 bits)
  }
  val indexer = new mod_index(address_bit_width = address_bit_width, index_bit_width = index_bit_width, table_size = table_size)
  val predictor_perceptron = new perceptron(bit_width = bit_width, feature_count = feature_count, lower_bound = lower_bound, upper_bound = upper_bound, zero = zero);
  val history = new history_table(bit_width = bit_width, feature_count = feature_count)
  val table = new weight_table(bit_width = bit_width, feature_count = feature_count, table_size = table_size, address_bit_width = index_bit_width)
  val trainer_perceptron = new trainer(bit_width = bit_width, feature_count = feature_count)

  // History Wiring
  // Store History
  history.io.taken := io.taken
  // Give trainer the History for training
  trainer_perceptron.io.current_data := history.io.history




}

