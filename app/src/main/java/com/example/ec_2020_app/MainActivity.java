package com.example.ec_2020_app;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ec_2020_app.adapter.EventsAdapter2;
import com.example.ec_2020_app.adapter.RoundStoryAdapter;
import com.example.ec_2020_app.adapter.ViewPagerAdapter;
import com.example.ec_2020_app.story.stories_main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    Button log_out;
    TextView show_name,show_mobile,show_college,show_email;
    DatabaseReference reff;
    Timer timer;
    Context ctx;
    int currentPosition=0;
    ViewPager viewPager;
    LinearLayout linearLayout;
    RecyclerView stories;

    static ArrayList<ArrayList<String>> sortedAllLinks;//Get value of all links from database
    static ArrayList<String> sortedFrom;//Get value from database
    static ArrayList<ArrayList<String>> sortedTime;//Format - "dd-mm-yyyy hh:ss"//24hrs system
    static ArrayList<Boolean> sortedState;//True if there is new story from a specific club
    static int sortedPosition[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new homepage()).commit();
        }
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
      //  log_out = findViewById(R.id.log_out);
            getSupportActionBar().hide();
     //   show_name = findViewById(R.id.fetch_name);
     //   show_college = findViewById(R.id.fetch_college);
    //    show_email = findViewById(R.id.fetch_email);
     //   show_mobile = findViewById(R.id.fetch_mobile);

        galleryViewPager();

        //RecyclerView recyclerView = findViewById(R.id.recycler1);
        stories=findViewById(R.id.recycler1);
        stories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        RecyclerView recyclerView2 = findViewById(R.id.recyclyer2);
        ctx=this;
        storyProcessor();
        stories.setAdapter(new RoundStoryAdapter(this,sortedFrom,sortedState));
        stories.addOnItemTouchListener(
                new RecyclerItemClickListener(ctx, stories ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        storyOnClick(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setAdapter(new EventsAdapter2());


        final String mmobile = getIntent().getStringExtra("mmobile");

        String datta = "rtr";

            if (mmobile.isEmpty())
            {
                Log.e("TAG","alpha");
                Toast.makeText(MainActivity.this, "You should first sign up and then come", Toast.LENGTH_LONG).show();
                Intent goto_signup = new Intent(MainActivity.this, sign_up.class);
                startActivity(goto_signup);
            }

            if(!mmobile.equals(datta))
        {

            Log.e("TAG","betA");
            Toast.makeText(this, mmobile, Toast.LENGTH_LONG).show();

          reff = FirebaseDatabase.getInstance().getReference().child("users").child(mmobile);
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String temp_name = dataSnapshot.child("full_name").getValue().toString();
                        String temp_college = dataSnapshot.child("college").getValue().toString();
                        String temp_email = dataSnapshot.child("email").getValue().toString();
                        String temp_mobile = dataSnapshot.child("mobile_no").getValue().toString();

                        // show_name.setText(temp_name);
                        //  show_college.setText(temp_college);
                        //  show_email.setText(temp_email);
                        //  show_mobile.setText(temp_mobile);
                    } else {
                        // show_email.setText("You should first sigu up and then come");
                        Toast.makeText(MainActivity.this, "You should first sign up and then come", Toast.LENGTH_LONG).show();
                        Intent goto_signup = new Intent(MainActivity.this, sign_up.class);
                        startActivity(goto_signup);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Sorry try again", Toast.LENGTH_SHORT).show();
                }

            });

        }


    /*    log_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(MainActivity.this, "Logging Out !!", Toast.LENGTH_SHORT).show();
                    Intent go_to_login = new Intent(MainActivity.this, login.class);
                    startActivity(go_to_login);
                }
            }
        });*/
    }
private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch(item.getItemId())
                {
                    case R.id.home_icon:
                        fragment=new homepage();
                        break;
                    case R.id.tickets_icon:
                        fragment=new tickets();
                        break;
                    case R.id.schedule_icon:
                        fragment=new schedule();
                        break;
                    case R.id.developers_icon:
                        fragment=new developers();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
                return true;
            }
        };
    private void galleryViewPager() {

        linearLayout = findViewById(R.id.dotsIndicator);
        viewPager = findViewById(R.id.gallery);

        viewPager.setAdapter(new ViewPagerAdapter(this));


        createSlideShow();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition=position;
            }

            @Override
            public void onPageSelected(int position) {
                prepareDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void createSlideShow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(currentPosition==5)
                    currentPosition=0;
                viewPager.setCurrentItem(currentPosition++,true);


            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },0,1000);

    }

    private void prepareDots(int currentSlidePosition) {
        if(linearLayout.getChildCount()>0)
            linearLayout.removeAllViews();

        ImageView dots[] = new ImageView[5];

        for(int i=0;i<5;i++)
        {
            dots[i] = new ImageView(this);
            if(i==currentSlidePosition)
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.activedots));
            else
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactivedots));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4,0,4,0);
            linearLayout.addView(dots[i],layoutParams);

        }
    }

    private void storyProcessor(){
        ArrayList<String> seenlinks=sharedlinkstolist();//Contains all seen story links, has to be stored as SharedPreferences
        StringTokenizer str=new StringTokenizer("");
        ArrayList<ArrayList<String>> alllinks=new ArrayList<>();//Get value of all links from database
        ArrayList<String> from=new ArrayList<>();//Get value from database
        ArrayList<ArrayList<String>> time=new ArrayList<>();//Format - "dd-mm-yyyy hh:ss"//24hrs system
        ArrayList<Boolean> state=new ArrayList<>();//True if there is new story from a specific club
        ArrayList<Integer> position=new ArrayList<>();//Stores position from where stories have to be started

        //SampleData
        from.add("Manan");
        from.add("Ananya");
        from.add("IEEE");

        ArrayList<String> pp=new ArrayList<>();
        pp.add("https://cdn.pixabay.com/photo/2020/03/07/05/18/coffee-4908764_960_720.jpg");
        pp.add("https://cdn.pixabay.com/photo/2020/03/04/05/57/key-4900643_960_720.jpg");
        alllinks.add(pp);
        ArrayList<String> bb=new ArrayList<>();
        bb.add("https://images.pexels.com/photos/1366630/pexels-photo-1366630.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        bb.add("https://images.pexels.com/photos/1624496/pexels-photo-1624496.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        bb.add("https://cdn.pixabay.com/photo/2019/09/28/23/55/girl-4512051_960_720.jpg");
        bb.add("https://cdn.pixabay.com/photo/2020/03/05/20/49/flowers-4905460_960_720.jpg");
        alllinks.add(bb);
        ArrayList<String> cc=new ArrayList<>();
        cc.add("https://cdn.pixabay.com/photo/2020/03/06/18/21/native-american-4907816_960_720.png");
        cc.add("https://images.pexels.com/photos/1067333/pexels-photo-1067333.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        cc.add("https://images.pexels.com/photos/775199/pexels-photo-775199.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        alllinks.add(cc);

        pp=new ArrayList<>();
        pp.add("Yesterday, 11:00 p.m.");
        pp.add("Today, 09:00 a.m.");
        time.add(pp);
        bb=new ArrayList<>(pp);
        bb.add("Today, 11:00 a.m.");
        bb.add("Today, 01:00 p.m. ");
        time.add(bb);
        cc=new ArrayList<>(pp);
        cc.add("Today 10:00 a.m.");
        time.add(cc);

        //Sample Data Ends


        for(int i=0;i<alllinks.size();i++){
            boolean _new=false;
            for (int j=0;j<alllinks.get(i).size();j++){
                String link=alllinks.get(i).get(j);
                boolean check=!(seenlinks.contains(link));
                if(!_new&&check)position.add(j);
                if(check)_new=true;
            }
            if(!_new)position.add(0);
            state.add(_new);
        }

        sortedAllLinks=new ArrayList<>();//Get value of all links from database
        sortedFrom=new ArrayList<>();//Get value from database
        sortedTime=new ArrayList<>();//Format - "dd-mm-yyyy hh:ss"//24hrs system
        sortedState=new ArrayList<>();//True if there is new story from a specific club
        sortedPosition=new int[alllinks.size()];//Stores position from where stories have to be started
        int count=0;

        for(int i=0;i<alllinks.size();i++){
            if(state.get(i)){
                sortedAllLinks.add(alllinks.get(i));
                sortedFrom.add(from.get(i));
                sortedTime.add(time.get(i));
                sortedState.add(state.get(i));
                sortedPosition[count]=position.get(i);
                count++;
            }
        }
        for(int i=0;i<alllinks.size();i++){
            if(!state.get(i)){
                sortedAllLinks.add(alllinks.get(i));
                sortedFrom.add(from.get(i));
                sortedTime.add(time.get(i));
                sortedState.add(state.get(i));
                sortedPosition[count]=position.get(i);
                count++;
            }
        }
    }

    private void storyOnClick(int position){
        Intent it=new Intent(ctx, stories_main.class);
        it.putExtra("url",sortedAllLinks);
        it.putExtra("allIndex",sortedPosition);
        it.putExtra("index",position);
        it.putExtra("from",sortedFrom);
        it.putExtra("time",sortedTime);

        startActivityForResult(it,0);
    }

    private ArrayList<String> sharedlinkstolist(){
        ArrayList<String> visitedlinks=new ArrayList<>();
        String newsaved="";
        StringTokenizer str=new StringTokenizer(SaveSharedPreference.getVisitedlinks(ctx).trim());
        for(int i=0;str.hasMoreTokens();i++){
            String a=str.nextToken();
            if(!visitedlinks.contains(a)) {
                visitedlinks.add(a);
                newsaved=newsaved+a+" ";
            }
        }
        SaveSharedPreference.setVisitedlinks(ctx,newsaved);
        return visitedlinks;
    }

    @Override
    protected void onResume() {
        super.onResume();
        storyProcessor();
        stories.setAdapter(new RoundStoryAdapter(this,sortedFrom,sortedState));
        stories.addOnItemTouchListener(
                new RecyclerItemClickListener(ctx, stories ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        storyOnClick(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }
}
