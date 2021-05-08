package percytheperceptron.ml.trainer.util

import percytheperceptron.ml.trainer.util.math.dot_product
import spinal.core._
import spinal.lib._

class error(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val delta: UInt = out UInt(bit_width bits)
  }
  val dot = new dot_product(bit_width = bit_width, feature_count = feature_count)
  val negate = UInt(bit_width bits)

  dot.io.a := io.weights
  dot.io.b := io.features
  negate := 0 - dot.io.res
  io.delta := negate
}

