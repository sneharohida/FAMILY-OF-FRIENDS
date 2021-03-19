package com.example.s3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.s3.Complaint.ComplaintItem;
import com.example.s3.R;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ExampleViewHolder> {
    private ArrayList<ComplaintItem> mExampleList;
    private OnNoteListener mOnNoteListener;
    private static ComplaintInterface complaintInterface;


    public ComplaintAdapter(ArrayList<ComplaintItem> mNotes,OnNoteListener onNoteListener,ComplaintInterface complaintInterface){
        this.mExampleList=mNotes;
        this.mOnNoteListener=onNoteListener;
        this.complaintInterface=complaintInterface;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnNoteListener mOnNoteListener;
        public ImageView mImageView;
        public ImageButton mImageBut;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public ExampleViewHolder(final View itemView, final OnNoteListener onNoteListener) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mImageBut = itemView.findViewById(R.id.trash);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.textView3);
            mTextView4 = itemView.findViewById(R.id.textView4);

            mImageBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition()!=RecyclerView.NO_POSITION)
                    complaintInterface.deleteItem(getAdapterPosition());
                }
            });
            mOnNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) { mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }



    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_complaint_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mOnNoteListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ComplaintItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText3());
        if(!currentItem.getText2().equals("NA"))
        holder.mTextView3.setText(currentItem.getText2());
        holder.mTextView4.setText(currentItem.getText4());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public interface OnNoteListener{
        void onNoteClick(int position);

    }
    public interface ComplaintInterface{
        void deleteItem(int position);
    }
}
