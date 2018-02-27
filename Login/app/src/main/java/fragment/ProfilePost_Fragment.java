package fragment;

/**
 * Created by nicole on 2018-02-26.
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterPost;
import application.MyApp;
import entity.Post_ListView;
import com.example.jiani.login.R;
import com.example.jiani.login.activity.LoginActivity;
import com.example.jiani.login.activity.ProfileActivity;
import com.example.jiani.login.activity.RegisterActivity;
import com.example.jiani.login.activity.SendActivity;


public class ProfilePost_Fragment extends Fragment {

    private ListView listViewPost;
    private List<Post_ListView> post_list = new ArrayList<>();
    private List<Fragment> fragments;
    private MyApp app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        MyApp app = new MyApp();
        View post = inflater.inflate(R.layout.layout_listview_profile_own_post, container, false);
//        initPost();
        ProfileActivity activity = (ProfileActivity)getActivity();
        this.fragments = activity.fragments;
        AdapterPost adapterPost = new AdapterPost(getActivity(), R.layout.listview_item_profile_own_post,post_list);
        listViewPost = (ListView) post.findViewById(R.id.listview_post);
        listViewPost.setAdapter(adapterPost);
        app.getList();
        adapterPost.notifyDataSetChanged();




        return post;


    }


}

