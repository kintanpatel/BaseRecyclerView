package `in`.kintanpatel.customrecylerview

import `in`.kintanpatel.baserecyclerview.BaseRecyclerAdapter
import `in`.kintanpatel.customrecylerview.adapter.PictureAdapter
import `in`.kintanpatel.customrecylerview.model.ImageDetail
import `in`.kintanpatel.customrecylerview.util.ApiUrls
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class KotlinActivity : AppCompatActivity() {


    private var adapter: PictureAdapter? = null
    private lateinit var retrofit: Retrofit
    private var searchView: SearchView? = null

    companion object {
        private val BASE_URL = ApiUrls.PICTURE_BASE_URL
    }


    private val instance: Retrofit
        get() {

            val gson = GsonBuilder().setLenient().create()

            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(60, TimeUnit.MINUTES)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar.title = getString(R.string.app_name)

        adapter = PictureAdapter(this)

        rvPicture.layoutManager = GridLayoutManager(this, 2)
        rvPicture.itemAnimator = DefaultItemAnimator()
        rvPicture.setHasFixedSize(true)
        rvPicture.adapter = adapter

        setSupportActionBar(toolbar)

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

        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String) = false

            override fun onQueryTextChange(newText: String): Boolean {
                //Text has changed, apply filtering?
                adapter?.filter?.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun getData() {
        instance.create(StatusService::class.java).getAllImages(ApiUrls.API_KEY, 30)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArrayList<ImageDetail>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(imageDetails: ArrayList<ImageDetail>) {
                        Log.e("e", " $imageDetails")
                        adapter?.addAll(imageDetails)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("e", "e")
                    }
                })
    }


}
