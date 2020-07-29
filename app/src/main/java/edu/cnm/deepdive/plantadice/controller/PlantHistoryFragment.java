package edu.cnm.deepdive.plantadice.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.plantadice.R;
import edu.cnm.deepdive.plantadice.viewmodel.PlantHistoryViewModel;

public class PlantHistoryFragment extends Fragment {

  private PlantHistoryViewModel plantHistoryViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    plantHistoryViewModel =
        ViewModelProviders.of(this).get(PlantHistoryViewModel.class);
    View root = inflater.inflate(R.layout.fragment_history, container, false);
    final TextView textView = root.findViewById(R.id.text_dashboard);
    plantHistoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}
