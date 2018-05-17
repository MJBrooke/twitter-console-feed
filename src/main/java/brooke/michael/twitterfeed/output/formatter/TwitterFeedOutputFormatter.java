package brooke.michael.twitterfeed.output.formatter;

import brooke.michael.twitterfeed.map.UserMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TwitterFeedOutputFormatter {

    public String formatTwitterFeedOutputString(UserMap users) {
        return users.entrySet().stream()
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
    }
}
