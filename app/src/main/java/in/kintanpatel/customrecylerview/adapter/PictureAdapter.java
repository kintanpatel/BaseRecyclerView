package in.kintanpatel.customrecylerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.kintanpatel.baserecyclerview.BaseRecyclerViewAdapter;
import in.kintanpatel.customrecylerview.R;
import in.kintanpatel.customrecylerview.model.ImageDetail;

/**
 * Created by sai on 26/09/2017.
 */

public class PictureAdapter extends BaseRecyclerViewAdapter<ImageDetail, PictureAdapter.VH> {

    private Context mContext;

    @Nullable
    @Override
    public Object getItemId(@NonNull ImageDetail item) {
        return null;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new VH(LayoutInflater.from(mContext).inflate(R.layout.lay_picture_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindView(holder.getAdapterPosition());
    }


    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.img_picture)
        ImageView imgPicture;
        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;
        @BindView(R.id.tv_likes)
        TextView tvLikes;
        @BindView(R.id.picture_detail_layout)
        LinearLayout pictureDetailLayout;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        private void bindView(int position) {


            Glide.with(mContext)
                    .load(getItem(position).getUrls().getSmall())
                    .apply(new RequestOptions().centerCrop())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            imgPicture.setImageDrawable(resource);

                            BitmapDrawable drawable = (BitmapDrawable) imgPicture.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();

                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(@NonNull Palette palette) {
                                    Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                                    Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                                    Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                                    if (darkVibrantSwatch == null) {
                                        if (vibrantSwatch != null)
                                            pictureDetailLayout.setBackgroundColor(vibrantSwatch.getRgb());
                                    }
                                    if (darkVibrantSwatch != null) {
                                        pictureDetailLayout.setBackgroundColor(darkVibrantSwatch.getRgb());
                                    }
                                }
                            });
                        }
                    });
            tvAuthorName.setText(getItem(position).getUser().getName());
            tvLikes.setText(String.valueOf(getItem(position).getLikes()).concat(" Likes"));
        }
    }
}
