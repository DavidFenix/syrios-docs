package br.com.syrios.mobile.data.api;

//import br.com.syrios.mobile.data.model.LoginResponse;
import br.com.syrios.mobile.network.dto.AlunoDaOfertaRemote;
import br.com.syrios.mobile.network.dto.LoginResponse;

import java.util.List;
import java.util.Map;

import br.com.syrios.mobile.network.dto.OcorrenciaRemote;
import br.com.syrios.mobile.network.dto.OfertaRemote;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("mobile/login")
    Call<LoginResponse> login(@Body Map<String, String> body);

    @GET("mobile/ofertas")
    Call<List<OfertaRemote>> getOfertas(
            @Header("Authorization") String bearerToken,
            @Query("school_id") Long schoolId,
            @Query("role") String role
    );

    @GET("mobile/ocorrencias")
    Call<List<OcorrenciaRemote>> getOcorrencias(
            @Header("Authorization") String bearerToken,
            @Query("school_id") Long schoolId,
            @Query("role") String role
    );

//    @GET("mobile/oferta/{id}/alunos")
//    Call<List<AlunoRemote>> getAlunosDaOferta(
//            @Header("Authorization") String bearer,
//            @Header("X-SCHOOL-ID") long schoolId,
//            @Header("X-ROLE") String role,
//            @Path("id") long ofertaId
//    );

    @GET("mobile/oferta/{id}/alunos")
    Call<List<AlunoDaOfertaRemote>> getAlunosDaOferta(
            @Header("Authorization") String token,
            @Header("X-SCHOOL-ID") Long schoolId,
            @Header("X-ROLE") String role,
            @Path("id") Long ofertaId
    );




}
