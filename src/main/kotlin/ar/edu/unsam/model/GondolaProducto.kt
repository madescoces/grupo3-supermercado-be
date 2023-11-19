package ar.edu.unsam.ar.edu.unsam.model

import ar.edu.unsam.model.Gondola
import ar.edu.unsam.model.Presentacion
import ar.edu.unsam.model.Producto
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "gondola_producto")
@IdClass(GondolaProductoId::class)
data class GondolaProducto(
  @ManyToOne
  @JoinColumn(name = "gondola_id", insertable = false, updatable = false)
  @Id
  val gondola: Gondola,

  @ManyToOne
  @JoinColumn(name = "producto_id", insertable = false, updatable = false)
  @Id
  val producto: Producto,

  @ManyToOne
  @JoinColumn(name = "presentacion_id")
  val presentacion: Presentacion
  ){
  fun toDTO() = GondolaProductoDTO(
    sectorId = gondola.idSector,
    productoId = producto.idProducto,
    productoNombre = producto.nombre,
    gondolaNombre = gondola.nombre,
    presentacionDesc = presentacion.desc,
  )
}

data class GondolaProductoId(
  val gondola: Long,
  val producto: Long
) : Serializable

data class GondolaProductoDTO(
  val sectorId: Int,
  val productoId: Int,
  val productoNombre: String,
  val gondolaNombre: String,
  val presentacionDesc: String,
)