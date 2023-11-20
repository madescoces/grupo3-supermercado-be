package ar.edu.unsam.controller

import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Producto
import ar.edu.unsam.model.ProductoDTO
import ar.edu.unsam.model.RepositorDataDTO
import ar.edu.unsam.model.SectorDataDTO
import ar.edu.unsam.service.ProductoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/productos")
class ProductoController {
  @Autowired
  val productoService: ProductoService? = null

  @GetMapping
  @Operation(summary = "Trae todos los productos")
  fun getAll(): ResponseEntity<List<ProductoDTO>>{
    return try{
      ResponseEntity(productoService!!.getAll(), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/{id}")
  fun getByID(@PathVariable("id") idProducto:Long): ResponseEntity<Producto>{
    return try{
      ResponseEntity(productoService!!.getById(idProducto), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @GetMapping("/sector/{idSector}")
  @Operation(summary = "Trae todos los productos que correspondan a un sector")
  fun getBySector(@PathVariable("idSector") idSector:Long): ResponseEntity<List<SectorDataDTO>>{
    return try{
      ResponseEntity(productoService!!.getBySector(idSector), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/repositor/{idRepositor}")
  @Operation(summary = "Trae todos los productos que correspondan a un repositor")
  fun getByRepositor(@PathVariable("idRepositor") idRepositor:Long): ResponseEntity<List<RepositorDataDTO>>{
    return try{
      ResponseEntity(productoService!!.getByRepositor(idRepositor), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PostMapping
  fun create (@RequestBody producto:Producto): ResponseEntity<Any>{
    return try{
      productoService!!.create(producto)
      val responseHeader = HttpHeaders()
      responseHeader.set("location","/productos" + "/" + producto.idProducto)
      ResponseEntity(responseHeader, HttpStatus.CREATED)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}