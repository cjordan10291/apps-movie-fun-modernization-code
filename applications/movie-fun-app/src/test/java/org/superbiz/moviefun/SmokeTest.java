package org.superbiz.moviefun;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SmokeTest {

    @Test
    public void smokeTest() {
        RestTemplate restTemplate = new RestTemplate();

        String homePage = restTemplate.getForObject(url("/"), String.class);

        MatcherAssert.assertThat(homePage, CoreMatchers.containsString("Please select one of the following links:"));

        String setupPage = restTemplate.getForObject(url("/setup"), String.class);

        MatcherAssert.assertThat(setupPage, CoreMatchers.containsString("Wedding Crashers"));
        MatcherAssert.assertThat(setupPage, CoreMatchers.containsString("Starsky & Hutch"));
        MatcherAssert.assertThat(setupPage, CoreMatchers.containsString("Shanghai Knights"));
        MatcherAssert.assertThat(setupPage, CoreMatchers.containsString("I-Spy"));
        MatcherAssert.assertThat(setupPage, CoreMatchers.containsString("The Royal Tenenbaums"));

        String movieFunPage = restTemplate.getForObject(url("/moviefun"), String.class);

        MatcherAssert.assertThat(movieFunPage, CoreMatchers.containsString("Wedding Crashers"));
        MatcherAssert.assertThat(movieFunPage, CoreMatchers.containsString("David Dobkin"));
    }

    private String url(String path) {
        String baseUrl = "http://localhost:8080/";
        String envUrl = System.getenv("MOVIE_FUN_URL");

        if (envUrl != null && !envUrl.isEmpty()) {
            baseUrl = envUrl;
        }

        return baseUrl + path;
    }
}
