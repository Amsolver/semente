package com.amsolver.semente.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.amsolver.semente.R;
import com.amsolver.semente.callbacks.SelectEmocionsLevelCallback;
import com.amsolver.semente.callbacks.SelectWordleLevelCallback;
import com.amsolver.semente.model.Emocion;
import com.amsolver.semente.model.Wordle;

import java.util.List;

public class SelectEmocionsLevelAdapter extends RecyclerView.Adapter{

    private List<Emocion> mData;
    private LayoutInflater mInflater;
    private Context context;
    private SelectEmocionsLevelCallback callback;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.adapter_list_level,
                        parent,
                        false
                );
        return new ViewHolder(view);
    }

    // data is passed into the constructor
    public SelectEmocionsLevelAdapter(Context context, List<Emocion> data, SelectEmocionsLevelCallback callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.callback = callback;
    }

    @SuppressLint({"NotifyDataSetChanged", "RecyclerView"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Emocion emocion = mData.get(position);
        ((TextView)holder.itemView.findViewById(R.id.tvNumber)).setText(String.valueOf(emocion.getNumber()));
        setUpLevelColor(emocion, ((ImageView)holder.itemView.findViewById(R.id.ivBackground)), ((TextView)holder.itemView.findViewById(R.id.tvNumber)));
        holder.itemView.findViewById(R.id.clLevel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.levelSelected(emocion.getNumber());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Emocion> getActualList() {
        return mData;
    }



    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Emocion> emocions) {  // new list here
        this.mData = emocions;
        notifyDataSetChanged();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setUpLevelColor(Emocion emocion, ImageView iv, TextView tv) {
        if (emocion.getCompleted() == 1) {
            iv.setBackgroundColor(context.getColor(R.color.colorPrimary));
            iv.setImageDrawable(context.getDrawable(R.drawable.ic_check_24dp));
            tv.setTextColor(context.getColor(R.color.white));
        } else {
            iv.setImageDrawable(context.getDrawable(R.drawable.squarebordersolidwhite));
            tv.setTextColor(context.getColor(R.color.colorPrimary));
        }
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout clLevel;
        ImageView ivBackground;
        TextView tvNumber;

        ViewHolder(View itemView) {
            super(itemView);
            clLevel = itemView.findViewById(R.id.clLevel);
            ivBackground = itemView.findViewById(R.id.ivBackground);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


}
