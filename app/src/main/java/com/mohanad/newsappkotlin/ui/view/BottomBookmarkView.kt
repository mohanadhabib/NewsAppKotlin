package com.mohanad.newsappkotlin.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
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
import com.google.gson.Gson
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.navigation.BottomNavRoute
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.view.composable.NewsTile
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.viewmodel.BottomBookmarkViewModel

@Composable
fun BottomBookmarkView(viewModel: BottomBookmarkViewModel ,navController:NavHostController){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        val gson = Gson()
        val context = LocalContext.current

        val (title,search,text,list) = createRefs()

        val savedNewsList by viewModel.getSavedNews(context).observeAsState()
        val searchedList by viewModel.getSearchedNews(
            viewModel.searchText,
            savedNewsList ?: emptyList()
        ).observeAsState()
        val currentList = if (viewModel.searchText.isEmpty()) savedNewsList else searchedList

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = "Bookmark",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        UserTextField(
            value = viewModel.searchText,
            errorText = "",
            isError = false,
            keyboardType = KeyboardType.Text,
            action = ImeAction.Search,
            onValueChange = {
                viewModel.searchText = it
            },
            modifier = Modifier.constrainAs(search){
                top.linkTo(title.bottom , margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            },
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search"
                    )
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


        if(currentList?.size == 0){
            Text(
                modifier = Modifier.constrainAs(text){
                    top.linkTo(search.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "There is no saved items yet"
            )
        }
        else{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(list) {
                            top.linkTo(search.bottom, margin = 20.dp)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)

                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                ) {
                    items(currentList ?: emptyList()) { item ->
                        NewsTile(
                            trendingNews = item,
                            categoryTxt = item.category ?: "",
                            timeTxt = viewModel.getTimeElapsed(item.publishedAt).value,
                            onMoreClick = {
                                val time = viewModel.getTimeElapsed(item.publishedAt).value ?: ""
                                val news = gson.toJson(item)
                                navController.currentBackStackEntry?.savedStateHandle?.set("news",news)
                                navController.currentBackStackEntry?.savedStateHandle?.set("time",time)
                                navController.navigate(BottomNavRoute.DetailsView.route)
                            },
                            onLongPress = { TODO() },
                            isSwappable = true,
                            onSwipe = {
                                viewModel.deleteNews(item, context)
                            }
                        ) {
                            val url = item.url
                            navController.currentBackStackEntry?.savedStateHandle?.set("url",url)
                            navController.navigate(BottomNavRoute.WebView.route)
                        }
                    }
                }
        }
    }
}