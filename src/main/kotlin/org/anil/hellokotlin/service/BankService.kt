package org.anil.hellokotlin.service

import org.anil.hellokotlin.model.Bank

interface BankService {

    fun create(name: String): Bank

}