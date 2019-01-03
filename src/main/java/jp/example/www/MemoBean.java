package jp.example.www;

import java.io.Serializable;

public class MemoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public MemoBean() {
    }

    private String title;
    private String memo;
    private String modify;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }
}
