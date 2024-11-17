package com.mohanad.newsappkotlin.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import com.mohanad.newsappkotlin.navigation.BottomNavRoute
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.view.composable.LabelText
import com.mohanad.newsappkotlin.ui.view.composable.NewsTile
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingNextButton
import com.mohanad.newsappkotlin.ui.viewmodel.BottomProfileViewModel

@Composable
fun BottomProfileView(viewModel: BottomProfileViewModel , mainNavController : NavHostController , navController: NavHostController){

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ){
        val gson = Gson()
        val context = LocalContext.current

        val (title,logOutBtn,image,name,fullName,editButton,savedNews,news) = createRefs()

        val user by viewModel.getUserInfo().observeAsState()
        val newsList by viewModel.getSavedNews(context).observeAsState()

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Profile",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Icon(
            modifier = Modifier
                .clickable {
                    viewModel.logOut()
                    mainNavController.navigate(NavRoute.Login.route){
                        popUpTo(NavRoute.Home.route){
                            inclusive = true
                        }
                    }
                }
                .constrainAs(logOutBtn) {
                    top.linkTo(title.top)
                    bottom.linkTo(title.bottom)
                    end.linkTo(parent.end)

                    height = Dimension.fillToConstraints
                },
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Log out")

        Image(
            painter = rememberAsyncImagePainter(model = user?.userImg),
            contentDescription ="User Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(60.dp))
                .constrainAs(image) {
                    top.linkTo(title.bottom, margin = 20.dp)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                }
        )

        LabelText(
            text = user?.userName ?: "",
            modifier = Modifier.constrainAs(name){
                top.linkTo(image.bottom, margin = 10.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = user?.userFullName ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = mainBlackGrey,
            modifier = Modifier.constrainAs(fullName){
                top.linkTo(name.bottom, margin = 10.dp)
                start.linkTo(name.start)
            }
        )


        OnBoardingNextButton(
            text = "Edit Profile",
            modifier = Modifier.constrainAs(editButton){
                top.linkTo(fullName.bottom , margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            }
        ) {
            navController.navigate(BottomNavRoute.EditProfile.route)
        }

        Text(
            modifier = Modifier.constrainAs(savedNews) {
                top.linkTo(editButton.bottom , margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Saved News",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(news) {
                    top.linkTo(savedNews.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            items(newsList ?: emptyList()) { item ->
                NewsTile(
                    trendingNews = item,
                    categoryTxt = item.category ?: "",
                    timeTxt = viewModel.getTimeElapsed(item.publishedAt).value ?: "",
                    onLongPress = {},
                    isSwappable = false,
                    onSwipe = null,
                    onMoreClick = {
                        val time = viewModel.getTimeElapsed(item.publishedAt).value ?: ""
                        val newsTxt = gson.toJson(item)
                        navController.currentBackStackEntry?.savedStateHandle?.set("news",newsTxt)
                        navController.currentBackStackEntry?.savedStateHandle?.set("time",time)
                        navController.navigate(BottomNavRoute.DetailsView.route)}) {
                    val url = item.url
                    navController.currentBackStackEntry?.savedStateHandle?.set("url",url)
                    navController.navigate(BottomNavRoute.WebView.route)
                }
            }
        }


    }
}