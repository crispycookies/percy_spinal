package percytheperceptron

import percytheperceptron.memory.register_file
import percytheperceptron.ml.perceptron.perceptron
import spinal.core._
import spinal.lib._

class weight_table(bit_width: Int, feature_count: Int, table_size : Int, address_bit_width : Int) extends Component {
  val io = new Bundle {
    val address = in UInt(bit_width bits)
    val weights_in: Vec[SInt] = in Vec(SInt(bit_width bits), feature_count)
    val weights_out: Vec[SInt] = out Vec(SInt(bit_width bits), feature_count)
  }
  val mem = new register_file(address_bit_width, bit_width, table_size, feature_count)
  mem.io.address := io.address
  mem.io.write_ena_user := 1

  for (i <- 0 until feature_count) {
    mem.io.store(i) := io.weights_in(i).asUInt
  }
  for (i <- 0 until feature_count) {
    io.weights_out(i) := mem.io.read(i).asSInt
  }
 }


