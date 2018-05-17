package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.model.builder.TweetBuilder;
import brooke.michael.twitterfeed.reader.impl.TwitterTextFileReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TweetServiceTest {

    @Mock
    private TwitterTextFileReader twitterTextFileReader;

    @Spy
    private TweetBuilder tweetBuilder;

    @InjectMocks
    private TweetService tweetService;

    @Before
    public void before() {
        ReflectionTestUtils.setField(tweetService, "tweetsFilePath", "C:/Users/some_user/tweets.txt");
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
        tweetService.getTweets(userMap);

        //Then the tweets are appropriately assigned to the Users in the UserMap, with the correct owner and content
        assertEquals(2, userMap.get("Alan").getTweets().size());
        assertEquals(1, userMap.get("Ward").getTweets().size());
    }
}