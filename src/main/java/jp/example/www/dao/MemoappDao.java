package jp.example.www.dao;

import java.util.List;

import jp.example.www.MemoBean;

public interface MemoappDao {
    List<MemoBean> getMemos();

    void save(MemoBean memo);
}
