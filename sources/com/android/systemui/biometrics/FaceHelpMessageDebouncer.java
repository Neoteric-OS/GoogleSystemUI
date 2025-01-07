package com.android.systemui.biometrics;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceHelpMessageDebouncer {
    public final List helpFaceAuthStatuses = new ArrayList();
    public Integer lastMessageIdShown;
    public final int shownFaceMessageFrequencyBoost;
    public long startTime;
    public final long startWindow;
    public final float threshold;
    public final long window;

    public FaceHelpMessageDebouncer(float f, int i, long j, long j2) {
        this.window = j;
        this.startWindow = j2;
        this.shownFaceMessageFrequencyBoost = i;
        this.threshold = f;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.systemui.deviceentry.shared.model.HelpFaceAuthenticationStatus getMessageToShow(long r10) {
        /*
            Method dump skipped, instructions count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.FaceHelpMessageDebouncer.getMessageToShow(long):com.android.systemui.deviceentry.shared.model.HelpFaceAuthenticationStatus");
    }

    public final void startNewFaceAuthSession(long j) {
        Log.d("FaceHelpMessageDebouncer", "startNewFaceAuthSession at startTime=" + this.startTime);
        this.startTime = j;
        this.helpFaceAuthStatuses.clear();
        this.lastMessageIdShown = null;
    }
}
