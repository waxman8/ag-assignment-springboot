package com.mdv.assignment;


import com.mdv.assignment.repository.TweetRepository;
import com.mdv.assignment.repository.UserRepository;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AGConfig {
    
    @Bean
    public Simulator simulatorProvider() {
        return new Simulator();
    }
    
    @Bean
    public TweetRepository tweetRepoProvider() throws IOException {
        return new TweetRepository();
    }
    
    @Bean
    public UserRepository userRepoProvider() throws IOException {
        return new UserRepository();
    }
}
