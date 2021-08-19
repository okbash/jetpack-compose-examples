package app.spidy.listapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.spidy.listapp.viewmodels.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(mainViewModel: MainViewModel, loadMore: () -> Unit, modifier: Modifier = Modifier) {
    val names = mainViewModel.names.value
    val isLoading = mainViewModel.isLoading.value
    val isRefreshing = mainViewModel.isRefreshing.value

    if (names.isEmpty() && isLoading) {
        InitPageLoading()
    }

    NameList(
        names = names,
        isLoading = isLoading,
        loadMore = loadMore,
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefresh = { mainViewModel.onRefresh() }
    )
}

@Composable
fun InitPageLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PageLoading()
    }
}


@Composable
fun NameList(
    names: List<String>, isLoading: Boolean, loadMore: () -> Unit,
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = { onRefresh() }) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
        ) {
            itemsIndexed(names) { index, name ->
                Text(
                    text = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    style = MaterialTheme.typography.h3
                )
                if (index >= names.size - 1) {
                    loadMore()
                }
                if (isLoading && index >= names.size - 1) {
                    PageLoading()
                }
            }
        }
    }
}

@Composable
fun PageLoading() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
