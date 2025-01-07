package com.google.android.setupwizard.deviceorigin;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeviceOrigin {
    public static final String[] COLUMN_VALUE_ARRAY = {"value"};

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Contract {
        public static final Uri CONTENT_URI;
        public static final Uri VALUE_URI;

        static {
            Uri parse = Uri.parse("content://com.google.android.setupwizard.deviceorigin");
            CONTENT_URI = parse;
            parse.buildUpon().appendPath("list").build();
            VALUE_URI = parse.buildUpon().appendPath("value").build();
        }
    }

    public static Cursor getCursorForKey(Context context) {
        Cursor query = context.getContentResolver().query(Contract.VALUE_URI.buildUpon().appendEncodedPath("source_device").build(), COLUMN_VALUE_ARRAY, "type=?", new String[]{"java.lang.String"}, null);
        if (query == null) {
            return null;
        }
        int count = query.getCount();
        if (count == 1) {
            query.moveToFirst();
            return query;
        }
        if (count == 0) {
            try {
                query.close();
            } catch (Exception unused) {
            }
            return null;
        }
        Log.w("DeviceOrigin", "Multiple values found for key=source_device");
        return null;
    }
}
