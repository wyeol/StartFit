package com.finaltest.startfit.calender.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {
    @Insert
    public void insertMemo(Memo memo);

    @Update
    public void updateMemo(Memo memo);

    @Delete
    public void deleteMemo(Memo memo);

    @Query("select * from memo")
    public List<Memo> getAllMemos();

}
