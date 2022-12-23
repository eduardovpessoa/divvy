package com.eduardovpessoa.ui.companies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardovpessoa.domain.GetCompaniesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompaniesViewModel(
    private val getCompaniesUseCase: GetCompaniesUseCase
) : ViewModel() {

    private val _companies: MutableStateFlow<CompaniesScreenUI> =
        MutableStateFlow(CompaniesScreenUI(emptyList()))
    val companies = _companies.asStateFlow()

    internal fun getCompanies() {
        viewModelScope.launch {
            getCompaniesUseCase().collect {
                _companies.value = it
            }
        }
    }
}