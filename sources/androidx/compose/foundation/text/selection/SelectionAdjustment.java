package androidx.compose.foundation.text.selection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface SelectionAdjustment {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = null;
        public static final SelectionAdjustment$Companion$$ExternalSyntheticLambda0 CharacterWithWordAccelerate;
        public static final SelectionAdjustment$Companion$$ExternalSyntheticLambda0 None;
        public static final SelectionAdjustment$Companion$$ExternalSyntheticLambda0 Paragraph;
        public static final SelectionAdjustment$Companion$$ExternalSyntheticLambda0 Word;

        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0] */
        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0] */
        /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0] */
        /* JADX WARN: Type inference failed for: r0v3, types: [androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0] */
        static {
            final int i = 0;
            None = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0
                /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
                
                    if (r1.offset == r4.offset) goto L41;
                 */
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final androidx.compose.foundation.text.selection.Selection adjust(androidx.compose.foundation.text.selection.SelectionLayout r10) {
                    /*
                        Method dump skipped, instructions count: 354
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0.adjust(androidx.compose.foundation.text.selection.SelectionLayout):androidx.compose.foundation.text.selection.Selection");
                }
            };
            final int i2 = 1;
            Word = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    /*
                        Method dump skipped, instructions count: 354
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0.adjust(androidx.compose.foundation.text.selection.SelectionLayout):androidx.compose.foundation.text.selection.Selection");
                }
            };
            final int i3 = 2;
            Paragraph = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    /*
                        Method dump skipped, instructions count: 354
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0.adjust(androidx.compose.foundation.text.selection.SelectionLayout):androidx.compose.foundation.text.selection.Selection");
                }
            };
            final int i4 = 3;
            CharacterWithWordAccelerate = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    /*
                        Method dump skipped, instructions count: 354
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$$ExternalSyntheticLambda0.adjust(androidx.compose.foundation.text.selection.SelectionLayout):androidx.compose.foundation.text.selection.Selection");
                }
            };
        }
    }

    Selection adjust(SelectionLayout selectionLayout);
}
