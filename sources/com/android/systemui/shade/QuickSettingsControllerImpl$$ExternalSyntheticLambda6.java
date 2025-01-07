package com.android.systemui.shade;

import com.android.systemui.plugins.qs.QS;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QuickSettingsControllerImpl$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ QuickSettingsControllerImpl$$ExternalSyntheticLambda6(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                QuickSettingsControllerImpl quickSettingsControllerImpl = (QuickSettingsControllerImpl) obj2;
                quickSettingsControllerImpl.mExpansionEnabledPolicy = ((Boolean) obj).booleanValue();
                QS qs = quickSettingsControllerImpl.mQs;
                if (qs != null) {
                    qs.setHeaderClickable(quickSettingsControllerImpl.isExpansionEnabled());
                    break;
                }
                break;
            case 1:
                ((Integer) obj).intValue();
                ((QuickSettingsControllerImpl) obj2).updateExpansionEnabledAmbient();
                break;
            case 2:
                boolean booleanValue = ((Boolean) obj).booleanValue();
                QS qs2 = ((QuickSettingsControllerImpl) obj2).mQs;
                if (qs2 != null) {
                    qs2.setShouldUpdateSquishinessOnMedia(booleanValue);
                    break;
                }
                break;
            case 3:
                ((QuickSettingsControllerImpl) obj2).mQsFrame.setVisibility(((Boolean) obj).booleanValue() ? 0 : 4);
                break;
            case 4:
                QuickSettingsControllerImpl quickSettingsControllerImpl2 = (QuickSettingsControllerImpl) obj2;
                boolean booleanValue2 = ((Boolean) obj).booleanValue();
                if (quickSettingsControllerImpl2.isQsFragmentCreated()) {
                    if (booleanValue2) {
                        quickSettingsControllerImpl2.mAnimateNextNotificationBounds = true;
                        quickSettingsControllerImpl2.mNotificationBoundsAnimationDuration = 360L;
                        quickSettingsControllerImpl2.mNotificationBoundsAnimationDelay = 0L;
                    }
                    quickSettingsControllerImpl2.setClippingBounds();
                    break;
                }
                break;
            default:
                QuickSettingsControllerImpl quickSettingsControllerImpl3 = QuickSettingsControllerImpl.this;
                if (quickSettingsControllerImpl3.mQs.isHeaderShown()) {
                    quickSettingsControllerImpl3.mAnimateNextNotificationBounds = true;
                    quickSettingsControllerImpl3.mNotificationBoundsAnimationDuration = 360L;
                    quickSettingsControllerImpl3.mNotificationBoundsAnimationDelay = 0L;
                    quickSettingsControllerImpl3.mNotificationStackScrollLayoutController.mView.mAnimateNextTopPaddingChange = true;
                    break;
                }
                break;
        }
    }
}
