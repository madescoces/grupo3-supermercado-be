package ar.edu.unsam.controller

import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Repositor
import ar.edu.unsam.model.RepositorDTO
import ar.edu.unsam.service.RepositorService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/repositores")
class RepositorController {
  @Autowired
  val repositorService: RepositorService? = null

  @GetMapping
  @Operation(summary = "Trae todos los repositores")
  fun getAll(): ResponseEntity<List<RepositorDTO>>{
    return try{
      ResponseEntity(repositorService!!.getAll(), HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/{id}")
  fun getByID(@PathVariable("id") idRepositor:Int): ResponseEntity<Repositor>{
    return try{
      ResponseEntity(repositorService!!.getById(idRepositor), HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @PostMapping
  fun create (@RequestBody repositor:Repositor): ResponseEntity<Any>{
    return try{
      repositorService!!.create(repositor)
      val responseHeader = HttpHeaders()
      responseHeader.set("location","/repositores" + "/" + repositor.idRepositor)
      ResponseEntity(responseHeader, HttpStatus.CREATED)
    } catch (e: BusinessException) {
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PutMapping
  fun update (@RequestBody repositor:Repositor): ResponseEntity<Any>{
    return try{
      repositorService!!.create(repositor)
      ResponseEntity(HttpStatus.OK)
    } catch (e: BusinessException) {
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") idRepositor:Int): ResponseEntity<Repositor>{
    return try{
      repositorService!!.delete(idRepositor)
      ResponseEntity(HttpStatus.OK)
    } catch (e:Exception){
      e.printStackTrace()
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }
}