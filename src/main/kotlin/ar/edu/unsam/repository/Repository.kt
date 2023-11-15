package ar.edu.unsam.repository

import ar.edu.unsam.data.ERROR_ID_NOT_FOUND
import ar.edu.unsam.errors.NotFoundException
import ar.edu.unsam.model.Sector

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SectorRepository:JpaRepository<Sector, Int>