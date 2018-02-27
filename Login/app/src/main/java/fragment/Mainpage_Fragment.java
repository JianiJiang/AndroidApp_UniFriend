package fragment;

/**
 * Created by nicole on 2018-02-22.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jiani.login.R;

import com.example.jiani.login.activity.LoginActivity;
import com.example.jiani.login.activity.MainActivity;
import com.example.jiani.login.activity.SendActivity;

import Adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import entity.Main_page_post;




public class Mainpage_Fragment extends Fragment{

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<Main_page_post> post_lists = new ArrayList<>();
    private List<Fragment> fragments;
    private MainActivity activity;
    private ImageButton like;
    private ImageButton dislike;
    private ImageButton share;
    private ImageButton report;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.layout_listview_mainpage, containter, false);
        initPost();
        activity = (MainActivity) getActivity();
        this.fragments=activity.fragments;
        MyAdapter adapter = new MyAdapter(getActivity(),R.layout.listview_item_mainpage,post_lists,fragments);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);


        return view;
    }



    private void initPost() {

        for (int i = 0; i < 7; i++) {
            Main_page_post post1 = new Main_page_post("A big title", "Key words: balabala", R.drawable.img, "Last edited: 2018-2-25.");
            post_lists.add(post1);
            Main_page_post post2 = new Main_page_post("Another big title", "Key words: balabala", R.drawable.img, "Last edited: 2018-2-24.");
            post_lists.add(post2);
            Main_page_post post3 = new Main_page_post("A big title again", "Key words: balabala", R.drawable.img, "Last edited: 2018-2-23.");
            post_lists.add(post3);


        }

    }

}
