package com.example.virtualguide.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualguide.R;
import com.example.virtualguide.model.KnowledgeItem;

import java.util.List;

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(KnowledgeItem item);
    }

    private List<KnowledgeItem> items;
    private final OnItemClickListener listener;

    public KnowledgeAdapter(List<KnowledgeItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public void updateList(List<KnowledgeItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KnowledgeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_knowledge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeAdapter.ViewHolder holder, int position) {
        final KnowledgeItem item = items.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvCategory;
        TextView tvShortDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvShortDescription = itemView.findViewById(R.id.tvShortDescription);
        }

        void bind(KnowledgeItem item) {
            tvTitle.setText(item.getTitle());
            tvCategory.setText(item.getCategory().getDisplayName());
            tvShortDescription.setText(item.getShortDescription());
        }
    }
}
