package CVD.check

import CVD.Server

class CheckOnline {
    fun getLastStableBuildChrome(allVersionString: String): String {     // Парсим список версий и получаем последний стабильный билд
        val s1: List<String> = allVersionString.split("\n")
        var temp = ""
        for (s: String in s1) {
            if (s.contains("win64,stable")) {
                temp = s.drop(13)
                break
            }
        }
        val s2: List<String>
        if (temp.isEmpty()) {
            return "Не удалось полчить номер последней сборки\n"

        } else {
            s2 = temp.split("/")
        }

        val s3: List<String> = s2[0].split(",")
        return s3[0]
    }

    fun getLastOnlineVersionChrome(): String? {  // Отдает именно версию, а не билд
        return Server().getResponse(Server().urlLink)?.let { getLastStableBuildChrome(it).split(".").first() }
    }
}

