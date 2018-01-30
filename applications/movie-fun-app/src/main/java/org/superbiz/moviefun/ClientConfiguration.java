package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.superbiz.moviefun.albumsapi.AlbumsServiceRestClient;
import org.superbiz.moviefun.albumsapi.AlbumsUserInterfaceController;
import org.superbiz.moviefun.moviesapi.MoviesClient;

@Configuration
public class ClientConfiguration {

    @Value("${movies.url}") String moviesUrl;
    @Value("${albums.url}") String albumsUrl;
    @Value("${blobstore.url}") String blobstoreUrl;

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    public MoviesClient moviesClient(RestOperations restOperations) {
        return new MoviesClient(moviesUrl, restOperations);
    }

    @Bean
    public AlbumsServiceRestClient albumsClient(RestOperations restOperations) {
        return new AlbumsServiceRestClient(albumsUrl, restOperations);
    }

    @Bean
    public AlbumsUserInterfaceController albumsUserInterfaceController(AlbumsServiceRestClient albumsServiceRestClient)
    {
                return new AlbumsUserInterfaceController(albumsServiceRestClient, blobstoreUrl);
    }


}