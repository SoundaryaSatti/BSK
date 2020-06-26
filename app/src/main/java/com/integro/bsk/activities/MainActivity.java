package com.integro.bsk.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.integro.bsk.R;
import com.integro.bsk.adapters.ViewPageAdapter;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.integro.bsk.firebase.MyFirebaseMessagingService.NEWS_KEY;
import static com.integro.bsk.firebase.MyFirebaseMessagingService.NOTIFICATION_KEY;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MyFirebaseMsgService";

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter adapter;

    TextView tvNewsletter, tvInitiatives, tvGallery, tvContactUs, tvAddress;
    Button btnMap;
    ImageView ivCall, ivMail, ivFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);
          //if (getSupportActionBar() != null)
           getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvNewsletter = findViewById(R.id.tv_newsletter);
        tvInitiatives = findViewById(R.id.tv_initiatives);
        tvGallery = findViewById(R.id.tv_gallery);
        tvContactUs = findViewById(R.id.tv_contactus);
        ivCall = findViewById(R.id.iv_call);
        ivMail = findViewById(R.id.iv_mail);
        ivFacebook = findViewById(R.id.iv_facebook);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new ViewPageAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);//setText("home")//;
        tabLayout.getTabAt(1).setIcon(R.drawable.newss);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.www1);

        FirebaseMessaging.getInstance().subscribeToTopic("boscoseva");

        boolean isFCMIntent = getIntent().getBooleanExtra(TAG, false);
        if (isFCMIntent) {
            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case NEWS_KEY:
                    viewPager.setCurrentItem(1);
                    break;
                case NOTIFICATION_KEY:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }


        final int colorYellow = ContextCompat.getColor(getApplicationContext(), R.color.colorYello);
        final int colorWhiite = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorYellow, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorWhiite, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContactUs();
            }
        });
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                final CharSequence[] phone = new CharSequence[]{"9440053219"};
                String phone1 = "9440053219";
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null));
                startActivity(intentCall);
            }
        });
        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailintent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject" + " " + "&body" + " " + "&to=" + "bskhyd1993@gmail.com");
                mailintent.setData(data);
                startActivity(mailintent);
            }
        });
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        tvInitiatives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intiativesIntent = new Intent(MainActivity.this, InitiativesActivity.class);
                startActivity(intiativesIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        AlertDialog.Builder builder = alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //System.exit(0);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        //super.onBackPressed();
    }


    public void getContactUs() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dilouge_contact_us, null);
        dialogBuilder.setView(view);
        tvAddress = view.findViewById(R.id.tv_Address);
        btnMap = view.findViewById(R.id.btn_Map);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Coming Soon..!", Toast.LENGTH_SHORT).show();
                String Map = "Bosco Seva Kendra";
                String uri = "https://www.google.com/maps/place/Shadan+Hospital/@17.347801,78.377752,13z/data=!4m5!3m4!1s0x0:0x6f4bde55288fc9fd!8m2!3d17.3518543!4d78.3736039?hl=en-IN" + Map;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
        dialogBuilder.show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}

