package br.com.syrios.mobile.data.api;

import br.com.syrios.mobile.App;
import br.com.syrios.mobile.data.session.SessionManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //private static final String BASE_URL = "https://seu-syrios.com/"; // depois alteramos para o real
    //private static final String BASE_URL = "https://escolafcm.rf.gd/syriosia/public/api/";
    private static final String BASE_URL = "http://10.0.2.2/syriosia/public/api/";

    private static Retrofit retrofit = null;

    public static ApiService getService() {

        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            SessionManager session = new SessionManager(App.getContext());

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {

                        okhttp3.Request original = chain.request();
                        okhttp3.Request.Builder builder = original.newBuilder();

                        // Authorization
                        String token = session.getToken();
                        if (token != null) {
                            builder.header("Authorization", "Bearer " + token);
                        }

                        // School ID
                        Long schoolId = session.getCurrentSchoolId();
                        if (schoolId != null) {
                            builder.header("X-SCHOOL-ID", String.valueOf(schoolId));
                        }

                        // Role
                        String role = session.getCurrentRole();
                        if (role != null) {
                            builder.header("X-ROLE", role);
                        }

                        return chain.proceed(builder.build());
                    })
                    .addInterceptor(logging)
                    .build();

//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(logging)
//                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit.create(ApiService.class);
    }
}
