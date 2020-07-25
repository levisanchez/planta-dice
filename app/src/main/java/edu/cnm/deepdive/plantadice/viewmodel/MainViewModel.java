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

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final WeatherRepository weatherRepository;
  private final MutableLiveData<Weather> weather;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public MainViewModel(@NonNull Application application) {
    super(application);
    weatherRepository = new WeatherRepository(application);
    weather = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<Weather> getWeather() {
    return weather;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

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
