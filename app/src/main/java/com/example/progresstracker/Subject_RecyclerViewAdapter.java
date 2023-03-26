package com.example.progresstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Subject_RecyclerViewAdapter extends RecyclerView.Adapter<Subject_RecyclerViewAdapter.MyViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Topic> topics;

    public Subject_RecyclerViewAdapter(Context context, ArrayList<Topic>topics, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.topics = topics;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Subject_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subject_recycler_view_row, parent, false);

        return new Subject_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Subject_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(topics.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textView = itemView.findViewById(R.id.topicTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface!= null) {
                        int pos = getAdapterPosition();

                        if (pos!=RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (recyclerViewInterface!= null) {
                        int pos = getAdapterPosition();

                        if (pos!=RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onLongItemClick(pos);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
