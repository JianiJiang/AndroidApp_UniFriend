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
import entity.Fav_ListView;
import com.example.jiani.login.R;

import com.example.jiani.login.activity.MainActivity;
import com.example.jiani.login.activity.ProfileActivity;

/**
 * Created by huangshimin on 2018/2/25.
 */

public class AdapterFav extends ArrayAdapter<Fav_ListView> {
    private ProfileActivity activity;
    private List<Fav_ListView> fav_list;
    private Context context;
    private MyApp app;

    private int resourcedID;

    public AdapterFav(Context context, int textViewResourcedId, List<Fav_ListView> objects) {
        super(context, textViewResourcedId, objects);
        fav_list = objects;
        resourcedID = textViewResourcedId;
        activity = (ProfileActivity) context;
        this.context = context;
        fav_list = MainActivity.fav_list;
    }

    @Override
    public int getCount() {
        return fav_list.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Fav_ListView fav = getItem(position);
        View view;
        AdapterFav.ViewHolder1 holder = null;
        if (convertView == null) {
            holder = new AdapterFav.ViewHolder1();
            view = LayoutInflater.from(getContext()).inflate(resourcedID, parent, false);
            holder.ivImg = (ImageView) view.findViewById(R.id.iv_item_favourite_img);
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_item_favourite_title);
            holder.tvBody = (TextView) view.findViewById(R.id.tv_item_favourite_body);
            holder.tvTime = (TextView) view.findViewById(R.id.tv_item_favourite_edited);
            holder.delete = (Button) view.findViewById(R.id.button_delete1);

            view.setTag(holder);

        } else {
            view = convertView;
            holder = (AdapterFav.ViewHolder1) view.getTag();

        }
        holder.ivImg.setImageResource(fav.getPost().getImageId());
        holder.tvTitle.setText(fav.getPost().getTitle());
        holder.tvBody.setText(fav.getPost().getBody());
        holder.tvTime.setText(fav.getPost().getDate());

        //设定监听delete按键
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fav_list.remove(position);
                notifyDataSetChanged();

                Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();
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

    class ViewHolder1 {
        ImageView ivImg;
        TextView tvTitle;
        TextView tvBody;
        TextView tvTime;
        Button delete;
    }
}

