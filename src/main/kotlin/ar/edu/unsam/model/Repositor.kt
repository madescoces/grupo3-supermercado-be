package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "repositor")
data class Repositor(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val idRepositor:Int = -1,
  val nombreRepositor:String
) {
  @OneToMany(mappedBy = "repositor")
  val gondolaProductoRepositor: List<GondolaProductoRepositor> = listOf()

  @ManyToOne
  @JoinColumn(name = "id_empresa", insertable = false, updatable = false)
  lateinit var empresa: Empresa

  fun toDTO() = RepositorDTO(
    id = this.idRepositor,
    name = this.nombreRepositor,
    empresa = this.empresa.razonSocial
  )
}

data class RepositorDTO(
  val id: Int,
  val name: String,
  val empresa: String
)