package com.eduardovpessoa.data.remote

import com.eduardovpessoa.data.DivvyTestApi
import com.eduardovpessoa.data.dto.Companies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CompanyRepository(
    private val api: DivvyTestApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getCompanies(): Response<Companies> {
        return withContext(dispatcher) {
            api.getCompanies()
        }
    }
}