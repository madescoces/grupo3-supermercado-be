package ar.edu.unsam.model

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "gondola_producto")
data class GondolaProducto(
  @EmbeddedId
  val id: GondolaProductoId,

  @MapsId("id_gondola")
  @ManyToOne
  @JoinColumn(name = "id_gondola")
  val gondola:Gondola,

  @MapsId("id_producto")
  @ManyToOne
  @JoinColumn(name = "id_producto")
  val producto:Producto,

  @ManyToOne
  @JoinColumn(name = "id_presentacion")
  val presentacion: Presentacion
  ){
  fun toDTO() = SectorDataDTO(
    sectorId = gondola.sector.idSector,
    productoId = producto.idProducto,
    productoNombre = producto.nombre,
    gondolaNombre = gondola.nombre,
    presentacionDesc = presentacion.descPresentacion,
  )
}

@Embeddable
data class GondolaProductoId(
  @Column(name = "id_gondola")
  val idGondola: Long,
  @Column(name = "id_producto")
  val idProducto: Long
) : Serializable