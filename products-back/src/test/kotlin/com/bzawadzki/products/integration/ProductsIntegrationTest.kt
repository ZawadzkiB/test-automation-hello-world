package com.bzawadzki.products.integration

import com.bzawadzki.products.domain.Product
import io.restassured.RestAssured.given
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ProductsIntegrationTest : BaseIntegrationTest() {

    @Test
    fun `should return 200 when searching for product by id`() {
        given().pathParam("id", 1).log().all()
                .get("$productsPath/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().`as`(Product::class.java).id shouldEqual 1
    }

    @Test
    fun `should return 404 when searching for product by id that not exists`() {
        given().pathParam("id", 666)
                .get("$productsPath/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return 400 when search for product with id that is not a number`() {
        given().pathParam("id", "jeden")
                .get("$productsPath/{id}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should return 200 when searching for product by name`() {
        given().param("name", "item1")
                .get(productsFindPath)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().`as`(Product::class.java).name shouldEqual "item1"

    }

    @Test
    fun `should return 404 when searching for product by name that not exists`() {
        given().param("name", "item666")
                .get(productsFindPath)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `should return 201 when creating new product`() {
        val product = getProductToCreate("itemToCreate")
        given().body(product)
                .header("Content-Type", "application/json")
                .post(productPath)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response().`as`(Product::class.java) shouldEqual product
    }
}