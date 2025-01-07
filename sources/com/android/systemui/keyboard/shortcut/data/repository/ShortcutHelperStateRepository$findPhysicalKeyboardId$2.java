package com.android.systemui.keyboard.shortcut.data.repository;

import android.hardware.input.InputManager;
import android.view.InputDevice;
import com.android.systemui.shared.hardware.InputManagerKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShortcutHelperStateRepository$findPhysicalKeyboardId$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ShortcutHelperStateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShortcutHelperStateRepository$findPhysicalKeyboardId$2(ShortcutHelperStateRepository shortcutHelperStateRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = shortcutHelperStateRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ShortcutHelperStateRepository$findPhysicalKeyboardId$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ShortcutHelperStateRepository$findPhysicalKeyboardId$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        InputManager inputManager = this.this$0.inputManager;
        ShortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1 shortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1 = ShortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1.INSTANCE;
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(InputManagerKt.getInputDeviceSequence(inputManager));
        while (true) {
            if (!filteringSequence$iterator$1.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = filteringSequence$iterator$1.next();
            if (((Boolean) shortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1.invoke((InputDevice) obj2)).booleanValue()) {
                break;
            }
        }
        InputDevice inputDevice = (InputDevice) obj2;
        return new Integer(inputDevice != null ? inputDevice.getId() : -1);
    }
}
