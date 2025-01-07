package androidx.core.text;

import android.text.SpannableStringBuilder;
import androidx.core.text.TextDirectionHeuristicsCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BidiFormatter {
    public static final BidiFormatter DEFAULT_LTR_INSTANCE;
    public static final BidiFormatter DEFAULT_RTL_INSTANCE;
    public static final TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal DEFAULT_TEXT_DIRECTION_HEURISTIC = null;
    public static final String LRM_STRING;
    public static final String RLM_STRING;
    public final boolean mIsRtlContext;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DirectionalityEstimator {
        public static final byte[] DIR_TYPE_CACHE = new byte[1792];
        public int charIndex;
        public char lastChar;
        public final int length;
        public final CharSequence text;

        static {
            for (int i = 0; i < 1792; i++) {
                DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
            }
        }

        public DirectionalityEstimator(CharSequence charSequence) {
            this.text = charSequence;
            this.length = ((String) charSequence).length();
        }

        public final byte dirTypeBackward() {
            char charAt = ((String) this.text).charAt(this.charIndex - 1);
            this.lastChar = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.charIndex--;
            char c = this.lastChar;
            return c < 1792 ? DIR_TYPE_CACHE[c] : Character.getDirectionality(c);
        }
    }

    static {
        TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal textDirectionHeuristicInternal = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        LRM_STRING = Character.toString((char) 8206);
        RLM_STRING = Character.toString((char) 8207);
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true);
    }

    public BidiFormatter(boolean z) {
        TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal textDirectionHeuristicInternal = TextDirectionHeuristicsCompat.LTR;
        this.mIsRtlContext = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0093, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0074, code lost:
    
        if (r1 != 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0077, code lost:
    
        if (r2 == 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x007d, code lost:
    
        if (r0.charIndex <= 0) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0083, code lost:
    
        switch(r0.dirTypeBackward()) {
            case 14: goto L65;
            case 15: goto L65;
            case 16: goto L64;
            case 17: goto L64;
            case 18: goto L63;
            default: goto L70;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0087, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008a, code lost:
    
        if (r1 != r3) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x008d, code lost:
    
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0090, code lost:
    
        if (r1 != r3) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getEntryDir(java.lang.CharSequence r9) {
        /*
            androidx.core.text.BidiFormatter$DirectionalityEstimator r0 = new androidx.core.text.BidiFormatter$DirectionalityEstimator
            r0.<init>(r9)
            r9 = 0
            r0.charIndex = r9
            r1 = r9
            r2 = r1
            r3 = r2
        Lb:
            int r4 = r0.charIndex
            int r5 = r0.length
            r6 = 1
            r7 = -1
            if (r4 >= r5) goto L74
            if (r1 != 0) goto L74
            java.lang.CharSequence r5 = r0.text
            java.lang.String r5 = (java.lang.String) r5
            char r4 = r5.charAt(r4)
            r0.lastChar = r4
            boolean r4 = java.lang.Character.isHighSurrogate(r4)
            if (r4 == 0) goto L3b
            java.lang.CharSequence r4 = r0.text
            int r5 = r0.charIndex
            int r4 = java.lang.Character.codePointAt(r4, r5)
            int r5 = r0.charIndex
            int r8 = java.lang.Character.charCount(r4)
            int r8 = r8 + r5
            r0.charIndex = r8
            byte r4 = java.lang.Character.getDirectionality(r4)
            goto L4f
        L3b:
            int r4 = r0.charIndex
            int r4 = r4 + r6
            r0.charIndex = r4
            char r4 = r0.lastChar
            r5 = 1792(0x700, float:2.511E-42)
            if (r4 >= r5) goto L4b
            byte[] r5 = androidx.core.text.BidiFormatter.DirectionalityEstimator.DIR_TYPE_CACHE
            r4 = r5[r4]
            goto L4f
        L4b:
            byte r4 = java.lang.Character.getDirectionality(r4)
        L4f:
            if (r4 == 0) goto L6e
            if (r4 == r6) goto L6a
            r5 = 2
            if (r4 == r5) goto L6a
            r5 = 9
            if (r4 == r5) goto Lb
            switch(r4) {
                case 14: goto L66;
                case 15: goto L66;
                case 16: goto L62;
                case 17: goto L62;
                case 18: goto L5e;
                default: goto L5d;
            }
        L5d:
            goto L72
        L5e:
            int r3 = r3 + (-1)
            r2 = r9
            goto Lb
        L62:
            int r3 = r3 + 1
            r2 = r6
            goto Lb
        L66:
            int r3 = r3 + 1
            r2 = r7
            goto Lb
        L6a:
            if (r3 != 0) goto L72
        L6c:
            r9 = r6
            goto L93
        L6e:
            if (r3 != 0) goto L72
        L70:
            r9 = r7
            goto L93
        L72:
            r1 = r3
            goto Lb
        L74:
            if (r1 != 0) goto L77
            goto L93
        L77:
            if (r2 == 0) goto L7b
            r9 = r2
            goto L93
        L7b:
            int r2 = r0.charIndex
            if (r2 <= 0) goto L93
            byte r2 = r0.dirTypeBackward()
            switch(r2) {
                case 14: goto L90;
                case 15: goto L90;
                case 16: goto L8a;
                case 17: goto L8a;
                case 18: goto L87;
                default: goto L86;
            }
        L86:
            goto L7b
        L87:
            int r3 = r3 + 1
            goto L7b
        L8a:
            if (r1 != r3) goto L8d
            goto L6c
        L8d:
            int r3 = r3 + (-1)
            goto L7b
        L90:
            if (r1 != r3) goto L8d
            goto L70
        L93:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.BidiFormatter.getEntryDir(java.lang.CharSequence):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0041, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getExitDir(java.lang.CharSequence r7) {
        /*
            androidx.core.text.BidiFormatter$DirectionalityEstimator r0 = new androidx.core.text.BidiFormatter$DirectionalityEstimator
            r0.<init>(r7)
            int r7 = r0.length
            r0.charIndex = r7
            r7 = 0
            r1 = r7
        Lb:
            r2 = r1
        Lc:
            int r3 = r0.charIndex
            if (r3 <= 0) goto L41
            byte r3 = r0.dirTypeBackward()
            r4 = -1
            if (r3 == 0) goto L3b
            r5 = 1
            if (r3 == r5) goto L35
            r6 = 2
            if (r3 == r6) goto L35
            r6 = 9
            if (r3 == r6) goto Lc
            switch(r3) {
                case 14: goto L31;
                case 15: goto L31;
                case 16: goto L2a;
                case 17: goto L2a;
                case 18: goto L27;
                default: goto L24;
            }
        L24:
            if (r2 != 0) goto Lc
            goto L40
        L27:
            int r1 = r1 + 1
            goto Lc
        L2a:
            if (r2 != r1) goto L2e
        L2c:
            r7 = r5
            goto L41
        L2e:
            int r1 = r1 + (-1)
            goto Lc
        L31:
            if (r2 != r1) goto L2e
        L33:
            r7 = r4
            goto L41
        L35:
            if (r1 != 0) goto L38
            goto L2c
        L38:
            if (r2 != 0) goto Lc
            goto L40
        L3b:
            if (r1 != 0) goto L3e
            goto L33
        L3e:
            if (r2 != 0) goto Lc
        L40:
            goto Lb
        L41:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.BidiFormatter.getExitDir(java.lang.CharSequence):int");
    }

    public final String unicodeWrap(String str) {
        if (str == null) {
            return null;
        }
        boolean isRtl = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR.isRtl(str.length(), str);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        boolean isRtl2 = (isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR).isRtl(str.length(), str);
        String str2 = "";
        String str3 = RLM_STRING;
        String str4 = LRM_STRING;
        boolean z = this.mIsRtlContext;
        spannableStringBuilder.append((CharSequence) ((z || !(isRtl2 || getEntryDir(str) == 1)) ? (!z || (isRtl2 && getEntryDir(str) != -1)) ? "" : str3 : str4));
        if (isRtl != z) {
            spannableStringBuilder.append(isRtl ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append((CharSequence) str);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append((CharSequence) str);
        }
        boolean isRtl3 = (isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR).isRtl(str.length(), str);
        if (!z && (isRtl3 || getExitDir(str) == 1)) {
            str2 = str4;
        } else if (z && (!isRtl3 || getExitDir(str) == -1)) {
            str2 = str3;
        }
        spannableStringBuilder.append((CharSequence) str2);
        return spannableStringBuilder.toString();
    }
}
