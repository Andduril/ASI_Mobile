package com.example.asi_mobile;
import com.example.asi_mobile.Models.Message;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    private final TextView contentTextView, contentConnectedUserTextView;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        this.contentTextView = itemView.findViewById(R.id.textView_content_autreUser);
        this.contentConnectedUserTextView = itemView.findViewById(R.id.textView_content_connectedUser);
    }

    public void MettreAJourLigne(Message unMessage, String userKey) {
        if (Objects.nonNull(unMessage)) {
            String chaineAffiche = unMessage.getUserId() + " le " + unMessage.getFormatedDate() + " :\n " + unMessage.getContent();
            if (Objects.equals(unMessage.getUserId(), userKey))//Si le userId est celui de l'utilisateur connecté
            {
                this.contentConnectedUserTextView.setText(chaineAffiche);
                this.contentTextView.setText("");
            }
            else
            {
                this.contentTextView.setText(chaineAffiche);
                this.contentConnectedUserTextView.setText("");
            }
        }
    }
}
