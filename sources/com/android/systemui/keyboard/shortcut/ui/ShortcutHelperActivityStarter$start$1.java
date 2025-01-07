package com.android.systemui.keyboard.shortcut.ui;

import android.content.Intent;
import com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity;
import com.android.systemui.keyboard.shortcut.ui.viewmodel.ShortcutHelperViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShortcutHelperActivityStarter$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ShortcutHelperActivityStarter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShortcutHelperActivityStarter$start$1(ShortcutHelperActivityStarter shortcutHelperActivityStarter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = shortcutHelperActivityStarter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ShortcutHelperActivityStarter$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ShortcutHelperActivityStarter$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ShortcutHelperActivityStarter shortcutHelperActivityStarter = this.this$0;
            ShortcutHelperViewModel shortcutHelperViewModel = shortcutHelperActivityStarter.viewModel;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyboard.shortcut.ui.ShortcutHelperActivityStarter$start$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    if (((Boolean) obj2).booleanValue()) {
                        ShortcutHelperActivityStarter shortcutHelperActivityStarter2 = ShortcutHelperActivityStarter.this;
                        shortcutHelperActivityStarter2.getClass();
                        shortcutHelperActivityStarter2.startActivity.invoke(new Intent(shortcutHelperActivityStarter2.context, (Class<?>) ShortcutHelperActivity.class).setFlags(268435456));
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (shortcutHelperViewModel.shouldShow.collect(flowCollector, this) == coroutineSingletons) {
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
