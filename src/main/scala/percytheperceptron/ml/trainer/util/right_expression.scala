package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class right_expression(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val eta: UInt= in UInt(bit_width bits)
    val r_expression: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
  }
  val frac = new fraction(bit_width = bit_width, feature_count = feature_count)
  val fractional_value: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)

  frac.io.features := io.features
  frac.io.weights := io.weights
  frac.io.eta := io.eta

  for (i <- 0 until feature_count) {
    fractional_value(i) := frac.io.fraction(i) * io.features(i)
  }
  io.r_expression := fractional_value
}

