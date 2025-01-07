package com.android.systemui.keyboard.shortcut.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.input.InputManager;
import android.os.UserHandle;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState;
import com.android.systemui.statusbar.CommandQueue;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperStateRepository implements CoreStartable {
    public final CoroutineDispatcher backgroundDispatcher;
    public final CoroutineScope backgroundScope;
    public final BroadcastDispatcher broadcastDispatcher;
    public final CommandQueue commandQueue;
    public final InputManager inputManager;
    public final StateFlowImpl state = StateFlowKt.MutableStateFlow(ShortcutHelperState.Inactive.INSTANCE);

    public ShortcutHelperStateRepository(CommandQueue commandQueue, BroadcastDispatcher broadcastDispatcher, InputManager inputManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.commandQueue = commandQueue;
        this.broadcastDispatcher = broadcastDispatcher;
        this.inputManager = inputManager;
        this.backgroundScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public final void registerBroadcastReceiver(String str, final Function0 function0) {
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, new BroadcastReceiver(function0) { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository$registerBroadcastReceiver$1
            public final /* synthetic */ Lambda $onReceive;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$onReceive = (Lambda) function0;
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                this.$onReceive.invoke();
            }
        }, new IntentFilter(str), null, UserHandle.ALL, 3, 36);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        registerBroadcastReceiver("com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS", new Function0() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository$start$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository$start$1$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                Object L$0;
                int label;
                final /* synthetic */ ShortcutHelperStateRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(ShortcutHelperStateRepository shortcutHelperStateRepository, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = shortcutHelperStateRepository;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MutableStateFlow mutableStateFlow;
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ShortcutHelperStateRepository shortcutHelperStateRepository = this.this$0;
                        StateFlowImpl stateFlowImpl = shortcutHelperStateRepository.state;
                        this.L$0 = stateFlowImpl;
                        this.label = 1;
                        obj = BuildersKt.withContext(shortcutHelperStateRepository.backgroundDispatcher, new ShortcutHelperStateRepository$findPhysicalKeyboardId$2(shortcutHelperStateRepository, null), this);
                        if (obj == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                        mutableStateFlow = stateFlowImpl;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutableStateFlow = (MutableStateFlow) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    ShortcutHelperState.Active active = new ShortcutHelperState.Active(((Number) obj).intValue());
                    StateFlowImpl stateFlowImpl2 = (StateFlowImpl) mutableStateFlow;
                    stateFlowImpl2.getClass();
                    stateFlowImpl2.updateState(null, active);
                    return Unit.INSTANCE;
                }
            }

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ShortcutHelperStateRepository shortcutHelperStateRepository = ShortcutHelperStateRepository.this;
                BuildersKt.launch$default(shortcutHelperStateRepository.backgroundScope, null, null, new AnonymousClass1(shortcutHelperStateRepository, null), 3);
                return Unit.INSTANCE;
            }
        });
        registerBroadcastReceiver("com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS", new Function0() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository$start$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StateFlowImpl stateFlowImpl = ShortcutHelperStateRepository.this.state;
                ShortcutHelperState.Inactive inactive = ShortcutHelperState.Inactive.INSTANCE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, inactive);
                return Unit.INSTANCE;
            }
        });
        registerBroadcastReceiver("android.intent.action.CLOSE_SYSTEM_DIALOGS", new Function0() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository$start$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StateFlowImpl stateFlowImpl = ShortcutHelperStateRepository.this.state;
                ShortcutHelperState.Inactive inactive = ShortcutHelperState.Inactive.INSTANCE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, inactive);
                return Unit.INSTANCE;
            }
        });
        this.commandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository$start$4
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void dismissKeyboardShortcutsMenu() {
                StateFlowImpl stateFlowImpl = ShortcutHelperStateRepository.this.state;
                ShortcutHelperState.Inactive inactive = ShortcutHelperState.Inactive.INSTANCE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, inactive);
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void toggleKeyboardShortcutsMenu(int i) {
                StateFlowImpl stateFlowImpl = ShortcutHelperStateRepository.this.state;
                Object active = stateFlowImpl.getValue() instanceof ShortcutHelperState.Inactive ? new ShortcutHelperState.Active(i) : ShortcutHelperState.Inactive.INSTANCE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, active);
            }
        });
    }
}
