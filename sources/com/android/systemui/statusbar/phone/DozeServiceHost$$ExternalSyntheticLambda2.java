package com.android.systemui.statusbar.phone;

import android.os.SystemClock;
import android.view.KeyEvent;
import com.android.systemui.doze.DozeReceiver;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DozeServiceHost$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ DozeServiceHost f$0;

    public /* synthetic */ DozeServiceHost$$ExternalSyntheticLambda2(DozeServiceHost dozeServiceHost) {
        this.f$0 = dozeServiceHost;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        DozeServiceHost dozeServiceHost = this.f$0;
        KeyguardRepositoryImpl keyguardRepositoryImpl = dozeServiceHost.mDozeInteractor.keyguardRepository;
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._dozeTimeTick;
        ((SystemClockImpl) keyguardRepositoryImpl.systemClock).getClass();
        Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        dozeServiceHost.mShadeLockscreenInteractor.dozeTimeTick();
        dozeServiceHost.mAuthController.dozeTimeTick();
        KeyEvent.Callback callback = dozeServiceHost.mAmbientIndicationContainer;
        if (callback instanceof DozeReceiver) {
            ((DozeReceiver) callback).dozeTimeTick();
        }
        return Unit.INSTANCE;
    }
}
