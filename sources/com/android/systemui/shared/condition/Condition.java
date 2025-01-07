package com.android.systemui.shared.condition;

import android.util.Log;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.shared.condition.Monitor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Condition {
    public final ArrayList mCallbacks;
    public Boolean mIsConditionMet;
    public boolean mStarted;
    public final String mTag;

    public Condition() {
        Boolean bool = Boolean.FALSE;
        this.mTag = getClass().getSimpleName();
        this.mCallbacks = new ArrayList();
        this.mStarted = false;
        this.mIsConditionMet = bool;
    }

    public abstract void start();

    public abstract void stop();

    public final void updateCondition(boolean z) {
        Boolean bool = this.mIsConditionMet;
        if (bool == null || bool.booleanValue() != z) {
            String str = this.mTag;
            if (Log.isLoggable(str, 3)) {
                MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("updating condition to ", str, z);
            }
            this.mIsConditionMet = Boolean.valueOf(z);
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                Monitor.AnonymousClass1 anonymousClass1 = (Monitor.AnonymousClass1) ((WeakReference) it.next()).get();
                if (anonymousClass1 == null) {
                    it.remove();
                } else {
                    Monitor.this.mExecutor.execute(new Monitor$$ExternalSyntheticLambda2(1, anonymousClass1, this));
                }
            }
        }
    }
}
