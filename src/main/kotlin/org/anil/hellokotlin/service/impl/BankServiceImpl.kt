package org.anil.hellokotlin.service.impl

import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.repository.BankRepository
import org.anil.hellokotlin.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BankServiceImpl(
        @Autowired
        val repository: BankRepository
) : BankService {


    override fun create(name: String): Bank {
        return repository.saveAndFlush(Bank(name))
    }
}