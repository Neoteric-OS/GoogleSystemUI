package com.android.systemui.qs.composefragment.ui;

import android.graphics.Path;
import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationScrimClipNode extends Modifier.Node implements DrawModifierNode {
    public float bottom;
    public float leftInset;
    public float radius;
    public float rightInset;
    public float top;
    public final AndroidPath path = AndroidPath_androidKt.Path();
    public boolean invalidated = true;

    public NotificationScrimClipNode(float f, float f2, float f3, float f4, float f5) {
        this.leftInset = f;
        this.top = f2;
        this.rightInset = f3;
        this.bottom = f4;
        this.radius = f5;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        boolean z = this.invalidated;
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        AndroidPath androidPath = this.path;
        if (z) {
            androidPath.internalPath.rewind();
            if (androidPath == null) {
                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
            }
            Path path = androidPath.internalPath;
            float f = -this.leftInset;
            float f2 = this.top;
            float intBitsToFloat = this.rightInset + Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() >> 32));
            float f3 = this.bottom;
            float f4 = this.radius;
            path.addRoundRect(f, f2, intBitsToFloat, f3, f4, f4, Path.Direction.CW);
            this.invalidated = false;
        }
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
        long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
        canvasDrawScope$drawContext$1.getCanvas().save();
        try {
            canvasDrawScope$drawContext$1.transform.$this_asDrawTransform.getCanvas().mo334clipPathmtrdDE(androidPath, 0);
            layoutNodeDrawScope.drawContent();
        } finally {
            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
        }
    }
}
