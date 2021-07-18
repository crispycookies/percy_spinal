package percytheperceptron.ml

import percytheperceptron.ml.perceptron.util.perceptron_core
import percytheperceptron.ml.trainer.util.get_update
import percytheperceptron.history_table
import spinal.sim._
import spinal.core.sim._

object DutTests {
  def main(args: Array[String]): Unit = {
    val core = SimConfig.withWave.compile(new history_table(bit_width = 16, feature_count = 3))
    core.doSim("Test A-core"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()

      assert(dut.io.history(2).toInt == 0)
      assert(dut.io.history(1).toInt == 1)
      assert(dut.io.history(0).toInt == 1)
    }
  }
}
