package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "sector")
data class Sector(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idSector:Long,
  val descSector:String
) {
  fun toDTO() = SectorDTO(
    id = this.idSector,
    name = this.descSector
  )
}

data class SectorDTO(
  val id: Long,
  val name: String
)