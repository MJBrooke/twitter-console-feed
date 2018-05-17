package brooke.michael.twitterfeed.model;

public class Tweet implements Comparable<Tweet> {

    //Since no timestamps are given, we will simulate it for each new Tweet
    private static long currentTimestamp = 1;

    private final long timestamp;
    private final String owner;
    private final String content;

    public Tweet(String owner, String content) {
        this.timestamp = currentTimestamp++;
        this.owner = owner;
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Tweet other) {
        return Long.compare(this.timestamp, other.timestamp);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "timestamp=" + timestamp +
                ", owner='" + owner + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
