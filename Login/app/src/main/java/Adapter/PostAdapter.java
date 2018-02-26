package Adapter;

import java.util.List;
import android.widget.ArrayAdapter;
import android.support.v4.app.Fragment;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jiani.login.R;
import com.example.jiani.login.activity.MainActivity;

import entity.Post_comment;

/**
 * Created by nicole on 2018-02-25.
 */

public class PostAdapter extends ArrayAdapter<Post_comment> {


          private int resourcedId;
          private MainActivity activity;
          private List<Fragment> fragments;

    public PostAdapter(Context context, int textViewResourceId, List<Post_comment> objects, List<Fragment> fragments){
        super(context, textViewResourceId, objects);
        resourcedId = textViewResourceId;
        activity = (MainActivity) context;
        this.fragments = fragments;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Post_comment comment = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourcedId,parent,false);
        }else{
            view = convertView;
        }
        ImageView commentImage = (ImageView) view.findViewById(R.id.iv_post_likstview_img);
        TextView commentName = (TextView)view.findViewById(R.id.tv_post_listview_author);
        TextView commentTime = (TextView) view.findViewById(R.id.tv_post_listview_time);
        TextView commentBody = (TextView) view.findViewById(R.id.tv_post_listview_body);
        commentImage.setImageResource(comment.getImageId());
        commentName.setText(comment.getName());
        commentTime.setText(comment.getTime());
        commentBody.setText(comment.getBody());
        return view;
    }

}
