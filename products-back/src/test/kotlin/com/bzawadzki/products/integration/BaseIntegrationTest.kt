package com.bzawadzki.products.integration

import com.bzawadzki.products.controller.product
import com.bzawadzki.products.controller.products
import com.bzawadzki.products.controller.productsFind
import com.bzawadzki.products.domain.Product
import com.bzawadzki.products.service.CategoryService
import com.bzawadzki.products.service.ProductService
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class BaseIntegrationTest {

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var productService: ProductService

    @Value("\${server.port}")
    val port: Int = 80

    @Value("\${server.servlet.context-path}")
    val pathPrefix: String = "/api"

    @BeforeEach
    fun setPort() {
        RestAssured.port = port
    }

    val productsPath = pathPrefix + products
    val productPath = pathPrefix + product
    val productsFindPath = pathPrefix + productsFind

    fun getProductToCreate(name: String): Product {
        productService.findAndDeleteByName(name)
        return Product(name = name,
                description = "desc",
                category = categoryService.findByName("ALL").get(),
                netPrice = BigDecimal.valueOf(100),
                tax = 10,
                marketPrice = BigDecimal.valueOf(110))
    }

}