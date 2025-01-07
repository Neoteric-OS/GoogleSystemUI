package com.android.systemui.statusbar.disableflags.data.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisableFlagsModel {
    public final boolean animate;
    public final boolean areNotificationIconsEnabled;
    public final int disable1;
    public final int disable2;
    public final boolean isClockEnabled;
    public final boolean isSystemInfoEnabled;

    public DisableFlagsModel(int i, int i2, boolean z) {
        this.disable1 = i;
        this.disable2 = i2;
        this.animate = z;
        boolean z2 = false;
        this.isClockEnabled = (8388608 & i) == 0;
        this.areNotificationIconsEnabled = (131072 & i) == 0;
        if ((i & 1048576) == 0 && (i2 & 2) == 0) {
            z2 = true;
        }
        this.isSystemInfoEnabled = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DisableFlagsModel)) {
            return false;
        }
        DisableFlagsModel disableFlagsModel = (DisableFlagsModel) obj;
        return this.disable1 == disableFlagsModel.disable1 && this.disable2 == disableFlagsModel.disable2 && this.animate == disableFlagsModel.animate;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.animate) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.disable2, Integer.hashCode(this.disable1) * 31, 31);
    }

    public final void logChange(LogBuffer logBuffer, final DisableFlagsLogger disableFlagsLogger) {
        LogMessage obtain = logBuffer.obtain("DisableFlagsModel", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel$logChange$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return DisableFlagsLogger.this.getDisableFlagsString(new DisableFlagsLogger.DisableState(logMessage.getInt1(), logMessage.getInt2()), null);
            }
        }, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = this.disable1;
        logMessageImpl.int2 = this.disable2;
        logBuffer.commit(obtain);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DisableFlagsModel(disable1=");
        sb.append(this.disable1);
        sb.append(", disable2=");
        sb.append(this.disable2);
        sb.append(", animate=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.animate, ")");
    }
}
