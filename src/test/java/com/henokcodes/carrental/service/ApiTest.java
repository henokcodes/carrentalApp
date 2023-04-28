package com.henokcodes.carrental.service;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void testGetEndpoint() {
        given()
                .baseUri("http://localhost:8082")
                .get("/api/v1/cars")
                .then()
                .statusCode(200)
                .body("", hasSize(3))
                .body("licensePlate", hasItems("HO125", "TK135", "TK136"));

    }


}