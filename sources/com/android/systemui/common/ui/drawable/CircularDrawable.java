package com.android.systemui.common.ui.drawable;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CircularDrawable extends DrawableWrapper {
    public final Lazy path$delegate;

    public CircularDrawable(Drawable drawable) {
        super(drawable);
        this.path$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.common.ui.drawable.CircularDrawable$path$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new Path();
            }
        });
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath((Path) this.path$delegate.getValue());
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.draw(canvas);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        ((Path) this.path$delegate.getValue()).reset();
        ((Path) this.path$delegate.getValue()).addCircle(getBounds().centerX(), getBounds().centerY(), Math.min(getBounds().width(), getBounds().height()) / 2.0f, Path.Direction.CW);
    }
}
