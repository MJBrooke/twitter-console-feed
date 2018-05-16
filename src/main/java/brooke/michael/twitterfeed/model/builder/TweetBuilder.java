package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.model.Tweet;
import brooke.michael.twitterfeed.reader.TwitterFileReader;
import brooke.michael.twitterfeed.map.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TweetBuilder {

    private static final String TWEET_LINE_VALIDATION_REGEX = "(\\w+)> (.){1,140}";
    private static final String OWNER_CONTENTS_DELIMITER = "> ";

    @Value("${tweets}")
    private String tweetsFilePath;

    private TwitterFileReader twitterFileReader;

    @Autowired
    public TweetBuilder(TwitterFileReader twitterFileReader) {
        this.twitterFileReader = twitterFileReader;
    }

    public UserMap addTweets(UserMap users) {
        UserMap newUserMap = new UserMap(users);

        twitterFileReader.readFile(tweetsFilePath)
                .forEach(line -> {
                    Tweet tweet = buildTweet(line);
                    newUserMap.get(tweet.getOwner()).addTweet(tweet);
                });

        return newUserMap;
    }

    private Tweet buildTweet(String line) {
        if(!line.matches(TWEET_LINE_VALIDATION_REGEX)) {
            throw new InvalidFileLineFormatException("Invalid line in the Tweets file: '" + line + "'");
        }

        var tweetTokens = line.split(OWNER_CONTENTS_DELIMITER);

        return new Tweet(tweetTokens[0], tweetTokens[1]);
    }
}
