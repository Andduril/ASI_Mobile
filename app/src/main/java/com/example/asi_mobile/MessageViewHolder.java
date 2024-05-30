package com.example.asi_mobile;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asi_mobile.Models.Message;

import java.util.Objects;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    //Autant d'attributs dans la classe que de widgets affichés (content, userId et timestamp : 3)
    private TextView contentTextView;
    private TextView userIdTextView;
    private TextView timestampTextView;
    private TextView contentConnectedUserTextView;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        this.contentTextView = itemView.findViewById(R.id.textView_content_autreUser);
        this.userIdTextView = itemView.findViewById(R.id.textView_userId);
        this.timestampTextView = itemView.findViewById(R.id.textView_timestamp);
        this.contentConnectedUserTextView = itemView.findViewById(R.id.textView_content_connectedUser);
    }

    public void MettreAJourLigne(Message unMessage) {
        if (Objects.nonNull(unMessage)) {
            if (Objects.equals(unMessage.getUserId(), "fakeID"))//Si le userId est celui de l'utilisateur connecté
            {
                this.contentConnectedUserTextView.setText(unMessage.getContent());
                this.contentTextView.setText("");
            }
            else
            {
                this.contentTextView.setText(unMessage.getContent());
                this.contentConnectedUserTextView.setText("");
            }
            this.userIdTextView.setText(unMessage.getUserId());
            this.timestampTextView.setText(unMessage.getFormatedDate());
        }
    }
}
