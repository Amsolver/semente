package com.amsolver.semente.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amsolver.semente.R;
import com.amsolver.semente.adapters.SelectEmocionsLevelAdapter;
import com.amsolver.semente.callbacks.SelectEmocionsLevelCallback;
import com.amsolver.semente.dao.implementations.EmocionDaoImp;
import com.amsolver.semente.game_activities.EmocionsActivity;
import com.amsolver.semente.model.Emocion;

import java.util.ArrayList;
import java.util.List;

public class SelectEmocionsLevelActivity extends AppCompatActivity {

    ImageView ivBack;
    ImageView ivArrowLeft;
    ImageView ivArrowRight;
    RecyclerView rvLevels;
    List<Emocion> emocions = new ArrayList<>();
    SelectEmocionsLevelAdapter adapter;

    public int page = 0;

    SelectEmocionsLevelActivity activity;

    SelectEmocionsLevelCallback selectEmocionsLevelCallback;

    EmocionDaoImp emocionDaoImp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDaos();
        loadViews();
        activity = this;
        loadLevels();
        loadListeners();
        loadEvents();
        initRecycler();
        updateArrowsVisibility();

    }

    public void loadDaos() {
        emocionDaoImp = new EmocionDaoImp();
    }

    public void loadViews() {
        setContentView(R.layout.activity_select_wordle_level_layout);
        ivBack = findViewById(R.id.ivBack);
        ivArrowLeft = findViewById(R.id.ivArrowLeft);
        ivArrowRight = findViewById(R.id.ivArrowRight);
        rvLevels = findViewById(R.id.rvLevels);
    }

    public void loadLevels() {
        new SelectLevelLocalPresenter(SelectEmocionsLevelActivity.this, page).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void loadListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                new SelectLevelLocalPresenter(getApplicationContext(), page).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                updateArrowsVisibility();
            }
        });

        ivArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                new SelectLevelLocalPresenter(getApplicationContext(), page).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                updateArrowsVisibility();
            }
        });
    }

    public void updateArrowsVisibility() {
        if (page == 0) {
            ivArrowLeft.setVisibility(View.INVISIBLE);
            if (emocionDaoImp.getLevelsForPage(getApplicationContext(), page + 1).size() == 0) {
                ivArrowRight.setVisibility(View.INVISIBLE);
            } else {
                ivArrowRight.setVisibility(View.VISIBLE);
            }

        } else if (emocionDaoImp.getLevelsForPage(getApplicationContext(), page + 1).size() == 0) {
            ivArrowLeft.setVisibility(View.VISIBLE);
            ivArrowRight.setVisibility(View.INVISIBLE);
        } else {
            ivArrowLeft.setVisibility(View.VISIBLE);
            ivArrowRight.setVisibility(View.VISIBLE);
        }

    }

    public void loadEvents() {
        selectEmocionsLevelCallback = new SelectEmocionsLevelCallback() {
            @Override
            public void levelSelected(int level) {
                Intent intent = new Intent(SelectEmocionsLevelActivity.this, EmocionsActivity.class);
                intent.putExtra("level", level);
                startActivityForResult(intent, 0);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        reloadLevels();
    }

    public void reloadLevels() {
        page = 0;
        new SelectLevelLocalPresenter(getApplicationContext(), page).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        updateArrowsVisibility();
    }

    public void initRecycler() {
        adapter = new SelectEmocionsLevelAdapter(this, emocions, selectEmocionsLevelCallback);
        rvLevels.setAdapter(adapter);
        rvLevels.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5));
        rvLevels.setNestedScrollingEnabled(false);
    }

    public void setDataResult(List<Emocion> emocions) {
        this.emocions = emocions;
        adapter.setData(this.emocions);
    }

    @SuppressLint("StaticFieldLeak")
    public class SelectLevelLocalPresenter extends AsyncTask<Void, Void, Void>
    {
        Context context;
        List<Emocion> emocionList;
        int pageToGet;

        public SelectLevelLocalPresenter(Context context, int pageToGet) {
            this.context = context;
            this.pageToGet = pageToGet;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            //get levels from database
            emocionList = emocionDaoImp.getLevelsForPage(context, pageToGet);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            setDataResult(emocionList);
        }

    }
}
