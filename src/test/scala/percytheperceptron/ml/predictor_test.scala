package percytheperceptron.ml

import spinal.core._
import spinal.lib._

class predictor_test extends Component {
  val io = new Bundle {
    val a, b = in UInt (8 bits)
    val y = out UInt (8 bits)
  }

}

