package com.example.s3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.s3.Notice.NoticeItem;
import com.example.s3.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ExampleViewHolder> {
    private ArrayList<NoticeItem> mExampleList;
  //  private OnItemClickListener mListener;
    private OnNoteListener mOnNoteListener;


    public NoticeAdapter(ArrayList<NoticeItem> mNotes,OnNoteListener onNoteListener){
        this.mExampleList=mNotes;
        this.mOnNoteListener=onNoteListener;
    }

    /*public interface OnItemClickListener {
        void onItemClick(int position);
    }*/
  /*  public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }*/

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnNoteListener mOnNoteListener;
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ExampleViewHolder(View itemView,OnNoteListener onNoteListener) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);


          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
            mOnNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) { mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }



    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mOnNoteListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        NoticeItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public interface OnNoteListener{
        void onNoteClick(int position);

    }
}
