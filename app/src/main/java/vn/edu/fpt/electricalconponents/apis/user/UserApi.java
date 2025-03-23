package vn.edu.fpt.electricalconponents.apis.user;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.apis.user.dto.UpdateUserRequest;
import vn.edu.fpt.electricalconponents.models.User;

public interface UserApi {
    @GET
    Observable<ApiListResponse<User>> getAllUsers(
            @Query("page") int page,
            @Query("size") int size,
            @Query("keyword") String keyword);

    @GET("{id}")
    Observable<ApiItemResponse<User>> getOneUser(@Path("id") String id);

    @PUT("{if}")
    Observable<ApiItemResponse<User>> updateUser(@Path("id") String id, UpdateUserRequest user);

    @DELETE("{id}")
    Observable<ApiItemResponse<User>> deleteUser(@Path("id") String id);
}
