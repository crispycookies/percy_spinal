package percytheperceptron.ml

import percytheperceptron.{predictor, predictor_jimenez, predictor_jimenez_delayed_train}
import spinal.sim._
import spinal.core.sim._

object JimenezPredictorTests {
  def main(args: Array[String]): Unit = {
    val core = SimConfig.withWave.compile(new predictor_jimenez(bit_width = 16, feature_count = 3, table_size = 2, address_bit_width = 16, index_bit_width = 16, -1,1,0,1, 127))
    core.doSim("Check Wiring"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 1
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      /*assert(dut.io.history(2).toInt == 0)
      assert(dut.io.history(1).toInt == 1)
      assert(dut.io.history(0).toInt == 1)
      */
    }
    val core2 = SimConfig.withWave.compile(new predictor_jimenez(bit_width = 16, feature_count = 3, table_size = 2, address_bit_width = 16, index_bit_width = 16, -1,1,0,3, 127))
    core2.doSim("Check Wiring"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 1
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      /*assert(dut.io.history(2).toInt == 0)
      assert(dut.io.history(1).toInt == 1)
      assert(dut.io.history(0).toInt == 1)
      */
    }
    val core3 = SimConfig.withWave.compile(new predictor_jimenez_delayed_train(bit_width = 16, feature_count = 3, table_size = 2, address_bit_width = 16, index_bit_width = 16, -1,1,0,1, 127))
    core3.doSim("Check Wiring Delay"){ dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 0
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 1
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1

      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.forkStimulus(10)
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 0
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.address #= 0
      dut.io.taken #= 1
      println(dut.io.prediction.toInt)
      dut.clockDomain.waitSampling()
      dut.io.taken #= 1
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()

      /*assert(dut.io.history(2).toInt == 0)
      assert(dut.io.history(1).toInt == 1)
      assert(dut.io.history(0).toInt == 1)
      */
    }
  }
}