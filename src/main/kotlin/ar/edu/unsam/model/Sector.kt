package ar.edu.unsam.model

import jakarta.persistence.*

@Entity
@Table(name = "sector")
data class Sector( val descSector:String = "" ) {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var idSector:Int = 0
}