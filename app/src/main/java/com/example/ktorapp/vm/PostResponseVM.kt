package com.example.ktorapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorapp.remote.ktor.PostService
import com.example.ktorapp.remote.ktor.model.PostRequest
import com.example.ktorapp.remote.ktor.model.PostResponse
import com.example.ktorapp.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostResponseVM @Inject constructor(private val postService: PostService) : ViewModel() {

    private val _response = MutableStateFlow<Resource<List<PostResponse>>?>(null)
    val response: MutableStateFlow<Resource<List<PostResponse>>?> = _response

//    private val _response = MutableStateFlow<Resource<PostResponse>?>(null)
//    val response: MutableStateFlow<Resource<PostResponse>?> = _response

    init {
        viewModelScope.launch {
            _response.value = Resource.Loading
            _response.value = postService.getPosts()
//                _response.value = postService.createPost(
//                    PostRequest(
//                        id = 8,
//                        title = "Srijan",
//                        body = "srijankhadka@gmail.com",
//                    )
//                )
        }
    }

}