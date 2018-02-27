package com.example.jiani.login.activity;

/**
 * Created by nicole on 2018-02-26.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import com.example.jiani.login.R;

import fragment.Favorite;
import fragment.Post;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Post postFragment;
    private Favorite favoriteFragment;

    private View postLayout;
    private View favoriteLayout;

    private TextView postText;
    private TextView favoriteText;

    private FragmentManager fragmentManager;
    public List<Fragment> fragments;

    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        navigationView = (NavigationView)findViewById(R.id.id_nv_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);




    }

    private void initViews() {
        postLayout = findViewById(R.id.post_layout);
        favoriteLayout = findViewById(R.id.favorite_layout);
        postText = (TextView) findViewById(R.id.post_text);
        favoriteText = (TextView) findViewById(R.id.favorite_text);
        postLayout.setOnClickListener(this);
        favoriteLayout.setOnClickListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.nav_homepage){
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_profile){

        }else if (id == R.id.nav_post){
            Intent intent = new Intent(ProfileActivity.this, SendActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_logout){
            final AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
            dialog.setTitle("Attention");
            dialog.setMessage("Do you really want to logout?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(ProfileActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.post_layout:
                setTabSelection(0);
                break;
            case R.id.favorite_layout:
                setTabSelection(1);
                break;
            default:
                break;
        }
    }




    private void setTabSelection(int index){
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch(index){
            case 0:
                postText.setTextColor(Color.BLACK);
                if(postFragment == null){
                    postFragment = new Post();
                    transaction.add(R.id.content, postFragment);
                }else{
                    transaction.show(postFragment);
                }
                break;
            case 1:
                favoriteText.setTextColor(Color.BLACK);
                if(favoriteFragment == null){
                    favoriteFragment = new Favorite();
                    transaction.add(R.id.content, favoriteFragment);
                }else{
                    transaction.show(favoriteFragment);
                }
                break;
        }
        transaction.commit();

    }

    private void clearSelection(){
        postText.setTextColor(Color.parseColor("#82858b"));
        favoriteText.setTextColor(Color.parseColor("#82858b"));
    }

    private void hideFragments(FragmentTransaction transaction){
        if(postFragment != null){
            transaction.hide(postFragment);
        }
        if (favoriteFragment != null){
            transaction.hide(favoriteFragment);
        }
    }


}
