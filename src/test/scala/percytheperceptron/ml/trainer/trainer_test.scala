package percytheperceptron.ml.trainer

import percytheperceptron.ml.perceptron.util.perceptron_core
import percytheperceptron.ml.trainer.util.get_update
import spinal.sim._
import spinal.core.sim._

object DutTests {
  def main(args: Array[String]): Unit = {
    val update = SimConfig.withWave.compile(new get_update(bit_width = 16))
    update.doSim("Test A-update") { dut =>
      // pure combinatorics, hence no clock required
      dut.io.eta #= 1
      dut.io.actual #= 1
      dut.io.predicted #= 1

      sleep(1)
      assert(dut.io.y.toInt == 0)
    }
    update.doSim("Test B-update") { dut =>
      // pure combinatorics, hence no clock required
      dut.io.eta #= 1
      dut.io.actual #= 1
      dut.io.predicted #= -1

      sleep(1)
      assert(dut.io.y.toInt == 2)
    }
    update.doSim("Test C-update") { dut =>
      // pure combinatorics, hence no clock required
      dut.io.eta #= 1
      dut.io.actual #= -1
      dut.io.predicted #= 1

      sleep(1)
      assert(dut.io.y.toInt == -2)
    }
    val trainer = SimConfig.withWave.compile(new trainer(bit_width = 16, feature_count = 4))
    trainer.doSim("Test A-trainer") { dut =>
      dut.io.current_weights(0) #= 55
      dut.io.current_weights(1) #= -15
      dut.io.current_weights(2) #= 111
      dut.io.current_weights(3) #= 47

      dut.io.current_data(0) #= 60
      dut.io.current_data(1) #= 29
      dut.io.current_data(2) #= 45
      dut.io.current_data(3) #= 15

      dut.io.eta #= 1
      dut.io.actual #= 1
      dut.io.predicted #= 1

      sleep(10000)

      assert(dut.io.bias.toInt == 0)
      assert(dut.io.new_weigths(0).toInt == 55)
      assert(dut.io.new_weigths(1).toInt == -15)
      assert(dut.io.new_weigths(2).toInt == 111)
      assert(dut.io.new_weigths(3).toInt == 47)
    }
    trainer.doSim("Test A-trainer") { dut =>
      dut.io.current_weights(0) #= 55
      dut.io.current_weights(1) #= -15
      dut.io.current_weights(2) #= 111
      dut.io.current_weights(3) #= 47

      dut.io.current_data(0) #= 51
      dut.io.current_data(1) #= 38
      dut.io.current_data(2) #= 19
      dut.io.current_data(3) #= 4

      dut.io.eta #= 1
      dut.io.actual #= -1
      dut.io.predicted #= 1

      sleep(1)

      assert(dut.io.bias.toInt == -2)
      assert(dut.io.new_weigths(0).toInt == -47)
      assert(dut.io.new_weigths(1).toInt == -91)
      assert(dut.io.new_weigths(2).toInt == 73)
      assert(dut.io.new_weigths(3).toInt == 39)
    }
  }
}

