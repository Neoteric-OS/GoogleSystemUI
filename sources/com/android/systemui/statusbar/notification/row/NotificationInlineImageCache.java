package com.android.systemui.statusbar.notification.row;

import android.net.Uri;
import android.os.AsyncTask;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationInlineImageCache {
    public final ConcurrentHashMap mCache = new ConcurrentHashMap();
    public NotificationInlineImageResolver mResolver;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PreloadImageTask extends AsyncTask {
        public final NotificationInlineImageResolver mResolver;

        public PreloadImageTask(NotificationInlineImageResolver notificationInlineImageResolver) {
            this.mResolver = notificationInlineImageResolver;
        }

        @Override // android.os.AsyncTask
        public final Object doInBackground(Object[] objArr) {
            return this.mResolver.resolveImage(((Uri[]) objArr)[0]);
        }
    }
}
