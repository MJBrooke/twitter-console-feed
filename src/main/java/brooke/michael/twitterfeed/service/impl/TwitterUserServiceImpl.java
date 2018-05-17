package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterUserServiceImpl implements TwitterUserService {

    private UserService userService;
    private TweetService tweetService;

    @Autowired
    public TwitterUserServiceImpl(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }

    public UserMap getTwitterUsers() {
        return tweetService.getTweets(userService.getUsers());
    }
}
