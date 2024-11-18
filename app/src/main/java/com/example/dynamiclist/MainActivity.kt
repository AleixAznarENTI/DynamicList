package com.example.dynamiclist

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import com.example.dynamiclist.data.DataSource
import com.example.dynamiclist.models.Destinations
import com.example.dynamiclist.ui.theme.DynamicListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicListTheme {
                DestinationApp()
            }
        }
    }
}

/*
fun caesarEncrypt(text: String, shift: Int): String{
    val encryptedList = mutableListOf<Char>()

    for(char in text){
        val encryptedLetter = when(char){
                in 'a'..'z' -> 'a' + (char - 'a' + shift) % 26
                in 'A'..'Z' -> 'A' + (char - 'A' + shift) % 26
                else ->char
        }
        encryptedList.add(encryptedLetter)
    }
    return encryptedList.joinToString ( "" )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditField(modifier: Modifier = Modifier){
    var input by remember {mutableStateOf("")}
    var encryptedText = caesarEncrypt(input,1)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = input,
            onValueChange = {input = it},
            modifier = modifier
        )
        Text(
            text = encryptedText,
            modifier = modifier,
            fontSize = 15.sp
        )
    }
}
 */

@Composable
fun DestinationApp(){
    val layoutDirection = LocalLayoutDirection.current
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ){
        DestinationsList(destinationsList = DataSource().GetDestinations())
    }
}



@Composable
fun DestinationsList(destinationsList: List<Destinations>, modifier: Modifier = Modifier){
    LazyColumn(modifier = Modifier){
        items(destinationsList) { destination ->
            DestinationCard(destination, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DestinationCard(destination: Destinations, modifier: Modifier = Modifier){

    var uri = Uri.parse("https://" + destination.youtubeResourceId.toString()); // missing 'http://' will cause crashed
    var intent = Intent(Intent.ACTION_VIEW, uri);
    var context = LocalContext.current

    Card(modifier = Modifier) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(143.dp)){
            Column (modifier = Modifier.weight(1F)){
                Box {
                    Image(
                        painter = painterResource(destination.imageResourceId),
                        contentDescription = stringResource(destination.nameResourceId),
                        modifier = Modifier
                            .height(142.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column (
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 4.dp)
                    .align(Alignment.CenterVertically)
            ){
                Text(
                    text = stringResource(destination.nameResourceId),
                    modifier = Modifier.padding(1.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(destination.singerResourceId),
                    modifier = Modifier.padding(1.dp)
                )
                Row {
                    Icon(
                        imageVector = Icons.Default.CheckCircle, contentDescription = "Views",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = stringResource(destination.viewsResourceId),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.Info, contentDescription = "Date",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = stringResource(destination.dateResourceId),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row{
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Download")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Safe")
                }

            }
            Column (
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight()

            ){
                Box(modifier=Modifier.weight(1F)) {
                    Text(
                        text = stringResource(destination.albumResourceId),
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Right
                    )
                }
                Box(modifier=Modifier.weight(1F)) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow, contentDescription = "YoutubeVid",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp)
                                .align(Alignment.BottomEnd)
                                .clickable {

                                     context.startActivity(intent);
                                }
                        )


                }
            }
        }
    }
}


@Preview
@Composable
fun DestinationCardPreview(){
    DestinationCard(Destinations(R.string.snUno, R.drawable.uno,R.string.anUno,R.string.alnUno,R.string.vUno,R.string.rdUno,R.string.ytUno))
}