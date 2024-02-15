package com.example.blogapp_modularization.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.commen.Resource
import com.example.domain.model.Blog

@Composable
fun HomeScreen(

    viewModel: HomeViewModel = hiltViewModel(),

) {

    LaunchedEffect(key1 = Unit) {
        viewModel.processIntent(HomeIntent.FetchData)
    }
    val state by viewModel.blogs.collectAsState()
    when (val resource = state) {
        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = resource.message ?: "Unknown error", modifier = Modifier.align(Alignment.Center))
            }
        }
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is Resource.Success -> {
            val lazyPagingItems = resource.data?.collectAsLazyPagingItems()
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                lazyPagingItems?.let {
                    items(count = it.itemCount) { index ->
                        val item = lazyPagingItems[index]
                        item?.let { PostItem(it = it) }
                    }
                }
            }
        }
    }

}


@Composable
fun PostItem(it: Blog) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            CircularImage(50.0, 50.0, 25.0, it.owner.picture)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "${it.owner.firstName} ${it.owner.lastName}")

        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = rememberImagePainter(data = it.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = it.text,
            modifier = Modifier.padding(12.dp),
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
        Divider()
    }

}


@Composable
fun CircularImage(width: Double, height: Double, redius: Double, imageUrl: String) {
    Card(
        modifier = Modifier
            .width(width = width.dp)
            .height(height = height.dp), shape = RoundedCornerShape(redius.dp)
    )
    {

        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }
}