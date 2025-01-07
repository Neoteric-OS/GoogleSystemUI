package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.translation.TranslationRequestValue;
import android.view.translation.ViewTranslationRequest;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.Snapshot$Companion$$ExternalSyntheticLambda0;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AndroidSemanticAutofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.draganddrop.AndroidDragAndDropManager;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTransactionManager;
import androidx.compose.ui.focus.FocusTransactionsKt;
import androidx.compose.ui.focus.FocusTraversalKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidGraphicsContext_androidKt;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.pointer.MatrixPositionCalculator;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.rotary.RotaryInputModifierKt;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DepthSortedSetsForDifferentPasses;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadAlignmentLines;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.scrollcapture.ScrollCapture;
import androidx.compose.ui.semantics.EmptySemanticsElement;
import androidx.compose.ui.semantics.EmptySemanticsModifier;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidComposeView extends ViewGroup implements Owner, MatrixPositionCalculator, DefaultLifecycleObserver {
    public static final Companion Companion = null;
    public static Method getBooleanMethod;
    public static Class systemPropertiesClass;
    public AndroidViewsHandler _androidViewsHandler;
    public final AndroidAutofill _autofill;
    public final InputModeManagerImpl _inputModeManager;
    public final MutableState _viewTreeOwners$delegate;
    public final WindowInfoImpl _windowInfo;
    public final AndroidAccessibilityManager accessibilityManager;
    public final AutofillTree autofillTree;
    public final CanvasHolder canvasHolder;
    public final AndroidClipboardManager clipboardManager;
    public final AndroidComposeViewAccessibilityDelegateCompat composeAccessibilityDelegate;
    public Function1 configurationChangeObserver;
    public final AndroidContentCaptureManager contentCaptureManager;
    public CoroutineContext coroutineContext;
    public int currentFontWeightAdjustment;
    public final MutableState density$delegate;
    public final List dirtyLayers;
    public final AndroidDragAndDropManager dragAndDropManager;
    public final MutableVector endApplyChangesListeners;
    public final FocusOwnerImpl focusOwner;
    public final MutableState fontFamilyResolver$delegate;
    public final AndroidFontResourceLoader fontLoader;
    public boolean forceUseMatrixCache;
    public final AndroidComposeView$$ExternalSyntheticLambda0 globalLayoutListener;
    public long globalPosition;
    public final GraphicsContext graphicsContext;
    public final PlatformHapticFeedback hapticFeedBack;
    public boolean hoverExitReceived;
    public boolean isDrawingContent;
    public boolean isPendingInteropViewLayoutChangeDispatch;
    public boolean isRenderNodeCompatible;
    public boolean keyboardModifiersRequireUpdate;
    public long lastDownPointerPosition;
    public long lastMatrixRecalculationAnimationTime;
    public final WeakCache layerCache;
    public final MutableState layoutDirection$delegate;
    public final TextInputServiceAndroid legacyTextInputServiceAndroid;
    public final CalculateMatrixToWindowApi29 matrixToWindow;
    public final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    public final ModifierLocalManager modifierLocalManager;
    public final MotionEventAdapter motionEventAdapter;
    public boolean observationClearRequested;
    public Constraints onMeasureConstraints;
    public Function1 onViewTreeOwnersAvailable;
    public final AndroidComposeView$pointerIconService$1 pointerIconService;
    public final PointerInputEventProcessor pointerInputEventProcessor;
    public List postponedDirtyLayers;
    public MotionEvent previousMotionEvent;
    public final RectManager rectManager;
    public long relayoutTime;
    public final Function0 resendMotionEventOnLayout;
    public final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    public final LayoutNode root;
    public final ScrollCapture scrollCapture;
    public final AndroidComposeView$$ExternalSyntheticLambda1 scrollChangedListener;
    public final SemanticsOwner semanticsOwner;
    public final AndroidComposeView$$ExternalSyntheticLambda3 sendHoverExitEvent;
    public final LayoutNodeDrawScope sharedDrawScope;
    public boolean showLayoutBounds;
    public final OwnerSnapshotObserver snapshotObserver;
    public final DelegatingSoftwareKeyboardController softwareKeyboardController;
    public final boolean superclassInitComplete;
    public final TextInputService textInputService;
    public final AtomicReference textInputSessionMutex;
    public final AndroidTextToolbar textToolbar;
    public final float[] tmpMatrix;
    public final int[] tmpPositionArray;
    public final AndroidComposeView$$ExternalSyntheticLambda2 touchModeChangeListener;
    public final AndroidViewConfiguration viewConfiguration;
    public DrawChildContainer viewLayersContainer;
    public final float[] viewToWindowMatrix;
    public final State viewTreeOwners$delegate;
    public boolean wasMeasuredWithMultipleConstraints;
    public long windowPosition;
    public final float[] windowToViewMatrix;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final boolean access$getIsShowingLayoutBounds() {
            Companion companion = AndroidComposeView.Companion;
            try {
                if (AndroidComposeView.systemPropertiesClass == null) {
                    Class<?> cls = Class.forName("android.os.SystemProperties");
                    AndroidComposeView.systemPropertiesClass = cls;
                    AndroidComposeView.getBooleanMethod = cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
                }
                Method method = AndroidComposeView.getBooleanMethod;
                Object invoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
                Boolean bool = invoke instanceof Boolean ? (Boolean) invoke : null;
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewTreeOwners {
        public final LifecycleOwner lifecycleOwner;
        public final SavedStateRegistryOwner savedStateRegistryOwner;

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner) {
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
        }
    }

    /* JADX WARN: Type inference failed for: r1v41, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    /* JADX WARN: Type inference failed for: r2v16, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r2v17, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r2v18, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2] */
    public AndroidComposeView(Context context, CoroutineContext coroutineContext) {
        super(context);
        this.lastDownPointerPosition = 9205357640488583168L;
        this.superclassInitComplete = true;
        this.sharedDrawScope = new LayoutNodeDrawScope();
        MutableState mutableStateOf = SnapshotStateKt.mutableStateOf(AndroidDensity_androidKt.Density(context), SnapshotStateKt.referentialEqualityPolicy());
        this.density$delegate = mutableStateOf;
        EmptySemanticsModifier emptySemanticsModifier = new EmptySemanticsModifier();
        EmptySemanticsElement emptySemanticsElement = new EmptySemanticsElement(emptySemanticsModifier);
        FocusOwnerImpl focusOwnerImpl = new FocusOwnerImpl(new AndroidComposeView$focusOwner$1(1, this, AndroidComposeView.class, "registerOnEndApplyChangesListener", "registerOnEndApplyChangesListener(Lkotlin/jvm/functions/Function0;)V", 0), new AndroidComposeView$focusOwner$2(2, this, AndroidComposeView.class, "onRequestFocusForOwner", "onRequestFocusForOwner-7o62pno(Landroidx/compose/ui/focus/FocusDirection;Landroidx/compose/ui/geometry/Rect;)Z", 0), new AndroidComposeView$focusOwner$3(1, this, AndroidComposeView.class, "onMoveFocusInChildren", "onMoveFocusInChildren-3ESFkO8(I)Z", 0), new AndroidComposeView$focusOwner$4(0, this, AndroidComposeView.class, "onClearFocusForOwner", "onClearFocusForOwner()V", 0), new AndroidComposeView$focusOwner$5(0, this, AndroidComposeView.class, "onFetchFocusRect", "onFetchFocusRect()Landroidx/compose/ui/geometry/Rect;", 0), new AndroidComposeView$focusOwner$6(this, AndroidComposeView.class, "layoutDirection", "getLayoutDirection()Landroidx/compose/ui/unit/LayoutDirection;", 0));
        this.focusOwner = focusOwnerImpl;
        this.coroutineContext = coroutineContext;
        AndroidDragAndDropManager androidDragAndDropManager = new AndroidDragAndDropManager(new AndroidComposeView$dragAndDropManager$1(3, this, AndroidComposeView.class, "startDrag", "startDrag-12SF9DM(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLkotlin/jvm/functions/Function1;)Z", 0));
        this.dragAndDropManager = androidDragAndDropManager;
        this._windowInfo = new WindowInfoImpl();
        Modifier onKeyEvent = KeyInputModifierKt.onKeyEvent(Modifier.Companion.$$INSTANCE, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final FocusDirection focusDirection;
                KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj).nativeKeyEvent;
                AndroidComposeView.this.getClass();
                long m448getKeyZmokQxo = KeyEvent_androidKt.m448getKeyZmokQxo(keyEvent);
                if (Key.m446equalsimpl0(m448getKeyZmokQxo, Key.Tab)) {
                    focusDirection = new FocusDirection(keyEvent.isShiftPressed() ? 2 : 1);
                } else if (Key.m446equalsimpl0(m448getKeyZmokQxo, Key.DirectionRight)) {
                    focusDirection = new FocusDirection(4);
                } else if (Key.m446equalsimpl0(m448getKeyZmokQxo, Key.DirectionLeft)) {
                    focusDirection = new FocusDirection(3);
                } else {
                    focusDirection = Key.m446equalsimpl0(m448getKeyZmokQxo, Key.DirectionUp) ? true : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.PageUp) ? new FocusDirection(5) : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.DirectionDown) ? true : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.PageDown) ? new FocusDirection(6) : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.DirectionCenter) ? true : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.Enter) ? true : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.NumPadEnter) ? new FocusDirection(7) : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.Back) ? true : Key.m446equalsimpl0(m448getKeyZmokQxo, Key.Escape) ? new FocusDirection(8) : null;
                }
                if (focusDirection == null || !KeyEventType.m447equalsimpl0(KeyEvent_androidKt.m449getTypeZmokQxo(keyEvent), 2)) {
                    return Boolean.FALSE;
                }
                Rect onFetchFocusRect = AndroidComposeView.this.onFetchFocusRect();
                FocusOwnerImpl focusOwnerImpl2 = AndroidComposeView.this.focusOwner;
                Function1 function1 = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1$focusWasMovedOrCancelled$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        Boolean m296requestFocusMxy_nc0 = FocusTransactionsKt.m296requestFocusMxy_nc0((FocusTargetNode) obj2, FocusDirection.this.value);
                        return Boolean.valueOf(m296requestFocusMxy_nc0 != null ? m296requestFocusMxy_nc0.booleanValue() : true);
                    }
                };
                int i = focusDirection.value;
                Boolean m290focusSearchULY8qGw = focusOwnerImpl2.m290focusSearchULY8qGw(i, onFetchFocusRect, function1);
                if (m290focusSearchULY8qGw != null ? m290focusSearchULY8qGw.booleanValue() : true) {
                    return Boolean.TRUE;
                }
                if (!(FocusDirection.m284equalsimpl0(i, 1) ? true : FocusDirection.m284equalsimpl0(i, 2))) {
                    return Boolean.FALSE;
                }
                Integer m286toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m286toAndroidFocusDirection3ESFkO8(i);
                if (m286toAndroidFocusDirection3ESFkO8 == null) {
                    throw new IllegalStateException("Invalid focus direction");
                }
                int intValue = m286toAndroidFocusDirection3ESFkO8.intValue();
                android.graphics.Rect androidRect = onFetchFocusRect != null ? RectHelper_androidKt.toAndroidRect(onFetchFocusRect) : null;
                if (androidRect == null) {
                    throw new IllegalStateException("Invalid rect");
                }
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                androidComposeView.getClass();
                View view = androidComposeView;
                loop0: while (true) {
                    if (view == null) {
                        view = null;
                        break;
                    }
                    view = FocusFinder.getInstance().findNextFocus((ViewGroup) androidComposeView.getRootView(), view, intValue);
                    if (view != null) {
                        Function1 function12 = AndroidComposeView_androidKt.platformTextInputServiceInterceptor;
                        if (!view.equals(androidComposeView)) {
                            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                                if (parent == androidComposeView) {
                                    break;
                                }
                            }
                            break loop0;
                        }
                        break;
                    }
                }
                if (Intrinsics.areEqual(view, AndroidComposeView.this)) {
                    view = null;
                }
                if (view != null && FocusInteropUtils_androidKt.requestInteropFocus(view, Integer.valueOf(intValue), androidRect)) {
                    return Boolean.TRUE;
                }
                if (!AndroidComposeView.this.focusOwner.m288clearFocusI7lrPNg(i, false, false)) {
                    return Boolean.TRUE;
                }
                Boolean m290focusSearchULY8qGw2 = AndroidComposeView.this.focusOwner.m290focusSearchULY8qGw(i, null, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        Boolean m296requestFocusMxy_nc0 = FocusTransactionsKt.m296requestFocusMxy_nc0((FocusTargetNode) obj2, FocusDirection.this.value);
                        return Boolean.valueOf(m296requestFocusMxy_nc0 != null ? m296requestFocusMxy_nc0.booleanValue() : true);
                    }
                });
                return Boolean.valueOf(m290focusSearchULY8qGw2 != null ? m290focusSearchULY8qGw2.booleanValue() : true);
            }
        });
        Modifier onRotaryScrollEvent = RotaryInputModifierKt.onRotaryScrollEvent(new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$rotaryInputModifier$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.FALSE;
            }
        });
        this.canvasHolder = new CanvasHolder();
        AndroidViewConfiguration androidViewConfiguration = new AndroidViewConfiguration(android.view.ViewConfiguration.get(context));
        this.viewConfiguration = androidViewConfiguration;
        LayoutNode layoutNode = new LayoutNode(3);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity$1((Density) ((SnapshotMutableStateImpl) mutableStateOf).getValue());
        layoutNode.setViewConfiguration(androidViewConfiguration);
        layoutNode.setModifier(emptySemanticsElement.then(onRotaryScrollEvent).then(onKeyEvent).then(focusOwnerImpl.modifier).then(androidDragAndDropManager.modifier));
        this.root = layoutNode;
        this.semanticsOwner = new SemanticsOwner(layoutNode, emptySemanticsModifier);
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.composeAccessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        AndroidContentCaptureManager androidContentCaptureManager = new AndroidContentCaptureManager(this, new AndroidComposeView$contentCaptureManager$1(0, this, AndroidComposeView_androidKt.class, "getContentCaptureSessionCompat", "getContentCaptureSessionCompat(Landroid/view/View;)Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", 1));
        this.contentCaptureManager = androidContentCaptureManager;
        AndroidAccessibilityManager androidAccessibilityManager = new AndroidAccessibilityManager();
        this.accessibilityManager = androidAccessibilityManager;
        this.graphicsContext = AndroidGraphicsContext_androidKt.GraphicsContext(this);
        AutofillTree autofillTree = new AutofillTree();
        this.autofillTree = autofillTree;
        this.dirtyLayers = new ArrayList();
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(layoutNode);
        this.configurationChangeObserver = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$configurationChangeObserver$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        this._autofill = new AndroidAutofill(this, autofillTree);
        new AndroidSemanticAutofill(this);
        this.clipboardManager = new AndroidClipboardManager(context);
        this.snapshotObserver = new OwnerSnapshotObserver(new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$snapshotObserver$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function0 function0 = (Function0) obj;
                Handler handler = AndroidComposeView.this.getHandler();
                if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                    function0.invoke();
                } else {
                    Handler handler2 = AndroidComposeView.this.getHandler();
                    if (handler2 != null) {
                        handler2.post(new AndroidComposeView$$ExternalSyntheticLambda3(1, function0));
                    }
                }
                return Unit.INSTANCE;
            }
        });
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(layoutNode);
        long j = Integer.MAX_VALUE;
        this.globalPosition = (j & 4294967295L) | (j << 32);
        this.tmpPositionArray = new int[]{0, 0};
        this.tmpMatrix = Matrix.m379constructorimpl$default();
        this.viewToWindowMatrix = Matrix.m379constructorimpl$default();
        this.windowToViewMatrix = Matrix.m379constructorimpl$default();
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.windowPosition = 9187343241974906880L;
        this.isRenderNodeCompatible = true;
        LayoutDirection layoutDirection = null;
        this._viewTreeOwners$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.viewTreeOwners$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return (AndroidComposeView.ViewTreeOwners) ((SnapshotMutableStateImpl) AndroidComposeView.this._viewTreeOwners$delegate).getValue();
            }
        });
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                AndroidComposeView.Companion companion = AndroidComposeView.Companion;
                androidComposeView.updatePositionCacheAndDispatch();
            }
        };
        this.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                AndroidComposeView.Companion companion = AndroidComposeView.Companion;
                androidComposeView.updatePositionCacheAndDispatch();
            }
        };
        this.touchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z) {
                ((SnapshotMutableStateImpl) AndroidComposeView.this._inputModeManager.inputMode$delegate).setValue(new InputMode(z ? 1 : 2));
            }
        };
        TextInputServiceAndroid textInputServiceAndroid = new TextInputServiceAndroid(this, this);
        this.legacyTextInputServiceAndroid = textInputServiceAndroid;
        ((AndroidComposeView_androidKt$platformTextInputServiceInterceptor$1) AndroidComposeView_androidKt.platformTextInputServiceInterceptor).getClass();
        TextInputService textInputService = new TextInputService(textInputServiceAndroid);
        this.textInputService = textInputService;
        this.textInputSessionMutex = new AtomicReference(null);
        this.softwareKeyboardController = new DelegatingSoftwareKeyboardController(textInputService);
        this.fontLoader = new AndroidFontResourceLoader();
        this.fontFamilyResolver$delegate = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        this.currentFontWeightAdjustment = context.getResources().getConfiguration().fontWeightAdjustment;
        int layoutDirection2 = context.getResources().getConfiguration().getLayoutDirection();
        LayoutDirection layoutDirection3 = LayoutDirection.Ltr;
        if (layoutDirection2 == 0) {
            layoutDirection = layoutDirection3;
        } else if (layoutDirection2 == 1) {
            layoutDirection = LayoutDirection.Rtl;
        }
        this.layoutDirection$delegate = SnapshotStateKt.mutableStateOf$default(layoutDirection != null ? layoutDirection : layoutDirection3);
        this.hapticFeedBack = new PlatformHapticFeedback(this);
        this._inputModeManager = new InputModeManagerImpl(isInTouchMode() ? 1 : 2);
        this.modifierLocalManager = new ModifierLocalManager(this);
        this.textToolbar = new AndroidTextToolbar(this);
        this.layerCache = new WeakCache();
        this.endApplyChangesListeners = new MutableVector(new Function0[16]);
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.this.removeCallbacks(this);
                MotionEvent motionEvent = AndroidComposeView.this.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z = motionEvent.getToolType(0) == 3;
                    int actionMasked = motionEvent.getActionMasked();
                    if (z) {
                        if (actionMasked == 10 || actionMasked == 1) {
                            return;
                        }
                    } else if (actionMasked == 1) {
                        return;
                    }
                    int i = 7;
                    if (actionMasked != 7 && actionMasked != 9) {
                        i = 2;
                    }
                    AndroidComposeView androidComposeView = AndroidComposeView.this;
                    androidComposeView.sendSimulatedEvent(motionEvent, i, androidComposeView.relayoutTime, false);
                }
            }
        };
        this.sendHoverExitEvent = new AndroidComposeView$$ExternalSyntheticLambda3(0, this);
        this.resendMotionEventOnLayout = new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventOnLayout$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                int actionMasked;
                MotionEvent motionEvent = AndroidComposeView.this.previousMotionEvent;
                if (motionEvent != null && ((actionMasked = motionEvent.getActionMasked()) == 7 || actionMasked == 9)) {
                    AndroidComposeView.this.relayoutTime = SystemClock.uptimeMillis();
                    AndroidComposeView androidComposeView = AndroidComposeView.this;
                    androidComposeView.post(androidComposeView.resendMotionEventRunnable);
                }
                return Unit.INSTANCE;
            }
        };
        this.matrixToWindow = new CalculateMatrixToWindowApi29();
        addOnAttachStateChangeListener(androidContentCaptureManager);
        setWillNotDraw(false);
        setFocusable(true);
        setFocusable(1);
        setDefaultFocusHighlightEnabled(false);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(this, androidComposeViewAccessibilityDelegateCompat);
        setOnDragListener(androidDragAndDropManager);
        layoutNode.attach$ui_release(this);
        setForceDarkAllowed(false);
        this.scrollCapture = new ScrollCapture();
        this.rectManager = new RectManager();
        this.pointerIconService = new AndroidComposeView$pointerIconService$1(this);
    }

    public static final void access$addExtraDataToAccessibilityNodeInfoHelper(AndroidComposeView androidComposeView, int i, AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        int orDefault;
        if (Intrinsics.areEqual(str, androidComposeView.composeAccessibilityDelegate.ExtraDataTestTraversalBeforeVal)) {
            int orDefault2 = androidComposeView.composeAccessibilityDelegate.idToBeforeMap.getOrDefault(i);
            if (orDefault2 != -1) {
                accessibilityNodeInfo.getExtras().putInt(str, orDefault2);
                return;
            }
            return;
        }
        if (!Intrinsics.areEqual(str, androidComposeView.composeAccessibilityDelegate.ExtraDataTestTraversalAfterVal) || (orDefault = androidComposeView.composeAccessibilityDelegate.idToAfterMap.getOrDefault(i)) == -1) {
            return;
        }
        accessibilityNodeInfo.getExtras().putInt(str, orDefault);
    }

    /* renamed from: access$onRequestFocusForOwner-7o62pno, reason: not valid java name */
    public static final boolean m551access$onRequestFocusForOwner7o62pno(AndroidComposeView androidComposeView, FocusDirection focusDirection, Rect rect) {
        Integer m286toAndroidFocusDirection3ESFkO8;
        if (androidComposeView.isFocused() || androidComposeView.hasFocus()) {
            return true;
        }
        return super.requestFocus((focusDirection == null || (m286toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m286toAndroidFocusDirection3ESFkO8(focusDirection.value)) == null) ? 130 : m286toAndroidFocusDirection3ESFkO8.intValue(), rect != null ? RectHelper_androidKt.toAndroidRect(rect) : null);
    }

    public static void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    /* renamed from: convertMeasureSpec-I7RO_PI, reason: not valid java name */
    public static long m552convertMeasureSpecI7RO_PI(int i) {
        long j;
        long j2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            j = 0 << 32;
        } else {
            if (mode != 0) {
                if (mode != 1073741824) {
                    throw new IllegalStateException();
                }
                j2 = size;
                j = j2 << 32;
                return j | j2;
            }
            j = 0 << 32;
            size = Integer.MAX_VALUE;
        }
        j2 = size;
        return j | j2;
    }

    public static void invalidateLayers(LayoutNode layoutNode) {
        layoutNode.invalidateLayers$ui_release();
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                invalidateLayers((LayoutNode) objArr[i2]);
                i2++;
            } while (i2 < i);
        }
    }

    public static boolean isBadMotionEvent(MotionEvent motionEvent) {
        boolean z = (Float.floatToRawIntBits(motionEvent.getX()) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(motionEvent.getY()) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(motionEvent.getRawX()) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(motionEvent.getRawY()) & Integer.MAX_VALUE) >= 2139095040;
        if (!z) {
            int pointerCount = motionEvent.getPointerCount();
            for (int i = 1; i < pointerCount; i++) {
                z = (Float.floatToRawIntBits(motionEvent.getX(i)) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(motionEvent.getY(i)) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(motionEvent.getRawX(i)) & Integer.MAX_VALUE) >= 2139095040 || (Float.floatToRawIntBits(motionEvent.getRawY(i)) & Integer.MAX_VALUE) >= 2139095040;
                if (z) {
                    break;
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        addView(view, -1);
    }

    @Override // android.view.View
    public final void autofill(SparseArray sparseArray) {
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                AutofillValue autofillValue = (AutofillValue) sparseArray.get(keyAt);
                if (autofillValue.isText()) {
                    autofillValue.getTextValue().toString();
                    if (androidAutofill.autofillTree.children.get(Integer.valueOf(keyAt)) != null) {
                        throw new ClassCastException();
                    }
                } else {
                    if (autofillValue.isDate()) {
                        throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for date");
                    }
                    if (autofillValue.isList()) {
                        throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for list");
                    }
                    if (autofillValue.isToggle()) {
                        throw new NotImplementedError("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        return this.composeAccessibilityDelegate.m559canScroll0AR0LA0$ui_release(i, this.lastDownPointerPosition, false);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        return this.composeAccessibilityDelegate.m559canScroll0AR0LA0$ui_release(i, this.lastDownPointerPosition, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (!isAttachedToWindow()) {
            invalidateLayers(this.root);
        }
        measureAndLayout(true);
        SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
        this.isDrawingContent = true;
        CanvasHolder canvasHolder = this.canvasHolder;
        AndroidCanvas androidCanvas = canvasHolder.androidCanvas;
        Canvas canvas2 = androidCanvas.internalCanvas;
        androidCanvas.internalCanvas = canvas;
        this.root.draw$ui_release(androidCanvas, null);
        canvasHolder.androidCanvas.internalCanvas = canvas2;
        if (!this.dirtyLayers.isEmpty()) {
            int size = ((ArrayList) this.dirtyLayers).size();
            for (int i = 0; i < size; i++) {
                ((OwnedLayer) ((ArrayList) this.dirtyLayers).get(i)).updateDisplayList();
            }
        }
        if (ViewLayer.shouldUseDispatchDraw) {
            int save = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(save);
        }
        this.dirtyLayers.clear();
        this.isDrawingContent = false;
        List list = this.postponedDirtyLayers;
        if (list != null) {
            this.dirtyLayers.addAll(list);
            list.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r12v11, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r12v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v22, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r13v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v40 */
    /* JADX WARN: Type inference failed for: r13v41 */
    /* JADX WARN: Type inference failed for: r13v42 */
    /* JADX WARN: Type inference failed for: r13v43 */
    /* JADX WARN: Type inference failed for: r13v44 */
    /* JADX WARN: Type inference failed for: r13v45 */
    /* JADX WARN: Type inference failed for: r13v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r13v8, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v27, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v36, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        RotaryInputModifierNode rotaryInputModifierNode;
        int size;
        NodeChain nodeChain;
        DelegatingNode delegatingNode;
        NodeChain nodeChain2;
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            if (motionEvent.getActionMasked() == 8) {
                this.hoverExitReceived = false;
            } else {
                this.sendHoverExitEvent.run();
            }
        }
        if (motionEvent.getActionMasked() != 8) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (motionEvent.isFromSource(4194304)) {
            android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
            float f = -motionEvent.getAxisValue(26);
            getContext();
            float scaledVerticalScrollFactor = viewConfiguration.getScaledVerticalScrollFactor() * f;
            getContext();
            RotaryScrollEvent rotaryScrollEvent = new RotaryScrollEvent(scaledVerticalScrollFactor, viewConfiguration.getScaledHorizontalScrollFactor() * f, motionEvent.getDeviceId(), motionEvent.getEventTime());
            FocusOwnerImpl focusOwnerImpl = this.focusOwner;
            if (focusOwnerImpl.focusInvalidationManager.hasPendingInvalidation()) {
                throw new IllegalStateException("Dispatching rotary event while focus system is invalidated.");
            }
            FocusTargetNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(focusOwnerImpl.rootFocusNode);
            if (findActiveFocusNode != null) {
                Modifier.Node node = findActiveFocusNode.node;
                if (!node.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(findActiveFocusNode);
                loop0: while (true) {
                    if (requireLayoutNode == null) {
                        delegatingNode = 0;
                        break;
                    }
                    if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 16384) != 0) {
                        while (node != null) {
                            if ((node.kindSet & 16384) != 0) {
                                ?? r6 = 0;
                                delegatingNode = node;
                                while (delegatingNode != 0) {
                                    if (delegatingNode instanceof RotaryInputModifierNode) {
                                        break loop0;
                                    }
                                    if ((delegatingNode.kindSet & 16384) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                        Modifier.Node node2 = delegatingNode.delegate;
                                        int i = 0;
                                        delegatingNode = delegatingNode;
                                        r6 = r6;
                                        while (node2 != null) {
                                            if ((node2.kindSet & 16384) != 0) {
                                                i++;
                                                r6 = r6;
                                                if (i == 1) {
                                                    delegatingNode = node2;
                                                } else {
                                                    if (r6 == 0) {
                                                        r6 = new MutableVector(new Modifier.Node[16]);
                                                    }
                                                    if (delegatingNode != 0) {
                                                        r6.add(delegatingNode);
                                                        delegatingNode = 0;
                                                    }
                                                    r6.add(node2);
                                                }
                                            }
                                            node2 = node2.child;
                                            delegatingNode = delegatingNode;
                                            r6 = r6;
                                        }
                                        if (i == 1) {
                                        }
                                    }
                                    delegatingNode = DelegatableNodeKt.access$pop(r6);
                                }
                            }
                            node = node.parent;
                        }
                    }
                    requireLayoutNode = requireLayoutNode.getParent$ui_release();
                    node = (requireLayoutNode == null || (nodeChain2 = requireLayoutNode.nodes) == null) ? null : nodeChain2.tail;
                }
                rotaryInputModifierNode = (RotaryInputModifierNode) delegatingNode;
            } else {
                rotaryInputModifierNode = null;
            }
            if (rotaryInputModifierNode == null) {
                return false;
            }
            Modifier.Node node3 = (Modifier.Node) rotaryInputModifierNode;
            Modifier.Node node4 = node3.node;
            if (!node4.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node5 = node4.parent;
            LayoutNode requireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(rotaryInputModifierNode);
            ArrayList arrayList = null;
            while (requireLayoutNode2 != null) {
                if ((requireLayoutNode2.nodes.head.aggregateChildKindSet & 16384) != 0) {
                    while (node5 != null) {
                        if ((node5.kindSet & 16384) != 0) {
                            Modifier.Node node6 = node5;
                            MutableVector mutableVector = null;
                            while (node6 != null) {
                                if (node6 instanceof RotaryInputModifierNode) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(node6);
                                } else if ((node6.kindSet & 16384) != 0 && (node6 instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node node7 = ((DelegatingNode) node6).delegate; node7 != null; node7 = node7.child) {
                                        if ((node7.kindSet & 16384) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                node6 = node7;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (node6 != null) {
                                                    mutableVector.add(node6);
                                                    node6 = null;
                                                }
                                                mutableVector.add(node7);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                node6 = DelegatableNodeKt.access$pop(mutableVector);
                            }
                        }
                        node5 = node5.parent;
                    }
                }
                requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
                node5 = (requireLayoutNode2 == null || (nodeChain = requireLayoutNode2.nodes) == null) ? null : nodeChain.tail;
            }
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                while (true) {
                    int i3 = size - 1;
                    ((RotaryInputModifierNode) arrayList.get(size)).getClass();
                    if (i3 < 0) {
                        break;
                    }
                    size = i3;
                }
            }
            DelegatingNode delegatingNode2 = node3.node;
            ?? r4 = 0;
            while (delegatingNode2 != 0) {
                if (delegatingNode2 instanceof RotaryInputModifierNode) {
                } else if ((delegatingNode2.kindSet & 16384) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                    Modifier.Node node8 = delegatingNode2.delegate;
                    int i4 = 0;
                    r4 = r4;
                    delegatingNode2 = delegatingNode2;
                    while (node8 != null) {
                        if ((node8.kindSet & 16384) != 0) {
                            i4++;
                            r4 = r4;
                            if (i4 == 1) {
                                delegatingNode2 = node8;
                            } else {
                                if (r4 == 0) {
                                    r4 = new MutableVector(new Modifier.Node[16]);
                                }
                                if (delegatingNode2 != 0) {
                                    r4.add(delegatingNode2);
                                    delegatingNode2 = 0;
                                }
                                r4.add(node8);
                            }
                        }
                        node8 = node8.child;
                        r4 = r4;
                        delegatingNode2 = delegatingNode2;
                    }
                    if (i4 == 1) {
                    }
                }
                delegatingNode2 = DelegatableNodeKt.access$pop(r4);
            }
            DelegatingNode delegatingNode3 = node3.node;
            ?? r13 = 0;
            while (true) {
                if (delegatingNode3 == 0) {
                    if (arrayList == null) {
                        return false;
                    }
                    int size2 = arrayList.size();
                    for (int i5 = 0; i5 < size2; i5++) {
                        if (!((RotaryInputModifierNode) arrayList.get(i5)).onRotaryScrollEvent(rotaryScrollEvent)) {
                        }
                    }
                    return false;
                }
                if (delegatingNode3 instanceof RotaryInputModifierNode) {
                    if (((RotaryInputModifierNode) delegatingNode3).onRotaryScrollEvent(rotaryScrollEvent)) {
                        break;
                    }
                } else if ((delegatingNode3.kindSet & 16384) != 0 && (delegatingNode3 instanceof DelegatingNode)) {
                    Modifier.Node node9 = delegatingNode3.delegate;
                    int i6 = 0;
                    delegatingNode3 = delegatingNode3;
                    r13 = r13;
                    while (node9 != null) {
                        if ((node9.kindSet & 16384) != 0) {
                            i6++;
                            r13 = r13;
                            if (i6 == 1) {
                                delegatingNode3 = node9;
                            } else {
                                if (r13 == 0) {
                                    r13 = new MutableVector(new Modifier.Node[16]);
                                }
                                if (delegatingNode3 != 0) {
                                    r13.add(delegatingNode3);
                                    delegatingNode3 = 0;
                                }
                                r13.add(node9);
                            }
                        }
                        node9 = node9.child;
                        delegatingNode3 = delegatingNode3;
                        r13 = r13;
                    }
                    if (i6 == 1) {
                    }
                }
                delegatingNode3 = DelegatableNodeKt.access$pop(r13);
            }
        } else if ((m553handleMotionEvent8iAsVTc(motionEvent) & 1) == 0) {
            return false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i;
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.composeAccessibilityDelegate;
        if (androidComposeViewAccessibilityDelegateCompat.accessibilityManager.isEnabled() && androidComposeViewAccessibilityDelegateCompat.accessibilityManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            AndroidComposeView androidComposeView = androidComposeViewAccessibilityDelegateCompat.view;
            if (action == 7 || action == 9) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                androidComposeView.measureAndLayout(true);
                HitTestResult hitTestResult = new HitTestResult();
                LayoutNode layoutNode = androidComposeView.root;
                long floatToRawIntBits = (Float.floatToRawIntBits(x) << 32) | (Float.floatToRawIntBits(y) & 4294967295L);
                LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
                NodeChain nodeChain = layoutNode.nodes;
                NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator;
                Function1 function1 = NodeCoordinator.onCommitAffectingLayerParams;
                nodeChain.outerCoordinator.m534hitTestYqVAtuI(NodeCoordinator.SemanticsSource, nodeCoordinator.m529fromParentPosition8S9VItk(floatToRawIntBits), hitTestResult, true, true);
                for (int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(hitTestResult); -1 < lastIndex; lastIndex--) {
                    LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode((Modifier.Node) hitTestResult.values[lastIndex]);
                    if (((AndroidViewHolder) androidComposeView.getAndroidViewsHandler$ui_release().layoutNodeToHolder.get(requireLayoutNode)) != null) {
                        break;
                    }
                    if (requireLayoutNode.nodes.m525hasH91voCI$ui_release(8)) {
                        int semanticsNodeIdToAccessibilityVirtualNodeId = androidComposeViewAccessibilityDelegateCompat.semanticsNodeIdToAccessibilityVirtualNodeId(requireLayoutNode.semanticsId);
                        SemanticsNode SemanticsNode = SemanticsNodeKt.SemanticsNode(requireLayoutNode, false);
                        if (SemanticsUtils_androidKt.isImportantForAccessibility(SemanticsNode)) {
                            if (!SemanticsNode.getConfig().props.containsKey(SemanticsProperties.LinkTestMarker)) {
                                i = semanticsNodeIdToAccessibilityVirtualNodeId;
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                i = Integer.MIN_VALUE;
                androidComposeView.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(motionEvent);
                int i2 = androidComposeViewAccessibilityDelegateCompat.hoveredVirtualViewId;
                if (i2 != i) {
                    androidComposeViewAccessibilityDelegateCompat.hoveredVirtualViewId = i;
                    AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(androidComposeViewAccessibilityDelegateCompat, i, 128, null, 12);
                    AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(androidComposeViewAccessibilityDelegateCompat, i2, 256, null, 12);
                }
            } else if (action == 10) {
                int i3 = androidComposeViewAccessibilityDelegateCompat.hoveredVirtualViewId;
                if (i3 == Integer.MIN_VALUE) {
                    androidComposeView.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(motionEvent);
                } else if (i3 != Integer.MIN_VALUE) {
                    androidComposeViewAccessibilityDelegateCompat.hoveredVirtualViewId = Integer.MIN_VALUE;
                    AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(androidComposeViewAccessibilityDelegateCompat, Integer.MIN_VALUE, 128, null, 12);
                    AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(androidComposeViewAccessibilityDelegateCompat, i3, 256, null, 12);
                }
            }
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10 && isInBounds(motionEvent)) {
                if (motionEvent.getToolType(0) == 3 && motionEvent.getButtonState() != 0) {
                    return false;
                }
                MotionEvent motionEvent2 = this.previousMotionEvent;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                this.hoverExitReceived = true;
                postDelayed(this.sendHoverExitEvent, 8L);
                return false;
            }
        } else if (!isPositionChanged(motionEvent)) {
            return false;
        }
        return (m553handleMotionEvent8iAsVTc(motionEvent) & 1) != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        boolean m289dispatchKeyEventYhN2O0w;
        if (!isFocused()) {
            return this.focusOwner.m289dispatchKeyEventYhN2O0w(keyEvent, new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$dispatchKeyEvent$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean dispatchKeyEvent;
                    dispatchKeyEvent = super/*android.view.ViewGroup*/.dispatchKeyEvent(keyEvent);
                    return Boolean.valueOf(dispatchKeyEvent);
                }
            });
        }
        WindowInfoImpl windowInfoImpl = this._windowInfo;
        int metaState = keyEvent.getMetaState();
        windowInfoImpl.getClass();
        ((SnapshotMutableStateImpl) WindowInfoImpl.GlobalKeyboardModifiers).setValue(new PointerKeyboardModifiers(metaState));
        m289dispatchKeyEventYhN2O0w = this.focusOwner.m289dispatchKeyEventYhN2O0w(keyEvent, new Function0() { // from class: androidx.compose.ui.focus.FocusOwner$dispatchKeyEvent$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Boolean.FALSE;
            }
        });
        return m289dispatchKeyEventYhN2O0w || super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v26, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Type inference failed for: r9v40 */
    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        SoftKeyboardInterceptionModifierNode softKeyboardInterceptionModifierNode;
        int size;
        NodeChain nodeChain;
        DelegatingNode delegatingNode;
        NodeChain nodeChain2;
        if (isFocused()) {
            FocusOwnerImpl focusOwnerImpl = this.focusOwner;
            if (focusOwnerImpl.focusInvalidationManager.hasPendingInvalidation()) {
                System.out.println((Object) "FocusRelatedWarning: Dispatching intercepted soft keyboard event while the focus system is invalidated.");
            } else {
                FocusTargetNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(focusOwnerImpl.rootFocusNode);
                if (findActiveFocusNode != null) {
                    Modifier.Node node = findActiveFocusNode.node;
                    if (!node.isAttached) {
                        throw new IllegalStateException("visitAncestors called on an unattached node");
                    }
                    LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(findActiveFocusNode);
                    loop0: while (true) {
                        if (requireLayoutNode == null) {
                            delegatingNode = 0;
                            break;
                        }
                        if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 131072) != 0) {
                            while (node != null) {
                                if ((node.kindSet & 131072) != 0) {
                                    ?? r9 = 0;
                                    delegatingNode = node;
                                    while (delegatingNode != 0) {
                                        if (delegatingNode instanceof SoftKeyboardInterceptionModifierNode) {
                                            break loop0;
                                        }
                                        if ((delegatingNode.kindSet & 131072) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                            Modifier.Node node2 = delegatingNode.delegate;
                                            int i = 0;
                                            delegatingNode = delegatingNode;
                                            r9 = r9;
                                            while (node2 != null) {
                                                if ((node2.kindSet & 131072) != 0) {
                                                    i++;
                                                    r9 = r9;
                                                    if (i == 1) {
                                                        delegatingNode = node2;
                                                    } else {
                                                        if (r9 == 0) {
                                                            r9 = new MutableVector(new Modifier.Node[16]);
                                                        }
                                                        if (delegatingNode != 0) {
                                                            r9.add(delegatingNode);
                                                            delegatingNode = 0;
                                                        }
                                                        r9.add(node2);
                                                    }
                                                }
                                                node2 = node2.child;
                                                delegatingNode = delegatingNode;
                                                r9 = r9;
                                            }
                                            if (i == 1) {
                                            }
                                        }
                                        delegatingNode = DelegatableNodeKt.access$pop(r9);
                                    }
                                }
                                node = node.parent;
                            }
                        }
                        requireLayoutNode = requireLayoutNode.getParent$ui_release();
                        node = (requireLayoutNode == null || (nodeChain2 = requireLayoutNode.nodes) == null) ? null : nodeChain2.tail;
                    }
                    softKeyboardInterceptionModifierNode = (SoftKeyboardInterceptionModifierNode) delegatingNode;
                } else {
                    softKeyboardInterceptionModifierNode = null;
                }
                if (softKeyboardInterceptionModifierNode != null) {
                    Modifier.Node node3 = (Modifier.Node) softKeyboardInterceptionModifierNode;
                    Modifier.Node node4 = node3.node;
                    if (!node4.isAttached) {
                        throw new IllegalStateException("visitAncestors called on an unattached node");
                    }
                    Modifier.Node node5 = node4.parent;
                    LayoutNode requireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(softKeyboardInterceptionModifierNode);
                    ArrayList arrayList = null;
                    while (requireLayoutNode2 != null) {
                        if ((requireLayoutNode2.nodes.head.aggregateChildKindSet & 131072) != 0) {
                            while (node5 != null) {
                                if ((node5.kindSet & 131072) != 0) {
                                    Modifier.Node node6 = node5;
                                    MutableVector mutableVector = null;
                                    while (node6 != null) {
                                        if (node6 instanceof SoftKeyboardInterceptionModifierNode) {
                                            if (arrayList == null) {
                                                arrayList = new ArrayList();
                                            }
                                            arrayList.add(node6);
                                        } else if ((node6.kindSet & 131072) != 0 && (node6 instanceof DelegatingNode)) {
                                            int i2 = 0;
                                            for (Modifier.Node node7 = ((DelegatingNode) node6).delegate; node7 != null; node7 = node7.child) {
                                                if ((node7.kindSet & 131072) != 0) {
                                                    i2++;
                                                    if (i2 == 1) {
                                                        node6 = node7;
                                                    } else {
                                                        if (mutableVector == null) {
                                                            mutableVector = new MutableVector(new Modifier.Node[16]);
                                                        }
                                                        if (node6 != null) {
                                                            mutableVector.add(node6);
                                                            node6 = null;
                                                        }
                                                        mutableVector.add(node7);
                                                    }
                                                }
                                            }
                                            if (i2 == 1) {
                                            }
                                        }
                                        node6 = DelegatableNodeKt.access$pop(mutableVector);
                                    }
                                }
                                node5 = node5.parent;
                            }
                        }
                        requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
                        node5 = (requireLayoutNode2 == null || (nodeChain = requireLayoutNode2.nodes) == null) ? null : nodeChain.tail;
                    }
                    if (arrayList != null && arrayList.size() - 1 >= 0) {
                        while (true) {
                            int i3 = size - 1;
                            ((SoftKeyboardInterceptionModifierNode) arrayList.get(size)).getClass();
                            if (i3 < 0) {
                                break;
                            }
                            size = i3;
                        }
                    }
                    DelegatingNode delegatingNode2 = node3.node;
                    ?? r7 = 0;
                    while (delegatingNode2 != 0) {
                        if (delegatingNode2 instanceof SoftKeyboardInterceptionModifierNode) {
                        } else if ((delegatingNode2.kindSet & 131072) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                            Modifier.Node node8 = delegatingNode2.delegate;
                            int i4 = 0;
                            delegatingNode2 = delegatingNode2;
                            r7 = r7;
                            while (node8 != null) {
                                if ((node8.kindSet & 131072) != 0) {
                                    i4++;
                                    r7 = r7;
                                    if (i4 == 1) {
                                        delegatingNode2 = node8;
                                    } else {
                                        if (r7 == 0) {
                                            r7 = new MutableVector(new Modifier.Node[16]);
                                        }
                                        if (delegatingNode2 != 0) {
                                            r7.add(delegatingNode2);
                                            delegatingNode2 = 0;
                                        }
                                        r7.add(node8);
                                    }
                                }
                                node8 = node8.child;
                                delegatingNode2 = delegatingNode2;
                                r7 = r7;
                            }
                            if (i4 == 1) {
                            }
                        }
                        delegatingNode2 = DelegatableNodeKt.access$pop(r7);
                    }
                    DelegatingNode delegatingNode3 = node3.node;
                    ?? r3 = 0;
                    while (true) {
                        if (delegatingNode3 != 0) {
                            if (delegatingNode3 instanceof SoftKeyboardInterceptionModifierNode) {
                                if (((SoftKeyboardInterceptionModifierNode) delegatingNode3).m450onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                                    break;
                                }
                            } else if ((delegatingNode3.kindSet & 131072) != 0 && (delegatingNode3 instanceof DelegatingNode)) {
                                Modifier.Node node9 = delegatingNode3.delegate;
                                int i5 = 0;
                                delegatingNode3 = delegatingNode3;
                                r3 = r3;
                                while (node9 != null) {
                                    if ((node9.kindSet & 131072) != 0) {
                                        i5++;
                                        r3 = r3;
                                        if (i5 == 1) {
                                            delegatingNode3 = node9;
                                        } else {
                                            if (r3 == 0) {
                                                r3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (delegatingNode3 != 0) {
                                                r3.add(delegatingNode3);
                                                delegatingNode3 = 0;
                                            }
                                            r3.add(node9);
                                        }
                                    }
                                    node9 = node9.child;
                                    delegatingNode3 = delegatingNode3;
                                    r3 = r3;
                                }
                                if (i5 == 1) {
                                }
                            }
                            delegatingNode3 = DelegatableNodeKt.access$pop(r3);
                        } else if (arrayList != null) {
                            int size2 = arrayList.size();
                            for (int i6 = 0; i6 < size2; i6++) {
                                if (((SoftKeyboardInterceptionModifierNode) arrayList.get(i6)).m450onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                                    break;
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
        if (!super.dispatchKeyEventPreIme(keyEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            Intrinsics.checkNotNull(motionEvent2);
            if (motionEvent.getActionMasked() == 0 && motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) {
                this.hoverExitReceived = false;
            } else {
                this.sendHoverExitEvent.run();
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int m553handleMotionEvent8iAsVTc = m553handleMotionEvent8iAsVTc(motionEvent);
        if ((m553handleMotionEvent8iAsVTc & 2) != 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return (m553handleMotionEvent8iAsVTc & 1) != 0;
    }

    public final View findViewByAccessibilityIdTraversal(int i) {
        try {
            Method declaredMethod = Class.forName("android.view.View").getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(this, Integer.valueOf(i));
            if (invoke instanceof View) {
                return (View) invoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
    
        if (r0 == null) goto L25;
     */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View focusSearch(android.view.View r7, int r8) {
        /*
            r6 = this;
            if (r7 == 0) goto L83
            androidx.compose.ui.node.MeasureAndLayoutDelegate r0 = r6.measureAndLayoutDelegate
            boolean r0 = r0.duringMeasureLayout
            if (r0 == 0) goto La
            goto L83
        La:
            android.view.FocusFinder r0 = android.view.FocusFinder.getInstance()
            android.view.View r0 = r0.findNextFocus(r6, r7, r8)
            if (r7 != r6) goto L2b
            androidx.compose.ui.focus.FocusOwnerImpl r1 = r6.focusOwner
            androidx.compose.ui.focus.FocusTargetNode r1 = r1.rootFocusNode
            androidx.compose.ui.focus.FocusTargetNode r1 = androidx.compose.ui.focus.FocusTraversalKt.findActiveFocusNode(r1)
            if (r1 == 0) goto L23
            androidx.compose.ui.geometry.Rect r1 = androidx.compose.ui.focus.FocusTraversalKt.focusRect(r1)
            goto L24
        L23:
            r1 = 0
        L24:
            if (r1 != 0) goto L2f
            androidx.compose.ui.geometry.Rect r1 = androidx.compose.ui.focus.FocusInteropUtils_androidKt.calculateBoundingRectRelativeTo(r7, r6)
            goto L2f
        L2b:
            androidx.compose.ui.geometry.Rect r1 = androidx.compose.ui.focus.FocusInteropUtils_androidKt.calculateBoundingRectRelativeTo(r7, r6)
        L2f:
            androidx.compose.ui.focus.FocusDirection r2 = androidx.compose.ui.focus.FocusInteropUtils_androidKt.toFocusDirection(r8)
            if (r2 == 0) goto L38
            int r2 = r2.value
            goto L39
        L38:
            r2 = 6
        L39:
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            androidx.compose.ui.focus.FocusOwnerImpl r4 = r6.focusOwner
            androidx.compose.ui.platform.AndroidComposeView$focusSearch$searchResult$1 r5 = new androidx.compose.ui.platform.AndroidComposeView$focusSearch$searchResult$1
            r5.<init>()
            java.lang.Boolean r4 = r4.m290focusSearchULY8qGw(r2, r1, r5)
            if (r4 != 0) goto L4c
            goto L52
        L4c:
            java.lang.Object r4 = r3.element
            if (r4 != 0) goto L54
            if (r0 != 0) goto L81
        L52:
            r6 = r7
            goto L82
        L54:
            if (r0 != 0) goto L57
            goto L82
        L57:
            r4 = 1
            boolean r5 = androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r2, r4)
            if (r5 == 0) goto L5f
            goto L64
        L5f:
            r4 = 2
            boolean r4 = androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r2, r4)
        L64:
            if (r4 == 0) goto L6b
            android.view.View r6 = super.focusSearch(r7, r8)
            goto L82
        L6b:
            java.lang.Object r7 = r3.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            androidx.compose.ui.focus.FocusTargetNode r7 = (androidx.compose.ui.focus.FocusTargetNode) r7
            androidx.compose.ui.geometry.Rect r7 = androidx.compose.ui.focus.FocusTraversalKt.focusRect(r7)
            androidx.compose.ui.geometry.Rect r8 = androidx.compose.ui.focus.FocusInteropUtils_androidKt.calculateBoundingRectRelativeTo(r0, r6)
            boolean r7 = androidx.compose.ui.focus.TwoDimensionalFocusSearchKt.m303isBetterCandidateI7lrPNg(r7, r8, r1, r2)
            if (r7 == 0) goto L81
            goto L82
        L81:
            r6 = r0
        L82:
            return r6
        L83:
            android.view.View r6 = super.focusSearch(r7, r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean z) {
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode, z);
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui_release() {
        if (this._androidViewsHandler == null) {
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(getContext());
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler, -1);
            requestLayout();
        }
        AndroidViewsHandler androidViewsHandler2 = this._androidViewsHandler;
        Intrinsics.checkNotNull(androidViewsHandler2);
        return androidViewsHandler2;
    }

    @Override // android.view.View
    public final void getFocusedRect(android.graphics.Rect rect) {
        Unit unit;
        Rect onFetchFocusRect = onFetchFocusRect();
        if (onFetchFocusRect != null) {
            rect.left = Math.round(onFetchFocusRect.left);
            rect.top = Math.round(onFetchFocusRect.top);
            rect.right = Math.round(onFetchFocusRect.right);
            rect.bottom = Math.round(onFetchFocusRect.bottom);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            super.getFocusedRect(rect);
        }
    }

    @Override // android.view.View
    public final int getImportantForAutofill() {
        return 1;
    }

    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners$delegate.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004c A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:5:0x0018, B:7:0x0021, B:12:0x0032, B:14:0x003c, B:19:0x004c, B:22:0x0075, B:23:0x0053, B:29:0x005f, B:32:0x0067, B:34:0x007a, B:42:0x008d, B:44:0x0093, B:46:0x00a3, B:47:0x00a6, B:49:0x00aa, B:51:0x00b0, B:53:0x00b4, B:54:0x00ba, B:56:0x00c0, B:59:0x00c8, B:60:0x00d6, B:62:0x00dc, B:64:0x00e2, B:66:0x00e8, B:67:0x00ee, B:69:0x00f2, B:70:0x00f6, B:75:0x0109, B:77:0x010d, B:78:0x0114, B:84:0x0124, B:85:0x0130, B:91:0x013b), top: B:4:0x0018, outer: #1 }] */
    /* renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int m553handleMotionEvent8iAsVTc(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.m553handleMotionEvent8iAsVTc(android.view.MotionEvent):int");
    }

    public final void invalidateLayoutNodeMeasurement(LayoutNode layoutNode) {
        int i = 0;
        this.measureAndLayoutDelegate.requestRemeasure(layoutNode, false);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i2 = mutableVector.size;
        if (i2 > 0) {
            Object[] objArr = mutableVector.content;
            do {
                invalidateLayoutNodeMeasurement((LayoutNode) objArr[i]);
                i++;
            } while (i < i2);
        }
    }

    public final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return 0.0f <= x && x <= ((float) getWidth()) && 0.0f <= y && y <= ((float) getHeight());
    }

    public final boolean isPositionChanged(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        return (motionEvent.getPointerCount() == 1 && (motionEvent2 = this.previousMotionEvent) != null && motionEvent2.getPointerCount() == motionEvent.getPointerCount() && motionEvent.getRawX() == motionEvent2.getRawX() && motionEvent.getRawY() == motionEvent2.getRawY()) ? false : true;
    }

    /* renamed from: localToScreen-58bKbWc, reason: not valid java name */
    public final void m554localToScreen58bKbWc(float[] fArr) {
        recalculateWindowPosition();
        Matrix.m383timesAssign58bKbWc(fArr, this.viewToWindowMatrix);
        float intBitsToFloat = Float.intBitsToFloat((int) (this.windowPosition >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (this.windowPosition & 4294967295L));
        float[] fArr2 = this.tmpMatrix;
        Function1 function1 = AndroidComposeView_androidKt.platformTextInputServiceInterceptor;
        Matrix.m382resetimpl(fArr2);
        Matrix.m384translateimpl(fArr2, intBitsToFloat, intBitsToFloat2);
        float m560dotp89u6pk = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 0, fArr, 0);
        float m560dotp89u6pk2 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 0, fArr, 1);
        float m560dotp89u6pk3 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 0, fArr, 2);
        float m560dotp89u6pk4 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 0, fArr, 3);
        float m560dotp89u6pk5 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 1, fArr, 0);
        float m560dotp89u6pk6 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 1, fArr, 1);
        float m560dotp89u6pk7 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 1, fArr, 2);
        float m560dotp89u6pk8 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 1, fArr, 3);
        float m560dotp89u6pk9 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 2, fArr, 0);
        float m560dotp89u6pk10 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 2, fArr, 1);
        float m560dotp89u6pk11 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 2, fArr, 2);
        float m560dotp89u6pk12 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 2, fArr, 3);
        float m560dotp89u6pk13 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 3, fArr, 0);
        float m560dotp89u6pk14 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 3, fArr, 1);
        float m560dotp89u6pk15 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 3, fArr, 2);
        float m560dotp89u6pk16 = AndroidComposeView_androidKt.m560dotp89u6pk(fArr2, 3, fArr, 3);
        fArr[0] = m560dotp89u6pk;
        fArr[1] = m560dotp89u6pk2;
        fArr[2] = m560dotp89u6pk3;
        fArr[3] = m560dotp89u6pk4;
        fArr[4] = m560dotp89u6pk5;
        fArr[5] = m560dotp89u6pk6;
        fArr[6] = m560dotp89u6pk7;
        fArr[7] = m560dotp89u6pk8;
        fArr[8] = m560dotp89u6pk9;
        fArr[9] = m560dotp89u6pk10;
        fArr[10] = m560dotp89u6pk11;
        fArr[11] = m560dotp89u6pk12;
        fArr[12] = m560dotp89u6pk13;
        fArr[13] = m560dotp89u6pk14;
        fArr[14] = m560dotp89u6pk15;
        fArr[15] = m560dotp89u6pk16;
    }

    /* renamed from: localToScreen-MK-Hz9U, reason: not valid java name */
    public final long m555localToScreenMKHz9U(long j) {
        recalculateWindowPosition();
        long m380mapMKHz9U = Matrix.m380mapMKHz9U(j, this.viewToWindowMatrix);
        float intBitsToFloat = Float.intBitsToFloat((int) (this.windowPosition >> 32)) + Float.intBitsToFloat((int) (m380mapMKHz9U >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (this.windowPosition & 4294967295L)) + Float.intBitsToFloat((int) (m380mapMKHz9U & 4294967295L));
        return (Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L);
    }

    public final void measureAndLayout(boolean z) {
        Function0 function0;
        if (this.measureAndLayoutDelegate.relayoutNodes.isNotEmpty() || this.measureAndLayoutDelegate.onPositionedDispatcher.layoutNodes.size != 0) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (z) {
                try {
                    function0 = this.resendMotionEventOnLayout;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                function0 = null;
            }
            if (this.measureAndLayoutDelegate.measureAndLayout(function0)) {
                requestLayout();
            }
            this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(false);
            if (this.isPendingInteropViewLayoutChangeDispatch) {
                getViewTreeObserver().dispatchOnGlobalLayout();
                this.isPendingInteropViewLayoutChangeDispatch = false;
            }
            Trace.endSection();
        }
    }

    /* renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    public final void m556measureAndLayout0kLqBqw(LayoutNode layoutNode, long j) {
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            this.measureAndLayoutDelegate.m523measureAndLayout0kLqBqw(layoutNode, j);
            if (!this.measureAndLayoutDelegate.relayoutNodes.isNotEmpty()) {
                this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(false);
                if (this.isPendingInteropViewLayoutChangeDispatch) {
                    getViewTreeObserver().dispatchOnGlobalLayout();
                    this.isPendingInteropViewLayoutChangeDispatch = false;
                }
            }
            this.rectManager.dispatchCallbacks();
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public final void notifyLayerIsDirty$ui_release(OwnedLayer ownedLayer, boolean z) {
        if (!z) {
            if (this.isDrawingContent) {
                return;
            }
            this.dirtyLayers.remove(ownedLayer);
            List list = this.postponedDirtyLayers;
            if (list != null) {
                list.remove(ownedLayer);
                return;
            }
            return;
        }
        if (!this.isDrawingContent) {
            this.dirtyLayers.add(ownedLayer);
            return;
        }
        List list2 = this.postponedDirtyLayers;
        if (list2 == null) {
            list2 = new ArrayList();
            this.postponedDirtyLayers = list2;
        }
        list2.add(ownedLayer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        Lifecycle lifecycle;
        LifecycleOwner lifecycleOwner;
        super.onAttachedToWindow();
        ((SnapshotMutableStateImpl) this._windowInfo._isWindowFocused).setValue(Boolean.valueOf(hasWindowFocus()));
        invalidateLayoutNodeMeasurement(this.root);
        invalidateLayers(this.root);
        SnapshotStateObserver snapshotStateObserver = this.snapshotObserver.observer;
        snapshotStateObserver.applyUnsubscribe = Snapshot.Companion.registerApplyObserver(snapshotStateObserver.applyObserver);
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AutofillCallback autofillCallback = AutofillCallback.INSTANCE;
            autofillCallback.getClass();
            androidAutofill.autofillManager.registerCallback(autofillCallback);
        }
        LifecycleOwner lifecycleOwner2 = ViewTreeLifecycleOwner.get(this);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(this);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners == null || (lifecycleOwner2 != null && savedStateRegistryOwner != null && (lifecycleOwner2 != (lifecycleOwner = viewTreeOwners.lifecycleOwner) || savedStateRegistryOwner != lifecycleOwner))) {
            if (lifecycleOwner2 == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (savedStateRegistryOwner == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (lifecycle = viewTreeOwners.lifecycleOwner.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
            lifecycleOwner2.getLifecycle().addObserver(this);
            ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(lifecycleOwner2, savedStateRegistryOwner);
            ((SnapshotMutableStateImpl) this._viewTreeOwners$delegate).setValue(viewTreeOwners2);
            Function1 function1 = this.onViewTreeOwnersAvailable;
            if (function1 != null) {
                ((WrappedComposition$setContent$1) function1).invoke(viewTreeOwners2);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        ((SnapshotMutableStateImpl) this._inputModeManager.inputMode$delegate).setValue(new InputMode(isInTouchMode() ? 1 : 2));
        ViewTreeOwners viewTreeOwners3 = getViewTreeOwners();
        Lifecycle lifecycle2 = viewTreeOwners3 != null ? viewTreeOwners3.lifecycleOwner.getLifecycle() : null;
        if (lifecycle2 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
            throw null;
        }
        lifecycle2.addObserver(this);
        lifecycle2.addObserver(this.contentCaptureManager);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
        setViewTranslationCallback(AndroidComposeViewTranslationCallback.INSTANCE);
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m275getCurrentSessionimpl(this.textInputSessionMutex);
        if (androidPlatformTextInputSession == null) {
            return this.legacyTextInputServiceAndroid.editorHasFocus;
        }
        InputMethodSession inputMethodSession = (InputMethodSession) SessionMutex.m275getCurrentSessionimpl(androidPlatformTextInputSession.methodSessionMutex);
        return inputMethodSession != null && (inputMethodSession.disposed ^ true);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ((SnapshotMutableStateImpl) this.density$delegate).setValue(AndroidDensity_androidKt.Density(getContext()));
        int i = configuration.fontWeightAdjustment;
        if (i != this.currentFontWeightAdjustment) {
            this.currentFontWeightAdjustment = i;
            ((SnapshotMutableStateImpl) this.fontFamilyResolver$delegate).setValue(FontFamilyResolver_androidKt.createFontFamilyResolver(getContext()));
        }
        this.configurationChangeObserver.invoke(configuration);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0073  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo r14) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.onCreateInputConnection(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    @Override // android.view.View
    public final void onCreateVirtualViewTranslationRequests(long[] jArr, int[] iArr, Consumer consumer) {
        SemanticsNode semanticsNode;
        String fastJoinToString$default;
        AndroidContentCaptureManager androidContentCaptureManager = this.contentCaptureManager;
        androidContentCaptureManager.getClass();
        for (long j : jArr) {
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) androidContentCaptureManager.getCurrentSemanticsNodes$ui_release().get((int) j);
            if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.semanticsNode) != null) {
                ViewTranslationRequest.Builder builder = new ViewTranslationRequest.Builder(androidContentCaptureManager.view.getAutofillId(), semanticsNode.id);
                List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.Text);
                if (list != null && (fastJoinToString$default = ListUtilsKt.fastJoinToString$default(list, "\n", null, 62)) != null) {
                    builder.setValue("android:text", TranslationRequestValue.forText(new AnnotatedString(fastJoinToString$default)));
                    consumer.accept(builder.build());
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SnapshotStateObserver snapshotStateObserver = this.snapshotObserver.observer;
        Snapshot$Companion$$ExternalSyntheticLambda0 snapshot$Companion$$ExternalSyntheticLambda0 = snapshotStateObserver.applyUnsubscribe;
        if (snapshot$Companion$$ExternalSyntheticLambda0 != null) {
            snapshot$Companion$$ExternalSyntheticLambda0.dispose();
        }
        snapshotStateObserver.clear();
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        Lifecycle lifecycle = viewTreeOwners != null ? viewTreeOwners.lifecycleOwner.getLifecycle() : null;
        if (lifecycle == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
            throw null;
        }
        lifecycle.removeObserver(this.contentCaptureManager);
        lifecycle.removeObserver(this);
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AutofillCallback autofillCallback = AutofillCallback.INSTANCE;
            autofillCallback.getClass();
            androidAutofill.autofillManager.unregisterCallback(autofillCallback);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
        clearViewTranslationCallback();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        this.rectManager.dispatchCallbacks();
    }

    public final void onEndApplyChanges() {
        if (this.observationClearRequested) {
            this.snapshotObserver.clearInvalidObservations$ui_release();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        while (true) {
            int i = this.endApplyChangesListeners.size;
            if (i == 0) {
                this.rectManager.dispatchCallbacks();
                return;
            }
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr = this.endApplyChangesListeners.content;
                Function0 function0 = (Function0) objArr[i2];
                objArr[i2] = null;
                if (function0 != null) {
                    function0.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, i);
        }
    }

    public final Rect onFetchFocusRect() {
        if (isFocused()) {
            FocusTargetNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.focusOwner.rootFocusNode);
            if (findActiveFocusNode != null) {
                return FocusTraversalKt.focusRect(findActiveFocusNode);
            }
            return null;
        }
        View findFocus = findFocus();
        if (findFocus != null) {
            return FocusInteropUtils_androidKt.calculateBoundingRectRelativeTo(findFocus, this);
        }
        return null;
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, android.graphics.Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z || hasFocus()) {
            return;
        }
        FocusOwnerImpl focusOwnerImpl = this.focusOwner;
        FocusTransactionManager focusTransactionManager = focusOwnerImpl.focusTransactionManager;
        boolean z2 = focusTransactionManager.ongoingTransaction;
        FocusTargetNode focusTargetNode = focusOwnerImpl.rootFocusNode;
        if (z2) {
            FocusTransactionsKt.clearFocus(focusTargetNode, true);
            return;
        }
        try {
            focusTransactionManager.ongoingTransaction = true;
            FocusTransactionsKt.clearFocus(focusTargetNode, true);
        } finally {
            FocusTransactionManager.access$commitTransaction(focusTransactionManager);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, i3 - i, i4 - i2);
        }
    }

    public final void onLayoutChange(LayoutNode layoutNode) {
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.composeAccessibilityDelegate;
        androidComposeViewAccessibilityDelegateCompat.currentSemanticsNodesInvalidated = true;
        if (androidComposeViewAccessibilityDelegateCompat.isEnabled$ui_release()) {
            androidComposeViewAccessibilityDelegateCompat.notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
        }
        AndroidContentCaptureManager androidContentCaptureManager = this.contentCaptureManager;
        androidContentCaptureManager.currentSemanticsNodesInvalidated = true;
        if (androidContentCaptureManager.isEnabled$ui_release() && androidContentCaptureManager.subtreeChangedLayoutNodes.add(layoutNode)) {
            androidContentCaptureManager.boundsUpdateChannel.mo1790trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(this.root);
            }
            long m552convertMeasureSpecI7RO_PI = m552convertMeasureSpecI7RO_PI(i);
            long m552convertMeasureSpecI7RO_PI2 = m552convertMeasureSpecI7RO_PI(i2);
            long m659fitPrioritizingHeightZbe2FdA = Constraints.Companion.m659fitPrioritizingHeightZbe2FdA((int) (m552convertMeasureSpecI7RO_PI >>> 32), (int) (m552convertMeasureSpecI7RO_PI & 4294967295L), (int) (m552convertMeasureSpecI7RO_PI2 >>> 32), (int) (4294967295L & m552convertMeasureSpecI7RO_PI2));
            Constraints constraints = this.onMeasureConstraints;
            if (constraints == null) {
                this.onMeasureConstraints = new Constraints(m659fitPrioritizingHeightZbe2FdA);
                this.wasMeasuredWithMultipleConstraints = false;
            } else if (!Constraints.m649equalsimpl0(constraints.value, m659fitPrioritizingHeightZbe2FdA)) {
                this.wasMeasuredWithMultipleConstraints = true;
            }
            this.measureAndLayoutDelegate.m524updateRootConstraintsBRTryo0(m659fitPrioritizingHeightZbe2FdA);
            this.measureAndLayoutDelegate.measureOnly();
            LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this.root.layoutDelegate.measurePassDelegate;
            setMeasuredDimension(measurePassDelegate.width, measurePassDelegate.height);
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(this.root.layoutDelegate.measurePassDelegate.width, 1073741824), View.MeasureSpec.makeMeasureSpec(this.root.layoutDelegate.measurePassDelegate.height, 1073741824));
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.view.View
    public final void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        AndroidAutofill androidAutofill;
        if (viewStructure == null || (androidAutofill = this._autofill) == null) {
            return;
        }
        AutofillTree autofillTree = androidAutofill.autofillTree;
        int addChildCount = viewStructure.addChildCount(autofillTree.children.size());
        for (Map.Entry entry : autofillTree.children.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            if (entry.getValue() != null) {
                throw new ClassCastException();
            }
            ViewStructure newChild = viewStructure.newChild(addChildCount);
            if (newChild != null) {
                AutofillId autofillId = viewStructure.getAutofillId();
                Intrinsics.checkNotNull(autofillId);
                newChild.setAutofillId(autofillId, intValue);
                newChild.setId(intValue, androidAutofill.view.getContext().getPackageName(), null, null);
                newChild.setAutofillType(1);
                throw null;
            }
            addChildCount++;
        }
    }

    public final void onRequestMeasure(LayoutNode layoutNode, boolean z, boolean z2, boolean z3) {
        LayoutNode parent$ui_release;
        LayoutNode parent$ui_release2;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate;
        LookaheadAlignmentLines lookaheadAlignmentLines;
        if (!z) {
            if (this.measureAndLayoutDelegate.requestRemeasure(layoutNode, z2) && z3) {
                scheduleMeasureAndLayout(layoutNode);
                return;
            }
            return;
        }
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        measureAndLayoutDelegate.getClass();
        if (layoutNode.lookaheadRoot == null) {
            InlineClassHelperKt.throwIllegalStateException("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope");
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        int ordinal = layoutNodeLayoutDelegate.layoutState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return;
            }
            if (ordinal != 2 && ordinal != 3) {
                if (ordinal != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!layoutNodeLayoutDelegate.lookaheadMeasurePending || z2) {
                    layoutNodeLayoutDelegate.lookaheadMeasurePending = true;
                    layoutNodeLayoutDelegate.measurePending = true;
                    if (layoutNode.isDeactivated) {
                        return;
                    }
                    boolean areEqual = Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE);
                    DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = measureAndLayoutDelegate.relayoutNodes;
                    if ((areEqual || (layoutNodeLayoutDelegate.lookaheadMeasurePending && (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock || !((lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate) == null || (lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines) == null || !lookaheadAlignmentLines.getRequired$ui_release())))) && ((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.layoutDelegate.lookaheadMeasurePending)) {
                        depthSortedSetsForDifferentPasses.add(layoutNode, true);
                    } else if ((layoutNode.isPlaced() || MeasureAndLayoutDelegate.getCanAffectParent(layoutNode)) && ((parent$ui_release2 = layoutNode.getParent$ui_release()) == null || !parent$ui_release2.layoutDelegate.measurePending)) {
                        depthSortedSetsForDifferentPasses.add(layoutNode, false);
                    }
                    if (measureAndLayoutDelegate.duringFullMeasureLayoutPass || !z3) {
                        return;
                    }
                    scheduleMeasureAndLayout(layoutNode);
                    return;
                }
                return;
            }
        }
        measureAndLayoutDelegate.postponedMeasureRequests.add(new MeasureAndLayoutDelegate.PostponedRequest(layoutNode, true, z2));
    }

    public final void onRequestRelayout(LayoutNode layoutNode, boolean z, boolean z2) {
        if (!z) {
            MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
            measureAndLayoutDelegate.getClass();
            int ordinal = layoutNode.layoutDelegate.layoutState.ordinal();
            if (ordinal == 0 || ordinal == 1 || ordinal == 2 || ordinal == 3) {
                return;
            }
            if (ordinal != 4) {
                throw new NoWhenBranchMatchedException();
            }
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
            if (!z2 && layoutNode.isPlaced() == layoutNodeLayoutDelegate.measurePassDelegate.isPlacedByParent && (layoutNodeLayoutDelegate.measurePending || layoutNodeLayoutDelegate.layoutPending)) {
                return;
            }
            layoutNodeLayoutDelegate.layoutPending = true;
            layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
            if (!layoutNode.isDeactivated && layoutNodeLayoutDelegate.measurePassDelegate.isPlacedByParent) {
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                if ((parent$ui_release == null || !parent$ui_release.layoutDelegate.layoutPending) && (parent$ui_release == null || !parent$ui_release.layoutDelegate.measurePending)) {
                    measureAndLayoutDelegate.relayoutNodes.add(layoutNode, false);
                }
                if (measureAndLayoutDelegate.duringFullMeasureLayoutPass) {
                    return;
                }
                scheduleMeasureAndLayout(null);
                return;
            }
            return;
        }
        MeasureAndLayoutDelegate measureAndLayoutDelegate2 = this.measureAndLayoutDelegate;
        measureAndLayoutDelegate2.getClass();
        int ordinal2 = layoutNode.layoutDelegate.layoutState.ordinal();
        if (ordinal2 != 0) {
            if (ordinal2 == 1) {
                return;
            }
            if (ordinal2 != 2) {
                if (ordinal2 == 3) {
                    return;
                }
                if (ordinal2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode.layoutDelegate;
        if ((layoutNodeLayoutDelegate2.lookaheadMeasurePending || layoutNodeLayoutDelegate2.lookaheadLayoutPending) && !z2) {
            return;
        }
        layoutNodeLayoutDelegate2.lookaheadLayoutPending = true;
        layoutNodeLayoutDelegate2.lookaheadLayoutPendingForAlignment = true;
        layoutNodeLayoutDelegate2.layoutPending = true;
        layoutNodeLayoutDelegate2.layoutPendingForAlignment = true;
        if (layoutNode.isDeactivated) {
            return;
        }
        LayoutNode parent$ui_release2 = layoutNode.getParent$ui_release();
        boolean areEqual = Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE);
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = measureAndLayoutDelegate2.relayoutNodes;
        if (areEqual && ((parent$ui_release2 == null || !parent$ui_release2.layoutDelegate.lookaheadMeasurePending) && (parent$ui_release2 == null || !parent$ui_release2.layoutDelegate.lookaheadLayoutPending))) {
            depthSortedSetsForDifferentPasses.add(layoutNode, true);
        } else if (layoutNode.isPlaced() && ((parent$ui_release2 == null || !parent$ui_release2.layoutDelegate.layoutPending) && (parent$ui_release2 == null || !parent$ui_release2.layoutDelegate.measurePending))) {
            depthSortedSetsForDifferentPasses.add(layoutNode, false);
        }
        if (measureAndLayoutDelegate2.duringFullMeasureLayoutPass) {
            return;
        }
        scheduleMeasureAndLayout(null);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume$1() {
        this.showLayoutBounds = Companion.access$getIsShowingLayoutBounds();
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirection = LayoutDirection.Ltr;
            LayoutDirection layoutDirection2 = i != 0 ? i != 1 ? null : LayoutDirection.Rtl : layoutDirection;
            if (layoutDirection2 != null) {
                layoutDirection = layoutDirection2;
            }
            ((SnapshotMutableStateImpl) this.layoutDirection$delegate).setValue(layoutDirection);
        }
    }

    @Override // android.view.View
    public final void onScrollCaptureSearch(android.graphics.Rect rect, Point point, Consumer consumer) {
        ScrollCapture scrollCapture = this.scrollCapture;
        if (scrollCapture != null) {
            scrollCapture.onScrollCaptureSearch(this, this.semanticsOwner, this.coroutineContext, consumer);
        }
    }

    public final void onSemanticsChange() {
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.composeAccessibilityDelegate;
        androidComposeViewAccessibilityDelegateCompat.currentSemanticsNodesInvalidated = true;
        if (androidComposeViewAccessibilityDelegateCompat.isEnabled$ui_release() && !androidComposeViewAccessibilityDelegateCompat.checkingForSemanticsChanges) {
            androidComposeViewAccessibilityDelegateCompat.checkingForSemanticsChanges = true;
            androidComposeViewAccessibilityDelegateCompat.handler.post(androidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker);
        }
        AndroidContentCaptureManager androidContentCaptureManager = this.contentCaptureManager;
        androidContentCaptureManager.currentSemanticsNodesInvalidated = true;
        if (!androidContentCaptureManager.isEnabled$ui_release() || androidContentCaptureManager.checkingForSemanticsChanges) {
            return;
        }
        androidContentCaptureManager.checkingForSemanticsChanges = true;
        androidContentCaptureManager.handler.post(androidContentCaptureManager.contentCaptureChangeChecker);
    }

    @Override // android.view.View
    public final void onVirtualViewTranslationResponses(LongSparseArray longSparseArray) {
        AndroidContentCaptureManager androidContentCaptureManager = this.contentCaptureManager;
        androidContentCaptureManager.getClass();
        AndroidContentCaptureManager.onVirtualViewTranslationResponses$ui_release(androidContentCaptureManager, longSparseArray);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        boolean access$getIsShowingLayoutBounds;
        ((SnapshotMutableStateImpl) this._windowInfo._isWindowFocused).setValue(Boolean.valueOf(z));
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(z);
        if (!z || this.showLayoutBounds == (access$getIsShowingLayoutBounds = Companion.access$getIsShowingLayoutBounds())) {
            return;
        }
        this.showLayoutBounds = access$getIsShowingLayoutBounds;
        invalidateLayers(this.root);
    }

    public final void recalculateWindowPosition() {
        if (this.forceUseMatrixCache) {
            return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (currentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = currentAnimationTimeMillis;
            recalculateWindowViewTransforms();
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            view.getLocationOnScreen(this.tmpPositionArray);
            int[] iArr = this.tmpPositionArray;
            float f = iArr[0];
            float f2 = iArr[1];
            view.getLocationInWindow(iArr);
            float f3 = this.tmpPositionArray[0];
            float f4 = f2 - r0[1];
            this.windowPosition = (Float.floatToRawIntBits(f - f3) << 32) | (Float.floatToRawIntBits(f4) & 4294967295L);
        }
    }

    public final void recalculateWindowViewTransforms() {
        CalculateMatrixToWindowApi29 calculateMatrixToWindowApi29 = this.matrixToWindow;
        float[] fArr = this.viewToWindowMatrix;
        calculateMatrixToWindowApi29.tmpMatrix.reset();
        transformMatrixToGlobal(calculateMatrixToWindowApi29.tmpMatrix);
        ViewParent parent = getParent();
        View view = this;
        while (parent instanceof View) {
            view = (View) parent;
            parent = view.getParent();
        }
        int[] iArr = calculateMatrixToWindowApi29.tmpPosition;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        view.getLocationInWindow(iArr);
        calculateMatrixToWindowApi29.tmpMatrix.postTranslate(iArr[0] - i, iArr[1] - i2);
        AndroidMatrixConversions_androidKt.m344setFromtUYjHk(calculateMatrixToWindowApi29.tmpMatrix, fArr);
        InvertMatrixKt.m565invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
    }

    public final void recycle$ui_release(OwnedLayer ownedLayer) {
        Reference poll;
        MutableVector mutableVector;
        if (this.viewLayersContainer != null) {
            Function2 function2 = ViewLayer.getMatrix;
        }
        WeakCache weakCache = this.layerCache;
        do {
            poll = weakCache.referenceQueue.poll();
            mutableVector = weakCache.values;
            if (poll != null) {
                mutableVector.remove(poll);
            }
        } while (poll != null);
        mutableVector.add(new WeakReference(ownedLayer, weakCache.referenceQueue));
    }

    public final void registerOnEndApplyChangesListener(Function0 function0) {
        if (this.endApplyChangesListeners.contains(function0)) {
            return;
        }
        this.endApplyChangesListeners.add(function0);
    }

    public final void removeAndroidView(final AndroidViewHolder androidViewHolder) {
        registerOnEndApplyChangesListener(new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$removeAndroidView$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidComposeView.this.getAndroidViewsHandler$ui_release().removeViewInLayout(androidViewHolder);
                HashMap hashMap = AndroidComposeView.this.getAndroidViewsHandler$ui_release().layoutNodeToHolder;
                TypeIntrinsics.asMutableMap(hashMap).remove(AndroidComposeView.this.getAndroidViewsHandler$ui_release().holderToLayoutNode.remove(androidViewHolder));
                androidViewHolder.setImportantForAccessibility(0);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i, android.graphics.Rect rect) {
        if (isFocused()) {
            return true;
        }
        if (this.focusOwner.rootFocusNode.getFocusState().getHasFocus()) {
            return super.requestFocus(i, rect);
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(i);
        final int i2 = focusDirection != null ? focusDirection.value : 7;
        Boolean m290focusSearchULY8qGw = this.focusOwner.m290focusSearchULY8qGw(i2, rect != null ? RectHelper_androidKt.toComposeRect(rect) : null, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Boolean m296requestFocusMxy_nc0 = FocusTransactionsKt.m296requestFocusMxy_nc0((FocusTargetNode) obj, i2);
                return Boolean.valueOf(m296requestFocusMxy_nc0 != null ? m296requestFocusMxy_nc0.booleanValue() : false);
            }
        });
        if (m290focusSearchULY8qGw != null) {
            return m290focusSearchULY8qGw.booleanValue();
        }
        return false;
    }

    public final void scheduleMeasureAndLayout(LayoutNode layoutNode) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (layoutNode != null) {
            while (layoutNode != null && layoutNode.layoutDelegate.measurePassDelegate.measuredByParent == LayoutNode.UsageByParent.InMeasureBlock) {
                if (!this.wasMeasuredWithMultipleConstraints) {
                    LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                    if (parent$ui_release == null) {
                        break;
                    }
                    long j = parent$ui_release.nodes.innerCoordinator.measurementConstraints;
                    if (Constraints.m653getHasFixedWidthimpl(j) && Constraints.m652getHasFixedHeightimpl(j)) {
                        break;
                    }
                }
                layoutNode = layoutNode.getParent$ui_release();
            }
            if (layoutNode == this.root) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    /* renamed from: screenToLocal-MK-Hz9U, reason: not valid java name */
    public final long m557screenToLocalMKHz9U(long j) {
        recalculateWindowPosition();
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (this.windowPosition >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L)) - Float.intBitsToFloat((int) (this.windowPosition & 4294967295L));
        return Matrix.m380mapMKHz9U((Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat) << 32), this.windowToViewMatrix);
    }

    /* renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    public final int m558sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        Object obj;
        int i = 0;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            WindowInfoImpl windowInfoImpl = this._windowInfo;
            int metaState = motionEvent.getMetaState();
            windowInfoImpl.getClass();
            ((SnapshotMutableStateImpl) WindowInfoImpl.GlobalKeyboardModifiers).setValue(new PointerKeyboardModifiers(metaState));
        }
        PointerInputEvent convertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEvent, this);
        if (convertToPointerInputEvent$ui_release != null) {
            ArrayList arrayList = (ArrayList) convertToPointerInputEvent$ui_release.pointers;
            int size = arrayList.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i2 = size - 1;
                    obj = arrayList.get(size);
                    if (((PointerInputEventData) obj).down) {
                        break;
                    }
                    if (i2 < 0) {
                        break;
                    }
                    size = i2;
                }
            }
            obj = null;
            PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
            if (pointerInputEventData != null) {
                this.lastDownPointerPosition = pointerInputEventData.position;
            }
            i = this.pointerInputEventProcessor.m465processBIzXfog(convertToPointerInputEvent$ui_release, this, isInBounds(motionEvent));
            int actionMasked = motionEvent.getActionMasked();
            if ((actionMasked == 0 || actionMasked == 5) && (i & 1) == 0) {
                MotionEventAdapter motionEventAdapter = this.motionEventAdapter;
                int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                motionEventAdapter.activeHoverIds.delete(pointerId);
                motionEventAdapter.motionEventToComposePointerIdMap.delete(pointerId);
            }
        } else {
            this.pointerInputEventProcessor.processCancel();
        }
        return i;
    }

    public final void sendSimulatedEvent(MotionEvent motionEvent, int i, long j, boolean z) {
        int actionMasked = motionEvent.getActionMasked();
        int i2 = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                i2 = motionEvent.getActionIndex();
            }
        } else if (i != 9 && i != 10) {
            i2 = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (i2 >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i3 = 0; i3 < pointerCount; i3++) {
            pointerPropertiesArr[i3] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i4 = 0; i4 < pointerCount; i4++) {
            pointerCoordsArr[i4] = new MotionEvent.PointerCoords();
        }
        int i5 = 0;
        while (i5 < pointerCount) {
            int i6 = ((i2 < 0 || i5 < i2) ? 0 : 1) + i5;
            motionEvent.getPointerProperties(i6, pointerPropertiesArr[i5]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i5];
            motionEvent.getPointerCoords(i6, pointerCoords);
            float f = pointerCoords.x;
            long m555localToScreenMKHz9U = m555localToScreenMKHz9U((Float.floatToRawIntBits(pointerCoords.y) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
            pointerCoords.x = Float.intBitsToFloat((int) (m555localToScreenMKHz9U >> 32));
            pointerCoords.y = Float.intBitsToFloat((int) (m555localToScreenMKHz9U & 4294967295L));
            i5++;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? j : motionEvent.getDownTime(), j, i, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), z ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        PointerInputEvent convertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(obtain, this);
        Intrinsics.checkNotNull(convertToPointerInputEvent$ui_release);
        this.pointerInputEventProcessor.m465processBIzXfog(convertToPointerInputEvent$ui_release, this, true);
        obtain.recycle();
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void textInputSession(kotlin.jvm.functions.Function2 r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.ui.platform.AndroidComposeView$textInputSession$1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$1 r0 = (androidx.compose.ui.platform.AndroidComposeView$textInputSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$1 r0 = new androidx.compose.ui.platform.AndroidComposeView$textInputSession$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 == r3) goto L2b
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2b:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L2f:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.concurrent.atomic.AtomicReference r6 = r4.textInputSessionMutex
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$2 r2 = new androidx.compose.ui.platform.AndroidComposeView$textInputSession$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r4 = androidx.compose.ui.SessionMutex.m276withSessionCancellingPreviousimpl(r6, r2, r5, r0)
            if (r4 != r1) goto L42
            return
        L42:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.textInputSession(kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }

    public final void updatePositionCacheAndDispatch() {
        getLocationOnScreen(this.tmpPositionArray);
        long j = this.globalPosition;
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        int[] iArr = this.tmpPositionArray;
        boolean z = false;
        int i3 = iArr[0];
        if (i != i3 || i2 != iArr[1]) {
            this.globalPosition = (iArr[1] & 4294967295L) | (i3 << 32);
            if (i != Integer.MAX_VALUE && i2 != Integer.MAX_VALUE) {
                this.root.layoutDelegate.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i) {
        Intrinsics.checkNotNull(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addViewInLayout(view, i, layoutParams, true);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, int i2) {
        ViewGroup.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.width = i;
        generateDefaultLayoutParams.height = i2;
        addViewInLayout(view, -1, generateDefaultLayoutParams, true);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, i, layoutParams, true);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, -1, layoutParams, true);
    }

    public final void recalculateWindowPosition(MotionEvent motionEvent) {
        this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
        recalculateWindowViewTransforms();
        float[] fArr = this.viewToWindowMatrix;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        long m380mapMKHz9U = Matrix.m380mapMKHz9U((Float.floatToRawIntBits(y) & 4294967295L) | (Float.floatToRawIntBits(x) << 32), fArr);
        float rawX = motionEvent.getRawX() - Float.intBitsToFloat((int) (m380mapMKHz9U >> 32));
        float rawY = motionEvent.getRawY() - Float.intBitsToFloat((int) (m380mapMKHz9U & 4294967295L));
        this.windowPosition = (Float.floatToRawIntBits(rawX) << 32) | (Float.floatToRawIntBits(rawY) & 4294967295L);
    }
}
