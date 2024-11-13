package com.mohanad.newsappkotlin.ui.view

import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.Image
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
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.ui.view.composable.MainRow
import com.mohanad.newsappkotlin.ui.view.composable.NewsCategoryTab
import com.mohanad.newsappkotlin.ui.view.composable.NewsTile
import com.mohanad.newsappkotlin.ui.viewmodel.BottomHomeViewModel

@Composable
fun LatestNewsView(viewModel: BottomHomeViewModel){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp , end = 20.dp , top = 20.dp)
    ){
        val context = LocalContext.current
        val newsByCategory by viewModel.getNewsByCategory(viewModel.category).observeAsState()

        val (logoImg,allRow,newsTab,newsScreen) = createRefs()

        Image(
            modifier = Modifier.constrainAs(logoImg){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            painter = painterResource(id = R.drawable.ic_logo_app),
            contentDescription = "App Logo")

        MainRow(
            modifier = Modifier.constrainAs(allRow){
                top.linkTo(logoImg.bottom , margin = 30.dp)
            },
            text = "Latest",
            seeAllButton = false
            ) {
            TODO()
        }

        NewsCategoryTab(
            selectedIndex = viewModel.selectedIndex,
            onClick = { index,item ->
                viewModel.category = item
                viewModel.selectedIndex = index
            },
            modifier = Modifier.constrainAs(newsTab){
                top.linkTo(allRow.bottom , margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxHeight().constrainAs(newsScreen){
                top.linkTo(newsTab.bottom , margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom )

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            items(newsByCategory?.articles ?: emptyList()){ item ->
                NewsTile(
                    trendingNews = item ,
                    categoryTxt = viewModel.category,
                    timeTxt = viewModel.getTimeElapsed(item.publishedAt).value ?: "",
                    onMoreClick = { TODO() }) {
                    Log.e("TAGG",item.url)
                    WebView(context).loadUrl(item.url)
                }
            }
        }
    }
}