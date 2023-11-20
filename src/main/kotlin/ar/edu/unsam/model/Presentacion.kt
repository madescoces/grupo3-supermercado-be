package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "presentacion")
data class Presentacion (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idPresentacion:Long,
  val descPresentacion:String = ""
) {
  fun toDTO() = PresentacionDTO(
    id = this.idPresentacion,
    desc = this.descPresentacion
  )
}

data class PresentacionDTO(
  val id: Long,
  val desc: String
)