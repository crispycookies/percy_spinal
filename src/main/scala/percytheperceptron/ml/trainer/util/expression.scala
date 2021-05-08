package percytheperceptron.ml.trainer.util

import spinal.core._
import spinal.lib._

class expression(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val features: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val eta: UInt= in UInt(bit_width bits)
    val r_expression: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
  }
  //val r_expression = new right_expression()

}

