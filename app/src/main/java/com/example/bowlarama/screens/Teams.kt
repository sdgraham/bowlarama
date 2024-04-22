package com.example.bowlarama.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


import com.example.bowlarama.R
import com.example.bowlarama.Team


@Composable
fun TeamsScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.usa),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        createScroller()

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun createScroller()
{
    val pinPals = Team(
        R.drawable.pin_pals,
        "Pin Pals",
        "Homer, Barney, Apu and Clancy make up the Pin Pals: a formidable team with a strong record and large bar tab."
    )

    val holyRollers = Team(
        R.drawable.holy_rollers,
        "Holy Rollers",
        "Reverend Lovejoy, Helen Lovejoy, Ned Flanders and Maud Flanders make up the Holy Rollers: a blessed team."
    )

    val homeWreckers = Team(
        R.drawable.homewreckers,
        "Home Wreckers",
        "Jacques, Lurleen, Mindy and Princess Kashmir make up the Homewreckers: a strong but unreliable team."
    )

    val teams = listOf(
        pinPals,
        holyRollers,
        homeWreckers
    )

    val pics = listOf(
        R.drawable.pin_pals,
        R.drawable.holy_rollers,
        R.drawable.homewreckers
    )

    val pagerState = rememberPagerState(pageCount = { pics.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(
            state = pagerState,
            key = { teams[it].picture },
            pageSize = PageSize.Fill
        ) {index ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 300.dp, height = 500.dp)
                    .align(Alignment.Center)
                    .offset(x = (40).dp, y = (20).dp)
            )
            {
                Column() {
                    val colours = listOf(Blue, Red)

                    Text(
                        modifier = Modifier.fillMaxWidth().padding(all = 15.dp),
                        text = teams[index].name,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            brush = Brush.linearGradient(colors = colours)
                        )
                    )

                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = teams[index].picture),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth().padding(all = 15.dp),
                        text = teams[index].description
                    )

                }

            }
        }

        Box(
            modifier = Modifier
                .offset(y = -(80).dp)
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ){
            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Go back"
                )
            }

            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go forth"
                )
            }
        }




    }
}

@Composable
@Preview
fun TeamsScreenPreview() {
    TeamsScreen()
}