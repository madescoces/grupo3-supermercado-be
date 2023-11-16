package ar.edu.unsam.model
import jakarta.persistence.*

@Entity
@Table(name = "Repositor")
data class Repositor( val nombre:String = "" ) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idRepositor:Int = 0

    fun toDTO() = RepositorDTO(
        id = this.idRepositor,
        name = this.nombre

    )
}