package com.finaltest.startfit.calender.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Memo.class}, version = 1, exportSchema = false)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract MemoDao memoDao();

    private static MemoDatabase INSTANCE;

    public static MemoDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            RoomDatabase.Builder<MemoDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                    MemoDatabase.class, "SwMemo.db");
            builder.allowMainThreadQueries();
            INSTANCE = builder.build();
        }
        return INSTANCE;
    }
}
