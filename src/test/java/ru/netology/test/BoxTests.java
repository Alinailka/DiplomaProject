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
import static com.codeborne.selenide.Selenide.$;

public class BoxTests {
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
    }

    @Test
    @DisplayName("Поле Номер карты осталось пустым")
    void cardNumberEmpty() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Номер карты менее 16 цифр")
    void cardNumberShort() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 444");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Номер карты заполнено кириллицей")
    void cardNumberСyrillic() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("Номер карты");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Номер карты заполнено латиницей")
    void cardNumberLatin() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("Card number");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Номер карты заполнено спецсимволами")
    void cardNumberSymbols() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("&$%");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Номер карты заполнено 16 цифрами")
    void cardNumberValid() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4567 4567 4567 4567");
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Банк отказал в проведении операции")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц осталось пустым")
    void monthEmpty() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц менее 2 цифр")
    void monthShort() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("1");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц заполнено кириллицей")
    void monthСyrillic() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("Месяц");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц заполнено латиницей")
    void monthLatin() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("Month");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц заполнено спецсимволами")
    void monthSymbols() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("%&$");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц >12 ")
    void monthNotExist() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("13");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан срок действия карты")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц <01")
    void monthNull() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("00");
        $("[placeholder=\"22\"]").setValue(DataGenerator.generateYearPlus(1));
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан срок действия карты")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Месяц заполнено валидным значением 09")
    void monthValid() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue("09");
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год осталось пустым")
    void yearEmpty() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue("");
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год заполнен менее 2 цифр")
    void yearShort() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue("1");
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год заполнено кириллицей")
    void yearСyrillic() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue("Год");
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год заполнено латиницей")
    void yearLatin() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue("Year");
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год заполнено спецсимволами")
    void yearSymbols() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue("%$&");
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год < текущего года")
    void yearLast() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.generateYearMinus(1));
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Истёк срок действия карты")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год > текущего года")
    void yearValid() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.generateYearPlus(1));
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год = текущему году, месяц = текущему")
    void yearNowMonthValid() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год = текущему году, месяц > текущего")
    void yearNowMonthPlus() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.generateMonthPlus(1));
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Год = текущему году, месяц < текущего")
    void yearNowMonthLast() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.generateMonthMinus(1));
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан срок действия карты")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Владелец заполнено кириллицей")
    void ownerСyrillic() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue("Василий Иванов");
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Владелец заполнено латиницей")
    void ownerLatin() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue("Vasiliy Ivanov");
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Владелец заполнено спецсимволами")
    void ownerSymbols() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue("&%$");
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле Владелец заполнено цифрами")
    void ownerNumbers() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue("4444 5555");
        $("[placeholder=\"999\"]").setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле CVC заполнено менее 2 цифр")
    void cvcShort() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("01");
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле CVC заполнено 3 цифрами")
    void cvcValid() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("011");
        $(byText("Продолжить")).click();
        $(withText("Операция одобрена Банком")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле CVC заполнено кириллицей")
    void cvcСyrillic() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("КОД");
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле CVC заполнено латиницей")
    void cvcLatin() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("CVC");
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Поле CVC заполнено спецсимволами")
    void cvcSymbols() {

        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(DataGenerator.getApprovedCard());
        $("[placeholder=\"08\"]").setValue(DataGenerator.getMonth());
        $("[placeholder=\"22\"]").setValue(DataGenerator.getYear());
        $x(("//span[contains(.,'Владелец')]/following-sibling::span/input")).setValue(DataGenerator.getRandomName());
        $("[placeholder=\"999\"]").setValue("&%$");
        $(byText("Продолжить")).click();
        $(withText("Неверный формат")).should(Condition.visible, Duration.ofSeconds(15));
    }
}
