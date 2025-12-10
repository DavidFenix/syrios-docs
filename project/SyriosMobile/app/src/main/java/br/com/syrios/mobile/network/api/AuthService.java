package br.com.syrios.mobile.network.api;

import br.com.syrios.mobile.network.dto.LoginRequest;
import br.com.syrios.mobile.network.dto.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    // TODO: ajustar endpoint conforme você criar no Laravel
    @POST("mobile/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
