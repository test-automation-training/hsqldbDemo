package com.hule.springboot.flyway.flywaytest;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class WireMockWithStubMappingTest {

    @Autowired
    private UserClient userClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9009).usingFilesUnderClasspath("stubmappings"));

    @Test
    public void test_user() {
        User user = userClient.getUser("id");
        assertEquals(user.getId(), "id");
        assertEquals(user.getFirstName(), "hu");
        assertEquals(user.getLastName(), "le");
        assertEquals(user.getCareer(), "Engineer");
    }
}
