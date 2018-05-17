package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
import brooke.michael.twitterfeed.model.builder.TweetBuilder;
import brooke.michael.twitterfeed.model.builder.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
public class TwitterUserServiceImplTest {

    @Mock
    private UserBuilder userBuilder;

    @Mock
    private TweetBuilder tweetBuilder;

    @InjectMocks
    private TwitterUserServiceImpl twitterUserService;

    @Test
    public void getTwitterUsers() {
        UserMap userMapFromBuildUsers = new UserTreeMap();
        UserMap userMapFromTweetBuilder = new UserTreeMap();

        when(userBuilder.buildUsers()).thenReturn(userMapFromBuildUsers);
        when(tweetBuilder.addTweets(userMapFromBuildUsers)).thenReturn(userMapFromTweetBuilder);

        UserMap userMapFromGetTwitterUsers = twitterUserService.getTwitterUsers();

        verify(userBuilder).buildUsers();
        //We must verify that addTweets was invoked on the UserMap returned by the buildUsers method
        verify(tweetBuilder).addTweets(userMapFromBuildUsers);
        //We must verify that the map returned by addTweets is what was returned by the method
        assertEquals(userMapFromTweetBuilder, userMapFromGetTwitterUsers);
    }
}