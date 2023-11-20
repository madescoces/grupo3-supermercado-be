package ar.edu.unsam.ar.edu.unsam.model

import ar.edu.unsam.model.*
import jakarta.persistence.*
import java.io.Serializable
import java.sql.Date

@Entity
@Table(name = "gondola_producto_repositor")
data class GondolaProductoRepositor(
  @EmbeddedId
  val id: GondolaProductoRepositorId,

  @MapsId("gondolaProductoId")
  @ManyToOne
  @JoinColumn(name = "id_gondola", referencedColumnName = "id_gondola")
  @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
  val gondolaProducto:GondolaProducto,

  @MapsId("id_repositor")
  @ManyToOne
  @JoinColumn(name = "id_repositor")
  val repositor: Repositor,
  val fecha: Date,
  val cantidad: Float
  ){
  fun toDTO() = RepositorDataDTO(
    repositorId = repositor.idRepositor,
    productoId = gondolaProducto.producto.idProducto,
    productoNombre = gondolaProducto.producto.nombre,
    gondolaNombre = gondolaProducto.gondola.nombre,
    presentacionDesc = gondolaProducto.presentacion.descPresentacion,
  )
}

@Embeddable
data class GondolaProductoRepositorId(
  @Embedded
  val gondolaProductoId: GondolaProductoId,
  @Column(name = "id_repositor")
  val idRepositor: Long
) : Serializable