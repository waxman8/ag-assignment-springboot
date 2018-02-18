package com.mdv.assignment.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.mdv.assignment.model.User;
import org.springframework.beans.factory.annotation.Value;

/**
 * Implementation of the User Repository using text files
 * @author martin
 */
public class UserRepository {
 
    private List<String> list = new ArrayList<>();
    
    @Value("${file.user}")
    private String filePath;
    
    /**
     * Strips the users and their followers out of their text format 
     * ignoring any malformed lines
     * Creates users and assigns followers and followed to user objects
     * @return alphabetically ordered Map of the users from the repository
     * @throws java.io.IOException
     */
    public Map<String,User> getUsers() throws IOException {
        readRepository();
        Map<String,User> users = new TreeMap<>();
        
        for (String userInput : list) {
            
            String userName = getUserFromLine(userInput);
            if (userName == null) continue;
            if (!users.containsKey(userName)) {
                users.put(userName, new User(userName));
            }
            
            List<String> tmpFollows = getFollowedFromLine(userInput);
            if (tmpFollows == null) continue;
            //add followers and following, creating new users where needed
            for (String followed : tmpFollows) {
                followed = followed.trim();
                //if user does not exist, then add - else just add followers
                if (!users.containsKey(followed)) {
                    users.put(followed, new User(followed));
                }
                users.get(userName).startFollowing(followed);
                users.get(followed).addFollower(userName);
            }
        }
        return users;
    }
    
    private String getUserFromLine(String line) {
        line = line.trim();
        if (line.indexOf("follows") > 0 && line.substring(0, line.indexOf("follows")-1).length() > 0) {
            return line.substring(0, line.indexOf("follows")-1).trim();
        }  else  {
            return null;
        } 
    }
    
    private List<String> getFollowedFromLine(String line) {
        line = line.trim();
        if (line.indexOf("follows") > 0 && line.substring(line.indexOf("follows")+7).trim().length() > 0) {
            String follows = line.substring(line.indexOf("follows")+7).trim();
            //not great behaviour here, asList creates unmodifiable list so this part can possibly be polished
            return Arrays.asList(follows.split(","));
        }  else {
            return null;
        }
    }
    
    private void readRepository() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            //when this repo fails, throw the error back to caller
            throw e;
        }
    }
    
}
 