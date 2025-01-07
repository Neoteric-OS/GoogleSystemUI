package com.android.systemui.inputdevice.tutorial.ui.view;

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
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.inputdevice.tutorial.domain.interactor.KeyboardTouchpadConnectionInteractor;
import com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel;
import com.android.systemui.touchpad.tutorial.domain.interactor.TouchpadGesturesInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$63;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.Optional;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardTouchpadTutorialActivity extends ComponentActivity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final InputDeviceTutorialLogger logger;
    public final Optional touchpadTutorialScreensProvider;
    public final KeyboardTouchpadTutorialViewModel.Factory.ViewModelFactoryAssistedProvider viewModelFactoryAssistedProvider;
    public final ViewModelLazy vm$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(KeyboardTouchpadTutorialViewModel.class), new Function0(this) { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$special$$inlined$viewModels$default$2
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
    }, new Function0() { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$vm$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity = KeyboardTouchpadTutorialActivity.this;
            KeyboardTouchpadTutorialViewModel.Factory.ViewModelFactoryAssistedProvider viewModelFactoryAssistedProvider = keyboardTouchpadTutorialActivity.viewModelFactoryAssistedProvider;
            boolean isPresent = keyboardTouchpadTutorialActivity.touchpadTutorialScreensProvider.isPresent();
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$63) viewModelFactoryAssistedProvider).this$0;
            Optional of = Optional.of((TouchpadGesturesInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).touchpadGesturesInteractorProvider.get());
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
            return new KeyboardTouchpadTutorialViewModel.Factory(of, (KeyboardTouchpadConnectionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyboardTouchpadConnectionInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inputDeviceTutorialLogger(), isPresent);
        }
    }, new Function0(this) { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$special$$inlined$viewModels$default$3
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

    public KeyboardTouchpadTutorialActivity(KeyboardTouchpadTutorialViewModel.Factory.ViewModelFactoryAssistedProvider viewModelFactoryAssistedProvider, Optional optional, InputDeviceTutorialLogger inputDeviceTutorialLogger) {
        this.viewModelFactoryAssistedProvider = viewModelFactoryAssistedProvider;
        this.touchpadTutorialScreensProvider = optional;
        this.logger = inputDeviceTutorialLogger;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EdgeToEdge.enable$default(this);
        getWindow().addPrivateFlags(536870912);
        getWindow().addPrivateFlags(8388608);
        this.lifecycleRegistry.addObserver((KeyboardTouchpadTutorialViewModel) this.vm$delegate.getValue());
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), null, null, new KeyboardTouchpadTutorialActivity$onCreate$1(this, null), 3);
        ComponentActivityKt.setContent$default(this, new ComposableLambdaImpl(1102336700, true, new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$onCreate$2
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$onCreate$2$1, kotlin.jvm.internal.Lambda] */
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
                final KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity = KeyboardTouchpadTutorialActivity.this;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-93451470, new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity$onCreate$2.1
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
                        KeyboardTouchpadTutorialActivity keyboardTouchpadTutorialActivity2 = KeyboardTouchpadTutorialActivity.this;
                        int i = KeyboardTouchpadTutorialActivity.$r8$clinit;
                        KeyboardTouchpadTutorialActivityKt.KeyboardTouchpadTutorialContainer((KeyboardTouchpadTutorialViewModel) keyboardTouchpadTutorialActivity2.vm$delegate.getValue(), KeyboardTouchpadTutorialActivity.this.touchpadTutorialScreensProvider, composer2, 72);
                        return Unit.INSTANCE;
                    }
                }, composer), composer, 48, 1);
                return Unit.INSTANCE;
            }
        }));
        if (bundle == null) {
            this.logger.logOpenTutorial(InputDeviceTutorialLogger.TutorialContext.KEYBOARD_TOUCHPAD_TUTORIAL);
        }
    }
}
