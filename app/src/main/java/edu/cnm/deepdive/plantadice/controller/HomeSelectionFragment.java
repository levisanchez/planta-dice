package edu.cnm.deepdive.plantadice.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.plantadice.R;


/**
 * The type HomeSelection fragment is currently a place holder a future integration
 * 'Adding Plant' functionality.
 */
public class HomeSelectionFragment extends Fragment {

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

//    String myArg = HomeSelectionFragmentArgs.fromBundle(getArguments()).getMyArg();
//    TextView textView = view.findViewById(R.id.text_dashboard);
//    textView.setText(getString(R.string.hello_home_second, myArg));

//    view.findViewById(R.id.add_plant_button).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        NavHostFragment.findNavController(HomeSelectionFragment.this)
//            .navigate(R.id.action_HomeSecondFragment_to_HomeFragment);
//      }
//    });
  }
}
