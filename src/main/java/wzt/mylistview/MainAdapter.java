package wzt.mylistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wzt.myapplication.R;

/**
 * Created by Tao on 2017/11/11.
 */

public class MainAdapter extends BaseAdapter {
    private List<Item> mList;
    private List<Item> mListVisible;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mIndex;
    private int mFirstIndex;
    public MainAdapter(Context context,List<Item> mList) {
        this.mList = mList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        if(mList.size() > 5) {
            mListVisible = mList.subList(0, 5);
        }else{
            mListVisible = mList;
        }
        Log.i("zte","mListVisible.toString = " + mListVisible.toString());
        Log.i("zte","mList.toString = " + mList.toString());
    }
    @Override
    public int getCount() {
        return mList.size() > 5 ? 5 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mListVisible.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View v = view;
        if(v == null){
            v = mInflater.inflate(R.layout.list_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = v.findViewById(R.id.title);
            viewHolder.textViewContent = v.findViewById(R.id.content);
            viewHolder.textViewRightIcon = v.findViewById(R.id.right_icon);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) v.getTag();
        }
        Item item = mListVisible.get(i);
        viewHolder.textViewTitle.setText(item.getTitleId());
        if(item.getType() == Item.TYPE_TIME) {
            TimerItem timerItem = (TimerItem) item;
            viewHolder.textViewContent.setText(timerItem.getTime());
        }else{
            viewHolder.textViewContent.setText(item.getContentId());
        }
        if(item.getType() == Item.TYPE_ENTER || item.getType() == Item.TYPE_TIME){
            viewHolder.textViewRightIcon.setVisibility(View.VISIBLE);
        }else{
            viewHolder.textViewRightIcon.setVisibility(View.INVISIBLE);
        }
        return v;
    }

    class ViewHolder{
        TextView textViewTitle;
        TextView textViewContent;
        TextView textViewRightIcon;
    }

    public boolean moveDown(){
        Log.i("zte","[moveDown] mIndex = " + mIndex + "; mFirstIndex = " + mFirstIndex + "; mList.size() = " + mList.size());
        if(mIndex == 4 && mFirstIndex + 5 < mList.size()){
            mFirstIndex++;
            mListVisible = mList.subList(mFirstIndex,mFirstIndex + 5);
            notifyDataSetChanged();
            return true;
        }else{
            return false;
        }
    }

    public boolean moveUp(){
        Log.i("zte","[moveUp] mIndex = " + mIndex + "; mFirstIndex = " + mFirstIndex + "; mList.size() = " + mList.size());
        if(mIndex == 0 && mFirstIndex != 0){
            mFirstIndex--;
            mListVisible = mList.subList(mFirstIndex,mFirstIndex + 5);
            notifyDataSetChanged();
            return true;
        }else{
            return false;
        }
    }

    public void setIndex(int i){
        mIndex = i;
    }
}
