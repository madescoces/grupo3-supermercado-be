package ar.edu.unsam.repository

import ar.edu.unsam.model.*
import jakarta.persistence.Tuple

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SectorRepository:JpaRepository<Sector, Int>

@Repository
interface FilaRepository:JpaRepository<Fila, Int>

@Repository
interface EmpresaRepository:JpaRepository<Empresa, Int>

@Repository
interface PresentacionRepository:JpaRepository<Presentacion, Int>

@Repository
interface RepositorRepository:JpaRepository<Repositor, Int>

@Repository
interface GondolaRepository:JpaRepository<Gondola, Int>

@Repository
interface ProductoRepository:JpaRepository<Producto, Int> {

  /*@Query("SELECT REP.idRepositor, PD.id, PD.nombre, GDL.nombre, PR.desc FROM Repositor REP " +
          "JOIN GondolaProductoRepositor GPR " +
          "JOIN GPR.gondola GDL " +
          "JOIN GDL.gondolaProducto GP " +
          "JOIN GP.presentacion PR " +
          "JOIN GP.producto PD " +
          "WHERE REP.id = :id")
  fun getProductoByRepositor(idRepositor:Int): Optional<Producto>*/
}

@Repository
interface GondolaProductoRepository:JpaRepository<GondolaProducto, GondolaProductoId>{
  @Query("SELECT NEW ar.edu.unsam.model.SectorDataDTO(ST.idSector, PD.idProducto, PD.nombreProducto, GDL.nombreGondola, PR.descPresentacion) " +
    "FROM Producto PD " +
    "JOIN GondolaProducto GP ON PD.idProducto = GP.idProducto " +
    "JOIN Presentacion PR ON GP.idPresentacion = PR.idPresentacion " +
    "JOIN Gondola GDL ON GP.idGondola = GDL.idGondola " +
    "JOIN Sector ST ON GDL.idSector = ST.idSector " +
    "WHERE ST.idSector = :idSector")
  fun getProductoBySector(idSector: Int): List<SectorDataDTO>
}

@Repository
interface GondolaProductoRepositorRepository:JpaRepository<GondolaProductoRepositor, GondolaProductoRepositorId>{
  @Query("SELECT NEW ar.edu.unsam.model.RepositorDataDTO(RP.idRepositor, PD.idProducto, PD.nombreProducto, GDL.nombreGondola, PR.descPresentacion) " +
    "FROM Producto PD " +
    "JOIN GondolaProductoRepositor GPR ON PD.idProducto = GPR.gondolaProducto.idProducto " +
    "JOIN Presentacion PR ON GPR.gondolaProducto.idPresentacion = PR.idPresentacion " +
    "JOIN Gondola GDL ON GPR.gondolaProducto.idGondola = GDL.idGondola " +
    "JOIN Repositor RP ON GPR.repositor.idRepositor = RP.idRepositor " +
    "WHERE RP.idRepositor = :idRepositor")
  fun getProductoByRepositor(idRepositor: Int): List<RepositorDataDTO>
}