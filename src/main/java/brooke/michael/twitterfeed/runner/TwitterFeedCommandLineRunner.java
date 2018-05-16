package brooke.michael.twitterfeed.runner;

import brooke.michael.twitterfeed.model.builder.TweetBuilder;
import brooke.michael.twitterfeed.model.builder.UserBuilder;
import brooke.michael.twitterfeed.set.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class TwitterFeedCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserBuilder userBuilder;

    @Autowired
    private TweetBuilder tweetBuilder;

    @Override
    public void run(String... args) {
        UserMap users = userBuilder.buildUsers();
        tweetBuilder.getTweets(users);

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
