package org.anil.hellokotlin.service.impl

import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.model.CreateBankRequest
import org.anil.hellokotlin.repository.BankRepository
import org.anil.hellokotlin.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BankServiceImpl(
        @Autowired
        val repository: BankRepository
) : BankService {

    override fun findAll(): List<Bank> = repository.findAll()

    override fun findById(id: Int): Optional<Bank> = repository.findById(id)

    override fun create(request: CreateBankRequest): Bank = repository.saveAndFlush(Bank(request.name))
}