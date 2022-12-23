package com.eduardovpessoa.di

import com.eduardovpessoa.ui.companies.CompaniesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CompaniesViewModel(getCompaniesUseCase = get()) }
}