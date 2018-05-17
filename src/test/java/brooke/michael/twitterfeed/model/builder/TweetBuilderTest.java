package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
public class TweetBuilderTest {

    private TweetBuilder tweetBuilder = new TweetBuilder();

    @Test
    public void buildTweet_success() {
        Tweet tweet = tweetBuilder.buildTweet("Alan> If you have a procedure with 10 parameters, you probably missed some.");
        assertEquals("Alan", tweet.getOwner());
        assertEquals("If you have a procedure with 10 parameters, you probably missed some.", tweet.getContent());
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweet_failure_lineDoesNotMatchTweetRegex_incorrectGreaterThan() {
        tweetBuilder.buildTweet("Alan< This is a malformed line");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweet_failure_lineDoesNotMatchTweetRegex_noGreaterThan() {
        tweetBuilder.buildTweet("Alan This is a malformed line");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweetfailure_lineDoesNotMatchTweetRegex_spaceAfterOwner() {
        tweetBuilder.buildTweet("Alan > This is a malformed line");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweetfailure_lineDoesNotMatchTweetRegex_noSpaceAfterGreaterThan() {
        tweetBuilder.buildTweet("Alan>This is a malformed line");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweetfailure_lineDoesNotMatchTweetRegex_noOwner() {
        tweetBuilder.buildTweet("> This is a malformed line");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweetfailure_lineDoesNotMatchTweetRegex_noTweet() {
        tweetBuilder.buildTweet("Alan>");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildTweetfailure_lineDoesNotMatchTweetRegex_tweetGreaterThan140Characters() {
        tweetBuilder.buildTweet("Alan> This is a tweet that has exactly 142 characters in it which makes it not match the tweet regex and it therefore throws this error when invoked");
    }
}