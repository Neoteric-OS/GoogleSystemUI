package com.android.systemui.inputdevice.tutorial.ui.viewmodel;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.inputdevice.tutorial.domain.interactor.ConnectionState;
import com.android.systemui.inputdevice.tutorial.domain.interactor.KeyboardTouchpadConnectionInteractor;
import com.android.systemui.log.ConstantStringsLoggerImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.touchpad.tutorial.domain.interactor.TouchpadGesturesInteractor;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardTouchpadTutorialViewModel extends ViewModel implements DefaultLifecycleObserver {
    public final StateFlowImpl _closeActivity;
    public final StateFlowImpl _screen;
    public final StateFlowImpl closeActivity;
    public ConnectionState connectionState;
    public final Optional gesturesInteractor;
    public final boolean hasTouchpadTutorialScreens;
    public final KeyboardTouchpadConnectionInteractor keyboardTouchpadConnectionInteractor;
    public final InputDeviceTutorialLogger logger;
    public final KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1 screen;
    public final ArrayDeque screensBackStack;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$1$1, reason: invalid class name and collision with other inner class name */
        public final class C00751 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ KeyboardTouchpadTutorialViewModel this$0;

            public /* synthetic */ C00751(KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel, int i) {
                this.$r8$classId = i;
                this.this$0 = keyboardTouchpadTutorialViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel = this.this$0;
                        keyboardTouchpadTutorialViewModel.logger.logNewConnectionState(keyboardTouchpadTutorialViewModel.connectionState);
                        keyboardTouchpadTutorialViewModel.connectionState = (ConnectionState) obj;
                        break;
                    case 1:
                        Pair pair = (Pair) obj;
                        Screen screen = (Screen) pair.component1();
                        Screen screen2 = (Screen) pair.component2();
                        if (screen2 != null) {
                            this.this$0.setupDeviceState(screen, screen2);
                        }
                        break;
                    default:
                        KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel2 = this.this$0;
                        ConstantStringsLoggerImpl constantStringsLoggerImpl = keyboardTouchpadTutorialViewModel2.logger.$$delegate_0;
                        LogLevel logLevel = LogLevel.ERROR;
                        constantStringsLoggerImpl.buffer.log(constantStringsLoggerImpl.tag, logLevel, "Touchpad is connected but touchpad module is missing, something went wrong", null);
                        Boolean bool = Boolean.TRUE;
                        StateFlowImpl stateFlowImpl = keyboardTouchpadTutorialViewModel2._closeActivity;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, bool);
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyboardTouchpadTutorialViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel = KeyboardTouchpadTutorialViewModel.this;
                FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = keyboardTouchpadTutorialViewModel.keyboardTouchpadConnectionInteractor.connectionState;
                C00751 c00751 = new C00751(keyboardTouchpadTutorialViewModel, 0);
                this.label = 1;
                if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(c00751, this) == coroutineSingletons) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function3 {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
                anonymousClass1.L$0 = (Pair) obj;
                anonymousClass1.L$1 = (Screen) obj2;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Pair pair = (Pair) this.L$0;
                return new Pair(pair.getSecond(), (Screen) this.L$1);
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyboardTouchpadTutorialViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 = new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(new Pair(null, null), new AnonymousClass1(3, null), KeyboardTouchpadTutorialViewModel.this.screen);
                AnonymousClass1.C00751 c00751 = new AnonymousClass1.C00751(KeyboardTouchpadTutorialViewModel.this, 1);
                this.label = 1;
                if (flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.collect(c00751, this) == coroutineSingletons) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyboardTouchpadTutorialViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return unit;
            }
            ResultKt.throwOnFailure(obj);
            KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel = KeyboardTouchpadTutorialViewModel.this;
            StateFlowImpl stateFlowImpl = keyboardTouchpadTutorialViewModel._screen;
            AnonymousClass1.C00751 c00751 = new AnonymousClass1.C00751(keyboardTouchpadTutorialViewModel, 2);
            this.label = 1;
            stateFlowImpl.collect(new KeyboardTouchpadTutorialViewModel$3$invokeSuspend$$inlined$filterNot$1$2(c00751, keyboardTouchpadTutorialViewModel), this);
            return coroutineSingletons;
        }
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1] */
    public KeyboardTouchpadTutorialViewModel(Optional optional, KeyboardTouchpadConnectionInteractor keyboardTouchpadConnectionInteractor, boolean z, InputDeviceTutorialLogger inputDeviceTutorialLogger, SavedStateHandle savedStateHandle) {
        Object obj;
        this.gesturesInteractor = optional;
        this.keyboardTouchpadConnectionInteractor = keyboardTouchpadConnectionInteractor;
        this.hasTouchpadTutorialScreens = z;
        this.logger = inputDeviceTutorialLogger;
        try {
            obj = savedStateHandle.regular.get("tutorial_type");
        } catch (ClassCastException unused) {
            savedStateHandle.regular.remove("tutorial_type");
            if (savedStateHandle.liveDatas.remove("tutorial_type") != null) {
                throw new ClassCastException();
            }
            savedStateHandle.flows.remove("tutorial_type");
            obj = null;
        }
        final StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Intrinsics.areEqual((String) obj, "keyboard") ? Screen.ACTION_KEY : Screen.BACK_GESTURE);
        this._screen = MutableStateFlow;
        this.screen = new Flow() { // from class: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ KeyboardTouchpadTutorialViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = keyboardTouchpadTutorialViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1$2$1 r0 = (com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1$2$1 r0 = new com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L51
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        r7 = r6
                        com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen r7 = (com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen) r7
                        com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel r2 = r5.this$0
                        r2.getClass()
                        com.android.systemui.inputdevice.tutorial.ui.viewmodel.RequiredHardware r7 = r7.getRequiredHardware()
                        com.android.systemui.inputdevice.tutorial.ui.viewmodel.RequiredHardware r4 = com.android.systemui.inputdevice.tutorial.ui.viewmodel.RequiredHardware.TOUCHPAD
                        if (r7 != r4) goto L46
                        boolean r7 = r2.hasTouchpadTutorialScreens
                        if (r7 == 0) goto L51
                    L46:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r6, r0)
                        if (r5 != r1) goto L51
                        return r1
                    L51:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                StateFlowImpl.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._closeActivity = MutableStateFlow2;
        this.closeActivity = MutableStateFlow2;
        List singletonList = Collections.singletonList(MutableStateFlow.getValue());
        ArrayDeque arrayDeque = new ArrayDeque();
        Object[] array = singletonList.toArray(new Object[0]);
        arrayDeque.elementData = array;
        arrayDeque.size = array.length;
        if (array.length == 0) {
            arrayDeque.elementData = ArrayDeque.emptyElementData;
        }
        this.screensBackStack = arrayDeque;
        this.connectionState = new ConnectionState(false, false);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass2(null), 3);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass3(null), 3);
    }

    public final void clearDeviceStateForScreen(Screen screen) {
        if (screen.getRequiredHardware().ordinal() != 0) {
            return;
        }
        ((TouchpadGesturesInteractor) this.gesturesInteractor.get()).enableGestures();
    }

    public final void onBack() {
        ArrayDeque arrayDeque = this.screensBackStack;
        if (arrayDeque.size <= 1) {
            Boolean bool = Boolean.TRUE;
            StateFlowImpl stateFlowImpl = this._closeActivity;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
            return;
        }
        arrayDeque.removeLast();
        this.logger.logGoingBack((Screen) arrayDeque.last());
        this._screen.setValue(arrayDeque.last());
    }

    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        clearDeviceStateForScreen((Screen) this._screen.getValue());
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
    
        if (r1 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0060, code lost:
    
        r0 = r5.$$delegate_0;
        r0.buffer.log(r0.tag, com.android.systemui.log.core.LogLevel.DEBUG, "Final screen reached, closing tutorial", null);
        r0 = java.lang.Boolean.TRUE;
        r7 = r7._closeActivity;
        r7.getClass();
        r7.updateState(null, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
    
        r5.logNextScreen(r1);
        r0.getClass();
        r0.updateState(null, r1);
        r7.screensBackStack.addLast(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0086, code lost:
    
        return;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004f -> B:5:0x0015). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDoneButtonClicked() {
        /*
            r7 = this;
            kotlinx.coroutines.flow.StateFlowImpl r0 = r7._screen
            java.lang.Object r1 = r0.getValue()
            com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen r1 = (com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen) r1
            int r1 = r1.ordinal()
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L20
            if (r1 == r3) goto L1d
            if (r1 != r2) goto L17
        L15:
            r1 = r4
            goto L22
        L17:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        L1d:
            com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen r1 = com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen.ACTION_KEY
            goto L22
        L20:
            com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen r1 = com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen.HOME_GESTURE
        L22:
            com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger r5 = r7.logger
            if (r1 == 0) goto L5e
            com.android.systemui.inputdevice.tutorial.ui.viewmodel.RequiredHardware r6 = r1.getRequiredHardware()
            int r6 = r6.ordinal()
            if (r6 == 0) goto L3d
            if (r6 != r3) goto L37
            com.android.systemui.inputdevice.tutorial.domain.interactor.ConnectionState r6 = r7.connectionState
            boolean r6 = r6.keyboardConnected
            goto L41
        L37:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        L3d:
            com.android.systemui.inputdevice.tutorial.domain.interactor.ConnectionState r6 = r7.connectionState
            boolean r6 = r6.touchpadConnected
        L41:
            if (r6 == 0) goto L44
            goto L5e
        L44:
            r5.logNextScreenMissingHardware(r1)
            int r1 = r1.ordinal()
            if (r1 == 0) goto L5b
            if (r1 == r3) goto L58
            if (r1 != r2) goto L52
            goto L15
        L52:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        L58:
            com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen r1 = com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen.ACTION_KEY
            goto L22
        L5b:
            com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen r1 = com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen.HOME_GESTURE
            goto L22
        L5e:
            if (r1 != 0) goto L78
            com.android.systemui.log.ConstantStringsLoggerImpl r0 = r5.$$delegate_0
            com.android.systemui.log.core.LogLevel r1 = com.android.systemui.log.core.LogLevel.DEBUG
            java.lang.String r2 = r0.tag
            com.android.systemui.log.LogBuffer r0 = r0.buffer
            java.lang.String r3 = "Final screen reached, closing tutorial"
            r0.log(r2, r1, r3, r4)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            kotlinx.coroutines.flow.StateFlowImpl r7 = r7._closeActivity
            r7.getClass()
            r7.updateState(r4, r0)
            goto L86
        L78:
            r5.logNextScreen(r1)
            r0.getClass()
            r0.updateState(r4, r1)
            kotlin.collections.ArrayDeque r7 = r7.screensBackStack
            r7.addLast(r1)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel.onDoneButtonClicked():void");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart$1() {
        setupDeviceState(null, (Screen) this._screen.getValue());
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop$1() {
        clearDeviceStateForScreen((Screen) this._screen.getValue());
    }

    public final void setupDeviceState(Screen screen, Screen screen2) {
        this.logger.logMovingBetweenScreens(screen, screen2);
        if ((screen != null ? screen.getRequiredHardware() : null) == screen2.getRequiredHardware()) {
            return;
        }
        if (screen != null) {
            clearDeviceStateForScreen(screen);
        }
        if (screen2.getRequiredHardware().ordinal() != 0) {
            return;
        }
        ((TouchpadGesturesInteractor) this.gesturesInteractor.get()).disableGestures();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {
        public final Optional gesturesInteractor;
        public final boolean hasTouchpadTutorialScreens;
        public final KeyboardTouchpadConnectionInteractor keyboardTouchpadConnected;
        public final InputDeviceTutorialLogger logger;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public interface ViewModelFactoryAssistedProvider {
        }

        public Factory(Optional optional, KeyboardTouchpadConnectionInteractor keyboardTouchpadConnectionInteractor, InputDeviceTutorialLogger inputDeviceTutorialLogger, boolean z) {
            this.gesturesInteractor = optional;
            this.keyboardTouchpadConnected = keyboardTouchpadConnectionInteractor;
            this.logger = inputDeviceTutorialLogger;
            this.hasTouchpadTutorialScreens = z;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls, MutableCreationExtras mutableCreationExtras) {
            if (((String) mutableCreationExtras.extras.get(ViewModelProvider.VIEW_MODEL_KEY)) == null) {
                throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
            }
            SavedStateHandle createSavedStateHandle = SavedStateHandleSupport.createSavedStateHandle(mutableCreationExtras);
            return new KeyboardTouchpadTutorialViewModel(this.gesturesInteractor, this.keyboardTouchpadConnected, this.hasTouchpadTutorialScreens, this.logger, createSavedStateHandle);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            if (cls.getCanonicalName() != null) {
                throw new UnsupportedOperationException("AbstractSavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
            }
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }

        @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
        public final void onRequery(ViewModel viewModel) {
        }
    }
}
