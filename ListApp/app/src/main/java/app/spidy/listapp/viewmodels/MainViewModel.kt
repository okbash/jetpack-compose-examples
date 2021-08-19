package app.spidy.listapp.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.spidy.listapp.repos.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MainRepo): ViewModel() {
    val names: MutableState<List<String>> = mutableStateOf(listOf())
    private var pageNum = 1
    var isLoading = mutableStateOf(false)
    private var valueBeforeLoading = listOf<String>()
    var isRefreshing = mutableStateOf(false)

    init {
        if (names.value.isEmpty() && !isLoading.value) {
            loadMore()
        }
    }

    fun onRefresh() {
        pageNum = 1
        valueBeforeLoading = listOf()
        names.value = listOf()
        loadMore()
    }

    fun loadMore() {
        viewModelScope.launch {
            if (valueBeforeLoading.size != names.value.size || names.value.isEmpty()) {
                isLoading.value = true
                valueBeforeLoading = names.value
                names.value = names.value + repo.getPage(pageNum)
                pageNum++
                isLoading.value = false
            }
        }
    }
}