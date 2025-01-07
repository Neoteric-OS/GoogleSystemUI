package androidx.compose.foundation.text;

import androidx.compose.ui.layout.ParentDataModifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextRangeLayoutModifier implements ParentDataModifier {
    public final TextLinkScope$$ExternalSyntheticLambda0 measurePolicy;

    public TextRangeLayoutModifier(TextLinkScope$$ExternalSyntheticLambda0 textLinkScope$$ExternalSyntheticLambda0) {
        this.measurePolicy = textLinkScope$$ExternalSyntheticLambda0;
    }

    @Override // androidx.compose.ui.layout.ParentDataModifier
    public final Object modifyParentData() {
        return this;
    }
}
