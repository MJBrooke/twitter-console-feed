package brooke.michael.twitterfeed.map.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.model.User;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UserTreeMap implements UserMap {

    private Map<String, User> userMap;

    public UserTreeMap() {
        //We use a tree map because it is sorted according to the natural ordering of its keys.
        //In our case, we want the map to be sorted alphabetically by username, so it is a natural fit.
        userMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public void put(User newUser) {
        addUpdateUser(newUser);
        addUsersBeingFollowed(newUser);
    }

    public User get(String username) {
        return userMap.get(username);
    }

    public Set<Map.Entry<String, User>> entrySet() {
        return userMap.entrySet();
    }

    private void addUpdateUser(User newUser) {
        User retrievedUser = get(newUser);

        if(retrievedUser == null) {
            userMap.put(newUser.getUsername(), newUser);
        } else {
            retrievedUser.addFollowing(newUser.getFollowing());
        }
    }

    private void addUsersBeingFollowed(User newUser) {
        newUser.getFollowing().forEach(followedUser -> {
            if(get(followedUser) == null) {
                userMap.put(followedUser, new User(followedUser));
            }
        });
    }

    private User get(User user) {
        return get(user.getUsername());
    }

    @Override
    public String toString() {
        return "UserTreeMap{" +
                "userMap=" + userMap +
                '}';
    }
}
