package androidx.compose.ui.text;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnnotatedString implements CharSequence {
    public final List annotations;
    public final List paragraphStylesOrNull;
    public final List spanStylesOrNull;
    public final String text;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Annotation {
    }

    static {
        SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotatedStringSaver;
    }

    public AnnotatedString(List list, String str) {
        ArrayList arrayList;
        List sortedWith;
        this.annotations = list;
        this.text = str;
        int i = 0;
        ArrayList arrayList2 = null;
        if (list != null) {
            int size = list.size();
            arrayList = null;
            for (int i2 = 0; i2 < size; i2++) {
                Range range = (Range) list.get(i2);
                Object obj = range.item;
                if (obj instanceof SpanStyle) {
                    arrayList2 = arrayList2 == null ? new ArrayList() : arrayList2;
                    arrayList2.add(range);
                } else if (obj instanceof ParagraphStyle) {
                    arrayList = arrayList == null ? new ArrayList() : arrayList;
                    arrayList.add(range);
                }
            }
        } else {
            arrayList = null;
        }
        this.spanStylesOrNull = arrayList2;
        this.paragraphStylesOrNull = arrayList;
        if (arrayList == null || (sortedWith = CollectionsKt.sortedWith(arrayList, new AnnotatedString$special$$inlined$sortedBy$1())) == null) {
            return;
        }
        int size2 = sortedWith.size();
        int i3 = -1;
        while (i < size2) {
            Range range2 = (Range) sortedWith.get(i);
            if (range2.start < i3) {
                throw new IllegalArgumentException("ParagraphStyle should not overlap");
            }
            int length = this.text.length();
            int i4 = range2.end;
            if (i4 > length) {
                throw new IllegalArgumentException(("ParagraphStyle range [" + range2.start + ", " + i4 + ") is out of boundary").toString());
            }
            i++;
            i3 = i4;
        }
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.text.charAt(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotatedString)) {
            return false;
        }
        AnnotatedString annotatedString = (AnnotatedString) obj;
        return Intrinsics.areEqual(this.text, annotatedString.text) && Intrinsics.areEqual(this.annotations, annotatedString.annotations);
    }

    public final AnnotatedString flatMapAnnotations(Function1 function1) {
        Builder builder = new Builder(this);
        ArrayList arrayList = (ArrayList) builder.annotations;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            List list = (List) function1.invoke(((Builder.MutableRange) arrayList.get(i)).toRange(Integer.MIN_VALUE));
            ArrayList arrayList3 = new ArrayList(list.size());
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Range range = (Range) list.get(i2);
                arrayList3.add(new Builder.MutableRange(range.item, range.start, range.end, range.tag));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, arrayList2);
        }
        builder.annotations.clear();
        builder.annotations.addAll(arrayList2);
        return builder.toAnnotatedString();
    }

    public final List getLinkAnnotations(int i) {
        List list = this.annotations;
        if (list == null) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            Range range = (Range) obj;
            if ((range.item instanceof LinkAnnotation) && AnnotatedStringKt.intersect(0, i, range.start, range.end)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final int hashCode() {
        int hashCode = this.text.hashCode() * 31;
        List list = this.annotations;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.text.length();
    }

    public final AnnotatedString mapAnnotations(Function1 function1) {
        Builder builder = new Builder(this);
        int size = ((ArrayList) builder.annotations).size();
        for (int i = 0; i < size; i++) {
            Range range = (Range) function1.invoke(((Builder.MutableRange) ((ArrayList) builder.annotations).get(i)).toRange(Integer.MIN_VALUE));
            builder.annotations.set(i, new Builder.MutableRange(range.item, range.start, range.end, range.tag));
        }
        return builder.toAnnotatedString();
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.text;
    }

    @Override // java.lang.CharSequence
    public final AnnotatedString subSequence(int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException(("start (" + i + ") should be less or equal to end (" + i2 + ')').toString());
        }
        if (i == 0 && i2 == this.text.length()) {
            return this;
        }
        String substring = this.text.substring(i, i2);
        List list = this.annotations;
        AnnotatedString annotatedString = AnnotatedStringKt.EmptyAnnotatedString;
        if (i > i2) {
            throw new IllegalArgumentException(("start (" + i + ") should be less than or equal to end (" + i2 + ')').toString());
        }
        ArrayList arrayList = null;
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(list.size());
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Range range = (Range) list.get(i3);
                int i4 = range.start;
                int i5 = range.end;
                if (AnnotatedStringKt.intersect(i, i2, i4, i5)) {
                    arrayList2.add(new Range(range.item, Math.max(i, range.start) - i, Math.min(i2, i5) - i, range.tag));
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList = arrayList2;
            }
        }
        return new AnnotatedString(arrayList, substring);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder implements Appendable {
        public final List annotations;
        public final List styleStack;
        public final StringBuilder text;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class MutableRange {
            public int end;
            public final Object item;
            public final int start;
            public final String tag;

            public /* synthetic */ MutableRange(SpanStyle spanStyle, int i, int i2, int i3) {
                this(spanStyle, i, (i3 & 4) != 0 ? Integer.MIN_VALUE : i2, "");
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MutableRange)) {
                    return false;
                }
                MutableRange mutableRange = (MutableRange) obj;
                return Intrinsics.areEqual(this.item, mutableRange.item) && this.start == mutableRange.start && this.end == mutableRange.end && Intrinsics.areEqual(this.tag, mutableRange.tag);
            }

            public final int hashCode() {
                Object obj = this.item;
                return this.tag.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.end, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.start, (obj == null ? 0 : obj.hashCode()) * 31, 31), 31);
            }

            public final Range toRange(int i) {
                int i2 = this.end;
                if (i2 != Integer.MIN_VALUE) {
                    i = i2;
                }
                if (i == Integer.MIN_VALUE) {
                    throw new IllegalStateException("Item.end should be set first");
                }
                return new Range(this.item, this.start, i, this.tag);
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("MutableRange(item=");
                sb.append(this.item);
                sb.append(", start=");
                sb.append(this.start);
                sb.append(", end=");
                sb.append(this.end);
                sb.append(", tag=");
                return OpaqueKey$$ExternalSyntheticOutline0.m(sb, this.tag, ')');
            }

            public MutableRange(Object obj, int i, int i2, String str) {
                this.item = obj;
                this.start = i;
                this.end = i2;
                this.tag = str;
            }
        }

        public Builder() {
            this.text = new StringBuilder(16);
            this.styleStack = new ArrayList();
            this.annotations = new ArrayList();
        }

        public final void addStyle(SpanStyle spanStyle, int i, int i2) {
            this.annotations.add(new MutableRange(spanStyle, i, i2, 8));
        }

        @Override // java.lang.Appendable
        public final Appendable append(CharSequence charSequence) {
            if (charSequence instanceof AnnotatedString) {
                append((AnnotatedString) charSequence);
            } else {
                this.text.append(charSequence);
            }
            return this;
        }

        public final void pop(int i) {
            if (i >= ((ArrayList) this.styleStack).size()) {
                throw new IllegalStateException((i + " should be less than " + ((ArrayList) this.styleStack).size()).toString());
            }
            while (((ArrayList) this.styleStack).size() - 1 >= i) {
                if (this.styleStack.isEmpty()) {
                    throw new IllegalStateException("Nothing to pop.");
                }
                ((MutableRange) this.styleStack.remove(((ArrayList) r0).size() - 1)).end = this.text.length();
            }
        }

        public final int pushStyle(SpanStyle spanStyle) {
            MutableRange mutableRange = new MutableRange(spanStyle, this.text.length(), 0, 12);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return ((ArrayList) this.styleStack).size() - 1;
        }

        public final AnnotatedString toAnnotatedString() {
            String sb = this.text.toString();
            ArrayList arrayList = (ArrayList) this.annotations;
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(((MutableRange) arrayList.get(i)).toRange(this.text.length()));
            }
            return new AnnotatedString(sb, arrayList2);
        }

        @Override // java.lang.Appendable
        public final Appendable append(CharSequence charSequence, int i, int i2) {
            List list;
            if (charSequence instanceof AnnotatedString) {
                AnnotatedString annotatedString = (AnnotatedString) charSequence;
                int length = this.text.length();
                this.text.append((CharSequence) annotatedString.text, i, i2);
                AnnotatedString annotatedString2 = AnnotatedStringKt.EmptyAnnotatedString;
                List list2 = null;
                if (i != i2 && (list = annotatedString.annotations) != null) {
                    if (i != 0 || i2 < annotatedString.text.length()) {
                        list2 = new ArrayList(list.size());
                        int size = list.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            Range range = (Range) list.get(i3);
                            int i4 = range.start;
                            int i5 = range.end;
                            if (AnnotatedStringKt.intersect(i, i2, i4, i5)) {
                                list2.add(new Range((Annotation) range.item, RangesKt.coerceIn(range.start, i, i2) - i, RangesKt.coerceIn(i5, i, i2) - i, range.tag));
                            }
                        }
                    } else {
                        list2 = list;
                    }
                }
                if (list2 != null) {
                    int size2 = list2.size();
                    for (int i6 = 0; i6 < size2; i6++) {
                        Range range2 = (Range) list2.get(i6);
                        this.annotations.add(new MutableRange(range2.item, range2.start + length, range2.end + length, range2.tag));
                    }
                }
            } else {
                this.text.append(charSequence, i, i2);
            }
            return this;
        }

        public Builder(AnnotatedString annotatedString) {
            this();
            append(annotatedString);
        }

        @Override // java.lang.Appendable
        public final Appendable append(char c) {
            this.text.append(c);
            return this;
        }

        public final void append(AnnotatedString annotatedString) {
            int length = this.text.length();
            this.text.append(annotatedString.text);
            List list = annotatedString.annotations;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Range range = (Range) list.get(i);
                    this.annotations.add(new MutableRange(range.item, range.start + length, range.end + length, range.tag));
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Range {
        public final int end;
        public final Object item;
        public final int start;
        public final String tag;

        public Range(Object obj, int i, int i2, String str) {
            this.item = obj;
            this.start = i;
            this.end = i2;
            this.tag = str;
            if (i > i2) {
                throw new IllegalArgumentException("Reversed range is not supported");
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return false;
            }
            Range range = (Range) obj;
            return Intrinsics.areEqual(this.item, range.item) && this.start == range.start && this.end == range.end && Intrinsics.areEqual(this.tag, range.tag);
        }

        public final int hashCode() {
            Object obj = this.item;
            return this.tag.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.end, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.start, (obj == null ? 0 : obj.hashCode()) * 31, 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Range(item=");
            sb.append(this.item);
            sb.append(", start=");
            sb.append(this.start);
            sb.append(", end=");
            sb.append(this.end);
            sb.append(", tag=");
            return OpaqueKey$$ExternalSyntheticOutline0.m(sb, this.tag, ')');
        }

        public Range(int i, int i2, Object obj) {
            this(obj, i, i2, "");
        }
    }

    public AnnotatedString(String str) {
        this(str, EmptyList.INSTANCE);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AnnotatedString(java.lang.String r2, java.util.List r3, int r4) {
        /*
            r1 = this;
            r4 = r4 & 2
            if (r4 == 0) goto L6
            kotlin.collections.EmptyList r3 = kotlin.collections.EmptyList.INSTANCE
        L6:
            kotlin.collections.EmptyList r4 = kotlin.collections.EmptyList.INSTANCE
            androidx.compose.ui.text.AnnotatedString r0 = androidx.compose.ui.text.AnnotatedStringKt.EmptyAnnotatedString
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L15
            r4.getClass()
            r3 = 0
            goto L18
        L15:
            r4.getClass()
        L18:
            r1.<init>(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.AnnotatedString.<init>(java.lang.String, java.util.List, int):void");
    }

    public AnnotatedString(String str, List list) {
        this(list.isEmpty() ? null : list, str);
    }
}
