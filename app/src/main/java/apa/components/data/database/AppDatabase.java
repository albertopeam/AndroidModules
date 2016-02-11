package apa.components.data.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by alberto on 11/2/16.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "app_db";
    public static final int VERSION = 1;


}
