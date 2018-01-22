package com.frandog.a20180117_01.data;

import android.content.Context;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentDAOFactory {
//    public static StudentDAO getDAOInstance(Context context, int dbType)  新增DBType後修改成下面那行
public static StudentDAO getDAOInstance(Context context, DBType dbType)
    {
        switch (dbType)
        {
//            case 1:
            case MEMORY:
                return new StudentScoreDAO();
//            case 2:
            case FILE:
                return new StudentFileDAO(context);

            case DB:    //教完20180122_02SQLite資料庫後，將此專案改成寫入SQL裡
                return new StudentDAOBImpl(context);

        }
        return null;
    }
}
