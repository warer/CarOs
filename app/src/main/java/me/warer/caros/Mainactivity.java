package me.warer.caros;
import android.content.pm.ActivityInfo;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Mainactivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            //  mControlsView.setVisibility(View.VISIBLE);
        }
    };
    int User=0;
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    // @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainactivity);

        Intent intent = getIntent();
        User = intent.getIntExtra("User",User);
        Toast.makeText(Mainactivity.this,"当前账户:"+UserDao.getUserName(User,getApplicationContext()),Toast.LENGTH_SHORT).show();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mVisible = true;
       if(UserDao.getAutomode(User,getApplicationContext())==1) {

           int[] ChairAngle = UserDao.getChairAngle(User, getApplicationContext());
           int ChairAngleX = ChairAngle[0];
           int ChairAngleY = ChairAngle[1];
           OBDDao.setChair(ChairAngleX, ChairAngleY);


           int[] MirrorAngle = UserDao.getMirrorAngle(User, getApplicationContext());
           int MirrorAngleX = MirrorAngle[0];
           int MirrorAngleY = MirrorAngle[1];
           OBDDao.setMirror(MirrorAngleX, MirrorAngleY);
       }

        int Drivemode=UserDao.getDriveMode(User,getApplicationContext());
        OBDDao.setDrivemode(Drivemode);
        int Suspenmode=UserDao.getSupensMode(User,getApplicationContext());
        OBDDao.setSuspensmode(Suspenmode);


        ImageButton imagebutton1 = (ImageButton) findViewById(R.id.imageButton1);
         imagebutton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
            Intent it1 = new Intent(Mainactivity.this, Accountactivity.class);
            startActivity(it1);
        }


        });



        ImageButton imagebutton2 = (ImageButton) findViewById(R.id.imageButton2);
        imagebutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Uri uri = Uri.parse("file:///sdcard/a.mp3");
               Intent it2 = new Intent(Intent.ACTION_VIEW,uri);
                it2.setDataAndType(uri , "audio/mp3");
               startActivity(it2);
            }
        });


        ImageButton imagebutton3 = (ImageButton) findViewById(R.id.imageButton3);
        imagebutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it1 = new Intent(Mainactivity.this, FMActivity.class);
                startActivity(it1);

            }
        });


        ImageButton imagebutton4 = (ImageButton) findViewById(R.id.imageButton4);
        imagebutton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Uri URI4=Uri.parse("http://maps.google.com/maps");
                Intent it4 = new Intent(Intent.ACTION_VIEW,URI4);
                startActivity(it4);
            }
        });




        ImageButton imagebutton5 = (ImageButton) findViewById(R.id.imageButton5);
        imagebutton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it5 = new Intent(Mainactivity.this, Caractivity.class);
                it5.putExtra("User",User);
                startActivity(it5);

            }
        });




        ImageButton imagebutton6 = (ImageButton) findViewById(R.id.imageButton6);
        imagebutton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it6 = new Intent(Mainactivity.this, Settingactivity.class);
                it6.putExtra("User",User);
                startActivity(it6);
            }
        });
    }


        //    @Override
            protected void onPostCreate(Bundle savedInstanceState) {
                super.onPostCreate(savedInstanceState);

                // Trigger the initial hide() shortly after the activity has been
                // created, to briefly hint to the user that UI controls
                // are available.
                //delayedHide(100);
            }

            // private void toggle() {
            //      if (mVisible) {
            //        hide();
            //     } else {
            //         show();
            //     }
            // }

            private void hide() {
                // Hide UI first
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }
                //  mControlsView.setVisibility(View.GONE);
                mVisible = false;

                // Schedule a runnable to remove the status and navigation bar after a delay
                mHideHandler.removeCallbacks(mShowPart2Runnable);
                mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
            }

            @SuppressLint("InlinedApi")
            private void show() {
                // Show the system bar
                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                mVisible = true;

                // Schedule a runnable to display UI elements after a delay
                mHideHandler.removeCallbacks(mHidePart2Runnable);
                mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
            }

            /**
             * Schedules a call to hide() in [delay] milliseconds, canceling any
             * previously scheduled calls.
             */
            private void delayedHide(int delayMillis) {
                mHideHandler.removeCallbacks(mHideRunnable);
                mHideHandler.postDelayed(mHideRunnable, delayMillis);
            }
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
    protected void onClose(){
        int[] ChairAngle = OBDDao.getChair();
        int ChairAngleX = ChairAngle[0];
        int ChairAngleY = ChairAngle[1];
        UserDao.setChairAngle(User,ChairAngleX,ChairAngleY,getApplicationContext());
        int[] MirrorAngle = OBDDao.getMirror();
        int MirrorAngleX = MirrorAngle[0];
        int MirrorAngleY = MirrorAngle[1];
        UserDao.setMirrorAngle(User,MirrorAngleX,MirrorAngleY,getApplicationContext());

    }

        }