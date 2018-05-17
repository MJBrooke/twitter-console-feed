package brooke.michael.twitterfeed.service;

import brooke.michael.twitterfeed.map.UserMap;

public interface UserService {

    /**
     * Fetches user data based on the program inputs
     *
     * @return A UserMap of the Users and the other users that they follow
     */
    UserMap getUsers();
}
