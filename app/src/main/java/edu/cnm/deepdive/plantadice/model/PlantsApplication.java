package edu.cnm.deepdive.plantadice.model;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.plantadice.model.service.PlantsDatabase;
import io.reactivex.schedulers.Schedulers;

public class PlantsApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
//    GoogleSignInService.setContext(this);
    PlantsDatabase.setContext(this);
    PlantsDatabase database = PlantsDatabase.getInstance();
    database.getPlantDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    Stetho.initializeWithDefaults(this);
  }
}