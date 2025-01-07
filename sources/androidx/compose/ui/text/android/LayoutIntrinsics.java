package androidx.compose.ui.text.android;

import android.text.BoringLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutIntrinsics {
    public BoringLayout.Metrics _boringMetrics;
    public CharSequence _charSequenceForIntrinsicWidth;
    public float _maxIntrinsicWidth = Float.NaN;
    public float _minIntrinsicWidth = Float.NaN;
    public boolean boringMetricsIsInit;
    public final CharSequence charSequence;
    public final int textDirectionHeuristic;
    public final TextPaint textPaint;

    public LayoutIntrinsics(CharSequence charSequence, TextPaint textPaint, int i) {
        this.charSequence = charSequence;
        this.textPaint = textPaint;
        this.textDirectionHeuristic = i;
    }

    public final CharSequence getCharSequenceForIntrinsicWidth() {
        CharSequence charSequence = this._charSequenceForIntrinsicWidth;
        if (charSequence == null) {
            charSequence = this.charSequence;
            if (charSequence instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence;
                if (SpannedExtensions_androidKt.hasSpan(spanned, CharacterStyle.class)) {
                    CharacterStyle[] characterStyleArr = (CharacterStyle[]) spanned.getSpans(0, charSequence.length(), CharacterStyle.class);
                    if (characterStyleArr != null && characterStyleArr.length != 0) {
                        SpannableString spannableString = null;
                        for (CharacterStyle characterStyle : characterStyleArr) {
                            if (!(characterStyle instanceof MetricAffectingSpan)) {
                                if (spannableString == null) {
                                    spannableString = new SpannableString(charSequence);
                                }
                                spannableString.removeSpan(characterStyle);
                            }
                        }
                        if (spannableString != null) {
                            charSequence = spannableString;
                        }
                    }
                }
            }
            this._charSequenceForIntrinsicWidth = charSequence;
        } else {
            Intrinsics.checkNotNull(charSequence);
        }
        return charSequence;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0068, code lost:
    
        if (androidx.compose.ui.text.android.SpannedExtensions_androidKt.hasSpan(r2, androidx.compose.ui.text.android.style.LetterSpacingSpanEm.class) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0070, code lost:
    
        if (r3.getLetterSpacing() == 0.0f) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float getMaxIntrinsicWidth() {
        /*
            r5 = this;
            float r0 = r5._maxIntrinsicWidth
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto Lc
            float r5 = r5._maxIntrinsicWidth
            goto L79
        Lc:
            boolean r0 = r5.boringMetricsIsInit
            if (r0 != 0) goto L24
            int r0 = r5.textDirectionHeuristic
            android.text.TextDirectionHeuristic r0 = androidx.compose.ui.text.android.TextLayout_androidKt.getTextDirectionHeuristic(r0)
            java.lang.CharSequence r1 = r5.charSequence
            android.text.TextPaint r2 = r5.textPaint
            r3 = 0
            r4 = 1
            android.text.BoringLayout$Metrics r0 = android.text.BoringLayout.isBoring(r1, r2, r0, r4, r3)
            r5._boringMetrics = r0
            r5.boringMetricsIsInit = r4
        L24:
            android.text.BoringLayout$Metrics r0 = r5._boringMetrics
            if (r0 == 0) goto L2b
            int r0 = r0.width
            goto L2c
        L2b:
            r0 = -1
        L2c:
            float r0 = (float) r0
            r1 = 0
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 >= 0) goto L4b
            java.lang.CharSequence r0 = r5.getCharSequenceForIntrinsicWidth()
            int r0 = r0.length()
            java.lang.CharSequence r2 = r5.getCharSequenceForIntrinsicWidth()
            android.text.TextPaint r3 = r5.textPaint
            r4 = 0
            float r0 = android.text.Layout.getDesiredWidth(r2, r4, r0, r3)
            double r2 = (double) r0
            double r2 = java.lang.Math.ceil(r2)
            float r0 = (float) r2
        L4b:
            java.lang.CharSequence r2 = r5.charSequence
            android.text.TextPaint r3 = r5.textPaint
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 != 0) goto L54
            goto L76
        L54:
            boolean r4 = r2 instanceof android.text.Spanned
            if (r4 == 0) goto L6a
            android.text.Spanned r2 = (android.text.Spanned) r2
            java.lang.Class<androidx.compose.ui.text.android.style.LetterSpacingSpanPx> r4 = androidx.compose.ui.text.android.style.LetterSpacingSpanPx.class
            boolean r4 = androidx.compose.ui.text.android.SpannedExtensions_androidKt.hasSpan(r2, r4)
            if (r4 != 0) goto L73
            java.lang.Class<androidx.compose.ui.text.android.style.LetterSpacingSpanEm> r4 = androidx.compose.ui.text.android.style.LetterSpacingSpanEm.class
            boolean r2 = androidx.compose.ui.text.android.SpannedExtensions_androidKt.hasSpan(r2, r4)
            if (r2 != 0) goto L73
        L6a:
            float r2 = r3.getLetterSpacing()
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 != 0) goto L73
            goto L76
        L73:
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
        L76:
            r5._maxIntrinsicWidth = r0
            r5 = r0
        L79:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutIntrinsics.getMaxIntrinsicWidth():float");
    }
}
