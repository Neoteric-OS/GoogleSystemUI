package com.android.settingslib.media;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectionRecordManager {
    public static ConnectionRecordManager sInstance;
    public static final Object sInstanceSync = new Object();
    public String mLastSelectedDevice;

    public static ConnectionRecordManager getInstance() {
        synchronized (sInstanceSync) {
            try {
                if (sInstance == null) {
                    sInstance = new ConnectionRecordManager();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return sInstance;
    }
}
