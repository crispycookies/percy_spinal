package percytheperceptron.memory

import percytheperceptron.memory.util.{controller, file}
import spinal.core._
import spinal.lib._

class register_file(address_bit_width : Int, bit_width : Int, row_count : Int, sub_cell_count : Int) extends Component {
  val io = new Bundle {
    val address = in UInt(address_bit_width bits)
    val write_ena_user = in UInt(1 bits)
    val read = out Vec(UInt(bit_width bits), sub_cell_count)
    val store = in Vec(UInt(bit_width bits), sub_cell_count)
  }
  val file = new file(bit_width = bit_width, row_count = row_count,sub_cell_count = sub_cell_count)
  val controller = new controller(bit_width = bit_width, row_count = row_count,sub_cell_count = sub_cell_count, address_bit_width = address_bit_width)

  controller.io.address := io.address
  controller.io.write_ena_user := io.write_ena_user
  controller.io.store := io.store
  io.read := controller.io.read

  file.io.features_to_store := controller.io.features_to_store
  controller.io.features_from_store := file.io.features_from_store
  file.io.write_ena := controller.io.write_ena
}

