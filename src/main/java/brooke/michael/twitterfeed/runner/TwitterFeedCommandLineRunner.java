package brooke.michael.twitterfeed.runner;

import brooke.michael.twitterfeed.model.Tweet;
import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.set.UserMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class TwitterFeedCommandLineRunner implements CommandLineRunner {

    @Value("${users}")
    private String usersFilePath;

    @Value("${tweets}")
    private String tweetsFilePath;

    @Override
    public void run(String... args) throws Exception {
        UserMap users = getUsers();
        getTweets(users);

        //TODO - if a follower doesn't exist, he must also be added

        users.entrySet()
                .forEach(user -> {
                    System.out.println(user.getKey());

                    Stream.concat(
                            user.getValue().getTweets().stream(),
                            user.getValue().getFollowing().stream()
                                    //TODO - below breaks when follower doesn't exist as user in map
                                    .map(userBeingFollowed -> users.get(userBeingFollowed).getTweets())
                                    .flatMap(List::stream)
                                    .collect(Collectors.toList()).stream()
                    ).sorted()
                            .forEach(tweet -> System.out.println("\t@" + tweet.getOwner() + ": " + tweet.getContent()));
                });
    }

    private UserMap getUsers() throws IOException {
        var users = new UserMap();

        var path = Paths.get(usersFilePath);

        //TODO - Check that the file exists before continuing - exception otherwise
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> users.put(buildUser(line)));
        lines.close();

        return users;
    }

    private User buildUser(String line) {
        //TODO - REGEX check before continuing - exception if messed up
        var tokens = line.split(" follows ");

        var following = tokens[1].split(", ");

        return new User(tokens[0], Arrays.stream(following).collect(Collectors.toSet()));
    }

    private void getTweets(UserMap users) throws IOException {
        var path = Paths.get(tweetsFilePath);

        //TODO - Check that the file exists before continuing - exception otherwise
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> {
            Tweet tweet = buildTweet(line);
            users.get(tweet.getOwner()).getTweets().add(tweet);
        });
        lines.close();
    }

    private Tweet buildTweet(String line) {
        //TODO - REGEX check before continuing - exception if messed up
        var tweetTokens = line.split("> ");

        return new Tweet(tweetTokens[0], tweetTokens[1]);
    }
}
