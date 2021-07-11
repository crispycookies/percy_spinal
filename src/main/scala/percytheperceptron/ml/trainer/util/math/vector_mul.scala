package percytheperceptron.ml.trainer.util.math

import spinal.core._
import spinal.lib._

class vector_mul(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val a: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val b: UInt = in UInt(bit_width bits)
    val y = out Vec(UInt(bit_width bits), feature_count)
  }
  val weight_product: Vec[UInt] = Vec(UInt(bit_width*2 bits), feature_count)
  val weight_product_truncd: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)

  for (i <- 0 until feature_count) {
    weight_product(i) := io.a(i) * io.b
  }
  for (i <- 0 until feature_count) {
    weight_product_truncd(i) := weight_product(i)(bit_width-1 downto 0)
  }
  io.y := weight_product_truncd
}

