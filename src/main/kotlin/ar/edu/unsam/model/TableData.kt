package ar.edu.unsam.model
abstract class BaseDataDTO(
  open val productoId: Long,
  open val productoNombre: String,
  open val gondolaNombre: String,
  open val presentacionDesc: String
)

data class RepositorDataDTO(
  val repositorId: Long,
  // Campos heredados de ar.edu.unsam.ar.edu.unsam.model.BaseDataDTO
  override val productoId: Long,
  override val productoNombre: String,
  override val gondolaNombre: String,
  override val presentacionDesc: String
) : BaseDataDTO(productoId, productoNombre, gondolaNombre, presentacionDesc)

data class SectorDataDTO(
  val sectorId: Long,
  // Campos heredados de ar.edu.unsam.ar.edu.unsam.model.BaseDataDTO
  override val productoId: Long,
  override val productoNombre: String,
  override val gondolaNombre: String,
  override val presentacionDesc: String
) : BaseDataDTO(productoId, productoNombre, gondolaNombre, presentacionDesc)