package edu.cnm.deepdive.plantadice.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.plantadice.R;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.view.PlantsAdapter.Holder;
import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<? extends Plant> plants;
  private final OnClickListener listener;

  public PlantsAdapter(Context context,
      List<? extends Plant> plants,
      OnClickListener listener) {
    this.context = context;
    this.plants = plants;
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    TextView wateringFrequency = parent.findViewById(R.id.water_frequency);
    TextView outdoor = parent.findViewById(R.id.location_outdoor);
    TextView zipCode = parent.findViewById(R.id.location_zip_code);
    View view = LayoutInflater.from(context).inflate(R.layout.item_plant, parent, false);
    return new Holder(view, wateringFrequency, outdoor, zipCode);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return plants.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final TextView name;
    public final TextView wateringFrequency;
    public final TextView outdoor;
    public final TextView zipCode;

    public Holder(@NonNull View itemView, TextView wateringFrequency,
        TextView outdoor, TextView zipCode) {
      super(itemView);
      name = itemView.findViewById(R.id.name);
      this.wateringFrequency = wateringFrequency;
      this.outdoor = outdoor;
      this.zipCode = zipCode;

    }

    private void bind(int position) {
      Plant plant = plants.get(position);
      name.setText(plant.getName());
      wateringFrequency.setText(plant.getWaterFrequencyDays());
      //ou;
      zipCode.setText(plant.getZipCode());
      itemView.setOnClickListener((v) -> listener.onClick(v, position, plant));
    }

  }

  @FunctionalInterface
  public interface OnClickListener {

    void onClick(View view, int position, Plant plant);
  }

}
