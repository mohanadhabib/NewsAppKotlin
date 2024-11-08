package com.mohanad.newsappkotlin.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.view.composable.SetupScreensTemplate
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.viewmodel.TopicsViewModel

// Topics Screen
@Composable
fun TopicsView(viewModel: TopicsViewModel , navController: NavHostController){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 35.dp)
    ){

        val context = LocalContext.current

        val (backBtn,text,list,nextBtn,searchTextField) = createRefs()

        val allTopics by viewModel.getAllTopics().observeAsState()

        val searchedTopics by viewModel.getSearchedTopics(viewModel.searchTxt,allTopics!!).observeAsState()

        val topics = if(searchedTopics?.size == 0) allTopics else searchedTopics

        SetupScreensTemplate(
            navController = navController,
            arrowModifier = Modifier.constrainAs(backBtn){
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start)
            },
            labelModifier = Modifier.constrainAs(text){
                top.linkTo(backBtn.top)
                bottom.linkTo(backBtn.bottom)
                start.linkTo(backBtn.end)
                end.linkTo(parent.end)
            },
            buttonModifier = Modifier.constrainAs(nextBtn){
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            labelTxt = "Choose your Topics"
        ) {
            viewModel.storeTopics(
                list = viewModel.selectedTitles,
                onSuccess = {
                    Toast.makeText(context,"Topics Saved Successfully",Toast.LENGTH_SHORT).show()
                    navController.navigate(NavRoute.NewsSource.route)
                },
                onFailure = {
                    Toast.makeText(context ,"Cannot save topics, Try again",Toast.LENGTH_SHORT).show()
                }
            )
        }

        UserTextField(
            value = viewModel.searchTxt,
            errorText = "",
            isError = false,
            keyboardType = KeyboardType.Text,
            action = ImeAction.Search,
            onValueChange = {
                viewModel.searchTxt = it
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
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ){
            items(topics!!){ topic ->
                ListItem(
                    title = topic,
                    selectedTitles = viewModel.selectedTitles
                    ){
                    viewModel.selectedTitles.add(topic)
                }
            }
        }
    }
}

@Composable
fun ListItem(title:String , selectedTitles:List<String> , onClick:(String)->Unit){
    val isSelected = selectedTitles.contains(title)
    Text(
        text = title,
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
                .clickable { onClick(title) }
        }
        else {
            Modifier
                .border(
                    width = 2.dp,
                    color = mainBlue,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
                .clickable { onClick(title) }
        }
    )
}