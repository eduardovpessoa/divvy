package com.eduardovpessoa.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardovpessoa.data.dto.Company
import com.eduardovpessoa.ui.companies.CompaniesScreen
import com.eduardovpessoa.ui.companydetails.CompanyDetailsScreen

@ExperimentalMaterial3Api
@Composable
fun NavConfigurationUtil(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "companies"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        var company: Company? = null
        composable("companies") {
            CompaniesScreen(
                onNavigateToCompanyDetails = {
                    company = it
                    navController.navigate("company-details")
                }
            )
        }
        composable("company-details") {
            company?.let {
                CompanyDetailsScreen(company = it) {
                    navController.popBackStack()
                }
            }
        }
    }

}