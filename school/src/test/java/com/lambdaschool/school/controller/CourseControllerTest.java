package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


// mocking service to test controller

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
// secure will be deprecated soon, it basically turns off user authentication
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList;

    @Before
    public void setUp() throws Exception {

        courseList = new ArrayList<>();

        Instructor i1 = new Instructor("Sally");
        Instructor i2 = new Instructor("Lucy");

        i1.setInstructid(1);
        i2.setInstructid(2);

        Student s1 = new Student("John");
        Student s2 = new Student("Julian");

        s1.setStudid(1);
        s2.setStudid(2);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);

        Course c1 = new Course("Data Science");
        Course c2 = new Course("JavaScript");

        c1.setCourseid(1);
        c1.setInstructor(i1);
        c1.setStudents(students);

        c2.setCourseid(2);
        c2.setInstructor(i2);
        c2.setStudents(students);

        courseList.add(c1);
        courseList.add(c2);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllCourses() throws Exception
    {
        // https://blog.csdn.net/cdw_sunshine/article/details/82963750
        // ^^ basically says to comment out @SpringBootApplication ins SchoolApplicationTests ot it wont work.
        String apiUrl = "/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String str = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, str);
    }

    @Test
    public void getCountStudentsInCourses() {
    }

    @Test
    public void deleteCourseById() {
    }
}