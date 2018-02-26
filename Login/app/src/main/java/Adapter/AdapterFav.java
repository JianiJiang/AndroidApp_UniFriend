package Adapter;

/**
 * Created by nicole on 2018-02-26.
 */
import android.content.Context;
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
        this.context = context;
        initPost();
    }

    private void initPost() {
        for (int i = 0; i < 7; i++) {
            Fav_ListView post1 = new Fav_ListView("Review about course IK2206", "I think it is interesting", R.drawable.img, "Last edited: 2018-02-01");
            fav_list.add(post1);
            Fav_ListView post2 = new Fav_ListView("Don't take this course", "I think it is bad", R.drawable.img, "Last edited: 2018-02-03");
            fav_list.add(post2);
            Fav_ListView post3 = new Fav_ListView("A big title", "Key word:blablabla", R.drawable.img, "Last edited: 2018-02-05");
            fav_list.add(post3);
            Fav_ListView post4 = new Fav_ListView("A big title", "Key word:blablabla", R.drawable.img, "Last edited: 2018-02-10");
            fav_list.add(post4);
        }
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
        holder.ivImg.setImageResource(fav.getImageId());
        holder.tvTitle.setText(fav.getTitle());
        holder.tvBody.setText(fav.getBody());
        holder.tvTime.setText(fav.getTime());

        //设定监听delete按键
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fav_list.remove(position);
                notifyDataSetChanged();

                Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();
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

