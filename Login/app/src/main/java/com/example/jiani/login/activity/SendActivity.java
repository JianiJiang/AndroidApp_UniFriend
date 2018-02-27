package com.example.jiani.login.activity;

/**
 * Created by nicole on 2018-02-26.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jiani.login.R;

import entity.Course;
import fragment.Comment_Fragment;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class SendActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button post;
    private Button cancel;
    private EditText title;
    private EditText etPost;
    private CheckBox checkBox;
    private Spinner spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_send);
        spinner = (Spinner) findViewById(R.id.sp_activity_send_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for(Course c : MainActivity.courses) {
            adapter.add(c.getCourseCode());
        }

        spinner.setAdapter(adapter);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_send);
        toolbar.setTitle("Create Your Post");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_send);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.id_send_menu);
        navigationView.setNavigationItemSelectedListener(this);

        Button post = (Button) findViewById(R.id.Post);
        Button cancel = (Button) findViewById(R.id.Cancel);

         title = (EditText)findViewById(R.id.et_send_activity_title) ;
         etPost = (EditText)findViewById(R.id.et_send_activity_post) ;
         checkBox= (CheckBox) findViewById(R.id.cb_activity_send_checkbox);


        title.setSingleLine(false);
        title.setHorizontallyScrolling(false);
        etPost.setHorizontallyScrolling(false);
        etPost.setSingleLine(false);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputTitle = title.getText().toString();
                String inputBody = etPost.getText().toString();
                String inputKeywords = spinner.getSelectedItem().toString();

                Calendar c = Calendar.getInstance();
                Date curDate = c.getTime();
                c.setTime(curDate);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);


                String inputTime = String.format(year+"-"+month+"-"+day+"-"+" "+hour+":"+minute+":"+second);
                Toast.makeText(SendActivity.this, "Post Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SendActivity.this, MainActivity.class);
                intent.putExtra("title",inputTitle);
                intent.putExtra("body",inputBody);
                intent.putExtra("time",inputTime);
                intent.putExtra("keywords",inputKeywords);
                intent.putExtra("val", true);

                if (checkBox.isChecked()){
                    intent.putExtra("name","Anonymous Post");
                }else {
                    intent.putExtra("name","Jiani Jiang");
                    //fake name!! need to get the name info from the server!!
                }
                startActivity(intent);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SendActivity.this);
                dialog.setTitle("Cancel Editing");
                dialog.setMessage("Do you really want to quit editing?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(SendActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SendActivity.this, "You have canceled.", Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.show();

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_homepage){
            Intent intent = new Intent(SendActivity.this, MainActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_profile){
            Intent intent = new Intent(SendActivity.this, ProfileActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_post){

        }else if (id == R.id.nav_logout){
            final AlertDialog.Builder dialog = new AlertDialog.Builder(SendActivity.this);
            dialog.setTitle("Attention");
            dialog.setMessage("Do you really want to logout?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(SendActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SendActivity.this, LoginActivity.class);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_send);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}

