package com.android.systemui.statusbar.dagger;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesDependenciesModule_ProvideNotificationMediaManagerFactory implements Provider {
    public static NotificationMediaManager provideNotificationMediaManager(Context context, NotificationVisibilityProvider notificationVisibilityProvider, NotifPipeline notifPipeline, NotifCollection notifCollection, MediaDataManager mediaDataManager, DumpManager dumpManager, Executor executor, Handler handler) {
        return new NotificationMediaManager(context, notificationVisibilityProvider, notifPipeline, notifCollection, mediaDataManager, dumpManager, executor, handler);
    }
}
