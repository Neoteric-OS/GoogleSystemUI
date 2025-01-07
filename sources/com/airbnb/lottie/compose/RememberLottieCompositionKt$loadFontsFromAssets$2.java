package com.airbnb.lottie.compose;

import android.content.Context;
import android.graphics.Typeface;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.utils.Logger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RememberLottieCompositionKt$loadFontsFromAssets$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $fontAssetsFolder;
    final /* synthetic */ String $fontFileExtension;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RememberLottieCompositionKt$loadFontsFromAssets$2(LottieComposition lottieComposition, Context context, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.$composition = lottieComposition;
        this.$context = context;
        this.$fontAssetsFolder = str;
        this.$fontFileExtension = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new RememberLottieCompositionKt$loadFontsFromAssets$2(this.$composition, this.$context, this.$fontAssetsFolder, this.$fontFileExtension, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        RememberLottieCompositionKt$loadFontsFromAssets$2 rememberLottieCompositionKt$loadFontsFromAssets$2 = (RememberLottieCompositionKt$loadFontsFromAssets$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        rememberLottieCompositionKt$loadFontsFromAssets$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        for (Font font : this.$composition.fonts.values()) {
            Context context = this.$context;
            Intrinsics.checkNotNull(font);
            String str = this.$fontAssetsFolder;
            String str2 = this.$fontFileExtension;
            String str3 = font.style;
            try {
                Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(str), font.family, str2));
                try {
                    Intrinsics.checkNotNull(createFromAsset);
                    boolean contains$default = StringsKt.contains$default(str3, "Italic");
                    boolean contains$default2 = StringsKt.contains$default(str3, "Bold");
                    int i = (contains$default && contains$default2) ? 3 : contains$default ? 2 : contains$default2 ? 1 : 0;
                    if (createFromAsset.getStyle() != i) {
                        createFromAsset = Typeface.create(createFromAsset, i);
                    }
                    font.typeface = createFromAsset;
                } catch (Exception unused) {
                    Logger.INSTANCE.getClass();
                }
            } catch (Exception unused2) {
                Logger.INSTANCE.getClass();
            }
        }
        return Unit.INSTANCE;
    }
}
