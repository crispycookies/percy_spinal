package percytheperceptron.ml.trainer.util

import percytheperceptron.ml.trainer.util.math.dot_product
import spinal.core._
import spinal.lib._

class inner_product(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val inner_product = out UInt(bit_width bits)
  }
  val dot = new dot_product(bit_width = bit_width, feature_count = feature_count);
  dot.io.a := io.features
  dot.io.b := io.features
  io.inner_product := dot.io.res
}

