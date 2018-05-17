package brooke.michael.twitterfeed.map;

import brooke.michael.twitterfeed.model.User;

import java.util.Map;
import java.util.Set;

public interface UserMap {

    /**
     * Adds a new user to the map.
     * If the user already exists, the map will update them.
     * It will also ensure that all users being followed exist in the map as well.
     *
     * @param newUser The object representing the new User to be added to the map
     */
    void put(User newUser);

    /**
     * Retrieves a user based on their Username
     *
     * @param username The user's username
     * @return The user, if they exist or else a null
     */
    User get(String username);

    /**
     * Returns the entry set of the UserMap, allowing for iteration over its elements
     *
     * @return The Map's entry set
     */
    Set<Map.Entry<String, User>> entrySet();
}
