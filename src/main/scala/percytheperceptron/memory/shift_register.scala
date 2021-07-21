package percytheperceptron.memory

import spinal.core._
import spinal.lib._

class shift_register(bit_width : Int, depth : Int) extends Component {
  val io = new Bundle {
    val input = in SInt(bit_width bits)
    val output = out SInt (bit_width bits)
  }
  if (depth == 1) {
    val register = Reg(io.input)
    register := io.input
    io.output := register
  } else {
    val register = Vec(Reg(SInt(bit_width bits)) init(0), depth + 1)
    register(0) := io.input

    for (i <- 1 until depth + 1) {
      register(i) := register(i-1)
    }
    io.output := register(depth)
  }
}

