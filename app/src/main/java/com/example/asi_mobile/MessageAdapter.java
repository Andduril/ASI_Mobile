package com.example.asi_mobile;
import com.example.asi_mobile.Models.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private final List<Message> messageList;
    private final String userKey;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String message);
    }
    public MessageAdapter(List<Message> messages, String userKey, OnItemClickListener listener) {
        messageList = messages;
        this.userKey = userKey;
        this.listener = listener;

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater monInflater = LayoutInflater.from(parent.getContext());
        View view = monInflater.inflate(R.layout.layout_ligne_recyclerview, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.MettreAJourLigne(messageList.get(position), userKey);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(messageList.get(position).getContent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

}
