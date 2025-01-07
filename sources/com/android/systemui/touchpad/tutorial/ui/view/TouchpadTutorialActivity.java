package com.android.systemui.touchpad.tutorial.ui.view;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.touchpad.tutorial.ui.viewmodel.TouchpadTutorialViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchpadTutorialActivity extends ComponentActivity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final InputDeviceTutorialLogger logger;
    public final TouchpadTutorialViewModel.Factory viewModelFactory;
    public final ViewModelLazy vm$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(TouchpadTutorialViewModel.class), new Function0(this) { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$special$$inlined$viewModels$default$2
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        {
            super(0);
            this.$this_viewModels = this;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return this.$this_viewModels.getViewModelStore();
        }
    }, new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$vm$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TouchpadTutorialActivity.this.viewModelFactory;
        }
    }, new Function0(this) { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$special$$inlined$viewModels$default$3
        final /* synthetic */ Function0 $extrasProducer = null;
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        {
            super(0);
            this.$this_viewModels = this;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CreationExtras creationExtras;
            Function0 function0 = this.$extrasProducer;
            return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? this.$this_viewModels.getDefaultViewModelCreationExtras() : creationExtras;
        }
    });

    public TouchpadTutorialActivity(TouchpadTutorialViewModel.Factory factory, InputDeviceTutorialLogger inputDeviceTutorialLogger) {
        this.viewModelFactory = factory;
        this.logger = inputDeviceTutorialLogger;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EdgeToEdge.enable$default(this);
        ComponentActivityKt.setContent$default(this, new ComposableLambdaImpl(-404230632, true, new Function2() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$onCreate$1
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$onCreate$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                final TouchpadTutorialActivity touchpadTutorialActivity = TouchpadTutorialActivity.this;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-2016162462, new Function2() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$onCreate$1.1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity$onCreate$1$1$1, reason: invalid class name and collision with other inner class name */
                    final /* synthetic */ class C02511 extends FunctionReferenceImpl implements Function0 {
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            TouchpadTutorialActivity touchpadTutorialActivity = (TouchpadTutorialActivity) this.receiver;
                            int i = TouchpadTutorialActivity.$r8$clinit;
                            touchpadTutorialActivity.getClass();
                            touchpadTutorialActivity.logger.logCloseTutorial(InputDeviceTutorialLogger.TutorialContext.TOUCHPAD_TUTORIAL);
                            touchpadTutorialActivity.finish();
                            return Unit.INSTANCE;
                        }
                    }

                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        Composer composer2 = (Composer) obj3;
                        if ((((Number) obj4).intValue() & 11) == 2) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        TouchpadTutorialActivity touchpadTutorialActivity2 = TouchpadTutorialActivity.this;
                        int i = TouchpadTutorialActivity.$r8$clinit;
                        TouchpadTutorialActivityKt.TouchpadTutorialScreen((TouchpadTutorialViewModel) touchpadTutorialActivity2.vm$delegate.getValue(), new C02511(0, TouchpadTutorialActivity.this, TouchpadTutorialActivity.class, "finishTutorial", "finishTutorial()V", 0), composer2, 8);
                        return Unit.INSTANCE;
                    }
                }, composer), composer, 48, 1);
                return Unit.INSTANCE;
            }
        }));
        getWindow().addPrivateFlags(536870912);
        this.logger.logOpenTutorial(InputDeviceTutorialLogger.TutorialContext.TOUCHPAD_TUTORIAL);
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        ((TouchpadTutorialViewModel) this.vm$delegate.getValue()).gesturesInteractor.enableGestures();
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        ((TouchpadTutorialViewModel) this.vm$delegate.getValue()).gesturesInteractor.disableGestures();
    }
}
