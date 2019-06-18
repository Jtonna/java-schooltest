package com.lambdaschool.school.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

// manual imports
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

// working status of the tile: broken
// if broken why: imports arent working
//
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// we do this so it doesnt interfere with productio
public class CourseControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        // not getting an import for some reason
    }

    @After
    public void tearDown() throws Exception {
    }

    // testing the response time

    @Test
    public void whenMeasuresResponseTime() {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
        // if it takes longer than 5 seconds to load data users wont use the service.
        // 5000 milliseconds
        // 5 seconds
    }


    // generated is below

    @Test
    public void listAllCourses() {
    }

    @Test
    public void getCountStudentsInCourses() {
    }

    @Test
    public void deleteCourseById() {
    }
}