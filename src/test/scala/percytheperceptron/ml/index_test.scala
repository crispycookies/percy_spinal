package percytheperceptron.ml

import percytheperceptron.{mod_index, mod_read_write_index}
import spinal.sim._
import spinal.core.sim._

object ModDutTests {
  def main(args: Array[String]): Unit = {
    val core = SimConfig.withWave.compile(new mod_index(address_bit_width = 16, index_bit_width = 16, table_size = 4))
    core.doSim("Test Index"){ dut =>
      dut.io.address #= 2
      sleep(1)
      assert(dut.io.index.toInt == 2)
      sleep(1)

      dut.io.address #= 5
      sleep(1)
      assert(dut.io.index.toInt == 1)
      sleep(1)

      dut.io.address #= 39
      sleep(1)
      assert(dut.io.index.toInt == 3)
      sleep(1)

      dut.io.address #= 154
      sleep(1)
      assert(dut.io.index.toInt == 2)
    }
    val core_reg = SimConfig.withWave.compile(new mod_read_write_index(address_bit_width = 16, index_bit_width = 16, table_size = 4))
    core_reg.doSim("Test Read/Write Index"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 2
      sleep(1)
      assert(dut.io.index_read.toInt == 2)
      assert(dut.io.index_write.toInt == 0)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.index_read.toInt == 2)
      assert(dut.io.index_write.toInt == 2)

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 5
      sleep(1)
      assert(dut.io.index_read.toInt == 1)
      assert(dut.io.index_write.toInt == 2)

      dut.io.address #= 39
      sleep(1)
      assert(dut.io.index_read.toInt == 3)
      sleep(1)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.index_write.toInt == 3)
    }
  }
}

