// Generated code from Butter Knife. Do not modify!
package in.kintanpatel.customrecylerview.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import in.kintanpatel.customrecylerview.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PictureAdapter$VH_ViewBinding implements Unbinder {
  private PictureAdapter.VH target;

  @UiThread
  public PictureAdapter$VH_ViewBinding(PictureAdapter.VH target, View source) {
    this.target = target;

    target.imgPicture = Utils.findRequiredViewAsType(source, R.id.img_picture, "field 'imgPicture'", ImageView.class);
    target.tvAuthorName = Utils.findRequiredViewAsType(source, R.id.tv_author_name, "field 'tvAuthorName'", TextView.class);
    target.tvLikes = Utils.findRequiredViewAsType(source, R.id.tv_likes, "field 'tvLikes'", TextView.class);
    target.pictureDetailLayout = Utils.findRequiredViewAsType(source, R.id.picture_detail_layout, "field 'pictureDetailLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PictureAdapter.VH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgPicture = null;
    target.tvAuthorName = null;
    target.tvLikes = null;
    target.pictureDetailLayout = null;
  }
}
