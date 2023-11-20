package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "empresa")
data class Empresa(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val idEmpresa:Long,
  val razonSocial:String,
  val domicilio:String
) {
  fun toDTO() = EmpresaDTO(
    id = this.idEmpresa,
    name = this.razonSocial,
    domicilio = this.domicilio
  )
}

data class EmpresaDTO(
  val id: Long,
  val name: String,
  val domicilio: String
)