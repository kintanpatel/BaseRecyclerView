package in.kintanpatel.customrecylerview;

import java.util.ArrayList;

import in.kintanpatel.customrecylerview.model.ImageDetail;
import in.kintanpatel.customrecylerview.util.ApiUrls;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kintan on 25/1/18.
 */

public interface StatusService {

    @GET(ApiUrls.PHOTO_TAG)
    Single<ArrayList<ImageDetail>> getAllImages(@Query(ApiUrls.API_KEY_TAG) String clientId,
                                                @Query(ApiUrls.PER_PAGE_ITEM_OFFSET) int pageOffset);
}
