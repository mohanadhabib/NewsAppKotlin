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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.view.composable.BackArrow
import com.mohanad.newsappkotlin.ui.view.composable.LabelText
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingNextButton
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.viewmodel.TopicsViewModel

@Composable
fun TopicsView(viewModel: TopicsViewModel , navController: NavHostController){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 35.dp)
    ){

        val context = LocalContext.current

        val (backBtn,text,list,nextBtn,searchTextField) = createRefs()

        var searchTxt by remember {
            mutableStateOf("")
        }

        val allTopics by viewModel.getAllTopics().observeAsState()

        val searchedTopics by viewModel.getSearchedTopics(searchTxt,allTopics!!).observeAsState()

        val topics = if(searchedTopics?.size == 0) allTopics else searchedTopics

        val selectedTitles = remember {
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
            text = "Choose your Topics",
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
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ){
            items(topics!!){ topic ->
                ListItem(
                    title = topic,
                    selectedTitles = selectedTitles
                    ){
                    selectedTitles.add(topic)
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
            viewModel.storeTopics(
                list = selectedTitles,
                onSuccess = {
                    Toast.makeText(context,"Topics Saved Successfully",Toast.LENGTH_SHORT).show()
                },
                onFailure = {
                    Toast.makeText(context ,"Cannot save topics, Try again",Toast.LENGTH_SHORT).show()
                }
            )
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