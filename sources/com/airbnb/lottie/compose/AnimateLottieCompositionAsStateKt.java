package com.airbnb.lottie.compose;

import android.content.Context;
import android.provider.Settings;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.utils.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimateLottieCompositionAsStateKt {
    public static final LottieAnimatable animateLottieCompositionAsState(LottieComposition lottieComposition, int i, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(683659508);
        LottieCancellationBehavior lottieCancellationBehavior = LottieCancellationBehavior.Immediately;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (i <= 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Iterations must be a positive number (", ").", i).toString());
        }
        if (Float.isInfinite(1.0f) || Float.isNaN(1.0f)) {
            throw new IllegalArgumentException(("Speed must be a finite number. It is 1.0.").toString());
        }
        composerImpl.startReplaceGroup(2024497114);
        composerImpl.startReplaceGroup(-610207850);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new LottieAnimatableImpl();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        LottieAnimatable lottieAnimatable = (LottieAnimatable) rememberedValue;
        composerImpl.end(false);
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-180606964);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = SnapshotStateKt.mutableStateOf$default(true);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        MutableState mutableState = (MutableState) rememberedValue2;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-180606834);
        Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        Utils.AnonymousClass1 anonymousClass1 = Utils.threadLocalPathMeasure;
        float f = 1.0f / Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(new Object[]{lottieComposition, true, null, Float.valueOf(f), Integer.valueOf(i)}, new AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3(true, true, lottieAnimatable, lottieComposition, i, false, f, lottieCancellationBehavior, false, mutableState, null), composerImpl);
        composerImpl.end(false);
        return lottieAnimatable;
    }
}
