package percytheperceptron

import spinal.core._

class predictor extends Component {
  val io = new Bundle {
    val a, b = in UInt (8 bits)
    val y = out UInt (8 bits)
  }

}

