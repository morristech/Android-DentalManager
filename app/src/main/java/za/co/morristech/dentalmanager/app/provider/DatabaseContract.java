package za.co.morristech.dentalmanager.app.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;

import za.co.morristech.dentalmanager.app.util.AccountUtils;

/**
 * @author Global Kinetic
 */
public class DatabaseContract {

    //TODO : Add Patient class
    /**
     * Query parameter to create a distinct query.
     */
    public static final String QUERY_PARAMETER_DISTINCT = "distinct";
    public static final String OVERRIDE_ACCOUNTNAME_PARAMETER = "overrideAccount";

    interface FeedbackColumns {
        String SESSION_ID = "session_id";
        String SESSION_RATING = "feedback_session_rating";
        String ANSWER_RELEVANCE = "feedback_answer_q1";
        String ANSWER_CONTENT = "feedback_answer_q2";
        String ANSWER_SPEAKER = "feedback_answer_q3";
        String COMMENTS = "feedback_comments";
        String SYNCED = "synced";
    }

    interface MyScheduleColumns {
        String SESSION_ID = SessionsColumns.SESSION_ID;
        /**
         * Account name for which the session is starred (in my schedule)
         */
        String MY_SCHEDULE_ACCOUNT_NAME = "account_name";
        /**
         * Indicate if last operation was "add" (true) or "remove" (false). Since uniqueness is
         * given by seesion_id+account_name, this field can be used as a way to find removals and
         * sync them with the cloud
         */
        String MY_SCHEDULE_IN_SCHEDULE = "in_schedule";
        /**
         * Flag to indicate if the corresponding in_my_schedule item needs to be synced
         */
        String MY_SCHEDULE_DIRTY_FLAG = "dirty";
    }

    interface RoomColumns {
        /**
         * Unique string identifying this room.
         */
        String ROOM_ID = "room_id";
        /**
         * Name describing this room.
         */
        String ROOM_NAME = "room_name";
        /**
         * Building floor this room exists on.
         */
        String ROOM_FLOOR = "room_floor";
    }

    interface SessionsColumns {
        /**
         * Unique string identifying this session.
         */
        String SESSION_ID = "session_id";
        /**
         * Difficulty level of the session.
         */
        String SESSION_LEVEL = "session_level";
        /**
         * Start time of this track.
         */
        String SESSION_START = "session_start";
        /**
         * End time of this track.
         */
        String SESSION_END = "session_end";
    }

    public interface SyncColumns {
        /**
         * Last time this entry was updated or synchronized.
         */
        String UPDATED = "updated";
    }


    public static final String CONTENT_AUTHORITY = "za.co.morristech.dentalmanager";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_FEEDBACK = "feedback";
    private static final String PATH_MY_SCHEDULE = "my_schedule";
    private static final String PATH_SESSIONS = "sessions";
    private static final String PATH_ROOM = "room";

    public static final String[] TOP_LEVEL_PATHS = {
            PATH_FEEDBACK,
            PATH_MY_SCHEDULE,
            PATH_SESSIONS
    };

    public static final String[] USER_DATA_RELATED_PATHS = {
            PATH_SESSIONS,
            PATH_MY_SCHEDULE
    };

    public static class Feedback implements BaseColumns, FeedbackColumns, SyncColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEEDBACK).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.dentalmanager.session_feedback";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.dentalmanager.session_feedback";

        /**
         * Default "ORDER BY" clause.
         */
        public static final String DEFAULT_SORT = BaseColumns._ID + " ASC, ";

        /**
         * Build {@link Uri} to feedback for given session.
         */
        public static Uri buildFeedbackUri(String sessionId) {
            return CONTENT_URI.buildUpon().appendPath(sessionId).build();
        }

        /**
         * Read {@link #SESSION_ID} from {@link Feedback} {@link Uri}.
         */
        public static String getSessionId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    /**
     * MySchedule represent the sessions that the user has starred/added to the "my schedule".
     * Each row of MySchedule represents one session in one account's my schedule.
     */
    public static class MySchedule implements MyScheduleColumns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MY_SCHEDULE).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.dentalmanager.myschedule";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.dentalmanager.myschedule";

        /**
         * Build {@link Uri} that references all My Schedule for the current user.
         */
        public static Uri buildMyScheduleUri(Context context) {
            return buildMyScheduleUri(context, null);
        }

        public static Uri buildMyScheduleUri(Context context, String accountName) {
            if (accountName == null) {
                accountName = AccountUtils.getActiveAccountName(context);
            }
            return addOverrideAccountName(CONTENT_URI, accountName);
        }

    }

    /**
     * Rooms are physical locations at the conference venue.
     */
    public static class Room implements RoomColumns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROOM).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.iosched2014.room";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.iosched2014.room";

        /**
         * Default "ORDER BY" clause.
         */
        public static final String DEFAULT_SORT = RoomColumns.ROOM_FLOOR + " ASC, "
                + RoomColumns.ROOM_NAME + " COLLATE NOCASE ASC";

        /**
         * Build {@link Uri} for requested {@link #ROOM_ID}.
         */
        public static Uri buildRoomUri(String roomId) {
            return CONTENT_URI.buildUpon().appendPath(roomId).build();
        }

        /**
         * Build {@link Uri} that references any {@link Sessions} associated
         * with the requested {@link #ROOM_ID}.
         */
        public static Uri buildSessionsDirUri(String roomId) {
            return CONTENT_URI.buildUpon().appendPath(roomId).appendPath(PATH_SESSIONS).build();
        }

        /**
         * Read {@link #ROOM_ID} from {@link Room} {@link Uri}.
         */
        public static String getRoomId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    /**
     * Each Session has a Room and a Patient.
     */
    public static class Sessions implements SessionsColumns, RoomColumns, SyncColumns, BaseColumns {
        public static final String QUERY_PARAMETER_TAG_FILTER = "filter";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SESSIONS).build();
        public static final Uri CONTENT_MY_SCHEDULE_URI =
                CONTENT_URI.buildUpon().appendPath(PATH_MY_SCHEDULE).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.dentalmanager.session";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.dentalmanager.session";

        public static final String ROOM_ID = "room_id";

        //TODO : Complete this class Add Patient And Appointment Details

    }

    /**
     * Adds an account override parameter to the URI.
     * The override parameter instructs the Content Provider to ignore the currently logged in
     * account and use the provided account when fetching account-specific data
     * (such as sessions in My Schedule).
     */
    public static Uri addOverrideAccountName(Uri uri, String accountName) {
        return uri.buildUpon().appendQueryParameter(
                OVERRIDE_ACCOUNTNAME_PARAMETER, accountName).build();
    }

    public static String getOverrideAccountName(Uri uri) {
        return uri.getQueryParameter(OVERRIDE_ACCOUNTNAME_PARAMETER);
    }

    private DatabaseContract() {
    }
}
