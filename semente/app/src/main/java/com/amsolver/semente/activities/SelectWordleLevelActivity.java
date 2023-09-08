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
import com.amsolver.semente.adapters.SelectWordleLevelAdapter;
import com.amsolver.semente.callbacks.SelectWordleLevelCallback;
import com.amsolver.semente.dao.implementations.WordleDaoImp;
import com.amsolver.semente.game_activities.WordleActivity;
import com.amsolver.semente.model.Wordle;

import java.util.ArrayList;
import java.util.List;

public class SelectWordleLevelActivity extends AppCompatActivity {

    ImageView ivBack;
    ImageView ivArrowLeft;
    ImageView ivArrowRight;
    RecyclerView rvLevels;
    List<Wordle> wordles = new ArrayList<>();
    SelectWordleLevelAdapter adapter;

    WordleDaoImp wordleDaoImp;

    public int page = 0;

    SelectWordleLevelCallback selectWordleLevelCallback;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDaos();
        loadViews();
        loadLevels();
        loadListeners();
        loadEvents();
        initRecycler();
        updateArrowsVisibility();

    }

    public void loadViews() {
        setContentView(R.layout.activity_select_wordle_level_layout);
        ivBack = findViewById(R.id.ivBack);
        ivArrowLeft = findViewById(R.id.ivArrowLeft);
        ivArrowRight = findViewById(R.id.ivArrowRight);
        rvLevels = findViewById(R.id.rvLevels);
    }

    public void loadDaos() {
        wordleDaoImp = new WordleDaoImp();
    }

    public void loadLevels() {
        new SelectLevelLocalPresenter(SelectWordleLevelActivity.this, page).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
            ivArrowRight.setVisibility(View.VISIBLE);
        } else if (wordleDaoImp.getLevelsForPage(getApplicationContext(), page + 1).size() == 0) {
            ivArrowLeft.setVisibility(View.VISIBLE);
            ivArrowRight.setVisibility(View.INVISIBLE);
        } else {
            ivArrowLeft.setVisibility(View.VISIBLE);
            ivArrowRight.setVisibility(View.VISIBLE);
        }

    }

    public void loadEvents() {
        selectWordleLevelCallback = new SelectWordleLevelCallback() {
            @Override
            public void levelSelected(int level) {
                Intent intent = new Intent(SelectWordleLevelActivity.this, WordleActivity.class);
                intent.putExtra("level", level);
                startActivityForResult(intent, 1);
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
        adapter = new SelectWordleLevelAdapter(this, wordles, selectWordleLevelCallback);
        rvLevels.setAdapter(adapter);
        rvLevels.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5));
        rvLevels.setNestedScrollingEnabled(false);
    }

    public void setDataResult(List<Wordle> wordleList) {
        wordles = wordleList;
        adapter.setData(wordles);
    }

    @SuppressLint("StaticFieldLeak")
    public class SelectLevelLocalPresenter extends AsyncTask<Void, Void, Void>
    {
        Context context;
        List<Wordle> wordleList;
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
            wordleList = wordleDaoImp.getLevelsForPage(context, pageToGet);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            setDataResult(wordleList);
        }

    }
}
