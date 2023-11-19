package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "gondola")
data class Gondola (
  val nombre:String = "",
  val idSector:Int = 0
){
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var idGondola:Int = 0

  fun perteneceASector() = idSector

  fun toDTO() = GondolaDTO(
    id = idGondola,
    name = nombre,
    idSector = idSector
  )
}

data class GondolaDTO(
  val id: Int,
  val name: String,
  val idSector: Int
)
