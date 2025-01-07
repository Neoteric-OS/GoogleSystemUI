package androidx.compose.material.ripple;

import androidx.collection.MutableScatterMap;
import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommonRippleNode extends RippleNode {
    public final MutableScatterMap ripples;

    public CommonRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0) {
        super(interactionSource, z, f, colorProducer, function0);
        this.ripples = new MutableScatterMap();
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void addRipple(PressInteraction$Press pressInteraction$Press) {
        MutableScatterMap mutableScatterMap = this.ripples;
        Object[] objArr = mutableScatterMap.keys;
        Object[] objArr2 = mutableScatterMap.values;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            RippleAnimation rippleAnimation = (RippleAnimation) objArr2[i4];
                            ((SnapshotMutableStateImpl) rippleAnimation.finishRequested$delegate).setValue(Boolean.TRUE);
                            rippleAnimation.finishSignalDeferred.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Unit.INSTANCE);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                } else {
                    i++;
                }
            }
        }
        boolean z = this.bounded;
        RippleAnimation rippleAnimation2 = new RippleAnimation(z ? new Offset(pressInteraction$Press.pressPosition) : null, this.targetRadius, z);
        mutableScatterMap.set(pressInteraction$Press, rippleAnimation2);
        BuildersKt.launch$default(getCoroutineScope(), null, null, new CommonRippleNode$addRipple$2(rippleAnimation2, this, pressInteraction$Press, null), 3);
        DrawModifierNodeKt.invalidateDraw(this);
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void drawRipples(LayoutNodeDrawScope layoutNodeDrawScope) {
        long[] jArr;
        Object[] objArr;
        Object[] objArr2;
        long[] jArr2;
        Object[] objArr3;
        Object[] objArr4;
        int i;
        int i2;
        long Color;
        long Color2;
        CommonRippleNode commonRippleNode = this;
        ((RippleAlpha) commonRippleNode.rippleAlpha.invoke()).getClass();
        MutableScatterMap mutableScatterMap = commonRippleNode.ripples;
        Object[] objArr5 = mutableScatterMap.keys;
        Object[] objArr6 = mutableScatterMap.values;
        long[] jArr3 = mutableScatterMap.metadata;
        int length = jArr3.length - 2;
        if (length < 0) {
            return;
        }
        int i3 = 0;
        while (true) {
            long j = jArr3[i3];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i4 = 8;
                int i5 = 8 - ((~(i3 - length)) >>> 31);
                int i6 = 0;
                while (i6 < i5) {
                    if ((255 & j) < 128) {
                        int i7 = (i3 << 3) + i6;
                        Object obj = objArr5[i7];
                        RippleAnimation rippleAnimation = (RippleAnimation) objArr6[i7];
                        Color = ColorKt.Color(Color.m368getRedimpl(r13), Color.m367getGreenimpl(r13), Color.m365getBlueimpl(r13), 0.1f, Color.m366getColorSpaceimpl(commonRippleNode.color.mo206invoke0d7_KjU()));
                        Float f = rippleAnimation.startRadius;
                        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                        if (f == null) {
                            long mo432getSizeNHjbRc = canvasDrawScope.mo432getSizeNHjbRc();
                            float f2 = RippleAnimationKt.BoundedRippleExtraRadius;
                            rippleAnimation.startRadius = Float.valueOf(Math.max(Size.m329getWidthimpl(mo432getSizeNHjbRc), Size.m327getHeightimpl(mo432getSizeNHjbRc)) * 0.3f);
                        }
                        jArr2 = jArr3;
                        if (rippleAnimation.origin == null) {
                            objArr3 = objArr5;
                            rippleAnimation.origin = new Offset(canvasDrawScope.mo431getCenterF1C5BW0());
                        } else {
                            objArr3 = objArr5;
                        }
                        if (rippleAnimation.targetCenter == null) {
                            rippleAnimation.targetCenter = new Offset(OffsetKt.Offset(Size.m329getWidthimpl(canvasDrawScope.mo432getSizeNHjbRc()) / 2.0f, Size.m327getHeightimpl(canvasDrawScope.mo432getSizeNHjbRc()) / 2.0f));
                        }
                        float floatValue = (!((Boolean) ((SnapshotMutableStateImpl) rippleAnimation.finishRequested$delegate).getValue()).booleanValue() || ((Boolean) ((SnapshotMutableStateImpl) rippleAnimation.finishedFadingIn$delegate).getValue()).booleanValue()) ? ((Number) rippleAnimation.animatedAlpha.internalState.getValue()).floatValue() : 1.0f;
                        Float f3 = rippleAnimation.startRadius;
                        Intrinsics.checkNotNull(f3);
                        objArr4 = objArr6;
                        float lerp = MathHelpersKt.lerp(f3.floatValue(), rippleAnimation.radius, ((Number) rippleAnimation.animatedRadiusPercent.internalState.getValue()).floatValue());
                        Offset offset = rippleAnimation.origin;
                        Intrinsics.checkNotNull(offset);
                        float m312getXimpl = Offset.m312getXimpl(offset.packedValue);
                        Offset offset2 = rippleAnimation.targetCenter;
                        Intrinsics.checkNotNull(offset2);
                        float m312getXimpl2 = Offset.m312getXimpl(offset2.packedValue);
                        Animatable animatable = rippleAnimation.animatedCenterPercent;
                        float lerp2 = MathHelpersKt.lerp(m312getXimpl, m312getXimpl2, ((Number) animatable.internalState.getValue()).floatValue());
                        Offset offset3 = rippleAnimation.origin;
                        Intrinsics.checkNotNull(offset3);
                        i = length;
                        float m313getYimpl = Offset.m313getYimpl(offset3.packedValue);
                        Offset offset4 = rippleAnimation.targetCenter;
                        Intrinsics.checkNotNull(offset4);
                        long Offset = OffsetKt.Offset(lerp2, MathHelpersKt.lerp(m313getYimpl, Offset.m313getYimpl(offset4.packedValue), ((Number) animatable.internalState.getValue()).floatValue()));
                        Color2 = ColorKt.Color(Color.m368getRedimpl(Color), Color.m367getGreenimpl(Color), Color.m365getBlueimpl(Color), Color.m364getAlphaimpl(Color) * floatValue, Color.m366getColorSpaceimpl(Color));
                        if (rippleAnimation.bounded) {
                            float m329getWidthimpl = Size.m329getWidthimpl(canvasDrawScope.mo432getSizeNHjbRc());
                            float m327getHeightimpl = Size.m327getHeightimpl(canvasDrawScope.mo432getSizeNHjbRc());
                            CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                            long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
                            canvasDrawScope$drawContext$1.getCanvas().save();
                            canvasDrawScope$drawContext$1.transform.m420clipRectN_I0leg(0.0f, 0.0f, m329getWidthimpl, m327getHeightimpl, 1);
                            DrawScope.m422drawCircleVaOC9Bg$default(layoutNodeDrawScope, Color2, lerp, Offset, 0.0f, 120);
                            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                        } else {
                            DrawScope.m422drawCircleVaOC9Bg$default(layoutNodeDrawScope, Color2, lerp, Offset, 0.0f, 120);
                        }
                        i2 = 8;
                    } else {
                        jArr2 = jArr3;
                        objArr3 = objArr5;
                        objArr4 = objArr6;
                        i = length;
                        i2 = i4;
                    }
                    j >>= i2;
                    i6++;
                    i4 = i2;
                    jArr3 = jArr2;
                    objArr5 = objArr3;
                    objArr6 = objArr4;
                    length = i;
                    commonRippleNode = this;
                }
                jArr = jArr3;
                objArr = objArr5;
                objArr2 = objArr6;
                int i8 = length;
                if (i5 != i4) {
                    return;
                } else {
                    length = i8;
                }
            } else {
                jArr = jArr3;
                objArr = objArr5;
                objArr2 = objArr6;
            }
            if (i3 == length) {
                return;
            }
            i3++;
            commonRippleNode = this;
            jArr3 = jArr;
            objArr5 = objArr;
            objArr6 = objArr2;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.ripples.clear();
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void removeRipple(PressInteraction$Press pressInteraction$Press) {
        RippleAnimation rippleAnimation = (RippleAnimation) this.ripples.get(pressInteraction$Press);
        if (rippleAnimation != null) {
            ((SnapshotMutableStateImpl) rippleAnimation.finishRequested$delegate).setValue(Boolean.TRUE);
            rippleAnimation.finishSignalDeferred.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Unit.INSTANCE);
        }
    }
}
