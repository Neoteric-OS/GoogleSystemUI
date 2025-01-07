package androidx.compose.ui.text.platform;

import android.text.Layout;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import androidx.compose.ui.text.android.LayoutIntrinsics;
import androidx.compose.ui.text.android.LayoutIntrinsics$$ExternalSyntheticLambda0;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.emoji2.text.EmojiCompat;
import java.text.BreakIterator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidParagraphIntrinsics implements ParagraphIntrinsics {
    public final CharSequence charSequence;
    public final Density density;
    public final boolean emojiCompatProcessed;
    public final FontFamily.Resolver fontFamilyResolver;
    public final LayoutIntrinsics layoutIntrinsics;
    public final List placeholders;
    public TypefaceDirtyTrackerLinkedList resolvedTypefaces;
    public final List spanStyles;
    public final TextStyle style;
    public final String text;
    public final int textDirectionHeuristic;
    public final AndroidTextPaint textPaint;

    /* JADX WARN: Code restructure failed: missing block: B:132:0x03ad, code lost:
    
        if ((r5.paragraphStyle.lineHeight & 1095216660480L) != 0) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x00b2, code lost:
    
        if (r7 == 1) goto L14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02e7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x051e  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0451  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02ca  */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r9v20, types: [android.text.Spannable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AndroidParagraphIntrinsics(java.lang.String r38, androidx.compose.ui.text.TextStyle r39, java.util.List r40, java.util.List r41, androidx.compose.ui.text.font.FontFamily.Resolver r42, androidx.compose.ui.unit.Density r43) {
        /*
            Method dump skipped, instructions count: 1378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.platform.AndroidParagraphIntrinsics.<init>(java.lang.String, androidx.compose.ui.text.TextStyle, java.util.List, java.util.List, androidx.compose.ui.text.font.FontFamily$Resolver, androidx.compose.ui.unit.Density):void");
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final boolean getHasStaleResolvedFonts() {
        TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList = this.resolvedTypefaces;
        if (!(typefaceDirtyTrackerLinkedList != null ? typefaceDirtyTrackerLinkedList.isStaleResolvedFont() : false)) {
            if (this.emojiCompatProcessed) {
                return false;
            }
            PlatformTextStyle platformTextStyle = this.style.platformStyle;
            EmojiCompatStatus emojiCompatStatus = EmojiCompatStatus.INSTANCE;
            DefaultImpl defaultImpl = (DefaultImpl) EmojiCompatStatus.delegate;
            State state = defaultImpl.loadState;
            if (state == null) {
                if (EmojiCompat.isConfigured()) {
                    state = defaultImpl.getFontLoadState();
                    defaultImpl.loadState = state;
                } else {
                    state = EmojiCompatStatus_androidKt.Falsey;
                }
            }
            if (!((Boolean) state.getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMinIntrinsicWidth() {
        float f;
        LayoutIntrinsics layoutIntrinsics = this.layoutIntrinsics;
        if (!Float.isNaN(layoutIntrinsics._minIntrinsicWidth)) {
            return layoutIntrinsics._minIntrinsicWidth;
        }
        BreakIterator lineInstance = BreakIterator.getLineInstance(layoutIntrinsics.textPaint.getTextLocale());
        CharSequence charSequence = layoutIntrinsics.charSequence;
        lineInstance.setText(new CharSequenceCharacterIterator(charSequence.length(), charSequence));
        PriorityQueue priorityQueue = new PriorityQueue(10, new LayoutIntrinsics$$ExternalSyntheticLambda0());
        int i = 0;
        for (int next = lineInstance.next(); next != -1; next = lineInstance.next()) {
            if (priorityQueue.size() < 10) {
                priorityQueue.add(new Pair(Integer.valueOf(i), Integer.valueOf(next)));
            } else {
                Pair pair = (Pair) priorityQueue.peek();
                if (pair != null && ((Number) pair.getSecond()).intValue() - ((Number) pair.getFirst()).intValue() < next - i) {
                    priorityQueue.poll();
                    priorityQueue.add(new Pair(Integer.valueOf(i), Integer.valueOf(next)));
                }
            }
            i = next;
        }
        if (priorityQueue.isEmpty()) {
            f = 0.0f;
        } else {
            Iterator it = priorityQueue.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            Pair pair2 = (Pair) it.next();
            float desiredWidth = Layout.getDesiredWidth(layoutIntrinsics.getCharSequenceForIntrinsicWidth(), ((Number) pair2.component1()).intValue(), ((Number) pair2.component2()).intValue(), layoutIntrinsics.textPaint);
            while (it.hasNext()) {
                Pair pair3 = (Pair) it.next();
                desiredWidth = Math.max(desiredWidth, Layout.getDesiredWidth(layoutIntrinsics.getCharSequenceForIntrinsicWidth(), ((Number) pair3.component1()).intValue(), ((Number) pair3.component2()).intValue(), layoutIntrinsics.textPaint));
            }
            f = desiredWidth;
        }
        layoutIntrinsics._minIntrinsicWidth = f;
        return f;
    }
}
