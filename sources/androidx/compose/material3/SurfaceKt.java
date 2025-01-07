package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderModifierNodeElement;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.internal.ChildSemanticsNodeElement;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SurfaceKt {
    public static final DynamicProvidableCompositionLocal LocalAbsoluteTonalElevation = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.SurfaceKt$LocalAbsoluteTonalElevation$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new Dp(0);
        }
    });

    /* JADX WARN: Type inference failed for: r11v4, types: [androidx.compose.material3.SurfaceKt$Surface$1, kotlin.jvm.internal.Lambda] */
    /* renamed from: Surface-T9BRK9s, reason: not valid java name */
    public static final void m232SurfaceT9BRK9s(Modifier modifier, Shape shape, final long j, long j2, float f, float f2, BorderStroke borderStroke, final Function2 function2, Composer composer, int i, int i2) {
        final Modifier modifier2 = (i2 & 1) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        final Shape shape2 = (i2 & 2) != 0 ? RectangleShapeKt.RectangleShape : shape;
        long m203contentColorForek8zF_U = (i2 & 8) != 0 ? ColorSchemeKt.m203contentColorForek8zF_U(j, composer) : j2;
        float f3 = (i2 & 16) != 0 ? 0 : f;
        final float f4 = (i2 & 32) != 0 ? 0 : f2;
        final BorderStroke borderStroke2 = (i2 & 64) != 0 ? null : borderStroke;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = LocalAbsoluteTonalElevation;
        final float f5 = f3 + ((Dp) composerImpl.consume(dynamicProvidableCompositionLocal)).value;
        CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{AppBarKt$$ExternalSyntheticOutline0.m(m203contentColorForek8zF_U, ContentColorKt.LocalContentColor), dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(new Dp(f5))}, ComposableLambdaKt.rememberComposableLambda(-70914509, new Function2() { // from class: androidx.compose.material3.SurfaceKt$Surface$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: androidx.compose.material3.SurfaceKt$Surface$1$3, reason: invalid class name */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(2, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    AnonymousClass3 anonymousClass3 = (AnonymousClass3) create((PointerInputScope) obj, (Continuation) obj2);
                    Unit unit = Unit.INSTANCE;
                    anonymousClass3.invokeSuspend(unit);
                    return unit;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Modifier then;
                Composer composer2 = (Composer) obj;
                int intValue = ((Number) obj2).intValue() & 3;
                Unit unit = Unit.INSTANCE;
                if (intValue == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return unit;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                then = SemanticsModifierKt.semantics(SurfaceKt.m234access$surfaceXOJAsU(Modifier.this, shape2, SurfaceKt.m235access$surfaceColorAtElevationCLU3JFs(j, f5, composer2), borderStroke2, ((Density) composerImpl3.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(f4)), false, new Function1() { // from class: androidx.compose.material3.SurfaceKt$Surface$1.2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.IsContainer;
                        KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[5];
                        semanticsPropertyKey.setValue((SemanticsPropertyReceiver) obj3, Boolean.TRUE);
                        return Unit.INSTANCE;
                    }
                }).then(new SuspendPointerInputElement(unit, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0(new AnonymousClass3(2, null)), 6));
                Function2 function22 = function2;
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, true);
                int i3 = composerImpl3.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, then);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composerImpl3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i3))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl3, i3, function23);
                }
                Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m(0, function22, composerImpl3, true);
                return unit;
            }
        }, composerImpl), composerImpl, 56);
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [androidx.compose.material3.SurfaceKt$Surface$2, kotlin.jvm.internal.Lambda] */
    /* renamed from: Surface-o_FOJdg, reason: not valid java name */
    public static final void m233Surfaceo_FOJdg(final Function0 function0, Modifier modifier, boolean z, final Shape shape, final long j, long j2, float f, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, final ComposableLambdaImpl composableLambdaImpl, Composer composer, int i, int i2) {
        final Modifier modifier2 = (i2 & 2) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        final boolean z2 = (i2 & 4) != 0 ? true : z;
        long m203contentColorForek8zF_U = (i2 & 32) != 0 ? ColorSchemeKt.m203contentColorForek8zF_U(j, composer) : j2;
        float f2 = 0;
        final float f3 = (i2 & 128) != 0 ? 0 : f;
        final BorderStroke borderStroke2 = (i2 & 256) != 0 ? null : borderStroke;
        final MutableInteractionSource mutableInteractionSource2 = (i2 & 512) == 0 ? mutableInteractionSource : null;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1680307834);
        if (mutableInteractionSource2 == null) {
            Object rememberedValue = composerImpl.rememberedValue();
            if (rememberedValue == Composer.Companion.Empty) {
                rememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerImpl.updateRememberedValue(rememberedValue);
            }
            mutableInteractionSource2 = (MutableInteractionSource) rememberedValue;
        }
        composerImpl.end(false);
        DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = LocalAbsoluteTonalElevation;
        final float f4 = f2 + ((Dp) composerImpl.consume(dynamicProvidableCompositionLocal)).value;
        CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{AppBarKt$$ExternalSyntheticOutline0.m(m203contentColorForek8zF_U, ContentColorKt.LocalContentColor), dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(new Dp(f4))}, ComposableLambdaKt.rememberComposableLambda(1279702876, new Function2() { // from class: androidx.compose.material3.SurfaceKt$Surface$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Modifier then;
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier modifier3 = Modifier.this;
                StaticProvidableCompositionLocal staticProvidableCompositionLocal = InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                then = ClickableKt.m31clickableO2vRcR0$default(SurfaceKt.m234access$surfaceXOJAsU(modifier3.then(MinimumInteractiveModifier.INSTANCE), shape, SurfaceKt.m235access$surfaceColorAtElevationCLU3JFs(j, f4, composer2), borderStroke2, ((Density) composerImpl3.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(f3)), mutableInteractionSource2, RippleKt.m221rippleH2RKhps$default(0.0f, false, 7), z2, null, function0, 24).then(new ChildSemanticsNodeElement(new Function1() { // from class: androidx.compose.material3.internal.ChildParentSemanticsKt$childSemantics$1
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                        return Unit.INSTANCE;
                    }
                }));
                MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                if (mutableInteractionSource3 == null) {
                    mutableInteractionSource3 = InteractionSourceKt.MutableInteractionSource();
                }
                Modifier then2 = then.then(new InteractionSourceModifierElement(mutableInteractionSource3));
                Function2 function2 = composableLambdaImpl;
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, true);
                int i3 = composerImpl3.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, then2);
                ComposeUiNode.Companion.getClass();
                Function0 function02 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function02);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composerImpl3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i3))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl3, i3, function22);
                }
                Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m(0, function2, composerImpl3, true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 56);
    }

    /* renamed from: access$surface-XO-JAsU, reason: not valid java name */
    public static final Modifier m234access$surfaceXOJAsU(Modifier modifier, Shape shape, long j, BorderStroke borderStroke, float f) {
        Modifier modifier2 = Modifier.Companion.$$INSTANCE;
        Modifier then = modifier.then(f > 0.0f ? GraphicsLayerModifierKt.m376graphicsLayerAp8cVGQ$default(modifier2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, shape, false, null, 124895) : modifier2);
        if (borderStroke != null) {
            modifier2 = new BorderModifierNodeElement(borderStroke.width, borderStroke.brush, shape);
        }
        return ClipKt.clip(BackgroundKt.m25backgroundbw27NRU(then.then(modifier2), j, shape), shape);
    }

    /* renamed from: access$surfaceColorAtElevation-CLU3JFs, reason: not valid java name */
    public static final long m235access$surfaceColorAtElevationCLU3JFs(long j, float f, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ColorScheme colorScheme = MaterialTheme.getColorScheme(composer);
        return (Color.m363equalsimpl0(j, colorScheme.surface) && ((Boolean) ((ComposerImpl) composer).consume(ColorSchemeKt.LocalTonalElevationEnabled)).booleanValue()) ? ColorSchemeKt.m205surfaceColorAtElevation3ABfNKs(colorScheme, f) : j;
    }
}
