package com.bzawadzki.products.service

import com.bzawadzki.products.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryService(private val products: CategoryRepository) {

}