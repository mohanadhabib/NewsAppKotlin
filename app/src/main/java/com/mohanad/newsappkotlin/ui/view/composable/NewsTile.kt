package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainGrey

// The main news tile in home , bookmarks and profile screens
@Composable
fun NewsTile(isSwappable:Boolean, trendingNews: News?, categoryTxt: String, timeTxt:String?, onMoreClick: ()-> Unit, onLongPress:(Offset)->Unit, onSwipe:(() -> Unit)?,onItemClick: (Offset)->Unit ){

    val context = LocalContext.current

    if (isSwappable){
        val swipeState = SwipeToDismissBoxState(
            initialValue = SwipeToDismissBoxValue.Settled,
            positionalThreshold =  SwipeToDismissBoxDefaults.positionalThreshold,
            density = Density(context = context),
            confirmValueChange = {
                if(it == SwipeToDismissBoxValue.EndToStart){
                    onSwipe?.invoke()
                    true
                }else{
                    false
                }
            }
        )
        SwipeToDismissBox(
            state = swipeState,
            backgroundContent = {},
        ) {
            MainBody(
                trendingNews = trendingNews,
                categoryTxt = categoryTxt,
                timeTxt = timeTxt,
                onMoreClick = { onMoreClick() },
                onLongPress = onLongPress,
                onItemClick = onItemClick
            )
        }
    }
    else{
        MainBody(
            trendingNews = trendingNews,
            categoryTxt = categoryTxt,
            timeTxt = timeTxt,
            onMoreClick = { onMoreClick() },
            onLongPress = onLongPress,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun MainBody(trendingNews: News?, categoryTxt: String, timeTxt:String?, onMoreClick: ()-> Unit, onLongPress:(Offset)->Unit,onItemClick: (Offset)->Unit ){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = onLongPress,
                    onTap = onItemClick
                )
            }
    ){

        val (image,category,title,source,timeIcon,time,more) = createRefs()

        val vGuideLine = createGuidelineFromStart(0.3f)
        val guideLine = createGuidelineFromTop(0.7f)
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(guideLine)
                    start.linkTo(parent.start)
                    end.linkTo(vGuideLine)

                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            painter = rememberAsyncImagePainter(model = trendingNews?.urlToImage),

            contentScale = ContentScale.FillBounds,
            contentDescription = trendingNews?.description)

        Text(
            modifier = Modifier.constrainAs(category){
                top.linkTo(parent.top)
                start.linkTo(image.end , margin = 5.dp)
            },
            text = categoryTxt,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = mainGrey
        )

        Text(
            modifier = Modifier.constrainAs(title){
                top.linkTo(category.bottom , margin = 4.dp)
                start.linkTo(category.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            },
            maxLines = 2,
            text =  trendingNews?.title ?: "",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = mainBlackGrey
        )

        Text(
            modifier = Modifier.constrainAs(source){
                top.linkTo(title.bottom , margin = 2.dp)
                bottom.linkTo(image.bottom)
                start.linkTo(title.start)
            },
            text = trendingNews?.source?.name ?: "",
            fontSize = 14.sp,
            color = mainBlackGrey,
            fontWeight = FontWeight.Bold)

        Image(
            modifier = Modifier.constrainAs(timeIcon){
                top.linkTo(source.top)
                bottom.linkTo(source.bottom)
                start.linkTo(source.end , margin = 10.dp)
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