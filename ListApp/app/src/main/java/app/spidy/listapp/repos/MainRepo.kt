package app.spidy.listapp.repos

import android.content.Context
import kotlinx.coroutines.delay

class MainRepo(private val context: Context) {
    suspend fun getPage(pageNum: Int): List<String> {
        delay(2000)
        val data = ArrayList<String>()
        val end = pageNum * 10
        for (i in (end - 9)..end) {
            data.add("Hello $i")
        }
        return data
    }
}