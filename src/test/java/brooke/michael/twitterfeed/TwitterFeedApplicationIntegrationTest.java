package brooke.michael.twitterfeed;

import brooke.michael.twitterfeed.reader.TwitterFileReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest({"users=C:/Users/my_user/user.txt", "tweets=C:/Users/my_user/tweet.txt"})
public class TwitterFeedApplicationIntegrationTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //We mock out only the bottom-most part of the software - reading the files in.
    @TestConfiguration
    static class TwitterFileReaderTestContextConfiguration {
        @Bean
        @Primary
        public TwitterFileReader twitterFileReader() {
            TwitterFileReader mock = mock(TwitterFileReader.class);

            when(mock.readFile("C:/Users/my_user/user.txt")).thenReturn(Arrays.asList(
                    "Ward follows Alan",
                    "Alan follows Martin",
                    "Ward follows Martin, Alan"
            ));

            when(mock.readFile("C:/Users/my_user/tweet.txt")).thenReturn(Arrays.asList(
                    "Alan> If you have a procedure with 10 parameters, you probably missed some.",
                    "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.",
                    "Alan> Random numbers should not be generated with a method chosen at random."
            ));

            return mock;
        }
    }

    @BeforeClass
    public static void beforeClass() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterClass()
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Test
    public void integrationTest() {
        assertTrue(
                outContent.toString().contains("Alan" + System.lineSeparator() +
                "\t@Alan: If you have a procedure with 10 parameters, you probably missed some." + System.lineSeparator() +
                "\t@Alan: Random numbers should not be generated with a method chosen at random." + System.lineSeparator() +
                "Martin" + System.lineSeparator() +
                "Ward" + System.lineSeparator() +
                "\t@Alan: If you have a procedure with 10 parameters, you probably missed some." + System.lineSeparator() +
                "\t@Ward: There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors." + System.lineSeparator() +
                "\t@Alan: Random numbers should not be generated with a method chosen at random." + System.lineSeparator())
        );
    }

}
