package com.android.systemui.recents;

import android.view.MotionEvent;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shade.ShadeViewController;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OverviewProxyService$1$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;
    public final /* synthetic */ MotionEvent f$1;

    public /* synthetic */ OverviewProxyService$1$$ExternalSyntheticLambda4(OverviewProxyService.AnonymousClass1 anonymousClass1, MotionEvent motionEvent, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass1;
        this.f$1 = motionEvent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((ShadeViewController) this.f$0.this$0.mShadeViewControllerLazy.get()).handleExternalTouch(this.f$1);
                break;
            case 1:
                OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                MotionEvent motionEvent = this.f$1;
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    OverviewProxyService overviewProxyService = anonymousClass1.this$0;
                    overviewProxyService.mInputFocusTransferStarted = true;
                    overviewProxyService.mInputFocusTransferStartY = motionEvent.getY();
                    anonymousClass1.this$0.mInputFocusTransferStartMillis = motionEvent.getEventTime();
                    ((ShadeViewController) anonymousClass1.this$0.mShadeViewControllerLazy.get()).startInputFocusTransfer();
                }
                if (actionMasked == 1 || actionMasked == 3) {
                    anonymousClass1.this$0.mInputFocusTransferStarted = false;
                    float y = motionEvent.getY() - anonymousClass1.this$0.mInputFocusTransferStartY;
                    long eventTime = motionEvent.getEventTime();
                    float f = y / (eventTime - r0.mInputFocusTransferStartMillis);
                    Lazy lazy = anonymousClass1.this$0.mShadeViewControllerLazy;
                    if (actionMasked == 3) {
                        ((ShadeViewController) lazy.get()).cancelInputFocusTransfer();
                    } else {
                        ((ShadeViewController) lazy.get()).finishInputFocusTransfer(f);
                    }
                }
                motionEvent.recycle();
                break;
            default:
                OverviewProxyService.AnonymousClass1 anonymousClass12 = this.f$0;
                MotionEvent motionEvent2 = this.f$1;
                if (motionEvent2.getActionMasked() == 0) {
                    ((ShadeViewController) anonymousClass12.this$0.mShadeViewControllerLazy.get()).startExpandLatencyTracking();
                }
                anonymousClass12.this$0.mHandler.post(new OverviewProxyService$1$$ExternalSyntheticLambda4(anonymousClass12, motionEvent2, 1));
                break;
        }
    }
}
