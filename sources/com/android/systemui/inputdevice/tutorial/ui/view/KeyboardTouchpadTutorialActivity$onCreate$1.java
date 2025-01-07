package com.android.systemui.inputdevice.tutorial.ui.view;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel;
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
final class KeyboardTouchpadTutorialActivity$onCreate$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyboardTouchpadTutorialActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyboardTouchpadTutorialActivity$onCreate$1(KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyboardTouchpadTutorialActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyboardTouchpadTutorialActivity$onCreate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((KeyboardTouchpadTutorialActivity$onCreate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity = this.this$0;
        int i2 = KeyboardTouchpadTutorialActivity.$r8$clinit;
        KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel = (KeyboardTouchpadTutorialViewModel) keyboardTouchpadTutorialActivity.vm$delegate.getValue();
        final KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity2 = this.this$0;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$onCreate$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                if (((Boolean) obj2).booleanValue()) {
                    KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity3 = KeyboardTouchpadTutorialActivity.this;
                    keyboardTouchpadTutorialActivity3.logger.logCloseTutorial(InputDeviceTutorialLogger.TutorialContext.KEYBOARD_TOUCHPAD_TUTORIAL);
                    keyboardTouchpadTutorialActivity3.finish();
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        keyboardTouchpadTutorialViewModel.closeActivity.collect(flowCollector, this);
        return coroutineSingletons;
    }
}
