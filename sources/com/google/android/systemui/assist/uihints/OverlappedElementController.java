package com.google.android.systemui.assist.uihints;

import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.recents.IOverviewProxy;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OverlappedElementController {
    public float mAlpha = 1.0f;
    public final KeyguardInteractor mKeyguardInteractor;
    public final OverviewProxyService mOverviewProxyService;

    public OverlappedElementController(OverviewProxyService overviewProxyService, KeyguardBottomAreaInteractor keyguardBottomAreaInteractor, KeyguardInteractor keyguardInteractor) {
        this.mOverviewProxyService = overviewProxyService;
        this.mKeyguardInteractor = keyguardInteractor;
    }

    public final void setAlpha(float f) {
        float f2 = this.mAlpha;
        if (f2 == f) {
            return;
        }
        if (f2 == 1.0f) {
            int i = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
        }
        this.mAlpha = f;
        float f3 = 1.0f - f;
        OverviewProxyService overviewProxyService = this.mOverviewProxyService;
        overviewProxyService.getClass();
        try {
            IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
            if (iOverviewProxy != null) {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeFloat(f3);
                    proxy.mRemote.transact(15, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } else {
                Log.e("OverviewProxyService", "Failed to get overview proxy for assistant visibility.");
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to call notifyAssistantVisibilityChanged()", e);
        }
        KeyguardRepositoryImpl keyguardRepositoryImpl = this.mKeyguardInteractor.repository;
        Float valueOf = Float.valueOf(f);
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._keyguardAlpha;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
    }
}
