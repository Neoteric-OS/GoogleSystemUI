package com.android.systemui.qs.panels.ui.compose;

import android.content.ClipData;
import androidx.compose.foundation.draganddrop.DragAndDropSourceScope;
import androidx.compose.foundation.draganddrop.LegacyDragAndDropSourceNode;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.State;
import androidx.compose.ui.draganddrop.DragAndDropNode;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntSizeKt;
import com.android.systemui.qs.panels.shared.model.SizedTile;
import com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DragAndDropStateKt$dragAndDropTileSource$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ State $dragState$delegate;
    final /* synthetic */ Function0 $onDragStart;
    final /* synthetic */ SizedTile $sizedTile;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragAndDropStateKt$dragAndDropTileSource$1(SizedTile sizedTile, Function0 function0, State state, Continuation continuation) {
        super(2, continuation);
        this.$sizedTile = sizedTile;
        this.$onDragStart = function0;
        this.$dragState$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DragAndDropStateKt$dragAndDropTileSource$1 dragAndDropStateKt$dragAndDropTileSource$1 = new DragAndDropStateKt$dragAndDropTileSource$1(this.$sizedTile, this.$onDragStart, this.$dragState$delegate, continuation);
        dragAndDropStateKt$dragAndDropTileSource$1.L$0 = obj;
        return dragAndDropStateKt$dragAndDropTileSource$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DragAndDropStateKt$dragAndDropTileSource$1) create((DragAndDropSourceScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object detectDragGesturesAfterLongPress;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final DragAndDropSourceScope dragAndDropSourceScope = (DragAndDropSourceScope) this.L$0;
            final SizedTile sizedTile = this.$sizedTile;
            final Function0 function0 = this.$onDragStart;
            final State state = this.$dragState$delegate;
            Function1 function1 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt$dragAndDropTileSource$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    long j = ((Offset) obj2).packedValue;
                    DragAndDropState dragAndDropState = (DragAndDropState) state.getValue();
                    EditTileListState editTileListState = (EditTileListState) dragAndDropState;
                    ((SnapshotMutableStateImpl) editTileListState._draggedCell).setValue(SizedTile.this);
                    editTileListState.regenerateGrid();
                    function0.invoke();
                    DragAndDropSourceScope dragAndDropSourceScope2 = dragAndDropSourceScope;
                    LegacyDragAndDropSourceNode.AnonymousClass1.C00011 c00011 = (LegacyDragAndDropSourceNode.AnonymousClass1.C00011) dragAndDropSourceScope2;
                    ((DragAndDropNode) c00011.$dragAndDropModifierNode).m277drag12SF9DM(new DragAndDropTransferData(new ClipData("tilespec", new String[]{"qstile/tilespec"}, new ClipData.Item(((EditTileViewModel) SizedTile.this.getTile()).tileSpec.getSpec()))), IntSizeKt.m685toSizeozmzZPI(c00011.$$delegate_0.mo44getSizeYbymL2g()), c00011.this$0.drawDragDecoration);
                    return Unit.INSTANCE;
                }
            };
            AnonymousClass2 anonymousClass2 = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt$dragAndDropTileSource$1.2
                @Override // kotlin.jvm.functions.Function2
                public final /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    long j = ((Offset) obj3).packedValue;
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            detectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(dragAndDropSourceScope, function1, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$3
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$4
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Unit.INSTANCE;
                }
            }, anonymousClass2, this);
            if (detectDragGesturesAfterLongPress == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
