import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PayMethodTests {
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
    @DisplayName("Нажатие кнопки Купить")
    void payBox() {

        $x("//*[text()=\"Купить\"]").click();
        $(withText("Оплата по карте")).should(Condition.visible, Duration.ofSeconds(15));
    }
    @Test
    @DisplayName("Нажатие кнопки Купить в кредит")
    void creditBox() {

        $x("//*[text()=\"Купить в кредит\"]").click();
        $(withText("Кредит по данным карты")).should(Condition.visible, Duration.ofSeconds(15));
    }
}
