package com.mdv.assignment;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import com.mdv.assignment.model.Tweet;
import com.mdv.assignment.model.User;
import com.mdv.assignment.repository.TweetRepository;
import com.mdv.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller class that will control the process once invoked
 * @author martin
 */
public class Simulator {
    
    private UserRepository userRepo;
    private TweetRepository tweetRepo;
    private Map<String,User> users = new TreeMap<>();
    private Set<Tweet> tweets = new LinkedHashSet<>();
    
    private String browserFeed = "";
    
    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    @Autowired
    public void setTweetRepo(TweetRepository tweetRepo) {
        this.tweetRepo = tweetRepo;
    }
    
    /**
     * @return the browserFeed string
     */
    public String getBrowserFeed() {
        return browserFeed;
    }
    
    /**
     * Method that controls the demonstration of the twitter-like feed
     * Since this time we are going to watch the console, allowing the IO errors to bubble up
     * @throws java.io.IOException let IO errors bubble up the call stack
     */
    public void simulate() throws IOException{
        users = getUserRepo().getUsers();
        tweets = getTweetRepo().getTweets();
        browserFeed = "";
        applyTweetsToUsers();
        generateAndPrintCurrentState();
    }
    
    /**
     * publish the tweets to the relevant user feeds 
     */
    private void applyTweetsToUsers() {
        for (Tweet tweet : tweets) {
            if (users.containsKey(tweet.getUserName())) {
                users.get(tweet.getUserName()).addTweet(tweet);
                for (String follower : users.get(tweet.getUserName()).getFollowers()) {
                   users.get(follower).addTweet(tweet);
                }
            }
        }
    }
    
    /**
     * Print the current state to the console, showcasing the twitter-like feed
     */
    private void generateAndPrintCurrentState() {
        for (Map.Entry<String,User> entry : users.entrySet()) {
            User user = entry.getValue();
            System.out.println("");
            System.out.println(user.getName());
            browserFeed = browserFeed + "<br><b>" + user.getName() + "</b><br>";
            for (Tweet tweet : user.getFeedHistory()) {
                System.out.println("\t@" + tweet.getUserName() + ": " + tweet.getText());
                browserFeed = browserFeed + "&nbsp; &nbsp; @" + tweet.getUserName() + ": " + tweet.getText() + "<br>";
            }
        }
    }

    /**
     * @return the userRepo - used only in unit testing
     */
    public UserRepository getUserRepo() {
        return userRepo;
    }

    /**
     * @return the tweetRepo - used only in unit testing
     */
    public TweetRepository getTweetRepo() {
        return tweetRepo;
    }

}
