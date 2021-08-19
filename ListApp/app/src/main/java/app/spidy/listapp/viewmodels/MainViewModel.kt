package app.spidy.listapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.spidy.listapp.repos.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MainRepo): ViewModel() {
    private val _names = MutableLiveData(listOf<String>())
    val names: LiveData<List<String>> = _names
    private var pageNum = 1

    fun loadMore() {
        repo.getPage(pageNum) {
            _names.value = names.value!! + it
            pageNum++
        }
    }
}