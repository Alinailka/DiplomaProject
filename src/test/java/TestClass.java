import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://185.119.57.64:8080/");
    }//по этой ссылке смотерть приложение порт 8080!!

    @Test
    @DisplayName("Купить с одобренной картой")
    void buyApprovedCard() {

        $x("//*[text()=\"Купить\"]").click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("111");
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible,Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Купить с отклоненной картой")
    void buyDeclinedCard() {
        $x("//*[text()=\"Купить\"]").click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4442");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("111");
        $(byText("Продолжить")).click();
        $(withText("Ошибка! Банк отказал в проведении операции")).should(Condition.visible,Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Купить в кредит с одобренной картой")
    void buyOnCreditApprovedCard() {
        $x("//*[text()=\"Купить в кредит\"]").click();
        $(byText("Номер карты")).setValue(DataGenerator.getApprovedCard());
        $(byText("Месяц")).setValue(DataGenerator.getMonth());
        $(byText("Год")).setValue(DataGenerator.getYear());
        $(byText("Владелец")).setValue(DataGenerator.getRandomName());
        $(byText("CVC/CVV")).setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(byText("Операция одобрена Банком")).should(Condition.visible);
    }

    @Test
    @DisplayName("Купить в кредит с отклоненной картой")
    void buyOnCreditDeclinedCard() {
        $(byText("Купить в кредит")).click();
        $(byText("Номер карты")).setValue(DataGenerator.getDeclinedCard());
        $(byText("Месяц")).setValue(DataGenerator.getMonth());
        $(byText("Год")).setValue(DataGenerator.getYear());
        $(byText("Владелец")).setValue(DataGenerator.getRandomName());
        $(byText("CVC/CVV")).setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции")).should(Condition.visible);
    }
}
