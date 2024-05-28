package com.example.asi_mobile;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asi_mobile.Models.User;

import java.util.Objects;

public class UserViewHolder extends RecyclerView.ViewHolder {
    //Autant d'attributs dans la classe que de widgets affichés (nom et email : 2)
    private TextView nomTextView;
    private TextView emailTextView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        this.nomTextView = itemView.findViewById(R.id.textView_nom_recycler);
        this.emailTextView = itemView.findViewById(R.id.textView_email_recycler);
    }

    public void MettreAJourLigne(User unUser) {
        if (Objects.nonNull(unUser)) {
            this.nomTextView.setText(unUser.getUsername());
            this.emailTextView.setText(unUser.getEmail());
        }
    }
}
