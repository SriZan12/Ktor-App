package com.example.ktorapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.*
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.ktorapp.remote.ktor.model.PostResponse
import com.example.ktorapp.resource.Resource
import com.example.ktorapp.vm.PostResponseVM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GetScreen(postVm: PostResponseVM, context: Context) {

    var posts: Resource<List<PostResponse>>? = null
    posts = postVm.response.collectAsState().value

//    LaunchedEffect(key1 = true) {
//        posts = postVm.response.value
//      posts.let {
//          for(item in it){
//
//          }
//      }
//    }

    Scaffold(
        topBar = {
            CustomToolBar()
        },
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        GetPostLists(posts = posts, context = context)
    }
}


@Composable
fun GetPostLists(posts: Resource<List<PostResponse>>?, context: Context) {
    posts.let {
        when (it) {
            is Resource.Loading -> {

            }

            is Resource.Error -> {
                Toast.makeText(context, it.errorMessage.toString(), Toast.LENGTH_SHORT)
                    .show()
                Log.d("ERROR", "onCreate: ${it.errorMessage}")
            }

            is Resource.Success -> {
                val result = it.result

                LazyColumn() {
                    items(result) { item ->
                        PostItemUI(
                            id = item.id,
                            userId = item.userId,
                            body = item.body,
                            title = item.title
                        )
                    }
//                    item {
//                        PostItemUI(
//                            id = result.id,
//                            userId = result.userId,
//                            body = result.body,
//                            title = result.title
//                        )
//                    }

                }
            }

            else -> {}
        }
    }
}

@Composable
fun CustomToolBar() {
    val backPressedDispatcher = OnBackPressedDispatcher()
    TopAppBar(
        title = {
            Text(text = "Get Screen")
        },
        navigationIcon = {
            IconButton(onClick = { backPressedDispatcher.onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
        }
    )
}

@Composable
fun PostItemUI(id: Int, userId: Int, body: String, title: String) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        shape = RectangleShape
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row() {
                Text(text = "Id:", modifier = Modifier.weight(1f))
                Text(text = id.toString(), modifier = Modifier.weight(1f))
            }

            Row() {
                Text(text = "userId:", modifier = Modifier.weight(1f))
                Text(text = userId.toString(), modifier = Modifier.weight(1f))
            }

            Row() {
                Text(text = "Title:", modifier = Modifier.weight(1f))
                Text(text = title, modifier = Modifier.weight(1f))
            }

            Row() {
                Text(text = "Body:", modifier = Modifier.weight(1f))
                Text(text = body, modifier = Modifier.weight(1f))
            }
        }
    }
}
