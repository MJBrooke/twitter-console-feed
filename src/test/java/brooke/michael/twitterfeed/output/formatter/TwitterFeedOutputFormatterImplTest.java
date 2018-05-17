package brooke.michael.twitterfeed.output.formatter;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
import brooke.michael.twitterfeed.model.Tweet;
import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.output.formatter.impl.TwitterFeedOutputFormatterImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TwitterFeedOutputFormatterImplTest {

    private TwitterFeedOutputFormatterImpl twitterFeedOutputFormatter = new TwitterFeedOutputFormatterImpl();
    
    @Test
    public void formatTwitterFeedOutputString() {
        String formatterTwitterFeedOutput = twitterFeedOutputFormatter.formatTwitterFeedOutputString(buildUserMap());

        assertEquals(
                "Alan" + System.lineSeparator() +
                        "\t@Alan: If you have a procedure with 10 parameters, you probably missed some." + System.lineSeparator() +
                        "\t@Alan: Random numbers should not be generated with a method chosen at random." + System.lineSeparator() +
                        "Martin" + System.lineSeparator() +
                        "Ward" + System.lineSeparator() +
                        "\t@Alan: If you have a procedure with 10 parameters, you probably missed some." + System.lineSeparator() +
                        "\t@Ward: There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors." + System.lineSeparator() +
                        "\t@Alan: Random numbers should not be generated with a method chosen at random." + System.lineSeparator(),
                formatterTwitterFeedOutput
        );
    }

    private UserMap buildUserMap() {
        UserMap userMap = new UserTreeMap();

        User ward = new User("Ward", Sets.newSet("Martin", "Alan"));
        User alan = new User("Alan", Sets.newSet("Martin"));

        userMap.put(ward);
        userMap.put(alan);

        userMap.get("Alan").addTweet(new Tweet("Alan", "If you have a procedure with 10 parameters, you probably missed some."));
        userMap.get("Ward").addTweet(new Tweet("Ward", "There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors."));
        userMap.get("Alan").addTweet(new Tweet("Alan", "Random numbers should not be generated with a method chosen at random."));

        return userMap;
    }
}