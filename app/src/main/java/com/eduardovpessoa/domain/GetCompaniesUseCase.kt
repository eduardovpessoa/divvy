package com.eduardovpessoa.domain

import android.util.Log
import com.eduardovpessoa.data.dto.Companies
import com.eduardovpessoa.data.remote.CompanyRepository
import com.eduardovpessoa.ui.companies.CompaniesScreenUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCompaniesUseCase constructor(private val repository: CompanyRepository) {
    suspend operator fun invoke(): Flow<CompaniesScreenUI> = flow {
        try {
            repository.getCompanies().run {
                if (isSuccessful) {
                    body()?.let { resp ->
                        emit(mapResponseToUI(resp))
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e(GetCompaniesUseCase::class.java.name, ex.message.orEmpty())
        }
    }


    private fun mapResponseToUI(response: Companies): CompaniesScreenUI {
        return CompaniesScreenUI(
            companies = response
        )
    }
}