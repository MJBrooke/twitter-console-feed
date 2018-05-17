package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
public class UserBuilderTest {

    private UserBuilder userBuilder = new UserBuilder();

    @Test
    public void buildUser_success_followingOneUser() {
        User alan = userBuilder.buildUser("Alan follows Martin");
        assertEquals("Alan", alan.getUsername());
        assertEquals(1, alan.getFollowing().size());
    }

    @Test
    public void buildUser_success_followingMultipleUsers() {
        User derek = userBuilder.buildUser("Derek follows Martin, Casey");
        assertEquals("Derek", derek.getUsername());
        assertEquals(2, derek.getFollowing().size());
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUser_failure_lineDoesNotMatchUserRegex_notSeparatedByFollows() {
        userBuilder.buildUser("Alan follow Martin");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUser_failure_lineDoesNotMatchUserRegex_noFollowsAtAll() {
        userBuilder.buildUser("Alan Martin");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUser_failure_lineDoesNotMatchUserRegex_commaWithNoOtherUser() {
        userBuilder.buildUser("Alan follows Martin,");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUser_failure_lineDoesNotMatchUserRegex_commaAndSpaceWithNoOtherUser() {
        userBuilder.buildUser("Alan follows Martin, ");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUser_failure_lineDoesNotMatchUserRegex_noMainUser() {
        userBuilder.buildUser(" follows Martin");
    }

    @Test(expected = InvalidFileLineFormatException.class)
    public void buildUser_failure_lineDoesNotMatchUserRegex_noFollowsUsers() {
        userBuilder.buildUser("Alan follows ");
    }
}