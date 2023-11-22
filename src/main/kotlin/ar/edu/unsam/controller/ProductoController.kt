package ar.edu.unsam.controller

import ar.edu.unsam.errors.*
import ar.edu.unsam.model.*
import ar.edu.unsam.service.ProductoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp

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
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/{id}")
  @Operation(summary = "Trae un producto según su ID")
  fun getByID(@PathVariable("id") idProducto:Int): ResponseEntity<Producto>{
    return try{
      ResponseEntity(productoService!!.getById(idProducto), HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @GetMapping("/sector/{idSector}")
  @Operation(summary = "Trae todos los productos que correspondan a un sector")
  fun getBySector(@PathVariable("idSector") idSector:Int = 0): ResponseEntity<List<ProductoDataDTO>>{
    return try{
      ResponseEntity(productoService!!.getBySector(idSector), HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/producto-full/{idGpr}")
  @Operation(summary = "Trae un producto dada una fecha")
  fun getFullProductData(@PathVariable("idGpr") idGpr: Int): ResponseEntity<ProductoFullDataDTO>{
    return try{
      ResponseEntity(productoService!!.getFullProductData(idGpr), HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }


  @GetMapping("/repositor/{idRepositor}")
  @Operation(summary = "Trae todos los productos que correspondan a un repositor")
  fun getByRepositor(@PathVariable("idRepositor") idRepositor:Int): ResponseEntity<List<ProductoDataDTO>>{
    return try{
      ResponseEntity(productoService!!.getByRepositor(idRepositor), HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
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
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}