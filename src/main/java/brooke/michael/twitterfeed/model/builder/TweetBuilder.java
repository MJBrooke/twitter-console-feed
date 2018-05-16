package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.model.Tweet;
import brooke.michael.twitterfeed.reader.TwitterFileReader;
import brooke.michael.twitterfeed.map.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//TODO - make an interface
public class TweetBuilder {

    private static final String OWNER_CONTENTS_DELIMITER = "> ";

    @Value("${tweets}")
    private String tweetsFilePath;

    private TwitterFileReader twitterFileReader;

    @Autowired
    public TweetBuilder(TwitterFileReader twitterFileReader) {
        this.twitterFileReader = twitterFileReader;
    }

    public void addTweets(UserMap users) {
        List<String> strings = twitterFileReader.readFile(tweetsFilePath);

        strings.forEach(line -> {
            Tweet tweet = buildTweet(line);
            users.get(tweet.getOwner()).addTweet(tweet);
        });
    }

    private Tweet buildTweet(String line) {
        //TODO - REGEX check before continuing - exception if messed up
        var tweetTokens = line.split(OWNER_CONTENTS_DELIMITER);

        return new Tweet(tweetTokens[0], tweetTokens[1]);
    }
}
