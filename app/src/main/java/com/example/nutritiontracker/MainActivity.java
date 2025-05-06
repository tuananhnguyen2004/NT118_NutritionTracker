package com.example.nutritiontracker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracker.adapters.FoodAdapter;
import com.example.nutritiontracker.models.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodItemClickListener {

    private RecyclerView foodListRecyclerView;
    private FoodAdapter foodAdapter;
    private TextView tabRecent;
    private TextView tabFavorites;
    private ConstraintLayout selectionContainer;
    private TextView kcalValue;
    private Button btnAddSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        foodListRecyclerView = findViewById(R.id.food_list);
        tabRecent = findViewById(R.id.tab_recent);
        tabFavorites = findViewById(R.id.tab_favorites);
        selectionContainer = findViewById(R.id.selection_container);
        kcalValue = findViewById(R.id.kcal_value);
        btnAddSelection = findViewById(R.id.btn_add_selection);

        // Set up tab click listeners
        tabRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTab(tabRecent);
            }
        });

        tabFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTab(tabFavorites);
            }
        });

        // Set up RecyclerView
        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Example food data (sushi items based on the screenshot)
        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Sushi Roll", 250, 180));
        foodItems.add(new FoodItem("Sushi, Tuna (Maguro)", 50, 28));
        foodItems.add(new FoodItem("Sushi, Salmon (Sake)", 48, 30));
        foodItems.add(new FoodItem("Sushi, Yellowtail (Hamachi)", 65, 30));
        foodItems.add(new FoodItem("Sushi, Eel (Unagi)", 100, 35));

        // Set up adapter
        foodAdapter = new FoodAdapter(foodItems, this);
        foodListRecyclerView.setAdapter(foodAdapter);

        // Set click listener for Add button
        btnAddSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement add functionality
                // For example: add to meal plan, history, etc.

                // Reset selection and hide container
                foodAdapter.setSelectedPosition(-1);
                selectionContainer.setVisibility(View.GONE);
            }
        });
    }

    private void setActiveTab(TextView activeTab) {
        // Reset all tabs
        tabRecent.setBackground(null);
        tabRecent.setTextColor(getResources().getColor(android.R.color.darker_gray));

        tabFavorites.setBackground(null);
        tabFavorites.setTextColor(getResources().getColor(android.R.color.darker_gray));

        // Set active tab
        activeTab.setBackground(getResources().getDrawable(R.drawable.tab_background_selected));
        activeTab.setTextColor(getResources().getColor(android.R.color.black));
    }

    @Override
    public void onFoodItemClick(FoodItem item, int position) {
        // Update adapter's selected position
        foodAdapter.setSelectedPosition(position);

        // Update bottom selection info
        kcalValue.setText(String.valueOf(item.getCalories()));

        // Show selection container
        selectionContainer.setVisibility(View.VISIBLE);

        // Update the add button text to show count
        btnAddSelection.setText("Add (1)");
    }
}