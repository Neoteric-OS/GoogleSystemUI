package androidx.compose.foundation.text.selection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SelectionAdjustment$Companion$Word$1$1 implements BoundaryFunction {
    public static final SelectionAdjustment$Companion$Word$1$1 INSTANCE = new SelectionAdjustment$Companion$Word$1$1();

    @Override // androidx.compose.foundation.text.selection.BoundaryFunction
    /* renamed from: getBoundary-fzxv0v0 */
    public final long mo182getBoundaryfzxv0v0(SelectableInfo selectableInfo, int i) {
        return selectableInfo.textLayoutResult.m596getWordBoundaryjx7JFs(i);
    }
}
