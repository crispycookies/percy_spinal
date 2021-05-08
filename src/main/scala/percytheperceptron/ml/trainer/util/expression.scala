package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class expression(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val eta: UInt= in UInt(bit_width bits)
    val new_weights: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
  }
  val r_expression = new right_expression(bit_width = bit_width, feature_count = feature_count)
  r_expression.io.eta := io.eta
  r_expression.io.features := io.features
  r_expression.io.weights := io.weights

  for(i <- 0 until feature_count) {
    io.new_weights(i) := io.weights(i) + r_expression.io.r_expression(i)
  }
}

