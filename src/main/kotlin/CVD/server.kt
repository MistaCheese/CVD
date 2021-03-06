package CVD

import CVD.Server.UnzipUtils.unzip
import CVD.check.CheckOnline
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.*
import java.net.URL
import java.util.concurrent.TimeUnit
import java.util.zip.ZipFile

class Server {
    val urlLink: String = "http://omahaproxy.appspot.com/all?csv=1"

    private val localPath2: String = "\\chromedriver.zip"

    fun run(localPath1: String) { // Запуск загрузки и распаковки файла в указанную папку
        val buildVersion =
            "https://chromedriver.storage.googleapis.com/" + getLastReleaseBuild() + "/chromedriver_win32.zip"
        download(buildVersion, localPath1 + CheckOnline().getLastOnlineVersionChrome() + localPath2)
        unzip(
            File(localPath1 + CheckOnline().getLastOnlineVersionChrome() + localPath2),
            localPath1 + CheckOnline().getLastOnlineVersionChrome()
        )

    }

    fun getPath(localPath1: String): String { // Возвращает путь, куда нужно положить новый драйвер
        return localPath1 + CheckOnline().getLastOnlineVersionChrome() + "\\chromedriver.exe"
    }

    fun checkDriver(path: String): Boolean { // Проврека, что драйвер по указанному пути существует
        return File(path).isFile
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

    fun getLastReleaseBuild(): String? {  // Получение последнего билда

        val request = Request.Builder()
            .url("http://chromedriver.storage.googleapis.com/LATEST_RELEASE")
            .build()
        client.newCall(request).execute().use { response ->
            return response.body()?.string()
        }
    }

    private fun download(link: String, path: String) { // Метод по загрузке

        URL(link).openStream().use { input ->
            FileOutputStream(File(path)).use { output ->
                input.copyTo(output)
            }
        }
    }

    object UnzipUtils { // Распаковка архива
        /**
         * @param zipFilePath
         * @param destDirectory
         * @throws IOException
         */
        @Throws(IOException::class)
        fun unzip(zipFilePath: File, destDirectory: String) {
            val destDir = File(destDirectory)
            if (!destDir.exists()) {
                destDir.mkdir()
            }
            ZipFile(zipFilePath).use { zip ->

                zip.entries().asSequence().forEach { entry ->

                    zip.getInputStream(entry).use { input ->


                        val filePath = destDirectory + File.separator + entry.name

                        if (!entry.isDirectory) {
                            // if the entry is a file, extracts it
                            extractFile(input, filePath)
                        } else {
                            // if the entry is a directory, make the directory
                            val dir = File(filePath)
                            dir.mkdir()
                        }

                    }

                }
            }
        }

        /**
         * Extracts a zip entry (file entry)
         * @param inputStream
         * @param destFilePath
         * @throws IOException
         */
        @Throws(IOException::class)
        private fun extractFile(inputStream: InputStream, destFilePath: String) {
            val bos = BufferedOutputStream(FileOutputStream(destFilePath))
            val bytesIn = ByteArray(BUFFER_SIZE)
            var read: Int
            while (inputStream.read(bytesIn).also { read = it } != -1) {
                bos.write(bytesIn, 0, read)
            }
            bos.close()
        }

        /**
         * Size of the buffer to read/write data
         */
        private const val BUFFER_SIZE = 4096

    }
}



