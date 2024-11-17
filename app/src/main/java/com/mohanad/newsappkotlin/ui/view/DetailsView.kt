package com.mohanad.newsappkotlin.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.view.composable.BackArrow
import com.mohanad.newsappkotlin.ui.view.composable.LabelText

@Composable
fun DetailsView(navController: NavController , news:News , time:String){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ){

        val (backBtn , shareBtn , sourceName , timeTxt , image , category ,title , content) = createRefs()

        val guideLine = createGuidelineFromTop(0.4f)

        BackArrow(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.constrainAs(backBtn){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "Share",
            modifier = Modifier
                .clickable {

                }
                .constrainAs(shareBtn) {
                    top.linkTo(backBtn.top)
                    bottom.linkTo(backBtn.bottom)
                    end.linkTo(parent.end)
                }
        )

        LabelText(
            text = news.source?.name ?: "" ,
            modifier = Modifier.constrainAs(sourceName){
                top.linkTo(backBtn.bottom, margin = 20.dp)
                start.linkTo(parent.start)
            } )

        Text(
            text = time,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = mainBlackGrey,
            modifier = Modifier.constrainAs(timeTxt){
                top.linkTo(sourceName.bottom , margin = 2.dp)
                start.linkTo(sourceName.start)
            }
        )

        Image(
            painter = rememberAsyncImagePainter(model = news.urlToImage),
            contentDescription = news.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.clip(RoundedCornerShape(8.dp)).constrainAs(image){
                top.linkTo(timeTxt.bottom , margin = 8.dp)
                bottom.linkTo(guideLine)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = news.category ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = mainBlackGrey,
            modifier = Modifier.constrainAs(category){
                top.linkTo(image.bottom , margin = 10.dp)
                start.linkTo(image.start)
            }
        )

        Text(
            text = news.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.constrainAs(title){
                top.linkTo(category.bottom , margin = 10.dp)
                start.linkTo(category.start)
            }
        )

        Text(
            text = news.content?.split("[")?.get(0) ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = mainBlackGrey,
            modifier = Modifier.constrainAs(content){
                top.linkTo(title.bottom , margin = 18.dp)
                start.linkTo(title.start)
            }
        )

    }
}