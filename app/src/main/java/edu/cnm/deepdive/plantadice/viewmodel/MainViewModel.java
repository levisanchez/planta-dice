package edu.cnm.deepdive.plantadice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import edu.cnm.deepdive.plantadice.service.WeatherRepository;
import io.reactivex.disposables.CompositeDisposable;

/**
 * The type Main view model.
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final WeatherRepository weatherRepository;
  private final MutableLiveData<Weather> weather;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   * Instantiates a new Main view model.
   *
   * @param application the application
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    weatherRepository = new WeatherRepository(application);
    weather = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  /**
   * Gets weather.
   *
   * @return the weather
   */
  public LiveData<Weather> getWeather() {
    return weather;
  }

  /**
   * Gets throwable.
   *
   * @return the throwable
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Update weather.
   *
   * @param zipCode the zip code
   */
  public void updateWeather(int zipCode) {
    pending.add(
        weatherRepository.getCurrent(zipCode)
            .subscribe(
                (weather) -> this.weather.postValue(weather),
                (throwable) -> this.throwable.postValue(throwable)
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending(){
    pending.clear();
  }
}
