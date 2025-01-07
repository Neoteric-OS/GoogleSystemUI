package androidx.compose.material3;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.activity.ComponentDialog;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.compose.animation.core.Animatable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import com.android.wm.shell.R;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ModalBottomSheetDialogWrapper extends ComponentDialog {
    public final View composeView;
    public final ModalBottomSheetDialogLayout dialogLayout;
    public Function0 onDismissRequest;
    public ModalBottomSheetProperties properties;

    public ModalBottomSheetDialogWrapper(Function0 function0, ModalBottomSheetProperties modalBottomSheetProperties, View view, LayoutDirection layoutDirection, Density density, UUID uuid, Animatable animatable, ContextScope contextScope, boolean z) {
        super(0, new ContextThemeWrapper(view.getContext(), R.style.EdgeToEdgeFloatingDialogWindowTheme));
        this.onDismissRequest = function0;
        this.properties = modalBottomSheetProperties;
        this.composeView = view;
        float f = 8;
        Window window = getWindow();
        if (window == null) {
            throw new IllegalStateException("Dialog has no window");
        }
        window.requestFeature(1);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setDecorFitsSystemWindows(false);
        Context context = getContext();
        this.properties.getClass();
        ModalBottomSheetDialogLayout modalBottomSheetDialogLayout = new ModalBottomSheetDialogLayout(context, this.onDismissRequest, animatable, contextScope);
        modalBottomSheetDialogLayout.setTag(R.id.compose_view_saveable_id_tag, "Dialog:" + uuid);
        modalBottomSheetDialogLayout.setClipChildren(false);
        modalBottomSheetDialogLayout.setElevation(density.mo51toPx0680j_4(f));
        modalBottomSheetDialogLayout.setOutlineProvider(new ModalBottomSheetDialogWrapper$1$2());
        this.dialogLayout = modalBottomSheetDialogLayout;
        setContentView(modalBottomSheetDialogLayout);
        modalBottomSheetDialogLayout.setTag(R.id.view_tree_lifecycle_owner, ViewTreeLifecycleOwner.get(view));
        modalBottomSheetDialogLayout.setTag(R.id.view_tree_view_model_store_owner, ViewTreeViewModelStoreOwner.get(view));
        modalBottomSheetDialogLayout.setTag(R.id.view_tree_saved_state_registry_owner, ViewTreeSavedStateRegistryOwner.get(view));
        updateParameters(this.onDismissRequest, this.properties, layoutDirection);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, window.getDecorView());
        boolean z2 = !z;
        windowInsetsControllerCompat.setAppearanceLightStatusBars(z2);
        windowInsetsControllerCompat.setAppearanceLightNavigationBars(z2);
        OnBackPressedDispatcher onBackPressedDispatcher = this.onBackPressedDispatcher;
        final Function1 function1 = new Function1() { // from class: androidx.compose.material3.ModalBottomSheetDialogWrapper.3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper = ModalBottomSheetDialogWrapper.this;
                modalBottomSheetDialogWrapper.properties.getClass();
                modalBottomSheetDialogWrapper.onDismissRequest.invoke();
                return Unit.INSTANCE;
            }
        };
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback() { // from class: androidx.activity.OnBackPressedDispatcherKt$addCallback$callback$1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackPressed() {
                Function1.this.invoke(this);
            }
        });
    }

    @Override // android.app.Dialog
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (onTouchEvent) {
            this.onDismissRequest.invoke();
        }
        return onTouchEvent;
    }

    public final void updateParameters(Function0 function0, ModalBottomSheetProperties modalBottomSheetProperties, LayoutDirection layoutDirection) {
        this.onDismissRequest = function0;
        this.properties = modalBottomSheetProperties;
        modalBottomSheetProperties.getClass();
        ViewGroup.LayoutParams layoutParams = this.composeView.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        int i = 0;
        boolean z = (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.setFlags(z ? 8192 : -8193, 8192);
        ModalBottomSheetDialogLayout modalBottomSheetDialogLayout = this.dialogLayout;
        int ordinal = layoutDirection.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            i = 1;
        }
        modalBottomSheetDialogLayout.setLayoutDirection(i);
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -1);
        }
        Window window3 = getWindow();
        if (window3 != null) {
            window3.setSoftInputMode(48);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void cancel() {
    }
}
