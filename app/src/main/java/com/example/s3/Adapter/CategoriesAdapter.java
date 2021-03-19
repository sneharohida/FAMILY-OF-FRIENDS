package com.example.s3.Adapter;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3.R;
import com.example.s3.extra.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AdapterAllCategoriesViewHolder> {
    ArrayList<CategoriesHelperClass> mostViewedLocations;
    private OnNoteListener2 mOnNoteListener;


    public CategoriesAdapter(ArrayList<CategoriesHelperClass> mostViewedLocations, OnNoteListener2 onNoteListener) {
        this.mostViewedLocations = mostViewedLocations;
        this.mOnNoteListener = onNoteListener;

    }

    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        AdapterAllCategoriesViewHolder lvh = new AdapterAllCategoriesViewHolder(view, mOnNoteListener);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterAllCategoriesViewHolder holder, int position) {

        CategoriesHelperClass helperClass = mostViewedLocations.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitle());
        // holder.textView2.setText(helperClass.getDescription());
        holder.textView.setTextSize(18);
        holder.relativeLayout.setBackground(helperClass.getGradient());


        if (holder.textView.getText().equals("Attendance Time")) {
            Long oldLong, NewLong, diff = null;
            Date today = new Date();
            Log.d("ttt", "1");

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String oldTime = formatter.format(Calendar.getInstance().getTime());
            Log.d("ttt", oldTime);
            String NewTime = "21:00:00";//Timer date 1
            if (oldTime.compareTo(NewTime) >= 0 && oldTime.compareTo("21:30:00") <= 0) {
                holder.textView2.setText("Can Fill");
            } else if (oldTime.compareTo("21:30:00") > 0) {
                holder.textView2.setText("Time Up");

            } else {
                Date oldDate, newDate;
                try {
                    oldDate = formatter.parse(oldTime);
                    newDate = formatter.parse(NewTime);
                    oldLong = oldDate.getTime();
                    NewLong = newDate.getTime();
                    Log.d("ttt", oldTime + " " + NewTime + " ");

                    diff = NewLong - oldLong;
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d("ttt", e.toString());

                }

                new CountDownTimer(diff, 500) {
                    public void onTick(long millisUntilFinished) {
                        long seconds = millisUntilFinished / 1000;
                        long minutes = seconds / 60;
                        long hours = minutes / 60;
                        long days = hours / 24;
                        String time = hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
                        holder.textView2.setText(time);
                        holder.textView2.setTextSize(20);
                    }

                    public void onFinish() {
                        holder.textView2.setText("Can Fill");
                        SessionManager sm = new SessionManager(holder.textView.getContext());
                        sm.setatt("no");
                    }
                }.start();
            }
        } else
            holder.textView2.setText(helperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }

    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnNoteListener2 mOnNoteListener;
        CountDownTimer timer;
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView, textView2;

        public AdapterAllCategoriesViewHolder(@NonNull View itemView, OnNoteListener2 onNoteListener) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.background_gradient);
            imageView = itemView.findViewById(R.id.categories_image);
            textView = itemView.findViewById(R.id.categories_title);
            textView2 = itemView.findViewById(R.id.categories_desc);
            mOnNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnNoteListener.onNoteClick2(getAdapterPosition());
        }
    }

    public interface OnNoteListener2 {
        void onNoteClick2(int position);

    }
}
