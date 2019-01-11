package org.anil.hellokotlin.service

import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.model.CreateBankRequest
import java.util.*

interface BankService {

    fun create(request: CreateBankRequest): Bank

    fun findById(id: Int): Optional<Bank>

    fun findAll(): List<Bank>

}