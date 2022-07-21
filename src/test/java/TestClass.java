import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestClass {
    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080"); //по этой ссылке смотерть приложение порт 8080!!
    }
}

// spring.datasource.url=jdbc:postgresql://217.25.88.206:5432/base