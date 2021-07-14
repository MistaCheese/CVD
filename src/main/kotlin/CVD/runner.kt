package CVD

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
            if (CheckLocal().checkLocalVersions(localPathToChromeBrowser) != CheckOnline().getLastOnlineVersionChrome()) {
                println("Начинаю загрузку файла")
                Server().run(localPathToDriver)
                println("Загрузка и распаковка завершена")
            } else {
                println("Версии драйверов совпадают")
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
