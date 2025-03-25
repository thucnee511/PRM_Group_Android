package vn.edu.fpt.electricalconponents.apis.authentication;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.GoogleSignInRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.RefreshRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.SignInRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.SignUpRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.Token;
import vn.edu.fpt.electricalconponents.models.User;

public interface AuthenticationApi {
    @POST("signin")
    Observable<ApiItemResponse<Token>> signIn(@Body SignInRequest request);

    @POST("signup")
    Observable<ApiItemResponse<Token>> signUp(@Body SignUpRequest request);

    @POST("refresh")
    Observable<ApiItemResponse<Token>> refresh(@Body RefreshRequest request);

    @POST("google")
    Observable<ApiItemResponse<Token>> googleSignIn(@Body GoogleSignInRequest request);

    @GET("me")
    Observable<ApiItemResponse<User>> getMe();
}
