import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {
    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://185.119.57.64:8080/");
    }//по этой ссылке смотерть приложение порт 8080!!

    @Test
    @DisplayName("Купить с одобренной картой")
    void buyApprovedCard() {
        $x("//*[text()=\"Купить\"]").click();
        $$(byText("Номер карты")).filter(visible).first().setValue("4444 4444 4444 4441");//DataGenerator.getApprovedCard());
        $(byText("Месяц")).setValue(DataGenerator.getMonth());
        $(byText("Год")).setValue(DataGenerator.getYear());
        $(byText("Владелец")).setValue(DataGenerator.getRandomName());
        $(byText("CVC/CVV")).setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(byText("Операция одобрена Банком")).should(Condition.visible,Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Купить с отклоненной картой")
    void buyDeclinedCard() {
        $(byText("Купить")).click();
        $(byText("Номер карты")).setValue(DataGenerator.getDeclinedCard());
        $(byText("Месяц")).setValue(DataGenerator.getMonth());
        $(byText("Год")).setValue(DataGenerator.getYear());
        $(byText("Владелец")).setValue(DataGenerator.getRandomName());
        $(byText("CVC/CVV")).setValue(DataGenerator.getCVC());
        $(byText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции")).should(Condition.visible);
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
