package CVD

import org.junit.Test
import org.openqa.selenium.chrome.ChromeDriver


fun main() {



    CVD("C:\\webDriver\\chrome\\", "C:\\Program Files (x86)\\Google\\Chrome\\Application").check()
    System.setProperty("webdriver.chrome.driver", "C:\\webDriver\\chrome\\91\\chromedriver.exe")
    val driver: ChromeDriver = ChromeDriver()
    driver.get("https://google.com")
    driver.close()



    
}