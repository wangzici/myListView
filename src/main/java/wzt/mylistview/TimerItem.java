package wzt.mylistview;

/**
 * Created by Tao on 2017/11/12.
 */

public class TimerItem extends Item {
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
    public TimerItem(int titleId) {
        super(titleId, -1, Item.TYPE_TIME);
    }
}
