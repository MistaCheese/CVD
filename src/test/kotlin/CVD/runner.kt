package CVD

import CVD.check.CheckLocal
import CVD.check.CheckOnline
import CVD.check.Driver
import java.io.File

class CVD(_localPathToDriver: String, _localPathToChromeBrowser: String) {
    private var localPathToDriver: String = ""
    private var localPathToChromeBrowser: String = ""

    init {
        localPathToDriver = _localPathToDriver
        localPathToChromeBrowser = _localPathToChromeBrowser
    }

    fun check() {
        println("Проверка версий драйвера")
        if (localPathToDriver.isEmpty() || localPathToChromeBrowser.isEmpty()) {
            println("Не указаны пути до папки с драйверами или до папки с браузером")
        } else {
            when {
                // когда в указанной папке нет дравйеров
                Driver().getLocalDriverSet(localPathToDriver).size == 0 -> {
                    println("В указанной папке не найдены драйвера для хрома")
                    println("Создаем папку $localPathToDriver" + CheckOnline().getLastOnlineVersionChrome())
                    val f = File(localPathToDriver, CheckOnline().getLastOnlineVersionChrome())
                    f.mkdir()
                    println("Начинаю загрузку файла")
                    Server().run(localPathToDriver)
                    println("Загрузка и распаковка завершена")
                }
                //  Ситуация, когда версия драйвера отличается от версии хрома
                CheckLocal().checkLocalVersions(localPathToChromeBrowser) != Driver().getLocalDriverSet(localPathToDriver).last() -> {
                    println("Необходима установка новой версии")
                    println("Начинаю загрузку файла")
                    val f = File(localPathToDriver, CheckOnline().getLastOnlineVersionChrome())
                    f.mkdir()
                    Server().run(localPathToDriver)
                    println("Загрузка и распаковка завершена")
                }
                else -> {
                    println("Версии хрома и дравйера совпадают")
                }
            }
        }
    }

    fun setLocalPathToDriver(path: String) { // Установка пути до папки, где должен хранится хром драйвер
        localPathToDriver = path
    }

    fun getLocalPathToDriver(): String { // Получение пути, где хранится хром драйвер
        return localPathToDriver
    }

    fun setLocalPathToChromeBrowser(path: String) { // Установка локального пути до папки с браузером
        localPathToChromeBrowser = path
    }

    fun getLocalPathToChromeBrowser(): String { // Получение локального пути с браузером
        return localPathToChromeBrowser
    }

}
