package com.android.systemui.statusbar.notification.collection.listbuilder.pluggable;

import android.os.Trace;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.util.Assert;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Pluggable {
    public ShadeListBuilder$$ExternalSyntheticLambda0 mListener;
    public final String mName;

    public Pluggable(String str) {
        this.mName = str;
    }

    public final void invalidateList(String str) {
        if (this.mListener != null) {
            if (Trace.isEnabled()) {
                Trace.traceBegin(4096L, "Pluggable<" + this.mName + ">.invalidateList");
            }
            ShadeListBuilder$$ExternalSyntheticLambda0 shadeListBuilder$$ExternalSyntheticLambda0 = this.mListener;
            switch (shadeListBuilder$$ExternalSyntheticLambda0.$r8$classId) {
                case 1:
                    ShadeListBuilder shadeListBuilder = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder.getClass();
                    Assert.isMainThread();
                    shadeListBuilder.mLogger.logPluggableInvalidated("ReorderingNowAllowed", (NotifStabilityManager) this, shadeListBuilder.mPipelineState.mState, str);
                    shadeListBuilder.rebuildListIfBefore(4);
                    break;
                case 2:
                    ShadeListBuilder shadeListBuilder2 = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder2.getClass();
                    Assert.isMainThread();
                    shadeListBuilder2.mLogger.logPluggableInvalidated("NotifSection", (NotifSectioner) this, shadeListBuilder2.mPipelineState.mState, str);
                    shadeListBuilder2.rebuildListIfBefore(7);
                    break;
                case 3:
                    ShadeListBuilder shadeListBuilder3 = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder3.getClass();
                    Assert.isMainThread();
                    shadeListBuilder3.mLogger.logPluggableInvalidated("NotifComparator", (NotifComparator) this, shadeListBuilder3.mPipelineState.mState, str);
                    shadeListBuilder3.rebuildListIfBefore(7);
                    break;
                case 4:
                    ShadeListBuilder shadeListBuilder4 = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder4.getClass();
                    Assert.isMainThread();
                    shadeListBuilder4.mLogger.logPluggableInvalidated("Pre-render Invalidator", (Invalidator) this, shadeListBuilder4.mPipelineState.mState, str);
                    shadeListBuilder4.rebuildListIfBefore(9);
                    break;
                case 5:
                    ShadeListBuilder shadeListBuilder5 = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder5.getClass();
                    Assert.isMainThread();
                    shadeListBuilder5.mLogger.logPluggableInvalidated("Pre-group NotifFilter", (NotifFilter) this, shadeListBuilder5.mPipelineState.mState, str);
                    shadeListBuilder5.rebuildListIfBefore(3);
                    break;
                case 6:
                    ShadeListBuilder shadeListBuilder6 = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder6.getClass();
                    Assert.isMainThread();
                    shadeListBuilder6.mLogger.logPluggableInvalidated("Finalize NotifFilter", (NotifFilter) this, shadeListBuilder6.mPipelineState.mState, str);
                    shadeListBuilder6.rebuildListIfBefore(8);
                    break;
                default:
                    ShadeListBuilder shadeListBuilder7 = shadeListBuilder$$ExternalSyntheticLambda0.f$0;
                    shadeListBuilder7.getClass();
                    Assert.isMainThread();
                    shadeListBuilder7.mLogger.logPluggableInvalidated("NotifPromoter", (NotifPromoter) this, shadeListBuilder7.mPipelineState.mState, str);
                    shadeListBuilder7.rebuildListIfBefore(5);
                    break;
            }
            Trace.endSection();
        }
    }

    public void onCleanup() {
    }
}
