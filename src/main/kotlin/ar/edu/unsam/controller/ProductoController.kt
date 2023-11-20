package ar.edu.unsam.controller

import ar.edu.unsam.ar.edu.unsam.model.GondolaProductoDTO
import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Producto
import ar.edu.unsam.model.ProductoDTO
import ar.edu.unsam.service.ProductoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/Productos")
class ProductoController {
  @Autowired
  val productoService: ProductoService? = null

  @GetMapping
  @Operation(summary = "Trae todos los prodictos")
  fun getAll(): ResponseEntity<List<ProductoDTO>>{
    return try{
      ResponseEntity(productoService!!.getAll(), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/{id}")
  fun getByID(@PathVariable("id") idProducto:Int): ResponseEntity<Producto>{
    return try{
      ResponseEntity(productoService!!.getById(idProducto), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @GetMapping("/{idSector}")
  @Operation(summary = "Trae todos los productos que correspondan a un sector")
  fun getBySector(@PathVariable("idSector") idSector:Int): ResponseEntity<List<GondolaProductoDTO>>{
    return try{
      ResponseEntity(productoService!!.getBySector(idSector), HttpStatus.OK)
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