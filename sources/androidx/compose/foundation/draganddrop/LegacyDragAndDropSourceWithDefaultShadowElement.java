package androidx.compose.foundation.draganddrop;

import android.graphics.Picture;
import androidx.compose.foundation.draganddrop.LegacyDragSourceNodeWithDefaultPainter;
import androidx.compose.foundation.draganddrop.LegacyDragSourceNodeWithDefaultPainter.AnonymousClass2;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.draganddrop.DragAndDropNode;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.ViewConfiguration;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LegacyDragAndDropSourceWithDefaultShadowElement extends ModifierNodeElement {
    public final Function2 dragAndDropSourceHandler;

    public LegacyDragAndDropSourceWithDefaultShadowElement(Function2 function2) {
        this.dragAndDropSourceHandler = function2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LegacyDragSourceNodeWithDefaultPainter legacyDragSourceNodeWithDefaultPainter = new LegacyDragSourceNodeWithDefaultPainter();
        legacyDragSourceNodeWithDefaultPainter.dragAndDropSourceHandler = this.dragAndDropSourceHandler;
        final CacheDrawScopeDragShadowCallback cacheDrawScopeDragShadowCallback = new CacheDrawScopeDragShadowCallback();
        legacyDragSourceNodeWithDefaultPainter.delegate(DrawModifierKt.CacheDrawModifierNode(new LegacyDragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1(1, cacheDrawScopeDragShadowCallback, CacheDrawScopeDragShadowCallback.class, "cachePicture", "cachePicture(Landroidx/compose/ui/draw/CacheDrawScope;)Landroidx/compose/ui/draw/DrawResult;", 0)));
        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.draganddrop.LegacyDragSourceNodeWithDefaultPainter.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DrawScope drawScope = (DrawScope) obj;
                Picture picture = CacheDrawScopeDragShadowCallback.this.cachedPicture;
                if (picture == null) {
                    throw new IllegalArgumentException("No cached drag shadow. Check if Modifier.cacheDragShadow(painter) was called.");
                }
                Canvas canvas = drawScope.getDrawContext().getCanvas();
                android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
                ((AndroidCanvas) canvas).internalCanvas.drawPicture(picture);
                return Unit.INSTANCE;
            }
        };
        LegacyDragSourceNodeWithDefaultPainter.AnonymousClass2 anonymousClass2 = legacyDragSourceNodeWithDefaultPainter.new AnonymousClass2(null);
        final LegacyDragAndDropSourceNode legacyDragAndDropSourceNode = new LegacyDragAndDropSourceNode();
        legacyDragAndDropSourceNode.drawDragDecoration = function1;
        legacyDragAndDropSourceNode.dragAndDropSourceHandler = anonymousClass2;
        final DragAndDropNode dragAndDropNode = new DragAndDropNode(2, null);
        legacyDragAndDropSourceNode.delegate(dragAndDropNode);
        legacyDragAndDropSourceNode.delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.draganddrop.LegacyDragAndDropSourceNode.1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: androidx.compose.foundation.draganddrop.LegacyDragAndDropSourceNode$1$1, reason: invalid class name and collision with other inner class name */
            public final class C00011 implements DragAndDropSourceScope, PointerInputScope {
                public final /* synthetic */ PointerInputScope $$delegate_0;
                public final /* synthetic */ DragAndDropModifierNode $dragAndDropModifierNode;
                public final /* synthetic */ LegacyDragAndDropSourceNode this$0;

                public C00011(PointerInputScope pointerInputScope, DragAndDropModifierNode dragAndDropModifierNode, LegacyDragAndDropSourceNode legacyDragAndDropSourceNode) {
                    this.$dragAndDropModifierNode = dragAndDropModifierNode;
                    this.this$0 = legacyDragAndDropSourceNode;
                    this.$$delegate_0 = pointerInputScope;
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputScope
                public final Object awaitPointerEventScope(Continuation continuation, Function2 function2) {
                    return this.$$delegate_0.awaitPointerEventScope(continuation, function2);
                }

                @Override // androidx.compose.ui.unit.Density
                public final float getDensity() {
                    return this.$$delegate_0.getDensity();
                }

                @Override // androidx.compose.ui.unit.FontScaling
                public final float getFontScale() {
                    return this.$$delegate_0.getFontScale();
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputScope
                /* renamed from: getSize-YbymL2g, reason: not valid java name */
                public final long mo44getSizeYbymL2g() {
                    return this.$$delegate_0.mo44getSizeYbymL2g();
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputScope
                public final ViewConfiguration getViewConfiguration() {
                    return this.$$delegate_0.getViewConfiguration();
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: roundToPx-0680j_4, reason: not valid java name */
                public final int mo45roundToPx0680j_4(float f) {
                    return this.$$delegate_0.mo45roundToPx0680j_4(f);
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputScope
                public final void setInterceptOutOfBoundsChildEvents() {
                    this.$$delegate_0.setInterceptOutOfBoundsChildEvents();
                }

                @Override // androidx.compose.ui.unit.FontScaling
                /* renamed from: toDp-GaN1DYA, reason: not valid java name */
                public final float mo46toDpGaN1DYA(long j) {
                    return this.$$delegate_0.mo46toDpGaN1DYA(j);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toDp-u2uoSUM, reason: not valid java name */
                public final float mo47toDpu2uoSUM(float f) {
                    return this.$$delegate_0.mo47toDpu2uoSUM(f);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toDpSize-k-rfVVM, reason: not valid java name */
                public final long mo49toDpSizekrfVVM(long j) {
                    return this.$$delegate_0.mo49toDpSizekrfVVM(j);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toPx--R2X_6o, reason: not valid java name */
                public final float mo50toPxR2X_6o(long j) {
                    return this.$$delegate_0.mo50toPxR2X_6o(j);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toPx-0680j_4, reason: not valid java name */
                public final float mo51toPx0680j_4(float f) {
                    return this.$$delegate_0.mo51toPx0680j_4(f);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toSize-XkaWNTQ, reason: not valid java name */
                public final long mo52toSizeXkaWNTQ(long j) {
                    return this.$$delegate_0.mo52toSizeXkaWNTQ(j);
                }

                @Override // androidx.compose.ui.unit.FontScaling
                /* renamed from: toSp-0xMU5do, reason: not valid java name */
                public final long mo53toSp0xMU5do(float f) {
                    return this.$$delegate_0.mo53toSp0xMU5do(f);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
                public final long mo54toSpkPz2Gy4(float f) {
                    return this.$$delegate_0.mo54toSpkPz2Gy4(f);
                }

                @Override // androidx.compose.ui.unit.Density
                /* renamed from: toDp-u2uoSUM, reason: not valid java name */
                public final float mo48toDpu2uoSUM(int i) {
                    return this.$$delegate_0.mo48toDpu2uoSUM(i);
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                LegacyDragAndDropSourceNode legacyDragAndDropSourceNode2 = LegacyDragAndDropSourceNode.this;
                Object invoke = ((LegacyDragSourceNodeWithDefaultPainter.AnonymousClass2) legacyDragAndDropSourceNode2.dragAndDropSourceHandler).invoke(new C00011(pointerInputScope, dragAndDropNode, legacyDragAndDropSourceNode2), continuation);
                return invoke == CoroutineSingletons.COROUTINE_SUSPENDED ? invoke : Unit.INSTANCE;
            }
        }));
        legacyDragSourceNodeWithDefaultPainter.delegate(legacyDragAndDropSourceNode);
        return legacyDragSourceNodeWithDefaultPainter;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LegacyDragAndDropSourceWithDefaultShadowElement)) {
            return false;
        }
        return Intrinsics.areEqual(this.dragAndDropSourceHandler, ((LegacyDragAndDropSourceWithDefaultShadowElement) obj).dragAndDropSourceHandler);
    }

    public final int hashCode() {
        return this.dragAndDropSourceHandler.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((LegacyDragSourceNodeWithDefaultPainter) node).dragAndDropSourceHandler = this.dragAndDropSourceHandler;
    }
}
