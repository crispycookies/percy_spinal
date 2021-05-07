package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class denominator(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val denominator: UInt = out UInt(bit_width bits)
  }
  val norm = new norm(bit_width = bit_width, feature_count = feature_count)
  val square = new mock_square(bit_width = bit_width)

  norm.io.features := io.features
  square.io.root := norm.io.norm
  io.denominator := square.io.square
}

