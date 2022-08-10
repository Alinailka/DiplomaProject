import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static String getRandomName() {
        return faker.name().name();
    }

    public static String getApprovedCard() {
        String cardApproved = "44444444444444441";
        return cardApproved;
    }

    public static String getDeclinedCard() {
        String cardDeclined = "44444444444444442";
        return cardDeclined;
    }

    public static String getMonth() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        String formattedString = localDate.format(formatter);
        return formattedString;}

    public static String getYear() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        String formattedString = localDate.format(formatter);
        return formattedString;}

    public static String getCVC() {
        String cvc = faker.currency().code();
        return cvc;
    }
}