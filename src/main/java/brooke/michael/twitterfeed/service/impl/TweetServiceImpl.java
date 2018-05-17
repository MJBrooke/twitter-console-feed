package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.model.Tweet;
import brooke.michael.twitterfeed.model.builder.TweetBuilder;
import brooke.michael.twitterfeed.reader.TwitterFileReader;
import brooke.michael.twitterfeed.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

    @Value("${tweets}")
    private String tweetsFilePath;

    private TwitterFileReader twitterFileReader;

    private TweetBuilder tweetBuilder;

    @Autowired
    public TweetServiceImpl(TwitterFileReader twitterFileReader, TweetBuilder tweetBuilder) {
        this.twitterFileReader = twitterFileReader;
        this.tweetBuilder = tweetBuilder;
    }

    public UserMap getTweets(UserMap users) {
        twitterFileReader.readFile(tweetsFilePath)
                .forEach(line -> {
                    Tweet tweet = tweetBuilder.buildTweet(line);
                    users.get(tweet.getOwner()).addTweet(tweet);
                });

        return users;
    }
}
