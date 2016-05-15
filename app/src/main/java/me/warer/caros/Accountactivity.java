package me.warer.caros;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

public class Accountactivity extends AppCompatActivity {
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

        setContentView(R.layout.activity_accountactivity);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        // mContentView = findViewById(R.id.fullscreen_content);
        mTextView02 = (TextView) findViewById(R.id.textView10);
        mTextView02.setText(UserDao.getUserName(1,getApplicationContext()));
        mTextView03 = (TextView) findViewById(R.id.textView11);
        mTextView03.setText(UserDao.getUserName(2,getApplicationContext()));
        mTextView04 = (TextView) findViewById(R.id.textView12);
        mTextView04.setText(UserDao.getUserName(3,getApplicationContext()));
        ImageButton imagebutton1 = (ImageButton) findViewById(R.id.imageButton1);
        imagebutton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it = new Intent(Accountactivity.this,Mainactivity.class);
                it.putExtra("User",1);
                startActivity(it);
            }
        });
        ImageButton imagebutton2 = (ImageButton) findViewById(R.id.imageButton2);
        imagebutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it = new Intent(Accountactivity.this,Mainactivity.class);
                it.putExtra("User",2);
                startActivity(it);
            }
        });
        ImageButton imagebutton3 = (ImageButton) findViewById(R.id.imageButton3);
        imagebutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent it = new Intent(Accountactivity.this,Mainactivity.class);
                it.putExtra("User",3);
                startActivity(it);
            }
        });

        Button button1 = (Button) findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                 AlertDialog.Builder dialog = new AlertDialog.Builder(Accountactivity.this).setTitle("选择修改的用户").setSingleChoiceItems(
 new String[] { UserDao.getUserName(1,getApplicationContext()),UserDao.getUserName(2,getApplicationContext()),UserDao.getUserName(3,getApplicationContext())},Choose, new DialogInterface.OnClickListener(){
                             public void onClick(DialogInterface dialog,int which){
                                 Choose=which+1;
                             }
                         });
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    LayoutInflater factory=LayoutInflater.from(Accountactivity.this);
                                    final View DialogView=factory.inflate(R.layout.dialog, null);
                                    AlertDialog dlg= new AlertDialog.Builder(Accountactivity.this)
                                            .setTitle("输入新用户名")
                                            .setView(DialogView)//设置自定义对话框样式
                                            .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                                                public void onClick(DialogInterface dialog,int which){
                                                    EditText et = (EditText)DialogView.findViewById(R.id.insertname);
                                                    UserDao.setUserName(Choose,et.getText().toString(),getApplicationContext());
                                                    mTextView02.setText(UserDao.getUserName(1,getApplicationContext()));
                                                    mTextView03.setText(UserDao.getUserName(2,getApplicationContext()));
                                                    mTextView04.setText(UserDao.getUserName(3,getApplicationContext()));
                                                }
                                            })
                                            .setNegativeButton("取消",null)
                                            .create();
                                    dlg.show();
                                }
                                });
dialog.setNegativeButton("取消", null);

                dialog.show();
                break;
                default:
                break;
            }
        }

        });



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
