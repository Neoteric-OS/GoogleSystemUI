package com.airbnb.lottie.compose;

import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import com.airbnb.lottie.AsyncUpdates;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.RenderMode;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LottieAnimationKt {
    public static final void LottieAnimation(final LottieComposition lottieComposition, final Function0 function0, Modifier modifier, boolean z, boolean z2, boolean z3, RenderMode renderMode, boolean z4, LottieDynamicProperties lottieDynamicProperties, Alignment alignment, ContentScale contentScale, boolean z5, boolean z6, Map map, AsyncUpdates asyncUpdates, boolean z7, Composer composer, final int i, final int i2, final int i3) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-674272918);
        Modifier modifier2 = (i3 & 4) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        boolean z8 = (i3 & 8) != 0 ? false : z;
        boolean z9 = (i3 & 16) != 0 ? false : z2;
        boolean z10 = (i3 & 32) != 0 ? false : z3;
        RenderMode renderMode2 = (i3 & 64) != 0 ? RenderMode.AUTOMATIC : renderMode;
        boolean z11 = (i3 & 128) != 0 ? false : z4;
        LottieDynamicProperties lottieDynamicProperties2 = (i3 & 256) != 0 ? null : lottieDynamicProperties;
        Alignment alignment2 = (i3 & 512) != 0 ? Alignment.Companion.Center : alignment;
        ContentScale contentScale2 = (i3 & 1024) != 0 ? ContentScale.Companion.Fit : contentScale;
        boolean z12 = (i3 & 2048) != 0 ? true : z5;
        boolean z13 = (i3 & 4096) != 0 ? false : z6;
        Map map2 = (i3 & 8192) != 0 ? null : map;
        AsyncUpdates asyncUpdates2 = (i3 & 16384) != 0 ? AsyncUpdates.AUTOMATIC : asyncUpdates;
        boolean z14 = (32768 & i3) != 0 ? false : z7;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(185152052);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new LottieDrawable();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final LottieDrawable lottieDrawable = (LottieDrawable) rememberedValue;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, 185152099);
        if (m == composer$Companion$Empty$1) {
            m = new Matrix();
            composerImpl.updateRememberedValue(m);
        }
        final Matrix matrix = (Matrix) m;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(185152179);
        boolean changed = composerImpl.changed(lottieComposition);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        final MutableState mutableState = (MutableState) rememberedValue2;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(185152231);
        if (lottieComposition == null || lottieComposition.getDuration() == 0.0f) {
            final Modifier modifier3 = modifier2;
            BoxKt.Box(modifier3, composerImpl, (i >> 6) & 14);
            composerImpl.end(false);
            RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
            if (endRestartGroup != null) {
                final boolean z15 = z8;
                final boolean z16 = z9;
                final boolean z17 = z10;
                final RenderMode renderMode3 = renderMode2;
                final boolean z18 = z11;
                final LottieDynamicProperties lottieDynamicProperties3 = lottieDynamicProperties2;
                final Alignment alignment3 = alignment2;
                final ContentScale contentScale3 = contentScale2;
                final boolean z19 = z12;
                final boolean z20 = z13;
                final Map map3 = map2;
                final AsyncUpdates asyncUpdates3 = asyncUpdates2;
                final boolean z21 = z14;
                endRestartGroup.block = new Function2() { // from class: com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        LottieAnimationKt.LottieAnimation(LottieComposition.this, function0, modifier3, z15, z16, z17, renderMode3, z18, lottieDynamicProperties3, alignment3, contentScale3, z19, z20, map3, asyncUpdates3, z21, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        return Unit.INSTANCE;
                    }
                };
                return;
            }
            return;
        }
        composerImpl.end(false);
        final Rect rect = lottieComposition.bounds;
        Modifier then = modifier2.then(new LottieAnimationSizeElement(rect.width(), rect.height()));
        final ContentScale contentScale4 = contentScale2;
        final Alignment alignment4 = alignment2;
        final boolean z22 = z10;
        final boolean z23 = z14;
        final RenderMode renderMode4 = renderMode2;
        final AsyncUpdates asyncUpdates4 = asyncUpdates2;
        final Map map4 = map2;
        final Modifier modifier4 = modifier2;
        final LottieDynamicProperties lottieDynamicProperties4 = lottieDynamicProperties2;
        final boolean z24 = z8;
        final boolean z25 = z9;
        final boolean z26 = z11;
        final boolean z27 = z12;
        final boolean z28 = z13;
        CanvasKt.Canvas(then, new Function1() { // from class: com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:143:0x0421, code lost:
            
                if (r1.progress != r11.animator.getAnimatedValueAbsolute()) goto L139;
             */
            /* JADX WARN: Code restructure failed: missing block: B:144:0x0423, code lost:
            
                ((java.util.concurrent.ThreadPoolExecutor) com.airbnb.lottie.LottieDrawable.setProgressExecutor).execute(r11.updateProgressRunnable);
             */
            /* JADX WARN: Code restructure failed: missing block: B:148:0x045b, code lost:
            
                if (r1.progress != r11.animator.getAnimatedValueAbsolute()) goto L139;
             */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r32) {
                /*
                    Method dump skipped, instructions count: 1121
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$2.invoke(java.lang.Object):java.lang.Object");
            }
        }, composerImpl, 0);
        RecomposeScopeImpl endRestartGroup2 = composerImpl.endRestartGroup();
        if (endRestartGroup2 != null) {
            final boolean z29 = z8;
            final boolean z30 = z9;
            final boolean z31 = z10;
            final RenderMode renderMode5 = renderMode2;
            final boolean z32 = z11;
            final LottieDynamicProperties lottieDynamicProperties5 = lottieDynamicProperties2;
            final Alignment alignment5 = alignment2;
            final ContentScale contentScale5 = contentScale2;
            final boolean z33 = z12;
            final boolean z34 = z13;
            final Map map5 = map2;
            final AsyncUpdates asyncUpdates5 = asyncUpdates2;
            final boolean z35 = z14;
            endRestartGroup2.block = new Function2() { // from class: com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    LottieAnimationKt.LottieAnimation(LottieComposition.this, function0, modifier4, z29, z30, z31, renderMode5, z32, lottieDynamicProperties5, alignment5, contentScale5, z33, z34, map5, asyncUpdates5, z35, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
