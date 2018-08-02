package com.fisha.hotel.rest;

import com.fisha.hotel.HotelApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.fisha.hotel.rest.ResourceConstants.ROOM_RESERVATION_V1;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = Integer.valueOf(port);
        RestAssured.basePath = ROOM_RESERVATION_V1;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test() {
        given().when().get("/" + 1).then().statusCode(200);
    }

}