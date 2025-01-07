package com.android.wm.shell.bubbles;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.LocusId;
import android.content.pm.ActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import android.util.Pair;
import android.util.Property;
import android.util.Slog;
import android.util.SparseArray;
import android.view.IWindowManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.statusbar.IStatusBarService;
import com.android.launcher3.icons.BubbleIconFactory;
import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda1;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda2;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda9;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.WindowManagerShellWrapper;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleData;
import com.android.wm.shell.bubbles.BubbleLogger;
import com.android.wm.shell.bubbles.BubbleOverflowContainerView;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda25;
import com.android.wm.shell.bubbles.BubbleViewInfoTask;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix;
import com.android.wm.shell.bubbles.animation.ExpandedAnimationController;
import com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;
import com.android.wm.shell.bubbles.animation.StackAnimationController;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedViewDragController;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.bubbles.properties.ProdBubbleProperties;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.onehanded.OneHandedTransitionCallback;
import com.android.wm.shell.pip.PinnedStackListenerForwarder;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;
import com.android.wm.shell.shared.bubbles.BubbleBarUpdate;
import com.android.wm.shell.sysui.ConfigurationChangeListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewTransitions;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleController implements ConfigurationChangeListener, RemoteCallable {
    public boolean mAddedToWindowManager;
    public final ShellExecutor mBackgroundExecutor;
    public final IStatusBarService mBarService;
    public final AnonymousClass6 mBroadcastReceiver;
    public final AnonymousClass8 mBubbleBarViewCallback;
    public final BubbleData mBubbleData;
    public final AnonymousClass8 mBubbleDataListener;
    public BubbleIconFactory mBubbleIconFactory;
    public final BubblePositioner mBubblePositioner;
    public final ProdBubbleProperties mBubbleProperties;
    public final AnonymousClass8 mBubbleStackViewCallback;
    public AnonymousClass8 mBubbleStateListener;
    public final AnonymousClass1 mBubbleTaskViewFactory;
    public AnonymousClass8 mBubbleViewCallback;
    public final Context mContext;
    public SparseArray mCurrentProfiles;
    public int mCurrentUserId;
    public final BubbleDataRepository mDataRepository;
    public int mDensityDpi;
    public final DisplayController mDisplayController;
    public final DragAndDropController mDragAndDropController;
    public BubbleController$$ExternalSyntheticLambda2 mExpandListener;
    public final BubbleExpandedViewManager$Companion$fromBubbleController$1 mExpandedViewManager;
    public final FloatingContentCoordinator mFloatingContentCoordinator;
    public float mFontScale;
    public final BubblesImpl mImpl;
    public boolean mInflateSynchronously;
    public boolean mIsStatusBarShade;
    public final LauncherApps mLauncherApps;
    public BubbleBarLayerView mLayerView;
    public int mLayoutDirection;
    public Locale mLocale;
    public final BubbleLogger mLogger;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public BubbleEntry mNotifEntryToExpandOnShadeUnlock;
    public final Optional mOneHandedOptional;
    public boolean mOverflowDataLoadNeeded;
    public BubbleOverflowContainerView.AnonymousClass1 mOverflowListener;
    public final SparseArray mSavedUserBubbleData;
    public final Rect mScreenBounds;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final AnonymousClass6 mShortcutBroadcastReceiver;
    public BubbleStackView mStackView;
    public BubblesManager.AnonymousClass5 mSysuiProxy;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final TaskStackListenerImpl mTaskStackListener;
    public NotificationListenerService.Ranking mTmpRanking;
    public final Transitions mTransitions;
    public final UserManager mUserManager;
    public WindowInsets mWindowInsets;
    public final WindowManager mWindowManager;
    public final WindowManagerShellWrapper mWindowManagerShellWrapper;
    public WindowManager.LayoutParams mWmLayoutParams;
    public final IWindowManager mWmService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleController$1, reason: invalid class name */
    public final class AnonymousClass1 implements BubbleTaskViewFactory {
        public final /* synthetic */ Context val$context;
        public final /* synthetic */ ShellExecutor val$mainExecutor;
        public final /* synthetic */ ShellTaskOrganizer val$organizer;
        public final /* synthetic */ SyncTransactionQueue val$syncQueue;
        public final /* synthetic */ TaskViewTransitions val$taskViewTransitions;

        public AnonymousClass1(Context context, ShellTaskOrganizer shellTaskOrganizer, TaskViewTransitions taskViewTransitions, SyncTransactionQueue syncTransactionQueue, ShellExecutor shellExecutor) {
            this.val$context = context;
            this.val$organizer = shellTaskOrganizer;
            this.val$taskViewTransitions = taskViewTransitions;
            this.val$syncQueue = syncTransactionQueue;
            this.val$mainExecutor = shellExecutor;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleController$2, reason: invalid class name */
    public final class AnonymousClass2 implements OneHandedTransitionCallback {
        public AnonymousClass2() {
        }

        @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
        public final void onStartFinished(Rect rect) {
            ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$2$$ExternalSyntheticLambda0(this, rect, 1));
        }

        @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
        public final void onStopFinished(Rect rect) {
            ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$2$$ExternalSyntheticLambda0(this, rect, 0));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleController$6, reason: invalid class name */
    public final class AnonymousClass6 extends BroadcastReceiver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ BubbleController this$0;

        public /* synthetic */ AnonymousClass6(BubbleController bubbleController, int i) {
            this.$r8$classId = i;
            this.this$0 = bubbleController;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            switch (this.$r8$classId) {
                case 0:
                    if (this.this$0.mBubbleData.mExpanded) {
                        String action = intent.getAction();
                        String stringExtra = intent.getStringExtra("reason");
                        boolean z = "recentapps".equals(stringExtra) || "homekey".equals(stringExtra) || "gestureNav".equals(stringExtra);
                        if (("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) && z) || "android.intent.action.SCREEN_OFF".equals(action)) {
                            ((HandlerExecutor) this.this$0.mMainExecutor).execute(new BubbleController$6$$ExternalSyntheticLambda0(0, this));
                            break;
                        }
                    }
                    break;
                default:
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, -925670237100425792L, 0, String.valueOf(intent.getAction()));
                    }
                    if ("com.android.wm.shell.bubbles.action.SHOW_BUBBLES".equals(intent.getAction())) {
                        ((HandlerExecutor) this.this$0.mMainExecutor).execute(new BubbleController$6$$ExternalSyntheticLambda0(this));
                        break;
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BubblesImeListener extends PinnedStackListenerForwarder.PinnedTaskListener {
        public BubblesImeListener() {
        }

        @Override // com.android.wm.shell.pip.PinnedStackListenerForwarder.PinnedTaskListener
        public final void onImeVisibilityChanged(boolean z, int i) {
            float f;
            BubbleController bubbleController = BubbleController.this;
            BubblePositioner bubblePositioner = bubbleController.mBubblePositioner;
            bubblePositioner.mImeVisible = z;
            bubblePositioner.mImeHeight = i;
            BubbleStackView bubbleStackView = bubbleController.mStackView;
            if (bubbleStackView != null) {
                if ((bubbleStackView.mIsExpansionAnimating || bubbleStackView.mIsBubbleSwitchAnimating) && bubbleStackView.mIsExpanded) {
                    ExpandedAnimationController expandedAnimationController = bubbleStackView.mExpandedAnimationController;
                    BubbleStackView$$ExternalSyntheticLambda3 bubbleStackView$$ExternalSyntheticLambda3 = new BubbleStackView$$ExternalSyntheticLambda3(bubbleStackView, z, 2);
                    expandedAnimationController.mPreparingToCollapse = false;
                    expandedAnimationController.mAnimatingCollapse = false;
                    expandedAnimationController.mAnimatingExpand = true;
                    expandedAnimationController.mAfterExpand = bubbleStackView$$ExternalSyntheticLambda3;
                    expandedAnimationController.startOrUpdatePathAnimation(true);
                    return;
                }
                if (!bubbleStackView.mIsExpanded && bubbleStackView.getBubbleCount() > 0) {
                    StackAnimationController stackAnimationController = bubbleStackView.mStackAnimationController;
                    float f2 = stackAnimationController.mPositioner.getAllowableStackPositionRegion(stackAnimationController.mBubbleCountSupplier.f$0.getBubbleCount()).bottom;
                    if (z) {
                        float f3 = stackAnimationController.mStackPosition.y;
                        if (f3 > f2 && stackAnimationController.mPreImeY == -1.4E-45f) {
                            stackAnimationController.mPreImeY = f3;
                            f = f2;
                        }
                        f = -1.4E-45f;
                    } else {
                        f2 = stackAnimationController.mPreImeY;
                        if (f2 != -1.4E-45f) {
                            stackAnimationController.mPreImeY = -1.4E-45f;
                            f = f2;
                        }
                        f = -1.4E-45f;
                    }
                    DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                    if (f != -1.4E-45f) {
                        SpringForce springForce = stackAnimationController.getSpringForce(null);
                        springForce.setStiffness(200.0f);
                        stackAnimationController.springFirstBubbleWithStackFollowing(anonymousClass1, springForce, 0.0f, f, new Runnable[0]);
                        stackAnimationController.notifyFloatingCoordinatorStackAnimatingTo(stackAnimationController.mStackPosition.x, f);
                    }
                    if (f == -1.4E-45f) {
                        f = stackAnimationController.mStackPosition.y;
                    }
                    float f4 = f - bubbleStackView.mStackAnimationController.mStackPosition.y;
                    if (bubbleStackView.mFlyout.getVisibility() == 0) {
                        BubbleFlyoutView bubbleFlyoutView = bubbleStackView.mFlyout;
                        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                        PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(bubbleFlyoutView);
                        companion.spring(anonymousClass1, bubbleStackView.mFlyout.getTranslationY() + f4, 0.0f, BubbleStackView.FLYOUT_IME_ANIMATION_SPRING_CONFIG);
                        companion.start();
                    }
                }
                if (bubbleStackView.mIsExpanded) {
                    bubbleStackView.mExpandedViewAnimationController.animateForImeVisibilityChange(z);
                    BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                    if (!bubbleStackView.mPositioner.showBubblesVertically() || expandedView == null) {
                        return;
                    }
                    float expandedViewY = bubbleStackView.mPositioner.getExpandedViewY(bubbleStackView.mExpandedBubble, bubbleStackView.mPositioner.getExpandedBubbleXY(bubbleStackView.getState().selectedIndex, bubbleStackView.getState()).y);
                    expandedView.mImeVisible = z;
                    if (!z && expandedView.mNeedsNewHeight) {
                        expandedView.updateHeight();
                    }
                    if (!expandedView.mUsingMaxHeight) {
                        bubbleStackView.mExpandedViewContainer.animate().translationY(expandedViewY);
                    }
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < bubbleStackView.mBubbleContainer.getChildCount(); i2++) {
                        arrayList.add(ObjectAnimator.ofFloat(bubbleStackView.mBubbleContainer.getChildAt(i2), (Property<View, Float>) FrameLayout.TRANSLATION_Y, bubbleStackView.mPositioner.getExpandedBubbleXY(i2, bubbleStackView.getState()).y));
                    }
                    bubbleStackView.updatePointerPosition(true);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(arrayList);
                    animatorSet.start();
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BubblesImpl implements Bubbles {
        public final CachedState mCachedState = new CachedState();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public class CachedState {
            public boolean mIsStackExpanded;
            public String mSelectedBubbleKey;
            public final HashSet mSuppressedBubbleKeys = new HashSet();
            public final HashMap mSuppressedGroupToNotifKeys = new HashMap();
            public final HashMap mShortcutIdToBubble = new HashMap();
            public final HashMap mAppBubbleTaskIds = new HashMap();
            public final ArrayList mTmpBubbles = new ArrayList();

            public CachedState() {
            }

            public final synchronized void updateBubbleSuppressedState(Bubble bubble) {
                try {
                    if (bubble.showInShade()) {
                        this.mSuppressedBubbleKeys.remove(bubble.mKey);
                    } else {
                        this.mSuppressedBubbleKeys.add(bubble.mKey);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public BubblesImpl() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
        
            if (r2.equals(r1.mSuppressedGroupToNotifKeys.get(r3)) != false) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean isBubbleNotificationSuppressedFromShade(java.lang.String r2, java.lang.String r3) {
            /*
                r1 = this;
                com.android.wm.shell.bubbles.BubbleController$BubblesImpl$CachedState r1 = r1.mCachedState
                monitor-enter(r1)
                java.util.HashSet r0 = r1.mSuppressedBubbleKeys     // Catch: java.lang.Throwable -> L20
                boolean r0 = r0.contains(r2)     // Catch: java.lang.Throwable -> L20
                if (r0 != 0) goto L24
                java.util.HashMap r0 = r1.mSuppressedGroupToNotifKeys     // Catch: java.lang.Throwable -> L20
                boolean r0 = r0.containsKey(r3)     // Catch: java.lang.Throwable -> L20
                if (r0 == 0) goto L22
                java.util.HashMap r0 = r1.mSuppressedGroupToNotifKeys     // Catch: java.lang.Throwable -> L20
                java.lang.Object r3 = r0.get(r3)     // Catch: java.lang.Throwable -> L20
                boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> L20
                if (r2 == 0) goto L22
                goto L24
            L20:
                r2 = move-exception
                goto L27
            L22:
                r2 = 0
                goto L25
            L24:
                r2 = 1
            L25:
                monitor-exit(r1)
                return r2
            L27:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L20
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleController.BubblesImpl.isBubbleNotificationSuppressedFromShade(java.lang.String, java.lang.String):boolean");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserBubbleData {
        public final Map mKeyToShownInShadeMap = new HashMap();
    }

    public BubbleController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, BubbleData bubbleData, FloatingContentCoordinator floatingContentCoordinator, BubbleDataRepository bubbleDataRepository, IStatusBarService iStatusBarService, WindowManager windowManager, WindowManagerShellWrapper windowManagerShellWrapper, UserManager userManager, LauncherApps launcherApps, BubbleLogger bubbleLogger, TaskStackListenerImpl taskStackListenerImpl, ShellTaskOrganizer shellTaskOrganizer, BubblePositioner bubblePositioner, DisplayController displayController, Optional optional, DragAndDropController dragAndDropController, ShellExecutor shellExecutor, Handler handler, ShellExecutor shellExecutor2, TaskViewTransitions taskViewTransitions, Transitions transitions, SyncTransactionQueue syncTransactionQueue, IWindowManager iWindowManager) {
        ProdBubbleProperties prodBubbleProperties = ProdBubbleProperties.INSTANCE;
        this.mImpl = new BubblesImpl();
        this.mOverflowListener = null;
        int i = 1;
        this.mOverflowDataLoadNeeded = true;
        int i2 = 0;
        this.mAddedToWindowManager = false;
        this.mDensityDpi = 0;
        this.mScreenBounds = new Rect();
        this.mFontScale = 0.0f;
        this.mLocale = null;
        this.mLayoutDirection = -1;
        this.mIsStatusBarShade = true;
        this.mBroadcastReceiver = new AnonymousClass6(this, i2);
        new AnonymousClass6(this, i);
        this.mBubbleStackViewCallback = new AnonymousClass8(i2, this);
        this.mBubbleBarViewCallback = new AnonymousClass8(2, this);
        this.mBubbleDataListener = new AnonymousClass8(i, this);
        this.mContext = context;
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellController = shellController;
        this.mLauncherApps = launcherApps;
        this.mBarService = iStatusBarService == null ? IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar")) : iStatusBarService;
        this.mWindowManager = windowManager;
        this.mWindowManagerShellWrapper = windowManagerShellWrapper;
        this.mUserManager = userManager;
        this.mFloatingContentCoordinator = floatingContentCoordinator;
        this.mDataRepository = bubbleDataRepository;
        this.mLogger = bubbleLogger;
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mBackgroundExecutor = shellExecutor2;
        this.mTaskStackListener = taskStackListenerImpl;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mCurrentUserId = ActivityManager.getCurrentUser();
        this.mBubblePositioner = bubblePositioner;
        this.mBubbleData = bubbleData;
        this.mSavedUserBubbleData = new SparseArray();
        this.mBubbleIconFactory = new BubbleIconFactory(context, context.getResources().getDimensionPixelSize(R.dimen.bubble_size), context.getResources().getDimensionPixelSize(R.dimen.bubble_badge_size), context.getResources().getColor(R.color.important_conversation), context.getResources().getDimensionPixelSize(android.R.dimen.indeterminate_progress_alpha_08));
        this.mDisplayController = displayController;
        this.mTransitions = transitions;
        this.mOneHandedOptional = optional;
        this.mDragAndDropController = dragAndDropController;
        this.mWmService = iWindowManager;
        this.mBubbleProperties = prodBubbleProperties;
        shellInit.addInitCallback(new BubbleController$$ExternalSyntheticLambda3(this, i2), this);
        this.mBubbleTaskViewFactory = new AnonymousClass1(context, shellTaskOrganizer, taskViewTransitions, syncTransactionQueue, shellExecutor);
        this.mExpandedViewManager = new BubbleExpandedViewManager$Companion$fromBubbleController$1(this);
    }

    public static boolean canLaunchInTaskView(Context context, BubbleEntry bubbleEntry) {
        PendingIntent intent = bubbleEntry.getBubbleMetadata() != null ? bubbleEntry.getBubbleMetadata().getIntent() : null;
        if (bubbleEntry.getBubbleMetadata() != null && bubbleEntry.getBubbleMetadata().getShortcutId() != null) {
            return true;
        }
        if (intent != null) {
            return isResizableActivity(intent.getIntent(), getPackageManagerForUser(bubbleEntry.mSbn.getUser().getIdentifier(), context), bubbleEntry.mSbn.getKey());
        }
        Log.w("Bubbles", "Unable to create bubble -- no intent: " + bubbleEntry.mSbn.getKey());
        return false;
    }

    public static PackageManager getPackageManagerForUser(int i, Context context) {
        if (i >= 0) {
            try {
                context = context.createPackageContextAsUser(context.getPackageName(), 4, new UserHandle(i));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return context.getPackageManager();
    }

    public static boolean isResizableActivity(Intent intent, PackageManager packageManager, String str) {
        if (intent == null) {
            Log.w("Bubbles", "Unable to send as bubble: " + str + " null intent");
            return false;
        }
        ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(packageManager, 0);
        if (resolveActivityInfo == null) {
            Log.w("Bubbles", "Unable to send as bubble: " + str + " couldn't find activity info for intent: " + intent);
            return false;
        }
        if (ActivityInfo.isResizeableMode(resolveActivityInfo.resizeMode)) {
            return true;
        }
        Log.w("Bubbles", "Unable to send as bubble: " + str + " activity is not resizable for intent: " + intent);
        return false;
    }

    public Bubbles asBubbles() {
        return this.mImpl;
    }

    public final boolean canShowAsBubbleBar() {
        this.mBubbleProperties.getClass();
        return ProdBubbleProperties._isBubbleBarEnabled && this.mBubblePositioner.mDeviceConfig.isLargeScreen;
    }

    public final void collapseStack() {
        this.mBubbleData.setExpanded(false);
    }

    public final void ensureBubbleViewsAndWindowCreated() {
        final int i = 1;
        final int i2 = 0;
        this.mBubblePositioner.mShowingInBubbleBar = isShowingAsBubbleBar();
        boolean isShowingAsBubbleBar = isShowingAsBubbleBar();
        BubbleData bubbleData = this.mBubbleData;
        if (isShowingAsBubbleBar) {
            if (this.mLayerView == null) {
                BubbleBarLayerView bubbleBarLayerView = new BubbleBarLayerView(this.mContext, this, bubbleData);
                this.mLayerView = bubbleBarLayerView;
                BubblesManager.AnonymousClass5 anonymousClass5 = this.mSysuiProxy;
                Objects.requireNonNull(anonymousClass5);
                bubbleBarLayerView.mUnBubbleConversationCallback = new BubbleController$$ExternalSyntheticLambda14(i2, anonymousClass5);
            }
        } else if (this.mStackView == null) {
            BubbleStackView bubbleStackView = new BubbleStackView(this.mContext, new BubbleStackViewManager$Companion$fromBubbleController$1(this), this.mBubblePositioner, this.mBubbleData, this.mFloatingContentCoordinator, this, this.mMainExecutor);
            this.mStackView = bubbleStackView;
            bubbleStackView.onOrientationChanged();
            BubbleController$$ExternalSyntheticLambda2 bubbleController$$ExternalSyntheticLambda2 = this.mExpandListener;
            if (bubbleController$$ExternalSyntheticLambda2 != null) {
                this.mStackView.mExpandListener = bubbleController$$ExternalSyntheticLambda2;
            }
            BubbleStackView bubbleStackView2 = this.mStackView;
            BubblesManager.AnonymousClass5 anonymousClass52 = this.mSysuiProxy;
            Objects.requireNonNull(anonymousClass52);
            bubbleStackView2.mUnbubbleConversationCallback = new BubbleController$$ExternalSyntheticLambda14(i2, anonymousClass52);
        }
        if (this.mAddedToWindowManager) {
            return;
        }
        if (isShowingAsBubbleBar() && this.mLayerView == null) {
            return;
        }
        if (isShowingAsBubbleBar() || this.mStackView != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2038, 16777256, -3);
            this.mWmLayoutParams = layoutParams;
            layoutParams.setTrustedOverlay();
            this.mWmLayoutParams.setFitInsetsTypes(0);
            WindowManager.LayoutParams layoutParams2 = this.mWmLayoutParams;
            layoutParams2.softInputMode = 16;
            layoutParams2.token = new Binder();
            this.mWmLayoutParams.setTitle("Bubbles!");
            this.mWmLayoutParams.packageName = this.mContext.getPackageName();
            WindowManager.LayoutParams layoutParams3 = this.mWmLayoutParams;
            layoutParams3.layoutInDisplayCutoutMode = 3;
            layoutParams3.privateFlags = 16 | layoutParams3.privateFlags;
            try {
                this.mAddedToWindowManager = true;
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter, 2);
                boolean isShowingAsBubbleBar2 = isShowingAsBubbleBar();
                BubbleExpandedViewManager$Companion$fromBubbleController$1 bubbleExpandedViewManager$Companion$fromBubbleController$1 = this.mExpandedViewManager;
                if (isShowingAsBubbleBar2) {
                    BubbleOverflow bubbleOverflow = bubbleData.mOverflow;
                    BubblePositioner bubblePositioner = this.mBubblePositioner;
                    BubbleBarExpandedView bubbleBarExpandedView = (BubbleBarExpandedView) bubbleOverflow.inflater.inflate(R.layout.bubble_bar_expanded_view, (ViewGroup) null, false);
                    bubbleBarExpandedView.applyThemeAttrs();
                    bubbleOverflow.bubbleBarExpandedView = bubbleBarExpandedView;
                    bubbleBarExpandedView.initialize(bubbleExpandedViewManager$Companion$fromBubbleController$1, bubblePositioner, true, null, null, null, null);
                } else {
                    BubbleOverflow bubbleOverflow2 = bubbleData.mOverflow;
                    BubbleStackView bubbleStackView3 = this.mStackView;
                    BubblePositioner bubblePositioner2 = this.mBubblePositioner;
                    BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) bubbleOverflow2.inflater.inflate(R.layout.bubble_expanded_view, (ViewGroup) null, false);
                    bubbleExpandedView.applyThemeAttrs();
                    bubbleOverflow2.expandedView = bubbleExpandedView;
                    bubbleOverflow2.updateResources();
                    bubbleExpandedView.initialize(bubbleExpandedViewManager$Companion$fromBubbleController$1, bubbleStackView3, bubblePositioner2, true, null);
                }
                if (isShowingAsBubbleBar()) {
                    this.mWindowManager.addView(this.mLayerView, this.mWmLayoutParams);
                    this.mLayerView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener(this) { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda17
                        public final /* synthetic */ BubbleController f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // android.view.View.OnApplyWindowInsetsListener
                        public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                            int i3 = i2;
                            BubbleController bubbleController = this.f$0;
                            switch (i3) {
                                case 0:
                                    if (!windowInsets.equals(bubbleController.mWindowInsets) && bubbleController.mLayerView != null) {
                                        bubbleController.mWindowInsets = windowInsets;
                                        bubbleController.mBubblePositioner.update(DeviceConfig.create(bubbleController.mContext, bubbleController.mWindowManager));
                                        BubbleBarLayerView bubbleBarLayerView2 = bubbleController.mLayerView;
                                        if (bubbleBarLayerView2.mIsExpanded && bubbleBarLayerView2.mExpandedView != null) {
                                            bubbleBarLayerView2.updateExpandedView();
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    if (!windowInsets.equals(bubbleController.mWindowInsets) && bubbleController.mStackView != null) {
                                        bubbleController.mWindowInsets = windowInsets;
                                        bubbleController.mBubblePositioner.update(DeviceConfig.create(bubbleController.mContext, bubbleController.mWindowManager));
                                        bubbleController.mStackView.onDisplaySizeChanged();
                                        break;
                                    }
                                    break;
                            }
                            return windowInsets;
                        }
                    });
                } else {
                    this.mWindowManager.addView(this.mStackView, this.mWmLayoutParams);
                    this.mStackView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener(this) { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda17
                        public final /* synthetic */ BubbleController f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // android.view.View.OnApplyWindowInsetsListener
                        public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                            int i3 = i;
                            BubbleController bubbleController = this.f$0;
                            switch (i3) {
                                case 0:
                                    if (!windowInsets.equals(bubbleController.mWindowInsets) && bubbleController.mLayerView != null) {
                                        bubbleController.mWindowInsets = windowInsets;
                                        bubbleController.mBubblePositioner.update(DeviceConfig.create(bubbleController.mContext, bubbleController.mWindowManager));
                                        BubbleBarLayerView bubbleBarLayerView2 = bubbleController.mLayerView;
                                        if (bubbleBarLayerView2.mIsExpanded && bubbleBarLayerView2.mExpandedView != null) {
                                            bubbleBarLayerView2.updateExpandedView();
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    if (!windowInsets.equals(bubbleController.mWindowInsets) && bubbleController.mStackView != null) {
                                        bubbleController.mWindowInsets = windowInsets;
                                        bubbleController.mBubblePositioner.update(DeviceConfig.create(bubbleController.mContext, bubbleController.mWindowManager));
                                        bubbleController.mStackView.onDisplaySizeChanged();
                                        break;
                                    }
                                    break;
                            }
                            return windowInsets;
                        }
                    });
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public final void expandStackAndSelectBubble(BubbleEntry bubbleEntry) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -2934966420708968428L, 12, String.valueOf(bubbleEntry.mSbn.getKey()), Boolean.valueOf(this.mIsStatusBarShade));
        }
        if (!this.mIsStatusBarShade) {
            this.mNotifEntryToExpandOnShadeUnlock = bubbleEntry;
            return;
        }
        this.mNotifEntryToExpandOnShadeUnlock = null;
        String key = bubbleEntry.mSbn.getKey();
        BubbleData bubbleData = this.mBubbleData;
        Bubble bubbleInStackWithKey = bubbleData.getBubbleInStackWithKey(key);
        if (bubbleInStackWithKey != null) {
            bubbleData.setSelectedBubbleAndExpandStack(bubbleInStackWithKey);
            return;
        }
        Bubble overflowBubbleWithKey = bubbleData.getOverflowBubbleWithKey(key);
        if (overflowBubbleWithKey != null) {
            promoteBubbleFromOverflow(overflowBubbleWithKey);
        } else if (bubbleEntry.mRanking.canBubble()) {
            setIsBubble(bubbleEntry, true, true);
        }
    }

    public void expandStackAndSelectBubbleFromLauncher(String str, int i) {
        this.mBubblePositioner.mBubbleBarTopOnScreen = i;
        boolean equals = "Overflow".equals(str);
        BubbleData bubbleData = this.mBubbleData;
        if (equals) {
            bubbleData.setSelectedBubbleFromLauncher(bubbleData.mOverflow);
            this.mLayerView.showExpandedView(bubbleData.mOverflow);
            return;
        }
        Bubble anyBubbleWithkey = bubbleData.getAnyBubbleWithkey(str);
        if (anyBubbleWithkey == null) {
            return;
        }
        String str2 = anyBubbleWithkey.mKey;
        if (bubbleData.hasBubbleInStackWithKey(str2)) {
            bubbleData.setSelectedBubbleFromLauncher(anyBubbleWithkey);
            this.mLayerView.showExpandedView(anyBubbleWithkey);
        } else {
            if (bubbleData.hasOverflowBubbleWithKey(str2)) {
                return;
            }
            Log.w("Bubbles", "didn't add bubble from launcher: " + str);
        }
    }

    public final ArrayList getBubblesInGroup(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        for (Bubble bubble : Collections.unmodifiableList(this.mBubbleData.mBubbles)) {
            String str2 = bubble.mGroupKey;
            if (str2 != null && str.equals(str2)) {
                arrayList.add(bubble);
            }
        }
        return arrayList;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    public BubblesImpl.CachedState getImplCachedState() {
        return this.mImpl.mCachedState;
    }

    public BubbleBarLayerView getLayerView() {
        return this.mLayerView;
    }

    public BubblePositioner getPositioner() {
        return this.mBubblePositioner;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public BubbleStackView getStackView() {
        return this.mStackView;
    }

    public boolean hasBubbles() {
        if (this.mStackView == null && this.mLayerView == null) {
            return false;
        }
        BubbleData bubbleData = this.mBubbleData;
        return !bubbleData.mBubbles.isEmpty() || (bubbleData.mShowingOverflow && bubbleData.mExpanded);
    }

    public final void hideCurrentInputMethod() {
        BubblePositioner bubblePositioner = this.mBubblePositioner;
        bubblePositioner.mImeVisible = false;
        bubblePositioner.mImeHeight = 0;
        try {
            this.mBarService.hideCurrentInputMethodForBubbles(this.mWindowManager.getDefaultDisplay().getDisplayId());
        } catch (RemoteException e) {
            Log.e("Bubbles", "Failed to hide IME", e);
        }
    }

    public void inflateAndAdd(Bubble bubble, final boolean z, final boolean z2) {
        ensureBubbleViewsAndWindowCreated();
        bubble.setInflateSynchronously(this.mInflateSynchronously);
        BubbleViewInfoTask.Callback callback = new BubbleViewInfoTask.Callback() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda4
            @Override // com.android.wm.shell.bubbles.BubbleViewInfoTask.Callback
            public final void onBubbleViewsReady(Bubble bubble2) {
                BubbleData bubbleData = BubbleController.this.mBubbleData;
                bubbleData.mPendingBubbles.remove(bubble2.mKey);
                String str = bubble2.mKey;
                Bubble bubbleInStackWithKey = bubbleData.getBubbleInStackWithKey(str);
                boolean z3 = (!bubble2.mIsTextChanged) | z;
                boolean z4 = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
                boolean z5 = z2;
                if (z4) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 7240892502206002960L, 1020, String.valueOf(str), Boolean.valueOf(bubbleInStackWithKey != null), Boolean.valueOf(z3), Boolean.valueOf(z5), Boolean.valueOf(bubble2.isEnabled(1)));
                }
                if (bubbleInStackWithKey == null) {
                    bubble2.mSuppressFlyout = z3;
                    ((BubbleData$$ExternalSyntheticLambda2) bubbleData.mTimeSource).getClass();
                    bubble2.mLastUpdated = System.currentTimeMillis();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 3174465872965350544L, 0, String.valueOf(str));
                    }
                    bubbleData.mBubbles.add(0, bubble2);
                    BubbleData.Update update = bubbleData.mStateChange;
                    update.addedBubble = bubble2;
                    update.orderChanged = ((ArrayList) bubbleData.mBubbles).size() > 1;
                    if (!bubbleData.mExpanded) {
                        bubbleData.setSelectedBubbleInternal((BubbleViewProvider) ((ArrayList) bubbleData.mBubbles).get(0));
                    }
                    bubbleData.trim();
                } else {
                    bubble2.mSuppressFlyout = z3;
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8830530833687167624L, 0, String.valueOf(str));
                    }
                    bubbleData.mStateChange.updatedBubble = bubble2;
                    if (!bubbleData.mExpanded && !z3) {
                        int indexOf = bubbleData.mBubbles.indexOf(bubble2);
                        bubbleData.mBubbles.remove(bubble2);
                        bubbleData.mBubbles.add(0, bubble2);
                        bubbleData.mStateChange.orderChanged = indexOf != 0;
                        bubbleData.setSelectedBubbleInternal((BubbleViewProvider) ((ArrayList) bubbleData.mBubbles).get(0));
                    }
                }
                if (bubble2.isEnabled(1)) {
                    bubble2.setShouldAutoExpand(false);
                    bubbleData.setSelectedBubbleInternal(bubble2);
                    if (!bubbleData.mExpanded) {
                        bubbleData.setExpandedInternal(true);
                    }
                }
                boolean z6 = bubbleData.mExpanded && bubbleData.mSelectedBubble == bubble2;
                bubble2.setSuppressNotification((!z6 && z5 && bubble2.showInShade()) ? false : true);
                bubble2.setShowDot(!z6);
                LocusId locusId = bubble2.mLocusId;
                if (locusId != null) {
                    boolean containsKey = bubbleData.mSuppressedBubbles.containsKey(locusId);
                    if (containsKey && (!bubble2.isSuppressed() || (bubble2.mFlags & 4) == 0)) {
                        bubbleData.mSuppressedBubbles.remove(locusId);
                        bubbleData.doUnsuppress(bubble2);
                    } else if (!containsKey && (bubble2.isSuppressed() || ((bubble2.mFlags & 4) != 0 && bubbleData.mVisibleLocusIds.contains(locusId)))) {
                        bubbleData.mSuppressedBubbles.put(locusId, bubble2);
                        bubbleData.doSuppress(bubble2);
                    }
                }
                bubbleData.dispatchPendingChanges();
            }
        };
        Context context = this.mContext;
        BubbleStackView bubbleStackView = this.mStackView;
        BubbleBarLayerView bubbleBarLayerView = this.mLayerView;
        BubbleIconFactory bubbleIconFactory = this.mBubbleIconFactory;
        bubble.inflate(callback, context, this.mExpandedViewManager, this.mBubbleTaskViewFactory, this.mBubblePositioner, bubbleStackView, bubbleBarLayerView, bubbleIconFactory, false);
    }

    public boolean isBubbleNotificationSuppressedFromShade(String str, String str2) {
        BubbleData bubbleData = this.mBubbleData;
        return (str.equals((String) bubbleData.mSuppressedGroupKeys.get(str2)) && bubbleData.isSummarySuppressed(str2)) || (bubbleData.hasAnyBubbleWithKey(str) && !bubbleData.getAnyBubbleWithkey(str).showInShade());
    }

    public final boolean isShowingAsBubbleBar() {
        return canShowAsBubbleBar() && this.mBubbleStateListener != null;
    }

    public final boolean isSummaryOfBubbles(BubbleEntry bubbleEntry) {
        String groupKey = bubbleEntry.mSbn.getGroupKey();
        ArrayList bubblesInGroup = getBubblesInGroup(groupKey);
        BubbleData bubbleData = this.mBubbleData;
        return ((bubbleData.isSummarySuppressed(groupKey) && ((String) bubbleData.mSuppressedGroupKeys.get(groupKey)).equals(bubbleEntry.mSbn.getKey())) || bubbleEntry.mSbn.getNotification().isGroupSummary()) && !bubblesInGroup.isEmpty();
    }

    public final void loadOverflowBubblesFromDisk() {
        if (this.mOverflowDataLoadNeeded) {
            this.mOverflowDataLoadNeeded = false;
            List list = this.mUserManager.getAliveUsers().stream().map(new BubbleController$$ExternalSyntheticLambda19()).toList();
            int i = this.mCurrentUserId;
            Function1 function1 = new Function1() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    BubbleController bubbleController = BubbleController.this;
                    bubbleController.getClass();
                    ((List) obj).forEach(new BubbleController$$ExternalSyntheticLambda11(bubbleController, 1));
                    return null;
                }
            };
            BubbleDataRepository bubbleDataRepository = this.mDataRepository;
            BuildersKt.launch$default(bubbleDataRepository.coroutineScope, null, null, new BubbleDataRepository$loadBubbles$1(bubbleDataRepository, list, i, function1, null), 3);
        }
    }

    public void onBubbleMetadataFlagChanged(Bubble bubble) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 2312076315504885239L, 4, String.valueOf(bubble.mKey), Long.valueOf(bubble.mFlags));
        }
        try {
            this.mBarService.onBubbleMetadataFlagChanged(bubble.mKey, bubble.mFlags);
        } catch (RemoteException unused) {
        }
        this.mImpl.mCachedState.updateBubbleSuppressedState(bubble);
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onConfigurationChanged(Configuration configuration) {
        BubbleOverflow bubbleOverflow;
        BubbleExpandedView bubbleExpandedView;
        BubbleOverflow bubbleOverflow2;
        BubbleExpandedView bubbleExpandedView2;
        BubblePositioner bubblePositioner = this.mBubblePositioner;
        if (bubblePositioner != null) {
            bubblePositioner.update(DeviceConfig.create(this.mContext, this.mWindowManager));
        }
        if (this.mStackView != null) {
            if (configuration.densityDpi != this.mDensityDpi || !configuration.windowConfiguration.getBounds().equals(this.mScreenBounds)) {
                this.mDensityDpi = configuration.densityDpi;
                this.mScreenBounds.set(configuration.windowConfiguration.getBounds());
                BubbleData bubbleData = this.mBubbleData;
                bubbleData.mMaxBubbles = bubbleData.mPositioner.mMaxBubbles;
                if (bubbleData.mExpanded) {
                    bubbleData.mNeedsTrimming = true;
                } else {
                    bubbleData.trim();
                    bubbleData.dispatchPendingChanges();
                }
                Context context = this.mContext;
                this.mBubbleIconFactory = new BubbleIconFactory(context, context.getResources().getDimensionPixelSize(R.dimen.bubble_size), this.mContext.getResources().getDimensionPixelSize(R.dimen.bubble_badge_size), this.mContext.getResources().getColor(R.color.important_conversation), this.mContext.getResources().getDimensionPixelSize(android.R.dimen.indeterminate_progress_alpha_08));
                this.mStackView.onDisplaySizeChanged();
            }
            float f = configuration.fontScale;
            if (f != this.mFontScale) {
                this.mFontScale = f;
                BubbleStackView bubbleStackView = this.mStackView;
                bubbleStackView.setUpManageMenu();
                bubbleStackView.mFlyout.updateFontSize();
                Iterator it = Collections.unmodifiableList(bubbleStackView.mBubbleData.mBubbles).iterator();
                while (it.hasNext()) {
                    BubbleExpandedView bubbleExpandedView3 = ((Bubble) it.next()).mExpandedView;
                    if (bubbleExpandedView3 != null) {
                        bubbleExpandedView3.updateFontSize();
                    }
                }
                if (bubbleStackView.mShowingOverflow && (bubbleOverflow2 = bubbleStackView.mBubbleOverflow) != null && (bubbleExpandedView2 = bubbleOverflow2.expandedView) != null) {
                    bubbleExpandedView2.updateFontSize();
                }
            }
            if (configuration.getLayoutDirection() != this.mLayoutDirection) {
                final int layoutDirection = configuration.getLayoutDirection();
                this.mLayoutDirection = layoutDirection;
                BubbleStackView bubbleStackView2 = this.mStackView;
                bubbleStackView2.mManageMenu.setLayoutDirection(layoutDirection);
                bubbleStackView2.mFlyout.setLayoutDirection(layoutDirection);
                StackEducationView stackEducationView = bubbleStackView2.mStackEduView;
                if (stackEducationView != null) {
                    stackEducationView.setLayoutDirection(layoutDirection);
                }
                ManageEducationView manageEducationView = bubbleStackView2.mManageEduView;
                if (manageEducationView != null) {
                    manageEducationView.setLayoutDirection(layoutDirection);
                }
                List unmodifiableList = Collections.unmodifiableList(bubbleStackView2.mBubbleData.mBubbles);
                if (!unmodifiableList.isEmpty()) {
                    unmodifiableList.forEach(new Consumer() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda24
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i = layoutDirection;
                            BubbleExpandedView bubbleExpandedView4 = ((Bubble) obj).mExpandedView;
                            if (bubbleExpandedView4 != null) {
                                bubbleExpandedView4.setLayoutDirection(i);
                            }
                        }
                    });
                }
            }
            Locale locale = configuration.locale;
            if (locale == null || locale.equals(this.mLocale)) {
                return;
            }
            this.mLocale = locale;
            BubbleStackView bubbleStackView3 = this.mStackView;
            if (!bubbleStackView3.mShowingOverflow || (bubbleOverflow = bubbleStackView3.mBubbleOverflow) == null || (bubbleExpandedView = bubbleOverflow.expandedView) == null) {
                return;
            }
            bubbleExpandedView.updateLocale();
        }
    }

    public void onEntryUpdated(BubbleEntry bubbleEntry, boolean z, boolean z2) {
        if (z2) {
            boolean z3 = z && canLaunchInTaskView(this.mContext, bubbleEntry);
            if (!z3) {
                if (this.mBubbleData.hasAnyBubbleWithKey(bubbleEntry.mSbn.getKey())) {
                    removeBubble(7, bubbleEntry.mSbn.getKey());
                    return;
                }
            }
            if (z3 && bubbleEntry.isBubble()) {
                updateBubble(bubbleEntry);
            }
        }
    }

    public void onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
        BubbleData bubbleData = this.mBubbleData;
        ArrayList arrayList = new ArrayList(bubbleData.getOverflowBubbles());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Bubble bubble = (Bubble) arrayList.get(i2);
            ShortcutInfo shortcutInfo = bubble.mShortcutInfo;
            if (Objects.equals(shortcutInfo != null ? shortcutInfo.getId() : bubble.mMetadataShortcutId, notificationChannel.getConversationId()) && bubble.mPackageName.equals(str) && bubble.mUser.getIdentifier() == userHandle.getIdentifier() && (!notificationChannel.canBubble() || notificationChannel.isDeleted())) {
                bubbleData.dismissBubbleWithKey(7, bubble.mKey);
            }
        }
    }

    public void onRankingUpdated(NotificationListenerService.RankingMap rankingMap, HashMap hashMap) {
        int identifier;
        SparseArray sparseArray;
        if (this.mTmpRanking == null) {
            this.mTmpRanking = new NotificationListenerService.Ranking();
        }
        for (String str : rankingMap.getOrderedKeys()) {
            Pair pair = (Pair) hashMap.get(str);
            BubbleEntry bubbleEntry = (BubbleEntry) pair.first;
            boolean booleanValue = ((Boolean) pair.second).booleanValue();
            if (bubbleEntry != null && (identifier = bubbleEntry.mSbn.getUser().getIdentifier()) != -1 && ((sparseArray = this.mCurrentProfiles) == null || sparseArray.get(identifier) == null)) {
                return;
            }
            if (bubbleEntry != null && (bubbleEntry.mShouldSuppressNotificationList || bubbleEntry.mRanking.isSuspended())) {
                booleanValue = false;
            }
            rankingMap.getRanking(str, this.mTmpRanking);
            BubbleData bubbleData = this.mBubbleData;
            boolean hasAnyBubbleWithKey = bubbleData.hasAnyBubbleWithKey(str);
            bubbleData.hasBubbleInStackWithKey(str);
            if (hasAnyBubbleWithKey && !this.mTmpRanking.canBubble()) {
                bubbleData.dismissBubbleWithKey(4, str);
            } else if (hasAnyBubbleWithKey && !booleanValue) {
                bubbleData.dismissBubbleWithKey(14, str);
            } else if (bubbleEntry != null && this.mTmpRanking.isBubble() && !hasAnyBubbleWithKey) {
                bubbleEntry.setFlagBubble(true);
                onEntryUpdated(bubbleEntry, booleanValue, true);
            }
        }
    }

    public void onStatusBarStateChanged(boolean z) {
        boolean z2 = this.mIsStatusBarShade != z;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            BubbleEntry bubbleEntry = this.mNotifEntryToExpandOnShadeUnlock;
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -6204015995872987500L, 15, Boolean.valueOf(z), Boolean.valueOf(z2), String.valueOf(bubbleEntry != null ? bubbleEntry.mSbn.getKey() : "null"));
        }
        this.mIsStatusBarShade = z;
        if (!z && z2) {
            collapseStack();
        }
        BubbleEntry bubbleEntry2 = this.mNotifEntryToExpandOnShadeUnlock;
        if (bubbleEntry2 != null) {
            expandStackAndSelectBubble(bubbleEntry2);
        }
        updateBubbleViews();
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onThemeChanged() {
        AnonymousClass1 anonymousClass1;
        BubbleExpandedViewManager$Companion$fromBubbleController$1 bubbleExpandedViewManager$Companion$fromBubbleController$1;
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.setUpFlyout();
            bubbleStackView.setUpManageMenu();
            bubbleStackView.setUpDismissView();
            bubbleStackView.updateOverflow();
            bubbleStackView.updateUserEdu();
            List unmodifiableList = Collections.unmodifiableList(bubbleStackView.mBubbleData.mBubbles);
            if (!unmodifiableList.isEmpty()) {
                unmodifiableList.forEach(new BubbleStackView$$ExternalSyntheticLambda41());
            }
            bubbleStackView.mScrim.setBackgroundDrawable(new ColorDrawable(bubbleStackView.getResources().getColor(android.R.color.system_neutral1_1000)));
            bubbleStackView.mManageMenuScrim.setBackgroundDrawable(new ColorDrawable(bubbleStackView.getResources().getColor(android.R.color.system_neutral1_1000)));
            if (bubbleStackView.mShowingManage) {
                bubbleStackView.post(new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 7));
            }
        }
        Context context = this.mContext;
        this.mBubbleIconFactory = new BubbleIconFactory(context, context.getResources().getDimensionPixelSize(R.dimen.bubble_size), this.mContext.getResources().getDimensionPixelSize(R.dimen.bubble_badge_size), this.mContext.getResources().getColor(R.color.important_conversation), this.mContext.getResources().getDimensionPixelSize(android.R.dimen.indeterminate_progress_alpha_08));
        BubbleData bubbleData = this.mBubbleData;
        Iterator it = Collections.unmodifiableList(bubbleData.mBubbles).iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            anonymousClass1 = this.mBubbleTaskViewFactory;
            bubbleExpandedViewManager$Companion$fromBubbleController$1 = this.mExpandedViewManager;
            if (!hasNext) {
                break;
            }
            ((Bubble) it.next()).inflate(null, this.mContext, bubbleExpandedViewManager$Companion$fromBubbleController$1, anonymousClass1, this.mBubblePositioner, this.mStackView, this.mLayerView, this.mBubbleIconFactory, false);
        }
        Iterator it2 = bubbleData.getOverflowBubbles().iterator();
        while (it2.hasNext()) {
            ((Bubble) it2.next()).inflate(null, this.mContext, bubbleExpandedViewManager$Companion$fromBubbleController$1, anonymousClass1, this.mBubblePositioner, this.mStackView, this.mLayerView, this.mBubbleIconFactory, false);
        }
    }

    public void onUserChanged(int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8003463218618614664L, 5, Long.valueOf(this.mCurrentUserId), Long.valueOf(i));
        }
        int i2 = this.mCurrentUserId;
        this.mSavedUserBubbleData.remove(i2);
        UserBubbleData userBubbleData = new UserBubbleData();
        BubbleData bubbleData = this.mBubbleData;
        for (Bubble bubble : Collections.unmodifiableList(bubbleData.mBubbles)) {
            userBubbleData.mKeyToShownInShadeMap.put(bubble.mKey, Boolean.valueOf(bubble.showInShade()));
        }
        this.mSavedUserBubbleData.put(i2, userBubbleData);
        this.mCurrentUserId = i;
        bubbleData.dismissAll(8);
        while (!bubbleData.mOverflowBubbles.isEmpty()) {
            bubbleData.doRemove(8, ((Bubble) ((ArrayList) bubbleData.mOverflowBubbles).get(0)).mKey);
        }
        bubbleData.dispatchPendingChanges();
        this.mOverflowDataLoadNeeded = true;
        UserBubbleData userBubbleData2 = (UserBubbleData) this.mSavedUserBubbleData.get(i);
        if (userBubbleData2 != null) {
            BubblesManager.AnonymousClass5 anonymousClass5 = this.mSysuiProxy;
            anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda1(anonymousClass5, userBubbleData2.mKeyToShownInShadeMap.keySet(), new BubbleController$$ExternalSyntheticLambda5(this, userBubbleData2, 0)));
            this.mSavedUserBubbleData.remove(i);
        }
        bubbleData.mCurrentUserId = i;
    }

    public final void promoteBubbleFromOverflow(Bubble bubble) {
        this.mLogger.log(bubble, BubbleLogger.Event.BUBBLE_OVERFLOW_REMOVE_BACK_TO_STACK);
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
        String str = bubble.mKey;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 1535426235779658681L, 0, String.valueOf(str));
        }
        bubble.setInflateSynchronously(this.mInflateSynchronously);
        bubble.setShouldAutoExpand(true);
        bubble.mLastAccessed = System.currentTimeMillis();
        bubble.setSuppressNotification(true);
        bubble.setShowDot(false);
        bubble.mIsBubble = true;
        BubblesManager.AnonymousClass5 anonymousClass5 = this.mSysuiProxy;
        anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda1(anonymousClass5, str, new BubbleController$$ExternalSyntheticLambda1(this, true, bubble)));
    }

    public void removeAllBubbles(int i) {
        this.mBubbleData.dismissAll(i);
    }

    public final void removeBubble(int i, String str) {
        BubbleData bubbleData = this.mBubbleData;
        if (bubbleData.hasAnyBubbleWithKey(str)) {
            bubbleData.dismissBubbleWithKey(i, str);
        }
    }

    public final void removeFromWindowManagerMaybe() {
        TaskView taskView;
        TaskView taskView2;
        if (this.mAddedToWindowManager) {
            this.mAddedToWindowManager = false;
            ((HandlerExecutor) this.mBackgroundExecutor).execute(new BubbleController$$ExternalSyntheticLambda3(this, 1));
            try {
                BubbleStackView bubbleStackView = this.mStackView;
                BubbleData bubbleData = this.mBubbleData;
                if (bubbleStackView != null) {
                    this.mWindowManager.removeView(bubbleStackView);
                    BubbleOverflow bubbleOverflow = bubbleData.mOverflow;
                    BubbleExpandedView bubbleExpandedView = bubbleOverflow.expandedView;
                    if (bubbleExpandedView != null && (taskView2 = bubbleExpandedView.mTaskView) != null) {
                        taskView2.setVisibility(8);
                    }
                    bubbleOverflow.expandedView = null;
                    BubbleBarExpandedView bubbleBarExpandedView = bubbleOverflow.bubbleBarExpandedView;
                    if (bubbleBarExpandedView != null) {
                        bubbleBarExpandedView.mMenuViewController.hideMenu(false);
                    }
                    bubbleOverflow.bubbleBarExpandedView = null;
                }
                BubbleBarLayerView bubbleBarLayerView = this.mLayerView;
                if (bubbleBarLayerView != null) {
                    this.mWindowManager.removeView(bubbleBarLayerView);
                    BubbleOverflow bubbleOverflow2 = bubbleData.mOverflow;
                    BubbleExpandedView bubbleExpandedView2 = bubbleOverflow2.expandedView;
                    if (bubbleExpandedView2 != null && (taskView = bubbleExpandedView2.mTaskView) != null) {
                        taskView.setVisibility(8);
                    }
                    bubbleOverflow2.expandedView = null;
                    BubbleBarExpandedView bubbleBarExpandedView2 = bubbleOverflow2.bubbleBarExpandedView;
                    if (bubbleBarExpandedView2 != null) {
                        bubbleBarExpandedView2.mMenuViewController.hideMenu(false);
                    }
                    bubbleOverflow2.bubbleBarExpandedView = null;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public final void setBubbleBarLocation(BubbleBarLocation bubbleBarLocation) {
        BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController;
        if (canShowAsBubbleBar()) {
            this.mBubblePositioner.mBubbleBarLocation = bubbleBarLocation;
            BubbleBarLayerView bubbleBarLayerView = this.mLayerView;
            if (bubbleBarLayerView != null && ((bubbleBarExpandedViewDragController = bubbleBarLayerView.mDragController) == null || !bubbleBarExpandedViewDragController.isDragged)) {
                bubbleBarLayerView.updateExpandedView();
            }
            BubbleBarUpdate bubbleBarUpdate = new BubbleBarUpdate(false);
            bubbleBarUpdate.bubbleBarLocation = bubbleBarLocation;
            this.mBubbleStateListener.onBubbleStateChange(bubbleBarUpdate);
        }
    }

    public void setExpandListener(Bubbles.BubbleExpandListener bubbleExpandListener) {
        BubbleController$$ExternalSyntheticLambda2 bubbleController$$ExternalSyntheticLambda2 = new BubbleController$$ExternalSyntheticLambda2(bubbleExpandListener);
        this.mExpandListener = bubbleController$$ExternalSyntheticLambda2;
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.mExpandListener = bubbleController$$ExternalSyntheticLambda2;
        }
    }

    public void setInflateSynchronously(boolean z) {
        this.mInflateSynchronously = z;
    }

    public final void setIsBubble(BubbleEntry bubbleEntry, boolean z, boolean z2) {
        Objects.requireNonNull(bubbleEntry);
        bubbleEntry.setFlagBubble(z);
        try {
            this.mBarService.onNotificationBubbleChanged(bubbleEntry.mSbn.getKey(), z, z2 ? 3 : 0);
        } catch (RemoteException unused) {
        }
    }

    public final void setUpBubbleViewsForMode() {
        PhysicsAnimationLayout physicsAnimationLayout;
        this.mBubbleViewCallback = isShowingAsBubbleBar() ? this.mBubbleBarViewCallback : this.mBubbleStackViewCallback;
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            BadgedImageView iconView$1 = bubbleStackView.mBubbleOverflow.getIconView$1();
            if (iconView$1 != null && (physicsAnimationLayout = (PhysicsAnimationLayout) iconView$1.getParent()) != null) {
                physicsAnimationLayout.removeViewNoAnimation(iconView$1);
            }
            this.mStackView.removeAllViews();
        }
        BubbleData bubbleData = this.mBubbleData;
        Collections.unmodifiableList(bubbleData.mBubbles).forEach(new BubbleController$$ExternalSyntheticLambda21(0));
        removeFromWindowManagerMaybe();
        BubbleController$$ExternalSyntheticLambda7 bubbleController$$ExternalSyntheticLambda7 = null;
        this.mLayerView = null;
        this.mStackView = null;
        if (bubbleData.mBubbles.isEmpty()) {
            return;
        }
        ensureBubbleViewsAndWindowCreated();
        if (!isShowingAsBubbleBar()) {
            bubbleController$$ExternalSyntheticLambda7 = new BubbleController$$ExternalSyntheticLambda7(this, 1);
        } else if (bubbleData.mExpanded && bubbleData.mSelectedBubble != null) {
            bubbleController$$ExternalSyntheticLambda7 = new BubbleController$$ExternalSyntheticLambda7(this, 2);
        }
        for (int size = Collections.unmodifiableList(bubbleData.mBubbles).size() - 1; size >= 0; size--) {
            ((Bubble) Collections.unmodifiableList(bubbleData.mBubbles).get(size)).inflate(bubbleController$$ExternalSyntheticLambda7, this.mContext, this.mExpandedViewManager, this.mBubbleTaskViewFactory, this.mBubblePositioner, this.mStackView, this.mLayerView, this.mBubbleIconFactory, false);
        }
    }

    public void updateBubble(BubbleEntry bubbleEntry) {
        SparseArray sparseArray;
        int userId = bubbleEntry.mSbn.getUserId();
        if (userId == -1 || !((sparseArray = this.mCurrentProfiles) == null || sparseArray.get(userId) == null)) {
            updateBubble(bubbleEntry, false, true);
            return;
        }
        ((UserBubbleData) this.mSavedUserBubbleData.get(userId, new UserBubbleData())).mKeyToShownInShadeMap.put(bubbleEntry.mSbn.getKey(), Boolean.TRUE);
        Log.w("Bubbles", "updateBubble, ignore update for non-active user=" + userId + " currentUser=" + this.mCurrentUserId);
    }

    public final void updateBubbleViews() {
        final BadgedImageView iconView$1;
        final BadgedImageView badgedImageView;
        final int i = 0;
        final int i2 = 1;
        if (this.mStackView == null && this.mLayerView == null) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, -5180604918216641258L, 0, String.valueOf(this.mIsStatusBarShade), String.valueOf(hasBubbles()));
        }
        if (!this.mIsStatusBarShade) {
            BubbleStackView bubbleStackView = this.mStackView;
            if (bubbleStackView != null) {
                bubbleStackView.setVisibility(4);
            }
            BubbleBarLayerView bubbleBarLayerView = this.mLayerView;
            if (bubbleBarLayerView != null) {
                bubbleBarLayerView.setVisibility(4);
            }
        } else if (hasBubbles()) {
            BubbleStackView bubbleStackView2 = this.mStackView;
            if (bubbleStackView2 != null) {
                bubbleStackView2.setVisibility(0);
            }
            BubbleBarLayerView bubbleBarLayerView2 = this.mLayerView;
            if (bubbleBarLayerView2 != null) {
                bubbleBarLayerView2.setVisibility(0);
            }
        }
        BubbleStackView bubbleStackView3 = this.mStackView;
        if (bubbleStackView3 != null) {
            if (!Collections.unmodifiableList(bubbleStackView3.mBubbleData.mBubbles).isEmpty()) {
                for (int i3 = 0; i3 < Collections.unmodifiableList(bubbleStackView3.mBubbleData.mBubbles).size(); i3++) {
                    Bubble bubble = (Bubble) Collections.unmodifiableList(bubbleStackView3.mBubbleData.mBubbles).get(i3);
                    String str = bubble.mAppName;
                    String str2 = bubble.mTitle;
                    if (str2 == null) {
                        str2 = bubbleStackView3.getResources().getString(R.string.notification_bubble_title);
                    }
                    BadgedImageView badgedImageView2 = bubble.mIconView;
                    if (badgedImageView2 != null) {
                        if (bubbleStackView3.mIsExpanded || i3 > 0) {
                            badgedImageView2.setContentDescription(bubbleStackView3.getResources().getString(R.string.bubble_content_description_single, str2, str));
                        } else {
                            bubble.mIconView.setContentDescription(bubbleStackView3.getResources().getString(R.string.bubble_content_description_stack, str2, str, Integer.valueOf(bubbleStackView3.getBubbleCount())));
                        }
                    }
                }
            }
            BubbleStackView bubbleStackView4 = this.mStackView;
            int i4 = 0;
            while (true) {
                if (i4 >= Collections.unmodifiableList(bubbleStackView4.mBubbleData.mBubbles).size()) {
                    break;
                }
                Bubble bubble2 = i4 > 0 ? (Bubble) Collections.unmodifiableList(bubbleStackView4.mBubbleData.mBubbles).get(i4 - 1) : null;
                BadgedImageView badgedImageView3 = ((Bubble) Collections.unmodifiableList(bubbleStackView4.mBubbleData.mBubbles).get(i4)).mIconView;
                if (badgedImageView3 != null) {
                    if (bubbleStackView4.mIsExpanded) {
                        badgedImageView3.setImportantForAccessibility(1);
                        iconView$1 = bubble2 != null ? bubble2.mIconView : null;
                        if (iconView$1 != null) {
                            badgedImageView3.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.wm.shell.bubbles.BubbleStackView.16
                                public final /* synthetic */ int $r8$classId;
                                public final /* synthetic */ BadgedImageView val$prevBubbleIconView;

                                public /* synthetic */ AnonymousClass16(final BadgedImageView iconView$12, final int i5) {
                                    r2 = i5;
                                    r1 = iconView$12;
                                }

                                @Override // android.view.View.AccessibilityDelegate
                                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                                    switch (r2) {
                                        case 0:
                                            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                                            accessibilityNodeInfo.setTraversalAfter(r1);
                                            break;
                                        default:
                                            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                                            accessibilityNodeInfo.setTraversalAfter(r1);
                                            break;
                                    }
                                }
                            });
                        }
                    } else {
                        badgedImageView3.setImportantForAccessibility(i4 == 0 ? 1 : 2);
                    }
                }
                i4++;
            }
            if (bubbleStackView4.mIsExpanded) {
                BubbleOverflow bubbleOverflow = bubbleStackView4.mBubbleOverflow;
                iconView$12 = bubbleOverflow != null ? bubbleOverflow.getIconView$1() : null;
                if (!bubbleStackView4.mShowingOverflow || iconView$12 == null || Collections.unmodifiableList(bubbleStackView4.mBubbleData.mBubbles).isEmpty() || (badgedImageView = ((Bubble) Collections.unmodifiableList(bubbleStackView4.mBubbleData.mBubbles).get(Collections.unmodifiableList(bubbleStackView4.mBubbleData.mBubbles).size() - 1)).mIconView) == null) {
                    return;
                }
                iconView$12.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.wm.shell.bubbles.BubbleStackView.16
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ BadgedImageView val$prevBubbleIconView;

                    public /* synthetic */ AnonymousClass16(final BadgedImageView badgedImageView4, final int i22) {
                        r2 = i22;
                        r1 = badgedImageView4;
                    }

                    @Override // android.view.View.AccessibilityDelegate
                    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                        switch (r2) {
                            case 0:
                                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                                accessibilityNodeInfo.setTraversalAfter(r1);
                                break;
                            default:
                                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                                accessibilityNodeInfo.setTraversalAfter(r1);
                                break;
                        }
                    }
                });
            }
        }
    }

    public final void updateNotNotifyingEntry(Bubble bubble, BubbleEntry bubbleEntry, boolean z) {
        boolean showInShade = bubble.showInShade();
        BubbleData bubbleData = this.mBubbleData;
        boolean z2 = bubbleData.mExpanded && bubble.equals(bubbleData.mSelectedBubble);
        bubble.setEntry(bubbleEntry);
        bubble.setSuppressNotification((!z2 && z && bubble.showInShade()) ? false : true);
        bubble.setShowDot(!z2);
        if (showInShade != bubble.showInShade()) {
            this.mImpl.mCachedState.updateBubbleSuppressedState(bubble);
        }
    }

    public final void updateWindowFlagsForBackpress(boolean z) {
        if (this.mAddedToWindowManager) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -1228140070930271613L, 3, Boolean.valueOf(z));
            }
            WindowManager.LayoutParams layoutParams = this.mWmLayoutParams;
            layoutParams.flags = 16777216 | (z ? 0 : 40);
            BubbleStackView bubbleStackView = this.mStackView;
            if (bubbleStackView != null) {
                this.mWindowManager.updateViewLayout(bubbleStackView, layoutParams);
                return;
            }
            BubbleBarLayerView bubbleBarLayerView = this.mLayerView;
            if (bubbleBarLayerView != null) {
                this.mWindowManager.updateViewLayout(bubbleBarLayerView, layoutParams);
            }
        }
    }

    public void updateBubble(BubbleEntry bubbleEntry, boolean z, boolean z2) {
        BubblesManager.AnonymousClass5 anonymousClass5 = this.mSysuiProxy;
        anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass5, bubbleEntry.mSbn.getKey(), 0));
        boolean z3 = (bubbleEntry.mRanking.isTextChanged() || bubbleEntry.getBubbleMetadata() == null || bubbleEntry.getBubbleMetadata().getAutoExpandBubble()) ? false : true;
        BubbleData bubbleData = this.mBubbleData;
        if (z3 && bubbleData.hasOverflowBubbleWithKey(bubbleEntry.mSbn.getKey())) {
            Bubble overflowBubbleWithKey = bubbleData.getOverflowBubbleWithKey(bubbleEntry.mSbn.getKey());
            if (bubbleEntry.isBubble()) {
                bubbleEntry.setFlagBubble(false);
            }
            updateNotNotifyingEntry(overflowBubbleWithKey, bubbleEntry, z2);
            return;
        }
        if (bubbleData.hasAnyBubbleWithKey(bubbleEntry.mSbn.getKey()) && z3) {
            Bubble anyBubbleWithkey = bubbleData.getAnyBubbleWithkey(bubbleEntry.mSbn.getKey());
            if (anyBubbleWithkey != null) {
                updateNotNotifyingEntry(anyBubbleWithkey, bubbleEntry, z2);
                return;
            }
            return;
        }
        if (bubbleData.mSuppressedBubbles.get(bubbleEntry.mSbn.getNotification().getLocusId()) != null) {
            Bubble suppressedBubbleWithKey = bubbleData.getSuppressedBubbleWithKey(bubbleEntry.mSbn.getKey());
            if (suppressedBubbleWithKey != null) {
                updateNotNotifyingEntry(suppressedBubbleWithKey, bubbleEntry, z2);
                return;
            }
            return;
        }
        Bubble orCreateBubble = bubbleData.getOrCreateBubble(bubbleEntry, null);
        if (bubbleEntry.mShouldSuppressNotificationList) {
            if (orCreateBubble.isEnabled(1)) {
                orCreateBubble.setShouldAutoExpand(false);
            }
            this.mImpl.mCachedState.updateBubbleSuppressedState(orCreateBubble);
            return;
        }
        inflateAndAdd(orCreateBubble, z, z2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleController$8, reason: invalid class name */
    public final class AnonymousClass8 {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass8(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda54, java.lang.Runnable] */
        public void expansionChanged(boolean z) {
            boolean z2;
            int i = 15;
            Object obj = this.this$0;
            int i2 = 1;
            switch (this.$r8$classId) {
                case 0:
                    final BubbleStackView bubbleStackView = ((BubbleController) obj).mStackView;
                    if (bubbleStackView != null) {
                        if (!z) {
                            bubbleStackView.releaseAnimatingOutBubbleBuffer();
                        }
                        boolean z3 = bubbleStackView.mIsExpanded;
                        if (z != z3) {
                            bubbleStackView.mManager.$controller.hideCurrentInputMethod();
                            BubblesManager.AnonymousClass5 anonymousClass5 = bubbleStackView.mSysuiProxyProvider.mSysuiProxy;
                            anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda9(anonymousClass5, anonymousClass5.val$sysUiState, z, i2));
                            if (z3) {
                                bubbleStackView.stopMonitoringSwipeUpGestureInternal();
                                ((HandlerExecutor) bubbleStackView.mMainExecutor).removeCallbacks(bubbleStackView.mDelayedAnimation);
                                bubbleStackView.mIsExpansionAnimating = false;
                                bubbleStackView.mIsBubbleSwitchAnimating = false;
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 813173073428952041L, 0, null);
                                }
                                if (bubbleStackView.isManageEduVisible()) {
                                    bubbleStackView.mManageEduView.hide();
                                }
                                bubbleStackView.mIsExpanded = false;
                                bubbleStackView.mIsExpansionAnimating = true;
                                if (!bubbleStackView.mRemovingLastBubbleWhileExpanded) {
                                    bubbleStackView.showScrim(false, null);
                                }
                                PhysicsAnimationLayout physicsAnimationLayout = bubbleStackView.mBubbleContainer;
                                PhysicsAnimationLayout.PhysicsAnimationController physicsAnimationController = physicsAnimationLayout.mController;
                                if (physicsAnimationController != null) {
                                    physicsAnimationLayout.cancelAllAnimationsOfProperties((DynamicAnimation.ViewProperty[]) physicsAnimationController.getAnimatedProperties().toArray(new DynamicAnimation.ViewProperty[0]));
                                }
                                FrameLayout frameLayout = bubbleStackView.mAnimatingOutSurfaceContainer;
                                Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                PhysicsAnimator.Companion.getInstance(frameLayout).cancel();
                                bubbleStackView.mAnimatingOutSurfaceContainer.setScaleX(0.0f);
                                bubbleStackView.mAnimatingOutSurfaceContainer.setScaleY(0.0f);
                                bubbleStackView.mExpandedAnimationController.mPreparingToCollapse = true;
                                StackAnimationController stackAnimationController = bubbleStackView.mStackAnimationController;
                                PointF pointF = stackAnimationController.mStackPosition;
                                boolean isFirstChildXLeftOfCenter = stackAnimationController.mLayout.isFirstChildXLeftOfCenter(pointF.x);
                                RectF allowableStackPositionRegion = stackAnimationController.mPositioner.getAllowableStackPositionRegion(stackAnimationController.mBubbleCountSupplier.f$0.getBubbleCount());
                                pointF.x = isFirstChildXLeftOfCenter ? allowableStackPositionRegion.left : allowableStackPositionRegion.right;
                                bubbleStackView.updateOverflowDotVisibility(false);
                                final BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda25 = new BubbleStackView$$ExternalSyntheticLambda25(i2, bubbleStackView, pointF);
                                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, i);
                                final ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl = bubbleStackView.mExpandedViewAnimationController;
                                expandedViewAnimationControllerImpl.getClass();
                                boolean z4 = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
                                int i3 = expandedViewAnimationControllerImpl.mMinFlingVelocity;
                                if (z4) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 327956261255593019L, 166, Double.valueOf(expandedViewAnimationControllerImpl.mSwipeUpVelocity), Long.valueOf(i3), Double.valueOf(pointF.x), Double.valueOf(pointF.y));
                                }
                                BubbleExpandedView bubbleExpandedView = expandedViewAnimationControllerImpl.mExpandedView;
                                if (bubbleExpandedView != null) {
                                    bubbleExpandedView.mIsAnimating = true;
                                    AnimatorSet animatorSet = expandedViewAnimationControllerImpl.mCollapseAnimation;
                                    if (animatorSet != null) {
                                        animatorSet.cancel();
                                    }
                                    BubbleExpandedView bubbleExpandedView2 = expandedViewAnimationControllerImpl.mExpandedView;
                                    ArrayList arrayList = new ArrayList();
                                    ValueAnimator ofInt = ValueAnimator.ofInt((int) expandedViewAnimationControllerImpl.mCollapsedAmount, bubbleExpandedView2.getContentHeight());
                                    ofInt.setInterpolator(Interpolators.EMPHASIZED_ACCELERATE);
                                    ofInt.setDuration(250L);
                                    ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl$$ExternalSyntheticLambda1
                                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                            ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl2 = ExpandedViewAnimationControllerImpl.this;
                                            expandedViewAnimationControllerImpl2.getClass();
                                            expandedViewAnimationControllerImpl2.setCollapsedAmount(((Integer) valueAnimator.getAnimatedValue()).intValue());
                                        }
                                    });
                                    arrayList.add(ofInt);
                                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(expandedViewAnimationControllerImpl.mExpandedView, BubbleExpandedView.MANAGE_BUTTON_ALPHA, 0.0f);
                                    ofFloat.setDuration(78L);
                                    Interpolator interpolator = Interpolators.LINEAR;
                                    ofFloat.setInterpolator(interpolator);
                                    arrayList.add(ofFloat);
                                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(expandedViewAnimationControllerImpl.mExpandedView, BubbleExpandedView.CONTENT_ALPHA, 0.0f);
                                    ofFloat2.setDuration(78L);
                                    ofFloat2.setInterpolator(interpolator);
                                    ofFloat2.setStartDelay(93L);
                                    final boolean[] zArr = {false};
                                    ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl$$ExternalSyntheticLambda0
                                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                            boolean[] zArr2 = zArr;
                                            BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda252 = bubbleStackView$$ExternalSyntheticLambda25;
                                            if (zArr2[0] || valueAnimator.getAnimatedFraction() <= 0.5f) {
                                                return;
                                            }
                                            zArr2[0] = true;
                                            bubbleStackView$$ExternalSyntheticLambda252.run();
                                        }
                                    });
                                    arrayList.add(ofFloat2);
                                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(expandedViewAnimationControllerImpl.mExpandedView, BubbleExpandedView.BACKGROUND_ALPHA, 0.0f);
                                    ofFloat3.setDuration(78L);
                                    ofFloat3.setInterpolator(interpolator);
                                    ofFloat3.setStartDelay(172L);
                                    arrayList.add(ofFloat3);
                                    AnimatorSet animatorSet2 = new AnimatorSet();
                                    animatorSet2.addListener(new ExpandedViewAnimationControllerImpl.AnonymousClass3(1, bubbleStackView$$ExternalSyntheticLambda2));
                                    animatorSet2.playTogether(arrayList);
                                    expandedViewAnimationControllerImpl.mCollapseAnimation = animatorSet2;
                                    if (expandedViewAnimationControllerImpl.mSwipeUpVelocity >= i3) {
                                        int contentHeight = expandedViewAnimationControllerImpl.mExpandedView.getContentHeight();
                                        ValueAnimator valueAnimator = new ValueAnimator();
                                        float f = expandedViewAnimationControllerImpl.mCollapsedAmount;
                                        float f2 = contentHeight;
                                        expandedViewAnimationControllerImpl.mFlingAnimationUtils.applyDismissing(valueAnimator, f, f2, expandedViewAnimationControllerImpl.mSwipeUpVelocity, f2 - f);
                                        float duration = valueAnimator.getDuration() / 250.0f;
                                        Iterator<Animator> it = expandedViewAnimationControllerImpl.mCollapseAnimation.getChildAnimations().iterator();
                                        while (it.hasNext()) {
                                            Animator next = it.next();
                                            next.setStartDelay((long) (next.getStartDelay() * duration));
                                            next.setDuration((long) (next.getDuration() * duration));
                                        }
                                        expandedViewAnimationControllerImpl.mCollapseAnimation.setInterpolator(valueAnimator.getInterpolator());
                                    }
                                    expandedViewAnimationControllerImpl.mCollapseAnimation.start();
                                }
                                BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                                if (expandedView != null) {
                                    expandedView.setContentVisibility(false);
                                }
                                bubbleStackView.showManageMenu(false);
                                bubbleStackView.logBubbleEvent(bubbleStackView.mExpandedBubble, 4);
                            } else {
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                    BubbleViewProvider bubbleViewProvider = bubbleStackView.mExpandedBubble;
                                    z2 = false;
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -394240243237309085L, 0, String.valueOf(bubbleViewProvider != null ? bubbleViewProvider.getKey() : "null"));
                                } else {
                                    z2 = false;
                                }
                                ((HandlerExecutor) bubbleStackView.mMainExecutor).removeCallbacks(bubbleStackView.mDelayedAnimation);
                                bubbleStackView.mIsExpansionAnimating = z2;
                                bubbleStackView.mIsBubbleSwitchAnimating = z2;
                                bubbleStackView.mIsExpanded = true;
                                if (bubbleStackView.isStackEduVisible()) {
                                    bubbleStackView.mStackEduView.hide(true);
                                }
                                bubbleStackView.mIsExpansionAnimating = true;
                                bubbleStackView.hideFlyoutImmediate();
                                bubbleStackView.updateExpandedBubble();
                                bubbleStackView.updateExpandedView();
                                bubbleStackView.showScrim(true, null);
                                bubbleStackView.updateBubbleShadows(bubbleStackView.mIsExpanded);
                                bubbleStackView.mBubbleContainer.setActiveController(bubbleStackView.mExpandedAnimationController);
                                bubbleStackView.updateOverflowVisibility();
                                bubbleStackView.updateBadges(false);
                                bubbleStackView.updatePointerPosition(false);
                                ExpandedAnimationController expandedAnimationController = bubbleStackView.mExpandedAnimationController;
                                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda22 = new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 16);
                                expandedAnimationController.mPreparingToCollapse = false;
                                expandedAnimationController.mAnimatingCollapse = false;
                                expandedAnimationController.mAnimatingExpand = true;
                                expandedAnimationController.mAfterExpand = bubbleStackView$$ExternalSyntheticLambda22;
                                expandedAnimationController.startOrUpdatePathAnimation(true);
                                BubbleViewProvider bubbleViewProvider2 = bubbleStackView.mExpandedBubble;
                                PointF expandedBubbleXY = bubbleStackView.mPositioner.getExpandedBubbleXY((bubbleViewProvider2 == null || !"Overflow".equals(bubbleViewProvider2.getKey())) ? bubbleStackView.getBubbleIndex(bubbleStackView.mExpandedBubble) : Collections.unmodifiableList(bubbleStackView.mBubbleData.mBubbles).size(), bubbleStackView.getState());
                                BubblePositioner bubblePositioner = bubbleStackView.mPositioner;
                                float expandedViewY = bubblePositioner.getExpandedViewY(bubbleStackView.mExpandedBubble, bubblePositioner.showBubblesVertically() ? expandedBubbleXY.y : expandedBubbleXY.x);
                                bubbleStackView.mExpandedViewContainer.setTranslationX(0.0f);
                                bubbleStackView.mExpandedViewContainer.setTranslationY(expandedViewY);
                                bubbleStackView.mExpandedViewContainer.setAlpha(1.0f);
                                final boolean showBubblesVertically = bubbleStackView.mPositioner.showBubblesVertically();
                                float f3 = showBubblesVertically ? bubbleStackView.mStackAnimationController.mStackPosition.y : bubbleStackView.mStackAnimationController.mStackPosition.x;
                                final float f4 = showBubblesVertically ? expandedBubbleXY.y : expandedBubbleXY.x;
                                long abs = bubbleStackView.getWidth() > 0 ? (long) (((Math.abs(f4 - f3) / bubbleStackView.getWidth()) * 30.0f) + 210.00002f) : 0L;
                                if (showBubblesVertically) {
                                    bubbleStackView.mExpandedViewContainerMatrix.setScale(0.9f, 0.9f, bubbleStackView.mStackOnLeftOrWillBe ? expandedBubbleXY.x + bubbleStackView.mBubbleSize + bubbleStackView.mExpandedViewPadding : expandedBubbleXY.x - bubbleStackView.mExpandedViewPadding, (bubbleStackView.mBubbleSize / 2.0f) + expandedBubbleXY.y);
                                } else {
                                    AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView.mExpandedViewContainerMatrix;
                                    float f5 = expandedBubbleXY.x;
                                    float f6 = bubbleStackView.mBubbleSize;
                                    animatableScaleMatrix.setScale(0.9f, 0.9f, (f6 / 2.0f) + f5, expandedBubbleXY.y + f6 + bubbleStackView.mExpandedViewPadding);
                                }
                                bubbleStackView.mExpandedViewContainer.setAnimationMatrix(bubbleStackView.mExpandedViewContainerMatrix);
                                BubbleExpandedView expandedView2 = bubbleStackView.getExpandedView();
                                if (expandedView2 != null) {
                                    expandedView2.setContentAlpha(0.0f);
                                    expandedView2.mPointerView.setAlpha(0.0f);
                                    expandedView2.setAlpha(0.0f);
                                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 7323766367734739256L, 0, String.valueOf(expandedView2.getBubbleKey()));
                                    }
                                    expandedView2.mIsAnimating = true;
                                }
                                ?? r1 = new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda54
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        final BubbleStackView bubbleStackView2 = BubbleStackView.this;
                                        final boolean z5 = showBubblesVertically;
                                        final float f7 = f4;
                                        bubbleStackView2.mExpandedViewAlphaAnimator.start();
                                        AnimatableScaleMatrix animatableScaleMatrix2 = bubbleStackView2.mExpandedViewContainerMatrix;
                                        Function2 function22 = PhysicsAnimator.onAnimatorCreated;
                                        PhysicsAnimator.Companion.getInstance(animatableScaleMatrix2).cancel();
                                        PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(bubbleStackView2.mExpandedViewContainerMatrix);
                                        companion.spring(AnimatableScaleMatrix.SCALE_X, 499.99997f, 0.0f, bubbleStackView2.mScaleInSpringConfig);
                                        companion.spring(AnimatableScaleMatrix.SCALE_Y, 499.99997f, 0.0f, bubbleStackView2.mScaleInSpringConfig);
                                        companion.updateListeners.add(new PhysicsAnimator.UpdateListener() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda1
                                            @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.UpdateListener
                                            public final void onAnimationUpdateForProperty(Object obj2) {
                                                BubbleStackView bubbleStackView3 = BubbleStackView.this;
                                                BubbleViewProvider bubbleViewProvider3 = bubbleStackView3.mExpandedBubble;
                                                if (bubbleViewProvider3 == null || bubbleViewProvider3.getIconView$1() == null) {
                                                    return;
                                                }
                                                bubbleStackView3.mExpandedViewContainerMatrix.postTranslate((z5 ? bubbleStackView3.mExpandedBubble.getIconView$1().getTranslationY() : bubbleStackView3.mExpandedBubble.getIconView$1().getTranslationX()) - f7, 0.0f);
                                                bubbleStackView3.mExpandedViewContainer.setAnimationMatrix(bubbleStackView3.mExpandedViewContainerMatrix);
                                            }
                                        });
                                        companion.withEndActions(new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView2, 0));
                                        companion.start();
                                    }
                                };
                                bubbleStackView.mDelayedAnimation = r1;
                                ((HandlerExecutor) bubbleStackView.mMainExecutor).executeDelayed(r1, abs);
                                bubbleStackView.logBubbleEvent(bubbleStackView.mExpandedBubble, 3);
                                bubbleStackView.logBubbleEvent(bubbleStackView.mExpandedBubble, 15);
                                BubbleStackViewManager$Companion$fromBubbleController$1 bubbleStackViewManager$Companion$fromBubbleController$1 = bubbleStackView.mManager;
                                BubbleStackView$$ExternalSyntheticLambda40 bubbleStackView$$ExternalSyntheticLambda40 = new BubbleStackView$$ExternalSyntheticLambda40(bubbleStackView);
                                BubbleController bubbleController = bubbleStackViewManager$Companion$fromBubbleController$1.$controller;
                                BubblesManager.AnonymousClass5 anonymousClass52 = bubbleController.mSysuiProxy;
                                anonymousClass52.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass52, new BubbleController$$ExternalSyntheticLambda5(bubbleController, bubbleStackView$$ExternalSyntheticLambda40, 1), 5));
                            }
                            BubbleViewProvider bubbleViewProvider3 = bubbleStackView.mExpandedBubble;
                            boolean z5 = bubbleStackView.mIsExpanded;
                            BubbleController$$ExternalSyntheticLambda2 bubbleController$$ExternalSyntheticLambda2 = bubbleStackView.mExpandListener;
                            if (bubbleController$$ExternalSyntheticLambda2 != null && bubbleViewProvider3 != null) {
                                bubbleController$$ExternalSyntheticLambda2.onBubbleExpandChanged(bubbleViewProvider3.getKey(), z5);
                            }
                            BubbleViewProvider bubbleViewProvider4 = bubbleStackView.mExpandedBubble;
                            boolean z6 = bubbleStackView.mIsExpanded;
                            if (bubbleViewProvider4 instanceof Bubble) {
                                Bubble bubble = (Bubble) bubbleViewProvider4;
                                String str = bubble.mAppName;
                                String str2 = bubble.mTitle;
                                if (str2 == null) {
                                    str2 = bubbleStackView.getResources().getString(R.string.notification_bubble_title);
                                }
                                if (str != null && !str2.equals(str)) {
                                    str2 = bubbleStackView.getResources().getString(R.string.bubble_content_description_single, str2, str);
                                }
                                bubbleStackView.announceForAccessibility(bubbleStackView.getResources().getString(z6 ? R.string.bubble_accessibility_announce_expand : R.string.bubble_accessibility_announce_collapse, str2));
                                break;
                            }
                        }
                    }
                    break;
                default:
                    BubbleBarLayerView bubbleBarLayerView = ((BubbleController) obj).mLayerView;
                    if (bubbleBarLayerView != null && !z) {
                        bubbleBarLayerView.collapse(null);
                        break;
                    }
                    break;
            }
        }

        public void onBubbleStateChange(BubbleBarUpdate bubbleBarUpdate) {
            Bundle bundle = new Bundle();
            bundle.setClassLoader(BubbleBarUpdate.class.getClassLoader());
            bundle.putParcelable("update", bubbleBarUpdate);
            IInterface iInterface = ((IBubblesImpl) this.this$0).mListener.mListener;
            if (iInterface == null) {
                Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                return;
            }
            try {
                IBubblesListener$Stub$Proxy iBubblesListener$Stub$Proxy = (IBubblesListener$Stub$Proxy) iInterface;
                Parcel obtain = Parcel.obtain(iBubblesListener$Stub$Proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.wm.shell.bubbles.IBubblesListener");
                    obtain.writeTypedObject(bundle, 0);
                    iBubblesListener$Stub$Proxy.mRemote.transact(1, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
            }
        }

        public void suppressionChanged(Bubble bubble, boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    BubbleStackView bubbleStackView = ((BubbleController) this.this$0).mStackView;
                    if (bubbleStackView != null) {
                        if (!z) {
                            BadgedImageView badgedImageView = bubble.mIconView;
                            if (badgedImageView != null) {
                                if (badgedImageView.getParent() == null) {
                                    int indexOf = Collections.unmodifiableList(bubbleStackView.mBubbleData.mBubbles).indexOf(bubble);
                                    PhysicsAnimationLayout physicsAnimationLayout = bubbleStackView.mBubbleContainer;
                                    BadgedImageView badgedImageView2 = bubble.mIconView;
                                    int i = bubbleStackView.mPositioner.mBubbleSize;
                                    physicsAnimationLayout.addViewInternal(badgedImageView2, indexOf, new FrameLayout.LayoutParams(i, i), false);
                                    bubbleStackView.updateBubbleShadows(bubbleStackView.mIsExpanded);
                                    bubbleStackView.requestUpdate();
                                    break;
                                } else {
                                    Log.e("Bubbles", "Bubble is already added to parent. Can't unsuppress: " + bubble);
                                    break;
                                }
                            }
                        } else {
                            bubbleStackView.mBubbleContainer.removeViewAt(bubbleStackView.getBubbleIndex(bubble));
                            bubbleStackView.updateExpandedView();
                            break;
                        }
                    }
                    break;
                default:
                    BubbleBarLayerView bubbleBarLayerView = ((BubbleController) this.this$0).mLayerView;
                    break;
            }
        }

        private final void addBubble$com$android$wm$shell$bubbles$BubbleController$9(Bubble bubble) {
        }

        private final void updateBubble$com$android$wm$shell$bubbles$BubbleController$9(Bubble bubble) {
        }

        private final void bubbleOrderChanged$com$android$wm$shell$bubbles$BubbleController$9(List list, boolean z) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IBubblesImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public final AnonymousClass8 mBubbleListener;
        public BubbleController mController;
        public final SingleInstanceRemoteListener mListener;

        public IBubblesImpl(BubbleController bubbleController) {
            attachInterface(this, "com.android.wm.shell.bubbles.IBubbles");
            this.mBubbleListener = new AnonymousClass8(3, this);
            this.mController = bubbleController;
            this.mListener = new SingleInstanceRemoteListener(bubbleController, new BubbleController$$ExternalSyntheticLambda14(1, this), new BubbleController$$ExternalSyntheticLambda21(1));
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
            this.mListener.unregister();
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBubblesListener$Stub$Proxy iBubblesListener$Stub$Proxy;
            IInterface queryLocalInterface;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.bubbles.IBubbles");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.bubbles.IBubbles");
                return true;
            }
            switch (i) {
                case 2:
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        iBubblesListener$Stub$Proxy = null;
                    } else {
                        IInterface queryLocalInterface2 = readStrongBinder.queryLocalInterface("com.android.wm.shell.bubbles.IBubblesListener");
                        if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IBubblesListener$Stub$Proxy)) {
                            IBubblesListener$Stub$Proxy iBubblesListener$Stub$Proxy2 = new IBubblesListener$Stub$Proxy();
                            iBubblesListener$Stub$Proxy2.mRemote = readStrongBinder;
                            iBubblesListener$Stub$Proxy = iBubblesListener$Stub$Proxy2;
                        } else {
                            iBubblesListener$Stub$Proxy = (IBubblesListener$Stub$Proxy) queryLocalInterface2;
                        }
                    }
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(8, this, iBubblesListener$Stub$Proxy));
                    return true;
                case 3:
                    IBinder readStrongBinder2 = parcel.readStrongBinder();
                    if (readStrongBinder2 != null && (queryLocalInterface = readStrongBinder2.queryLocalInterface("com.android.wm.shell.bubbles.IBubblesListener")) != null && (queryLocalInterface instanceof IBubblesListener$Stub$Proxy)) {
                    }
                    parcel.enforceNoDataAvail();
                    ShellExecutor shellExecutor = BubbleController.this.mMainExecutor;
                    SingleInstanceRemoteListener singleInstanceRemoteListener = this.mListener;
                    Objects.requireNonNull(singleInstanceRemoteListener);
                    ((HandlerExecutor) shellExecutor).execute(new BubbleController$6$$ExternalSyntheticLambda0(3, singleInstanceRemoteListener));
                    return true;
                case 4:
                    String readString = parcel.readString();
                    int readInt = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda4(this, readString, readInt, 2));
                    return true;
                case 5:
                    final String readString2 = parcel.readString();
                    final long readLong = parcel.readLong();
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleController$IBubblesImpl$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            BubbleViewProvider bubbleViewProvider;
                            BubbleBarLayerView bubbleBarLayerView;
                            Bubble bubbleInStackWithKey;
                            BubbleController.IBubblesImpl iBubblesImpl = BubbleController.IBubblesImpl.this;
                            String str = readString2;
                            long j = readLong;
                            BubbleController bubbleController = iBubblesImpl.mController;
                            BubbleData bubbleData = bubbleController.mBubbleData;
                            bubbleData.getClass();
                            if (bubbleData.hasAnyBubbleWithKey(str) && ((bubbleInStackWithKey = bubbleData.getBubbleInStackWithKey(str)) == null || Math.max(bubbleInStackWithKey.mLastUpdated, bubbleInStackWithKey.mLastAccessed) <= j)) {
                                bubbleData.doRemove(18, str);
                                bubbleData.dispatchPendingChanges();
                            }
                            if (bubbleData.mBubbles.isEmpty() || (bubbleViewProvider = bubbleController.mBubbleData.mSelectedBubble) == null || (bubbleBarLayerView = bubbleController.mLayerView) == null) {
                                return;
                            }
                            bubbleBarLayerView.showExpandedView(bubbleViewProvider);
                        }
                    });
                    return true;
                case 6:
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(this, 2));
                    return true;
                case 7:
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(this, 3));
                    return true;
                case 8:
                    String readString3 = parcel.readString();
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(7, this, readString3));
                    return true;
                case 9:
                    final int readInt2 = parcel.readInt();
                    final int readInt3 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleController$IBubblesImpl$$ExternalSyntheticLambda9
                        @Override // java.lang.Runnable
                        public final void run() {
                            BubbleController.IBubblesImpl iBubblesImpl = BubbleController.IBubblesImpl.this;
                            int i3 = readInt2;
                            int i4 = readInt3;
                            BubbleController bubbleController = iBubblesImpl.mController;
                            Point point = new Point(i3, i4);
                            BubbleBarLayerView bubbleBarLayerView = bubbleController.mLayerView;
                            if (bubbleBarLayerView == null) {
                                return;
                            }
                            bubbleBarLayerView.showUserEducation(point);
                        }
                    });
                    return true;
                case 10:
                    BubbleBarLocation bubbleBarLocation = (BubbleBarLocation) parcel.readTypedObject(BubbleBarLocation.CREATOR);
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(6, this, bubbleBarLocation));
                    return true;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    int readInt4 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda2(readInt4, 1, this));
                    return true;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    BubbleBarLocation bubbleBarLocation2 = (BubbleBarLocation) parcel.readTypedObject(BubbleBarLocation.CREATOR);
                    int readInt5 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda4(this, bubbleBarLocation2, readInt5, 1));
                    return true;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    ShortcutInfo shortcutInfo = (ShortcutInfo) parcel.readTypedObject(ShortcutInfo.CREATOR);
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(this, shortcutInfo));
                    return true;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    Intent intent = (Intent) parcel.readTypedObject(Intent.CREATOR);
                    parcel.enforceNoDataAvail();
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(this, intent));
                    return true;
                case 15:
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(this, 4));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
