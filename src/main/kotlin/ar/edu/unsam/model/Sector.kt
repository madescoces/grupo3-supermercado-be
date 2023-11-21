package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "sector")
data class Sector(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idSector:Int = -1,
  val descSector:String
) {
  @OneToMany(mappedBy = "sector")
  var gondolas: List<Gondola> = listOf()
  fun toDTO() = SectorDTO(
    id = this.idSector,
    name = this.descSector
  )
}

data class SectorDTO(
  val id: Int,
  val name: String
)