package com.eduardovpessoa.data

import com.eduardovpessoa.data.dto.Companies
import retrofit2.Response
import retrofit2.http.GET

interface DivvyTestApi {
    @GET("cb0bbbd65cf5804b40018f6dbe013229d9bd1d6c/data.json")
    suspend fun getCompanies() : Response<Companies>
}