package ar.edu.unsam.model

import java.sql.Timestamp


data class ProductoDataDTO(
  val id: Int,
  val id_producto: Int,
  val producto: String,
  val gondola: String,
  val presentacion: String
)

data class ProductoFullDataDTO(
  val idgpr: Int,
  val fecha: Timestamp,
  val producto: String,
  val descripcion: String,
  val id_reemplazo: Int,
  val presentacion: String,
  val fila: String,
  val gondola: String,
  val sector: String,
  val repositor: String,
  val empresa: String,
  val domicilio_empresa: String
)