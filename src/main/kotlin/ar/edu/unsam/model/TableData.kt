package ar.edu.unsam.model
abstract class BaseDataDTO(
  open val productoId: Int,
  open val productoNombre: String,
  open val gondolaNombre: String,
  open val presentacionDesc: String
)

data class RepositorDataDTO(
  val repositorId: Int,
  override val productoId: Int,
  override val productoNombre: String,
  override val gondolaNombre: String,
  override val presentacionDesc: String
) : BaseDataDTO(productoId, productoNombre, gondolaNombre, presentacionDesc)

data class SectorDataDTO(
  val sectorId: Int,
  override val productoId: Int,
  override val productoNombre: String,
  override val gondolaNombre: String,
  override val presentacionDesc: String
) : BaseDataDTO(productoId, productoNombre, gondolaNombre, presentacionDesc)