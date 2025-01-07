package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m616updateRangeAfterDeletepWDy79M(long j, long j2) {
        int m599getLengthimpl;
        int m601getMinimpl = TextRange.m601getMinimpl(j);
        int m600getMaximpl = TextRange.m600getMaximpl(j);
        if (TextRange.m601getMinimpl(j2) >= TextRange.m600getMaximpl(j) || TextRange.m601getMinimpl(j) >= TextRange.m600getMaximpl(j2)) {
            if (m600getMaximpl > TextRange.m601getMinimpl(j2)) {
                m601getMinimpl -= TextRange.m599getLengthimpl(j2);
                m599getLengthimpl = TextRange.m599getLengthimpl(j2);
                m600getMaximpl -= m599getLengthimpl;
            }
        } else if (TextRange.m601getMinimpl(j2) > TextRange.m601getMinimpl(j) || TextRange.m600getMaximpl(j) > TextRange.m600getMaximpl(j2)) {
            if (TextRange.m601getMinimpl(j) > TextRange.m601getMinimpl(j2) || TextRange.m600getMaximpl(j2) > TextRange.m600getMaximpl(j)) {
                int m601getMinimpl2 = TextRange.m601getMinimpl(j2);
                if (m601getMinimpl >= TextRange.m600getMaximpl(j2) || m601getMinimpl2 > m601getMinimpl) {
                    m600getMaximpl = TextRange.m601getMinimpl(j2);
                } else {
                    m601getMinimpl = TextRange.m601getMinimpl(j2);
                    m599getLengthimpl = TextRange.m599getLengthimpl(j2);
                }
            } else {
                m599getLengthimpl = TextRange.m599getLengthimpl(j2);
            }
            m600getMaximpl -= m599getLengthimpl;
        } else {
            m601getMinimpl = TextRange.m601getMinimpl(j2);
            m600getMaximpl = m601getMinimpl;
        }
        return TextRangeKt.TextRange(m601getMinimpl, m600getMaximpl);
    }
}
