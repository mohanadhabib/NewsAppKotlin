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
import com.mohanad.newsappkotlin.ui.view.composable.SetupScreensTemplate
import com.mohanad.newsappkotlin.ui.view.composable.TextFieldLabel
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.view.validation.nameErrorText
import com.mohanad.newsappkotlin.ui.view.validation.nameValidation
import com.mohanad.newsappkotlin.ui.view.validation.phoneErrorText
import com.mohanad.newsappkotlin.ui.view.validation.phoneValidation
import com.mohanad.newsappkotlin.ui.viewmodel.FillProfileViewModel

// FillProfile Screen
@Composable
fun FillProfileView(viewModel: FillProfileViewModel , navController :NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ){
        ConstraintLayout (
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 35.dp)
        ){
            val context = LocalContext.current

            val (backBtn,text,nextBtn,pickImage,userTxt,userTextField,fullNameTxt,nameTextField,phoneNumberTxt,phoneTextField) = createRefs()

            val guideLine0 = createGuidelineFromStart(0.3f)
            val guideLine1 = createGuidelineFromEnd(0.3f)

            val hGuideLine = createGuidelineFromTop(0.25f)


            val pickImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
                viewModel.imageUrl = it
            }

            val nameError = nameErrorText
            val fullNameError = nameErrorText
            val phoneError = phoneErrorText

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
                labelTxt = "Fill your profile"
            ) {

                viewModel.isNameError = nameValidation(viewModel.userNameTextField)
                viewModel.isFullNameError = nameValidation(viewModel.fullNameTextField)
                viewModel.isPhoneNumberError = phoneValidation(viewModel.phoneNumberTextField)

                if(!viewModel.isNameError && !viewModel.isFullNameError && !viewModel.isPhoneNumberError && viewModel.imageUrl != null){
                    viewModel.storeUserImage(
                        imageUrl = viewModel.imageUrl ?: Uri.EMPTY,
                        onSuccess = {
                            viewModel.getUserImageAsUri(
                                onSuccess =  {
                                    viewModel.storeAllUserData(
                                        name = viewModel.userNameTextField,
                                        fullName = viewModel.fullNameTextField,
                                        phone = viewModel.phoneNumberTextField,
                                        imageUrl = it!!,
                                        onSuccess = {
                                            Toast.makeText(context,"User Info Added Successfully", Toast.LENGTH_SHORT).show()
                                            navController.navigate(NavRoute.Home.route){
                                                popUpTo(NavRoute.SelectCountry.route){
                                                    inclusive = true
                                                }
                                            }
                                        },
                                        onFailure = {
                                            Toast.makeText(context,"Sorry, Couldn't store your info", Toast.LENGTH_SHORT).show()
                                        }
                                    )
                                },
                                onFailure = {
                                    Toast.makeText(context,"Sorry, Couldn't get your profile image", Toast.LENGTH_SHORT).show()
                                }
                            )
                        },
                        onFailure = {
                            Toast.makeText(context,"Sorry, Couldn't Store your profile image", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

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
                text = "Phone Number",
                modifier = Modifier.constrainAs(phoneNumberTxt){
                    top.linkTo(nameTextField.bottom, margin = 20.dp)
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
