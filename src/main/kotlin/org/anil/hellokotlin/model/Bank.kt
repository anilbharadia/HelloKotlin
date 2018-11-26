package org.anil.hellokotlin.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
class Bank(
        val name: String
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Int? = null

}