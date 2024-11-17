package com.mohanad.newsappkotlin.ui.view

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGreyContainer
import com.mohanad.newsappkotlin.ui.view.composable.TextFieldLabel
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.view.validation.emailErrorText
import com.mohanad.newsappkotlin.ui.view.validation.emailValidation
import com.mohanad.newsappkotlin.ui.view.validation.nameErrorText
import com.mohanad.newsappkotlin.ui.view.validation.nameValidation
import com.mohanad.newsappkotlin.ui.view.validation.phoneErrorText
import com.mohanad.newsappkotlin.ui.view.validation.phoneValidation
import com.mohanad.newsappkotlin.ui.viewmodel.EditProfileViewModel

@Composable
fun EditProfileView(viewModel: EditProfileViewModel , navController:NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 35.dp)
        ) {

            val context = LocalContext.current

            val (backBtn,text,okBtn,pickImage,userTxt,userTextField,fullNameTxt,emailTxt,emailTextField,nameTextField,phoneNumberTxt,phoneTextField) = createRefs()

            val guideLine0 = createGuidelineFromStart(0.3f)
            val guideLine1 = createGuidelineFromEnd(0.3f)

            val hGuideLine = createGuidelineFromTop(0.25f)


            val pickImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
                viewModel.imageUrl = it
            }

            val nameError = nameErrorText
            val fullNameError = nameErrorText
            val emailError = emailErrorText
            val phoneError = phoneErrorText

            Image(
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }.constrainAs(backBtn){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                painter = painterResource(id = R.drawable.ic_cancel) ,
                contentDescription = "Cancel")

            Text(
                modifier = Modifier.constrainAs(text){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "Edit Profile"
            )

            Image(
                modifier = Modifier.clickable {
                    viewModel.isNameError = nameValidation(viewModel.userNameTextField)
                    viewModel.isFullNameError = nameValidation(viewModel.fullNameTextField)
                    viewModel.isPhoneNumberError = phoneValidation(viewModel.phoneNumberTextField)
                    viewModel.isEmailError = emailValidation(viewModel.emailTextField)
                    if(!viewModel.isNameError && !viewModel.isFullNameError && !viewModel.isEmailError && !viewModel.isPhoneNumberError && viewModel.imageUrl != null){
                        viewModel.storeUserImage(
                            imageUrl = viewModel.imageUrl ?: Uri.EMPTY,
                            onSuccess = {
                                viewModel.getUserImageAsUri(
                                    onSuccess = {
                                        viewModel.updateUserInfo(
                                            name = viewModel.userNameTextField,
                                            fullName = viewModel.fullNameTextField,
                                            email = viewModel.emailTextField,
                                            phone = viewModel.phoneNumberTextField,
                                            imageUrl = it!!,
                                            onSuccess = {
                                                Toast.makeText(
                                                    context,
                                                    "User Info Added Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navController.navigate(NavRoute.Home.route) {
                                                    popUpTo(NavRoute.SelectCountry.route) {
                                                        inclusive = true
                                                    }
                                                }
                                            },
                                            onFailure = {Toast.makeText(context,"Couldn't update your info.Try again",Toast.LENGTH_SHORT).show()},
                                            onEmailUpdateFailure = {Toast.makeText(context,"Couldn't update your email.Try again",Toast.LENGTH_SHORT).show()}
                                        ) },
                                    onFailure = {Toast.makeText(context,"Couldn't get your image",Toast.LENGTH_SHORT).show()}
                                ) },
                            onFailure = {Toast.makeText(context,"Couldn't store your image",Toast.LENGTH_SHORT).show()}
                        ) }
                }.constrainAs(okBtn){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
                painter = painterResource(id = R.drawable.ic_ok),
                contentDescription = "Ok")

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = mainGreyContainer, shape = RoundedCornerShape(60.dp))
                    .constrainAs(pickImage) {
                        top.linkTo(text.bottom, margin = 10.dp)
                        bottom.linkTo(hGuideLine)
                        start.linkTo(guideLine0)
                        end.linkTo(guideLine1)
                    },
                contentAlignment = Alignment.BottomEnd
            ){
                if(viewModel.imageUrl != null){
                    Image(
                        painter = rememberAsyncImagePainter(viewModel.imageUrl),
                        contentDescription ="User Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(shape = RoundedCornerShape(60.dp)))
                }
                Box (
                    modifier = Modifier
                        .size(30.dp)
                        .background(color = mainBlue, shape = RoundedCornerShape(15.dp)),
                    contentAlignment = Alignment.Center,
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        tint = Color.White,
                        contentDescription = "Select Photo",
                        modifier = Modifier.clickable {
                            pickImageLauncher.launch(PickVisualMediaRequest())
                        })
                }
            }

            TextFieldLabel(
                text = "Username",
                modifier = Modifier.constrainAs(userTxt){
                    top.linkTo(pickImage.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                })

            UserTextField(
                value = viewModel.userNameTextField,
                errorText = nameError,
                isError = viewModel.isNameError,
                keyboardType = KeyboardType.Text,
                action = ImeAction.Next,
                onValueChange = {
                    viewModel.userNameTextField = it
                    viewModel.isNameError = nameValidation(viewModel.userNameTextField)
                },
                modifier = Modifier.constrainAs(userTextField){
                    top.linkTo(userTxt.bottom , margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                visualTransformation = VisualTransformation.None,
                placeholder = null,
                trailing = null)

            TextFieldLabel(
                text = "Full name",
                modifier = Modifier.constrainAs(fullNameTxt){
                    top.linkTo(userTextField.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                })

            UserTextField(
                value = viewModel.fullNameTextField,
                errorText = fullNameError,
                isError = viewModel.isFullNameError,
                keyboardType = KeyboardType.Text,
                action = ImeAction.Next,
                onValueChange = {
                    viewModel.fullNameTextField = it
                    viewModel.isFullNameError = nameValidation(viewModel.fullNameTextField)
                },
                modifier = Modifier.constrainAs(nameTextField){
                    top.linkTo(fullNameTxt.bottom , margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                visualTransformation = VisualTransformation.None,
                placeholder = null,
                trailing = null)

            TextFieldLabel(
                text = "Email Address",
                modifier = Modifier.constrainAs(emailTxt){
                    top.linkTo(nameTextField.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                })

            UserTextField(
                value = viewModel.emailTextField,
                errorText = emailError,
                isError = viewModel.isEmailError,
                keyboardType = KeyboardType.Email,
                action = ImeAction.Next,
                onValueChange = {
                    viewModel.emailTextField = it
                    viewModel.isEmailError = emailValidation(viewModel.emailTextField)
                },
                modifier = Modifier.constrainAs(emailTextField){
                    top.linkTo(emailTxt.bottom , margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                visualTransformation = VisualTransformation.None,
                placeholder = null,
                trailing = null)

            TextFieldLabel(
                text = "Phone Number",
                modifier = Modifier.constrainAs(phoneNumberTxt){
                    top.linkTo(emailTextField.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                })

            UserTextField(
                value = viewModel.phoneNumberTextField,
                errorText = phoneError,
                isError = viewModel.isPhoneNumberError,
                keyboardType = KeyboardType.Phone,
                action = ImeAction.Next,
                onValueChange = {
                    viewModel.phoneNumberTextField = it
                    viewModel.isPhoneNumberError = phoneValidation(viewModel.phoneNumberTextField)
                },
                modifier = Modifier.constrainAs(phoneTextField){
                    top.linkTo(phoneNumberTxt.bottom , margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                visualTransformation = VisualTransformation.None,
                placeholder = null,
                trailing = null)
        }
    }
}