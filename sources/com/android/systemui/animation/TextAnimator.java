package com.android.systemui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.text.Layout;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextAnimator {
    public static final String TAG;
    public final ValueAnimator animator;
    public final FontVariationUtils fontVariationUtils;
    public final Function0 invalidateCallback;
    public final TextInterpolator textInterpolator;
    public final TypefaceVariantCacheImpl typefaceCache;

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(TextAnimator.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    public TextAnimator(Layout layout, Function0 function0) {
        this.invalidateCallback = function0;
        TypefaceVariantCacheImpl typefaceVariantCacheImpl = new TypefaceVariantCacheImpl(layout.getPaint().getTypeface());
        this.typefaceCache = typefaceVariantCacheImpl;
        this.textInterpolator = new TextInterpolator(layout, typefaceVariantCacheImpl);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f);
        ofFloat.setDuration(300L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.animation.TextAnimator$animator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TextAnimator.this.textInterpolator.progress = MathKt.roundToInt(((Float) valueAnimator.getAnimatedValue()).floatValue() * r1) / 30;
                TextAnimator.this.invalidateCallback.invoke();
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.animation.TextAnimator$animator$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                TextAnimator.this.textInterpolator.rebase();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                TextAnimator.this.textInterpolator.rebase();
            }
        });
        this.animator = ofFloat;
        FontVariationUtils fontVariationUtils = new FontVariationUtils();
        fontVariationUtils.mWeight = -1;
        this.fontVariationUtils = fontVariationUtils;
    }

    public static void setTextStyle$default(TextAnimator textAnimator, int i, Integer num, boolean z, long j, TimeInterpolator timeInterpolator, long j2, Runnable runnable) {
        FontVariationUtils fontVariationUtils = textAnimator.fontVariationUtils;
        fontVariationUtils.isUpdated = false;
        if (i >= 0 && fontVariationUtils.mWeight != i) {
            fontVariationUtils.isUpdated = true;
            fontVariationUtils.mWeight = i;
        }
        int i2 = fontVariationUtils.mWeight;
        textAnimator.setTextStyleInternal(fontVariationUtils.isUpdated ? i2 >= 0 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i2, "'wght' ") : "" : "", num, z, j, timeInterpolator, j2, runnable, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x002e, code lost:
    
        r3.targetPaint.setColor(r14.intValue());
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setTextStyleInternal(java.lang.String r13, java.lang.Integer r14, boolean r15, long r16, android.animation.TimeInterpolator r18, long r19, final java.lang.Runnable r21, boolean r22) {
        /*
            r12 = this;
            r1 = r12
            r2 = r13
            r7 = r18
            r10 = r21
            com.android.systemui.animation.TextInterpolator r3 = r1.textInterpolator
            if (r15 == 0) goto L18
            android.animation.ValueAnimator r0 = r1.animator     // Catch: java.lang.IllegalArgumentException -> L13
            r0.cancel()     // Catch: java.lang.IllegalArgumentException -> L13
            r3.rebase()     // Catch: java.lang.IllegalArgumentException -> L13
            goto L18
        L13:
            r0 = move-exception
            r8 = r19
            goto L87
        L18:
            if (r2 == 0) goto L2c
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r13)     // Catch: java.lang.IllegalArgumentException -> L13
            if (r0 == 0) goto L21
            goto L2c
        L21:
            android.text.TextPaint r0 = r3.targetPaint     // Catch: java.lang.IllegalArgumentException -> L13
            com.android.systemui.animation.TypefaceVariantCacheImpl r4 = r1.typefaceCache     // Catch: java.lang.IllegalArgumentException -> L13
            android.graphics.Typeface r4 = r4.getTypefaceForVariant(r13)     // Catch: java.lang.IllegalArgumentException -> L13
            r0.setTypeface(r4)     // Catch: java.lang.IllegalArgumentException -> L13
        L2c:
            if (r14 == 0) goto L37
            android.text.TextPaint r0 = r3.targetPaint     // Catch: java.lang.IllegalArgumentException -> L13
            int r4 = r14.intValue()     // Catch: java.lang.IllegalArgumentException -> L13
            r0.setColor(r4)     // Catch: java.lang.IllegalArgumentException -> L13
        L37:
            android.text.Layout r0 = r3.layout     // Catch: java.lang.IllegalArgumentException -> L13
            android.text.TextPaint r4 = r3.targetPaint     // Catch: java.lang.IllegalArgumentException -> L13
            java.util.List r0 = com.android.systemui.animation.TextInterpolator.shapeText(r0, r4)     // Catch: java.lang.IllegalArgumentException -> L13
            r4 = 0
            r3.updatePositionsAndFonts(r0, r4)     // Catch: java.lang.IllegalArgumentException -> L13
            if (r15 == 0) goto L78
            android.animation.ValueAnimator r0 = r1.animator     // Catch: java.lang.IllegalArgumentException -> L13
            r8 = r19
            r0.setStartDelay(r8)     // Catch: java.lang.IllegalArgumentException -> L64
            android.animation.ValueAnimator r0 = r1.animator     // Catch: java.lang.IllegalArgumentException -> L64
            r4 = -1
            int r4 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r4 != 0) goto L57
            r4 = 300(0x12c, double:1.48E-321)
            goto L59
        L57:
            r4 = r16
        L59:
            r0.setDuration(r4)     // Catch: java.lang.IllegalArgumentException -> L64
            if (r7 == 0) goto L66
            android.animation.ValueAnimator r0 = r1.animator     // Catch: java.lang.IllegalArgumentException -> L64
            r0.setInterpolator(r7)     // Catch: java.lang.IllegalArgumentException -> L64
            goto L66
        L64:
            r0 = move-exception
            goto L87
        L66:
            if (r10 == 0) goto L72
            com.android.systemui.animation.TextAnimator$setTextStyleInternal$listener$1 r0 = new com.android.systemui.animation.TextAnimator$setTextStyleInternal$listener$1     // Catch: java.lang.IllegalArgumentException -> L64
            r0.<init>()     // Catch: java.lang.IllegalArgumentException -> L64
            android.animation.ValueAnimator r4 = r1.animator     // Catch: java.lang.IllegalArgumentException -> L64
            r4.addListener(r0)     // Catch: java.lang.IllegalArgumentException -> L64
        L72:
            android.animation.ValueAnimator r0 = r1.animator     // Catch: java.lang.IllegalArgumentException -> L64
            r0.start()     // Catch: java.lang.IllegalArgumentException -> L64
            goto La7
        L78:
            r8 = r19
            r0 = 1065353216(0x3f800000, float:1.0)
            r3.progress = r0     // Catch: java.lang.IllegalArgumentException -> L64
            r3.rebase()     // Catch: java.lang.IllegalArgumentException -> L64
            kotlin.jvm.functions.Function0 r0 = r1.invalidateCallback     // Catch: java.lang.IllegalArgumentException -> L64
            r0.invoke()     // Catch: java.lang.IllegalArgumentException -> L64
            goto La7
        L87:
            if (r22 == 0) goto La8
            java.lang.String r4 = com.android.systemui.animation.TextAnimator.TAG
            java.lang.String r5 = "setTextStyleInternal: Exception caught but retrying. This is usually due to the layout having changed unexpectedly without being notified."
            android.util.Log.e(r4, r5, r0)
            android.text.Layout r0 = r3.layout
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r12.updateLayout(r0, r3)
            r11 = 0
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r7 = r18
            r8 = r19
            r10 = r21
            r1.setTextStyleInternal(r2, r3, r4, r5, r7, r8, r10, r11)
        La7:
            return
        La8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.TextAnimator.setTextStyleInternal(java.lang.String, java.lang.Integer, boolean, long, android.animation.TimeInterpolator, long, java.lang.Runnable, boolean):void");
    }

    public final void updateLayout(Layout layout, float f) {
        TextInterpolator textInterpolator = this.textInterpolator;
        textInterpolator.layout = layout;
        textInterpolator.shapeText(layout);
        if (f >= 0.0f) {
            textInterpolator.targetPaint.setTextSize(f);
            textInterpolator.basePaint.setTextSize(f);
            textInterpolator.updatePositionsAndFonts(TextInterpolator.shapeText(textInterpolator.layout, textInterpolator.targetPaint), false);
            textInterpolator.updatePositionsAndFonts(TextInterpolator.shapeText(textInterpolator.layout, textInterpolator.basePaint), true);
        }
    }
}
