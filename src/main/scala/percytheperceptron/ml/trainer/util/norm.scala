package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class norm(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val norm: UInt= out UInt(bit_width bits)
  }
  val inner = new inner_product(bit_width = bit_width, feature_count = feature_count)
  val root = new mock_root(bit_width = bit_width)

  inner.io.features := io.features
  root.io.inner_product := inner.io.inner_product
  io.norm := root.io.root
}

