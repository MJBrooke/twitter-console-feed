package brooke.michael.twitterfeed.model;

import java.util.Set;

public class User {

    //TODO - Use ID's?
    private String username;
    private Set<String> following;

    //TODO - check if this is good practice with the List param
    public User(String username, Set<String> following) {
        this.username = username;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFollowing() {
        return following;
    }

    public void setFollowing(Set<String> following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", following=" + following +
                '}';
    }
}
