package ar.edu.unsam.ar.edu.unsam.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan

@Configuration
@EnableJpaRepositories(basePackages = ["ar.edu.unsam"])
@EntityScan(basePackages = ["ar.edu.unsam.model"])
class JpaConfig
