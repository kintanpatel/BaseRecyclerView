package in.kintanpatel.baserecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;


/**
 * Created by kintan on 27/4/18.
 */

public class BaseRecyclerView extends RecyclerView {
    public static int STATE_LOADING = 0;
    public static int STATE_DONE = 1;
    private int state = 0;
    private ViewGroup mEmptyView;
    final AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            checkEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkEmptyView();
        }
    };
    private int idView;

    public BaseRecyclerView(Context context) {
        super(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void checkEmptyView() {
        if (mEmptyView != null) {
            if (getAdapter() == null || getAdapter().getItemCount() == 0) {
                mEmptyView.setVisibility(VISIBLE);
            } else {
                mEmptyView.setVisibility(GONE);
            }

            if (getAdapter() == null || getAdapter().getItemCount() == 0) {
                BaseRecyclerView.this.setVisibility(GONE);
            } else {
                BaseRecyclerView.this.setVisibility(VISIBLE);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Adapter oldAdapter = getAdapter();
        super.setAdapter(adapter);
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
    }

    //programatically set empty view
    public void setEmptyView(ViewGroup view) {
        this.mEmptyView = view;
        checkEmptyView();
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.BaseRecyclerView,
                    0, 0);
            try {//set empty view from xml
                idView = a.getResourceId(R.styleable.BaseRecyclerView_emptyViewId, 0);
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (idView != 0) {
            mEmptyView = ((ViewGroup) this.getParent()).findViewById(idView);
        }
    }

    public void setState(int state) {
        this.state = state;
        checkEmptyView();
    }

}