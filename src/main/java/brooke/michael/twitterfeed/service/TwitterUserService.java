package brooke.michael.twitterfeed.service;

import brooke.michael.twitterfeed.map.UserMap;

public interface TwitterUserService {

    /**
     * Retrieves a map of Twitter users
     *
     * @return Map of Twitter users
     */
    UserMap getTwitterUsers();
}
