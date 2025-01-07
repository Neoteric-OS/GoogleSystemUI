package com.android.systemui.statusbar.phone;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ComposeView;
import com.android.compose.theme.PlatformThemeKt;
import com.android.systemui.util.Assert;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIDialogFactoryExtKt {
    public static final Modifier access$bottomSheetClickable(Modifier modifier, final Function0 function0, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-853331142);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-259198655);
        boolean z = (((i & 112) ^ 48) > 32 && composerImpl.changed(function0)) || (i & 48) == 32;
        Object rememberedValue = composerImpl.rememberedValue();
        if (z || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new PointerInputEventHandler(function0) { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$bottomSheetClickable$1$1
                public final /* synthetic */ Lambda $onClick;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.$onClick = (Lambda) function0;
                }

                /* JADX WARN: Type inference failed for: r6v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    final ?? r6 = this.$onClick;
                    Object detectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, new Function1() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$bottomSheetClickable$1$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            long j = ((Offset) obj).packedValue;
                            Function0.this.invoke();
                            return Unit.INSTANCE;
                        }
                    }, continuation, 7);
                    return detectTapGestures$default == CoroutineSingletons.COROUTINE_SUSPENDED ? detectTapGestures$default : Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(modifier, function0, (PointerInputEventHandler) rememberedValue);
        composerImpl.end(false);
        return pointerInput;
    }

    public static final ComponentSystemUIDialog create(SystemUIDialogFactory systemUIDialogFactory, Context context, int i, DialogDelegate dialogDelegate, final ComposableLambdaImpl composableLambdaImpl) {
        systemUIDialogFactory.getClass();
        Assert.isMainThread();
        final ComponentSystemUIDialog componentSystemUIDialog = new ComponentSystemUIDialog(context, i, systemUIDialogFactory.dialogManager, systemUIDialogFactory.sysUiState, systemUIDialogFactory.broadcastDispatcher, systemUIDialogFactory.dialogTransitionAnimator, dialogDelegate);
        componentSystemUIDialog.create();
        ComposeView composeView = new ComposeView(context, null, 0, 6, null);
        composeView.setContent$1(new ComposableLambdaImpl(-1860640285, true, new Function2() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$create$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$create$2$1$1, kotlin.jvm.internal.Lambda] */
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
                final Function3 function3 = composableLambdaImpl;
                final ComponentSystemUIDialog componentSystemUIDialog2 = componentSystemUIDialog;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-962447399, new Function2() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$create$2$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$create$2$1$1$1, kotlin.jvm.internal.Lambda] */
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
                        ProvidedValue m = AppBarKt$$ExternalSyntheticOutline0.m(MaterialTheme.getColorScheme(composer2).onSurfaceVariant, ContentColorKt.LocalContentColor);
                        final Function3 function32 = Function3.this;
                        final ComponentSystemUIDialog componentSystemUIDialog3 = componentSystemUIDialog2;
                        CompositionLocalKt.CompositionLocalProvider(m, ComposableLambdaKt.rememberComposableLambda(582245529, new Function2() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt.create.2.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj5, Object obj6) {
                                Composer composer3 = (Composer) obj5;
                                if ((((Number) obj6).intValue() & 11) == 2) {
                                    ComposerImpl composerImpl3 = (ComposerImpl) composer3;
                                    if (composerImpl3.getSkipping()) {
                                        composerImpl3.skipToGroupEnd();
                                        return Unit.INSTANCE;
                                    }
                                }
                                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                                Function3.this.invoke(componentSystemUIDialog3, composer3, 8);
                                return Unit.INSTANCE;
                            }
                        }, composer2), composer2, 56);
                        return Unit.INSTANCE;
                    }
                }, composer), composer, 48, 1);
                return Unit.INSTANCE;
            }
        }));
        componentSystemUIDialog.setContentView(composeView);
        return componentSystemUIDialog;
    }

    public static ComponentSystemUIDialog create$default(SystemUIDialogFactory systemUIDialogFactory, Context context, final Integer num, ComposableLambdaImpl composableLambdaImpl, int i) {
        int i2;
        if ((i & 1) != 0) {
            context = systemUIDialogFactory.applicationContext;
        }
        if ((i & 2) != 0) {
            int i3 = SystemUIDialog.$r8$clinit;
            i2 = R.style.Theme_SystemUI_Dialog;
        } else {
            i2 = R.style.Theme_VolumePanel_Popup;
        }
        if ((i & 8) != 0) {
            num = null;
        }
        return create(systemUIDialogFactory, context, i2, new DialogDelegate() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$create$1
            @Override // com.android.systemui.statusbar.phone.DialogDelegate
            public final void onCreate(Dialog dialog, Bundle bundle) {
                SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
                Integer num2 = num;
                if (num2 != null) {
                    int intValue = num2.intValue();
                    Window window = systemUIDialog.getWindow();
                    if (window != null) {
                        window.setGravity(intValue);
                    }
                }
            }
        }, composableLambdaImpl);
    }
}
