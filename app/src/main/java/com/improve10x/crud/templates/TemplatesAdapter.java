package com.improve10x.crud.templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item, parent, false);
        TemplateViewHolder templatesViewHolder = new TemplateViewHolder(view);
        return templatesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templateList.get(position);
        holder.templateTxt.setText(template.messageText);
        holder.deleteBtn.setOnClickListener(view -> {
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
