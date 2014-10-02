package za.co.morristech.dentalmanager.app.provider;

import android.accounts.Account;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import za.co.morristech.dentalmanager.app.util.AccountUtils;

import static za.co.morristech.dentalmanager.app.provider.DatabaseContract.FeedbackColumns;
import static za.co.morristech.dentalmanager.app.provider.DatabaseContract.Room;
import static za.co.morristech.dentalmanager.app.provider.DatabaseContract.Sessions;
import static za.co.morristech.dentalmanager.app.provider.DatabaseContract.SyncColumns;
import static za.co.morristech.dentalmanager.app.util.LogUtils.LOGD;
import static za.co.morristech.dentalmanager.app.util.LogUtils.LOGI;
import static za.co.morristech.dentalmanager.app.util.LogUtils.LOGW;
import static za.co.morristech.dentalmanager.app.util.LogUtils.makeLogTag;

/**
 * @author Global Kinetic
 */
public class DentalDatabase extends SQLiteOpenHelper {

    private static final String TAG = makeLogTag(DentalDatabase.class);

    private static final String DATABASE_NAME = "dentalmanager.db";

    private static final int VER_2014_RELEASE_1 = 1;
    private static final int CUR_DATABASE_VERSION = VER_2014_RELEASE_1;

    private final Context mContext;

    interface Tables {
        String PATIENT = "patient";
        String FEEDBACK = "feedback";
        String MY_SCHEDULE = "myschedule";
        String ROOM = "rooms";
        String SESSIONS = "sessions";
    }

    private interface Triggers {
    }

    /**
     * Fully-qualified field names.
     */
    private interface Qualified {
    }

    /**
     * {@code REFERENCES} clauses.
     */
    private interface References {
        String ROOM_ID = "REFERENCES " + Tables.ROOM + "(" + Room.ROOM_ID + ")";
        String SESSION_ID = "REFERENCES " + Tables.SESSIONS + "(" + Sessions.SESSION_ID + ")";
    }

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DentalDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        //FeedBack Create
        db.execSQL("CREATE TABLE " + Tables.FEEDBACK + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + DatabaseContract.Sessions.SESSION_ID + " TEXT " + References.SESSION_ID + ","
                + DatabaseContract.FeedbackColumns.SESSION_RATING + " INTEGER NOT NULL,"
                + FeedbackColumns.ANSWER_RELEVANCE + " INTEGER NOT NULL,"
                + FeedbackColumns.ANSWER_CONTENT + " INTEGER NOT NULL,"
                + FeedbackColumns.ANSWER_SPEAKER + " INTEGER NOT NULL,"
                + FeedbackColumns.COMMENTS + " TEXT,"
                + FeedbackColumns.SYNCED + " INTEGER NOT NULL DEFAULT 0)");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LOGD(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);

        Account account = AccountUtils.getActiveAccount(mContext);
        if (account != null) {
            //TODO : Cancel any sync currently in progress
        }

        // Current DB version. We update this variable as we perform upgrades to reflect
        // the current version we are in.
        int version = oldVersion;

        // Indicates whether the data we currently have should be invalidated as a
        // result of the db upgrade. Default is true (invalidate); if we detect that this
        // is a trivial DB upgrade, we set this to false.
        boolean dataInvalidated = true;

        // at this point, we ran out of upgrade logic, so if we are still at the wrong
        // version, we have no choice but to delete everything and create everything again.
        if (version != CUR_DATABASE_VERSION) {
            LOGW(TAG, "Upgrade unsuccessful -- destroying old data during upgrade");

            db.execSQL("DROP TABLE IF EXISTS " + Tables.FEEDBACK);

            onCreate(db);
            version = CUR_DATABASE_VERSION;
        }

        if (dataInvalidated) {
            LOGD(TAG, "Data invalidated; resetting our data timestamp.");
            if (account != null) {
                LOGI(TAG, "DB upgrade complete. Requesting resync.");
                //SyncHelper.requestManualSync(account);
            }
        }
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
