package com.example.s3.Grid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RadioGrid extends TableLayout implements View.OnClickListener {
    RadioButton active=null;

    public RadioGrid(Context context) {
        super(context);
    }

    public RadioGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View view) {
        final RadioButton rb=(RadioButton)view;
       // final TextView tb=(TextView)view;
      //  rb.setBackgroundColor(R.drawable.tree);
      //  rb.setBackground(getResources().getDrawable(R.drawable.tree));
        if(active!=null)
        {

            active.setChecked(false);

           // active.setBackground(getResources().getDrawable(R.drawable.pb));

        }

        rb.setChecked(true);
       // tb.setTextColor(Color.RED);

        //if(active!=rb)
      //  rb.setBackground(getResources().getDrawable(R.drawable.tree));

        active=rb;
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
            if(view instanceof  RadioButton)
                view.setOnClickListener(this);
        }
    }


}
