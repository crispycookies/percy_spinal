package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class mock_square(bit_width : Int) extends Component {
  val io = new Bundle {
    val root: UInt = in UInt(bit_width bits)
    val square: UInt = out UInt(bit_width bits)
  }
  io.square := io.root
}


