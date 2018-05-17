package brooke.michael.twitterfeed.service;

import brooke.michael.twitterfeed.map.UserMap;

public interface TweetService {

    /**
     * Fetches Tweet data based on the program inputs
     *
     * @param users The UserMap representing the current set of users and their followers
     * @return An updated UserMap with Tweets assigned to their corresponding user
     */
    UserMap getTweets(UserMap users);
}
