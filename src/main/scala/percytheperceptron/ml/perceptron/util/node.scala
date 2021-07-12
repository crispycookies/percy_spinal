package percytheperceptron.ml.perceptron.util

import spinal.core._

import scala.language.postfixOps

class node(bit_width : Int) extends Component {
  val io = new Bundle {
    val value: SInt = in SInt(bit_width bits)
    val weight: SInt = in SInt(bit_width bits)
    val out_product: SInt = out SInt (bit_width bits)
    val overflow: UInt = out UInt(1 bits)
  }
  val register_value: SInt = RegNext(io.value) init(0)
  val register_weight: SInt = RegNext(io.weight) init(0)
  val result: SInt = SInt(2*bit_width bits)
  result := register_value * register_weight
  io.out_product := result(bit_width -1 downto 0)
  when(result(2*bit_width-1 downto bit_width) =/= 0){
    io.overflow := 1
  } otherwise {
    io.overflow := 0
  }
}