package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSessionBasedFilter$onSmartspaceMediaDataLoaded$1 implements Runnable {
    public final /* synthetic */ SmartspaceMediaData $data;
    public final /* synthetic */ String $key;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaSessionBasedFilter this$0;

    public /* synthetic */ MediaSessionBasedFilter$onSmartspaceMediaDataLoaded$1(MediaSessionBasedFilter mediaSessionBasedFilter, String str, SmartspaceMediaData smartspaceMediaData, int i) {
        this.$r8$classId = i;
        this.this$0 = mediaSessionBasedFilter;
        this.$key = str;
        this.$data = smartspaceMediaData;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                MediaSessionBasedFilter mediaSessionBasedFilter = this.this$0;
                mediaSessionBasedFilter.foregroundExecutor.execute(new MediaSessionBasedFilter$onSmartspaceMediaDataLoaded$1(mediaSessionBasedFilter, this.$key, this.$data, 1));
                break;
            default:
                Set set = CollectionsKt.toSet(this.this$0.listeners);
                String str = this.$key;
                SmartspaceMediaData smartspaceMediaData = this.$data;
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((MediaDataManager.Listener) it.next()).onSmartspaceMediaDataLoaded(str, smartspaceMediaData, false);
                }
                break;
        }
    }
}
