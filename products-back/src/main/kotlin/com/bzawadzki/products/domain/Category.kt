package com.bzawadzki.products.domain

import com.bzawadzki.products.annotation.NoArg
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@NoArg
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column
        val name: String = "ALL"
) : Serializable {

    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "category")
    private var products: Set<Product> = Collections.emptySet()

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other === null || other !is Category) {
            return false
        }
        if (this::class != other::class) {
            return false
        }
        return name == other.name
    }
}
