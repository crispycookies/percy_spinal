package percytheperceptron.ml.trainer.util

import spinal.core._

class get_update(bit_width: Int) extends Component {
  val io = new Bundle {
    val eta, actual, predicted = in UInt (8 bits)
    val y = out UInt (8 bits)
  }
  val error: UInt = UInt(bit_width bits)
  error := io.actual - io.predicted

  val update_pre: UInt = UInt(2*bit_width bits)
  val update: UInt = UInt(bit_width bits)
  update_pre := io.eta * error
  update := update_pre(bit_width-1 downto 0)

  io.y := update
}

