package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "gondola")
data class Gondola (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idGondola:Int = -1,

  @Column(name = "id_sector", insertable = false, updatable = false)
  val idSector: Int = -1,

  val nombreGondola:String = "",

  @ManyToOne
  @JoinColumn(name = "id_sector", insertable = false, updatable = false)
  val sector:Sector
){
  @OneToMany(mappedBy = "gondola")
  val gondolaProducto:List<GondolaProducto> = listOf()

  fun toDTO() = GondolaDTO(
    id = this.idGondola,
    name = this.nombreGondola,
    idSector = this.sector.idSector
  )
}

data class GondolaDTO(
  val id: Int,
  val name: String,
  val idSector: Int
)
