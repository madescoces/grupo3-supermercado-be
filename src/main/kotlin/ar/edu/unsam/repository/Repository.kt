package ar.edu.unsam.repository

import ar.edu.unsam.model.Repositor
import ar.edu.unsam.model.Sector

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SectorRepository:JpaRepository<Sector, Int>
@Repository
interface RepositorRepository:JpaRepository<Repositor, Int>