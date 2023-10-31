package com.example.ktorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ktorapp.ui.screens.GetScreen
import com.example.ktorapp.ui.theme.KtorAppTheme
import com.example.ktorapp.vm.PostResponseVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val postResponseVm: PostResponseVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
//            val posts = postResponseVm.response.collectAsState()
            KtorAppTheme {

                GetScreen(postVm = postResponseVm, context = context)
//                posts.value.let {
//                    when (it) {
//                        is Resource.Loading -> {
//
//                        }
//
//                        is Resource.Error -> {
//                            Toast.makeText(context, it.errorMessage.toString(), Toast.LENGTH_SHORT)
//                                .show()
//                            Log.d("ERROR", "onCreate: ${it.errorMessage.toString()}")
//                        }
//
//                        is Resource.Success -> {
//                            val result = it.result
//                            LazyColumn() {
////                                items(result) { item ->
////                                    PostItemUI(
////                                        id = item.id,
////                                        userId = item.userId,
////                                        body = item.body,
////                                        title = item.title
////                                    )
////                                }
//
//                                item {
//                                    PostItemUI(
//                                        id = result.id,
//                                        userId = result.userId,
//                                        body = result.body,
//                                        title = result.title
//                                    )
//                                }
//
//                            }
//                        }
//
//                        else -> {}
//                    }
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtorAppTheme {
        Greeting("Android")
    }
}