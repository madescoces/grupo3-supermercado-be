package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "empresa")
data class Empresa(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val idEmpresa:Int = -1,
  val razonSocial:String,
  val domicilio:String
) {
  @OneToMany(mappedBy = "empresa")
  var repositores: List<Repositor> = listOf()

  fun toDTO() = EmpresaDTO(
    id = this.idEmpresa,
    name = this.razonSocial,
    domicilio = this.domicilio
  )
}

data class EmpresaDTO(
  val id: Int,
  val name: String,
  val domicilio: String
)