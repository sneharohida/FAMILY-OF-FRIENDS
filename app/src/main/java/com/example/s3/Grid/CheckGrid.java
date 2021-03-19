package com.example.s3.Grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class CheckGrid extends TableLayout implements View.OnClickListener {
    public CheckGrid(Context context) {
        super(context);
    }

    public CheckGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View view) {
       // final RadioButton rb=(RadioButton)view;
        final CheckBox rb=(CheckBox)view;
        //  rb.setBackgroundColor(R.drawable.tree);
        //  rb.setBackground(getResources().getDrawable(R.drawable.tree));

    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        setChild(((TableRow)child));
//setOnClickListener((TableRow)child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        setChild(((TableRow)child));

    }

    private void setChild(TableRow tr)
    {
        final int c=tr.getChildCount();
        for(int i=0;i<c;i++)
        {
            final View view=tr.getChildAt(i);
            if(view instanceof CheckBox)
                view.setOnClickListener(this);
        }
    }

}
