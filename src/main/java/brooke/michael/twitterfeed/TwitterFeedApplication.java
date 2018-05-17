package brooke.michael.twitterfeed;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.output.formatter.TwitterFeedOutputFormatter;
import brooke.michael.twitterfeed.service.TwitterUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterFeedApplication implements CommandLineRunner {

    private TwitterUserService twitterUserService;
    private TwitterFeedOutputFormatter twitterFeedOutputFormatter;

    public TwitterFeedApplication(TwitterUserService twitterUserService, TwitterFeedOutputFormatter twitterFeedOutputFormatter) {
        this.twitterUserService = twitterUserService;
        this.twitterFeedOutputFormatter = twitterFeedOutputFormatter;
    }

    @Override
    public void run(String... args) {
        UserMap users = twitterUserService.getTwitterUsers();
        System.out.println(twitterFeedOutputFormatter.formatTwitterFeedOutputString(users));
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterFeedApplication.class, args);
    }
}
