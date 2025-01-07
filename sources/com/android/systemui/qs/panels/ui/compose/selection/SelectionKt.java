package com.android.systemui.qs.panels.ui.compose.selection;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.InteractiveComponentSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SelectionKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0057  */
    /* renamed from: ResizingDot-FNF3uiM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m847ResizingDotFNF3uiM(final kotlin.jvm.functions.Function0 r13, androidx.compose.ui.Modifier r14, long r15, androidx.compose.runtime.Composer r17, final int r18, final int r19) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt.m847ResizingDotFNF3uiM(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, long, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void ResizingHandle(final boolean z, final MutableSelectionState mutableSelectionState, final Function0 function0, final Function0 function02, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(964834964);
        if ((i2 & 8) != 0) {
            function02 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return null;
                }
            };
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (z) {
            composerImpl.startReplaceGroup(1210377749);
            float f = ((Dp) composerImpl.consume(InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize)).value;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(SizeKt.m113size3ABfNKs(companion, f), Unit.INSTANCE, new PointerInputEventHandler() { // from class: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$2

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$2$1, reason: invalid class name */
                final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ((MutableSelectionState) this.receiver).onResizingDragEnd();
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$2$2, reason: invalid class name */
                final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0 {
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ((MutableSelectionState) this.receiver).onResizingDragEnd();
                        return Unit.INSTANCE;
                    }
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(0, MutableSelectionState.this, MutableSelectionState.class, "onResizingDragEnd", "onResizingDragEnd()V", 0);
                    final MutableSelectionState mutableSelectionState2 = MutableSelectionState.this;
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(0, mutableSelectionState2, MutableSelectionState.class, "onResizingDragEnd", "onResizingDragEnd()V", 0);
                    final Function0 function03 = function02;
                    Object detectHorizontalDragGestures = DragGestureDetectorKt.detectHorizontalDragGestures(pointerInputScope, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$2.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            long j = ((Offset) obj).packedValue;
                            TileWidths tileWidths = (TileWidths) function03.invoke();
                            if (tileWidths != null) {
                                MutableSelectionState mutableSelectionState3 = mutableSelectionState2;
                                Selection selection = (Selection) ((SnapshotMutableStateImpl) mutableSelectionState3._selection).getValue();
                                if (selection != null) {
                                    ((SnapshotMutableStateImpl) mutableSelectionState3._resizingState).setValue(new ResizingState(tileWidths, new MutableSelectionState$onResizingDragStart$1$1(mutableSelectionState3, selection)));
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }, anonymousClass1, anonymousClass2, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$2.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            float floatValue = ((Number) obj2).floatValue();
                            ResizingState resizingState = (ResizingState) ((SnapshotMutableStateImpl) MutableSelectionState.this._resizingState).getValue();
                            if (resizingState != null) {
                                float f2 = resizingState.totalOffset + floatValue;
                                resizingState.totalOffset = f2;
                                TileWidths tileWidths = resizingState.widths;
                                int coerceIn = RangesKt.coerceIn((int) (tileWidths.base + f2), tileWidths.min, tileWidths.max);
                                MutableIntState mutableIntState = resizingState.width$delegate;
                                ((SnapshotMutableIntStateImpl) mutableIntState).setIntValue(coerceIn);
                                int intValue = ((SnapshotMutableIntStateImpl) mutableIntState).getIntValue();
                                int i3 = tileWidths.min;
                                boolean z2 = RangesKt.coerceIn(((float) (intValue - i3)) / ((float) (tileWidths.max - i3)), 0.0f, 1.0f) >= 0.25f;
                                if (resizingState.passedThreshold != z2) {
                                    resizingState.passedThreshold = z2;
                                    ((MutableSelectionState$onResizingDragStart$1$1) resizingState.onResize).invoke();
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }, continuation);
                    return detectHorizontalDragGestures == CoroutineSingletons.COROUTINE_SUSPENDED ? detectHorizontalDragGestures : Unit.INSTANCE;
                }
            });
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, pointerInput);
            ComposeUiNode.Companion.getClass();
            Function0 function03 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function03);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            m847ResizingDotFNF3uiM(function0, BoxScopeInstance.INSTANCE.align(companion, Alignment.Companion.Center), 0L, composerImpl, (i >> 6) & 14, 4);
            composerImpl.end(true);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(1210378442);
            m847ResizingDotFNF3uiM(function0, null, 0L, composerImpl, (i >> 6) & 14, 6);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Function0 function04 = function02;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.selection.SelectionKt$ResizingHandle$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SelectionKt.ResizingHandle(z, mutableSelectionState, function0, function04, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
