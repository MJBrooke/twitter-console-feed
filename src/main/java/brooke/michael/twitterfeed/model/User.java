package brooke.michael.twitterfeed.model;

import java.util.*;

public class User {

    private final String username;
    private final Set<String> following;
    private final List<Tweet> tweets;

    public User(String username) {
        this.username = username;
        following = new HashSet<>();
        tweets = new ArrayList<>();
    }

    public User(String username, Set<String> following) {
        this(username);
        this.following.addAll(following);
    }

    public String getUsername() {
        return username;
    }

    public void addFollowing(Set<String> newFollowing) {
        following.addAll(newFollowing);
    }

    public Set<String> getFollowing() {
        return new HashSet<>(following);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return new ArrayList<>(tweets);
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
