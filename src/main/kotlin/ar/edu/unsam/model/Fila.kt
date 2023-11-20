package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "fila_producto")
data class Fila(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idFila:Long,
  val descFila:String = ""
) {
  fun toDTO() = FilaDTO(
    id = this.idFila,
    name = this.descFila
  )
}

data class FilaDTO(
  val id: Long,
  val name: String
)