package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class error(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val delta: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
  }
  val negate: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)
  val multiply: Vec[UInt] = Vec(UInt(bit_width * 2 bits), feature_count)
  val multiply_trucd: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)
  for (i <- 0 until feature_count) {
    negate(i) := 0 - io.weights(i)
  }
  for (i <- 0 until feature_count) {
    multiply(i) := negate(i) * io.features(i)
  }
  for (i <- 0 until feature_count) {
    multiply_trucd(i) := multiply(i)(bit_width - 1 downto 0)
  }
  io.delta := multiply_trucd
}

