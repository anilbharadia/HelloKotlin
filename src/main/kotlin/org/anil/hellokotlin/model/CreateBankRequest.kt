package org.anil.hellokotlin.model

import javax.validation.constraints.NotBlank

data class CreateBankRequest(
        @field:NotBlank
        val name: String = ""
)