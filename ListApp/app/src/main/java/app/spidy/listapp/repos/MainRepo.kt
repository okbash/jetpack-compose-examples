package app.spidy.listapp.repos

import android.content.Context

class MainRepo(private val context: Context) {
    fun getPage(pageNum: Int, callback: (data: List<String>) -> Unit) {
        val data = ArrayList<String>()
        val end = pageNum * 10
        for (i in (end - 9)..end) {
            data.add("Hello $i")
        }
        callback(data)
    }
}