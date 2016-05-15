package me.warer.caros;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import me.warer.caros.UserDao;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;
import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class Welcomeactivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private TextView mTextView02;
    private TextView mTextView03;
    private TextView mTextView04;
    int Choose=2;
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

    private View mContentView;
    private View mControlsView;
    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcomeactivity);


        if(UserDao.getFirstrun(getApplicationContext())==true)
        {UserDao.setUserName(1,"1号驾驶员",getApplicationContext());
            UserDao.setUserName(2,"2号驾驶员",getApplicationContext());
            UserDao.setUserName(3,"3号驾驶员",getApplicationContext());
        }

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        // mContentView = findViewById(R.id.fullscreen_content);
        mTextView02 = (TextView) findViewById(R.id.textView20);
        mTextView02.setText(UserDao.getUserName(1,getApplicationContext()));
        mTextView03 = (TextView) findViewById(R.id.textView21);
        mTextView03.setText(UserDao.getUserName(2,getApplicationContext()));
        mTextView04 = (TextView) findViewById(R.id.textView22);
        mTextView04.setText(UserDao.getUserName(3,getApplicationContext()));
        int DefaultUser =0;
        DefaultUser=UserDao.getDefaultUser(getApplicationContext());
        if(DefaultUser !=0)
        { Intent it = new Intent(Welcomeactivity.this,Mainactivity.class);
            it.putExtra("User",DefaultUser);
            startActivity(it);}



        ImageButton imagebutton2 = (ImageButton) findViewById(R.id.imageButton21);
        imagebutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it = new Intent(Welcomeactivity.this,Mainactivity.class);
                it.putExtra("User",1);
                startActivity(it);
            }
        });
        ImageButton imagebutton3 = (ImageButton) findViewById(R.id.imageButton22);
        imagebutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it = new Intent(Welcomeactivity.this,Mainactivity.class);
                it.putExtra("User",2);
                startActivity(it);
            }
        });
        ImageButton imagebutton4 = (ImageButton) findViewById(R.id.imageButton23);
        imagebutton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it = new Intent(Welcomeactivity.this,Mainactivity.class);
                it.putExtra("User",3);
                startActivity(it);
            }
        });


        UserDao.setFirstrun(false,getApplicationContext());
    }


    // Set up the user interaction to manually show or hide the system UI.


    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);

    }

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

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

        }
    };

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

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

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
}
