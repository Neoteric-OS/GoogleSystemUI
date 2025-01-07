package androidx.compose.ui.window;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.Snapshot$Companion$$ExternalSyntheticLambda0;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import com.android.wm.shell.R;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$LongRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PopupLayout extends AbstractComposeView {
    public static final Function1 onCommitAffectingPopupPosition = new Function1() { // from class: androidx.compose.ui.window.PopupLayout$Companion$onCommitAffectingPopupPosition$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            PopupLayout popupLayout = (PopupLayout) obj;
            if (popupLayout.isAttachedToWindow()) {
                popupLayout.updatePosition();
            }
            return Unit.INSTANCE;
        }
    };
    public Api33Impl$$ExternalSyntheticLambda0 backCallback;
    public final State canCalculatePosition$delegate;
    public final View composeView;
    public final MutableState content$delegate;
    public final int[] locationOnScreen;
    public Function0 onDismissRequest;
    public final WindowManager.LayoutParams params;
    public IntRect parentBounds;
    public final MutableState parentLayoutCoordinates$delegate;
    public LayoutDirection parentLayoutDirection;
    public final MutableState popupContentSize$delegate;
    public final PopupLayoutHelper popupLayoutHelper;
    public PopupPositionProvider positionProvider;
    public final Rect previousWindowVisibleFrame;
    public PopupProperties properties;
    public boolean shouldCreateCompositionOnAttachedToWindow;
    public final SnapshotStateObserver snapshotStateObserver;
    public final WindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.compose.ui.window.PopupLayout$2, reason: invalid class name */
    public final class AnonymousClass2 extends ViewOutlineProvider {
        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
            outline.setAlpha(0.0f);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PopupLayout(Function0 function0, PopupProperties popupProperties, View view, Density density, PopupPositionProvider popupPositionProvider, UUID uuid) {
        super(view.getContext());
        PopupLayoutHelperImpl29 popupLayoutHelperImpl29 = new PopupLayoutHelperImpl29();
        this.onDismissRequest = function0;
        this.properties = popupProperties;
        this.composeView = view;
        this.popupLayoutHelper = popupLayoutHelperImpl29;
        this.windowManager = (WindowManager) view.getContext().getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        PopupProperties popupProperties2 = this.properties;
        boolean isFlagSecureEnabled = AndroidPopup_androidKt.isFlagSecureEnabled(view);
        boolean z = popupProperties2.inheritSecurePolicy;
        int i = popupProperties2.flags;
        if (z && isFlagSecureEnabled) {
            i |= 8192;
        } else if (z && !isFlagSecureEnabled) {
            i &= -8193;
        }
        layoutParams.flags = i;
        layoutParams.type = 1002;
        layoutParams.token = view.getApplicationWindowToken();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.setTitle(view.getContext().getResources().getString(R.string.default_popup_window_title));
        this.params = layoutParams;
        this.positionProvider = popupPositionProvider;
        this.parentLayoutDirection = LayoutDirection.Ltr;
        this.popupContentSize$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.parentLayoutCoordinates$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.canCalculatePosition$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.ui.window.PopupLayout$canCalculatePosition$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                PopupLayout popupLayout = PopupLayout.this;
                Function1 function1 = PopupLayout.onCommitAffectingPopupPosition;
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) ((SnapshotMutableStateImpl) popupLayout.parentLayoutCoordinates$delegate).getValue();
                if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
                    layoutCoordinates = null;
                }
                return Boolean.valueOf((layoutCoordinates == null || ((IntSize) ((SnapshotMutableStateImpl) PopupLayout.this.popupContentSize$delegate).getValue()) == null) ? false : true);
            }
        });
        this.previousWindowVisibleFrame = new Rect();
        this.snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: androidx.compose.ui.window.PopupLayout$snapshotStateObserver$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final Function0 function02 = (Function0) obj;
                Handler handler = PopupLayout.this.getHandler();
                if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                    function02.invoke();
                } else {
                    Handler handler2 = PopupLayout.this.getHandler();
                    if (handler2 != null) {
                        handler2.post(new Runnable() { // from class: androidx.compose.ui.window.PopupLayout$snapshotStateObserver$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                Function0.this.invoke();
                            }
                        });
                    }
                }
                return Unit.INSTANCE;
            }
        });
        setId(android.R.id.content);
        setTag(R.id.view_tree_lifecycle_owner, ViewTreeLifecycleOwner.get(view));
        setTag(R.id.view_tree_view_model_store_owner, ViewTreeViewModelStoreOwner.get(view));
        setTag(R.id.view_tree_saved_state_registry_owner, ViewTreeSavedStateRegistryOwner.get(view));
        setTag(R.id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        setElevation(density.mo51toPx0680j_4((float) 8));
        setOutlineProvider(new AnonymousClass2());
        this.content$delegate = SnapshotStateKt.mutableStateOf$default(ComposableSingletons$AndroidPopup_androidKt.f13lambda1);
        this.locationOnScreen = new int[2];
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final void Content(final int i, Composer composer) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-857613600);
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
            endRestartGroup.block = new Function2() { // from class: androidx.compose.ui.window.PopupLayout$Content$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PopupLayout.this.Content(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.properties.dismissOnBackPress) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getKeyCode() == 4 || keyEvent.getKeyCode() == 111) {
            KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
            if (keyDispatcherState == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyDispatcherState.startTracking(keyEvent, this);
                return true;
            }
            if (keyEvent.getAction() == 1 && keyDispatcherState.isTracking(keyEvent) && !keyEvent.isCanceled()) {
                Function0 function0 = this.onDismissRequest;
                if (function0 != null) {
                    function0.invoke();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final void internalOnLayout$ui_release(boolean z, int i, int i2, int i3, int i4) {
        super.internalOnLayout$ui_release(z, i, i2, i3, i4);
        this.properties.getClass();
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        this.params.width = childAt.getMeasuredWidth();
        this.params.height = childAt.getMeasuredHeight();
        PopupLayoutHelper popupLayoutHelper = this.popupLayoutHelper;
        WindowManager windowManager = this.windowManager;
        WindowManager.LayoutParams layoutParams = this.params;
        ((PopupLayoutHelperImpl) popupLayoutHelper).getClass();
        windowManager.updateViewLayout(this, layoutParams);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final void internalOnMeasure$ui_release(int i, int i2) {
        this.properties.getClass();
        super.internalOnMeasure$ui_release(View.MeasureSpec.makeMeasureSpec(Math.round(getContext().getResources().getConfiguration().screenWidthDp * getContext().getResources().getDisplayMetrics().density), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(Math.round(getContext().getResources().getConfiguration().screenHeightDp * getContext().getResources().getDisplayMetrics().density), Integer.MIN_VALUE));
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0] */
    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        OnBackInvokedDispatcher findOnBackInvokedDispatcher;
        super.onAttachedToWindow();
        SnapshotStateObserver snapshotStateObserver = this.snapshotStateObserver;
        snapshotStateObserver.applyUnsubscribe = Snapshot.Companion.registerApplyObserver(snapshotStateObserver.applyObserver);
        if (this.properties.dismissOnBackPress) {
            if (this.backCallback == null) {
                final Function0 function0 = this.onDismissRequest;
                this.backCallback = new OnBackInvokedCallback() { // from class: androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0
                    @Override // android.window.OnBackInvokedCallback
                    public final void onBackInvoked() {
                        Function0 function02 = Function0.this;
                        if (function02 != null) {
                            function02.invoke();
                        }
                    }
                };
            }
            Api33Impl$$ExternalSyntheticLambda0 api33Impl$$ExternalSyntheticLambda0 = this.backCallback;
            if (api33Impl$$ExternalSyntheticLambda0 == null || (findOnBackInvokedDispatcher = findOnBackInvokedDispatcher()) == null) {
                return;
            }
            findOnBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, api33Impl$$ExternalSyntheticLambda0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        OnBackInvokedDispatcher findOnBackInvokedDispatcher;
        super.onDetachedFromWindow();
        Snapshot$Companion$$ExternalSyntheticLambda0 snapshot$Companion$$ExternalSyntheticLambda0 = this.snapshotStateObserver.applyUnsubscribe;
        if (snapshot$Companion$$ExternalSyntheticLambda0 != null) {
            snapshot$Companion$$ExternalSyntheticLambda0.dispose();
        }
        this.snapshotStateObserver.clear();
        Api33Impl$$ExternalSyntheticLambda0 api33Impl$$ExternalSyntheticLambda0 = this.backCallback;
        if (api33Impl$$ExternalSyntheticLambda0 != null && (findOnBackInvokedDispatcher = findOnBackInvokedDispatcher()) != null) {
            findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(api33Impl$$ExternalSyntheticLambda0);
        }
        this.backCallback = null;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.properties.dismissOnClickOutside) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent != null && motionEvent.getAction() == 0 && (motionEvent.getX() < 0.0f || motionEvent.getX() >= getWidth() || motionEvent.getY() < 0.0f || motionEvent.getY() >= getHeight())) {
            Function0 function0 = this.onDismissRequest;
            if (function0 != null) {
                function0.invoke();
            }
            return true;
        }
        if (motionEvent == null || motionEvent.getAction() != 4) {
            return super.onTouchEvent(motionEvent);
        }
        Function0 function02 = this.onDismissRequest;
        if (function02 != null) {
            function02.invoke();
        }
        return true;
    }

    public final void updateParameters(Function0 function0, PopupProperties popupProperties, LayoutDirection layoutDirection) {
        int i;
        this.onDismissRequest = function0;
        if (!Intrinsics.areEqual(this.properties, popupProperties)) {
            popupProperties.getClass();
            this.properties = popupProperties;
            WindowManager.LayoutParams layoutParams = this.params;
            boolean isFlagSecureEnabled = AndroidPopup_androidKt.isFlagSecureEnabled(this.composeView);
            boolean z = popupProperties.inheritSecurePolicy;
            int i2 = popupProperties.flags;
            if (z && isFlagSecureEnabled) {
                i2 |= 8192;
            } else if (z && !isFlagSecureEnabled) {
                i2 &= -8193;
            }
            layoutParams.flags = i2;
            PopupLayoutHelper popupLayoutHelper = this.popupLayoutHelper;
            WindowManager windowManager = this.windowManager;
            WindowManager.LayoutParams layoutParams2 = this.params;
            ((PopupLayoutHelperImpl) popupLayoutHelper).getClass();
            windowManager.updateViewLayout(this, layoutParams2);
        }
        int ordinal = layoutDirection.ordinal();
        if (ordinal != 0) {
            i = 1;
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            i = 0;
        }
        super.setLayoutDirection(i);
    }

    public final void updateParentBounds$ui_release() {
        LayoutCoordinates layoutCoordinates = (LayoutCoordinates) ((SnapshotMutableStateImpl) this.parentLayoutCoordinates$delegate).getValue();
        if (layoutCoordinates != null) {
            if (!layoutCoordinates.isAttached()) {
                layoutCoordinates = null;
            }
            if (layoutCoordinates == null) {
                return;
            }
            long mo481getSizeYbymL2g = layoutCoordinates.mo481getSizeYbymL2g();
            long mo486localToWindowMKHz9U = layoutCoordinates.mo486localToWindowMKHz9U(0L);
            IntRect m681IntRectVbeCjmY = IntRectKt.m681IntRectVbeCjmY((Math.round(Float.intBitsToFloat((int) (mo486localToWindowMKHz9U >> 32))) << 32) | (4294967295L & Math.round(Float.intBitsToFloat((int) (mo486localToWindowMKHz9U & 4294967295L)))), mo481getSizeYbymL2g);
            if (m681IntRectVbeCjmY.equals(this.parentBounds)) {
                return;
            }
            this.parentBounds = m681IntRectVbeCjmY;
            updatePosition();
        }
    }

    public final void updatePosition() {
        IntSize intSize;
        final IntRect intRect = this.parentBounds;
        if (intRect == null || (intSize = (IntSize) ((SnapshotMutableStateImpl) this.popupContentSize$delegate).getValue()) == null) {
            return;
        }
        Rect rect = this.previousWindowVisibleFrame;
        PopupLayoutHelper popupLayoutHelper = this.popupLayoutHelper;
        View view = this.composeView;
        ((PopupLayoutHelperImpl) popupLayoutHelper).getClass();
        view.getWindowVisibleDisplayFrame(rect);
        DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = AndroidPopup_androidKt.LocalPopupTestTag;
        IntRect intRect2 = new IntRect(rect.left, rect.top, rect.right, rect.bottom);
        final long width = (intRect2.getWidth() << 32) | (intRect2.getHeight() & 4294967295L);
        final Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = 0L;
        SnapshotStateObserver snapshotStateObserver = this.snapshotStateObserver;
        Function1 function1 = onCommitAffectingPopupPosition;
        final long j = intSize.packedValue;
        snapshotStateObserver.observeReads(this, function1, new Function0() { // from class: androidx.compose.ui.window.PopupLayout$updatePosition$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Ref$LongRef ref$LongRef2 = Ref$LongRef.this;
                PopupLayout popupLayout = this;
                ref$LongRef2.element = popupLayout.positionProvider.mo42calculatePositionllwVHH4(intRect, width, popupLayout.parentLayoutDirection, j);
                return Unit.INSTANCE;
            }
        });
        WindowManager.LayoutParams layoutParams = this.params;
        long j2 = ref$LongRef.element;
        layoutParams.x = (int) (j2 >> 32);
        layoutParams.y = (int) (j2 & 4294967295L);
        if (this.properties.excludeFromSystemGesture) {
            ((PopupLayoutHelperImpl29) this.popupLayoutHelper).getClass();
            setSystemGestureExclusionRects(CollectionsKt__CollectionsKt.mutableListOf(new Rect(0, 0, (int) (width >> 32), (int) (4294967295L & width))));
        }
        PopupLayoutHelper popupLayoutHelper2 = this.popupLayoutHelper;
        WindowManager windowManager = this.windowManager;
        WindowManager.LayoutParams layoutParams2 = this.params;
        ((PopupLayoutHelperImpl) popupLayoutHelper2).getClass();
        windowManager.updateViewLayout(this, layoutParams2);
    }

    @Override // android.view.View
    public final void setLayoutDirection(int i) {
    }
}
