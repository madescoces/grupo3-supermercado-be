package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "producto")
data class Producto ( val nombre:String = "", val desc: String = ""){
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var idProducto:Int = 0

  fun toDTO() = ProductoDTO(
    id = idProducto,
    name = nombre,
    desc = desc
  )
}

data class ProductoDTO(
  val id: Int,
  val name: String,
  val desc: String
)