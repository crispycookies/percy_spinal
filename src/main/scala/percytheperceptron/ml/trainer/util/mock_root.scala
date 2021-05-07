package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class mock_root(bit_width : Int) extends Component {
  val io = new Bundle {
    val inner_product: UInt = in UInt(bit_width bits)
    val root: UInt = out UInt(bit_width bits)
  }
  io.root := io.inner_product
}

