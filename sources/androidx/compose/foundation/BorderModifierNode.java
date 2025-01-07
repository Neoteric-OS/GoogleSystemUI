package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.node.DelegatingNode;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BorderModifierNode extends DelegatingNode {
    public BorderCache borderCache;
    public SolidColor brush;
    public final CacheDrawModifierNode drawWithCacheModifierNode;
    public Shape shape;
    public float width;

    public BorderModifierNode(float f, SolidColor solidColor, Shape shape) {
        this.width = f;
        this.brush = solidColor;
        this.shape = shape;
        CacheDrawModifierNode CacheDrawModifierNode = DrawModifierKt.CacheDrawModifierNode(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$drawWithCacheModifierNode$1
            {
                super(1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:36:0x017a, code lost:
            
                if (r10 != false) goto L48;
             */
            /* JADX WARN: Code restructure failed: missing block: B:43:0x01bf, code lost:
            
                if (r9 != false) goto L57;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r4v20, types: [androidx.compose.ui.graphics.Canvas] */
            /* JADX WARN: Type inference failed for: r4v21 */
            /* JADX WARN: Type inference failed for: r4v23 */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r39) {
                /*
                    Method dump skipped, instructions count: 1011
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BorderModifierNode$drawWithCacheModifierNode$1.invoke(java.lang.Object):java.lang.Object");
            }
        });
        delegate(CacheDrawModifierNode);
        this.drawWithCacheModifierNode = CacheDrawModifierNode;
    }
}
