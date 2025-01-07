package com.android.systemui.media.controls.data.repository;

import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.util.MediaFlags;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDataRepository implements Dumpable {
    public final StateFlowImpl _mediaEntries;
    public final StateFlowImpl _smartspaceMediaData;
    public final ReadonlyStateFlow mediaEntries;
    public final ReadonlyStateFlow smartspaceMediaData;

    public MediaDataRepository(MediaFlags mediaFlags, DumpManager dumpManager) {
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(new LinkedHashMap());
        this._mediaEntries = MutableStateFlow;
        this.mediaEntries = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(new SmartspaceMediaData(null, false, null, null, null, null, 0L, null, 0L, 1023));
        this._smartspaceMediaData = MutableStateFlow2;
        this.smartspaceMediaData = new ReadonlyStateFlow(MutableStateFlow2);
        dumpManager.registerNormalDumpable("MediaDataRepository", this);
    }

    public final MediaData addMediaEntry(MediaData mediaData, String str) {
        StateFlowImpl stateFlowImpl = this._mediaEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
        MediaData mediaData2 = (MediaData) linkedHashMap.put(str, mediaData);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, linkedHashMap);
        return mediaData2;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("mediaEntries: " + ((StateFlowImpl) this.mediaEntries.$$delegate_0).getValue());
    }

    public final MediaData removeMediaEntry(String str) {
        StateFlowImpl stateFlowImpl = this._mediaEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl.getValue());
        MediaData mediaData = (MediaData) linkedHashMap.remove(str);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, linkedHashMap);
        return mediaData;
    }
}
