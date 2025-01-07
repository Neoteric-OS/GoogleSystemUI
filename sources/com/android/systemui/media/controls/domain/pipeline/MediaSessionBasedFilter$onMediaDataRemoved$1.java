package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSessionBasedFilter$onMediaDataRemoved$1 implements Runnable {
    public final /* synthetic */ String $key;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ boolean $userInitiated;
    public final /* synthetic */ MediaSessionBasedFilter this$0;

    public /* synthetic */ MediaSessionBasedFilter$onMediaDataRemoved$1(MediaSessionBasedFilter mediaSessionBasedFilter, String str, boolean z, int i) {
        this.$r8$classId = i;
        this.this$0 = mediaSessionBasedFilter;
        this.$key = str;
        this.$userInitiated = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.keyedTokens.remove(this.$key);
                MediaSessionBasedFilter mediaSessionBasedFilter = this.this$0;
                mediaSessionBasedFilter.foregroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataRemoved$1(mediaSessionBasedFilter, this.$key, this.$userInitiated, 1));
                break;
            case 1:
                Set set = CollectionsKt.toSet(this.this$0.listeners);
                String str = this.$key;
                boolean z = this.$userInitiated;
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
                }
                break;
            case 2:
                Set set2 = CollectionsKt.toSet(this.this$0.listeners);
                String str2 = this.$key;
                boolean z2 = this.$userInitiated;
                Iterator it2 = set2.iterator();
                while (it2.hasNext()) {
                    ((MediaDataManager.Listener) it2.next()).onSmartspaceMediaDataRemoved(str2, z2);
                }
                break;
            default:
                MediaSessionBasedFilter mediaSessionBasedFilter2 = this.this$0;
                mediaSessionBasedFilter2.foregroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataRemoved$1(mediaSessionBasedFilter2, this.$key, this.$userInitiated, 2));
                break;
        }
    }
}
