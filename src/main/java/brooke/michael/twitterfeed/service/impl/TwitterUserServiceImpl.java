package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.model.builder.TweetBuilder;
import brooke.michael.twitterfeed.model.builder.UserBuilder;
import brooke.michael.twitterfeed.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterUserServiceImpl implements TwitterUserService {

    private UserBuilder userBuilder;
    private TweetBuilder tweetBuilder;

    @Autowired
    public TwitterUserServiceImpl(UserBuilder userBuilder, TweetBuilder tweetBuilder) {
        this.userBuilder = userBuilder;
        this.tweetBuilder = tweetBuilder;
    }

    public UserMap getTwitterUsers() {
        return tweetBuilder.addTweets(userBuilder.buildUsers());
    }
}
