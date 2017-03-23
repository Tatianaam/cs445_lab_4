package cs445.a1;

public class Profile implements ProfileInterface{
    private String mName, mAboutMe;
    private SetInterface<ProfileInterface> mFollowing;

    public Profile() {
        this("","");
    }
    public Profile(String name, String about) {
        if (name == null)
            name = "";
        if (about == null)
            about = "";
        mName = name;
        mAboutMe = about;
        mFollowing = new Set<>();
    }

    @Override
    public void setName(String newName) throws IllegalArgumentException {
        if (newName == null)
            throw new IllegalArgumentException("Name cannot be null");
        mName = newName;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setAbout(String newAbout) throws IllegalArgumentException {
        if (newAbout == null)
            throw new IllegalArgumentException("AboutMe cannot be null");
        mAboutMe = newAbout;
    }

    @Override
    public String getAbout() {
        return mAboutMe;
    }

    @Override
    public boolean follow(ProfileInterface other) {
        boolean isFollowed = false;
        // Check for "set is at capacity" is not needed, because it will resize if full
        if (other == null)
            return isFollowed;
        else {
            try {
                mFollowing.add(other);
                isFollowed = true;
            } catch (SetFullException e) {
                e.printStackTrace();
            }
        }
        return isFollowed;
    }

    @Override
    public boolean unfollow(ProfileInterface other) {
        if (other == null)
            return false;
        else if (!mFollowing.contains(other))
            return false;
        else {
            mFollowing.remove(other);
            return true;
        }

    }

    @Override
    public ProfileInterface[] following(int howMany) {
        Object[] temp = mFollowing.toArray();
        ProfileInterface[] result;
        if (mFollowing.getCurrentSize() <= howMany) {
            result = new Profile[mFollowing.getCurrentSize()];
            for (int i = 0; i<mFollowing.getCurrentSize(); i++) {
                result[i] = (ProfileInterface) temp[i];
            }
        } else {
            result = new Profile[howMany];
            for (int i = 0; i < howMany; i++) {
                result[i] = (ProfileInterface) temp[i];
            }
        }
        return result;
    }

    @Override
    public ProfileInterface recommend() {
        Profile[] friends = (Profile[]) following(mFollowing.getCurrentSize());
        for (Profile friend : friends) {
            for (ProfileInterface friendOfFriend : friend.following(friend.mFollowing.getCurrentSize())) {
                if (!mFollowing.contains(friendOfFriend)) {
                    return friendOfFriend;
                }
            }
        }
        return null;
    }
}
