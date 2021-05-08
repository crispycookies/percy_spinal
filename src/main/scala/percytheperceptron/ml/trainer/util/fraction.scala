package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class fraction(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val eta: UInt= in UInt(bit_width bits)
    val fraction: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
  }
  val num = new numerator(bit_width: Int, feature_count: Int)
  val den = new denominator(bit_width: Int, feature_count: Int)

  val frac: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)
  // numerator
  num.io.weights := io.weights
  num.io.eta := io.eta
  num.io.features := io.features

  // denominator
  den.io.features := io.features

  for(i <- 0 until feature_count) {
    frac(i) := num.io.numerator(i) / den.io.denominator
  }
  io.fraction := frac
}
