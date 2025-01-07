package androidx.compose.ui.text;

import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MultiParagraphIntrinsics implements ParagraphIntrinsics {
    public final AnnotatedString annotatedString;
    public final List infoList;
    public final Lazy maxIntrinsicWidth$delegate;
    public final Lazy minIntrinsicWidth$delegate;
    public final List placeholders;

    /* JADX WARN: Removed duplicated region for block: B:29:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01b0 A[EDGE_INSN: B:45:0x01b0->B:46:0x01b0 BREAK  A[LOOP:2: B:37:0x0191->B:43:0x01ab], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MultiParagraphIntrinsics(androidx.compose.ui.text.AnnotatedString r38, androidx.compose.ui.text.TextStyle r39, java.util.List r40, androidx.compose.ui.unit.Density r41, androidx.compose.ui.text.font.FontFamily.Resolver r42) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.MultiParagraphIntrinsics.<init>(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.text.TextStyle, java.util.List, androidx.compose.ui.unit.Density, androidx.compose.ui.text.font.FontFamily$Resolver):void");
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final boolean getHasStaleResolvedFonts() {
        ArrayList arrayList = (ArrayList) this.infoList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((ParagraphIntrinsicInfo) arrayList.get(i)).intrinsics.getHasStaleResolvedFonts()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMaxIntrinsicWidth() {
        return ((Number) this.maxIntrinsicWidth$delegate.getValue()).floatValue();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMinIntrinsicWidth() {
        return ((Number) this.minIntrinsicWidth$delegate.getValue()).floatValue();
    }
}
