package Adapter;

/**
 * Created by nicole on 2018-02-26.
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import application.MyApp;
import entity.Post_ListView;
import com.example.jiani.login.R;

import com.example.jiani.login.activity.MainActivity;
import com.example.jiani.login.activity.ProfileActivity;
import com.example.jiani.login.activity.SendActivity;

public class AdapterPost extends ArrayAdapter<Post_ListView> {
    //    private TextView tvBody;
//    private ImageView ivImg;
//    private TextView tvTitle;
//    private TextView tvTime;
//    private Button delete;
//    private Button edit;

    private List<Post_ListView> post_list;
    private Context context;
    private MyApp app;
    private ProfileActivity activity;

    private int resourcedID;

    public AdapterPost(Context context, int textViewResourcedId, List<Post_ListView> objects){
        super(context, textViewResourcedId, objects);
        post_list = objects;
        resourcedID = textViewResourcedId;
        activity = (ProfileActivity) context;
        this.context = context;
        post_list = MainActivity.post_list;
    }

    @Override
    public int getCount() {
        return post_list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        Post_ListView post = post_list.get(position);
        System.out.println(post_list.get(0).getUser().getUsername());

        View view;
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourcedID, parent, false);

            holder.ivImg = (ImageView) view.findViewById(R.id.iv_post_listview_img);
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_post_listview_title);
            holder.tvBody = (TextView) view.findViewById(R.id.tv_post_listview_body);
            holder.tvTime = (TextView) view.findViewById(R.id.tv_post_listview_edited);
            holder.delete = (Button) view.findViewById(R.id.post_button_delete);
            holder.edit = (Button) view.findViewById(R.id.post_button_edit);
            view.setTag(holder);

        }
        else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.ivImg.setImageResource(R.drawable.img);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
        holder.tvTime.setText(post.getDate());

        //设定删除键的监听
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post_list.remove(position);
                notifyDataSetChanged();

                Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();

            }
        });
        //设定编辑键的监听，在这里插入一个跳转
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     Intent intent = new Intent(activity, SendActivity.class);
                      activity.startActivity(intent);
            }
        });
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MainActivity.class);


                //add the content that get from the server!

                intent.putExtra("val", true);
                activity.startActivity(intent);
            }
        });

        return view;

    }

    class ViewHolder{
        ImageView ivImg;
        TextView tvTitle;
        TextView tvBody;
        TextView tvTime;
        Button delete;
        Button edit;
    }
}
