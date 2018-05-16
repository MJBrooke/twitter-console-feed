package brooke.michael.twitterfeed.model;

public class Tweet implements Comparable<Tweet> {

    private static long currentTimestamp = 1;

    private long timestamp;
    private String owner;
    private String content;

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
