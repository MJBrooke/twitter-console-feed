package brooke.michael.twitterfeed.map;

import brooke.michael.twitterfeed.model.User;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//TODO - create abstraction for this for the Dependency Inversion principle
//TODO - make this a component?
public class UserMap {

    private Map<String, User> userMap;

    public UserMap() {
        //We use a tree map because it is sorted according to the natural ordering of its keys.
        //In our case, we want the map to be sorted alphabetically by username, so it is a natural fit.
        userMap = new TreeMap<>();
    }

    public void put(User newUser) {
        User retrievedUser = get(newUser);

        if(retrievedUser == null) {
            userMap.put(newUser.getUsername(), newUser);
        } else {
            retrievedUser.addFollowing(newUser.getFollowing());
        }

        newUser.getFollowing().forEach(followedUser -> {
            if(get(followedUser) == null) {
                userMap.put(followedUser, new User(followedUser));
            }
        });
    }

    public Set<Map.Entry<String, User>> entrySet() {
        return new TreeMap<>(userMap).entrySet();
    }

    public User get(String username) {
        return userMap.get(username);
    }

    private User get(User user) {
        return get(user.getUsername());
    }

    @Override
    public String toString() {
        return "UserMap{" +
                "userMap=" + userMap +
                '}';
    }
}
