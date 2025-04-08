package com.at.test.exercise3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class ViaCepTest {
    @BeforeAll
    static void setUpBeforeClass() {
        RestAssured.baseURI = "https://viacep.com.br/ws";
    }

    @Test
    public void test_whenCepIsValid() {
        String cep = "01001-000";
        String path = String.format("/%s/json", cep.replace("-", ""));

        given()
                .when()
                    .get(path)
                .then()
                    .statusCode(200)
                    .body("cep", equalTo(cep));
    }

    @Test
    public void test_whenCepIsInvalid() {
        String cep = "01001-abc";
        String path = String.format("/%s/json", cep.replace("-", ""));

        given()
                .when()
                    .get(path)
                .then()
                    .statusCode(400);
    }

    @Test
    public void test_searchByAddress() {
        String path = "/SP/Sao Paulo/Avenida Paulista/json";

        given()
                .when()
                    .get(path)
                .then()
                    .statusCode(200)
                    .body("$", not(empty()));
    }

    @Test
    public void test_searchByAddress_whenStateInvalid() {
        String path = "/batata/Sao Paulo/Avenida Paulista/json";

        given()
                .when()
                .get(path)
                .then()
                .statusCode(400);
    }

    @Test
    public void test_searchByAddress_whenCityInvalid() {
        String path = "/SP/Batata 123/Avenida Paulista/json";

        given()
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .body("$", empty());
    }

    @Test
    public void test_searchByAddress_whenStreetInvalid() {
        String path = "/SP/Sao Paulo/eu nao sou um robo/json";

        given()
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .body("$", empty());
    }
}
