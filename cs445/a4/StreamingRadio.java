package cs445.a4;

/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * like/dislike ratings that users assign to songs.
 */
public interface StreamingRadio {

    /**
     * The abstract methods below are declared as void methods with no
     * parameters. You need to expand each declaration to specify a return type
     * and parameters, as necessary. You also need to include a detailed comment
     * for each abstract method describing its effect, its return value, any
     * corner cases that the client may need to consider, any exceptions the
     * method may throw (including a description of the circumstances under
     * which this will happen), and so on. You should include enough details
     * that a client could use this data structure without ever being surprised
     * or not knowing what will happen, even though they haven't read the
     * implementation.
     */

    /**
     * Adds a new song to the system.
     *
     * <p> If newSong is not null, this set does not contain newSong, and this
     * set has available capacity (if fixed), then addSong modifies the set so that
     * it contains newSong. All other songs remain unmodified. Duplicates are
     * determined using the .equals() method.
     *
     * <p> If newSong is null, then addSong throws IllegalArgumentException without
     * modifying the set. If this set already contains newSong, then addSong
     * returns false without modifying the set. If this set has a capacity
     * limit, and does not have available capacity, then add throws
     * SetFullException without modifying the set.
     *
     * @param newSong  The object to be added as a new song
     * @return  true if the addition is successful; false if the item already is
     * in this set
     * @throws SetFullException  If this set has a fixed capacity and does not
     * have the capacity to store an additional song
     * @throws IllegalArgumentException  If newSong is null
     */
    public boolean addSong(Song newSong)
    throws SetFullException, IllegalArgumentException;

    /**
     * Removes an existing song from the system.
     */
    void removeSong();

    /**
     * Adds an existing song to the playlist for an existing radio station.
     */
    void addToStation();

    /**
     * Removes a song from the playlist for a radio station.
     */
    void removeFromStation();

    /**
     * Sets a user's rating for a song, as either "like" or "dislike".
     */
    void rateSong();

    /**
     * Clears a user's rating on a song. If this user has rated this song and
     * the rating has not already been cleared, then the rating is cleared and
     * the state will appear as if the rating was never made. If there is no
     * such rating on record (either because this user has not rated this song,
     * or because the rating has already been cleared), then this method will
     * throw an IllegalArgumentException.
     *
     * @param theUser user whose rating should be cleared
     * @param theSong song from which the user's rating should be cleared
     * @throws IllegalArgumentException if the user does not currently have a
     * rating on record for the song
     * @throws NullPointerException if either the user or the song is null
     */
    public void clearRating(User theUser, Song theSong)
    throws IllegalArgumentException, NullPointerException;

    /**
     * Predicts whether a user will like or dislike a song (that they have not
     * yet rated).
     */
    void predictRating();

    /**
     * Suggests a song for a user that they are predicted to like.
     */
    void suggestSong();

}
