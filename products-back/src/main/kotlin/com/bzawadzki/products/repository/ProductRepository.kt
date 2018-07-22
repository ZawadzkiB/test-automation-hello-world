package com.bzawadzki.products.repository

import com.bzawadzki.products.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository: JpaRepository<Product, Long> {

    fun findByName(name: String): Optional<Product>
}