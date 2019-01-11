package org.anil.hellokotlin.resources

import com.fasterxml.jackson.databind.ObjectMapper
import org.anil.hellokotlin.model.Bank
import org.anil.hellokotlin.model.CreateBankRequest
import org.anil.hellokotlin.repository.BankRepository
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
internal class BankResourceIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: BankRepository

    @BeforeEach
    fun cleanupDatabase() {
        repository.deleteAll()
    }

    @Test
    fun create_a_new_bank() {
        val request = CreateBankRequest("SBI")
        val requestBody = ObjectMapper().writeValueAsString(request)

        val post = post("/banks").content(requestBody).contentType(APPLICATION_JSON)
        mockMvc.perform(post)
                .andExpect(status().isCreated)
                .andExpect(header().string("location", containsString("/banks/")))

        assertEquals(1, repository.count())
        var bank = repository.findAll().first()
        assertEquals("SBI", bank.name)
    }

    @Test
    fun create_a_new_bank__with_invalid_json() {
        val requestBody = "{\"name2\": \"SBI\"}"

        val post = post("/banks").content(requestBody).contentType(APPLICATION_JSON)
        mockMvc.perform(post)
                .andExpect(status().isBadRequest)

        assertEquals(0, repository.count())
    }

    @Test
    fun get_bank_details() {
        var bank = repository.saveAndFlush(Bank("SBI"))

        mockMvc.perform(get("/banks/" + bank.id))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", equalTo("SBI")))
    }

    @Test
    fun get_bank_details__by_invalid_ID() {
        mockMvc.perform(get("/banks/" + Integer.MAX_VALUE))
                .andExpect(status().isNotFound)
    }

    @Test
    fun get_all_banks() {
        repository.saveAndFlush(Bank("SBI"))
        repository.saveAndFlush(Bank("HDFC"))
        repository.saveAndFlush(Bank("ICICI"))

        mockMvc.perform(get("/banks"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(3)))
                .andExpect(jsonPath("$[0].name", `is`("SBI")))
    }
}
