package com.android.systemui.keyboard.shortcut.ui.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ModalBottomSheetKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.keyboard.shortcut.domain.interactor.ShortcutHelperStateInteractor;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState;
import com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt;
import com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperUtilsKt;
import com.android.systemui.keyboard.shortcut.ui.model.ShortcutsUiState;
import com.android.systemui.keyboard.shortcut.ui.viewmodel.ShortcutHelperViewModel;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperActivity extends ComponentActivity {
    public final UserTracker userTracker;
    public final ShortcutHelperViewModel viewModel;
    public static final float DefaultTopPadding = 64;
    public static final float LargeScreenTopPadding = 72;
    public static final float DefaultWidth = 412;
    public static final float LargeScreenWidthPortrait = 704;
    public static final float LargeScreenWidthLandscape = 864;

    public ShortcutHelperActivity(UserTracker userTracker, ShortcutHelperViewModel shortcutHelperViewModel) {
        this.userTracker = userTracker;
        this.viewModel = shortcutHelperViewModel;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$4, kotlin.jvm.internal.Lambda] */
    public static final void access$BottomSheet(final ShortcutHelperActivity shortcutHelperActivity, final Function0 function0, Composer composer, final int i) {
        shortcutHelperActivity.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-2105969101);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-766601829);
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && composerImpl.changed(function0)) || (i & 6) == 4;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (z2 || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new Function0() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Function0.this.invoke();
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        Function0 function02 = (Function0) rememberedValue;
        composerImpl.end(false);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        composerImpl.startReplaceGroup(-72469345);
        float f = ShortcutHelperUtilsKt.hasCompactWindowSize(composerImpl) ? DefaultWidth : ((Configuration) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration)).orientation == 2 ? LargeScreenWidthLandscape : LargeScreenWidthPortrait;
        composerImpl.end(false);
        Modifier m117width3ABfNKs = SizeKt.m117width3ABfNKs(companion, f);
        composerImpl.startReplaceGroup(1169995353);
        float f2 = ShortcutHelperUtilsKt.hasCompactWindowSize(composerImpl) ? DefaultTopPadding : LargeScreenTopPadding;
        composerImpl.end(false);
        Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(m117width3ABfNKs, 0.0f, f2, 0.0f, 0.0f, 13);
        composerImpl.startReplaceGroup(-766601704);
        if ((i2 <= 4 || !composerImpl.changed(function0)) && (i & 6) != 4) {
            z = false;
        }
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (z || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    boolean z3;
                    if (Key.m446equalsimpl0(Key_androidKt.Key(((KeyEvent) obj).nativeKeyEvent.getKeyCode()), Key.Escape)) {
                        Function0.this.invoke();
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    return Boolean.valueOf(z3);
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        ModalBottomSheetKt.m215ModalBottomSheetdYc4hso(function02, KeyInputModifierKt.onKeyEvent(m102paddingqDBjuR0$default, (Function1) rememberedValue2), ModalBottomSheetKt.rememberModalBottomSheetState(6, 2, composerImpl), 0.0f, null, 0L, 0L, 0.0f, 0L, ComposableLambdaKt.rememberComposableLambda(1901315920, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$3
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                ShortcutHelperActivity.this.DragHandle(8, composer2);
                return Unit.INSTANCE;
            }
        }, composerImpl), null, null, ComposableLambdaKt.rememberComposableLambda(330693136, new Function3() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$4$1, reason: invalid class name */
            final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ShortcutHelperActivity shortcutHelperActivity = (ShortcutHelperActivity) this.receiver;
                    float f = ShortcutHelperActivity.DefaultTopPadding;
                    shortcutHelperActivity.getClass();
                    try {
                        shortcutHelperActivity.startActivityAsUser(new Intent("android.settings.HARD_KEYBOARD_SETTINGS"), ((UserTrackerImpl) shortcutHelperActivity.userTracker).getUserHandle());
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                    return Unit.INSTANCE;
                }
            }

            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Composer composer2 = (Composer) obj2;
                if ((((Number) obj3).intValue() & 81) == 16) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                ShortcutsUiState shortcutsUiState = (ShortcutsUiState) FlowExtKt.collectAsStateWithLifecycle(ShortcutHelperActivity.this.viewModel.shortcutsUiState, composer2).getValue();
                final ShortcutHelperActivity shortcutHelperActivity2 = ShortcutHelperActivity.this;
                ShortcutHelperKt.ShortcutHelper(new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$4.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        StateFlowImpl stateFlowImpl = ShortcutHelperActivity.this.viewModel.searchQuery;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, (String) obj4);
                        return Unit.INSTANCE;
                    }
                }, new AnonymousClass1(0, shortcutHelperActivity2, ShortcutHelperActivity.class, "onKeyboardSettingsClicked", "onKeyboardSettingsClicked()V", 0), null, shortcutsUiState, null, composer2, 0, 20);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 805306368, 384, 3576);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$BottomSheet$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperActivity.access$BottomSheet(ShortcutHelperActivity.this, function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$Content$1, kotlin.jvm.internal.Lambda] */
    public static final void access$Content(final ShortcutHelperActivity shortcutHelperActivity, Composer composer, final int i) {
        shortcutHelperActivity.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(507068698);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        CompositionLocalKt.CompositionLocalProvider(AndroidCompositionLocals_androidKt.LocalContext.defaultProvidedValue$runtime_release(((UserTrackerImpl) shortcutHelperActivity.userTracker).getUserContext()), ComposableLambdaKt.rememberComposableLambda(1387230298, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$Content$1
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$Content$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                final ShortcutHelperActivity shortcutHelperActivity2 = ShortcutHelperActivity.this;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-1939547952, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$Content$1.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        Composer composer3 = (Composer) obj3;
                        if ((((Number) obj4).intValue() & 11) == 2) {
                            ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                            if (composerImpl3.getSkipping()) {
                                composerImpl3.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        final ShortcutHelperActivity shortcutHelperActivity3 = ShortcutHelperActivity.this;
                        ShortcutHelperActivity.access$BottomSheet(shortcutHelperActivity3, new Function0() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity.Content.1.1.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                ShortcutHelperActivity.this.finish();
                                return Unit.INSTANCE;
                            }
                        }, composer3, 64);
                        return Unit.INSTANCE;
                    }
                }, composer2), composer2, 48, 1);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 56);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperActivity.access$Content(ShortcutHelperActivity.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public final void DragHandle(final int i, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1947887909);
        if ((i & 1) == 0 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final String stringResource = StringResources_androidKt.stringResource(R.string.shortcut_helper_content_description_drag_handle, composerImpl);
            Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(Modifier.Companion.$$INSTANCE, 0.0f, 16, 0.0f, 6, 5);
            composerImpl.startReplaceGroup(-1268992500);
            boolean changed = composerImpl.changed(stringResource);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$DragHandle$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        SemanticsPropertiesKt.setContentDescription((SemanticsPropertyReceiver) obj, stringResource);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            SurfaceKt.m232SurfaceT9BRK9s(SemanticsModifierKt.semantics(m102paddingqDBjuR0$default, false, (Function1) rememberedValue), MaterialTheme.getShapes(composerImpl).extraLarge, MaterialTheme.getColorScheme(composerImpl).outlineVariant, 0L, 0.0f, 0.0f, null, ComposableSingletons$ShortcutHelperActivityKt.f35lambda1, composerImpl, 12582912, 120);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$DragHandle$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ShortcutHelperActivity.this.DragHandle(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        getWindow().setDecorFitsSystemWindows(false);
        super.onCreate(bundle);
        ComponentActivityKt.setContent$default(this, new ComposableLambdaImpl(1244840227, true, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity$onCreate$1
            {
                super(2);
            }

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
                ShortcutHelperActivity.access$Content(ShortcutHelperActivity.this, composer, 8);
                return Unit.INSTANCE;
            }
        }));
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), null, null, new ShortcutHelperActivity$observeFinishRequired$1(this, null), 3);
        this.viewModel.stateInteractor.setSysUiStateFlagEnabled(true);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ShortcutHelperStateInteractor shortcutHelperStateInteractor = this.viewModel.stateInteractor;
            StateFlowImpl stateFlowImpl = shortcutHelperStateInteractor.repository.state;
            ShortcutHelperState.Inactive inactive = ShortcutHelperState.Inactive.INSTANCE;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, inactive);
            shortcutHelperStateInteractor.setSysUiStateFlagEnabled(false);
        }
    }
}
