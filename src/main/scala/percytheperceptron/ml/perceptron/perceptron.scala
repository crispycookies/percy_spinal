package percytheperceptron.ml.perceptron

import percytheperceptron.ml.perceptron.util.{activation, perceptron_core}
import spinal.core._

class perceptron(bit_width: Int, feature_count: Int, lower_bound: Int, upper_bound : Int, zero : Int) extends Component {
  val io = new Bundle {
    val bias: UInt = in UInt(bit_width bits)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val values: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val out_data: UInt = out UInt(bit_width bits)
  }
  val percy = new perceptron_core(bit_width = bit_width, feature_count = feature_count)
  val activation_function = new activation(bit_width = bit_width, lower_bound = lower_bound, upper_bound = upper_bound, zero = zero);

  percy.io.weights := io.weights
  percy.io.values := io.values
  percy.io.bias := io.bias

  activation_function.io.trigger := percy.io.out_data
  io.out_data := activation_function.io.step
}

