package com.mohanad.newsappkotlin.ui.view

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.navigation.BottomNavRoute
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.view.composable.MainRow
import com.mohanad.newsappkotlin.ui.view.composable.NewsCategoryTab
import com.mohanad.newsappkotlin.ui.view.composable.NewsTile
import com.mohanad.newsappkotlin.ui.view.composable.TrendingNewsTile
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.viewmodel.BottomHomeViewModel

@Composable
fun BottomHomeView(viewModel: BottomHomeViewModel , navController: NavHostController){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ){

        val context = LocalContext.current
        val latestNews by viewModel.getLatestNews().observeAsState()
        val savedNews by viewModel.getSavedNews(context).observeAsState()
        val newsByCategory by viewModel.getNewsByCategory(viewModel.category).observeAsState()
        val timeOfTopTile by viewModel.getTimeElapsed(latestNews?.articles?.get(0)?.publishedAt ?:"").observeAsState()

        val (logoImg,searchBar,trendingRow,trendingTile,allRow,newsTab,newsScreen) = createRefs()

        val guideLine0 = createGuidelineFromTop(0.5f)
        val guideLine1 = createGuidelineFromTop(0.7f)


        Image(
            modifier = Modifier.constrainAs(logoImg){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            painter = painterResource(id = R.drawable.ic_logo_app),
            contentDescription = "App Logo")


        UserTextField(
            value = viewModel.searchTxt,
            errorText = "",
            isError = false,
            keyboardType = KeyboardType.Text,
            action = ImeAction.Search,
            onValueChange = {viewModel.searchTxt = it},
            modifier = Modifier.constrainAs(searchBar){
                top.linkTo(logoImg.bottom , margin = 35.dp)
                start.linkTo(parent.start)
            },
            visualTransformation = VisualTransformation.None,
            placeholder = { 
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Search",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = mainGrey
                        )
                }
            },
            trailing = null
        )

        MainRow(
            modifier = Modifier.constrainAs(trendingRow){
                top.linkTo(searchBar.bottom , margin = 15.dp)
            },
            seeAllButton = true,
            text = "Trending") {
            navController.navigate(BottomNavRoute.HomeTrendingNews.route)
        }

        TrendingNewsTile(
            trendingNews = latestNews?.articles?.get(0),
            timeTxt = timeOfTopTile,
            modifier =  Modifier.constrainAs(trendingTile){
                top.linkTo(trendingRow.bottom, margin = 20.dp)
                bottom.linkTo(guideLine0)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            onLongPress = {
                val newItem = latestNews?.articles?.get(0)!!.copy(category = viewModel.category)
                if(!viewModel.isContainNews(newItem,savedNews ?: emptyList())){
                    viewModel.insertNews(newItem,context)
                    Toast.makeText(context,"News article saved",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context,"Article is already saved",Toast.LENGTH_SHORT).show()
                }
            },
            onMoreClick = {
                navController.navigate(BottomNavRoute.HomeTrendingNews.route)
            }) {
            WebView(context).loadUrl(latestNews?.articles?.get(0)?.url ?: "")
        }

        MainRow(
            modifier = Modifier.constrainAs(allRow){
                top.linkTo(guideLine1)
            },
            seeAllButton = true,
            text = "Latest") {
            navController.navigate(BottomNavRoute.HomeLatestNews.route)
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
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(newsScreen) {
                    top.linkTo(newsTab.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            items(newsByCategory?.articles ?: emptyList()){ item ->
                NewsTile(
                    trendingNews = item,
                    categoryTxt = viewModel.category,
                    timeTxt = viewModel.getTimeElapsed(item.publishedAt).value ?: "",
                    onLongPress = {
                        val newItem = item.copy(category = viewModel.category)
                        if(!viewModel.isContainNews(newItem,savedNews ?: emptyList())){
                            viewModel.insertNews(newItem,context)
                            Toast.makeText(context,"News article saved",Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(context,"Article is already saved",Toast.LENGTH_SHORT).show()
                        }
                    },
                    onMoreClick = { WebView(context).loadUrl(item.url) }) {
                    WebView(context).loadUrl(item.url)
                }
            }
        }
    }
}

//