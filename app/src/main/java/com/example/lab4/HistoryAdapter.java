package com.example.lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder> {

    private ArrayList<HistoryItem> history;

    HistoryAdapter() {
        history = new ArrayList<>();
    }

    void initialize(ArrayList<HistoryItem> history) {
        this.history = history;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item, viewGroup, false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder historyItemViewHolder, final int i) {
        final HistoryItem item = this.history.get(i);

        historyItemViewHolder.bind(item);

        //-------------------------------------------------------------------------------------------
        historyItemViewHolder.swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapElements(item);
            }
        });
        //-------------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    //-----------------------------------------------------------------------------------------------
    public void swapElements(HistoryItem item) {
        int i = history.indexOf(item);
        if (i >= history.size() - 1) {
            Collections.swap(history, i, i - 1);
        }
        else {
            Collections.swap(history, i, i + 1);
        }
        notifyDataSetChanged();
    }
    //-----------------------------------------------------------------------------------------------

    class HistoryItemViewHolder extends RecyclerView.ViewHolder {

        private TextView historyText;
        public Button swapButton;

        HistoryItemViewHolder(View itemView) {
            super(itemView);
            historyText = itemView.findViewById(R.id.history_text);
            swapButton = itemView.findViewById(R.id.swap_btn);
        }

        void bind(final HistoryItem historyItem) {
            historyText.setText(historyItem.getTextRepresentation());
        }
    }
}
