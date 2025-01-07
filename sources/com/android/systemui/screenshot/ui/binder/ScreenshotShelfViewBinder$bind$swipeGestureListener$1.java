package com.android.systemui.screenshot.ui.binder;

import com.android.systemui.screenshot.ScreenshotEvent;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenshotShelfViewBinder$bind$swipeGestureListener$1 extends Lambda implements Function1 {
    final /* synthetic */ Function2 $onDismissalRequested;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenshotShelfViewBinder$bind$swipeGestureListener$1(Function2 function2) {
        super(1);
        this.$onDismissalRequested = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.$onDismissalRequested.invoke(ScreenshotEvent.SCREENSHOT_SWIPE_DISMISSED, (Float) obj);
        return Unit.INSTANCE;
    }
}
