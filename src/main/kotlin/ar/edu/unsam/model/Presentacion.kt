package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "presentacion")
data class Presentacion (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val idPresentacion:Int = -1,
  val descPresentacion:String = ""
) {
  @OneToMany(mappedBy = "presentacion")
  val godonlaProducto:List<GondolaProducto> = listOf()

  fun toDTO() = PresentacionDTO(
    id = this.idPresentacion,
    desc = this.descPresentacion
  )
}

data class PresentacionDTO(
  val id: Int,
  val desc: String
)