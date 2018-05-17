package brooke.michael.twitterfeed.runner;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
import brooke.michael.twitterfeed.model.Tweet;
import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.service.TwitterUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TwitterFeedCommandLineRunnerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String newLine = System.lineSeparator();

    @Mock
    private TwitterUserService twitterUserService;

    @InjectMocks
    private TwitterFeedCommandLineRunner twitterFeedCommandLineRunner;

    @Before
    public void before() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void after() {
        System.setOut(System.out);
    }

    @Test
    public void run() {
        when(twitterUserService.getTwitterUsers()).thenReturn(buildUserMap());

        twitterFeedCommandLineRunner.run();

        assertEquals(
                "Alan" + newLine +
                        "\t@Alan: If you have a procedure with 10 parameters, you probably missed some." + newLine +
                        "\t@Alan: Random numbers should not be generated with a method chosen at random." + newLine +
                        "Martin" + newLine +
                        "Ward" + newLine +
                        "\t@Alan: If you have a procedure with 10 parameters, you probably missed some." + newLine +
                        "\t@Ward: There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors." + newLine +
                        "\t@Alan: Random numbers should not be generated with a method chosen at random." + newLine,
                outContent.toString()
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