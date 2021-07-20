package percytheperceptron.ml

import percytheperceptron.memory.util.{cell, controller}
import percytheperceptron.ml.perceptron.util.perceptron_core
import percytheperceptron.ml.trainer.util.get_update
import percytheperceptron.{history_table, weight_table}
import spinal.sim._
import spinal.core.sim._

object WeightDutTests {
  def main(args: Array[String]): Unit = {
    val subcell = SimConfig.withWave.compile(new cell(bit_width = 16, 3))
    subcell.doSim("Subcell A-core") { dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.write_ena #= 1

      dut.io.features_to_store(0) #= 1
      dut.io.features_to_store(1) #= 2
      dut.io.features_to_store(2) #= 3

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      assert(dut.io.features_from_store(0).toInt == 1)
      assert(dut.io.features_from_store(1).toInt == 2)
      assert(dut.io.features_from_store(2).toInt == 3)

      dut.io.write_ena #= 0
      dut.io.features_to_store(0) #= 5
      dut.io.features_to_store(1) #= 6
      dut.io.features_to_store(2) #= 7

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      assert(dut.io.features_from_store(0).toInt == 1)
      assert(dut.io.features_from_store(1).toInt == 2)
      assert(dut.io.features_from_store(2).toInt == 3)
    }

    val controller = SimConfig.withWave.compile(new controller(address_bit_width = 3, bit_width = 16, row_count = 2, sub_cell_count = 2))
    controller.doSim("Subcell A-core") { dut =>
      dut.io.address_write #= 0
      dut.io.write_ena_user #= 1

      sleep(10)

      assert(dut.io.write_ena(0).toInt == 1)
      assert(dut.io.write_ena(1).toInt == 0)
    }


    val core = SimConfig.withWave.compile(new weight_table(bit_width = 16, feature_count = 3, 3, 3))
    core.doSim("Test A-core") { dut =>
      dut.clockDomain.forkStimulus(10)

      dut.io.address_write #= 0
      dut.io.address_read #= 0
      dut.io.weights_in(0) #= 1
      dut.io.weights_in(1) #= 2
      dut.io.weights_in(2) #= 3
      dut.io.bias_in #= 100

      //dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      assert(dut.io.weights_out(0).toInt == 1)
      assert(dut.io.weights_out(1).toInt == 2)
      assert(dut.io.weights_out(2).toInt == 3)
      assert(dut.io.bias_out.toInt == 100)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.weights_in(0) #= 4
      dut.io.weights_in(1) #= 5
      dut.io.weights_in(2) #= 6
      dut.io.bias_in #= 7

      //dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.weights_out(0).toInt == 4)
      assert(dut.io.weights_out(1).toInt == 5)
      assert(dut.io.weights_out(2).toInt == 6)
      assert(dut.io.bias_out.toInt == 7)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.bias_in #= 100
      dut.io.weights_in(0) #= 9
      dut.io.weights_in(1) #= 19
      dut.io.weights_in(2) #= 29
      dut.io.address_write #= 2
      dut.io.address_read #= 2

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.weights_out(0).toInt == 9)
      assert(dut.io.weights_out(1).toInt == 19)
      assert(dut.io.weights_out(2).toInt == 29)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address_write #= 0
      dut.io.address_read #= 0
      dut.io.weights_in(0) #= 0
      dut.io.weights_in(1) #= 0
      dut.io.weights_in(2) #= 0


      sleep(1)

      assert(dut.io.weights_out(0).toInt == 4)
      assert(dut.io.weights_out(1).toInt == 5)
      assert(dut.io.weights_out(2).toInt == 6)
      assert(dut.io.bias_out.toInt == 7)


      //dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      //dut.io.taken #= 0
      dut.clockDomain.waitSampling()
    }
    core.doSim("Test B-core") { dut =>
      dut.clockDomain.forkStimulus(10)

      dut.io.address_write #= 0
      dut.io.address_read #= 1
      dut.io.weights_in(0) #= 1
      dut.io.weights_in(1) #= 2
      dut.io.weights_in(2) #= 3
      dut.io.bias_in #= 100

      //dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.weights_out(0).toInt == 0)
      assert(dut.io.weights_out(1).toInt == 0)
      assert(dut.io.weights_out(2).toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address_read #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      assert(dut.io.weights_out(0).toInt == 1)
      assert(dut.io.weights_out(1).toInt == 2)
      assert(dut.io.weights_out(2).toInt == 3)
      assert(dut.io.bias_out.toInt == 100)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.weights_in(0) #= 4
      dut.io.weights_in(1) #= 5
      dut.io.weights_in(2) #= 6
      dut.io.bias_in #= 7

      //dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.weights_out(0).toInt == 4)
      assert(dut.io.weights_out(1).toInt == 5)
      assert(dut.io.weights_out(2).toInt == 6)
      assert(dut.io.bias_out.toInt == 7)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.bias_in #= 100
      dut.io.weights_in(0) #= 9
      dut.io.weights_in(1) #= 19
      dut.io.weights_in(2) #= 29
      dut.io.address_write #= 2
      dut.io.address_read #= 0

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.weights_out(0).toInt == 4)
      assert(dut.io.weights_out(1).toInt == 5)
      assert(dut.io.weights_out(2).toInt == 6)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      dut.io.address_read #= 2

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      assert(dut.io.weights_out(0).toInt == 9)
      assert(dut.io.weights_out(1).toInt == 19)
      assert(dut.io.weights_out(2).toInt == 29)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address_write #= 0
      dut.io.address_read #= 0
      dut.io.weights_in(0) #= 0
      dut.io.weights_in(1) #= 0
      dut.io.weights_in(2) #= 0


      sleep(1)

      assert(dut.io.weights_out(0).toInt == 4)
      assert(dut.io.weights_out(1).toInt == 5)
      assert(dut.io.weights_out(2).toInt == 6)
      assert(dut.io.bias_out.toInt == 7)


      //dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      //dut.io.taken #= 0
      dut.clockDomain.waitSampling()
    }
  }
}
