package com.examples.ejemplo_navdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BMIAdapter extends RecyclerView.Adapter<BMIAdapter.ViewHolder> {
    private List<BmiItem> bmiList; // Aqui lo que puse fue la clase que tengo sobre mi ITEM

    // Constructor que recibe la lista de objetos BmiItem
    public BMIAdapter(List<BmiItem> bmiList) {
        this.bmiList = bmiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout personalizado para cada item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bmi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtén el objeto BmiItem en la posición actual
        BmiItem bmiItem = bmiList.get(position);
        // Establece los textos en los TextViews
        holder.bmiValue.setText("BMI: " + bmiItem.getValue());
        holder.bmiCategory.setText("Categoría: " + bmiItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return bmiList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bmiValue; // TextView para el valor del BMI
        TextView bmiCategory; // TextView para la categoría del BMI

        ViewHolder(View itemView) {
            super(itemView);
            // Inicializa los TextViews con los IDs del layout personalizado
            bmiValue = itemView.findViewById(R.id.bmi_value);
            bmiCategory = itemView.findViewById(R.id.bmi_category);
        }
    }
}
