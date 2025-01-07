package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDataCombineLatest implements MediaDataManager.Listener {
    public final Set listeners = new LinkedHashSet();
    public final Map entries = new LinkedHashMap();

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2) {
        if (str2 == null || str2.equals(str) || !this.entries.containsKey(str2)) {
            Map map = this.entries;
            Pair pair = (Pair) map.get(str);
            map.put(str, new Pair(mediaData, pair != null ? (MediaDeviceData) pair.getSecond() : null));
            update(str, str);
            return;
        }
        Map map2 = this.entries;
        Pair pair2 = (Pair) map2.remove(str2);
        map2.put(str, new Pair(mediaData, pair2 != null ? (MediaDeviceData) pair2.getSecond() : null));
        update(str, str2);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataRemoved(String str, boolean z) {
        remove(str, z);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataLoaded(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
        Iterator it = CollectionsKt.toSet(this.listeners).iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onSmartspaceMediaDataLoaded(str, smartspaceMediaData, false);
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
        Iterator it = CollectionsKt.toSet(this.listeners).iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onSmartspaceMediaDataRemoved(str, z);
        }
    }

    public final void remove(String str, boolean z) {
        if (((Pair) this.entries.remove(str)) != null) {
            Iterator it = CollectionsKt.toSet(this.listeners).iterator();
            while (it.hasNext()) {
                ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
            }
        }
    }

    public final void update(String str, String str2) {
        Pair pair = (Pair) this.entries.get(str);
        if (pair == null) {
            pair = new Pair(null, null);
        }
        MediaData mediaData = (MediaData) pair.component1();
        MediaDeviceData mediaDeviceData = (MediaDeviceData) pair.component2();
        if (mediaData == null || mediaDeviceData == null) {
            return;
        }
        MediaData copy$default = MediaData.copy$default(mediaData, null, null, null, null, null, mediaDeviceData, false, null, false, null, 0L, 0L, null, 0, 1073733631);
        Iterator it = CollectionsKt.toSet(this.listeners).iterator();
        while (it.hasNext()) {
            MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str, str2, copy$default, false, 0, false, 56);
        }
    }
}
