package percytheperceptron.ml

import spinal.core._


class perceptron(bit_width: Int, nodes: Int) extends Component {
  val io = new Bundle {
    val bias = in UInt(bit_width bits)
    val weights = in Vec(UInt(bit_width bits), nodes)
    val values = in Vec(UInt(bit_width bits), nodes)
    val out_data = out UInt(bit_width bits)
  }
  val delay_sync_bias = RegNext(io.bias) init(0)

}

