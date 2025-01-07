package androidx.compose.material.ripple;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidRippleNode extends RippleNode implements RippleHostKey {
    public RippleContainer rippleContainer;
    public RippleHostView rippleHostView;
    public long rippleSize;

    @Override // androidx.compose.material.ripple.RippleNode
    public final void addRipple(PressInteraction$Press pressInteraction$Press) {
        RippleContainer rippleContainer = this.rippleContainer;
        if (rippleContainer == null) {
            Object obj = (View) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, AndroidCompositionLocals_androidKt.LocalView);
            boolean z = Ripple_androidKt.IsRunningInPreview;
            while (!(obj instanceof ViewGroup)) {
                Object parent = ((View) obj).getParent();
                if (!(parent instanceof View)) {
                    throw new IllegalArgumentException(("Couldn't find a valid parent for " + obj + ". Are you overriding LocalView and providing a View that is not attached to the view hierarchy?").toString());
                }
                obj = parent;
            }
            ViewGroup viewGroup = (ViewGroup) obj;
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    RippleContainer rippleContainer2 = new RippleContainer(viewGroup.getContext());
                    viewGroup.addView(rippleContainer2);
                    rippleContainer = rippleContainer2;
                    break;
                } else {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt instanceof RippleContainer) {
                        rippleContainer = (RippleContainer) childAt;
                        break;
                    }
                    i++;
                }
            }
            this.rippleContainer = rippleContainer;
            Intrinsics.checkNotNull(rippleContainer);
        }
        RippleHostView rippleHostView = (RippleHostView) rippleContainer.rippleHostMap.indicationToHostMap.get(this);
        if (rippleHostView == null) {
            List list = rippleContainer.unusedRippleHosts;
            rippleHostView = (RippleHostView) (list.isEmpty() ? null : list.remove(0));
            if (rippleHostView == null) {
                if (rippleContainer.nextHostIndex > CollectionsKt__CollectionsKt.getLastIndex(rippleContainer.rippleHosts)) {
                    rippleHostView = new RippleHostView(rippleContainer.getContext());
                    rippleContainer.addView(rippleHostView);
                    rippleContainer.rippleHosts.add(rippleHostView);
                } else {
                    rippleHostView = (RippleHostView) ((ArrayList) rippleContainer.rippleHosts).get(rippleContainer.nextHostIndex);
                    RippleHostKey rippleHostKey = (RippleHostKey) rippleContainer.rippleHostMap.hostToIndicationMap.get(rippleHostView);
                    if (rippleHostKey != null) {
                        AndroidRippleNode androidRippleNode = (AndroidRippleNode) rippleHostKey;
                        androidRippleNode.rippleHostView = null;
                        DrawModifierNodeKt.invalidateDraw(androidRippleNode);
                        RippleHostMap rippleHostMap = rippleContainer.rippleHostMap;
                        RippleHostView rippleHostView2 = (RippleHostView) rippleHostMap.indicationToHostMap.get(rippleHostKey);
                        if (rippleHostView2 != null) {
                        }
                        rippleHostMap.indicationToHostMap.remove(rippleHostKey);
                        rippleHostView.disposeRipple();
                    }
                }
                int i2 = rippleContainer.nextHostIndex;
                if (i2 < rippleContainer.MaxRippleHosts - 1) {
                    rippleContainer.nextHostIndex = i2 + 1;
                } else {
                    rippleContainer.nextHostIndex = 0;
                }
            }
            RippleHostMap rippleHostMap2 = rippleContainer.rippleHostMap;
            rippleHostMap2.indicationToHostMap.put(this, rippleHostView);
            rippleHostMap2.hostToIndicationMap.put(rippleHostView, this);
        }
        long j = this.rippleSize;
        int roundToInt = MathKt.roundToInt(this.targetRadius);
        long mo206invoke0d7_KjU = this.color.mo206invoke0d7_KjU();
        ((RippleAlpha) this.rippleAlpha.invoke()).getClass();
        Function0 function0 = new Function0() { // from class: androidx.compose.material.ripple.AndroidRippleNode$addRipple$1$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                DrawModifierNodeKt.invalidateDraw(AndroidRippleNode.this);
                return Unit.INSTANCE;
            }
        };
        UnprojectedRipple unprojectedRipple = rippleHostView.ripple;
        boolean z2 = this.bounded;
        if (unprojectedRipple == null || !Boolean.valueOf(z2).equals(rippleHostView.bounded)) {
            UnprojectedRipple unprojectedRipple2 = new UnprojectedRipple(z2);
            rippleHostView.setBackground(unprojectedRipple2);
            rippleHostView.ripple = unprojectedRipple2;
            rippleHostView.bounded = Boolean.valueOf(z2);
        }
        UnprojectedRipple unprojectedRipple3 = rippleHostView.ripple;
        Intrinsics.checkNotNull(unprojectedRipple3);
        rippleHostView.onInvalidateRipple = function0;
        rippleHostView.m192updateRipplePropertiesbiQXAtU(roundToInt, j, mo206invoke0d7_KjU);
        if (z2) {
            unprojectedRipple3.setHotspot(Offset.m312getXimpl(pressInteraction$Press.pressPosition), Offset.m313getYimpl(pressInteraction$Press.pressPosition));
        } else {
            unprojectedRipple3.setHotspot(unprojectedRipple3.getBounds().centerX(), unprojectedRipple3.getBounds().centerY());
        }
        rippleHostView.setRippleState(true);
        this.rippleHostView = rippleHostView;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void drawRipples(LayoutNodeDrawScope layoutNodeDrawScope) {
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        this.rippleSize = canvasDrawScope.mo432getSizeNHjbRc();
        Canvas canvas = canvasDrawScope.drawContext.getCanvas();
        RippleHostView rippleHostView = this.rippleHostView;
        if (rippleHostView != null) {
            long mo432getSizeNHjbRc = canvasDrawScope.mo432getSizeNHjbRc();
            int roundToInt = MathKt.roundToInt(this.targetRadius);
            long mo206invoke0d7_KjU = this.color.mo206invoke0d7_KjU();
            ((RippleAlpha) this.rippleAlpha.invoke()).getClass();
            rippleHostView.m192updateRipplePropertiesbiQXAtU(roundToInt, mo432getSizeNHjbRc, mo206invoke0d7_KjU);
            android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
            rippleHostView.draw(((AndroidCanvas) canvas).internalCanvas);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        RippleContainer rippleContainer = this.rippleContainer;
        if (rippleContainer != null) {
            this.rippleHostView = null;
            DrawModifierNodeKt.invalidateDraw(this);
            RippleHostView rippleHostView = (RippleHostView) rippleContainer.rippleHostMap.indicationToHostMap.get(this);
            if (rippleHostView != null) {
                rippleHostView.disposeRipple();
                RippleHostMap rippleHostMap = rippleContainer.rippleHostMap;
                RippleHostView rippleHostView2 = (RippleHostView) rippleHostMap.indicationToHostMap.get(this);
                if (rippleHostView2 != null) {
                }
                rippleHostMap.indicationToHostMap.remove(this);
                rippleContainer.unusedRippleHosts.add(rippleHostView);
            }
        }
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void removeRipple(PressInteraction$Press pressInteraction$Press) {
        RippleHostView rippleHostView = this.rippleHostView;
        if (rippleHostView != null) {
            rippleHostView.setRippleState(false);
        }
    }
}
