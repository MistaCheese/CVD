class CVD {
    private var localPathToDriver: String = ""
    private var localPathToChromeBrowser: String = ""
    fun check() {
        println("Проверка версий драйвера")
        if (localPathToDriver.isEmpty() || localPathToChromeBrowser.isEmpty()) {
            println("Не указаны пути до папки с драйверами или до папки с браузером")
        } else {
            if (CheckLocal().getLastLocalVersion() != CheckOnline().getLastOnlineVersionChrome()) {
                println("Начинаю загрузку файла")
                Server().run()
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
