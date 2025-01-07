package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.LongPressTextDragObserverKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldSelectionManagerKt {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void TextFieldSelectionHandle(final boolean z, final ResolvedTextDirection resolvedTextDirection, final TextFieldSelectionManager textFieldSelectionManager, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1344558920);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changed(resolvedTextDirection) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(textFieldSelectionManager) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            int i3 = i2 & 14;
            boolean changed = (i3 == 4) | composerImpl.changed(textFieldSelectionManager);
            Object rememberedValue = composerImpl.rememberedValue();
            Object obj = Composer.Companion.Empty;
            if (changed || rememberedValue == obj) {
                textFieldSelectionManager.getClass();
                rememberedValue = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
                    @Override // androidx.compose.foundation.text.TextDragObserver
                    /* renamed from: onDown-k-4lQ0M */
                    public final void mo159onDownk4lQ0M() {
                        TextLayoutResultProxy layoutResult;
                        boolean z2 = z;
                        Handle handle = z2 ? Handle.SelectionStart : Handle.SelectionEnd;
                        TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.draggingHandle$delegate).setValue(handle);
                        long m185getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m185getAdjustedCoordinatesk4lQ0M(textFieldSelectionManager2.m191getHandlePositiontuRUvjQ$foundation_release(z2));
                        LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager2.state;
                        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null) {
                            return;
                        }
                        long m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release = layoutResult.m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release(m185getAdjustedCoordinatesk4lQ0M);
                        textFieldSelectionManager2.dragBeginPosition = m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release;
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(new Offset(m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release));
                        textFieldSelectionManager2.dragTotalDistance = 0L;
                        textFieldSelectionManager2.previousRawDragOffset = -1;
                        LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager2.state;
                        if (legacyTextFieldState2 != null) {
                            ((SnapshotMutableStateImpl) legacyTextFieldState2.isInTouchMode$delegate).setValue(Boolean.TRUE);
                        }
                        textFieldSelectionManager2.updateFloatingToolbar(false);
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    /* renamed from: onDrag-k-4lQ0M */
                    public final void mo160onDragk4lQ0M(long j) {
                        TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                        long m315plusMKHz9U = Offset.m315plusMKHz9U(textFieldSelectionManager2.dragTotalDistance, j);
                        textFieldSelectionManager2.dragTotalDistance = m315plusMKHz9U;
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(new Offset(Offset.m315plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, m315plusMKHz9U)));
                        TextFieldValue value$foundation_release = textFieldSelectionManager2.getValue$foundation_release();
                        Offset m190getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m190getCurrentDragPosition_m7T9E();
                        Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E);
                        SelectionAdjustment$Companion$$ExternalSyntheticLambda0 selectionAdjustment$Companion$$ExternalSyntheticLambda0 = SelectionAdjustment.Companion.CharacterWithWordAccelerate;
                        TextFieldSelectionManager.m187access$updateSelection8UEBfa8(textFieldSelectionManager2, value$foundation_release, m190getCurrentDragPosition_m7T9E.packedValue, false, z, selectionAdjustment$Companion$$ExternalSyntheticLambda0, true);
                        textFieldSelectionManager2.updateFloatingToolbar(false);
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    public final void onStop() {
                        TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.draggingHandle$delegate).setValue(null);
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(null);
                        textFieldSelectionManager2.updateFloatingToolbar(true);
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    public final void onUp() {
                        TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.draggingHandle$delegate).setValue(null);
                        ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(null);
                        textFieldSelectionManager2.updateFloatingToolbar(true);
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    public final void onCancel() {
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    /* renamed from: onStart-k-4lQ0M */
                    public final void mo161onStartk4lQ0M(long j) {
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final TextDragObserver textDragObserver = (TextDragObserver) rememberedValue;
            boolean changedInstance = composerImpl.changedInstance(textFieldSelectionManager) | (i3 == 4);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changedInstance || rememberedValue2 == obj) {
                rememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1$1
                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                    /* renamed from: provide-F1C5BW0 */
                    public final long mo156provideF1C5BW0() {
                        return TextFieldSelectionManager.this.m191getHandlePositiontuRUvjQ$foundation_release(z);
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            OffsetProvider offsetProvider = (OffsetProvider) rememberedValue2;
            boolean m602getReversedimpl = TextRange.m602getReversedimpl(textFieldSelectionManager.getValue$foundation_release().selection);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            boolean changedInstance2 = composerImpl.changedInstance(textDragObserver);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changedInstance2 || rememberedValue3 == obj) {
                rememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$2$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                        Object detectDownAndDragGesturesWithObserver = LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(pointerInputScope, TextDragObserver.this, continuation);
                        return detectDownAndDragGesturesWithObserver == CoroutineSingletons.COROUTINE_SUSPENDED ? detectDownAndDragGesturesWithObserver : Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            AndroidSelectionHandles_androidKt.m181SelectionHandlepzduO1o(offsetProvider, z, resolvedTextDirection, m602getReversedimpl, 0L, SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (PointerInputEventHandler) rememberedValue3), composerImpl, (i2 << 3) & 1008, 16);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    TextFieldSelectionManagerKt.TextFieldSelectionHandle(z, resolvedTextDirection, textFieldSelectionManager, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager textFieldSelectionManager, boolean z) {
        LayoutCoordinates layoutCoordinates;
        LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager.state;
        if (legacyTextFieldState == null || (layoutCoordinates = legacyTextFieldState.getLayoutCoordinates()) == null) {
            return false;
        }
        Rect visibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates);
        long m191getHandlePositiontuRUvjQ$foundation_release = textFieldSelectionManager.m191getHandlePositiontuRUvjQ$foundation_release(z);
        float intBitsToFloat = Float.intBitsToFloat((int) (m191getHandlePositiontuRUvjQ$foundation_release >> 32));
        if (visibleBounds.left > intBitsToFloat || intBitsToFloat > visibleBounds.right) {
            return false;
        }
        float intBitsToFloat2 = Float.intBitsToFloat((int) (m191getHandlePositiontuRUvjQ$foundation_release & 4294967295L));
        return visibleBounds.top <= intBitsToFloat2 && intBitsToFloat2 <= visibleBounds.bottom;
    }
}
