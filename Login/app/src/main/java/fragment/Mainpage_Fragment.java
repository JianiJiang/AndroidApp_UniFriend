package fragment;

/**
 * Created by nicole on 2018-02-22.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.jiani.login.R;

import com.example.jiani.login.activity.MainActivity;

import Adapter.MyAdapter;

import java.util.List;

import static com.example.jiani.login.activity.MainActivity.post_list;


public class Mainpage_Fragment extends Fragment{

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<Fragment> fragments;
    private MainActivity activity;
    private ImageButton like;
    private ImageButton dislike;
    private ImageButton share;
    private ImageButton report;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.layout_listview_mainpage, containter, false);
        activity = (MainActivity) getActivity();
        this.fragments=activity.fragments;
        MyAdapter adapter = new MyAdapter(getActivity(),R.layout.listview_item_mainpage,post_list,fragments);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);


        return view;
    }



}
