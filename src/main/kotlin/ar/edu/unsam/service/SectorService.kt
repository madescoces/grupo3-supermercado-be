package ar.edu.unsam.service

import ar.edu.unsam.data.ERROR_ID_NOT_FOUND
import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Sector
import ar.edu.unsam.model.SectorDTO
import ar.edu.unsam.repository.SectorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface ISectorService {
  fun getAll(): List<SectorDTO>
  fun getById(idSector:Long):Sector
  fun create(sector: Sector):Sector
  fun delete(idSector: Long)
}

@Service
class SectorService : ISectorService{
  @Autowired
  val sectorRepository: SectorRepository? = null

  @Throws(BusinessException::class)
  override fun getAll(): List<SectorDTO> {
    try {
      return sectorRepository!!.findAll().map { sector -> sector.toDTO() }
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }

  @Throws(BusinessException::class, NotFoundException::class)
  override fun getById(idSector:Long):Sector{
    val op: Optional<Sector>
    try {
      op = sectorRepository!!.findById(idSector)
    } catch (e:Exception) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }

    if(!op.isPresent) {
        throw NotFoundException(ERROR_ID_NOT_FOUND)
    }
    return op.get()
  }

  @Throws(BusinessException::class)
  override fun create(sector: Sector): Sector {
    try {
      return sectorRepository!!.save(sector)
    } catch (e:Exception){
      throw BusinessException(e.message!!)
    }
  }
  @Throws(BusinessException::class, NotFoundException::class)
  override fun delete(idSector: Long) {
    val op: Optional<Sector>
    try {
      op = sectorRepository!!.findById(idSector)
    } catch (e:Exception) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }

    if (!op.isPresent){
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }
    else {
      try {
        sectorRepository!!.deleteById(idSector)
      } catch (e:Exception) {
        throw BusinessException(e.message!!)
      }
    }
  }
}