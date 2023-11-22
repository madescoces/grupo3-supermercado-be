package ar.edu.unsam.model

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "gondola_producto")
@IdClass(GondolaProductoId::class)
data class GondolaProducto(
  @Id
  @Column(name = "id_gondola", insertable = false, updatable = false)
  val idGondola: Int = -1,

  @Id
  @Column(name = "id_producto", insertable = false, updatable = false)
  val idProducto: Int = -1,

  @Id
  @Column(name = "id_presentacion", insertable = false, updatable = false)
  val idPresentacion: Int = -1,

  @ManyToOne
  @JoinColumn(name = "id_gondola", insertable = false, updatable = false)
  val gondola: Gondola,

  @ManyToOne
  @JoinColumn(name = "id_producto", insertable = false, updatable = false)
  val producto: Producto,

  @ManyToOne
  @JoinColumn(name = "id_presentacion", insertable = false, updatable = false)
  val presentacion: Presentacion,

  @OneToMany(mappedBy = "gondolaProducto") // Corregir el nombre del atributo
  val gondolaProductoRepositor: List<GondolaProductoRepositor> = listOf()
) {
  /*fun toDTO() = SectorDataDTO(
    id_sector = gondola.sector.idSector,
    id_producto = producto.idProducto,
    producto = producto.nombreProducto,
    gondola = gondola.nombreGondola,
    presentacion = presentacion.descPresentacion,
  )*/
}

data class GondolaProductoId(
  val idGondola: Int = -1,
  val idProducto: Int = -1
) : Serializable

