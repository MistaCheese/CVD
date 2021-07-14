package CVD

import org.junit.Assert
import java.io.File
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
class CheckLocal () {




    fun checkLocalVersions(link: String): String { // Получение версии из локального хранилища в корне хрома
        val versionSet: ArrayList<String> = ArrayList()
        val text = File(link)
        try {
            for (s1: Int in text.listFiles().indices) {
                var s2: String = text.listFiles()[s1].toString()
                if (!s2.contains("Dictionaries") && text.listFiles()[s1].isDirectory && !s2.contains("SetupMetrics")) {
                    versionSet.add(s2.split("\\").last().split(".").first())
                    return versionSet[0]
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Предупреждение!!! Не удалось получить локальнкую версию хрома")
        }
        return versionSet[0]
    }
}


