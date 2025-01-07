package androidx.compose.foundation.text;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class StringHelpers_androidKt {
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
    
        if (r0.getLoadState() == 1) goto L8;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int findFollowingBreak(int r12, java.lang.String r13) {
        /*
            boolean r0 = androidx.emoji2.text.EmojiCompat.isConfigured()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L13
            androidx.emoji2.text.EmojiCompat r0 = androidx.emoji2.text.EmojiCompat.get()
            int r3 = r0.getLoadState()
            if (r3 != r2) goto L13
            goto L14
        L13:
            r0 = r1
        L14:
            if (r0 == 0) goto L81
            int r3 = r0.getLoadState()
            r4 = 0
            if (r3 != r2) goto L1e
            goto L1f
        L1e:
            r2 = r4
        L1f:
            java.lang.String r3 = "Not initialized yet"
            androidx.core.util.Preconditions.checkState(r3, r2)
            java.lang.String r2 = "charSequence cannot be null"
            androidx.core.util.Preconditions.checkNotNull(r13, r2)
            androidx.emoji2.text.EmojiCompat$CompatInternal r0 = r0.mHelper
            androidx.emoji2.text.EmojiProcessor r5 = r0.mProcessor
            r5.getClass()
            r0 = -1
            if (r12 < 0) goto L78
            int r2 = r13.length()
            if (r12 < r2) goto L3a
            goto L78
        L3a:
            boolean r2 = r13 instanceof android.text.Spanned
            if (r2 == 0) goto L55
            r2 = r13
            android.text.Spanned r2 = (android.text.Spanned) r2
            int r3 = r12 + 1
            java.lang.Class<androidx.emoji2.text.TypefaceEmojiSpan> r6 = androidx.emoji2.text.TypefaceEmojiSpan.class
            java.lang.Object[] r3 = r2.getSpans(r12, r3, r6)
            androidx.emoji2.text.TypefaceEmojiSpan[] r3 = (androidx.emoji2.text.TypefaceEmojiSpan[]) r3
            int r6 = r3.length
            if (r6 <= 0) goto L55
            r3 = r3[r4]
            int r2 = r2.getSpanEnd(r3)
            goto L79
        L55:
            int r2 = r12 + (-16)
            int r7 = java.lang.Math.max(r4, r2)
            int r2 = r13.length()
            int r3 = r12 + 16
            int r8 = java.lang.Math.min(r2, r3)
            androidx.emoji2.text.EmojiProcessor$EmojiProcessLookupCallback r11 = new androidx.emoji2.text.EmojiProcessor$EmojiProcessLookupCallback
            r11.<init>(r12)
            r9 = 2147483647(0x7fffffff, float:NaN)
            r10 = 1
            r6 = r13
            java.lang.Object r2 = r5.process(r6, r7, r8, r9, r10, r11)
            androidx.emoji2.text.EmojiProcessor$EmojiProcessLookupCallback r2 = (androidx.emoji2.text.EmojiProcessor.EmojiProcessLookupCallback) r2
            int r2 = r2.end
            goto L79
        L78:
            r2 = r0
        L79:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            if (r2 != r0) goto L80
            goto L81
        L80:
            r1 = r3
        L81:
            if (r1 == 0) goto L88
            int r12 = r1.intValue()
            return r12
        L88:
            java.text.BreakIterator r0 = java.text.BreakIterator.getCharacterInstance()
            r0.setText(r13)
            int r12 = r0.following(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.StringHelpers_androidKt.findFollowingBreak(int, java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
    
        if (r0.getLoadState() == 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int findPrecedingBreak(int r4, java.lang.String r5) {
        /*
            boolean r0 = androidx.emoji2.text.EmojiCompat.isConfigured()
            r1 = 0
            if (r0 == 0) goto L13
            androidx.emoji2.text.EmojiCompat r0 = androidx.emoji2.text.EmojiCompat.get()
            int r2 = r0.getLoadState()
            r3 = 1
            if (r2 != r3) goto L13
            goto L14
        L13:
            r0 = r1
        L14:
            if (r0 == 0) goto L2a
            int r2 = r4 + (-1)
            r3 = 0
            int r2 = java.lang.Math.max(r3, r2)
            int r0 = r0.getEmojiStart(r2, r5)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r3 = -1
            if (r0 != r3) goto L29
            goto L2a
        L29:
            r1 = r2
        L2a:
            if (r1 == 0) goto L31
            int r4 = r1.intValue()
            return r4
        L31:
            java.text.BreakIterator r0 = java.text.BreakIterator.getCharacterInstance()
            r0.setText(r5)
            int r4 = r0.preceding(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.StringHelpers_androidKt.findPrecedingBreak(int, java.lang.String):int");
    }
}
