package com.android.systemui.util.wrapper;

import android.content.Context;
import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationPolicyWrapperImpl {
    public final Context context;
    public final SecureSettings secureSettings;

    public RotationPolicyWrapperImpl(Context context, SecureSettings secureSettings) {
        this.context = context;
        this.secureSettings = secureSettings;
    }

    public final void setRotationLock(String str, boolean z) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("RotationPolicyWrapperImpl#setRotationLock");
        }
        try {
            RotationPolicy.setRotationLock(this.context, z, str);
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
