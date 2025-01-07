package androidx.compose.ui.focus;

import kotlin.jvm.internal.PropertyReference0Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FocusOwnerImpl$focusInvalidationManager$2 extends PropertyReference0Impl {
    @Override // kotlin.reflect.KProperty0
    public final Object get() {
        return ((FocusOwnerImpl) this.receiver).rootFocusNode.getFocusState();
    }
}
