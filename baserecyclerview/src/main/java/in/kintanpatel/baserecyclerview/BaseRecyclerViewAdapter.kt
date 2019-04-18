package `in`.kintanpatel.baserecyclerview

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import uffizio.trakzee.common.inflate
import uffizio.trakzee.common.singleClick


/**
 * @param T model that will represent in ArrayList
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseRecyclerAdapter.MyViewHolder>(), Filterable {
    private var mObject: ArrayList<T> = ArrayList()
    private var mCopyObjects: ArrayList<T> = ArrayList()
    private var filterConsumer: ArrayList<FilterConsumer<T>> = ArrayList()
    private var mListener: RecyclerClick<T>? = null
    private var mSearching = ""
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        mContext = parent.context
        return MyViewHolder(parent.inflate(getContentLayoutResId()))
    }

    override fun getItemCount() = mObject.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        populateItem(holder.itemView, getItemAtPosition(position), position)
        holder.itemView.singleClick { mListener?.onItemClick(position, getItemAtPosition(position)) }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {

                mSearching = charSequence.toString()
                var list: MutableList<T> = ArrayList()

                val results = FilterResults()
                if (charSequence.toString().trim().isEmpty()) {
                    list = ArrayList(mCopyObjects)
                } else {
                    outerLoop@ for (item in mCopyObjects) {
                        for (fConsume in filterConsumer) {
                            if (fConsume.apply(item).contains(charSequence.toString(), true)) {
                                list.add(item)
                                continue@outerLoop
                            }
                        }
                    }
                }
                results.count = list.size
                results.values = list
                return results
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mObject = filterResults.values as ArrayList<T>
                notifyDataSetChanged()
            }
        }
    }

    /**
     * Layout ID use for create ViewHolder
     */
    @LayoutRes
    abstract fun getContentLayoutResId(): Int

    /**
     * @param itemView holder view which is inflate by you
     * @param item which was passed in T
     * @param position adapter position
     */
    abstract fun populateItem(itemView: View, item: T, position: Int)


    fun setOnItemClickListener(listener: RecyclerClick<T>) {
        mListener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * @param list add multiple item in list
     */
    fun addAll(@NonNull list: ArrayList<T>) {
        mObject = list
        mCopyObjects = ArrayList(list)
        notifyDataSetChanged()
    }

    /**
     * @param item add single  item in list
     */
    fun add(@NonNull item: T) {
        mObject.add(item)
        mCopyObjects.add(item)
        notifyDataSetChanged()
    }

    /**
     * Returns the item at the specified position.
     *
     * @param position index of the item to return
     * @return the item at the specified position or {@code null} when not found
     */
     fun getItemAtPosition(position: Int) = mObject[position]

    /**
     * Clear all data
     */
    fun clear() {
        mObject.clear()
        mCopyObjects.clear()
        notifyDataSetChanged()
    }

    /**
     * Register a Filter to be invoked when an item in this AdapterView has
     * Search
     *
     * @param filterConsumer list of search item
     */
    fun setFilterConsumer(vararg filterConsumer: FilterConsumer<T>) {
        this.filterConsumer.addAll(filterConsumer)
    }

    fun getContext(): Context {
        return mContext!!
    }

    fun getAll(): ArrayList<T> {
        return mObject
    }

    interface RecyclerClick<T> {
        fun onItemClick(position: Int, item: T)

    }

    interface FilterConsumer<T> {
        fun apply(item: T): String
    }
}
