package com.improve10x.crud.templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesViewHolder> {

    public List<Template> templateArrayList;

    public OnItemActionListener onItemActionListener;

    public void setOnItemActionListener(OnItemActionListener listener){
        onItemActionListener = listener;
    }

    public void setData(List<Template> templates) {
        templateArrayList = templates;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TemplatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item, parent, false);
        TemplatesViewHolder templatesViewHolder = new TemplatesViewHolder(view);
        return templatesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplatesViewHolder holder, int position) {
        Template template = templateArrayList.get(position);
        holder.templateTxt.setText(template.message);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(template);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(template);
        });
    }

    @Override
    public int getItemCount() {
        return templateArrayList.size();
    }
}
