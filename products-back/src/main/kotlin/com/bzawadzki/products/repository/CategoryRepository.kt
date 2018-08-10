package com.bzawadzki.products.repository

import com.bzawadzki.products.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(propagation = Propagation.MANDATORY)
interface CategoryRepository: JpaRepository<Category, Long> {

    fun findByName(name: String): Optional<Category>
}