package ar.edu.unsam.repository

import ar.edu.unsam.ar.edu.unsam.model.GondolaProducto
import ar.edu.unsam.model.Repositor
import ar.edu.unsam.model.Sector
import ar.edu.unsam.model.Gondola
import ar.edu.unsam.model.Producto

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SectorRepository:JpaRepository<Sector, Int>

@Repository
interface RepositorRepository:JpaRepository<Repositor, Int>

@Repository
interface GondolaRepository:JpaRepository<Gondola, Int>

@Repository
interface ProductoRepository:JpaRepository<Producto, Int>

@Repository
interface GondolaProductoRepository:JpaRepository<GondolaProducto, Int>