package com.mohanad.newsappkotlin.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.ui.view.composable.LabelText
import com.mohanad.newsappkotlin.ui.view.composable.TrendingNewsTile
import com.mohanad.newsappkotlin.ui.viewmodel.BottomHomeViewModel

@Composable
fun TrendingNewsView(navController: NavHostController,viewModel: BottomHomeViewModel){

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ){
        val context = LocalContext.current
        val savedNews by viewModel.getSavedNews(context).observeAsState()
        val latestNews by viewModel.getLatestNews().observeAsState()

        val (back,trendingTxt,trendingList) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(back) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable {
                    navController.popBackStack()
                },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back")

        LabelText(
            text = "Trending",
            modifier = Modifier.constrainAs(trendingTxt){
                top.linkTo(back.top)
                bottom.linkTo(back.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        LazyColumn (
            modifier = Modifier.fillMaxHeight().constrainAs(trendingList){
                top.linkTo(trendingTxt.bottom, margin = 10.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ){
            items(latestNews?.articles ?: emptyList()){ item ->
                TrendingNewsTile(
                    trendingNews = item,
                    timeTxt = viewModel.getTimeElapsed(item.publishedAt).value ?: "" ,
                    modifier = Modifier,
                    onLongPress = {
                        val newItem = item.copy(category = viewModel.category)
                        if(!viewModel.isContainNews(newItem,savedNews ?: emptyList())){
                            viewModel.insertNews(newItem,context)
                            Toast.makeText(context,"News article saved",Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(context,"Article is already saved",Toast.LENGTH_SHORT).show()
                        }
                    },
                    onMoreClick = { /*TODO*/ }) {
                }
            }
        }
    }

}