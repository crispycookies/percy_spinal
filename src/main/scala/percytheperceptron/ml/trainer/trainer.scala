package percytheperceptron.ml.trainer

import percytheperceptron.ml.trainer.util.get_update
import percytheperceptron.ml.trainer.util.math.{vector_add, vector_mul}
import spinal.core._

class trainer(bit_width: Int, feature_count: Int) extends Component {
  val io = new Bundle {
    val bias: UInt = in UInt(bit_width bits)
    val current_weights: Vec[UInt] = in Vec(UInt(bit_width bits), feature_count)
    val new_weigths: Vec[UInt] = out Vec(UInt(bit_width bits), feature_count)
    val current_data = in Vec(UInt(bit_width bits), feature_count)
    val predicted: UInt = in UInt(bit_width bits)
    val eta: UInt = in UInt(bit_width bits)
    val actual: UInt = in UInt(bit_width bits)
  }
  // error = actual - predicted
  // update = eta * error
  val update_instance = new get_update(bit_width = bit_width)
  update_instance.io.actual := io.actual
  update_instance.io.predicted := io.predicted
  update_instance.io.eta := io.eta

  // assign result onto update
  val update: UInt = UInt(bit_width bits)
  update := update_instance.io.y

  /*
  * ---------------------------------------------------------
  * ---------------------------------------------------------
  */

  // for each input : update * input[i]
  val weight_mul_instance = new vector_mul(bit_width = bit_width, feature_count = feature_count)
  weight_mul_instance.io.a := io.current_data
  weight_mul_instance.io.b := update




  val multiplied: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)
  //multiplied := weight_mul_instance.io.y

  // for each weight: (old) weight + muled
  val weight_add_instance = new vector_add(bit_width = bit_width, feature_count = feature_count)
  weight_mul_instance.io.a := io.current_weights
  weight_mul_instance.io.b := multiplied

  val new_weights: Vec[UInt] = Vec(UInt(bit_width bits), feature_count)
  new_weights := weight_mul_instance.io.y

  // write back
  io.bias := update
  io.new_weigths := new_weights
}

