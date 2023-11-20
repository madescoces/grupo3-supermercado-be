package ar.edu.unsam.repository

import ar.edu.unsam.ar.edu.unsam.model.GondolaProductoRepositor
import ar.edu.unsam.model.*

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SectorRepository:JpaRepository<Sector, Long>

@Repository
interface FilaRepository:JpaRepository<Fila, Long>

@Repository
interface EmpresaRepository:JpaRepository<Empresa, Long>

@Repository
interface PresentacionRepository:JpaRepository<Presentacion, Long>

@Repository
interface RepositorRepository:JpaRepository<Repositor, Long>

@Repository
interface GondolaRepository:JpaRepository<Gondola, Long>

@Repository
interface ProductoRepository:JpaRepository<Producto, Long>

@Repository
interface GondolaProductoRepository:JpaRepository<GondolaProducto, Long>

@Repository
interface GondolaProductoRepositorRepository:JpaRepository<GondolaProductoRepositor, Long>