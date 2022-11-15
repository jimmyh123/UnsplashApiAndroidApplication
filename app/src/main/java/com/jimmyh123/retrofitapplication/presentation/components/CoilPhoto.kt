package com.jimmyh123.retrofitapplication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Links
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.ProfileImage
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Urls
import com.jimmyh123.retrofitapplication.R

@Composable
fun DisplayImage(
    imageUrl: String
) {
    HotlinkImage(imageUrl)
}

@Composable
fun DisplayImageDetail(
    likes: Int,
    username: String?,
    links: Links?,
    profileImage: ProfileImage?,
    dateCreated: String?,
    dateUpdated: String?,
    description: String?,
    urls: Urls?
) {
    val unsplashWeblink = "https://unsplash.com/?utm_source=your_app_name&utm_medium=referral"
    val currentImageSizeUrl = urls?.regular

    Column {

        HotlinkImage(currentImageSizeUrl)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "like icon",
                tint = Color.Red
            )
            Text(likes.toString())
        }

        Spacer(modifier = Modifier.height(10.dp))

        val textToAnnotate: List<String> = listOf("Photo by ", username.toString()," on ","Unsplash")
        val hyperlinks: List<String> = listOf("", links?.html ?: "", "", unsplashWeblink)
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfileImageThumbnail(profileImage?.medium, unsplashWeblink)
            HyperlinkFormatter(textToAnnotate, hyperlinks)
        }

        Spacer(modifier = Modifier.height(10.dp))

        DetailPhotoTextDescription(description)

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ProfileImageThumbnail(profileImageLink: String?, unsplashWeblink: String) {

    val uriHandler = LocalUriHandler.current
    AsyncImage(
        model = profileImageLink,
        contentDescription = "profile image",
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50))
            .clickable { uriHandler.openUri(profileImageLink ?: unsplashWeblink) },
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.missing_image)
    )
}

@Composable
fun DetailPhotoTextDescription(
    description: String?,
) {

    Column() {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.body2,
            text = description ?: ""
        )
    }
}


@Composable
fun HotlinkImage(imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.Center,
        placeholder = painterResource(R.drawable.missing_image)
    )
}

@Composable
fun HyperlinkFormatter(
    textToAnnotate: List<String> = listOf("Photo by ","Unknown"," on ","Unsplash"),
    hyperlinks: List<String> = listOf(
        "", "", "", "https://unsplash.com/?utm_source=your_app_name&utm_medium=referral"
    )
) {
    var annotationIndexPosition = 0
    val annotatedString = buildAnnotatedString {
        for (i in textToAnnotate.indices){
            if (hyperlinks[i].isBlank()){
                append(textToAnnotate[i])
                annotationIndexPosition+=textToAnnotate[i].length+1
            } else {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                ){
                    append(textToAnnotate[i])
                }
                addStringAnnotation(
                    tag = "URL",
                    annotation = hyperlinks[i],
                    start = annotationIndexPosition,
                    end = annotationIndexPosition+textToAnnotate[i].length
                )
                annotationIndexPosition+=textToAnnotate[i].length+1
            }
        }
    }

    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.body2,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Preview
@Composable
fun HyperlinkFormatterPreview() {
    HyperlinkFormatter()
}

@Preview
@Composable
fun DisplayImageDetailPreview() {
    val profileImage = ProfileImage(
        "https://images.unsplash.com/profile-1605642019416-f1f5d5d75bfbimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128",
        "https://images.unsplash.com/profile-1605642019416-f1f5d5d75bfbimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64",
        "https://images.unsplash.com/profile-1605642019416-f1f5d5d75bfbimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32"
    )
    val links = Links(
        "https://unsplash.com/photos/FHhbHW4vFxc/download?ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ",
        "https://api.unsplash.com/photos/FHhbHW4vFxc/download?ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ",
        "https://unsplash.com/photos/FHhbHW4vFxc",
        "https://api.unsplash.com/photos/FHhbHW4vFxc"
    )
    val urls = Urls(
        "https://images.unsplash.com/photo-1664575599736-c5197c684128?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ&ixlib=rb-4.0.3&q=80",
        "https://images.unsplash.com/photo-1664575599736-c5197c684128?ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ&ixlib=rb-4.0.3",
        "https://images.unsplash.com/photo-1664575599736-c5197c684128?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ&ixlib=rb-4.0.3&q=80&w=1080",
        "https://images.unsplash.com/photo-1664575599736-c5197c684128?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ&ixlib=rb-4.0.3&q=80&w=400",
        "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1664575599736-c5197c684128",
        "https://images.unsplash.com/photo-1664575599736-c5197c684128?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzkzNjl8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2ODUyMjA4NQ&ixlib=rb-4.0.3&q=80&w=200"
    )
    DisplayImageDetail(
        likes = 4,
        username = "creatorUsername",
        links = links,
        profileImage = profileImage,
        dateCreated = "<dateCreatedHere>",
        dateUpdated = "<dateUpdatedHere>",
        description = "<descriptionHere>",
        urls = urls
    )
}