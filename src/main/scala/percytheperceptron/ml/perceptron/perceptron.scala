package percytheperceptron.ml.perceptron

import percytheperceptron.ml.perceptron.util.node
import spinal.core._


class perceptron(bit_width: Int, nodes: Int) extends Component {
  val io = new Bundle {
    val bias: UInt = in UInt(bit_width bits)
    val weights: Vec[UInt] = in Vec(UInt(bit_width bits), nodes)
    val values: Vec[UInt] = in Vec(UInt(bit_width bits), nodes)
    val out_data: UInt = out UInt(bit_width bits)
  }
  val delay_sync_bias: UInt = RegNext(io.bias) init(0)

  val nodes_entities: Array[node] = Array.fill(nodes)(new node(bit_width=bit_width))
  val nodes_result: Vec[UInt] = Vec(UInt(bit_width bits), nodes)
  val reduce_results: UInt = UInt(bit_width bits)
  val reduce_results_added_bias: UInt = UInt(bit_width bits)

  for (i <- 0 until nodes){
    nodes_entities(i).io.weight :=  io.weights(i)
    nodes_entities(i).io.value := io.values(i)
    nodes_result(i) := nodes_entities(i).io.out_product
  }
  reduce_results := nodes_result.reduce(_+_)
  reduce_results_added_bias := reduce_results + delay_sync_bias
  io.out_data := reduce_results_added_bias
}

