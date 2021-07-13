import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.concurrent.TimeUnit

const val urlLink: String = "http://omahaproxy.appspot.com/all?csv=1"

fun main() {
    val buildVersion = getLastReleaseBuild()
    download("https://chromedriver.storage.googleapis.com/$buildVersion/chromedriver_win32.zip","D:\\test\\chromedriver.exe")

}

private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .callTimeout(10, TimeUnit.SECONDS)
    .build()

fun getResponse(url: String): String? {   // Гет запрос списка всех версий хром драйвера, отдает стрингу
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).execute().use { response ->
        return response.body()?.string()
    }

}

fun getLastReleaseBuild(): String? {  // загрузка хром драйвера

    val request = Request.Builder()
        .url("http://chromedriver.storage.googleapis.com/LATEST_RELEASE")
        .build()
    client.newCall(request).execute().use { response ->
        return response.body()?.string()
    }
}

fun download(link: String, path: String) { // Метод по загрузке

    URL(link).openStream().use { input ->
        FileOutputStream(File(path)).use { output ->
            input.copyTo(output)
        }
    }
}

