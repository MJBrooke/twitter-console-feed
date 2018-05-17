package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
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
    private UserService userService;

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private TwitterUserServiceImpl twitterUserService;

    @Test
    public void getTwitterUsers() {
        UserMap userMapFromBuildUsers = new UserTreeMap();
        UserMap userMapFromTweetBuilder = new UserTreeMap();

        when(userService.getUsers()).thenReturn(userMapFromBuildUsers);
        when(tweetService.getTweets(userMapFromBuildUsers)).thenReturn(userMapFromTweetBuilder);

        UserMap userMapFromGetTwitterUsers = twitterUserService.getTwitterUsers();

        verify(userService).getUsers();
        //We must verify that getTweets was invoked on the UserMap returned by the getUsers method
        verify(tweetService).getTweets(userMapFromBuildUsers);
        //We must verify that the map returned by getTweets is what was returned by the method
        assertEquals(userMapFromTweetBuilder, userMapFromGetTwitterUsers);
    }
}