package com.bzawadzki.products.unit

import com.bzawadzki.products.domain.Product
import com.bzawadzki.products.repository.ProductRepository
import com.bzawadzki.products.service.ProductService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProductsUnitTest {

    @Mock
    lateinit var productRepository: ProductRepository

    @InjectMocks
    lateinit var productService: ProductService

    @Test
    fun `should return empty when no product found`() {
        productService.findByName("bed") shouldBe Optional.empty()
    }

    @Test
    fun `should return product`() {
        doReturn(Optional.of(Product())).`when`(productRepository).findByName(any())
        productService.findByName("bed") shouldEqual Optional.of(Product())
    }

    @Test
    fun `should return empty when trying to create product that exists`() {
        doReturn(Optional.of(Product())).`when`(productRepository).findByName(any())
        productService.tryCreate(Product()) shouldBe Optional.empty()
    }

    @Test
    fun `should return product when save product`() {
        doReturn(Product()).`when`(productRepository).save(any())
        productService.tryCreate(Product()) shouldEqual Optional.of(Product())
    }

    @Test
    fun `should return empty when trying to delete product that not exists`() {
        productService.findAndDelete(1L) shouldBe Optional.empty()
    }

    @Test
    fun `should return optional is present when delete product`() {
        doReturn(Optional.of(Product())).`when`(productRepository).findById(any())
        productService.findAndDelete(1L) shouldEqual Optional.of(Unit)
    }

    @Test
    fun `should return empty when trying to update product that not exists`() {
        productService.findAndUpdate(Product()) shouldBe Optional.empty()
    }

    @Test
    fun `should return product when update product`() {
        doReturn(Optional.of(Product())).`when`(productRepository).findById(any())
        doReturn(Product()).`when`(productRepository).save(any())
        productService.findAndUpdate(Product()) shouldEqual Optional.of(Product())
    }

}