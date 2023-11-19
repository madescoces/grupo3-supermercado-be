package ar.edu.unsam.service

import ar.edu.unsam.ar.edu.unsam.model.GondolaProductoDTO
import ar.edu.unsam.data.ERROR_ID_NOT_FOUND
import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Producto
import ar.edu.unsam.model.ProductoDTO
import ar.edu.unsam.repository.GondolaProductoRepository
import ar.edu.unsam.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface IProductoService {
  fun getAll(): List<ProductoDTO>
  fun getById(idProducto:Int):Producto
  fun getBySector(idSector:Int): List<GondolaProductoDTO>
}

@Service
class ProductoService : IProductoService{
  @Autowired
  val productoRepository: ProductoRepository? = null

  @Autowired
  val gondolaProductoRepository: GondolaProductoRepository? = null

  @Throws(BusinessException::class)
  override fun getAll(): List<ProductoDTO> {
    try {
      return productoRepository!!.findAll().map{ producto -> producto.toDTO()}
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }

  @Throws(BusinessException::class, NotFoundException::class)
  override fun getById(idProducto: Int):Producto{
    val op: Optional<Producto>
    try {
      op = productoRepository!!.findById(idProducto)
    } catch (e:Exception) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }

    if(!op.isPresent) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }
    return op.get()
  }

  @Throws(BusinessException::class)
  override fun getBySector(idSector: Int): List<GondolaProductoDTO> {
    try {
      val lista = gondolaProductoRepository!!.findAll().map { elemento -> elemento.toDTO() }
      return lista.filter { it.sectorId == idSector }
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }
}