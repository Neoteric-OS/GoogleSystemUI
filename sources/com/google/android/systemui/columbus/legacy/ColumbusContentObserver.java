package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusContentObserver extends ContentObserver {
    public final Lambda callback;
    public final ContentResolverWrapper contentResolver;
    public final Executor executor;
    public final Uri settingsUri;
    public final UserTracker userTracker;
    public final ColumbusContentObserver$userTrackerCallback$1 userTrackerCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ContentResolverWrapper contentResolver;
        public final Executor executor;
        public final Handler handler;
        public final UserTracker userTracker;

        public Factory(ContentResolverWrapper contentResolverWrapper, UserTracker userTracker, Handler handler, Executor executor) {
            this.contentResolver = contentResolverWrapper;
            this.userTracker = userTracker;
            this.handler = handler;
            this.executor = executor;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.ColumbusContentObserver$userTrackerCallback$1] */
    public ColumbusContentObserver(ContentResolverWrapper contentResolverWrapper, Uri uri, Function1 function1, UserTracker userTracker, Executor executor, Handler handler) {
        super(handler);
        this.contentResolver = contentResolverWrapper;
        this.settingsUri = uri;
        this.callback = (Lambda) function1;
        this.userTracker = userTracker;
        this.executor = executor;
        this.userTrackerCallback = new UserTracker.Callback() { // from class: com.google.android.systemui.columbus.legacy.ColumbusContentObserver$userTrackerCallback$1
            /* JADX WARN: Type inference failed for: r3v5, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context) {
                ColumbusContentObserver columbusContentObserver = ColumbusContentObserver.this;
                columbusContentObserver.contentResolver.contentResolver.unregisterContentObserver(columbusContentObserver);
                ContentResolverWrapper contentResolverWrapper2 = columbusContentObserver.contentResolver;
                contentResolverWrapper2.contentResolver.registerContentObserver(columbusContentObserver.settingsUri, false, columbusContentObserver, ((UserTrackerImpl) columbusContentObserver.userTracker).getUserId());
                columbusContentObserver.callback.invoke(columbusContentObserver.settingsUri);
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // android.database.ContentObserver
    public final void onChange(boolean z, Uri uri) {
        if (uri != null) {
            this.callback.invoke(uri);
        }
    }
}
