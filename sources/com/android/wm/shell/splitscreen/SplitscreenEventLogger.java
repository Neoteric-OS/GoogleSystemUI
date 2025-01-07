package com.android.wm.shell.splitscreen;

import android.util.Slog;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.FrameworkStatsLog;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitscreenEventLogger {
    public int mDragEnterPosition;
    public InstanceId mEnterSessionId;
    public InstanceId mLoggerSessionId;
    public int mLastMainStagePosition = -1;
    public int mLastMainStageUid = -1;
    public int mLastSideStagePosition = -1;
    public int mLastSideStageUid = -1;
    public float mLastSplitRatio = -1.0f;
    public int mEnterReason = 0;
    public final InstanceIdSequence mIdSequence = new InstanceIdSequence(Integer.MAX_VALUE);

    public static int getLoggerExitReason(int i) {
        switch (i) {
            case 1:
                return 8;
            case 2:
                return 7;
            case 3:
                return 5;
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 6;
            case 7:
                return 3;
            case 8:
                return 4;
            case 9:
                return 9;
            case 10:
                return 10;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return 11;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return 12;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return 13;
            default:
                Slog.e("SplitscreenEventLogger", "Unknown exit reason: " + i);
                return 0;
        }
    }

    public static int getMainStagePositionFromSplitPosition(int i, boolean z) {
        if (i == -1) {
            return 0;
        }
        return z ? i == 0 ? 1 : 2 : i == 0 ? 3 : 4;
    }

    public static int getSideStagePositionFromSplitPosition(int i, boolean z) {
        if (i == -1) {
            return 0;
        }
        return z ? i == 0 ? 1 : 2 : i == 0 ? 3 : 4;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void logEnter(float r27, int r28, int r29, int r30, int r31, boolean r32) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.SplitscreenEventLogger.logEnter(float, int, int, int, int, boolean):void");
    }

    public final void logExit(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (this.mLoggerSessionId == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -7501547534164937581L, 0, null);
                return;
            }
            return;
        }
        if ((i2 != -1 && i4 != -1) || (i3 != 0 && i5 != 0)) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 5601116116111932964L, 0, null);
            }
            throw new IllegalArgumentException("Only main or side stage should be set");
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3521887009086780667L, 7509, Long.valueOf(getLoggerExitReason(i)), Long.valueOf(getMainStagePositionFromSplitPosition(i2, z)), Long.valueOf(i3), Long.valueOf(getSideStagePositionFromSplitPosition(i4, z)), Long.valueOf(i5), Boolean.valueOf(z), Long.valueOf(this.mLoggerSessionId.getId()));
        }
        FrameworkStatsLog.write(388, 2, 0, getLoggerExitReason(i), 0.0f, getMainStagePositionFromSplitPosition(i2, z), i3, getSideStagePositionFromSplitPosition(i4, z), i5, 0, this.mLoggerSessionId.getId());
        this.mLoggerSessionId = null;
        this.mDragEnterPosition = -1;
        this.mEnterSessionId = null;
        this.mLastMainStagePosition = -1;
        this.mLastMainStageUid = -1;
        this.mLastSideStagePosition = -1;
        this.mLastSideStageUid = -1;
        this.mEnterReason = 0;
    }

    public final boolean updateMainStageState(int i, int i2) {
        if (this.mLastMainStagePosition == i && this.mLastMainStageUid == i2) {
            return false;
        }
        this.mLastMainStagePosition = i;
        this.mLastMainStageUid = i2;
        return true;
    }

    public final boolean updateSideStageState(int i, int i2) {
        if (this.mLastSideStagePosition == i && this.mLastSideStageUid == i2) {
            return false;
        }
        this.mLastSideStagePosition = i;
        this.mLastSideStageUid = i2;
        return true;
    }
}
