package com.airbnb.lottie.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LottieDynamicPropertiesKt {
    public static final LottieDynamicProperties rememberLottieDynamicProperties(LottieDynamicProperty[] lottieDynamicPropertyArr, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-395574495);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int hashCode = Arrays.hashCode(lottieDynamicPropertyArr);
        composerImpl.startReplaceGroup(34468001);
        boolean changed = composerImpl.changed(hashCode);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new LottieDynamicProperties(ArraysKt.toList(lottieDynamicPropertyArr));
            composerImpl.updateRememberedValue(rememberedValue);
        }
        LottieDynamicProperties lottieDynamicProperties = (LottieDynamicProperties) rememberedValue;
        composerImpl.end(false);
        composerImpl.end(false);
        return lottieDynamicProperties;
    }
}
