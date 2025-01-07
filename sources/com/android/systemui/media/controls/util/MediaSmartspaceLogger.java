package com.android.systemui.media.controls.util;

import android.os.Build;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.shared.system.SysUiStatsLog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSmartspaceLogger {
    public static final boolean DEBUG = Log.isLoggable("MediaSmartspaceLogger", 3);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static int getSurface(int i) {
            if (Log.isLoggable("RefactorFlagAssert", 7)) {
                Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
            } else if (Log.isLoggable("RefactorFlag", 5)) {
                Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
            }
            if (i == 0 || i == 1) {
                return 4;
            }
            if (i != 2) {
                return i != 3 ? 0 : 5;
            }
            return 2;
        }
    }

    public static void logSmartspaceCardReceived$default(MediaSmartspaceLogger mediaSmartspaceLogger, int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        boolean z2 = (i6 & 8) == 0;
        boolean z3 = (i6 & 16) != 0 ? false : z;
        int i7 = (i6 & 64) != 0 ? 0 : i5;
        mediaSmartspaceLogger.getClass();
        logSmartspaceCardReported$default(mediaSmartspaceLogger, 759, i, i2, new int[]{4, 2, 5}, i3, z2, z3, 0, 0, i4, i7, false, 2432);
    }

    public static void logSmartspaceCardReported$default(MediaSmartspaceLogger mediaSmartspaceLogger, int i, int i2, int i3, int[] iArr, int i4, boolean z, boolean z2, int i5, int i6, int i7, int i8, boolean z3, int i9) {
        int i10;
        int[] iArr2 = iArr;
        int i11 = 0;
        int i12 = (i9 & 128) != 0 ? 0 : i5;
        int i13 = (i9 & 256) != 0 ? 0 : i6;
        int i14 = (i9 & 1024) != 0 ? 0 : i8;
        boolean z4 = (i9 & 2048) != 0 ? false : z3;
        mediaSmartspaceLogger.getClass();
        int length = iArr2.length;
        while (i11 < length) {
            int i15 = iArr2[i11];
            int i16 = z4 ? -1 : i7;
            int i17 = length;
            int i18 = i14;
            boolean z5 = z4;
            int i19 = i13;
            SysUiStatsLog.write(i, i2, i15, i16, i4, z ? 15 : z2 ? 43 : 31, i3, i12, i13, i18, null, null);
            if (DEBUG) {
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "Log Smartspace card event id: ", " instance id: ", " surface: ");
                ViewPager$$ExternalSyntheticOutline0.m(m, i15, " rank: ", i7, " cardinality: ");
                m.append(i4);
                m.append(" isRecommendationCard: ");
                m.append(z);
                m.append(" isSsReactivated: ");
                m.append(z2);
                m.append("uid: ");
                m.append(i3);
                m.append(" interactedSubcardRank: ");
                ViewPager$$ExternalSyntheticOutline0.m(m, i12, " interactedSubcardCardinality: ", i19, " received_latency_millis: ");
                i10 = i18;
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(m, i10, "MediaSmartspaceLogger");
            } else {
                i10 = i18;
            }
            i11++;
            iArr2 = iArr;
            i13 = i19;
            i14 = i10;
            length = i17;
            z4 = z5;
        }
    }

    public static void logSmartspaceCardUIEvent$default(MediaSmartspaceLogger mediaSmartspaceLogger, int i, int i2, int i3, int i4, int i5, boolean z, int i6, int i7, int i8, boolean z2, int i9) {
        boolean z3 = (i9 & 32) == 0;
        boolean z4 = (i9 & 64) != 0 ? false : z;
        mediaSmartspaceLogger.getClass();
        logSmartspaceCardReported$default(mediaSmartspaceLogger, i, i2, i3, new int[]{i4}, i5, z3, z4, i6, i7, i8, 0, z2, 1024);
    }
}
