package com.bzawadzki.products.controller

import com.bzawadzki.products.domain.Product
import com.bzawadzki.products.exceptions.NotFoundException
import com.bzawadzki.products.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], allowCredentials = "true")
@RestController
class ProductController(
        private val productsService: ProductService
) {

    @GetMapping(products)
    fun getAllProducts(@RequestParam(required = false, defaultValue = "0") page: Int,
                       @RequestParam(required = false, defaultValue = "10") limit: Int): Page<Product>? = productsService.findAll(PageRequest.of(page, limit))

    @GetMapping("$products/{id}")
    fun getProductsById(@PathVariable(value = "id") id: Long): Product = productsService.findById(id)
            .orElseThrow { NotFoundException("Product with id:$id not found") }

    @GetMapping(productsFind)
    fun getProductsByName(@RequestParam(required = true, defaultValue = "") name: String): Product = productsService.findByName(name)
            .orElseThrow { NotFoundException("Product with name:$name not found") }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(product)
    fun postProduct(@RequestBody(required = true) product: Product) = productsService.tryCreate(product)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(products)
    fun postProducts(@RequestBody(required = true) products: List<Product>) = productsService.tryCreateBulk(products)

    @PutMapping(product)
    fun putProduct(@RequestBody(required = true) product: Product) = productsService.findAndUpdate(product)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(product)
    fun deleteProduct(@RequestParam(required = true, defaultValue = "-1") id: Long) = productsService.findAndDelete(id)
}