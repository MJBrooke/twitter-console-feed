package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.reader.impl.TwitterTextFileReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TweetBuilderTest {

    @Mock
    private TwitterTextFileReader twitterTextFileReader;

    @InjectMocks
    private TweetBuilder tweetBuilder;

    @Before
    public void before() {
        ReflectionTestUtils.setField(tweetBuilder, "tweetsFilePath", "C:/Users/some_user/tweets.txt");
    }

    @Test
    public void addTweets_success() {
        //Given we have a map of the following 2 users
        UserMap userMap = new UserTreeMap();
        userMap.put(new User("Alan"));
        userMap.put(new User("Ward"));

        //And we have the following set of tweets relating to those users
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Arrays.asList(
                "Alan> If you have a procedure with 10 parameters, you probably missed some.",
                "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.",
                "Alan> Random numbers should not be generated with a method chosen at random."
        ));

        //When we add tweets
        tweetBuilder.addTweets(userMap);

        //Then the tweets are appropriately assigned to the Users in the UserMap, with the correct owner and content
        assertEquals(2, userMap.get("Alan").getTweets().size());
        assertEquals("If you have a procedure with 10 parameters, you probably missed some.", userMap.get("Alan").getTweets().get(0).getContent());
        assertEquals("Alan", userMap.get("Alan").getTweets().get(0).getOwner());
        assertEquals("Random numbers should not be generated with a method chosen at random.", userMap.get("Alan").getTweets().get(1).getContent());
        assertEquals("Alan", userMap.get("Alan").getTweets().get(1).getOwner());

        assertEquals(1, userMap.get("Ward").getTweets().size());
        assertEquals("There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.", userMap.get("Ward").getTweets().get(0).getContent());
        assertEquals("Ward", userMap.get("Ward").getTweets().get(0).getOwner());
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_incorrectGreaterThan() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan< This is a malformed line"));
        tweetBuilder.addTweets(any(UserMap.class));
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_noGreaterThan() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan This is a malformed line"));
        tweetBuilder.addTweets(any(UserMap.class));
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_spaceAfterOwner() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan > This is a malformed line"));
        tweetBuilder.addTweets(any(UserMap.class));
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_noSpaceAfterGreaterThan() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan> This is a malformed line\\"));
        tweetBuilder.addTweets(any(UserMap.class));
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_noOwner() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("> This is a malformed line"));
        tweetBuilder.addTweets(any(UserMap.class));
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_noTweet() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan>"));
        tweetBuilder.addTweets(any(UserMap.class));
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void addTweets_failure_lineDoesNotMatchTweetRegex_tweetGreaterThan140Characters() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan> This is a tweet that has exactly 142 characters in it which makes it not match the tweet regex and it therefore throws this error when invoked"));
        tweetBuilder.addTweets(any(UserMap.class));
    }
}