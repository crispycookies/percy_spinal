package percytheperceptron

import percytheperceptron.memory.register_file
import percytheperceptron.ml.perceptron.perceptron
import percytheperceptron.ml.perceptron.util.{activation, perceptron_core}
import percytheperceptron.ml.trainer.trainer
import percytheperceptron.ml.trainer.util.{error, expression}
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
    SpinalVerilog(new trainer(bit_width = 16, feature_count = 4))
  }
}
