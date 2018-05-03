package in.kintanpatel.customrecylerview;

import java.util.ArrayList;

import in.kintanpatel.customrecylerview.model.StudentBean;
import io.reactivex.Single;
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
