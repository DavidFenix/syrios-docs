package br.com.syrios.mobile.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.syrios.mobile.data.session.SessionManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String TAG = "ApiClient";

    // TODO: ajustar para a URL real da API Syrios
    //private static final String BASE_URL = "https://escolafcm.rf.gd/syriosia/public/api/";
    //private static final String BASE_URL = "http://localhost/syriosia/public/api/";
    private static final String BASE_URL = "http://10.0.2.2/syriosia/public/api/";
    /*
    "http://192.168.0.10/syriosia/public/api/"
    * http://10.0.2.2:8000/api/
    * http://192.168.0.10:8000/api/
    Onde está rodando o backend?	Onde está rodando o app?	URL que deve usar
    Laravel local (localhost)	    Emulador Android	        http://10.0.2.2:8000/api/
    Laravel local (localhost)	    Celular físico	            http://SEU_IP_LOCAL:8000/api/
        Laravel local via Apache/XAMPP	Emulador	                http://10.0.2.2/seuprojeto/public/api/
    Laravel local via Apache/XAMPP	Celular físico	            http://SEU_IP_LOCAL/seuprojeto/public/api/
    */

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(SessionManager sessionManager) {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                if (retrofit == null) {

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message ->
                            Log.d(TAG, message)
                    );
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                    Interceptor authInterceptor = chain -> {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();

                        String token = sessionManager.getToken();
                        if (token != null) {
                            builder.header("Authorization", "Bearer " + token);
                        }

                        return chain.proceed(builder.build());
                    };

                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .addInterceptor(authInterceptor)
                            .build();

                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                            .create();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                }
            }
        }
        return retrofit;
    }
}
