package fragment;

/**
 * Created by nicole on 2018-02-26.
 */

import android.app.Fragment;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiani.login.activity.LoginActivity;
import com.example.jiani.login.activity.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterFav;
import application.MyApp;
import entity.Fav_ListView;
import com.example.jiani.login.R;

import static com.example.jiani.login.activity.MainActivity.fav_list;


public class ProfileFavorite_Fragment extends Fragment {

    private ListView listViewFav;
    private List<Fragment> fragments;
    private MyApp app;
    private RequestQueue myQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View favorite = inflater.inflate(R.layout.layout_listview_favorite, container, false);
        ProfileActivity activity = (ProfileActivity) getActivity();
        this.fragments = activity.fragments;



        AdapterFav adapterFav = new AdapterFav(getActivity(), R.layout.listview_item_favorite,fav_list);
        listViewFav = (ListView) favorite.findViewById(R.id.listview_favorite);
        listViewFav.setAdapter(adapterFav);
//        app.getList();
        adapterFav.notifyDataSetChanged();

        return favorite;

    }
}
