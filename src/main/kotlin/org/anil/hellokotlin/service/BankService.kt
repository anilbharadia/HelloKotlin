package org.anil.hellokotlin.service

import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.model.CreateBankRequest
import java.util.*

interface BankService {

    fun create(name: CreateBankRequest): Bank

    fun get(id: Int): Optional<Bank>

}