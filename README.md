# CVD
Данный модуль добавляет проверку версии хрома и скачанного хромДрайвера.












Добавление в проект : 









Шаг 1
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Шаг 2
	dependencies {
	        implementation 'com.github.MistaCheese:CVD:"Версия билда"'
	}

Шаг 3 
	Указываем пути, пример

CVD("C:\\webDriver\\chrome\\", "C:\\Program Files (x86)\\Google\\Chrome\\Application").check()
System.setProperty("webdriver.chrome.driver", Server().getPath("C:\\webDriver\\chrome\\"))
