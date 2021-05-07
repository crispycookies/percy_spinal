package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class numerator(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val error: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val eta: UInt= in UInt(bit_width bits)
    val numerator: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
  }
  val sum: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)

  for (i <- 0 until feature_count) {
    sum(i) := io.error(i) + io.eta
  }

  io.numerator := sum
}

