package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainGrey

// The news tile used in trending screen
@Composable
fun TrendingNewsTile(trendingNews:News? , timeTxt:String? , modifier: Modifier , onMoreClick: ()-> Unit ,onLongPress:(Offset)->Unit ,  onItemClick: (Offset)->Unit ){

    ConstraintLayout (
        modifier = modifier.fillMaxSize().padding(horizontal = 5.dp).pointerInput(Unit){
            detectTapGestures (
                onLongPress = onLongPress,
                onTap = onItemClick
            )
        }){

        val (image,category,title,source,timeIcon,time,more) = createRefs()

        val guideLine = createGuidelineFromTop(0.65f)

        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(guideLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            painter = rememberAsyncImagePainter(model = trendingNews?.urlToImage),

            contentScale = ContentScale.FillBounds,
            contentDescription = trendingNews?.description)

        Text(
            modifier = Modifier.constrainAs(category){
                top.linkTo(image.bottom , margin = 8.dp)
                start.linkTo(image.start)
            },
            text = "General",
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = mainGrey)

        Text(
            modifier = Modifier.constrainAs(title){
                top.linkTo(category.bottom , margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            },
            text =  trendingNews?.title ?: "",
            maxLines = 2,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = mainBlackGrey)

        Text(
            modifier = Modifier.constrainAs(source){
                top.linkTo(title.bottom , margin = 8.dp)

            },
            text = trendingNews?.source?.name ?: "",
            fontSize = 14.sp,
            color = mainBlackGrey,
            fontWeight = FontWeight.Bold)

        Image(
            modifier = Modifier.constrainAs(timeIcon){
                top.linkTo(source.top)
                bottom.linkTo(source.bottom)
                start.linkTo(source.end , margin = 12.dp)
            },
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = "Time")

        Text(
            modifier = Modifier.constrainAs(time){
                top.linkTo(timeIcon.top)
                bottom.linkTo(timeIcon.bottom)
                start.linkTo(timeIcon.end , margin = 5.dp)
            },
            text = timeTxt ?: "",
            fontSize = 14.sp,
            color = mainGrey,
        )

        Image(
            modifier = Modifier
                .clickable { onMoreClick() }
                .constrainAs(more) {
                    top.linkTo(time.top)
                    bottom.linkTo(time.bottom)
                    end.linkTo(parent.end)
                }
            ,
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "More")
    }
}


