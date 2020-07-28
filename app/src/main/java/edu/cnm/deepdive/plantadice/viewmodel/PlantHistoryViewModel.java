package edu.cnm.deepdive.plantadice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import edu.cnm.deepdive.plantadice.service.PlantHistoryRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class PlantHistoryViewModel extends AndroidViewModel implements LifecycleObserver {

  private final PlantHistoryRepository plantHistoryRepository;
  private final MutableLiveData<PlantHistory> history;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;


  public PlantHistoryViewModel(@NonNull Application application) {
    super(application);
    plantHistoryRepository = new PlantHistoryRepository(application);
    history = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();

  }

  public LiveData<PlantHistory> getHistory() {
    return history;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<PlantHistory>> getHistories() {
    return plantHistoryRepository.getAll();
  }

  public void setHistoryId(long id) {
    throwable.setValue(null);
    pending.add(
        plantHistoryRepository.get(id)
            .subscribe(
                (history) -> this.history.postValue(history),
                (throwable) -> this.throwable.postValue(throwable)
            )
    );
  }

  public void save(PlantHistory plantHistory) {
    throwable.setValue(null);
    pending.add(
        plantHistoryRepository.save(plantHistory)
            .subscribe(
                () -> {},
                (throwable) -> this.throwable.postValue(throwable)
            )
    );
  }

  public void delete (PlantHistory plantHistory) {
    throwable.setValue(null);
    pending.add(
        plantHistoryRepository.delete(plantHistory)
            .subscribe(
                () -> {},
                (throwable) -> this.throwable.postValue(throwable)
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
