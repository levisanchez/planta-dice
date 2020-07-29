package edu.cnm.deepdive.plantadice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.plantadice.BuildConfig;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * The interface Weather service.
 */
public interface WeatherService {

  /**
   * Gets weather.
   *
   * @param zipCode the zip code
   * @param appId   the app id
   * @param appKey  the app key
   * @return the weather
   */
  @GET("current/us.{zipCode}")
  Single<Weather> getWeather(
      @Path("zipCode") String zipCode,
      @Query("app_id") String appId, @Query("app_key") String appKey);

//  @GET("forecast/us.{zipCode}")
//  Single<Weather.SearchResult> getSearchResult(
//      @Path("zipCode") String zipCode,
//      @Query("app_id") String appId, @Query("app_key") String appKey);


  /**
   * Gets instance.
   *
   * @return the instance
   */
  static WeatherService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * The type Instance holder.
   */
  class InstanceHolder {

    private static final WeatherService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.BASIC);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WeatherService.class);
    }

  }


}


