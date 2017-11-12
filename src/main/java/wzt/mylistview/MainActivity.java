package wzt.mylistview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import wzt.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, AdapterView.OnItemSelectedListener {
    ListView mListview;
    MainAdapter mAdapter;
    List<Item> mList;
    TimerItem mTimerItem;
    Timer mTimer;
    SimpleDateFormat mDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        mListview = (ListView) findViewById(R.id.listview);
        mList = new ArrayList<>();
        mTimerItem = new TimerItem(R.string.display);
        mList.add(mTimerItem);
        for(int i = 0 ;i<6;i++) {
            Item item = new Item(R.string.sound, R.string.sound_on, Item.TYPE_NOMAL);
            mList.add(item);
        }
        mAdapter = new MainAdapter(this,mList);
        mListview.setAdapter(mAdapter);
        mListview.setOnKeyListener(this);
        mListview.setOnItemSelectedListener(this);
        if(mTimerItem != null){
            mTimer = new Timer(true);
            mTimer.schedule(timerTask,0,1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i == KeyEvent.KEYCODE_DPAD_DOWN && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            return mAdapter.moveDown();
        }else if(i == KeyEvent.KEYCODE_DPAD_UP && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            return mAdapter.moveUp();
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mAdapter.setIndex(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    Handler mTimeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTimerItem.setTime((String) msg.obj);
            mAdapter.notifyDataSetChanged();
        }
    };

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message msg = mTimeHandler.obtainMessage();
            Date curDate = new Date(System.currentTimeMillis());
            String time = mDateFormat.format(curDate);
            msg.obj = time;
            mTimeHandler.sendMessage(msg);
        }
    };
}
