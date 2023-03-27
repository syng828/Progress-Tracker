package com.example.progresstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Topic_RecyclerViewAdapter extends RecyclerView.Adapter<Topic_RecyclerViewAdapter.MyViewHolder> {
    private final TRecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Question> questions;


    public Topic_RecyclerViewAdapter(Context context, ArrayList<Question> questions, TRecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.questions = questions;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Topic_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topic_recycler_view_row, parent, false);

        return new Topic_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Topic_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(questions.get(position).getQuestion());
        boolean isExpanded = questions.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, questionSymbol, correctness;
        EditText input;
        Button checkAnswer, getAnswer;

        ConstraintLayout constraintLayout;
        RelativeLayout expandableLayout;

        public MyViewHolder(@NonNull View itemView, TRecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textView = itemView.findViewById(R.id.question);
            questionSymbol = itemView.findViewById(R.id.questionSymbol);
            input = itemView.findViewById(R.id.editAnswer);
            correctness = itemView.findViewById(R.id.correctness);

            checkAnswer = itemView.findViewById(R.id.chkBtn);
            getAnswer = itemView.findViewById(R.id.ansBtn);


            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            //expands the constraint
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question question = questions.get(getAdapterPosition());
                    question.setExpanded(!question.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            //removes value
            constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                        if (recyclerViewInterface != null) {
                            int pos = getAdapterPosition();

                            if (pos != RecyclerView.NO_POSITION) {
                                recyclerViewInterface.onLongItemClick(pos);
                            }
                        }
                        return true;
                }
            });

            //checks answer
            checkAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question question = questions.get(getAdapterPosition());
                    question.check(input.getText().toString());
                    correctness.setText(question.getCorrectString());
                    correctness.setVisibility(View.VISIBLE);
                    notifyItemChanged(getAdapterPosition());
                }
            });


            getAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question question = questions.get(getAdapterPosition());
                    correctness.setVisibility(View.GONE);
                    input.setText(question.getAnswer());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }

    }
}
