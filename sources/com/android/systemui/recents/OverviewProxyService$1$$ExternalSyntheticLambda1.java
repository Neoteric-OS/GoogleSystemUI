package com.android.systemui.recents;

import com.android.systemui.contextualeducation.GestureType;
import com.android.systemui.education.dagger.NoOpKeyboardTouchpadEduStatsInteractor;
import com.android.systemui.recents.OverviewProxyService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OverviewProxyService$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ OverviewProxyService$1$$ExternalSyntheticLambda1(OverviewProxyService.AnonymousClass1 anonymousClass1, boolean z, String str, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass1;
        this.f$1 = z;
        this.f$2 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                anonymousClass1.this$0.mHandler.post(new OverviewProxyService$1$$ExternalSyntheticLambda1(anonymousClass1, this.f$1, this.f$2, 1));
                break;
            default:
                OverviewProxyService.AnonymousClass1 anonymousClass12 = this.f$0;
                boolean z = this.f$1;
                String str = this.f$2;
                OverviewProxyService overviewProxyService = anonymousClass12.this$0;
                GestureType.valueOf(str);
                NoOpKeyboardTouchpadEduStatsInteractor noOpKeyboardTouchpadEduStatsInteractor = overviewProxyService.mKeyboardTouchpadEduStatsInteractor;
                if (!z) {
                    noOpKeyboardTouchpadEduStatsInteractor.getClass();
                    break;
                } else {
                    noOpKeyboardTouchpadEduStatsInteractor.getClass();
                    break;
                }
        }
    }
}
