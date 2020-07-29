package edu.cnm.deepdive.plantadice.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.plantadice.R;
import edu.cnm.deepdive.plantadice.viewmodel.HomeViewModel;

/**
 * The HomeFragment functions as the beginning page of the app.
 * It is also the fragment used when a user clicks the home button
 */
public class HomeFragment extends Fragment implements OnClickListener {

  private HomeViewModel homeViewModel;

  @Override
  public void onClick(View v) {

  }
  //TODO create viewmodel for this to get a list of plants
  //need to observe that from Onviewcreated
  //get new instance of plantsadapter and set adapter of recycler view to that adapter.
  //logic in quotes and imgur

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_plant_selection, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

}
