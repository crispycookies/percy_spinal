package percytheperceptron.memory

import spinal.core._
import spinal.core.internals.Operator
import spinal.lib._

class controller(address_bit_width : Int, bit_width : Int, row_count : Int, sub_cell_count : Int) extends Component{
  val io = new Bundle {
    val features_to_store = out Vec(Vec(UInt(bit_width bits),sub_cell_count),row_count)
    val features_from_store = in Vec(Vec(UInt(bit_width bits),sub_cell_count),row_count)
    val write_ena = out Vec(UInt(1 bits), row_count)

    val address = in UInt(address_bit_width bits)
    val write_ena_user = in UInt(1 bits)
    val read = out Vec(UInt(bit_width bits), sub_cell_count)
    val store = in Vec(UInt(bit_width bits), sub_cell_count)
  }
  val address = UInt(log2Up(row_count) bits)

  address := io.address(log2Up(row_count)-1 downto 0)

  io.write_ena.map(_ := 0)
  io.read.map(_ := 0)

  for (i <- io.features_to_store) {
    i.map(_ := 0)
  }

  when(address <= row_count){
    io.features_to_store(address) := io.store
    io.read := io.features_from_store(address)
    when(io.write_ena_user === 1) {
      io.write_ena(address) := 1
    }
  }
}

