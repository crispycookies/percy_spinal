package percytheperceptron

import spinal.core._
import spinal.lib._

class branch_predictor extends Component {
  val io = new Bundle {
    val a, b = in UInt (8 bits)
    val y = out UInt (8 bits)
  }

}

