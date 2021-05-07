package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class inner_product(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val inner_product = out UInt(bit_width bits)
  }
  val inner_products: Vec[UInt] = Vec(UInt(bit_width*2 bits), feature_count)
  val inner_products_truncd: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)

  for (i <- 0 until feature_count) {
    inner_products(i) := io.features(i) * io.features(i)
  }
  for (i <- 0 until feature_count) {
    inner_products_truncd(i) := inner_products(i)(bit_width-1 downto 0)
  }
  io.inner_product := inner_products_truncd.reduce(_+_)
}

