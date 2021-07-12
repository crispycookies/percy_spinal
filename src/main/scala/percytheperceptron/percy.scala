package percytheperceptron

import percytheperceptron.memory.register_file
import percytheperceptron.ml.perceptron.perceptron
import percytheperceptron.ml.perceptron.util.{activation, perceptron_core}
import percytheperceptron.ml.trainer.trainer
import percytheperceptron.ml.trainer.util.get_update
import spinal.core._

// This is the main function that generates the VHDL and the Verilog corresponding to MyTopLevel.
object PercyMain {
  def main(args: Array[String]) {
    SpinalVerilog(new trainer(bit_width = 16, feature_count = 4))
  }
}
