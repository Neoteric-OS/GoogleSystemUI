package com.android.systemui.statusbar.policy.ui.dialog;

import android.content.Intent;
import android.util.Log;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsProperties_androidKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.lifecycle.DefaultLifecycleObserver;
import com.android.compose.PlatformButtonsKt;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.dialog.ui.composable.AlertDialogContentKt;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.QSModesEvent;
import com.android.systemui.statusbar.phone.ComponentSystemUIDialog;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.SystemUIDialogFactory;
import com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt;
import com.android.systemui.statusbar.policy.ui.dialog.composable.ModeTileGridKt;
import com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModesDialogViewModel;
import com.android.systemui.util.Assert;
import dagger.internal.Provider;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesDialogDelegate implements SystemUIDialog.Delegate {
    public static final Intent ZEN_MODE_SETTINGS_INTENT = new Intent("android.settings.ZEN_MODE_SETTINGS");
    public final ActivityStarter activityStarter;
    public ComponentSystemUIDialog currentDialog;
    public final ModesDialogEventLogger dialogEventLogger;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final CoroutineContext mainCoroutineContext;
    public final SystemUIDialogFactory sysuiDialogFactory;
    public final Provider viewModel;

    public ModesDialogDelegate(SystemUIDialogFactory systemUIDialogFactory, DialogTransitionAnimator dialogTransitionAnimator, ActivityStarter activityStarter, Provider provider, ModesDialogEventLogger modesDialogEventLogger, CoroutineContext coroutineContext) {
        this.sysuiDialogFactory = systemUIDialogFactory;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.activityStarter = activityStarter;
        this.viewModel = provider;
        this.dialogEventLogger = modesDialogEventLogger;
        this.mainCoroutineContext = coroutineContext;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$2, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$4, kotlin.jvm.internal.Lambda] */
    public static final void access$ModesDialogContent(final ModesDialogDelegate modesDialogDelegate, final SystemUIDialog systemUIDialog, Composer composer, final int i) {
        modesDialogDelegate.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-134546816);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        AlertDialogContentKt.AlertDialogContent(ComposableSingletons$ModesDialogDelegateKt.f47lambda1, ComposableLambdaKt.rememberComposableLambda(129621693, new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$2
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
                ModeTileGridKt.ModeTileGrid((ModesDialogViewModel) ModesDialogDelegate.this.viewModel.get(), composer2, 8);
                return Unit.INSTANCE;
            }
        }, composerImpl), SemanticsModifierKt.semantics(Modifier.Companion.$$INSTANCE, false, new Function1() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KProperty[] kPropertyArr = SemanticsProperties_androidKt.$$delegatedProperties;
                SemanticsPropertyKey semanticsPropertyKey = SemanticsPropertiesAndroid.TestTagsAsResourceId;
                KProperty kProperty = SemanticsProperties_androidKt.$$delegatedProperties[0];
                semanticsPropertyKey.setValue((SemanticsPropertyReceiver) obj, Boolean.TRUE);
                return Unit.INSTANCE;
            }
        }), null, ComposableLambdaKt.rememberComposableLambda(-1116358758, new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$3
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
                final SystemUIDialog systemUIDialog2 = SystemUIDialog.this;
                PlatformButtonsKt.PlatformButton(new Function0() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$3.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        SystemUIDialog.this.dismiss();
                        return Unit.INSTANCE;
                    }
                }, null, false, null, ComposableSingletons$ModesDialogDelegateKt.f48lambda2, composer2, 24576, 14);
                return Unit.INSTANCE;
            }
        }, composerImpl), null, ComposableLambdaKt.rememberComposableLambda(-1947012392, new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                final ModesDialogDelegate modesDialogDelegate2 = ModesDialogDelegate.this;
                final SystemUIDialog systemUIDialog2 = systemUIDialog;
                PlatformButtonsKt.PlatformOutlinedButton(new Function0() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$4.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ModesDialogDelegate.this.openSettings(systemUIDialog2);
                        return Unit.INSTANCE;
                    }
                }, null, false, null, null, ComposableSingletons$ModesDialogDelegateKt.f49lambda3, composer2, 196608, 30);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 1597494, 40);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$ModesDialogContent$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ModesDialogDelegate.access$ModesDialogContent(ModesDialogDelegate.this, systemUIDialog, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        Assert.isMainThread();
        if (this.currentDialog != null) {
            Log.w("ModesDialogDelegate", "Dialog is already open, dismissing it and creating a new one.");
            ComponentSystemUIDialog componentSystemUIDialog = this.currentDialog;
            if (componentSystemUIDialog != null) {
                componentSystemUIDialog.dismiss();
            }
        }
        ComponentSystemUIDialog create$default = SystemUIDialogFactoryExtKt.create$default(this.sysuiDialogFactory, null, null, new ComposableLambdaImpl(-1992485395, true, new Function3() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$createDialog$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                OpaqueKey opaqueKey = ComposerKt.invocation;
                ModesDialogDelegate.access$ModesDialogContent(ModesDialogDelegate.this, (SystemUIDialog) obj, (Composer) obj2, 72);
                return Unit.INSTANCE;
            }
        }), 15);
        this.currentDialog = create$default;
        create$default.getLifecycleRegistry$1().addObserver(new DefaultLifecycleObserver() { // from class: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$createDialog$2
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onStop$1() {
                Assert.isMainThread();
                ModesDialogDelegate.this.currentDialog = null;
            }
        });
        ComponentSystemUIDialog componentSystemUIDialog2 = this.currentDialog;
        Intrinsics.checkNotNull(componentSystemUIDialog2);
        return componentSystemUIDialog2;
    }

    public final void openSettings(SystemUIDialog systemUIDialog) {
        this.dialogEventLogger.uiEventLogger.log(QSModesEvent.QS_MODES_SETTINGS);
        DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(this.dialogTransitionAnimator, systemUIDialog);
        if (createActivityTransitionController$default == null) {
            systemUIDialog.dismiss();
        }
        this.activityStarter.startActivity(ZEN_MODE_SETTINGS_INTENT, true, (ActivityTransitionAnimator.Controller) createActivityTransitionController$default);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object showDialog(com.android.systemui.animation.Expandable r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$1 r0 = (com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$1 r0 = new com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r4 = r0.L$0
            com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate r4 = (com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L49
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$2 r6 = new com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate$showDialog$2
            r2 = 0
            r6.<init>(r4, r5, r2)
            r0.L$0 = r4
            r0.label = r3
            kotlin.coroutines.CoroutineContext r5 = r4.mainCoroutineContext
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r0)
            if (r5 != r1) goto L49
            return r1
        L49:
            com.android.systemui.statusbar.phone.ComponentSystemUIDialog r4 = r4.currentDialog
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate.showDialog(com.android.systemui.animation.Expandable, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static /* synthetic */ void getCurrentDialog$annotations() {
    }
}
