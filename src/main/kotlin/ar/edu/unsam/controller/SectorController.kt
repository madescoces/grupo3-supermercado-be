package ar.edu.unsam.controller

import ar.edu.unsam.errors.BusinessException
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.service.SectorService
import ar.edu.unsam.model.Sector
import ar.edu.unsam.model.SectorDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/sectores")
class SectorController {
  @Autowired
  val sectorService: SectorService? = null

  @GetMapping
  @Operation(summary = "Trae todos los sectores")
  fun getAll(): ResponseEntity<List<SectorDTO>>{
    return try{
      ResponseEntity(sectorService!!.getAll(), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/{id}")
  fun getByID(@PathVariable("id") idSector:Int): ResponseEntity<Sector>{
    return try{
      ResponseEntity(sectorService!!.getById(idSector), HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @PostMapping
  fun create (@RequestBody sector:Sector): ResponseEntity<Sector>{
    return try{
      sectorService!!.create(sector)
      val responseHeader = HttpHeaders()
      responseHeader.set("location","/sectores" + "/" + sector.idSector)
      ResponseEntity(responseHeader, HttpStatus.CREATED)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PutMapping
  fun update (@RequestBody sector:Sector): ResponseEntity<Sector>{
    return try{
      sectorService!!.create(sector)
      ResponseEntity(HttpStatus.OK)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") idSector:Int): ResponseEntity<Sector>{
    return try{
      sectorService!!.delete(idSector)
      ResponseEntity(HttpStatus.OK)
    } catch (e:Exception){
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e:NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }
}