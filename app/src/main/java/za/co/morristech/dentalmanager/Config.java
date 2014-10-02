package za.co.morristech.dentalmanager;

/**
 * @author Global Kinetic
 */
public class Config {
    // General configuration

    // Is this an internal dogfood build?
    public static final boolean IS_DOGFOOD_BUILD = false;

    // shorthand for some units of time
    public static final long SECOND_MILLIS = 1000;
    public static final long MINUTE_MILLIS = 60 * SECOND_MILLIS;
    public static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    public static final long DAY_MILLIS = 24 * HOUR_MILLIS;

    // OAuth 2.0 related config
    public static final String APP_NAME = "DentalManager";
    public static final String API_KEY = "";

    // YouTube API config
    public static final String YOUTUBE_API_KEY = "";

    // YouTube share URL
    public static final String YOUTUBE_SHARE_URL_PREFIX = "http://youtu.be/";

    // GCM config
    public static final String GCM_SERVER_PROD_URL = "";
    public static final String GCM_SERVER_URL = "";

    // the GCM sender ID is the ID of the app in Google Cloud Console
    public static final String GCM_SENDER_ID = "";

    // The registration api KEY in the gcm server (configured in the GCM server's AuthHelper.java file)
    public static final String GCM_API_KEY = "";

    // Format of the youtube link to a Video Library video
    public static final String VIDEO_LIBRARY_URL_FMT = "https://www.youtube.com/watch?v=%s";

    // Values for the EventPoint feedback API. Sync happens at the same time as schedule sync,
    // and before that values are stored locally in the database.

    public static final String FEEDBACK_API_CODE = "";
    public static final String FEEDBACK_URL = "";
    public static final String FEEDBACK_API_KEY = "";
    public static final String FEEDBACK_DUMMY_REGISTRANT_ID = "";
    public static final String FEEDBACK_SURVEY_ID = "";
}
