// Em ui/screens/carteira/CarteiraScreen.kt
package com.example.trabalhoapp2.ui.screens.carteira

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.data.model.PortfolioItem
import com.example.trabalhoapp2.data.model.ServiceIcons
import com.example.trabalhoapp2.data.model.ServiceItem
import com.example.trabalhoapp2.ui.theme.*

@Composable
fun CarteiraScreen(navController: NavController) {
    val portfolioItems = listOf(
        PortfolioItem("Fundos Imobiliários", 45, "R$ 450.000,00", 15.16),
        PortfolioItem("Ações", 50, "R$ 500.000,00", 15.24),
        PortfolioItem("Proventos", 5, "R$ 50.000,00", 0.0)
    )

    val serviceItems = listOf(
        ServiceItem("Ordens", ServiceIcons["Ordens"]!!),
        ServiceItem("Aprovações", ServiceIcons["Aprovações"]!!),
        ServiceItem("Controle de garantia", ServiceIcons["Controle de garantia"]!!),
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RicoDarkBlue)
            .verticalScroll(rememberScrollState())

    ) {

        Column(
            modifier = Modifier.systemBarsPadding()
        ) {

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Total investido + Conta investimento",
                    color = RicoGrayText,
                    fontSize = 14.sp
                )
                Text(
                    text = "Atualizado hoje às 17h36",
                    color = RicoGrayText,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "R$ 1.000.000,00",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {  },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = RicoOrange)
                    ) {
                        Text("Investir")
                    }
                    Button(
                        onClick = {  },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = RicoDarkBlueLight)
                    ) {
                        Text("Resgatar")
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Aplicando padding horizontal
                colors = CardDefaults.cardColors(containerColor = RicoDarkBlueLight),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Rentabilidade • Até 12/09/2025",
                        color = RicoGrayText,
                        fontSize = 12.sp
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "R$ 1.000.000,00",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(color = RicoGreen, fontWeight = FontWeight.Bold)) {
                                    append("▲ 16,33 %")
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "• 42,47% do CDI",
                            color = RicoGrayText,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    SimpleLineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "Total investido • 3 Produtos",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                SimplePieChart(
                    portfolioItems = portfolioItems,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Column(modifier = Modifier.heightIn(max = 250.dp)) {
                    portfolioItems.forEach { item ->
                        PortfolioItemView(item)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Serviços",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Mostrar mais ->",
                        color = RicoOrange,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {  }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(modifier = Modifier.heightIn(max = 150.dp)) {
                    serviceItems.forEach { item ->
                        if (item.name == "Ordens") {
                            ServiceListItem(item.name, item.icon, onClick = { navController.navigate("orders_list") })
                        } else {
                            ServiceListItem(item.name, item.icon)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Voltar")
            }
            Spacer(modifier = Modifier.height(20.dp)) // Espaço no final, dentro do padding

        }
    }
}

@Composable
fun PortfolioItemView(item: PortfolioItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(RicoGreen)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = item.name, color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text(text = "${item.percentage}%", color = RicoGrayText, fontSize = 12.sp)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.value,
                    color = RicoGrayText,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "▲ ${item.rentability}%",
                    color = RicoGreen,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ServiceListItem(text: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = RicoDarkBlueLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun SimplePieChart(portfolioItems: List<PortfolioItem>, modifier: Modifier = Modifier) {
    val totalPercentage = portfolioItems.sumOf { it.percentage }.toFloat()
    val colors = listOf(RicoOrange, Color.White, RicoGreen)
    var startAngle = 0f

    Canvas(modifier = modifier) {
        portfolioItems.forEachIndexed { index, item ->
            val sweepAngle = (item.percentage.toFloat() / totalPercentage) * 360f
            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true
            )
            startAngle += sweepAngle
        }
    }
}

@Composable
fun SimpleLineChart(modifier: Modifier = Modifier) {
    val dataPoints = listOf(0.2f, 0.4f, 0.3f, 0.6f, 0.5f, 0.7f, 0.8f)

    Canvas(modifier = modifier) {
        val path = Path()
        val totalWidth = size.width
        val totalHeight = size.height
        val stepX = totalWidth / (dataPoints.size - 1)

        dataPoints.forEachIndexed { index, value ->
            val x = index * stepX
            val y = totalHeight - (totalHeight * value)
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = RicoGreen,
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )

        val dashedPath = Path()
        dashedPath.moveTo(0f, totalHeight * 0.5f)
        dashedPath.lineTo(totalWidth, totalHeight * 0.5f)

        drawPath(
            path = dashedPath,
            color = Color.White.copy(alpha = 0.5f),
            style = Stroke(
                width = 2.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        )
    }
}