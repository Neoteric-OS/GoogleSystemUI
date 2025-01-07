package com.android.systemui.screenshot.ui.binder;

import android.view.View;
import com.android.systemui.screenshot.ScreenshotEvent;
import com.android.systemui.screenshot.ui.viewmodel.PreviewAction;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotShelfViewBinder$bind$2 implements View.OnClickListener {
    public final /* synthetic */ Object $onDismissalRequested;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ScreenshotShelfViewBinder$bind$2(int i, Object obj) {
        this.$r8$classId = i;
        this.$onDismissalRequested = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Function0 function0;
        switch (this.$r8$classId) {
            case 0:
                ((Function2) this.$onDismissalRequested).invoke(ScreenshotEvent.SCREENSHOT_EXPLICIT_DISMISSAL, null);
                break;
            default:
                PreviewAction previewAction = (PreviewAction) this.$onDismissalRequested;
                if (previewAction != null && (function0 = previewAction.onClick) != null) {
                    function0.invoke();
                    break;
                }
                break;
        }
    }
}
