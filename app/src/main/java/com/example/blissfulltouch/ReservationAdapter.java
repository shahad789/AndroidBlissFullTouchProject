package com.example.blissfulltouch;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private Cursor cursor;

    public ReservationAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        int serviceNameIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_SERVICE_NAME_FK);
        int dateIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_DATE);
        int timeIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TIME);
        int locationIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_LOCATION);

        if (serviceNameIndex == -1 || dateIndex == -1 || timeIndex == -1 || locationIndex == -1) {
            // Handle the case where one or more columns are not found
            return;
        }

        String serviceName = cursor.getString(serviceNameIndex);
        String date = cursor.getString(dateIndex);
        String time = cursor.getString(timeIndex);
        String location = cursor.getString(locationIndex);

        holder.serviceNameTextView.setText(serviceName);
        holder.dateTextView.setText(date);
        holder.timeTextView.setText(time);
        holder.locationTextView.setText(location);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        private TextView serviceNameTextView;
        private TextView dateTextView;
        private TextView timeTextView;
        private TextView locationTextView;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.serviceNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
        }
    }
}
