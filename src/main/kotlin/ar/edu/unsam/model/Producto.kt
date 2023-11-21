package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "producto")
data class Producto (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idProducto:Int = -1,
  val nombreProducto:String,
  val idProductoReemplazo: Int
){
  private lateinit var descProducto: String

  @ManyToOne
  @JoinColumn(name = "id_fila", insertable = false, updatable = false)
  lateinit var fila: Fila

  @OneToMany(mappedBy = "producto")
  val gondolaProducto:List<GondolaProducto> = listOf()

  fun toDTO() = ProductoDTO(
    id = this.idProducto,
    name = this.nombreProducto,
    desc = this.descProducto,
    fila = this.fila.idFila,
    idReemplazo = this.idProductoReemplazo
  )
}

data class ProductoDTO(
  val id: Int,
  val name: String,
  val desc: String,
  val fila: Int,
  val idReemplazo: Int
)