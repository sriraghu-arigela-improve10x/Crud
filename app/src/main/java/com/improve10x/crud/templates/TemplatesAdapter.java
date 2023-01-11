package com.improve10x.crud.templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.TemplateItemBinding;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {

    private List<Template> templateList;

    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener listener){
        onItemActionListener = listener;
    }

    void setData(List<Template> templates) {
        templateList = templates;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TemplateItemBinding binding = TemplateItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TemplateViewHolder templatesViewHolder = new TemplateViewHolder(binding);
        return templatesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templateList.get(position);
        holder.binding.setTemplate(template);
        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(template);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(template);
        });
    }

    @Override
    public int getItemCount() {
        return templateList.size();
    }
}
