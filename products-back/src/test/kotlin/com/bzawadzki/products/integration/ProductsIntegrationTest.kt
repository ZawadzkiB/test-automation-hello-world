package com.bzawadzki.products.integration

import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class ProductsIntegrationTest {

    @Value("\${server.port}")
    val port: Int = 0

    @Test
    fun getProduct() {
        given().baseUri("http://localhost")
                .port(port)
                .pathParam("id", 1)
                .`when`()
                .get("api/v1/products/{id}")
                .then()
                .statusCode(200)
    }
}