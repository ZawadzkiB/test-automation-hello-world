package com.bzawadzki.products.service

import com.bzawadzki.products.domain.Category
import com.bzawadzki.products.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CategoryService(private val categoryRepository: CategoryRepository) {


    fun tryCreateCategory(category: Category): Optional<Category> {
        if(categoryRepository.findByName(category.name).isPresent){
            return Optional.empty()
        }
        return Optional.of(categoryRepository.save(category))
    }

    fun findByName(name: String): Optional<Category> {
        return categoryRepository.findByName(name)
    }
}