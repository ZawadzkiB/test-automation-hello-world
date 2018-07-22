package com.bzawadzki.products.controller

import com.bzawadzki.products.domain.Product
import com.bzawadzki.products.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8080"], allowCredentials = "true")
@RestController
class ProductController(
        private val productsService: ProductService
) {

    @GetMapping("/v1/products")
    fun getAllProducts(@RequestParam(required = false, defaultValue = "0") page: Int,
                       @RequestParam(required = false, defaultValue = "10") limit: Int): Page<Product>? = productsService.findAll(PageRequest.of(page, limit))

    @GetMapping("/v1/products/{id}")
    fun getProductsById(@PathVariable(value = "id") id: Long) = productsService.findById(id)

    @GetMapping("/v1/products/find")
    fun getProductsByName(@RequestParam(required = true, defaultValue = "") name: String) = productsService.findByName(name)

    @PostMapping("/v1/product")
    fun postProduct(@RequestBody(required = true)product: Product) = productsService.tryCreate(product)

    @PostMapping("/v1/products")
    fun postProducts(@RequestBody(required = true)products: List<Product>) = productsService.tryCreateBulk(products)

    @PutMapping("/v1/product")
    fun putProduct(@RequestBody(required = true)product: Product) = productsService.findAndUpdate(product)

    @DeleteMapping("v1/product")
    fun deleteProduct(@RequestParam(required = true, defaultValue = "-1")id: Long) = productsService.findAndDelete(id)
}