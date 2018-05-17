package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.model.Tweet;
import org.springframework.stereotype.Component;

@Component
public class TweetBuilder {

    private static final String TWEET_LINE_VALIDATION_REGEX = "(\\w+)> (.){1,140}";
    private static final String OWNER_CONTENTS_DELIMITER = "> ";

    public Tweet buildTweet(String line) {
        if(!line.matches(TWEET_LINE_VALIDATION_REGEX)) {
            throw new InvalidFileLineFormatException("Invalid line in the Tweets file: '" + line + "'");
        }

        String[] tweetTokens = line.split(OWNER_CONTENTS_DELIMITER);

        return new Tweet(tweetTokens[0], tweetTokens[1]);
    }
}
