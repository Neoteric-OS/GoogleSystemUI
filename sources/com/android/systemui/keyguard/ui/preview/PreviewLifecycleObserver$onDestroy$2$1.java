package com.android.systemui.keyguard.ui.preview;

import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$bind$1;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PreviewLifecycleObserver$onDestroy$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardPreviewRenderer $rendererToDestroy;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreviewLifecycleObserver$onDestroy$2$1(KeyguardPreviewRenderer keyguardPreviewRenderer, Continuation continuation) {
        super(2, continuation);
        this.$rendererToDestroy = keyguardPreviewRenderer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new PreviewLifecycleObserver$onDestroy$2$1(this.$rendererToDestroy, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        PreviewLifecycleObserver$onDestroy$2$1 previewLifecycleObserver$onDestroy$2$1 = (PreviewLifecycleObserver$onDestroy$2$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        previewLifecycleObserver$onDestroy$2$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        KeyguardPreviewRenderer keyguardPreviewRenderer = this.$rendererToDestroy;
        keyguardPreviewRenderer.isDestroyed = true;
        keyguardPreviewRenderer.lockscreenSmartspaceController.disconnect();
        keyguardPreviewRenderer.disposables.dispose();
        Iterator it = keyguardPreviewRenderer.shortcutsBindings.iterator();
        while (it.hasNext()) {
            ((KeyguardQuickAffordanceViewBinder$bind$1) it.next()).destroy();
        }
        return Unit.INSTANCE;
    }
}
