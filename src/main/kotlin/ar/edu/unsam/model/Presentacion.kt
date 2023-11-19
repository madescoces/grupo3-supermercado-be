package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "presentacion")
data class Presentacion (val desc:String = "") {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var idPresentacion:Int = 0

  fun toDTO() = PresentacionDTO(
    id = idPresentacion,
    desc = desc
  )
}

data class PresentacionDTO(
  val id: Int,
  val desc: String
)