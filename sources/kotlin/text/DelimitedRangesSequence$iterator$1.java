package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator, KMappedMarker {
    public int counter;
    public int currentStartIndex;
    public IntRange nextItem;
    public int nextSearchIndex;
    public int nextState = -1;
    public final /* synthetic */ DelimitedRangesSequence this$0;

    public DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.this$0 = delimitedRangesSequence;
        delimitedRangesSequence.getClass();
        int coerceIn = RangesKt.coerceIn(0, 0, delimitedRangesSequence.input.length());
        this.currentStartIndex = coerceIn;
        this.nextSearchIndex = coerceIn;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        if (r6 < r3) goto L9;
     */
    /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void calcNext$3() {
        /*
            r7 = this;
            int r0 = r7.nextSearchIndex
            r1 = 0
            if (r0 >= 0) goto Lc
            r7.nextState = r1
            r0 = 0
            r7.nextItem = r0
            goto L86
        Lc:
            kotlin.text.DelimitedRangesSequence r2 = r7.this$0
            int r3 = r2.limit
            r4 = -1
            r5 = 1
            if (r3 <= 0) goto L1b
            int r6 = r7.counter
            int r6 = r6 + r5
            r7.counter = r6
            if (r6 >= r3) goto L23
        L1b:
            java.lang.CharSequence r2 = r2.input
            int r2 = r2.length()
            if (r0 <= r2) goto L37
        L23:
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r7.currentStartIndex
            kotlin.text.DelimitedRangesSequence r2 = r7.this$0
            java.lang.CharSequence r2 = r2.input
            int r2 = kotlin.text.StringsKt.getLastIndex(r2)
            r0.<init>(r1, r2, r5)
            r7.nextItem = r0
            r7.nextSearchIndex = r4
            goto L84
        L37:
            kotlin.text.DelimitedRangesSequence r0 = r7.this$0
            kotlin.jvm.internal.Lambda r2 = r0.getNextMatch
            java.lang.CharSequence r0 = r0.input
            int r3 = r7.nextSearchIndex
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r0 = r2.invoke(r0, r3)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L5f
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r7.currentStartIndex
            kotlin.text.DelimitedRangesSequence r2 = r7.this$0
            java.lang.CharSequence r2 = r2.input
            int r2 = kotlin.text.StringsKt.getLastIndex(r2)
            r0.<init>(r1, r2, r5)
            r7.nextItem = r0
            r7.nextSearchIndex = r4
            goto L84
        L5f:
            java.lang.Object r2 = r0.component1()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.component2()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r3 = r7.currentStartIndex
            kotlin.ranges.IntRange r3 = kotlin.ranges.RangesKt.until(r3, r2)
            r7.nextItem = r3
            int r2 = r2 + r0
            r7.currentStartIndex = r2
            if (r0 != 0) goto L81
            r1 = r5
        L81:
            int r2 = r2 + r1
            r7.nextSearchIndex = r2
        L84:
            r7.nextState = r5
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.calcNext$3():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.nextState == -1) {
            calcNext$3();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.nextState == -1) {
            calcNext$3();
        }
        if (this.nextState == 0) {
            throw new NoSuchElementException();
        }
        IntRange intRange = this.nextItem;
        this.nextItem = null;
        this.nextState = -1;
        return intRange;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
