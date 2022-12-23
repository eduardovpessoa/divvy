package com.eduardovpessoa.ui.companies

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eduardovpessoa.R
import com.eduardovpessoa.data.dto.Company
import com.eduardovpessoa.ui.composables.DivvyCircularProgressIndicator
import com.eduardovpessoa.ui.composables.DivvyToolbar
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CompaniesScreen(
    viewModel: CompaniesViewModel = getViewModel(),
    onNavigateToCompanyDetails: (Company) -> Unit
) {
    val companiesScreenUi = viewModel.companies.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getCompanies()
    }

    Scaffold(
        topBar = {
            DivvyToolbar(
                title = stringResource(R.string.company),
                false,
                onBackClick = {})
        },

        content = {
            if (companiesScreenUi.value.companies.isEmpty()) {
                DivvyCircularProgressIndicator(it)
            } else {
                CompaniesScreenList(
                    companiesUi = companiesScreenUi.value,
                    paddingValues = it,
                    onItemClick = { company ->
                        onNavigateToCompanyDetails(company)
                    }
                )
            }
        }
    )
}

@Composable
fun CompaniesScreenList(
    companiesUi: CompaniesScreenUI,
    paddingValues: PaddingValues,
    onItemClick: (Company) -> Unit
) {
    val lazyState = rememberLazyListState()
    LazyColumn(
        state = lazyState,
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            start = 16.dp,
            end = 16.dp
        )
    ) {
        items(companiesUi.companies) { item ->
            CompaniesScreenListItem(item) {
                onItemClick(it)
            }
        }
    }
}

@Composable
fun CompaniesScreenListItem(
    item: Company,
    onItemClick: (Company) -> Unit
) {
    Text(
        text = item.name,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onItemClick(item)
            }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CompaniesScreenPreview() {
    CompaniesScreen { }
}