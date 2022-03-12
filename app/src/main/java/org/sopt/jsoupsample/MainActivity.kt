package org.sopt.jsoupsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.sopt.jsoupsample.databinding.ActivityMainBinding
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private val ogData = OgData("", "", "", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch {

            try {
                val url = "https://firebase.google.com/"
                val doc: Document = Jsoup.connect(url).get()
                Log.d("docCrawling", doc.toString())
                val ogData = OgData()

                val ogTags = doc.select("meta[property^=og:]")
                if (ogTags.size > 0){
                    ogTags.forEachIndexed { index, _ ->
                        val tag = ogTags[index]
                        when (tag.attr("property")) {
                            "og:image" -> ogData.ogImage = tag.attr("content")
                            "og:description" -> ogData.ogDescription = tag.attr("content")
                            "og:title" -> ogData.ogTitle = tag.attr("content")
                        }
                    }
                }

                binding.ogData = ogData


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}


//        val ogData = OgData(url, "https://s.pstatic.net/shopping.phinf/20220311_25/f40b81c9-41e8-4811-bcd2-85fe102362fb.jpg?type=f214_292",
//        "title", "Description")