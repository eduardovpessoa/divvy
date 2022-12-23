package com.eduardovpessoa.di

import com.eduardovpessoa.domain.GetCompaniesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCompaniesUseCase(repository = get()) }
}