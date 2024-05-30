package com.example.asi_mobile;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asi_mobile.Models.Message;

import java.util.Objects;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    //Autant d'attributs dans la classe que de widgets affichés (content et userId : 2)
    private TextView contentTextView;
    private TextView userIdTextView;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        this.contentTextView = itemView.findViewById(R.id.textView_content_recycler);
        this.userIdTextView = itemView.findViewById(R.id.textView_userId_recycler);
    }

    public void MettreAJourLigne(Message unMessage) {
        if (Objects.nonNull(unMessage)) {
            this.contentTextView.setText(unMessage.getContent());
            this.userIdTextView.setText(unMessage.getUserId());
        }
    }
}
