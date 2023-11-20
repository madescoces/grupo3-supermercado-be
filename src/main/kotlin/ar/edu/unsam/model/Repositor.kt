package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "repositor")
data class Repositor(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val idRepositor:Long,
  val nombre:String,

  @ManyToOne
  @JoinColumn(name = "id_empresa")
  val empresa: Empresa
) {
  fun toDTO() = RepositorDTO(
    id = this.idRepositor,
    name = this.nombre,
    empresa = this.empresa.razonSocial
  )
}

data class RepositorDTO(
  val id: Long,
  val name: String,
  val empresa: String
)