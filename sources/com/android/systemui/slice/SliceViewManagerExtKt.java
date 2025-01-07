package com.android.systemui.slice;

import android.net.Uri;
import androidx.slice.SliceViewManagerWrapper;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SliceViewManagerExtKt {
    public static final Flow sliceForUri(SliceViewManagerWrapper sliceViewManagerWrapper, Uri uri) {
        return FlowConflatedKt.conflatedCallbackFlow(new SliceViewManagerExtKt$sliceForUri$1(sliceViewManagerWrapper, uri, null));
    }
}
