package com.example.asi_mobile;
import com.example.asi_mobile.Models.Message;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    private TextView contentTextView;
    private TextView contentConnectedUserTextView;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        this.contentTextView = itemView.findViewById(R.id.textView_content_autreUser);
        this.contentConnectedUserTextView = itemView.findViewById(R.id.textView_content_connectedUser);
    }

    public void MettreAJourLigne(Message unMessage) {
        if (Objects.nonNull(unMessage)) {
            String chaineAffiche = unMessage.getUserId() + " : " + unMessage.getContent() + " (" + unMessage.getFormatedDate() + ")";
            if (Objects.equals(unMessage.getUserId(), "fakeID"))//Si le userId est celui de l'utilisateur connecté
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
