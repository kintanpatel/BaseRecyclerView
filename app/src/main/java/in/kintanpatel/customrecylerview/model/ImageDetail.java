package in.kintanpatel.customrecylerview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sai on 26/09/2017.
 */

public class ImageDetail {


    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("current_user_collections")
    private List<String> currentUserCollections;
    @Expose
    @SerializedName("liked_by_user")
    private boolean likedByUser;
    @Expose
    @SerializedName("likes")
    private int likes;
    @Expose
    @SerializedName("sponsored")
    private boolean sponsored;
    @Expose
    @SerializedName("categories")
    private List<String> categories;
    @Expose
    @SerializedName("urls")
    private Urls urls;
    @Expose
    @SerializedName("color")
    private String color;
    @Expose
    @SerializedName("height")
    private int height;
    @Expose
    @SerializedName("width")
    private int width;
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    @SerializedName("id")
    private String id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<String> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(boolean sponsored) {
        this.sponsored = sponsored;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class User {
        @Expose
        @SerializedName("total_photos")
        private int totalPhotos;
        @Expose
        @SerializedName("total_likes")
        private int totalLikes;
        @Expose
        @SerializedName("instagram_username")
        private String instagramUsername;
        @Expose
        @SerializedName("total_collections")
        private int totalCollections;
        @Expose
        @SerializedName("location")
        private String location;
        @Expose
        @SerializedName("bio")
        private String bio;
        @Expose
        @SerializedName("portfolio_url")
        private String portfolioUrl;
        @Expose
        @SerializedName("twitter_username")
        private String twitterUsername;
        @Expose
        @SerializedName("last_name")
        private String lastName;
        @Expose
        @SerializedName("first_name")
        private String firstName;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("username")
        private String username;
        @Expose
        @SerializedName("updated_at")
        private String updatedAt;
        @Expose
        @SerializedName("id")
        private String id;

        public int getTotalPhotos() {
            return totalPhotos;
        }

        public void setTotalPhotos(int totalPhotos) {
            this.totalPhotos = totalPhotos;
        }

        public int getTotalLikes() {
            return totalLikes;
        }

        public void setTotalLikes(int totalLikes) {
            this.totalLikes = totalLikes;
        }

        public String getInstagramUsername() {
            return instagramUsername;
        }

        public void setInstagramUsername(String instagramUsername) {
            this.instagramUsername = instagramUsername;
        }

        public int getTotalCollections() {
            return totalCollections;
        }

        public void setTotalCollections(int totalCollections) {
            this.totalCollections = totalCollections;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getPortfolioUrl() {
            return portfolioUrl;
        }

        public void setPortfolioUrl(String portfolioUrl) {
            this.portfolioUrl = portfolioUrl;
        }

        public String getTwitterUsername() {
            return twitterUsername;
        }

        public void setTwitterUsername(String twitterUsername) {
            this.twitterUsername = twitterUsername;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Urls {
        @Expose
        @SerializedName("thumb")
        private String thumb;
        @Expose
        @SerializedName("small")
        private String small;
        @Expose
        @SerializedName("regular")
        private String regular;
        @Expose
        @SerializedName("full")
        private String full;
        @Expose
        @SerializedName("raw")
        private String raw;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }
    }
}
