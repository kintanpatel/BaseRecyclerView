package in.kintanpatel.customrecylerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import in.kintanpatel.baserecyclerview.BaseRecyclerView;
import in.kintanpatel.baserecyclerview.BaseRecyclerViewAdapter;
import in.kintanpatel.customrecylerview.adapter.StudentAdapter;
import in.kintanpatel.customrecylerview.model.StudentBean;
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
    private StudentAdapter adapter;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new StudentAdapter();
        BaseRecyclerView rvStudent = findViewById(R.id.rv_student);
        EditText edSearch = findViewById(R.id.ed_search);
        rvStudent.setLayoutManager(new GridLayoutManager(this, 2));
        rvStudent.setItemAnimator(new DefaultItemAnimator());
        rvStudent.setAdapter(adapter);

      /*  ArrayList<StudentBean> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add(new StudentBean("K" + i));
        }
        adapter.addAll(arrayList);*/

        adapter.setFilterConsumer((BaseRecyclerViewAdapter.FilterConsumer<StudentBean>) StudentBean::getStudentName);

        getData();

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void getData() {
        getInstance().create(StatusService.class).getStatusCommandTest("latest/latest.json")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<StudentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<StudentBean> studentBeans) {
                        Log.e("e", " " + studentBeans);
                        adapter.addAll(studentBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("e", "e");
                    }
                });
    }

    private Retrofit getInstance() {
        //String BASE_URL = "http://103.249.120.62:2131";
        String BASE_URL = "http://appdevbuild.com/json/";

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
