package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.net.NetworkPolicyManager;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataSaverControllerImpl implements CallbackController {
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final ArrayList mListeners = new ArrayList();
    public final AnonymousClass1 mPolicyListener = new AnonymousClass1();
    public final NetworkPolicyManager mPolicyManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.policy.DataSaverControllerImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends NetworkPolicyManager.Listener {
        public AnonymousClass1() {
        }

        public final void onRestrictBackgroundChanged(final boolean z) {
            DataSaverControllerImpl.this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.DataSaverControllerImpl.1.1
                @Override // java.lang.Runnable
                public final void run() {
                    ArrayList arrayList;
                    DataSaverControllerImpl dataSaverControllerImpl = DataSaverControllerImpl.this;
                    boolean z2 = z;
                    synchronized (dataSaverControllerImpl.mListeners) {
                        arrayList = new ArrayList(dataSaverControllerImpl.mListeners);
                    }
                    for (int i = 0; i < arrayList.size(); i++) {
                        ((DataSaverController$Listener) arrayList.get(i)).onDataSaverChanged(z2);
                    }
                }
            });
        }
    }

    public DataSaverControllerImpl(Context context) {
        this.mPolicyManager = NetworkPolicyManager.from(context);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        DataSaverController$Listener dataSaverController$Listener = (DataSaverController$Listener) obj;
        synchronized (this.mListeners) {
            try {
                this.mListeners.add(dataSaverController$Listener);
                if (this.mListeners.size() == 1) {
                    this.mPolicyManager.registerListener(this.mPolicyListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        dataSaverController$Listener.onDataSaverChanged(this.mPolicyManager.getRestrictBackground());
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        DataSaverController$Listener dataSaverController$Listener = (DataSaverController$Listener) obj;
        synchronized (this.mListeners) {
            try {
                this.mListeners.remove(dataSaverController$Listener);
                if (this.mListeners.size() == 0) {
                    this.mPolicyManager.unregisterListener(this.mPolicyListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setDataSaverEnabled(boolean z) {
        this.mPolicyManager.setRestrictBackground(z);
        try {
            this.mPolicyListener.onRestrictBackgroundChanged(z);
        } catch (RemoteException unused) {
        }
    }
}
