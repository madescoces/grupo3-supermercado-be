package ar.edu.unsam

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Supermarket

fun main(args: Array<String>) {
  runApplication<Supermarket>(*args)
}