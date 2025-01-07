package com.android.systemui.unfold;

import android.os.SystemClock;
import android.util.Log;
import com.android.internal.util.FrameworkStatsLog;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldStateLoggingProviderImpl implements FoldStateProvider$FoldUpdatesListener, CallbackController {
    public Long actionStartMillis;
    public final SystemClockImpl clock;
    public final DeviceFoldStateProvider foldStateProvider;
    public Integer lastState;
    public final List outputListeners = new ArrayList();

    public FoldStateLoggingProviderImpl(DeviceFoldStateProvider deviceFoldStateProvider, SystemClockImpl systemClockImpl) {
        this.foldStateProvider = deviceFoldStateProvider;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.outputListeners.add((FoldStateLogger) obj);
    }

    public final void dispatchState(int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Integer num = this.lastState;
        Long l = this.actionStartMillis;
        if (num != null && num.intValue() != i && l != null) {
            long longValue = elapsedRealtime - l.longValue();
            int intValue = num.intValue();
            Iterator it = this.outputListeners.iterator();
            while (it.hasNext()) {
                ((FoldStateLogger) it.next()).getClass();
                FrameworkStatsLog.write(414, intValue, i, longValue);
            }
        }
        this.actionStartMillis = null;
        this.lastState = Integer.valueOf(i);
    }

    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    public final void onFoldUpdate(int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (i == 0) {
            this.lastState = 2;
            this.actionStartMillis = Long.valueOf(elapsedRealtime);
            return;
        }
        if (i == 1) {
            this.actionStartMillis = Long.valueOf(elapsedRealtime);
            return;
        }
        if (i == 2) {
            dispatchState(3);
        } else if (i == 3) {
            dispatchState(1);
        } else {
            if (i != 4) {
                return;
            }
            dispatchState(2);
        }
    }

    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    public final void onUnfoldedScreenAvailable() {
        Log.d("FoldStateLoggingProviderImpl", "Unfolded screen available");
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.outputListeners.remove((FoldStateLogger) obj);
    }
}
