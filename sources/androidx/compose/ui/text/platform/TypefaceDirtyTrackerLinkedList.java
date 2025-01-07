package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.font.TypefaceResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TypefaceDirtyTrackerLinkedList {
    public final Object initial;
    public final TypefaceDirtyTrackerLinkedList next;
    public final TypefaceResult resolveResult;

    public TypefaceDirtyTrackerLinkedList(TypefaceResult typefaceResult, TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList) {
        this.resolveResult = typefaceResult;
        this.next = typefaceDirtyTrackerLinkedList;
        this.initial = typefaceResult.getValue();
    }

    public final boolean isStaleResolvedFont() {
        TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList;
        return this.resolveResult.getValue() != this.initial || ((typefaceDirtyTrackerLinkedList = this.next) != null && typefaceDirtyTrackerLinkedList.isStaleResolvedFont());
    }
}
