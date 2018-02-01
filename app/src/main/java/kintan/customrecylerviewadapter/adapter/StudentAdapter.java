package kintan.customrecylerviewadapter.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import kintan.customrecylerviewadapter.R;
import kintan.customrecylerviewadapter.model.StudentBean;
import kintan.customrecylerviewadapter.widget.BaseRecyclerViewAdapter;

/**
 * Created by sai on 26/09/2017.
 */

public class StudentAdapter extends BaseRecyclerViewAdapter<StudentBean, StudentAdapter.VH> {

    private Context mContext;

    @Nullable
    @Override
    public Object getItemId(@NonNull StudentBean item) {
        return null;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new VH(LayoutInflater.from(mContext).inflate(R.layout.lay_stud, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        //Glide.with(mContext).load("https://api.androidhive.info/images/glide/medium/deadpool.jpg").into(holder.imageView);
        //Glide.with(mContext).load(getItem(position).getUrl()).into(holder.imageView);
        String filePath = "/storage/emulated/0/Pictures/example_video.mp4";

        Glide.with(mContext)
                .asBitmap()
                .load(getItem(position).getUrl())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.imageView);


    }

    static class VH extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
