package fragment;

/**
 * Created by nicole on 2018-02-26.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jiani.login.activity.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterFav;
import application.MyApp;
import entity.Fav_ListView;
import com.example.jiani.login.R;


public class ProfileFavorite_Fragment extends Fragment {

    private ListView listViewFav;
    private List<Fav_ListView> fav_list = new ArrayList<>();
    private List<Fragment> fragments;
    private MyApp app;
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
