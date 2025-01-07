package androidx.compose.material3;

import androidx.compose.material3.internal.DraggableAnchors;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ModalBottomSheetKt$ModalBottomSheet$5$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SheetState $sheetState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModalBottomSheetKt$ModalBottomSheet$5$1(SheetState sheetState, Continuation continuation) {
        super(2, continuation);
        this.$sheetState = sheetState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ModalBottomSheetKt$ModalBottomSheet$5$1(this.$sheetState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ModalBottomSheetKt$ModalBottomSheet$5$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SheetState sheetState = this.$sheetState;
            this.label = 1;
            DraggableAnchors anchors = sheetState.anchoredDraggableState.getAnchors();
            SheetValue sheetValue = SheetValue.PartiallyExpanded;
            if (!anchors.hasAnchorFor(sheetValue)) {
                sheetValue = SheetValue.Expanded;
            }
            Object animateTo$material3_release$default = SheetState.animateTo$material3_release$default(sheetState, sheetValue, sheetState.showMotionSpec, this);
            if (animateTo$material3_release$default != coroutineSingletons) {
                animateTo$material3_release$default = unit;
            }
            if (animateTo$material3_release$default == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
