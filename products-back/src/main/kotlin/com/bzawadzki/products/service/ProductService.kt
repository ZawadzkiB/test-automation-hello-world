package com.bzawadzki.products.service

import com.bzawadzki.products.annotation.ForTesting
import com.bzawadzki.products.domain.Product
import com.bzawadzki.products.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ProductService(
        private val products: ProductRepository
) {

    fun tryCreate(product: Product): Optional<Product> {
        if (products.findByName(product.name).isPresent)
            return Optional.empty()
        return Optional.of(products.save(product))
    }

    fun tryCreateBulk(products: List<Product>) = products.forEach { tryCreate(it) }

    fun findByName(name: String) = products.findByName(name)

    fun findAll(pageRequest: PageRequest): Page<Product>? = products.findAll(pageRequest)

    fun findById(id: Long): Optional<Product> = products.findById(id)

    fun findAndUpdate(product: Product): Optional<Product> {
        if (products.findById(product.id).isPresent)
            return Optional.of(products.save(product))
        return Optional.empty()
    }

    fun findAndDelete(id: Long): Optional<Unit> {
        val product = products.findById(id)
        if (products.findById(id).isPresent)
            return Optional.of(products.delete(product.get()))
        return Optional.empty()
    }

    @ForTesting
    fun findAndDeleteByName(name: String): Optional<Unit> {
        val id = findByName(name).orElseGet{Product(id=0)}.id
        return findAndDelete(id)
    }
}