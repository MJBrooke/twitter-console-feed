package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.map.UserMap;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserBuilderTest {

    @Mock
    private TwitterTextFileReader twitterTextFileReader;

    @InjectMocks
    private UserBuilder userBuilder;

    @Before
    public void before() {
        ReflectionTestUtils.setField(userBuilder, "usersFilePath", "C:/Users/some_user/users.txt");
    }

    @Test
    public void buildUsers_success() {
        //Given the following valid lines representing users and the users they follow
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Arrays.asList(
                "Ward follows Alan",
                "Alan follows Martin",
                "Ward follows Martin, Alan"
        ));

        //When users are built
        UserMap userMap = userBuilder.buildUsers();

        assertEquals(3, userMap.size());
        assertEquals(1, userMap.get("Alan").getFollowing().size());
        assertEquals(2, userMap.get("Ward").getFollowing().size());
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUsers_failure_lineDoesNotMatchUserRegex_notSeparatedByFollows() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan follow Martin"));
        userBuilder.buildUsers();
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUsers_failure_lineDoesNotMatchUserRegex_noFollowsAtAll() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan Martin"));
        userBuilder.buildUsers();
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUsers_failure_lineDoesNotMatchUserRegex_commaWithNoOtherUser() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan follows Martin,"));
        userBuilder.buildUsers();
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUsers_failure_lineDoesNotMatchUserRegex_commaAndSpaceWithNoOtherUser() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan follows Martin, "));
        userBuilder.buildUsers();
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUsers_failure_lineDoesNotMatchUserRegex_noMainUser() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList(" follows Martin"));
        userBuilder.buildUsers();
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUsers_failure_lineDoesNotMatchUserRegex_noFollowsUsers() {
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Collections.singletonList("Alan follows "));
        userBuilder.buildUsers();
    }
}