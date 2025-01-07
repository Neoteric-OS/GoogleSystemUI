package androidx.compose.ui.platform;

import androidx.compose.ui.input.pointer.AndroidPointerIconType;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerIcon_androidKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidComposeView$pointerIconService$1 implements PointerIconService {
    public final /* synthetic */ AndroidComposeView this$0;

    public AndroidComposeView$pointerIconService$1(AndroidComposeView androidComposeView) {
        this.this$0 = androidComposeView;
        PointerIcon.Companion.getClass();
    }

    public final void setIcon(PointerIcon pointerIcon) {
        if (pointerIcon == null) {
            PointerIcon.Companion.getClass();
            pointerIcon = PointerIcon_androidKt.pointerIconDefault;
        }
        boolean z = pointerIcon instanceof AndroidPointerIconType;
        AndroidComposeView androidComposeView = this.this$0;
        android.view.PointerIcon systemIcon = z ? android.view.PointerIcon.getSystemIcon(androidComposeView.getContext(), ((AndroidPointerIconType) pointerIcon).type) : android.view.PointerIcon.getSystemIcon(androidComposeView.getContext(), 1000);
        if (Intrinsics.areEqual(androidComposeView.getPointerIcon(), systemIcon)) {
            return;
        }
        androidComposeView.setPointerIcon(systemIcon);
    }
}
