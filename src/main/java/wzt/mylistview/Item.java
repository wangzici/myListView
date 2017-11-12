package wzt.mylistview;

/**
 * Created by Tao on 2017/11/11.
 */

public class Item {
    public static final int TYPE_NOMAL = 0;
    public static final int TYPE_ENTER = 1;
    public static final int TYPE_TIME = 2;

    private int titleId;
    private int contentId;
    private int type;

    public Item(int titleId, int contentId, int type){
        this.titleId = titleId;
        this.contentId = contentId;
        this.type = type;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getContentId() {
        return contentId;
    }

    public int getType() {
        return type;
    }

}
