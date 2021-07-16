package CVD.check

import java.io.File
import java.util.*

class Driver() {

    fun getLocalDriverSet(pathToDriver: String): TreeSet<String> {  // Возвращает коллекцию со списком локальных версий в указанной папке
        val tSet: TreeSet<String> = TreeSet()
        val tempFile: File = File(pathToDriver)
        for (s1: String in tempFile.list()) {
            tSet.add(s1)
        }
        return tSet
    }
}