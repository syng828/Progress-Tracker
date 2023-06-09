package com.example.progresstracker;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Start_RecyclerViewAdapter extends RecyclerView.Adapter<Start_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Subject> subjects;

    //constructor
    public Start_RecyclerViewAdapter(Context context, ArrayList<Subject> subjects, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.subjects = subjects;
    }


    @NonNull
    @Override  //inflates the viewholder
    public Start_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.start_recycler_view_row, parent, false); //gets view from xml row file

        return new Start_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override  //When its scrolling
    public void onBindViewHolder(@NonNull Start_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(subjects.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

//basically the layout
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textView = itemView.findViewById(R.id.subjectTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface!= null) {   //passes the position to the activity, and makes use of interface
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
