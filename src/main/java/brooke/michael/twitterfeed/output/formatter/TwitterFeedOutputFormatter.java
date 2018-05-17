package brooke.michael.twitterfeed.output.formatter;

import brooke.michael.twitterfeed.map.UserMap;

public interface TwitterFeedOutputFormatter {

    /**
     * Returns a formatted string from data provided in the UserMap object
     * @param users The UserMap containing User and Tweet data
     * @return Formatted output String representing data from the UserMap
     */
    String formatTwitterFeedOutputString(UserMap users);
}
