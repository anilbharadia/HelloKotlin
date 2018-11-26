package org.anil.hellokotlin.resources

import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.resources.BankResource.Companion.BANKS_URI
import org.anil.hellokotlin.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI

@Controller
@RequestMapping(BANKS_URI)
class BankResource(
        @Autowired
        val service: BankService
) {

    companion object {
        const val BANKS_URI = "/banks"
    }


    @PostMapping
    fun create(name: String): ResponseEntity<Bank> {
        var bank = service.create(name)
        // TODO build link with spring hateoas
        return ResponseEntity.created(URI.create(BANKS_URI + "/" + bank.id)).build()
    }

}