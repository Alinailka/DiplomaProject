package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardPayTests {

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
        open("http://185.119.57.126:8080/");
    }

    @Test
    @DisplayName("Успешная оплата картой")
    void buyApprovedCard() {

        $x("//*[text()=\"Купить\"]").click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Отклоненная оплата картой")
    void buyDeclinedCard() {

        $x("//*[text()=\"Купить\"]").click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Ошибка! Банк отказал в проведении операции")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Отклоненная оплата любой картой")
    void buySomethingDeclinedCard() {

        $x("//*[text()=\"Купить\"]").click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getSomethingDeclinedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Ошибка! Банк отказал в проведении операции")).should(Condition.visible, Duration.ofSeconds(15));
    }
}
