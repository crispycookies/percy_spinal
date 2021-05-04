package percytheperceptron

import percytheperceptron.memory.{cell, register_file}
import spinal.core._

class percy(test: Int) extends Component {
  val io = new Bundle {
    val a, b = in UInt (8 bits)
    val y = out UInt (8 bits)
  }
  io.y := io.a
}

// This is the main function that generates the VHDL and the Verilog corresponding to MyTopLevel.
object PercyMain {
  def main(args: Array[String]) {
    SpinalVerilog(new register_file(bit_width = 16, sub_cell_count = 7, row_count = 300, address_bit_width = 16))
  }
}
