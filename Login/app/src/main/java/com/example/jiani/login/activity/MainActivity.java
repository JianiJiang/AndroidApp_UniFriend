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
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;


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
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import entity.Course;
import fragment.Mainpage_Fragment;
import fragment.Comment_Fragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private DrawerLayout mDrawerlayout;
    private ArrayAdapter arrayAdapter;
    public List<Fragment> fragments;
    boolean value = false;
    public static List<Course> courses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar.setTitle("Toolbar");
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


}
