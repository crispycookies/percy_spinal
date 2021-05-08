package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class numerator(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val eta: UInt= in UInt(bit_width bits)
    val numerator: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
  }
  val error_calc = new error(bit_width = bit_width, feature_count = feature_count)
  val numerator_calc = new numerator_error_substitute(bit_width = bit_width, feature_count = feature_count)

  error_calc.io.features := io.features
  error_calc.io.weights := io.weights

  numerator_calc.io.error := error_calc.io.delta
  numerator_calc.io.eta := io.eta
  io.numerator := numerator_calc.io.numerator
}

