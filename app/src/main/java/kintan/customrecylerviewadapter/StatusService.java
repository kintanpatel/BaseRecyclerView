package kintan.customrecylerviewadapter;

import java.util.ArrayList;

import io.reactivex.Single;
import kintan.customrecylerviewadapter.model.StudentBean;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by kintan on 25/1/18.
 */

public interface StatusService {
    //latest/latest.json

    @GET
    Single<ArrayList<StudentBean>> getStatusCommandTest(@Url String UserId);
}
