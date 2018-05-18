package brooke.michael.twitterfeed;

import brooke.michael.twitterfeed.exception.FailedToReadFileException;
import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.output.formatter.TwitterFeedOutputFormatter;
import brooke.michael.twitterfeed.service.TwitterUserService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
        try {
            UserMap users = twitterUserService.getTwitterUsers();
            System.out.println(twitterFeedOutputFormatter.formatTwitterFeedOutputString(users));
        } catch(FailedToReadFileException | InvalidFileLineFormatException e) {
            System.out.println(e.getMessage());
            System.exit(e.getExitCode());
        }
    }

    public static void main(String[] args) {
        System.err.close();
        new SpringApplicationBuilder(TwitterFeedApplication.class)
                .logStartupInfo(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
