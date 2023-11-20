package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "gondola")
data class Gondola (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idGondola:Long,

  val nombre:String = "",

  @ManyToOne
  @JoinColumn(name="id_sector")
  val sector:Sector
){
  fun toDTO() = GondolaDTO(
    id = idGondola,
    name = nombre,
    idSector = sector.idSector
  )
}

data class GondolaDTO(
  val id: Long,
  val name: String,
  val idSector: Long
)
