package com.israt.mydailyexpense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private SqlHelper helper;
    private List<Expenses> expensesList;
    private Context context;

    public ExpenseAdapter(List<Expenses> expensesList, Context context) {
        this.expensesList = expensesList;
        this.context = context;
    }

    //    public ExpenseAdapter(List<Expenses> expensesList) {
//        this.expensesList = expensesList;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_design, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Expenses expenses = expensesList.get(position);

        holder.typeTv.setText(expenses.getType());
        holder.amountTv.setText(String.valueOf(expenses.getAmount())+" Tk.");
//        holder.dateTv.setText(expenses.getDate());
//        holder.timeTv.setText(expenses.getTime());

        holder.updateIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,AddActivity.class);
                intent.putExtra("id", String.valueOf(expenses.getId()));
                intent.putExtra("type",expenses.getType());
                intent.putExtra("amount", String.valueOf(expenses.getAmount()));
                intent.putExtra("date",expenses.getDate());
                intent.putExtra("time",expenses.getTime());
                context.startActivity(intent);

            }
        });

        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new SqlHelper(context);
                helper.deleteData(expenses.getId());
                expensesList.remove(position);
                notifyDataSetChanged();

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                holder.idTv.setVisibility(View.GONE);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", String.valueOf(expenses.getId()));
                intent.putExtra("type",expenses.getType());
                intent.putExtra("amount", String.valueOf(expenses.getAmount()));
                intent.putExtra("date",expenses.getDate());
                intent.putExtra("time",expenses.getTime());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeTv,amountTv,dateTv,timeTv;
        private ImageView updateIv,deleteIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTv =itemView.findViewById(R.id.typeTv);
            amountTv =itemView.findViewById(R.id.amountTv);
//            dateTv =itemView.findViewById(R.id.dateTv);
//            timeTv =itemView.findViewById(R.id.timeTv);
            updateIv =itemView.findViewById(R.id.updateIv);
            deleteIv =itemView.findViewById(R.id.deleteIv);

        }
    }
}
