package com.android.systemui.accessibility.extradim;

import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.Collections;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ExtraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ExtraDimDialogDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2(ExtraDimDialogDelegate extraDimDialogDelegate, Continuation continuation) {
        super(2, continuation);
        this.this$0 = extraDimDialogDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ExtraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ExtraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2 extraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2 = (ExtraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        extraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.accessibilityManager.enableShortcutsForTargets(false, 63, Collections.singleton(AccessibilityShortcutController.REDUCE_BRIGHT_COLORS_COMPONENT_NAME.flattenToString()), ((UserTrackerImpl) this.this$0.userTracker).getUserId());
        return Unit.INSTANCE;
    }
}
