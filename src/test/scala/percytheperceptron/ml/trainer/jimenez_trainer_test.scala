package percytheperceptron.ml.trainer

import spinal.core.sim._

object JimenezDutTests {
  def main(args: Array[String]): Unit = {
    val core = SimConfig.withWave.compile(new jimenez_trainer(16, 4, 127))
    core.doSim("Test Normal") { dut =>
      dut.io.predicted #= -1
      dut.io.predicted_numerical #= 102
      dut.io.current_weights(0) #= 1
      dut.io.current_weights(1) #= 2
      dut.io.current_weights(2) #= 3
      dut.io.current_weights(3) #= 4

      dut.io.current_data(0) #= 1
      dut.io.current_data(1) #= -1
      dut.io.current_data(2) #= 1
      dut.io.current_data(3) #= -1
      dut.io.bias_old #= 1

      dut.io.actual #= 1

      sleep(10)

      assert(dut.io.bias_new.toInt == 2)
      assert(dut.io.new_weigths(0).toInt == 2)
      assert(dut.io.new_weigths(1).toInt == 1)
      assert(dut.io.new_weigths(2).toInt == 4)
      assert(dut.io.new_weigths(3).toInt == 3)
    }
    core.doSim("Test Corner Case 1") { dut =>
      dut.io.predicted #= -1
      dut.io.predicted_numerical #= 102
      dut.io.current_weights(0) #= 1
      dut.io.current_weights(1) #= 2
      dut.io.current_weights(2) #= 3
      dut.io.current_weights(3) #= 4

      dut.io.current_data(0) #= 1
      dut.io.current_data(1) #= -1
      dut.io.current_data(2) #= 1
      dut.io.current_data(3) #= -1
      dut.io.bias_old #= 128

      dut.io.actual #= 1

      sleep(10)

      assert(dut.io.bias_new.toInt == 127)
    }
    core.doSim("Test Corner Case 2") { dut =>
      dut.io.predicted #= -1
      dut.io.predicted_numerical #= 102
      dut.io.current_weights(0) #= 1
      dut.io.current_weights(1) #= 2
      dut.io.current_weights(2) #= 3
      dut.io.current_weights(3) #= 4

      dut.io.current_data(0) #= 1
      dut.io.current_data(1) #= -1
      dut.io.current_data(2) #= 1
      dut.io.current_data(3) #= -1
      dut.io.bias_old #= 128

      dut.io.actual #= 1

      sleep(10)

      assert(dut.io.bias_old.toInt == 128)
    }
    core.doSim("Test Corner Case 3") { dut =>
      dut.io.predicted #= -1
      dut.io.predicted_numerical #= 102
      dut.io.current_weights(0) #= 1
      dut.io.current_weights(1) #= 2
      dut.io.current_weights(2) #= 3
      dut.io.current_weights(3) #= 4

      dut.io.current_data(0) #= 1
      dut.io.current_data(1) #= -1
      dut.io.current_data(2) #= 1
      dut.io.current_data(3) #= -1
      dut.io.bias_old #= -128

      dut.io.actual #= -1

      sleep(10)

      assert(dut.io.bias_old.toInt == -128)
    }
  }
}
