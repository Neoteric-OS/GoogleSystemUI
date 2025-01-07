package com.android.systemui.animation.back;

import android.util.DisplayMetrics;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OnBackAnimationCallbackExtensionKt {
    /* JADX WARN: Type inference failed for: r7v0, types: [com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$4] */
    public static OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$4 onBackAnimationCallbackFrom$default(final BackAnimationSpec backAnimationSpec, final DisplayMetrics displayMetrics, final Function1 function1, final Function0 function0) {
        final OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$1 onBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$1 = new Function1() { // from class: com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        final OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$3 onBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$3 = new Function0() { // from class: com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        return new OnBackAnimationCallback() { // from class: com.android.systemui.animation.back.OnBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$4
            public float initialY;
            public final BackTransformation lastTransformation;

            {
                BackTransformation backTransformation = new BackTransformation();
                backTransformation.translateX = Float.NaN;
                backTransformation.translateY = Float.NaN;
                backTransformation.scale = Float.NaN;
                backTransformation.scalePivotPosition = null;
                this.lastTransformation = backTransformation;
            }

            @Override // android.window.OnBackAnimationCallback
            public final void onBackCancelled() {
                onBackAnimationCallbackExtensionKt$onBackAnimationCallbackFrom$3.invoke();
            }

            @Override // android.window.OnBackInvokedCallback
            public final void onBackInvoked() {
                function0.invoke();
            }

            @Override // android.window.OnBackAnimationCallback
            public final void onBackProgressed(BackEvent backEvent) {
                backAnimationSpec.getBackTransformation(backEvent, (backEvent.getTouchY() - this.initialY) / displayMetrics.heightPixels, this.lastTransformation);
                function1.invoke(this.lastTransformation);
            }

            @Override // android.window.OnBackAnimationCallback
            public final void onBackStarted(BackEvent backEvent) {
                this.initialY = backEvent.getTouchY();
                Function1.this.invoke(backEvent);
            }
        };
    }
}
