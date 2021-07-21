package percytheperceptron.ml

import percytheperceptron.memory.shift_register
import spinal.sim._
import spinal.core.sim._

object ShiftRegisterDutTests {
  def main(args: Array[String]): Unit = {
    val shift_reg_test = SimConfig.withWave.compile(new shift_register(bit_width = 16, 1))
    shift_reg_test.doSim("Register") { dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.input #= 0
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
      dut.io.input #= 10
      sleep(10)
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 20
      assert(dut.io.output.toInt == 10)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 0
      assert(dut.io.output.toInt == 20)
    }
    val test2 = SimConfig.withWave.compile(new shift_register(bit_width = 16, 2))
    test2.doSim("Test2") { dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.input #= 10
      sleep(10)
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 20
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 0
      assert(dut.io.output.toInt == 10)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 20)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 0)
    }

    val test = SimConfig.withWave.compile(new shift_register(bit_width = 16, 3))
    test.doSim("Test") { dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.input #= 0
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
      dut.io.input #= 10
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 20
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 0
      sleep(10)
      assert(dut.io.output.toInt == 10)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 20)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 0)
    }
    val test4 = SimConfig.withWave.compile(new shift_register(bit_width = 16, 4))
    test4.doSim("Test") { dut =>
      dut.clockDomain.forkStimulus(10)
      dut.io.input #= 0
      sleep(10)
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 10
      sleep(10)
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 20
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      dut.io.input #= 0
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 10)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 20)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 0)
      dut.clockDomain.waitSampling()
      dut.clockDomain.waitSampling()
      assert(dut.io.output.toInt == 0)
    }
  }
}