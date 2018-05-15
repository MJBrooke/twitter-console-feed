package brooke.michael.twitterfeed.set;

import brooke.michael.twitterfeed.model.User;

import java.util.HashMap;
import java.util.Map;

//TODO - create abstraction for this for the Dependency Inversion principle
public class UserMap {

    private Map<String, User> userMap;

    public UserMap() {
        userMap = new HashMap<>();
    }

    public void put(User newUser) {
        User user = get(newUser);

        if(user == null) {
            userMap.put(newUser.getUsername(), newUser);
        } else {
            user.getFollowing().addAll(newUser.getFollowing());
        }
    }

    //TODO - is this needed?
    private User get(User user) {
        return userMap.get(user.getUsername());
    }

    @Override
    public String toString() {
        return "UserMap{" +
                "userMap=" + userMap +
                '}';
    }
}
