package com.airbnb.lottie.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieTask;
import com.airbnb.lottie.compose.LottieCompositionSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RememberLottieCompositionKt {
    public static final String access$ensureTrailingSlash(String str) {
        if (str == null || StringsKt__StringsJVMKt.isBlank(str)) {
            return null;
        }
        return (str.length() <= 0 || !CharsKt.equals(str.charAt(StringsKt.getLastIndex(str)), '/', false)) ? str.concat("/") : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$lottieComposition(android.content.Context r15, com.airbnb.lottie.compose.LottieCompositionSpec r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, kotlin.coroutines.jvm.internal.ContinuationImpl r21) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.RememberLottieCompositionKt.access$lottieComposition(android.content.Context, com.airbnb.lottie.compose.LottieCompositionSpec, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final LottieTask lottieTask(Context context, LottieCompositionSpec lottieCompositionSpec, String str) {
        if (!(lottieCompositionSpec instanceof LottieCompositionSpec.RawRes)) {
            throw new NoWhenBranchMatchedException();
        }
        if (!Intrinsics.areEqual(str, "__LottieInternalDefaultCacheKey__")) {
            return LottieCompositionFactory.fromRawRes(context, str, ((LottieCompositionSpec.RawRes) lottieCompositionSpec).resId);
        }
        int i = ((LottieCompositionSpec.RawRes) lottieCompositionSpec).resId;
        return LottieCompositionFactory.fromRawRes(context, LottieCompositionFactory.rawResCacheKey(i, context), i);
    }

    public static final LottieCompositionResultImpl rememberLottieComposition(LottieCompositionSpec.RawRes rawRes, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1248473602);
        RememberLottieCompositionKt$rememberLottieComposition$1 rememberLottieCompositionKt$rememberLottieComposition$1 = new RememberLottieCompositionKt$rememberLottieComposition$1(3, null);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        composerImpl.startReplaceGroup(1388713885);
        boolean changed = composerImpl.changed(rawRes);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (changed || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(new LottieCompositionResultImpl());
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(1388714176);
        boolean changed2 = composerImpl.changed(rawRes) | composerImpl.changed("__LottieInternalDefaultCacheKey__");
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = lottieTask(context, rawRes, "__LottieInternalDefaultCacheKey__");
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(rawRes, "__LottieInternalDefaultCacheKey__", new RememberLottieCompositionKt$rememberLottieComposition$3(rememberLottieCompositionKt$rememberLottieComposition$1, context, rawRes, null, "fonts/", ".ttf", "__LottieInternalDefaultCacheKey__", mutableState, null), composerImpl);
        LottieCompositionResultImpl lottieCompositionResultImpl = (LottieCompositionResultImpl) mutableState.getValue();
        composerImpl.end(false);
        return lottieCompositionResultImpl;
    }
}
