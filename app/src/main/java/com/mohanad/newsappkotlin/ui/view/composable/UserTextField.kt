package com.mohanad.newsappkotlin.ui.view.composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainRedContainerError
import com.mohanad.newsappkotlin.ui.theme.mainRedError

// The main text field that can receive all user data like: email , password , etc.
@Composable
fun UserTextField(value: String,errorText: String,isError: Boolean,keyboardType: KeyboardType,action: ImeAction,onValueChange:(String)->Unit,modifier: Modifier,visualTransformation: VisualTransformation,trailing: @Composable (()->Unit)?){
    Column (
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ){
        OutlinedTextField(
            visualTransformation = visualTransformation,
            value = value,
            isError = isError,
            onValueChange = {onValueChange(it)},
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = trailing,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = keyboardType,
                imeAction = action,
            ),
            colors = TextFieldDefaults.colors(
                errorContainerColor = mainRedContainerError,
                errorIndicatorColor = mainRedError,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = mainBlue,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = mainBlackGrey,
                cursorColor = mainBlue,
                errorCursorColor = mainRedError
            )
        )
        Spacer(modifier = modifier.height(5.dp))
        if (isError){
            Text(
                text = errorText,
                fontSize = 12.sp,
                color = mainRedError
            )
        }
    }
}