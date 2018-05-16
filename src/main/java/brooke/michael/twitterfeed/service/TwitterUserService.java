package brooke.michael.twitterfeed.service;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.model.builder.TweetBuilder;
import brooke.michael.twitterfeed.model.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterUserService {

    private UserBuilder userBuilder;
    private TweetBuilder tweetBuilder;

    @Autowired
    public TwitterUserService(UserBuilder userBuilder, TweetBuilder tweetBuilder) {
        this.userBuilder = userBuilder;
        this.tweetBuilder = tweetBuilder;
    }

    public UserMap getTwitterUsers() {
        UserMap users = userBuilder.buildUsers();
        tweetBuilder.addTweets(users);
        return users;
    }
}
