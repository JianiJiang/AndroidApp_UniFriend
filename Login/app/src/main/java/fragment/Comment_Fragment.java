package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.jiani.login.R;
import com.example.jiani.login.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.PostAdapter;
import com.example.listscrollview.ListScrollView;
import entity.Post_comment;
import android.content.Intent;
import android.widget.TextView;


/**
 * Created by nicole on 2018-02-23.
 */

public class Comment_Fragment extends Fragment {
    private ListScrollView listView;
    private ArrayAdapter arrayAdapter;
    private List<Post_comment> comment_lists = new ArrayList<>();
    private List<Fragment> fragments;
    private MainActivity activity;
    boolean value = false;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_post, container, false);
        initComments();
        activity = (MainActivity) getActivity();
        this.fragments = activity.fragments;

        super.onCreate(savedInstanceState);


        TextView title = (TextView) view.findViewById(R.id.tv_post_title) ;
        TextView body = (TextView) view.findViewById(R.id.tv_post_body);
        TextView name = (TextView) view.findViewById(R.id.tv_post_author);
        TextView  time = (TextView) view.findViewById(R.id.tv_post_time);

        listView = (ListScrollView)view.findViewById(R.id.lv_post_listview);
        setListViewHeightBasedOnChildren(listView);



        PostAdapter adapter = new PostAdapter(getActivity(), R.layout.listview_item_post, comment_lists, fragments);

        listView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getActivity().getIntent();
        value = intent.getBooleanExtra("val",false);

        if ( value == true)
        {
            String titleString = intent.getStringExtra("title");
            String bodyString = intent.getStringExtra("body");
            String timeString = intent.getStringExtra("time");


            TextView title = (TextView) view.findViewById(R.id.tv_post_title) ;
            TextView body = (TextView) view.findViewById(R.id.tv_post_body);
            TextView name = (TextView) view.findViewById(R.id.tv_post_author);
            TextView  time = (TextView) view.findViewById(R.id.tv_post_time);
            title.setText(titleString);
            body.setText(bodyString);
            time.setText(timeString);
        }
    }

    private void initComments() {

        for (int i = 0; i < 5; i++) {
            Post_comment post1 = new Post_comment("User 1", "Last edited: 2018-2-25.", R.drawable.img, "The review is accurate! Great!");
            comment_lists.add(post1);
            Post_comment post2 = new Post_comment("User 2", "Last edited: 2018-2-22.", R.drawable.img, "I love it!");
            comment_lists.add(post2);
            Post_comment post3 = new Post_comment("User 3", "Last edited: 2018-2-20.", R.drawable.img, "Thank you!");
            comment_lists.add(post3);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}