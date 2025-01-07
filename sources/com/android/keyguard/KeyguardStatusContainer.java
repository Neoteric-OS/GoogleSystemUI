package com.android.keyguard;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.android.keyguard.KeyguardClockFrame;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardStatusContainer extends LinearLayout {
    public int drawAlpha;

    public KeyguardStatusContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawAlpha = 255;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(final Canvas canvas) {
        int i = KeyguardClockFrame.$r8$clinit;
        KeyguardClockFrame.Companion.saveCanvasAlpha(this, canvas, this.drawAlpha, new Function1() { // from class: com.android.keyguard.KeyguardStatusContainer$dispatchDraw$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.widget.LinearLayout*/.dispatchDraw(canvas);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // android.view.View
    public final boolean onSetAlpha(int i) {
        this.drawAlpha = i;
        return true;
    }
}
