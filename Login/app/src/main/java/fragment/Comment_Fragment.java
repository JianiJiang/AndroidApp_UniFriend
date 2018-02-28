package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiani.login.R;
import com.example.jiani.login.activity.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.AdapterSendPost;

import com.example.listscrollview.ListScrollView;

import entity.Comment;
import entity.Post_ListView;
import entity.SendComment;
import entity.Post_comment;

import android.widget.TextView;
import android.widget.Toast;

import static com.example.jiani.login.activity.LoginActivity.user;
import static com.example.jiani.login.activity.MainActivity.postDetail;


/**
 * Created by nicole on 2018-02-23.
 */

public class Comment_Fragment extends Fragment {
    private ListScrollView listView;
    private ArrayAdapter arrayAdapter;
    private List<Fragment> fragments;
    private MainActivity activity;
    boolean value = false;
    private View view;
    private ImageButton favorite;
    private TextView submit;
    private RequestQueue myQueue;
    private List<Post_comment> comment_list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_post, container, false);
        initComments();
        activity = (MainActivity) getActivity();
        this.fragments = activity.fragments;

        super.onCreate(savedInstanceState);

        myQueue = Volley.newRequestQueue(getActivity());

        TextView title = (TextView) view.findViewById(R.id.tv_post_title) ;
        TextView body = (TextView) view.findViewById(R.id.tv_post_body);
        TextView name = (TextView) view.findViewById(R.id.tv_post_author);
        TextView  time = (TextView) view.findViewById(R.id.tv_post_time);
        TextView keywords = (TextView) view.findViewById(R.id.tv_activity_post_select_keywords);
        final EditText comments = (EditText) view.findViewById(R.id.Comment);
        favorite = (ImageButton) view.findViewById(R.id.ib_post_comment);
        submit = (TextView) view.findViewById(R.id.tv_activity_post_submit) ;

        listView = (ListScrollView)view.findViewById(R.id.lv_post_listview);

        setListViewHeightBasedOnChildren(listView);

        title.setText(postDetail.getTitle());
        body.setText(postDetail.getBody());
        name.setText(postDetail.getUsername());
        time.setText(postDetail.getDate());
        keywords.setText(postDetail.getCoursecode());
        System.out.println(postDetail.getUsername());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="http://213.89.22.179:8080/api/rest/post/comments/add";
                StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response",response);
//                        Toast.makeText(getActivity(), "Register sucessful! Go to your kth email to verify!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), LoginActivity.class);
//                        startActivity(intent);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), "Send comment failure!", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        SendComment sendComment = new SendComment();
                        sendComment.setUsername(user.getUsername());
                        sendComment.setPostID(postDetail.getId());
                        sendComment.setBody(comments.getText().toString());
                        Log.i("JSON", JSON.toJSONString(sendComment));
                        return JSON.toJSONString(sendComment).getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/json");
                        return params;
                    }
                };
                myQueue.add(stringRequest);
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
            }
        });
        AdapterSendPost adapter = new AdapterSendPost(getActivity(), R.layout.listview_item_post, comment_list, fragments);

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
            String nameString = intent.getStringExtra("name");
            String keywordsString = intent.getStringExtra("keywords");

            TextView title = (TextView) view.findViewById(R.id.tv_post_title) ;
            TextView body = (TextView) view.findViewById(R.id.tv_post_body);
            TextView name = (TextView) view.findViewById(R.id.tv_post_author);
            TextView  time = (TextView) view.findViewById(R.id.tv_post_time);
            TextView keywords = (TextView) view.findViewById(R.id.tv_activity_post_select_keywords);

            title.setText(titleString);
            body.setText(bodyString);
            time.setText(timeString);
            name.setText(nameString);
            keywords.setText(keywordsString);
        }
    }



    private void initComments() {
        myQueue = Volley.newRequestQueue(getActivity());

        String url ="http://213.89.22.179:8080/api/rest/post/comments/"+postDetail.getId();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("comments get", response);
                List<Comment> temp = Arrays.asList(JSON.parseObject(response, Comment [].class));

                for (Comment c :temp){
                        Comment comment = new Comment(c.getId(), c.getUser(), c.getPost(), c.getBody(), c.getDate());
                        Post_comment post_comment = new Post_comment(comment.getUser().getUsername(), comment.getDate(), R.drawable.img, comment.getBody());
                        comment_list.add(post_comment);
                        //System.out.println(p.getUser().getUsername());
                    }
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("ProfilePost error", volleyError.getMessage());
            }
        });
        myQueue.add(stringRequest);
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