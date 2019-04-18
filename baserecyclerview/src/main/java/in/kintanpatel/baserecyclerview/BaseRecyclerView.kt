package `in`.kintanpatel.baserecyclerview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup


/**
 * Created by kintan on 27/4/18.
 */

class BaseRecyclerView : RecyclerView {
    private var mEmptyView: ViewGroup? = null
    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkEmptyView()
        }
    }
    private var idView: Int = 0


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs)
    }

    private fun checkEmptyView() {
        if (mEmptyView != null) {
            if (adapter == null || adapter?.itemCount == 0) {
                mEmptyView!!.visibility = View.VISIBLE
            } else {
                mEmptyView!!.visibility = View.GONE
            }

            if (adapter == null || adapter?.itemCount == 0) {
                this@BaseRecyclerView.visibility = View.GONE
            } else {
                this@BaseRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)
        oldAdapter?.unregisterAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
    }

    //Programmatically set empty view
    fun setEmptyView(view: ViewGroup) {
        this.mEmptyView = view
        checkEmptyView()
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.BaseRecyclerView,
                    0, 0)
            try {//set empty view from xml
                idView = a.getResourceId(R.styleable.BaseRecyclerView_emptyViewId, 0)
            } finally {
                a.recycle()
            }
        }
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (idView != 0) {
            mEmptyView = (this.parent as ViewGroup).findViewById(idView)
        }
    }


}