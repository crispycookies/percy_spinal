package percytheperceptron.ml.perceptron

import percytheperceptron.ml.perceptron.util.perceptron_core
import spinal.sim._
import spinal.core.sim._

object DutTests {
  def main(args: Array[String]): Unit = {
    val core = SimConfig.withWave.compile(new perceptron_core(bit_width = 16, feature_count = 4/*, lower_bound = 100, upper_bound = 200, zero = 150*/))
    core.doSim("Test A-core"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.bias #= 100
      dut.io.values(0) #= 5
      dut.io.values(1) #= 10
      dut.io.values(2) #= 11
      dut.io.values(3) #= 20

      dut.io.weights(0) #= 4
      dut.io.weights(1) #= 3
      dut.io.weights(2) #= 2
      dut.io.weights(3) #= 1

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.out_data.toInt == 192)
    }
    val percy = SimConfig.withWave.compile(new perceptron(bit_width = 16, feature_count = 4, lower_bound = 1, upper_bound = 2, zero = 150))
    percy.doSim("Test A-percy"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.bias #= 100
      dut.io.values(0) #= 5
      dut.io.values(1) #= 10
      dut.io.values(2) #= 11
      dut.io.values(3) #= 20

      dut.io.weights(0) #= 4
      dut.io.weights(1) #= 3
      dut.io.weights(2) #= 2
      dut.io.weights(3) #= 1

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      assert(dut.io.prediction.toInt == 2)
    }

    core.doSim("Test B-core"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.bias #= 1
      dut.io.values(0) #= 5
      dut.io.values(1) #= 10
      dut.io.values(2) #= 11
      dut.io.values(3) #= 20

      dut.io.weights(0) #= 4
      dut.io.weights(1) #= 3
      dut.io.weights(2) #= 2
      dut.io.weights(3) #= 1

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.out_data.toInt == 93)
    }

    percy.doSim("Test B-percy"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.bias #= 1
      dut.io.values(0) #= 5
      dut.io.values(1) #= 10
      dut.io.values(2) #= 11
      dut.io.values(3) #= 20

      dut.io.weights(0) #= 4
      dut.io.weights(1) #= 3
      dut.io.weights(2) #= 2
      dut.io.weights(3) #= 1

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.prediction.toInt == 1)
    }
  }
}

