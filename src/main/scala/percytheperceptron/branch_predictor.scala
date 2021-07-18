package percytheperceptron

import spinal.core._
import spinal.lib._

class branch_predictor(bit_width: Int, feature_count: Int, table_size : Int, address_bit_width : Int) extends Component {
  val io = new Bundle {
    val address = in SInt(bit_width bits)
    val prediction = out UInt (bit_width bits)
  }

}

