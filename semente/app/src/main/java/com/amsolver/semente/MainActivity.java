package com.amsolver.semente;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.amsolver.semente.databinding.ActivityMainBinding;
import com.amsolver.semente.dao.DbHelper;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createOrOpenLocalDatabase();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment)).getNavController();
        setupSmoothBottomMenu();
    }

    public void createOrOpenLocalDatabase() {
        if (!checkDataBase()) {
            DbHelper dbHelper = new DbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String pathDB = getDatabasePath("semente.sqlite").toString();
            checkDB = SQLiteDatabase.openDatabase(pathDB, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteCantOpenDatabaseException e) {
            Log.e("Error", "No existe la base de datos " + e.getMessage());
        }
        return checkDB != null;
    }

    private void setupSmoothBottomMenu() {
        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.menu_bottom);
        Menu menu = popupMenu.getMenu();
        binding.bottomBar.setupWithNavController(menu, navController);
        binding.bottomBar.setVisibility(View.VISIBLE);

    }


}