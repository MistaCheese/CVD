fun check() {
    println("Проверка версий драйвера")
    if (getLastLocalVersion() != getLastOnlineVersionChrome()) {
        println("Начинаю загрузку файла")
        run()
        println("Загрузка и распаковка завершена")
    } else {
        println("Версии драйверов совпадают")
    }
}
