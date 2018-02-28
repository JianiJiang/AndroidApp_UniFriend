package com.example.jiani.login.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiani.login.R;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import entity.Course;
import entity.Fav_ListView;
import entity.Post_ListView;
import fragment.Mainpage_Fragment;
import fragment.Comment_Fragment;

import static com.example.jiani.login.activity.LoginActivity.user;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private DrawerLayout mDrawerlayout;
    private ArrayAdapter arrayAdapter;
    public List<Fragment> fragments;
    boolean value = false;
    public static List<Course> courses = new ArrayList<>();
    private RequestQueue myQueue;
    public static List<Post_ListView> post_list = new ArrayList<>();
    public static List<Fav_ListView> fav_list = new ArrayList<>();
    public static List<Post_ListView> homepagePost_list = new ArrayList<>();
    public static Post_ListView postDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar.setTitle("UniFriends");
       setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragments = new ArrayList<>();
        NavigationView navigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragmentMain = new Mainpage_Fragment();
        fragments.add(fragmentMain);
        Fragment fragmentPost = new Comment_Fragment();
        fragments.add(fragmentPost);

        replaceFragment(fragments.get(0));


        loadCourses();
        HomepagePost();
        ProfilePosts();
        ProfileFav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        value = intent.getBooleanExtra("val",false);

        if ( value == true)
        {
            replaceFragment(fragments.get(1));
        }
    }

    public void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {

        int id = item.getItemId();
        if (id == R.id.nav_homepage) {
            replaceFragment(new Mainpage_Fragment());

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_post) {
            Intent intent = new Intent(MainActivity.this, SendActivity.class);

            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("Attention");
            dialog.setMessage("Do you really want to logout?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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



private void loadCourses(){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
            .permitAll().build();
    StrictMode.setThreadPolicy(policy);

    final String url = "https://www.kth.se/api/kopps/v1/courseRounds/2018:1";

    String filePath = getApplicationContext().getFilesDir().getPath().toString() + "/test.xml";
    File f = new File(filePath);
    try {
        URL request = new URL(url);
        FileUtils.copyURLToFile(request, f, 10000, 10000);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(f);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("courseRound");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                courses.add(new Course(eElement.getAttribute("courseCode")));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void HomepagePost() {
    myQueue = Volley.newRequestQueue(this);

    String url = "http://213.89.22.179:8080/api/rest/posts";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            List<Post_ListView> temp = Arrays.asList(JSON.parseObject(response, Post_ListView[].class));
            for (Post_ListView h : temp) {
                Post_ListView home = new Post_ListView(h.getId(), h.getUser(), h.getTitle(), h.getBody(), h.isAnonymous(), h.getLikes(), h.getDislikes(), h.getDate(), h.getCoursecode());
                homepagePost_list.add(home);
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

public void ProfilePosts(){
    myQueue = Volley.newRequestQueue(this);

    String url ="http://213.89.22.179:8080/api/rest/posts";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Log.i("posts", response);
            List<Post_ListView> temp = Arrays.asList(JSON.parseObject(response, Post_ListView [].class));

            for (Post_ListView p :temp){
                if (p.getUser().getUsername().equals(user.getUsername())){
                    Post_ListView post = new Post_ListView(p.getId(), p.getUser(), p.getTitle(), p.getBody(), p.isAnonymous(), p.getLikes(), p.getDislikes(), p.getDate(), p.getCoursecode());
                    post_list.add(post);
                    //System.out.println(p.getUser().getUsername());
                }
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

public void ProfileFav(){
    myQueue = Volley.newRequestQueue(this);
    String url ="http://213.89.22.179:8080/api/rest/post/profile/favorites/"+LoginActivity.user.getId();
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("favorite", response);
            List<Fav_ListView> temp = Arrays.asList(JSON.parseObject(response, Fav_ListView [].class));

            for (Fav_ListView f :temp){
                    Fav_ListView post = new Fav_ListView(f.getId(),f.getPost(), f.getUser());
                    fav_list.add(f);
                    //System.out.println(p.getUser().getUsername())
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.i("ProfileFav error", volleyError.getMessage());
        }
    });
    myQueue.add(stringRequest);
}



}
