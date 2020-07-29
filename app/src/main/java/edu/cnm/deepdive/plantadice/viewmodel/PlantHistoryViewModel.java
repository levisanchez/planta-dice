package edu.cnm.deepdive.plantadice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The type Plant history view model.
 */
public class PlantHistoryViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  /**
   * Instantiates a new Plant history view model.
   */
  public PlantHistoryViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is dashboard fragment");
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  public LiveData<String> getText() {
    return mText;
  }
}