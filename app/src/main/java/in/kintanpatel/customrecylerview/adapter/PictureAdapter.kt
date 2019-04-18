package `in`.kintanpatel.customrecylerview.adapter

import `in`.kintanpatel.baserecyclerview.BaseRecyclerAdapter
import `in`.kintanpatel.customrecylerview.R
import `in`.kintanpatel.customrecylerview.model.ImageDetail
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.lay_picture_detail.view.*

/**
 * Created by sai on 26/09/2017.
 */

class PictureAdapter(private var mContext: Context) : BaseRecyclerAdapter<ImageDetail>() {

    override fun getContentLayoutResId() = R.layout.lay_picture_detail


    override fun populateItem(itemView: View, item: ImageDetail, position: Int) {
        Glide.with(mContext)
                .load(getItemAtPosition(position).urls?.small)
                .apply(RequestOptions().centerCrop())
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        itemView.imgPicture.setImageDrawable(resource)

                        val drawable = itemView.imgPicture.drawable as BitmapDrawable
                        val bitmap = drawable.bitmap

                        Palette.from(bitmap).generate { palette ->
                            val darkVibrantSwatch = palette?.darkVibrantSwatch
                            val lightVibrantSwatch = palette?.lightVibrantSwatch
                            val vibrantSwatch = palette?.vibrantSwatch
                            if (darkVibrantSwatch == null) {
                                if (vibrantSwatch != null)
                                    itemView.pictureDetailLayout.setBackgroundColor(vibrantSwatch.rgb)
                            }
                            if (darkVibrantSwatch != null) {
                                itemView.pictureDetailLayout.setBackgroundColor(darkVibrantSwatch.rgb)
                            }
                        }
                    }
                })
        itemView.tvAuthorName.text = getItemAtPosition(position).user?.name
        itemView.tvLikes.text = (getItemAtPosition(position).likes).toString().plus(" Likes")
    }


}
