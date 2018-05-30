package in.kintanpatel.customrecylerview;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.kintanpatel.baserecyclerview.BaseRecyclerView;
import in.kintanpatel.baserecyclerview.BaseRecyclerViewAdapter;
import in.kintanpatel.customrecylerview.adapter.PictureAdapter;
import in.kintanpatel.customrecylerview.model.ImageDetail;
import in.kintanpatel.customrecylerview.util.ApiUrls;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static Gson gson;
    @BindView(R.id.rv_picture)
    BaseRecyclerView rvPicture;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    private PictureAdapter adapter;
    private Retrofit retrofit;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(getString(R.string.app_name));

        adapter = new PictureAdapter();
        //EditText edSearch = findViewById(R.id.ed_search);
        rvPicture.setLayoutManager(new GridLayoutManager(this, 2));
        rvPicture.setItemAnimator(new DefaultItemAnimator());
        rvPicture.setHasFixedSize(true);
        rvPicture.setAdapter(adapter);

        setSupportActionBar(toolbar);

        adapter.setFilterConsumer((BaseRecyclerViewAdapter.FilterConsumer<ImageDetail>) (ImageDetail imageDetail) -> {
            return imageDetail.getUser().getName();
        });
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(),adapter.getItemAtPosition(position).getUser().getName(),Toast.LENGTH_SHORT).show();
            }
        });
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Perform the final search

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Text has changed, apply filtering?
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        getInstance().create(StatusService.class).getAllImages(ApiUrls.API_KEY, 30)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<ImageDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<ImageDetail> imageDetails) {
                        Log.e("e", " " + imageDetails);
                        adapter.addAll(imageDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("e", "e");
                    }
                });
    }

    private Retrofit getInstance() {
        //String BASE_URL = "http://103.249.120.62:2131";
        String BASE_URL = ApiUrls.PICTURE_BASE_URL;

        if (retrofit == null) {
            if (gson == null) {
                gson = new GsonBuilder().setLenient().create();
            }
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(60, TimeUnit.MINUTES);
            httpClient.connectTimeout(60, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                httpClient.addInterceptor(logging);
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return retrofit;
    }
}
