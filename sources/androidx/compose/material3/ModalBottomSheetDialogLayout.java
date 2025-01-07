package androidx.compose.material3;

import android.content.Context;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.compose.animation.core.Animatable;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AbstractComposeView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ModalBottomSheetDialogLayout extends AbstractComposeView {
    public ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 backCallback;
    public final MutableState content$delegate;
    public final Function0 onDismissRequest;
    public final Animatable predictiveBackProgress;
    public final ContextScope scope;
    public boolean shouldCreateCompositionOnAttachedToWindow;
    public final boolean shouldDismissOnBackPress;

    public ModalBottomSheetDialogLayout(Context context, Function0 function0, Animatable animatable, ContextScope contextScope) {
        super(context);
        this.shouldDismissOnBackPress = true;
        this.onDismissRequest = function0;
        this.predictiveBackProgress = animatable;
        this.scope = contextScope;
        this.content$delegate = SnapshotStateKt.mutableStateOf$default(ComposableSingletons$ModalBottomSheet_androidKt.f7lambda2);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final void Content(final int i, Composer composer) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(576708319);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ((Function2) ((SnapshotMutableStateImpl) this.content$delegate).getValue()).invoke(composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.ModalBottomSheetDialogLayout$Content$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ModalBottomSheetDialogLayout.this.Content(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.compose.material3.ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1] */
    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        OnBackInvokedDispatcher findOnBackInvokedDispatcher;
        super.onAttachedToWindow();
        if (this.shouldDismissOnBackPress) {
            if (this.backCallback == null) {
                final Function0 function0 = this.onDismissRequest;
                final Animatable animatable = this.predictiveBackProgress;
                final ContextScope contextScope = this.scope;
                this.backCallback = new OnBackAnimationCallback() { // from class: androidx.compose.material3.ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1
                    @Override // android.window.OnBackAnimationCallback
                    public final void onBackCancelled() {
                        BuildersKt.launch$default(ContextScope.this, null, null, new ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1$onBackCancelled$1(animatable, null), 3);
                    }

                    @Override // android.window.OnBackInvokedCallback
                    public final void onBackInvoked() {
                        function0.invoke();
                    }

                    @Override // android.window.OnBackAnimationCallback
                    public final void onBackProgressed(BackEvent backEvent) {
                        BuildersKt.launch$default(ContextScope.this, null, null, new ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1$onBackProgressed$1(animatable, backEvent, null), 3);
                    }

                    @Override // android.window.OnBackAnimationCallback
                    public final void onBackStarted(BackEvent backEvent) {
                        BuildersKt.launch$default(ContextScope.this, null, null, new ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1$onBackStarted$1(animatable, backEvent, null), 3);
                    }
                };
            }
            ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 modalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 = this.backCallback;
            if (modalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 == null || (findOnBackInvokedDispatcher = findOnBackInvokedDispatcher()) == null) {
                return;
            }
            findOnBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, modalBottomSheetDialogLayout$Api34Impl$createBackCallback$1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        OnBackInvokedDispatcher findOnBackInvokedDispatcher;
        super.onDetachedFromWindow();
        ModalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 modalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 = this.backCallback;
        if (modalBottomSheetDialogLayout$Api34Impl$createBackCallback$1 != null && (findOnBackInvokedDispatcher = findOnBackInvokedDispatcher()) != null) {
            findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(modalBottomSheetDialogLayout$Api34Impl$createBackCallback$1);
        }
        this.backCallback = null;
    }
}
