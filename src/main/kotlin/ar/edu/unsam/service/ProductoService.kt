package ar.edu.unsam.service

import ar.edu.unsam.data.ERROR_ID_NOT_FOUND
import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Producto
import ar.edu.unsam.model.ProductoDTO
import ar.edu.unsam.model.RepositorDataDTO
import ar.edu.unsam.model.SectorDataDTO
import ar.edu.unsam.repository.GondolaProductoRepositorRepository
import ar.edu.unsam.repository.GondolaProductoRepository
import ar.edu.unsam.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface IProductoService {
  fun getAll(): List<ProductoDTO>
  fun getById(idProducto:Long):Producto
  fun getBySector(idSector:Long): List<SectorDataDTO>
  fun getByRepositor(idRepositor: Long): List<RepositorDataDTO>
  fun create(producto: Producto): Producto
  fun delete(idProducto: Long)
}

@Service
class ProductoService : IProductoService{
  @Autowired
  val productoRepository: ProductoRepository? = null

  @Autowired
  val gondolaProductoRepository: GondolaProductoRepository? = null

  @Autowired
  val gondolaProductoRepositorRepository: GondolaProductoRepositorRepository? = null

  @Throws(BusinessException::class)
  override fun getAll(): List<ProductoDTO> {
    try {
      return productoRepository!!.findAll().map{ producto -> producto.toDTO()}
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }

  @Throws(BusinessException::class, NotFoundException::class)
  override fun getById(idProducto: Long):Producto{
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
  override fun getBySector(idSector: Long): List<SectorDataDTO> {
    try {
      val lista = gondolaProductoRepository!!.findAll().map { elemento -> elemento.toDTO() }
      return lista.filter { it.sectorId == idSector }
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }

  @Throws(BusinessException::class)
  override fun getByRepositor(idRepositor: Long): List<RepositorDataDTO> {
    try {
      val lista = gondolaProductoRepositorRepository!!.findAll().map { elemento -> elemento.toDTO() }
      println(lista)
      return lista.filter { it.repositorId == idRepositor }
    } catch (e: Exception) {
      throw BusinessException(e.message!!)
    }
  }

  @Throws(BusinessException::class)
  override fun create(producto: Producto): Producto {
    try {
      return productoRepository!!.save(producto)
    } catch (e:Exception){
      throw BusinessException(e.message!!)
    }
  }
  @Throws(BusinessException::class, NotFoundException::class)
  override fun delete(idProducto: Long) {
    val op: Optional<Producto>
    try {
      op = productoRepository!!.findById(idProducto)
    } catch (e:Exception) {
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }

    if (!op.isPresent){
      throw NotFoundException(ERROR_ID_NOT_FOUND)
    }
    else {
      try {
        productoRepository!!.deleteById(idProducto)
      } catch (e:Exception) {
        throw BusinessException(e.message!!)
      }
    }
  }
}