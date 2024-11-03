package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberMeRow(checked:Boolean, modifier: Modifier, onCheckedChange:(Boolean)->Unit ){

    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Checkbox(
                checked = checked ,
                onCheckedChange = {onCheckedChange(it)},
                colors = CheckboxDefaults.colors(
                    checkedColor = mainBlue,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.White,
                ).copy(
                    uncheckedBorderColor = mainBlackGrey
                ),
                modifier = modifier.padding(0.dp)
            )
            Spacer(modifier = modifier.padding(horizontal = 3.dp))
            Text(
                text = "Remember me",
                fontSize = 16.sp,
                color = mainBlackGrey,
                fontWeight = FontWeight.Normal,
                modifier = modifier
            )
        }
    }
}