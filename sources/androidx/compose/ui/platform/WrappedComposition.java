package androidx.compose.ui.platform;

import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionImpl;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WrappedComposition implements Composition, LifecycleEventObserver {
    public Lifecycle addedToLifecycle;
    public boolean disposed;
    public Function2 lastContent = ComposableSingletons$Wrapper_androidKt.f12lambda1;
    public final CompositionImpl original;
    public final AndroidComposeView owner;

    public WrappedComposition(AndroidComposeView androidComposeView, CompositionImpl compositionImpl) {
        this.owner = androidComposeView;
        this.original = compositionImpl;
    }

    @Override // androidx.compose.runtime.Composition
    public final void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.owner.setTag(R.id.wrapped_composition_tag, null);
            Lifecycle lifecycle = this.addedToLifecycle;
            if (lifecycle != null) {
                lifecycle.removeObserver(this);
            }
        }
        this.original.dispose();
    }

    @Override // androidx.compose.runtime.Composition
    public final boolean isDisposed() {
        return this.original.disposed;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            dispose();
        } else {
            if (event != Lifecycle.Event.ON_CREATE || this.disposed) {
                return;
            }
            setContent(this.lastContent);
        }
    }

    @Override // androidx.compose.runtime.Composition
    public final void setContent(Function2 function2) {
        WrappedComposition$setContent$1 wrappedComposition$setContent$1 = new WrappedComposition$setContent$1(this, function2);
        AndroidComposeView androidComposeView = this.owner;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = androidComposeView.getViewTreeOwners();
        if (viewTreeOwners != null) {
            wrappedComposition$setContent$1.invoke(viewTreeOwners);
        }
        if (androidComposeView.isAttachedToWindow()) {
            return;
        }
        androidComposeView.onViewTreeOwnersAvailable = wrappedComposition$setContent$1;
    }
}
