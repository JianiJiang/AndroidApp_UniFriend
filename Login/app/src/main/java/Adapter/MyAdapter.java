package Adapter;

/**
 * Created by nicole on 2018-02-19.
 */
import java.util.List;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiani.login.R;
import com.example.jiani.login.activity.LoginActivity;
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
        tvBody = (TextView) view.findViewById(R.id.tv_item_listview_mainpage_body);

        ibReport = (ImageButton) view.findViewById(R.id.ib_item_listview_mainpage_report);
        ibDislike = (ImageButton) view.findViewById(R.id.ib_item_listview_mainpage_dislike);
        ibLike = (ImageButton) view.findViewById(R.id.ib_item_listview_mainpage_like);
        ibShare = (ImageButton) view.findViewById(R.id.ib_item_listview_mainpage_share);
        ivImg = (ImageView) view.findViewById(R.id.iv_item_listview_mainpage_img);
        tvTitle = (TextView) view.findViewById(R.id.tv_item_listview_mainpage_title);
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
                ibLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Like +1 :)", Toast.LENGTH_SHORT).show();

                    }


                });
                ibDislike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Dislike +1 :(", Toast.LENGTH_SHORT).show();

                    }


                });
                ibShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setTitle("Link Share");
                        dialog.setMessage("https://download_link/UniFriend.se");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        dialog.show();


                    }


                });
                ibReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Report Successful!", Toast.LENGTH_SHORT).show();

                    }


                });




      return view;
    }

}
