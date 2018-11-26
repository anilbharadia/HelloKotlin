package org.anil.hellokotlin.repository

import org.anil.hellokotlin.model.Bank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository : JpaRepository<Bank, Int>