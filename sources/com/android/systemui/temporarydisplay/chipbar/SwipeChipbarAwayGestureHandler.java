package com.android.systemui.temporarydisplay.chipbar;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.gesture.SwipeUpGestureHandler;
import com.android.systemui.statusbar.gesture.SwipeUpGestureLogger;
import com.android.systemui.util.ConvenienceExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SwipeChipbarAwayGestureHandler extends SwipeUpGestureHandler {
    public Lambda viewFetcher;

    public SwipeChipbarAwayGestureHandler(Context context, DisplayTracker displayTracker, SwipeUpGestureLogger swipeUpGestureLogger) {
        super(context, swipeUpGestureLogger, "SwipeChipbarAway");
        this.viewFetcher = new Function0() { // from class: com.android.systemui.temporarydisplay.chipbar.SwipeChipbarAwayGestureHandler$viewFetcher$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return null;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.statusbar.gesture.SwipeUpGestureHandler
    public final boolean startOfGestureIsWithinBounds(MotionEvent motionEvent) {
        View view = (View) this.viewFetcher.invoke();
        if (view == null) {
            return false;
        }
        return ((double) motionEvent.getY()) <= ((double) ConvenienceExtensionsKt.getBoundsOnScreen(view).bottom) * 1.5d;
    }
}
