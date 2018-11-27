package org.anil.hellokotlin.resources

import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.model.CreateBankRequest
import org.anil.hellokotlin.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/banks")
class BankResource(
        @Autowired
        val service: BankService
) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateBankRequest): ResponseEntity<Bank> {
        var bank = service.create(request)
        var link = linkTo(methodOn(BankResource::class.java).get(bank.id!!)).toUri()
        return ResponseEntity.created(link).build()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Bank> {
        var bank = service.get(id)

        return if (bank.isPresent) {
            ResponseEntity.ok(bank.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

}