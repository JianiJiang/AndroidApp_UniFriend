package com.example.listscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by nicole on 2018-02-26.
 */


public class ListScrollView extends ListView {

    public ListScrollView(Context context) {
        super(context);
    }
    public ListScrollView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public ListScrollView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs,defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,expandSpec);
    }


}

