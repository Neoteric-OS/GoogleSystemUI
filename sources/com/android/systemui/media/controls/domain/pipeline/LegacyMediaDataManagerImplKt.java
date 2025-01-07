package com.android.systemui.media.controls.domain.pipeline;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LegacyMediaDataManagerImplKt {
    public static final SmartspaceMediaData EMPTY_SMARTSPACE_MEDIA_DATA;
    public static final MediaData LOADING;

    static {
        EmptyList emptyList = EmptyList.INSTANCE;
        LOADING = new MediaData(-1, false, (String) null, (Icon) null, (CharSequence) null, (CharSequence) null, (Icon) null, (List) emptyList, (List) emptyList, (MediaButton) null, "INVALID", (MediaSession.Token) null, (PendingIntent) null, (MediaDeviceData) null, true, (Runnable) null, 0, (String) null, false, (Boolean) null, false, 0L, 0L, InstanceId.fakeInstanceId(-1), -1, false, (Double) null, 0, 1023345152);
        EMPTY_SMARTSPACE_MEDIA_DATA = new SmartspaceMediaData("INVALID", false, "INVALID", null, emptyList, null, 0L, InstanceId.fakeInstanceId(-1), 0L, 512);
    }
}
