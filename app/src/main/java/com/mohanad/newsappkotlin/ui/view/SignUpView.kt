package com.mohanad.newsappkotlin.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingNextButton
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingText
import com.mohanad.newsappkotlin.ui.view.composable.RememberMeRow
import com.mohanad.newsappkotlin.ui.view.composable.SocialMediaButton
import com.mohanad.newsappkotlin.ui.view.composable.TextFieldLabel
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.view.composable.WelcomeText
import com.mohanad.newsappkotlin.ui.view.validation.emailErrorText
import com.mohanad.newsappkotlin.ui.view.validation.emailValidation
import com.mohanad.newsappkotlin.ui.view.validation.passwordErrorText
import com.mohanad.newsappkotlin.ui.view.validation.passwordValidation
import com.mohanad.newsappkotlin.ui.viewmodel.SignUpViewModel

// Signup Screen
@Composable
fun SignUpView(viewModel: SignUpViewModel,navController: NavHostController){
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

            val (
                text1,text2,emailLabel,
                emailTextField,passwordLabel,passwordTextField,
                rememberMe,loginBtn,continueTxt,
                googleBtn,facebookBtn,signUpTxt,signUpBtn
            ) = createRefs()

            val guideline0 = createGuidelineFromTop(0.035f)
            val guideline1 = createGuidelineFromTop(0.185f)

            val vGuidLine = createGuidelineFromStart(0.15f)

            createHorizontalChain(googleBtn,facebookBtn, chainStyle = ChainStyle.SpreadInside)

            val emailError = emailErrorText
            val passwordError = passwordErrorText

            WelcomeText(
                text = "Hello!",
                color = mainBlue,
                modifier = Modifier.constrainAs(text1){
                    top.linkTo(guideline0)
                    start.linkTo(parent.start)
                }
            )

            OnBoardingText(
                text = "Signup to get Started",
                modifier = Modifier.constrainAs(text2){
                    top.linkTo(text1.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                }
            )

            TextFieldLabel(
                text = "Email",
                modifier = Modifier.constrainAs(emailLabel){
                    top.linkTo(guideline1)
                }
            )

            UserTextField(
                isError = viewModel.isEmailError,
                errorText = emailError,
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Email,
                action = ImeAction.Next,
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                    viewModel.isEmailError = emailValidation(viewModel.email)
                },
                placeholder = null,
                modifier = Modifier.constrainAs(emailTextField){
                    top.linkTo(emailLabel.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                trailing = null
            )

            TextFieldLabel(
                text = "Password",
                modifier = Modifier.constrainAs(passwordLabel){
                    top.linkTo(emailTextField.bottom , margin = 20.dp)
                }
            )

            UserTextField(
                isError = viewModel.isPasswordError,
                errorText = passwordError,
                visualTransformation = viewModel.passwordHide,
                keyboardType = KeyboardType.Password,
                action = ImeAction.Done,
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                    viewModel.isPasswordError = passwordValidation(viewModel.password)
                },
                placeholder = null,
                modifier = Modifier.constrainAs(passwordTextField){
                    top.linkTo(passwordLabel.bottom , margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            ){
                Image(
                    painter = painterResource(R.drawable.ic_password_action),
                    contentDescription = "Show and Hide Password",
                    modifier = Modifier.clickable{
                        viewModel.isPasswordShown = !viewModel.isPasswordShown

                        viewModel.passwordHide = if(viewModel.isPasswordShown){
                            VisualTransformation.None
                        }else{
                            PasswordVisualTransformation()
                        }
                    }
                )
            }

            RememberMeRow(
                checked = viewModel.isChecked,
                modifier = Modifier.constrainAs(rememberMe){
                    top.linkTo(passwordTextField.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                }
            ) {
                viewModel.isChecked = it
            }

            OnBoardingNextButton(
                text = "Sign Up",
                modifier = Modifier.constrainAs(loginBtn){
                    top.linkTo(rememberMe.bottom , margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            ) {
                viewModel.isEmailError = emailValidation(viewModel.email)
                viewModel.isPasswordError = passwordValidation(viewModel.password)
                if(!viewModel.isEmailError && !viewModel.isPasswordError){
                    viewModel.signUp(
                        email = viewModel.email,
                        password= viewModel.password,
                        onSuccess = { it ->
                            Toast.makeText(context,"Signup Successfully!!", Toast.LENGTH_LONG).show()
                            val user = User(
                                userId = it.user!!.uid,
                                userEmail = viewModel.email,
                                password = viewModel.password,
                            )
                            viewModel.createUser(
                                user = user,
                                onSuccess = {
                                    Toast.makeText(context,"User Created Successfully", Toast.LENGTH_LONG).show()
                                    if(viewModel.isChecked){
                                        viewModel.storeId(user.userId ?: "")
                                    }
                                    navController.navigate(NavRoute.SelectCountry.route){
                                        popUpTo(NavRoute.SignUp.route){
                                            inclusive = true
                                        }
                                    }
                                },
                                onFailure = {
                                    Toast.makeText(context,it.localizedMessage, Toast.LENGTH_LONG).show()
                                },
                                onExceptionFound = {
                                    Toast.makeText(context,it.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            )
                        },
                        onFailure = {
                            Toast.makeText(context,it.localizedMessage, Toast.LENGTH_LONG).show()
                        },
                        onExceptionFound = {
                            Toast.makeText(context,it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }

            Text(
                text = "or continue with",
                fontSize = 16.sp,
                color = mainBlackGrey,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(continueTxt){
                    top.linkTo(loginBtn.bottom , margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            SocialMediaButton(
                text = "Facebook",
                contentDescription = "Facebook login",
                painter = painterResource(R.drawable.ic_facebook),
                modifier = Modifier.constrainAs(facebookBtn){
                    top.linkTo(continueTxt.bottom , margin = 15.dp)
                    start.linkTo(parent.start)
                    width = Dimension.wrapContent
                },
                onClick = {
                    TODO()
                }
            )

            SocialMediaButton(
                text = "Google",
                contentDescription = "google login",
                painter = painterResource(R.drawable.ic_google),
                modifier = Modifier.constrainAs(googleBtn){
                    top.linkTo(continueTxt.bottom , margin = 20.dp)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                },
                onClick = {
                    TODO()
                }
            )

            Text(
                text = "Already have an account ?",
                fontSize = 16.sp,
                color = mainBlackGrey,
                modifier = Modifier.constrainAs(signUpTxt){
                    top.linkTo(googleBtn.bottom, margin = 15.dp)
                    start.linkTo(vGuidLine)
                }
            )

            TextButton(
                modifier = Modifier
                    .padding(0.dp)
                    .constrainAs(signUpBtn) {
                        top.linkTo(signUpTxt.top)
                        bottom.linkTo(signUpTxt.bottom)
                        start.linkTo(signUpTxt.end)
                    },
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = mainBlue
                )
            }
        }
    }
}
