package percytheperceptron.ml.perceptron

import spinal.sim._
import spinal.core.sim._

object DutTests {
  def main(args: Array[String]): Unit = {
    SimConfig.withWave.compile(new perceptron(bit_width = 32, feature_count = 4, lower_bound = 100, upper_bound = 200, zero = 150)).doSim{ dut =>
      dut.clockDomain.forkStimulus(10)
    }
  }
}

