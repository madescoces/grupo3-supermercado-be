package ar.edu.unsam.model

import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
@Table(name = "gondola_producto_repositor")
data class GondolaProductoRepositor(
  @Column(name = "id_gpr", insertable = false, updatable = false)
  val idGpr: Int,

  @EmbeddedId
  val id: GondolaProductoRepositorId,

  @ManyToOne
  @JoinColumn(name = "id_repositor", referencedColumnName = "idRepositor", insertable = false, updatable = false)
  val repositor: Repositor,

  @ManyToOne
  @JoinColumns(
    JoinColumn(name = "idGondola", referencedColumnName = "id_gondola", insertable = false, updatable = false),
    JoinColumn(name = "idProducto", referencedColumnName = "id_producto", insertable = false, updatable = false)
  )
  val gondolaProducto: GondolaProducto,

  @Column(name = "fecha", insertable = false, updatable = false)
  val fecha: Timestamp,

  val cantidad: BigDecimal
)

@Embeddable
data class GondolaProductoRepositorId(
  @Embedded
  val idGondolaProducto: GondolaProductoId,

  @Column(name = "id_repositor")
  val idRepositor: Int = -1
) : Serializable