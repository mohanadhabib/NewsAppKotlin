package com.mohanad.newsappkotlin.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.mohanad.newsappkotlin.R
import com.mohanad.newsappkotlin.data.datasource.retrofit.CountriesRetrofit
import com.mohanad.newsappkotlin.data.model.Country
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.view.composable.SetupScreensTemplate
import com.mohanad.newsappkotlin.ui.view.composable.UserTextField
import com.mohanad.newsappkotlin.ui.viewmodel.SelectCountryViewModel

// SelectCountry Screen
@Composable
fun SelectCountryView(viewModel: SelectCountryViewModel,navController: NavHostController){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 35.dp)
    ) {

        val (backBtn,text,searchTextField,list,nextBtn) = createRefs()

        val context = LocalContext.current

        val allCountriesList by viewModel.getAllCountries(
            onFailure = { Toast.makeText(context,"Cannot Get Countries",Toast.LENGTH_SHORT).show() }
        ).observeAsState(initial = emptyList())
        
        val searchedCountries by viewModel.getSearchedCountries(
            name = viewModel.searchTxt,
            list = allCountriesList).observeAsState(initial = emptyList())

        var myList = if(viewModel.searchTxt.isEmpty()) allCountriesList else searchedCountries

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
            labelTxt = "Select your Country"
        ) {
            viewModel.storeUserCountry(
                countryName = viewModel.selectedName,
                onSuccess = {
                    Toast.makeText(context, "Country Stored Successfully", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(NavRoute.Topics.route)
                },
                onFailure = {
                    Toast.makeText(context, "Cannot Store The Country", Toast.LENGTH_SHORT)
                        .show()

                }
            )
        }

        UserTextField(
            value = viewModel.searchTxt,
            errorText = "",
            isError = false,
            keyboardType = KeyboardType.Text,
            action = ImeAction.Search,
            onValueChange = {
                viewModel.searchTxt = it
                myList = searchedCountries
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

        LazyColumn (
            modifier = Modifier.constrainAs(list){
                top.linkTo(searchTextField.bottom , margin = 20.dp)
                bottom.linkTo(nextBtn.top, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ){
            items(myList){ item ->
                CountryListItem(
                    country = item,
                    selectedName = viewModel.selectedName,
                    onClick = {viewModel.selectedName = it})
            }
        }
    }
}

@Composable
fun CountryListItem(country: Country , selectedName:String , onClick:(String)->Unit){
    val isSelected = selectedName == country.name
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }.build()

        Row (
            modifier = if(isSelected) {
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = mainBlue,
                        shape = RoundedCornerShape(corner = CornerSize(8.dp))
                    )
                    .clickable { onClick(country.name) }
                    .padding(horizontal = 10.dp, vertical = 6.dp) }
            else {
                Modifier
                    .fillMaxWidth()
                    .clickable { onClick(country.name) }
                    .padding(horizontal = 10.dp, vertical = 6.dp) },
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                painter = rememberAsyncImagePainter(
                    model = "${CountriesRetrofit.COUNTRIES_BASE_URL}${country.image}",
                    imageLoader = imageLoader),
                contentDescription = "${country.name}'s flag",
                modifier = Modifier.size(60.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp))

            Text(
                text = country.name,
                fontSize = 16.sp,
                color = if(isSelected) Color.White else mainBlackGrey
            )
        }
    Spacer(modifier = Modifier.height(20.dp))
}