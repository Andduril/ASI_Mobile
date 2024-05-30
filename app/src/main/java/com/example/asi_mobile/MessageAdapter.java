package com.example.asi_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asi_mobile.Models.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messages) {
        messageList = messages;
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
        holder.MettreAJourLigne(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}