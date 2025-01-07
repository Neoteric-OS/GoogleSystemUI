package com.android.systemui.bouncer.domain.interactor;

import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import java.util.Iterator;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrimaryBouncerInteractor$showRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PrimaryBouncerInteractor this$0;

    public /* synthetic */ PrimaryBouncerInteractor$showRunnable$1(PrimaryBouncerInteractor primaryBouncerInteractor, int i) {
        this.$r8$classId = i;
        this.this$0 = primaryBouncerInteractor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = this.this$0.repository;
                Boolean bool = Boolean.TRUE;
                StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._primaryBouncerShow;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
                KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl2 = this.this$0.repository;
                Boolean bool2 = Boolean.FALSE;
                StateFlowImpl stateFlowImpl2 = keyguardBouncerRepositoryImpl2._primaryBouncerShowingSoon;
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, bool2);
                Iterator it = this.this$0.primaryBouncerCallbackInteractor.expansionCallbacks.iterator();
                while (it.hasNext()) {
                    ((PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback) it.next()).onVisibilityChanged(true);
                }
                return;
            default:
                Iterator it2 = this.this$0.primaryBouncerCallbackInteractor.resetCallbacks.listeners.iterator();
                if (it2.hasNext()) {
                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                }
                return;
        }
    }
}
