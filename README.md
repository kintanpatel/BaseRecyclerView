# BaseRecyclerView

BaseRecyclerView is here to simplify the process. Just write the logic for how your view/item should look like, and you are done. This library has a fast and highly optimized core which provides core functionality, most apps require.Minimizing the code you need to write it has only few files so you can copy that file and use in your application.

## Don't write a boilerplate code of RecyclerView adapter. Not even a ViewHolder!


* Written in Kotlin
* Searching with multiple field
* Recyclerview ItemClick
* No need to write the viewholders
* Optional Callbacks/Listeners
* Empty View Support
* Highly optimized code
* Very Fast

### BaseRecyclerAdapter Methods
**1.Layout ID use for create ViewHolder**
 ```  
@LayoutRes
abstract fun getContentLayoutResId(): Int
```     
**2.ItemView holder view which is inflate by you**
 ```
abstract fun populateItem(itemView: View, item: T, position: Int)
```
**3.Recyclerview Item Click**
```
fun setOnItemClickListener(listener: RecyclerClick<T>)
```
 **4.Add Multiple and Single item in list**
```
fun addAll(@NonNull list: ArrayList<T>)
fun add(@NonNull item: T) 
fun getItemAtPosition(position: Int) = mObject[position]
fun getAll(): ArrayList<T> 
 ```
**5.Set Filter Consumer**
```
fun setFilterConsumer(vararg filterConsumer: FilterConsumer<T>)
 ```
 
 
 ## How to use in Code
 **Add BaseRecylerView and EmptyView in Your XML**
 ```
 <in.kintanpatel.baserecyclerview.BaseRecyclerView
        android:id="@+id/rvPicture"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/toolbar"
        app:emptyViewId="@id/empty_view" />

    <LinearLayout
        android:id="@+id/empty_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:gravity="center"
        android:orientation="vertical">

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/ic_android_black_24dp" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="10dp"
            android:text="@string/no_records"
            android:textColor="#938a8a"
            android:textSize="20sp" />
    </LinearLayout>
 ```
  **Create Adapter and Extend With BaseRecyclerAdapter**
  ```
  class PictureAdapter(private var mContext: Context) : BaseRecyclerAdapter<ImageDetail>() {

    override fun getContentLayoutResId() = R.layout.lay_picture_detail
    
    override fun populateItem(itemView: View, item: ImageDetail, position: Int) {
    //Do your code here
    }
      
}
```
 **Finaly adapter click and Searching assignment**
 ```
   //Apply Searching on Multiple Item of Model
        adapter?.setFilterConsumer(object : BaseRecyclerAdapter.FilterConsumer<ImageDetail> {
            override fun apply(item: ImageDetail) = item.user?.name.toString()
        }, object : BaseRecyclerAdapter.FilterConsumer<ImageDetail> {
            override fun apply(item: ImageDetail) = item.user?.location.toString()
        })
        
         //Item Click Listener
        adapter?.setOnItemClickListener(object : BaseRecyclerAdapter.RecyclerClick<ImageDetail> {
            override fun onItemClick(position: Int, item: ImageDetail) {
                Toast.makeText(applicationContext, item.user?.name, Toast.LENGTH_SHORT).show()
            }
        })   
        
   //Apply Searching string to filter   
            override fun onQueryTextChange(newText: String): Boolean {
                //Text has changed, apply filtering?
                adapter?.filter?.filter(newText)
                return false
            }
        
 ```
 
 
 
 
 
 
   
     
    


 


