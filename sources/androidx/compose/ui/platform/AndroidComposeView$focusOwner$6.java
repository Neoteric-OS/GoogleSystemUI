package androidx.compose.ui.platform;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class AndroidComposeView$focusOwner$6 extends MutablePropertyReference0Impl {
    @Override // kotlin.reflect.KProperty0
    public final Object get() {
        return (LayoutDirection) ((SnapshotMutableStateImpl) ((AndroidComposeView) this.receiver).layoutDirection$delegate).getValue();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public final void set(Object obj) {
        AndroidComposeView androidComposeView = (AndroidComposeView) this.receiver;
        AndroidComposeView.Companion companion = AndroidComposeView.Companion;
        ((SnapshotMutableStateImpl) androidComposeView.layoutDirection$delegate).setValue((LayoutDirection) obj);
    }
}
