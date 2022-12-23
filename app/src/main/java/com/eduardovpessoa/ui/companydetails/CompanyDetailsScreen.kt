package com.eduardovpessoa.ui.companydetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eduardovpessoa.R
import com.eduardovpessoa.data.dto.Company
import com.eduardovpessoa.data.dto.Revenue
import com.eduardovpessoa.ui.composables.DivvyToolbar
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CompanyDetailsScreen(
    company: Company,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            DivvyToolbar(title = stringResource(R.string.company_details), true) {
                onBackPressed()
            }
        },
        content = {
            CompanyDetailsScreenInfo(company = company, paddingValues = it)
        }
    )
}

@Composable
fun CompanyDetailsScreenInfo(company: Company, paddingValues: PaddingValues) {
    val context = LocalContext.current
    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state, enabled = true)
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = company.name,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.address),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Text(
            text = company.formatAddress(),
            fontSize = 18.sp,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.align(CenterHorizontally),
            onClick = {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri = Uri.parse("geo:0,0?q=${company.formatAddress()}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }) {
            Icon(imageVector = Icons.Rounded.Map, contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.open_maps))
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
    Column(Modifier.padding(top = 270.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        LineChartView(company.revenue)
    }
}

@Composable
fun LineChartView(revenues: List<Revenue>) {
    val bars = mutableListOf<BarChartData.Bar>()
    revenues.forEach {
        bars.add(
            BarChartData.Bar(
                label = "${it.date.substring(2, 4)}/${it.date.substring(5, 7)}",
                value = it.value.toFloat(),
                color = MaterialTheme.colorScheme.tertiary
            )
        )
    }
    BarChart(
        barChartData = BarChartData(
            bars = bars
        ),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        labelDrawer = SimpleLabelDrawer()
    )
}