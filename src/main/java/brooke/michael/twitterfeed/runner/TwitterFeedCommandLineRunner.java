package brooke.michael.twitterfeed.runner;

import brooke.michael.twitterfeed.map.UserMap;
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
        UserMap users = twitterUserService.getTwitterUsers();

        String output = users.entrySet().stream()
                .map(user -> user.getKey() + System.lineSeparator() +
                        Stream.concat(
                                user.getValue().getTweets().stream(),
                                user.getValue().getFollowing().stream()
                                        .map(userBeingFollowed -> users.get(userBeingFollowed).getTweets())
                                        .flatMap(List::stream)
                                        .collect(Collectors.toList()).stream()
                        ).sorted()
                                .map(tweet -> "\t@" + tweet.getOwner() + ": " + tweet.getContent() + System.lineSeparator())
                                .reduce("", String::concat))
                .reduce("", String::concat);

        System.out.print(output);
    }
}
