package brooke.michael.twitterfeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//TODO - provide files in local test resources

@RunWith(SpringRunner.class)
@SpringBootTest({"users=C:/Users/201377881/Documents/Allan Gray/user.txt", "tweets=seh"})
public class TwitterFeedApplicationTests {

    @Test
    public void contextLoads() {
    }

}
