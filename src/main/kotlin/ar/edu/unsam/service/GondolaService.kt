package ar.edu.unsam.service

import ar.edu.unsam.data.ERROR_ID_NOT_FOUND
import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Gondola
import ar.edu.unsam.model.GondolaDTO
import ar.edu.unsam.repository.GondolaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface IGondolaService {
  fun getAll(): List<GondolaDTO>
  fun getById(idGondola:Long):Gondola
}

@Service
class GondolaService : IGondolaService{
  @Autowired
  val gondolaRepository: GondolaRepository? = null

  @Throws(BusinessException::class)
  override fun getAll(): List<GondolaDTO> {
    try {
      return gondolaRepository!!.findAll().map{ gondola -> gondola.toDTO()}
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }

  @Throws(BusinessException::class, NotFoundException::class)
  override fun getById(idGondola: Long):Gondola{
    val op: Optional<Gondola>
    try {
      op = gondolaRepository!!.findById(idGondola)
    } catch (e:Exception) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }

    if(!op.isPresent) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }
    return op.get()
  }
}