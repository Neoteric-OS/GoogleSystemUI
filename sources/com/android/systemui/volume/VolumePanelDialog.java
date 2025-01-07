package com.android.systemui.volume;

import android.net.Uri;
import androidx.lifecycle.LifecycleOwner;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import java.util.HashSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class VolumePanelDialog extends SystemUIDialog implements LifecycleOwner {
    public final HashSet mLoadedSlices;

    public abstract boolean removeSliceLiveData(Uri uri);
}
