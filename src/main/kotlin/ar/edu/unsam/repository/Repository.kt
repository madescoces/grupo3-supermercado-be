package ar.edu.unsam.repository

import ar.edu.unsam.model.*

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
interface ProductoRepository:JpaRepository<Producto, Int>

@Repository
interface GondolaProductoRepository:JpaRepository<GondolaProducto, GondolaProductoId>{
  @Query("SELECT NEW ar.edu.unsam.model.ProductoDataDTO(GPR.idGpr, PD.idProducto, PD.nombreProducto, GDL.nombreGondola, PR.descPresentacion) " +
    "FROM Producto PD " +
    "JOIN GondolaProductoRepositor GPR ON PD.idProducto = GPR.gondolaProducto.idProducto " +
    "JOIN Presentacion PR ON GPR.gondolaProducto.idPresentacion = PR.idPresentacion " +
    "JOIN Gondola GDL ON GPR.gondolaProducto.idGondola = GDL.idGondola " +
    "JOIN Sector ST ON GDL.idSector = ST.idSector " +
    "WHERE ST.idSector = :idSector")
  fun getProductoBySector(idSector: Int): List<ProductoDataDTO>
}

@Repository
interface GondolaProductoRepositorRepository:JpaRepository<GondolaProductoRepositor, GondolaProductoRepositorId>{
  @Query("SELECT NEW ar.edu.unsam.model.ProductoDataDTO(GPR.idGpr, PD.idProducto, PD.nombreProducto, GDL.nombreGondola, PR.descPresentacion) " +
    "FROM Producto PD " +
    "JOIN GondolaProductoRepositor GPR ON PD.idProducto = GPR.gondolaProducto.idProducto " +
    "JOIN Presentacion PR ON GPR.gondolaProducto.idPresentacion = PR.idPresentacion " +
    "JOIN Gondola GDL ON GPR.gondolaProducto.idGondola = GDL.idGondola " +
    "JOIN Repositor RP ON GPR.repositor.idRepositor = RP.idRepositor " +
    "WHERE RP.idRepositor = :idRepositor")
  fun getProductoByRepositor(idRepositor: Int): List<ProductoDataDTO>

  @Query("SELECT NEW ar.edu.unsam.model.ProductoFullDataDTO(GPR.idGpr, GPR.fecha, PD.nombreProducto, PD.descProducto, PD.idProductoReemplazo, PR.descPresentacion, FL.descFila, GDL.nombreGondola, ST.descSector, REP.nombreRepositor, EMP.razonSocial, EMP.domicilio ) " +
          "FROM Producto PD " +
          "JOIN Fila FL ON FL.idFila = PD.fila.idFila " +
          "JOIN GondolaProductoRepositor GPR ON PD.idProducto = GPR.gondolaProducto.idProducto " +
          "JOIN Presentacion PR ON GPR.gondolaProducto.idPresentacion = PR.idPresentacion " +
          "JOIN Gondola GDL ON GPR.gondolaProducto.idGondola = GDL.idGondola " +
          "JOIN Sector ST ON ST.idSector = GDL.idSector " +
          "JOIN Repositor REP ON GPR.repositor.idRepositor = REP.idRepositor " +
          "JOIN Empresa EMP ON EMP.idEmpresa = REP.empresa.idEmpresa " +
          "WHERE GPR.idGpr = :id")
  fun getFullProductData(id: Int): ProductoFullDataDTO
}