package percytheperceptron

import percytheperceptron.memory.register_file
import percytheperceptron.ml.perceptron.perceptron
import percytheperceptron.ml.perceptron.util.{activation, perceptron_core}
import percytheperceptron.ml.trainer.{trainer, trainer_with_percy}
import percytheperceptron.ml.trainer.util.get_update
import spinal.core._

// This is the main function that generates the VHDL and the Verilog corresponding to MyTopLevel.
object PercyMain {
  def main(args: Array[String]) {
    SpinalVerilog(new trainer_with_percy(bit_width = 16, feature_count = 4, lower_bound = -1, upper_bound = 1, zero = 0))
  }
}
