package brooke.michael.twitterfeed.runner;

import brooke.michael.twitterfeed.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TwitterFeedCommandLineRunner implements CommandLineRunner {

    private TwitterUserService twitterUserService;

    @Autowired
    public TwitterFeedCommandLineRunner(TwitterUserService twitterUserService) {
        this.twitterUserService = twitterUserService;
    }

    @Override
    public void run(String... args) {
        var users = twitterUserService.getTwitterUsers();

        users.entrySet()
                .forEach(user -> {
                    System.out.println(user.getKey());

                    Stream.concat(
                            user.getValue().getTweets().stream(),
                            user.getValue().getFollowing().stream()
                                    .map(userBeingFollowed -> users.get(userBeingFollowed).getTweets())
                                    .flatMap(List::stream)
                                    .collect(Collectors.toList()).stream()
                    ).sorted()
                            .forEach(tweet -> System.out.println("\t@" + tweet.getOwner() + ": " + tweet.getContent()));
                });
    }
}
