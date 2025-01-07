package com.android.systemui.plugins.qs;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface QSContainerController {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultImpls {
        public static void setCustomizerShowing(QSContainerController qSContainerController, boolean z) {
            qSContainerController.setCustomizerShowing(z, 0L);
        }
    }

    void setCustomizerAnimating(boolean z);

    void setCustomizerShowing(boolean z);

    void setCustomizerShowing(boolean z, long j);

    void setDetailShowing(boolean z);
}
