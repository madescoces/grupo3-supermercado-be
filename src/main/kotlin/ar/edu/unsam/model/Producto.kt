package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "producto")
data class Producto (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idProducto:Long,
  val nombre:String,
  val descProducto: String,

  @ManyToOne
  @JoinColumn(name="id_fila")
  val fila: Fila,
  val idProductoReemplazo: Long
){
  fun toDTO() = ProductoDTO(
    id = this.idProducto,
    name = this.nombre,
    desc = this.descProducto,
    fila = this.fila.idFila,
    idReemplazo = this.idProductoReemplazo
  )
}

data class ProductoDTO(
  val id: Long,
  val name: String,
  val desc: String,
  val fila: Long,
  val idReemplazo: Long
)