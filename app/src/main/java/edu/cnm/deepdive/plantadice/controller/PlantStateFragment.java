package edu.cnm.deepdive.plantadice.controller;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.plantadice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlantStateFragment extends Fragment {

  public PlantStateFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_plant_selection, container, false);
  }
}
