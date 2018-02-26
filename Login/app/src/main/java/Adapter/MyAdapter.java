package Adapter;

/**
 * Created by nicole on 2018-02-19.
 */
import java.util.List;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jiani.login.R;
import com.example.jiani.login.activity.MainActivity;

import entity.Main_page_post;

public class MyAdapter extends ArrayAdapter<Main_page_post> {




    private ImageButton ibShare;
    private ImageButton ibLike;
    private ImageButton ibDislike;
    private ImageButton ibReport;
    private ImageButton ibComment;
    private TextView tvBody;
    private ImageView ivImg;
    private TextView tvTitle;
    private TextView tvTime;
    private MainActivity activity;
    private List<Fragment> fragments;


    private int resourcedId;

    public MyAdapter(Context context, int textViewResourceId, List<Main_page_post> objects, List<Fragment> fragments){
        super(context, textViewResourceId, objects);
        resourcedId = textViewResourceId;
        activity = (MainActivity) context;
        this.fragments = fragments;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Main_page_post post = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourcedId,parent,false);
        }else{
            view = convertView;
        }
        tvBody = (TextView) view.findViewById(R.id.tv_item_listview_body);
        ibComment = (ImageButton) view.findViewById(R.id.ib_item_listview_comment);
        ibReport = (ImageButton) view.findViewById(R.id.ib_item_listview_report);
        ibDislike = (ImageButton) view.findViewById(R.id.im_item_listview_dislike);
        ibLike = (ImageButton) view.findViewById(R.id.im_item_listview_like);
        ibShare = (ImageButton) view.findViewById(R.id.im_item_listview_share);
        ivImg = (ImageView) view.findViewById(R.id.iv_item_listview_img);
        tvTitle = (TextView) view.findViewById(R.id.tv_item_listview_title);
        tvTime = (TextView) view.findViewById(R.id.tv_item_listview_edited);
        ivImg.setImageResource(post.getImageId());
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
        tvTime.setText(post.getTime());

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.replaceFragment(fragments.get(1));
            }
        });
      return view;
    }

}
