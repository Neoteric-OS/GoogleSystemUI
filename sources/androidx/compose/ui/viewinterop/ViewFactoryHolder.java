package androidx.compose.ui.viewinterop;

import android.view.View;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewFactoryHolder extends AndroidViewHolder {
    public Function1 releaseBlock;
    public Function1 resetBlock;
    public SaveableStateRegistry.Entry savableRegistryEntry;
    public final View typedView;
    public Function1 updateBlock;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ViewFactoryHolder(android.content.Context r8, kotlin.jvm.functions.Function1 r9, androidx.compose.runtime.CompositionContext r10, androidx.compose.runtime.saveable.SaveableStateRegistry r11, int r12, androidx.compose.ui.node.Owner r13) {
        /*
            r7 = this;
            java.lang.Object r9 = r9.invoke(r8)
            android.view.View r9 = (android.view.View) r9
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r4 = new androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
            r4.<init>()
            r0 = r7
            r1 = r8
            r2 = r10
            r3 = r12
            r5 = r9
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7.typedView = r9
            r8 = 0
            r7.setClipChildren(r8)
            java.lang.String r8 = java.lang.String.valueOf(r12)
            r10 = 0
            if (r11 == 0) goto L26
            java.lang.Object r12 = r11.consumeRestored(r8)
            goto L27
        L26:
            r12 = r10
        L27:
            boolean r13 = r12 instanceof android.util.SparseArray
            if (r13 == 0) goto L2e
            r10 = r12
            android.util.SparseArray r10 = (android.util.SparseArray) r10
        L2e:
            if (r10 == 0) goto L33
            r9.restoreHierarchyState(r10)
        L33:
            if (r11 == 0) goto L49
            androidx.compose.ui.viewinterop.ViewFactoryHolder$registerSaveStateProvider$1 r9 = new androidx.compose.ui.viewinterop.ViewFactoryHolder$registerSaveStateProvider$1
            r9.<init>()
            androidx.compose.runtime.saveable.SaveableStateRegistry$Entry r8 = r11.registerProvider(r8, r9)
            androidx.compose.runtime.saveable.SaveableStateRegistry$Entry r9 = r7.savableRegistryEntry
            if (r9 == 0) goto L47
            androidx.compose.runtime.saveable.SaveableStateRegistryImpl$registerProvider$3 r9 = (androidx.compose.runtime.saveable.SaveableStateRegistryImpl$registerProvider$3) r9
            r9.unregister()
        L47:
            r7.savableRegistryEntry = r8
        L49:
            kotlin.jvm.functions.Function1 r8 = androidx.compose.ui.viewinterop.AndroidView_androidKt.NoOpUpdate
            r7.updateBlock = r8
            r7.resetBlock = r8
            r7.releaseBlock = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.ViewFactoryHolder.<init>(android.content.Context, kotlin.jvm.functions.Function1, androidx.compose.runtime.CompositionContext, androidx.compose.runtime.saveable.SaveableStateRegistry, int, androidx.compose.ui.node.Owner):void");
    }
}
