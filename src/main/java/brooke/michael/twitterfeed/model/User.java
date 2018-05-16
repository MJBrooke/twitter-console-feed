package brooke.michael.twitterfeed.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private String username;
    private Set<String> following;
    private List<Tweet> tweets;

    public User(String username) {
        this.username = username;
    }

    //TODO - check if this is good practice with the List param
    public User(String username, Set<String> following) {
        this.username = username;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getFollowing() {
        if(following == null) {
            following = new HashSet<>();
        }
        return following;
    }

    public List<Tweet> getTweets() {
        //TODO - good practice?
        if(tweets == null) {
            //TODO - right data structure?
            tweets = new ArrayList<>();
        }

        return tweets;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", following=" + following +
                ", tweets=" + tweets +
                '}';
    }
}
