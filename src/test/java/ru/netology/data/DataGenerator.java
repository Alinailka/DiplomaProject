package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCard() {
        return "4444 4444 4444 4442";
    }

    public static String getSomethingDeclinedCard() {
        return "4444 4444 4444 4443";
    }

    public static String getMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateMonthPlus(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateMonthMinus(int month) {
        return LocalDate.now().minusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateYearPlus(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateYearMinus(int year) {
        return LocalDate.now().minusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCVC() {
        return faker.finance().creditCard().toLowerCase();
    }
}