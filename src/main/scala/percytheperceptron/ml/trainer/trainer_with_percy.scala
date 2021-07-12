package percytheperceptron.ml.trainer

import percytheperceptron.ml.perceptron
import percytheperceptron.ml.perceptron.perceptron
import spinal.core._
import spinal.lib._

class trainer_with_percy(bit_width: Int, feature_count: Int, lower_bound: Int, upper_bound : Int, zero : Int) extends Component {
  val io = new Bundle {
    val eta: SInt = in SInt(bit_width bits)
    val actual: SInt = in SInt(bit_width bits)
    val prediction: SInt = out SInt(bit_width bits)
    val values: Vec[SInt] = in Vec(SInt(bit_width bits), feature_count)
  }
  val percy = new perceptron(feature_count = feature_count, bit_width = bit_width, lower_bound = lower_bound, upper_bound = upper_bound, zero = zero)
  val trainer_instance = new trainer(bit_width = bit_width, feature_count = feature_count);


  percy.io.values := io.values
  percy.io.weights := io.values
  percy.io.bias := trainer_instance.io.bias
  io.prediction := percy.io.prediction

  trainer_instance.io.eta := io.eta
  trainer_instance.io.current_data := io.values
  trainer_instance.io.current_weights := percy.io.weights
  trainer_instance.io.predicted := percy.io.prediction
  trainer_instance.io.actual := io.actual
}

