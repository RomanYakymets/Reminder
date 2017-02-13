package com.example.roma.todo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.roma.todo.EditTaskActivity;
import com.example.roma.todo.R;
import com.example.roma.todo.dto.RemindDTO;
import com.example.roma.todo.dto.RemindDbHelper;

import java.lang.ref.WeakReference;
import java.util.List;

public class RemindListAdapter extends RecyclerView.Adapter<RemindListAdapter.RemindViewHolder>{

    private List<RemindDTO> data;
    public RemindListAdapter(List<RemindDTO> data) {
        this.data = data;
    }

    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_item, parent, false);
        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RemindViewHolder holder, int position) {

        holder._id = data.get(position).getId();
        holder.title.setText(data.get(position).getTitle());
        holder.details.setText(data.get(position).getDetails());
        holder.type.setText(data.get(position).getType());
        holder.done.setChecked(data.get(position).isDone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemindDbHelper remindDbHelper = new RemindDbHelper(v.getContext());

                if (holder.done.isChecked()) {
                    holder.done.setChecked(false);
                    remindDbHelper.updateRemind(holder._id, 0);
                }else{
                    holder.done.setChecked(true);
                    remindDbHelper.updateRemind(holder._id, 1);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final WeakReference<Context> weakContext = new WeakReference<>(view.getContext());
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.inflate(R.menu.menu_context_item);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.contextMenuEdit:
                                Intent intent= new Intent(weakContext.get(), EditTaskActivity.class);
                                intent.putExtra("_id", holder._id);
                                intent.putExtra("Title", holder.title.getText());
                                intent.putExtra("Type", holder.type.getText());
                                intent.putExtra("Details", holder.details.getText());
                                weakContext.get().startActivity(intent);
                                break;
                            case R.id.contextMenuDelete:
                                RemindDbHelper remindDbHelper = new RemindDbHelper(weakContext.get());
                                remindDbHelper.deleteRemind(holder._id);
                                data.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(),data.size());
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RemindViewHolder extends RecyclerView.ViewHolder {

        Integer _id;
        CardView cardView;
        TextView title, type, details;
        CheckBox done;

        public RemindViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            type = (TextView) itemView.findViewById(R.id.type);
            details = (TextView) itemView.findViewById(R.id.details);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            done = (CheckBox) itemView.findViewById(R.id.done);
        }
    }

}
