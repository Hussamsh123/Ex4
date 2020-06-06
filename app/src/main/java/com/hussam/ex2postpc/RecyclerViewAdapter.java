package com.hussam.ex2postpc;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Item> todoBoomList;
    private Context context;
    private ArrayList<ViewHolder> holderList;

    RecyclerViewAdapter(ArrayList<Item> toodoBoomList, Context context) {
        this.todoBoomList = toodoBoomList;
        this.context = context;
        this.holderList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todoboomm_layout, parent,
                false);

        return new ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.text.setText(todoBoomList.get(position).getItemText());
        final MainActivity main = (MainActivity)context;
        if (todoBoomList.get(position).isDone()) {
            holder.text.setAlpha(0.3f);
            holder.img.setImageResource(R.drawable.done);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(todoBoomList.get(position).isDone()){
                    main.deleteActivity(position);
                }else{
                    main.editActivity(position);
                }

            }
        });
    }

    void updateData(ArrayList<Item> viewModels) {
        todoBoomList.clear();
        todoBoomList.addAll(viewModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todoBoomList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ConstraintLayout layout;
        private ImageView img;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.TextID);
            layout = itemView.findViewById(R.id.layout);
            img = itemView.findViewById(R.id.imageViewID);
            RecyclerViewAdapter.this.holderList.add(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void markTask(int position){
        holderList.get(position).text.setAlpha(0.3f);
        todoBoomList.get(position).setDone(true);
        onBindViewHolder(holderList.get(position), position);
}

    void editName(int position, String newTask){
        todoBoomList.get(position).setItemText(newTask);

    }
    private void doneTaskAnimation(int position){
        holderList.get(position).text.setAlpha(0.3f);
        holderList.get(position).img.setImageResource(R.drawable.done);
    }

    void unMarkTask(int position){
        todoBoomList.get(position).setDone(false);
        holderList.get(position).img.setImageResource(0);
        holderList.get(position).text.setAlpha(1.f);

    }
    void deleteTodo(int position){
        holderList.remove(position);
    }
    void appendItems(ArrayList<Item> arr){
            todoBoomList.addAll(arr);

    }

}
