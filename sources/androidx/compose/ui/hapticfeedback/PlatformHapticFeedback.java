package androidx.compose.ui.hapticfeedback;

import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PlatformHapticFeedback implements HapticFeedback {
    public final AndroidComposeView view;

    public PlatformHapticFeedback(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
    }

    /* renamed from: performHapticFeedback-CdsT49E, reason: not valid java name */
    public final void m445performHapticFeedbackCdsT49E(int i) {
        AndroidComposeView androidComposeView = this.view;
        if (i == 0) {
            androidComposeView.performHapticFeedback(0);
        } else if (i == 9) {
            androidComposeView.performHapticFeedback(9);
        }
    }
}
