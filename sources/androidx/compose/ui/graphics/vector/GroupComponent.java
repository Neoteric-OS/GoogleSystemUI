package androidx.compose.ui.graphics.vector;

import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScopeKt$asDrawTransform$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GroupComponent extends VNode {
    public AndroidPath clipPath;
    public float[] groupMatrix;
    public Lambda invalidateListener;
    public float pivotX;
    public float pivotY;
    public float rotation;
    public float translationX;
    public float translationY;
    public final List children = new ArrayList();
    public boolean isTintable = true;
    public long tintColor = Color.Unspecified;
    public List clipPathData = VectorKt.EmptyPath;
    public boolean isClipPathDirty = true;
    public final Function1 wrappedListener = new Function1() { // from class: androidx.compose.ui.graphics.vector.GroupComponent$wrappedListener$1
        {
            super(1);
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            VNode vNode = (VNode) obj;
            GroupComponent.this.markTintForVNode(vNode);
            ?? r1 = GroupComponent.this.invalidateListener;
            if (r1 != 0) {
                r1.invoke(vNode);
            }
            return Unit.INSTANCE;
        }
    };
    public String name = "";
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public boolean isMatrixDirty = true;

    @Override // androidx.compose.ui.graphics.vector.VNode
    public final void draw(DrawScope drawScope) {
        if (this.isMatrixDirty) {
            float[] fArr = this.groupMatrix;
            if (fArr == null) {
                fArr = Matrix.m379constructorimpl$default();
                this.groupMatrix = fArr;
            } else {
                Matrix.m382resetimpl(fArr);
            }
            Matrix.m384translateimpl(fArr, this.translationX + this.pivotX, this.translationY + this.pivotY);
            float f = this.rotation;
            if (fArr.length >= 16) {
                float f2 = f * 0.0027777778f;
                float floor = f2 - ((float) Math.floor(f2 + 0.5f));
                float abs = Math.abs(floor) * 2.0f;
                float f3 = 1.0f - abs;
                float f4 = ((floor * 8.0f) * f3) / (1.25f - (abs * f3));
                float floor2 = (f2 + 0.25f) - ((float) Math.floor(0.5f + r4));
                float abs2 = Math.abs(floor2) * 2.0f;
                float f5 = 1.0f - abs2;
                float f6 = ((floor2 * 8.0f) * f5) / (1.25f - (abs2 * f5));
                float f7 = fArr[0];
                float f8 = fArr[4];
                float f9 = (f4 * f8) + (f6 * f7);
                float f10 = -f4;
                float f11 = (f8 * f6) + (f7 * f10);
                float f12 = fArr[1];
                float f13 = fArr[5];
                float f14 = (f4 * f13) + (f6 * f12);
                float f15 = (f13 * f6) + (f12 * f10);
                float f16 = fArr[2];
                float f17 = fArr[6];
                float f18 = (f4 * f17) + (f6 * f16);
                float f19 = fArr[3];
                float f20 = fArr[7];
                fArr[0] = f9;
                fArr[1] = f14;
                fArr[2] = f18;
                fArr[3] = (f4 * f20) + (f6 * f19);
                fArr[4] = f11;
                fArr[5] = f15;
                fArr[6] = (f17 * f6) + (f16 * f10);
                fArr[7] = (f6 * f20) + (f10 * f19);
            }
            float f21 = this.scaleX;
            float f22 = this.scaleY;
            if (fArr.length >= 16) {
                fArr[0] = fArr[0] * f21;
                fArr[1] = fArr[1] * f21;
                fArr[2] = fArr[2] * f21;
                fArr[3] = fArr[3] * f21;
                fArr[4] = fArr[4] * f22;
                fArr[5] = fArr[5] * f22;
                fArr[6] = fArr[6] * f22;
                fArr[7] = fArr[7] * f22;
                fArr[8] = fArr[8] * 1.0f;
                fArr[9] = fArr[9] * 1.0f;
                fArr[10] = fArr[10] * 1.0f;
                fArr[11] = fArr[11] * 1.0f;
            }
            Matrix.m384translateimpl(fArr, -this.pivotX, -this.pivotY);
            this.isMatrixDirty = false;
        }
        if (this.isClipPathDirty) {
            if (!this.clipPathData.isEmpty()) {
                AndroidPath androidPath = this.clipPath;
                if (androidPath == null) {
                    androidPath = AndroidPath_androidKt.Path();
                    this.clipPath = androidPath;
                }
                PathParserKt.toPath(this.clipPathData, androidPath);
            }
            this.isClipPathDirty = false;
        }
        CanvasDrawScope$drawContext$1 drawContext = drawScope.getDrawContext();
        long m418getSizeNHjbRc = drawContext.m418getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            CanvasDrawScopeKt$asDrawTransform$1 canvasDrawScopeKt$asDrawTransform$1 = drawContext.transform;
            float[] fArr2 = this.groupMatrix;
            CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScopeKt$asDrawTransform$1.$this_asDrawTransform;
            if (fArr2 != null) {
                canvasDrawScope$drawContext$1.getCanvas().mo336concat58bKbWc(fArr2);
            }
            AndroidPath androidPath2 = this.clipPath;
            if (!this.clipPathData.isEmpty() && androidPath2 != null) {
                canvasDrawScope$drawContext$1.getCanvas().mo334clipPathmtrdDE(androidPath2, 1);
            }
            ArrayList arrayList = (ArrayList) this.children;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((VNode) arrayList.get(i)).draw(drawScope);
            }
        } finally {
            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(drawContext, m418getSizeNHjbRc);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.graphics.vector.VNode
    public final Function1 getInvalidateListener$ui_release() {
        return this.invalidateListener;
    }

    public final void insertAt(int i, VNode vNode) {
        if (i < ((ArrayList) this.children).size()) {
            this.children.set(i, vNode);
        } else {
            this.children.add(vNode);
        }
        markTintForVNode(vNode);
        vNode.setInvalidateListener$ui_release(this.wrappedListener);
        invalidate();
    }

    /* renamed from: markTintForColor-8_81llA, reason: not valid java name */
    public final void m438markTintForColor8_81llA(long j) {
        if (this.isTintable && j != 16) {
            long j2 = this.tintColor;
            if (j2 == 16) {
                this.tintColor = j;
                return;
            }
            EmptyList emptyList = VectorKt.EmptyPath;
            if (Color.m368getRedimpl(j2) == Color.m368getRedimpl(j) && Color.m367getGreenimpl(j2) == Color.m367getGreenimpl(j) && Color.m365getBlueimpl(j2) == Color.m365getBlueimpl(j)) {
                return;
            }
            this.isTintable = false;
            this.tintColor = Color.Unspecified;
        }
    }

    public final void markTintForVNode(VNode vNode) {
        if (!(vNode instanceof PathComponent)) {
            if (vNode instanceof GroupComponent) {
                GroupComponent groupComponent = (GroupComponent) vNode;
                if (groupComponent.isTintable && this.isTintable) {
                    m438markTintForColor8_81llA(groupComponent.tintColor);
                    return;
                } else {
                    this.isTintable = false;
                    this.tintColor = Color.Unspecified;
                    return;
                }
            }
            return;
        }
        PathComponent pathComponent = (PathComponent) vNode;
        Brush brush = pathComponent.fill;
        if (this.isTintable && brush != null) {
            if (brush instanceof SolidColor) {
                m438markTintForColor8_81llA(((SolidColor) brush).value);
            } else {
                this.isTintable = false;
                this.tintColor = Color.Unspecified;
            }
        }
        Brush brush2 = pathComponent.stroke;
        if (this.isTintable && brush2 != null) {
            if (brush2 instanceof SolidColor) {
                m438markTintForColor8_81llA(((SolidColor) brush2).value);
            } else {
                this.isTintable = false;
                this.tintColor = Color.Unspecified;
            }
        }
    }

    public final void remove(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (i < ((ArrayList) this.children).size()) {
                ((VNode) ((ArrayList) this.children).get(i)).setInvalidateListener$ui_release(null);
                this.children.remove(i);
            }
        }
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.graphics.vector.VNode
    public final void setInvalidateListener$ui_release(Function1 function1) {
        this.invalidateListener = (Lambda) function1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("VGroup: ");
        sb.append(this.name);
        List list = this.children;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            VNode vNode = (VNode) ((ArrayList) list).get(i);
            sb.append("\t");
            sb.append(vNode.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
