package edu.cnm.deepdive.plantadice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The type Notifications view model.
 */
public class NotificationsViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  /**
   * Instantiates a new Notifications view model.
   */
  public NotificationsViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is notifications fragment");
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