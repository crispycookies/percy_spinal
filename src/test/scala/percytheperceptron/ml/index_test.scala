package percytheperceptron.ml

import percytheperceptron.mod_index
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
  }
}

