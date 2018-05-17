package brooke.michael.twitterfeed.service;

import brooke.michael.twitterfeed.map.UserMap;

public interface TwitterUserService {

    /**
     * Retrieves a map of Twitter users based on the user and tweet information supplied
     *
     * @return Map of Twitter users
     */
    UserMap getTwitterUsers();
}
