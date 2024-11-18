package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGrey

// The tab view of categories list
@Composable
fun NewsCategoryTab(modifier: Modifier ,selectedIndex:Int ,onClick:(Int,String)->Unit){

    val list = listOf("General","Sports","Politics","Business","Health","Science","Technology")

    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedIndex,
        edgePadding = 0.dp,
        divider = {
            HorizontalDivider(
                color = Color.Transparent
            )
        },
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(it[selectedIndex]),
                height = 2.dp,
                color = mainBlue
            )
        }) {
        list.forEachIndexed{ index , text ->
            Tab(
                selected = index == selectedIndex,
                onClick = {onClick(index,text)}) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = text,
                    fontSize = 14.sp,
                    color = if(index == selectedIndex) Color.Black else mainGrey
                )
            }
        }
    }
}