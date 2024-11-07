package com.mohanad.newsappkotlin.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.data.datasource.retrofit.NewsSourceRetrofit
import com.mohanad.newsappkotlin.data.model.NewsSource
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.theme.mainGreyContainer
import com.mohanad.newsappkotlin.ui.view.composable.BackArrow
import com.mohanad.newsappkotlin.ui.view.composable.LabelText
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingNextButton
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.viewmodel.NewsSourceViewModel

// NewsSource Screen
@Composable
fun NewsSourceView(viewModel:NewsSourceViewModel ,navController:NavHostController){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 35.dp)
    ){
        val context = LocalContext.current

        val (backBtn,text,searchTextField,list,nextBtn) = createRefs()

        var searchTxt by remember {
            mutableStateOf("")
        }

        val allNewsSource by viewModel.getNewsSource(
            apiKey = NewsSourceRetrofit.API_KEY,
            onFailure = {Toast.makeText(context,"Cannot load news source",Toast.LENGTH_SHORT).show()}
        ).observeAsState()

        val searchedNewsSource by viewModel.getSearchedNewsSource(
            title = searchTxt,
            allNewsList = allNewsSource?.results ?: emptyList()
        ).observeAsState()

        val newsSourceList = if(searchedNewsSource?.size == 0) allNewsSource?.results else searchedNewsSource

        val selectedList = remember {
            mutableStateListOf<String>()
        }

        BackArrow(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.constrainAs(backBtn){
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start)
            })

        LabelText(
            text = "Choose your News Sources",
            modifier = Modifier.constrainAs(text){
                top.linkTo(backBtn.top)
                bottom.linkTo(backBtn.bottom)
                start.linkTo(backBtn.end)
                end.linkTo(parent.end)
            })

        UserTextField(
            value = searchTxt,
            errorText = "",
            isError = false,
            keyboardType = KeyboardType.Text,
            action = ImeAction.Search,
            onValueChange = {
                searchTxt = it
            },
            modifier = Modifier.constrainAs(searchTextField){
                top.linkTo(backBtn.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = "Search",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = mainGrey
                )
            }) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search")
        }

        LazyVerticalStaggeredGrid (
            columns = StaggeredGridCells.Fixed(3),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.constrainAs(list){
                top.linkTo(searchTextField.bottom , margin = 20.dp)
                bottom.linkTo(nextBtn.top, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ){
            items(newsSourceList ?: emptyList()){ item ->
                NewsSourceItem(
                    newsSource = item,
                    selectedList = selectedList) {
                    selectedList.add(it)
                }
            }
        }

        OnBoardingNextButton(
            text = "Next",
            modifier = Modifier.constrainAs(nextBtn){
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }) {
            viewModel.storeNewsSources(
                list = selectedList,
                onSuccess = {
                    Toast.makeText(context,"Sources saved successfully!!",Toast.LENGTH_SHORT).show()
                    navController.navigate(NavRoute.FillProfile.route)
                },
                onFailure = {
                    Toast.makeText(context,"Cannot store your selected news source",Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun NewsSourceItem(newsSource: NewsSource ,selectedList: List<String> ,onClick:(String)->Unit){

    val isSelected = selectedList.contains(newsSource.name)

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
        }.build()
    Column (
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = mainGreyContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(
                model = newsSource.icon,
                imageLoader = imageLoader),
            contentDescription = newsSource.description,
            modifier = Modifier
                .background(
                    color = mainGrey,
                    shape = RoundedCornerShape(8.dp)
                )
                .size(60.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = newsSource.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,

        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if(isSelected) "Following" else "Follow",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = if(isSelected) Color.White else mainBlue,
            modifier = if(isSelected) {
                Modifier
                    .background(
                        color = mainBlue,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
                    .clickable { onClick(newsSource.name) }
            }
            else {
                Modifier
                    .border(
                        width = 2.dp,
                        color = mainBlue,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
                    .clickable { onClick(newsSource.name) }
            }
        )

    }
}