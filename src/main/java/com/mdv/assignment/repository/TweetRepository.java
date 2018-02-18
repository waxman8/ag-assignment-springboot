package com.mdv.assignment.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.mdv.assignment.model.Tweet;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author martin
 */
public class TweetRepository {
    
    private List<String> list = new ArrayList<>();
    
    @Value("${file.tweet}")
    private String filePath;
    
    /**
     * Strips the tweets out of their text format and ignores any malformed lines
     * @return the tweets, in chronological order from the repository
     * @throws java.io.IOException
     */
    public Set<Tweet> getTweets() throws IOException {
        readRepository();
        Set<Tweet> tweets = new LinkedHashSet<>();
        for (String tweetInput : list) {
            int split = tweetInput.indexOf("> ");
            //strip out the user and the tweet - ignoring malformed lines
            if (split > 0 && tweetInput.length() > split + 2) {
                tweets.add(new Tweet(tweetInput.substring(0,split), tweetInput.substring(split+2)));
            }   
        }
        return tweets;
    }  
    
    private void readRepository() throws IOException {
        //replaced old Buffered reader with streams implementation to read the file into ArrayList
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            //when this repo fails, throw the error back to caller
            throw e;
        }
    }
}
