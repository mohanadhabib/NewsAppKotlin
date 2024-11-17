package com.mohanad.newsappkotlin.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.ui.viewmodel.BottomExploreViewModel

@Composable
fun BottomExploreView(viewModel: BottomExploreViewModel , navController:NavHostController){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ){
        val (title ,topicsRow,topicsColumn) = createRefs()

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = "Explore",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}