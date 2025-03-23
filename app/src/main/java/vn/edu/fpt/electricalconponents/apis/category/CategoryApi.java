package vn.edu.fpt.electricalconponents.apis.category;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.models.Category;

public interface CategoryApi {
    @GET
    Observable<ApiListResponse<Category>> getAllCategories();
}
