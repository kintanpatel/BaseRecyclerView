package `in`.kintanpatel.customrecylerview.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sai on 26/09/2017.
 */

class ImageDetail {
    @Expose
    @SerializedName("user")
    var user: User? = null
    @Expose
    @SerializedName("current_user_collections")
    var currentUserCollections: List<String>? = null
    @Expose
    @SerializedName("liked_by_user")
    var likedByUser: Boolean = false
    @Expose
    @SerializedName("likes")
    var likes: Int = 0
    @Expose
    @SerializedName("sponsored")
    var sponsored: Boolean = false
    @Expose
    @SerializedName("categories")
    var categories: List<String>? = null
    @Expose
    @SerializedName("urls")
    var urls: Urls? = null
    @Expose
    @SerializedName("color")
    var color: String? = null
    @Expose
    @SerializedName("height")
    var height: Int = 0
    @Expose
    @SerializedName("width")
    var width: Int = 0
    @Expose
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @Expose
    @SerializedName("created_at")
    var createdAt: String? = null
    @Expose
    @SerializedName("id")
    var id: String? = null

    class User {
        @Expose
        @SerializedName("total_photos")
        var totalPhotos: Int = 0
        @Expose
        @SerializedName("total_likes")
        var totalLikes: Int = 0
        @Expose
        @SerializedName("instagram_username")
        var instagramUsername: String? = null
        @Expose
        @SerializedName("total_collections")
        var totalCollections: Int = 0
        @Expose
        @SerializedName("location")
        var location: String? = null
        @Expose
        @SerializedName("bio")
        var bio: String? = null
        @Expose
        @SerializedName("portfolio_url")
        var portfolioUrl: String? = null
        @Expose
        @SerializedName("twitter_username")
        var twitterUsername: String? = null
        @Expose
        @SerializedName("last_name")
        var lastName: String? = null
        @Expose
        @SerializedName("first_name")
        var firstName: String? = null
        @Expose
        @SerializedName("name")
        var name: String? = null
        @Expose
        @SerializedName("username")
        var username: String? = null
        @Expose
        @SerializedName("updated_at")
        var updatedAt: String? = null
        @Expose
        @SerializedName("id")
        var id: String? = null
    }

    class Urls {
        @Expose
        @SerializedName("thumb")
        var thumb: String? = null
        @Expose
        @SerializedName("small")
        var small: String? = null
        @Expose
        @SerializedName("regular")
        var regular: String? = null
        @Expose
        @SerializedName("full")
        var full: String? = null
        @Expose
        @SerializedName("raw")
        var raw: String? = null
    }
}
