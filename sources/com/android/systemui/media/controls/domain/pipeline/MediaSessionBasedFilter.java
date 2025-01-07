package com.android.systemui.media.controls.domain.pipeline;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.statusbar.phone.NotificationListenerWithPlugins;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__IterablesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSessionBasedFilter implements MediaDataManager.Listener {
    public final Executor backgroundExecutor;
    public final Executor foregroundExecutor;
    public final MediaSessionManager sessionManager;
    public final Set listeners = new LinkedHashSet();
    public final LinkedHashMap packageControllers = new LinkedHashMap();
    public final Map keyedTokens = new LinkedHashMap();
    public final Set tokensWithNotifications = new LinkedHashSet();
    public final MediaSessionBasedFilter$sessionListener$1 sessionListener = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaSessionBasedFilter$sessionListener$1
        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public final void onActiveSessionsChanged(List list) {
            MediaSessionBasedFilter.access$handleControllersChanged(MediaSessionBasedFilter.this, list);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TokenId {
        public final int id;

        public TokenId(MediaSession.Token token) {
            this.id = token.hashCode();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TokenId) && this.id == ((TokenId) obj).id;
        }

        public final int hashCode() {
            return Integer.hashCode(this.id);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("TokenId(id="), this.id, ")");
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.systemui.media.controls.domain.pipeline.MediaSessionBasedFilter$sessionListener$1] */
    public MediaSessionBasedFilter(final Context context, MediaSessionManager mediaSessionManager, Executor executor, Executor executor2) {
        this.sessionManager = mediaSessionManager;
        this.foregroundExecutor = executor;
        this.backgroundExecutor = executor2;
        executor2.execute(new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaSessionBasedFilter.1
            @Override // java.lang.Runnable
            public final void run() {
                ComponentName componentName = new ComponentName(context, (Class<?>) NotificationListenerWithPlugins.class);
                MediaSessionBasedFilter mediaSessionBasedFilter = this;
                mediaSessionBasedFilter.sessionManager.addOnActiveSessionsChangedListener(mediaSessionBasedFilter.sessionListener, componentName);
                MediaSessionBasedFilter mediaSessionBasedFilter2 = this;
                MediaSessionBasedFilter.access$handleControllersChanged(mediaSessionBasedFilter2, mediaSessionBasedFilter2.sessionManager.getActiveSessions(componentName));
            }
        });
    }

    public static final void access$handleControllersChanged(MediaSessionBasedFilter mediaSessionBasedFilter, List list) {
        mediaSessionBasedFilter.packageControllers.clear();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaController mediaController = (MediaController) it.next();
                List list2 = (List) mediaSessionBasedFilter.packageControllers.get(mediaController.getPackageName());
                if (list2 != null) {
                    list2.add(mediaController);
                }
            }
        }
        if (list != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(new TokenId(((MediaController) it2.next()).getSessionToken()));
            }
            mediaSessionBasedFilter.tokensWithNotifications.retainAll(arrayList);
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2) {
        this.backgroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataLoaded$1(mediaData, str2, str, this, z));
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataRemoved(String str, boolean z) {
        this.backgroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataRemoved$1(this, str, z, 0));
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataLoaded(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
        this.backgroundExecutor.execute(new MediaSessionBasedFilter$onSmartspaceMediaDataLoaded$1(this, str, smartspaceMediaData, 0));
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
        this.backgroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataRemoved$1(this, str, z, 3));
    }
}
