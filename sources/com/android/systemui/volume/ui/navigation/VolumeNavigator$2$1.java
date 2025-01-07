package com.android.systemui.volume.ui.navigation;

import android.content.res.Configuration;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsHolder;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.systemui.statusbar.phone.ComponentSystemUIDialog;
import com.android.systemui.statusbar.phone.EdgeToEdgeDialogDelegate;
import com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt;
import com.android.systemui.volume.panel.ui.VolumePanelUiEvent;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class VolumeNavigator$2$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VolumeNavigator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeNavigator$2$1(VolumeNavigator volumeNavigator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = volumeNavigator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        VolumeNavigator$2$1 volumeNavigator$2$1 = new VolumeNavigator$2$1(this.this$0, continuation);
        volumeNavigator$2$1.L$0 = obj;
        return volumeNavigator$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((VolumeNavigator$2$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final ComponentSystemUIDialog create;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final VolumeNavigator volumeNavigator = this.this$0;
            volumeNavigator.getClass();
            create = SystemUIDialogFactoryExtKt.create(r1, volumeNavigator.dialogFactory.applicationContext, R.style.Theme_SystemUI_BottomSheet, new EdgeToEdgeDialogDelegate(), new ComposableLambdaImpl(1458194704, true, new Function3() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$createBottomSheet$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                /* JADX WARN: Type inference failed for: r1v9, types: [com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$createBottomSheet$1$2$2, kotlin.jvm.internal.Lambda] */
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    final SystemUIDialog systemUIDialog = (SystemUIDialog) obj2;
                    Composer composer = (Composer) obj3;
                    ((Number) obj4).intValue();
                    OpaqueKey opaqueKey = ComposerKt.invocation;
                    Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                    Modifier access$bottomSheetClickable = SystemUIDialogFactoryExtKt.access$bottomSheetClickable(companion, new Function0() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$createBottomSheet$1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            SystemUIDialog.this.dismiss();
                            return Unit.INSTANCE;
                        }
                    }, composer, 6);
                    BiasAlignment biasAlignment = Alignment.Companion.BottomCenter;
                    final Function3 function3 = r1;
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer);
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, access$bottomSheetClickable);
                    ComposeUiNode.Companion.getClass();
                    Function0 function0 = ComposeUiNode.Companion.Constructor;
                    composerImpl.startReusableNode();
                    if (composerImpl.inserting) {
                        composerImpl.createNode(function0);
                    } else {
                        composerImpl.useNode();
                    }
                    Updater.m259setimpl(composer, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                    Updater.m259setimpl(composer, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                    Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl, currentCompositeKeyHash, function2);
                    }
                    Updater.m259setimpl(composer, materializeModifier, ComposeUiNode.Companion.SetModifier);
                    float dimensionResource = PrimitiveResources_androidKt.dimensionResource(R.dimen.bottom_sheet_corner_radius, composer);
                    composerImpl.startReplaceGroup(154623750);
                    boolean z = ((Configuration) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration)).orientation == 1;
                    Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
                    WindowInsetsHolder.Companion companion2 = WindowInsetsHolder.Companion;
                    WindowInsets windowInsets = WindowInsetsHolder.Companion.current(composerImpl).safeDrawing;
                    float f = z ? 0 : 48;
                    StaticProvidableCompositionLocal staticProvidableCompositionLocal = CompositionLocalsKt.LocalLayoutDirection;
                    Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(companion, density.mo48toDpu2uoSUM(windowInsets.getLeft(density, (LayoutDirection) composerImpl.consume(staticProvidableCompositionLocal))) + f, density.mo48toDpu2uoSUM(windowInsets.getTop(density)), density.mo48toDpu2uoSUM(windowInsets.getRight(density, (LayoutDirection) composerImpl.consume(staticProvidableCompositionLocal))) + f, 0.0f, 8);
                    composerImpl.end(false);
                    SurfaceKt.m232SurfaceT9BRK9s(SizeKt.m118widthInVpY3zN4$default(SystemUIDialogFactoryExtKt.access$bottomSheetClickable(m102paddingqDBjuR0$default, new Function0() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$createBottomSheet$1$2$1
                        @Override // kotlin.jvm.functions.Function0
                        public final /* bridge */ /* synthetic */ Object invoke() {
                            return Unit.INSTANCE;
                        }
                    }, composer, 48), 0.0f, 800, 1), RoundedCornerShapeKt.m150RoundedCornerShapea9UjIt4$default(12, dimensionResource, dimensionResource), MaterialTheme.getColorScheme(composer).surfaceContainer, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-775727375, new Function2() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogFactoryExtKt$createBottomSheet$1$2$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj5, Object obj6) {
                            Composer composer2 = (Composer) obj5;
                            if ((((Number) obj6).intValue() & 11) == 2) {
                                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                                if (composerImpl2.getSkipping()) {
                                    composerImpl2.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            OpaqueKey opaqueKey2 = ComposerKt.invocation;
                            Modifier.Companion companion3 = Modifier.Companion.$$INSTANCE;
                            ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                            composerImpl3.startReplaceGroup(1256027668);
                            Density density2 = (Density) composerImpl3.consume(CompositionLocalsKt.LocalDensity);
                            WindowInsetsHolder.Companion companion4 = WindowInsetsHolder.Companion;
                            float mo48toDpu2uoSUM = density2.mo48toDpu2uoSUM(WindowInsetsHolder.Companion.current(composerImpl3).safeDrawing.getBottom(density2));
                            composerImpl3.end(false);
                            Modifier m102paddingqDBjuR0$default2 = PaddingKt.m102paddingqDBjuR0$default(companion3, 0.0f, 0.0f, 0.0f, mo48toDpu2uoSUM, 7);
                            Function3 function32 = Function3.this;
                            SystemUIDialog systemUIDialog2 = systemUIDialog;
                            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
                            int i2 = composerImpl3.compoundKeyHash;
                            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl3, m102paddingqDBjuR0$default2);
                            ComposeUiNode.Companion.getClass();
                            Function0 function02 = ComposeUiNode.Companion.Constructor;
                            composerImpl3.startReusableNode();
                            if (composerImpl3.inserting) {
                                composerImpl3.createNode(function02);
                            } else {
                                composerImpl3.useNode();
                            }
                            Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.SetMeasurePolicy);
                            Updater.m259setimpl(composerImpl3, currentCompositionLocalScope2, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                            Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                            if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i2))) {
                                AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl3, i2, function22);
                            }
                            Updater.m259setimpl(composerImpl3, materializeModifier2, ComposeUiNode.Companion.SetModifier);
                            function32.invoke(systemUIDialog2, composerImpl3, 8);
                            composerImpl3.end(true);
                            return Unit.INSTANCE;
                        }
                    }, composer), composer, 12582912, 120);
                    composerImpl.end(true);
                    return Unit.INSTANCE;
                }
            }));
            this.this$0.uiEventLogger.log(VolumePanelUiEvent.VOLUME_PANEL_SHOWN);
            create.show();
            Function0 function0 = new Function0() { // from class: com.android.systemui.volume.ui.navigation.VolumeNavigator$2$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    create.dismiss();
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
