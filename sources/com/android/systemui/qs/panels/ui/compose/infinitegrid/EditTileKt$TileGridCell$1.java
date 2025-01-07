package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionState;
import com.android.systemui.qs.panels.ui.compose.selection.Selection;
import com.android.systemui.qs.panels.ui.model.TileGridCell;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class EditTileKt$TileGridCell$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ TileGridCell $cell;
    final /* synthetic */ MutableState $selected$delegate;
    final /* synthetic */ MutableSelectionState $selectionState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditTileKt$TileGridCell$1(MutableSelectionState mutableSelectionState, TileGridCell tileGridCell, MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$selectionState = mutableSelectionState;
        this.$cell = tileGridCell;
        this.$selected$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EditTileKt$TileGridCell$1(this.$selectionState, this.$cell, this.$selected$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((EditTileKt$TileGridCell$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Selection selection = (Selection) ((SnapshotMutableStateImpl) this.$selectionState.selection$delegate).getValue();
            if (selection != null && !selection.manual) {
                this.label = 1;
                if (DelayKt.delay(250L, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        MutableState mutableState = this.$selected$delegate;
        Selection selection2 = (Selection) ((SnapshotMutableStateImpl) this.$selectionState.selection$delegate).getValue();
        mutableState.setValue(Boolean.valueOf(Intrinsics.areEqual(selection2 != null ? selection2.tileSpec : null, this.$cell.tile.tileSpec)));
        return Unit.INSTANCE;
    }
}
