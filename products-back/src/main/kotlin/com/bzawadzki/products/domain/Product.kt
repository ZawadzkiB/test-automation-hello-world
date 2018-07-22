package com.bzawadzki.products.domain

import com.bzawadzki.products.annotation.NoArg
import java.io.Serializable
import javax.persistence.*

@Entity
@NoArg
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true)
        val name: String = "default",

        val description: String = "",

        @ManyToOne
        @JoinColumn(name = "category")
        val category: Category,

        @Column(name = "net_price", nullable = false)
        val netPrice: Long,

        @Column(nullable = false)
        val tax: Int,

        @Column(name = "market_price", nullable = false)
        val marketPrice: Long
) : Serializable {
    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other === null || other !is Product) {
            return false
        }
        if (this::class != other::class) {
            return false
        }
        return name == other.name
    }
}