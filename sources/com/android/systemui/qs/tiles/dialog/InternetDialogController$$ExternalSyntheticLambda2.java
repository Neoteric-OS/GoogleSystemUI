package com.android.systemui.qs.tiles.dialog;

import android.telephony.SubscriptionInfo;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import java.util.Set;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class InternetDialogController$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ InternetDialogController$$ExternalSyntheticLambda2(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                SubscriptionInfo subscriptionInfo = (SubscriptionInfo) obj;
                ((InternetDialogController) obj2).getClass();
                String trim = subscriptionInfo.getDisplayName().toString().trim();
                InternetDialogController.C1DisplayInfo c1DisplayInfo = new InternetDialogController.C1DisplayInfo();
                c1DisplayInfo.subscriptionInfo = subscriptionInfo;
                c1DisplayInfo.originalName = trim;
                return c1DisplayInfo;
            default:
                InternetDialogController.C1DisplayInfo c1DisplayInfo2 = (InternetDialogController.C1DisplayInfo) obj;
                if (((Set) obj2).contains(c1DisplayInfo2.uniqueName)) {
                    c1DisplayInfo2.uniqueName = ((Object) c1DisplayInfo2.originalName) + " " + c1DisplayInfo2.subscriptionInfo.getSubscriptionId();
                }
                return c1DisplayInfo2;
        }
    }
}
