package com.eduardovpessoa.di

import com.eduardovpessoa.data.remote.CompanyRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CompanyRepository(api = get()) }
}