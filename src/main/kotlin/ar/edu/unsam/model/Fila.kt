package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "fila_producto")
data class Fila(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var idFila:Int = -1,
  val descFila:String
) {
  @OneToMany(mappedBy = "fila")
  var productos: List<Producto> = listOf()
  fun toDTO() = FilaDTO(
    id = this.idFila,
    name = this.descFila
  )
}

data class FilaDTO(
  val id: Int,
  val name: String
)