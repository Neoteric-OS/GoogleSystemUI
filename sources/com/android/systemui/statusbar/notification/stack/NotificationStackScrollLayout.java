package com.android.systemui.statusbar.notification.stack;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.MathUtils;
import android.util.Pair;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.BouncerPanelExpansionCalculator;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.ExpandHelper;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.scene.shared.flag.SceneContainerFlag;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda11;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda3;
import com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda6;
import com.android.systemui.shade.TouchLogger;
import com.android.systemui.statusbar.EmptyShadeView;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.footer.shared.FooterViewRefactor;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.logging.NotificationLogger$$ExternalSyntheticLambda2;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.StackScrollerDecorView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator;
import com.android.systemui.statusbar.notification.stack.StackStateAnimator.AnonymousClass2;
import com.android.systemui.statusbar.notification.stack.ui.view.NotificationScrollView;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$3;
import com.android.systemui.statusbar.notification.ui.viewbinder.HeadsUpNotificationViewBinderKt$isHeadsUpAnimatingAway$1$1;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.ListenerSet;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationStackScrollLayout extends ViewGroup implements Dumpable, NotificationScrollView {
    public static final /* synthetic */ int $r8$clinit = 0;
    static final float RUBBER_BAND_FACTOR_NORMAL = 0.1f;
    public final AnonymousClass4 collectVisibleLocationsCallable;
    public int mActivePointerId;
    public ActivityStarter mActivityStarter;
    public final ArrayList mAddedHeadsUpChildren;
    public final AmbientState mAmbientState;
    public boolean mAnimateNextTopPaddingChange;
    public boolean mAnimateStackYForContentHeightChange;
    public final ArrayList mAnimationEvents;
    public final HashSet mAnimationFinishedRunnables;
    public boolean mAnimationRunning;
    public boolean mAnimationsEnabled;
    public final Rect mBackgroundAnimationRect;
    public float mBackgroundXFactor;
    public boolean mBackwardScrollable;
    public final float[] mBgCornerRadii;
    public int mBottomPadding;
    public boolean mChangePositionInProgress;
    public boolean mCheckForLeavebehind;
    public boolean mChildTransferInProgress;
    public final ArrayList mChildrenChangingPositions;
    public final HashSet mChildrenToAddAnimated;
    public final ArrayList mChildrenToRemoveAnimated;
    public boolean mChildrenUpdateRequested;
    public final AnonymousClass1 mChildrenUpdater;
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda5 mClearAllAnimationListener;
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda7 mClearAllFinishedWhilePanelExpandedRunnable;
    public boolean mClearAllInProgress;
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda5 mClearAllListener;
    public final HashSet mClearTransientViewsWhenFinished;
    public final Rect mClipRect;
    public int mContentHeight;
    public boolean mContinuousShadowUpdate;
    public NotificationStackScrollLayoutController mController;
    public int mCornerRadius;
    public int mCurrentStackHeight;
    public boolean mDisallowDismissInThisMotion;
    public boolean mDisallowScrollingInThisMotion;
    public boolean mDismissUsingRowTranslationX;
    public boolean mDontReportNextOverScroll;
    public int mDownX;
    public EmptyShadeView mEmptyShadeView;
    public boolean mEverythingNeedsAnimation;
    public final ExpandHelper mExpandHelper;
    public final AnonymousClass6 mExpandHelperCallback;
    public ExpandableNotificationRow mExpandedGroupView;
    public float mExpandedHeight;
    public final ArrayList mExpandedHeightListeners;
    public boolean mExpandedInThisMotion;
    public boolean mExpandingNotification;
    public ExpandableNotificationRow mExpandingNotificationRow;
    public float mExtraTopInsetForFullShadeTransition;
    public Runnable mFinishScrollingCallback;
    public boolean mFlingAfterUpEvent;
    public FooterView mFooterView;
    public boolean mForceNoOverlappingRendering;
    public View mForcedScroll;
    public boolean mForwardScrollable;
    public final HashSet mFromMoreCardAdditions;
    public int mGapHeight;
    public long mGoToFullShadeDelay;
    public boolean mGoToFullShadeNeedsAnimation;
    public final GroupExpansionManagerImpl mGroupExpansionManager;
    public final GroupMembershipManagerImpl mGroupMembershipManager;
    boolean mHeadsUpAnimatingAway;
    public final HeadsUpNotificationViewBinderKt$isHeadsUpAnimatingAway$1$1 mHeadsUpAnimatingAwayListener;
    public HeadsUpAppearanceController mHeadsUpAppearanceController;
    public final AnonymousClass6 mHeadsUpCallback;
    public final HashSet mHeadsUpChangeAnimations;
    public boolean mHeadsUpGoingAwayAnimationsAllowed;
    public final ListenerSet mHeadsUpHeightChangedListeners;
    public int mHeadsUpInset;
    public boolean mHideSensitiveNeedsAnimation;
    public Interpolator mHideXInterpolator;
    public boolean mHighPriorityBeforeSpeedBump;
    int mImeInset;
    public boolean mInHeadsUpPinnedMode;
    public float mInitialTouchX;
    public float mInitialTouchY;
    public final AnonymousClass5 mInsetsCallback;
    public float mInterpolatedHideAmount;
    public float mIntrinsicContentHeight;
    public int mIntrinsicPadding;
    public boolean mIsBeingDragged;
    public boolean mIsClipped;
    public boolean mIsExpanded;
    public boolean mIsExpansionChanging;
    public boolean mIsInsetAnimationRunning;
    public boolean mKeyguardBypassEnabled;
    public String mLastInitViewDumpString;
    public long mLastInitViewElapsedRealtime;
    public int mLastMotionY;
    public float mLastSentAppear;
    public float mLastSentExpandedHeight;
    public String mLastUpdateSidePaddingDumpString;
    public long mLastUpdateSidePaddingElapsedRealtime;
    public LaunchAnimationParameters mLaunchAnimationParams;
    public final Path mLaunchedNotificationClipPath;
    public final float[] mLaunchedNotificationRadii;
    public boolean mLaunchingNotification;
    public boolean mLaunchingNotificationNeedsToBeClipped;
    public NotificationLogger$$ExternalSyntheticLambda2 mLegacyLocationsChangedListener;
    public float mLinearHideAmount;
    public NotificationStackScrollLogger mLogger;
    public int mMaxDisplayedNotifications;
    public int mMaxLayoutHeight;
    public float mMaxOverScroll;
    public int mMaxScrollAfterExpand;
    public int mMaxTopPadding;
    public int mMaximumVelocity;
    public int mMinInteractionHeight;
    public float mMinTopOverScrollToEscape;
    public int mMinimumPaddings;
    public int mMinimumVelocity;
    public boolean mNeedViewResizeAnimation;
    public boolean mNeedsAnimation;
    public NotificationStackSizeCalculator mNotificationStackSizeCalculator;
    public final AnonymousClass6 mOnChildHeightChangedListener;
    public final AnonymousClass7 mOnChildSensitivityChangedListener;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mOnEmptySpaceClickListener;
    public NotificationPanelViewController.AnonymousClass10 mOnHeightChangedListener;
    public SharedNotificationContainerBinder$bind$3 mOnHeightChangedRunnable;
    public QuickSettingsControllerImpl$$ExternalSyntheticLambda6 mOnStackYChanged;
    public boolean mOnlyScrollingInThisMotion;
    public final AnonymousClass3 mOutlineProvider;
    public float mOverScrolledBottomPixels;
    public float mOverScrolledTopPixels;
    public int mOverflingDistance;
    public QuickSettingsControllerImpl.NsslOverscrollTopChangedListener mOverscrollTopChangedListener;
    public int mOwnScrollY;
    public int mPaddingBetweenElements;
    public boolean mPanelTracking;
    public boolean mPulsing;
    public float mQsExpansionFraction;
    public boolean mQsFullScreen;
    public ViewGroup mQsHeader;
    public final Rect mQsHeaderBound;
    public int mQsScrollBoundaryPosition;
    public int mQsTilePadding;
    public final NotificationStackScrollLayout$$ExternalSyntheticLambda2 mReflingAndAnimateScroll;
    public Rect mRequestedClipBounds;
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda7 mResetUserExpandedStatesRunnable;
    public final Path mRoundedClipPath;
    public int mRoundedRectClippingBottom;
    public int mRoundedRectClippingLeft;
    public int mRoundedRectClippingRight;
    public int mRoundedRectClippingTop;
    public int mRoundedRectClippingYTranslation;
    public final AnonymousClass1 mRunningAnimationUpdater;
    public final AnonymousClass6 mScrollAdapter;
    public QuickSettingsControllerImpl$$ExternalSyntheticLambda6 mScrollListener;
    public final ScrollViewFields mScrollViewFields;
    public boolean mScrollable;
    public boolean mScrolledToTopOnFirstDown;
    public OverScroller mScroller;
    public boolean mScrollingEnabled;
    public final NotificationSection[] mSections;
    public final NotificationSectionsManager mSectionsManager;
    public boolean mShadeNeedsToClose;
    public final NotificationStackScrollLayout$$ExternalSyntheticLambda0 mShadowUpdater;
    public NotificationShelf mShelf;
    public boolean mShouldShowShelfOnly;
    public boolean mShouldSkipTopPaddingAnimationAfterFold;
    public boolean mShouldUseRoundedRectClipping;
    public boolean mShouldUseSplitNotificationShade;
    public int mSidePaddings;
    public boolean mSkinnyNotifsInLandscape;
    public float mSlopMultiplier;
    public boolean mSpeedBumpIndexDirty;
    public final int mSplitShadeMinContentHeight;
    public SplitShadeStateControllerImpl mSplitShadeStateController;
    public final StackScrollAlgorithm mStackScrollAlgorithm;
    public final StackStateAnimator mStateAnimator;
    int mStatusBarHeight;
    public int mStatusBarState;
    public boolean mSuppressChildrenMeasureAndLayout;
    public NotificationSwipeHelper mSwipeHelper;
    public final ArrayList mSwipedOutViews;
    public final int[] mTempInt2;
    public final ArrayList mTmpList;
    public final Rect mTmpRect;
    public final ArrayList mTmpSortedChildren;
    public ExpandableNotificationRow mTopHeadsUpRow;
    public boolean mTopPaddingNeedsAnimation;
    public float mTopPaddingOverflow;
    public NotificationStackScrollLayoutController.TouchHandler mTouchHandler;
    public boolean mTouchIsClick;
    public int mTouchSlop;
    public VelocityTracker mVelocityTracker;
    public final NotificationStackScrollLayout$$ExternalSyntheticLambda1 mViewPositionComparator;
    public int mWaterfallTopInset;
    public boolean mWillExpand;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$6, reason: invalid class name */
    public final class AnonymousClass6 {
        public /* synthetic */ AnonymousClass6() {
        }

        public boolean canChildBeExpanded(ExpandableView expandableView) {
            if (expandableView instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
                if (expandableNotificationRow.isExpandable$1() && !expandableNotificationRow.areGutsExposed() && (NotificationStackScrollLayout.this.mIsExpanded || !expandableNotificationRow.mIsPinned)) {
                    return true;
                }
            }
            return false;
        }

        public void expansionStateChanged(boolean z) {
            NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
            notificationStackScrollLayout.mExpandingNotification = z;
            if (notificationStackScrollLayout.mExpandedInThisMotion) {
                return;
            }
            notificationStackScrollLayout.mMaxScrollAfterExpand = notificationStackScrollLayout.mOwnScrollY;
            notificationStackScrollLayout.mExpandedInThisMotion = true;
        }

        public void setUserExpandedChild(View view, boolean z) {
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (z) {
                    NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                    if (notificationStackScrollLayout.onKeyguard()) {
                        expandableNotificationRow.setUserLocked(false);
                        notificationStackScrollLayout.updateContentHeight();
                        notificationStackScrollLayout.notifyHeightChangeListener(expandableNotificationRow, false);
                        return;
                    }
                }
                expandableNotificationRow.setUserExpanded(z, true);
                expandableNotificationRow.onExpandedByGesture(z);
            }
        }

        public void setUserLockedChild(View view, boolean z) {
            if (view instanceof ExpandableNotificationRow) {
                ((ExpandableNotificationRow) view).setUserLocked(z);
            }
            NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
            notificationStackScrollLayout.cancelLongPress();
            notificationStackScrollLayout.requestDisallowInterceptTouchEvent(true);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationEvent {
        public static final AnimationFilter[] FILTERS;
        public static final int[] LENGTHS;
        public final int animationType;
        public final AnimationFilter filter;
        public boolean headsUpFromBottom;
        public final long length;
        public final ExpandableView mChangingView;
        public View viewAfterChangingView;

        static {
            AnimationFilter animationFilter = new AnimationFilter();
            animationFilter.animateAlpha = true;
            animationFilter.animateHeight = true;
            animationFilter.animateTopInset = true;
            animationFilter.animateY = true;
            animationFilter.animateZ = true;
            animationFilter.hasDelays = true;
            AnimationFilter animationFilter2 = new AnimationFilter();
            animationFilter2.animateAlpha = true;
            animationFilter2.animateHeight = true;
            animationFilter2.animateTopInset = true;
            animationFilter2.animateY = true;
            animationFilter2.animateZ = true;
            animationFilter2.hasDelays = true;
            AnimationFilter animationFilter3 = new AnimationFilter();
            animationFilter3.animateHeight = true;
            animationFilter3.animateTopInset = true;
            animationFilter3.animateY = true;
            animationFilter3.animateZ = true;
            animationFilter3.hasDelays = true;
            AnimationFilter animationFilter4 = new AnimationFilter();
            animationFilter4.animateHeight = true;
            animationFilter4.animateTopInset = true;
            animationFilter4.animateY = true;
            animationFilter4.animateZ = true;
            AnimationFilter animationFilter5 = new AnimationFilter();
            animationFilter5.animateZ = true;
            AnimationFilter animationFilter6 = new AnimationFilter();
            AnimationFilter animationFilter7 = new AnimationFilter();
            animationFilter7.animateAlpha = true;
            animationFilter7.animateHeight = true;
            animationFilter7.animateTopInset = true;
            animationFilter7.animateY = true;
            animationFilter7.animateZ = true;
            AnimationFilter animationFilter8 = new AnimationFilter();
            animationFilter8.animateHeight = true;
            animationFilter8.animateTopInset = true;
            animationFilter8.animateY = true;
            animationFilter8.animateZ = true;
            animationFilter8.hasDelays = true;
            AnimationFilter animationFilter9 = new AnimationFilter();
            animationFilter9.animateHideSensitive = true;
            AnimationFilter animationFilter10 = new AnimationFilter();
            animationFilter10.animateHeight = true;
            animationFilter10.animateTopInset = true;
            animationFilter10.animateY = true;
            animationFilter10.animateZ = true;
            AnimationFilter animationFilter11 = new AnimationFilter();
            animationFilter11.animateAlpha = true;
            animationFilter11.animateHeight = true;
            animationFilter11.animateTopInset = true;
            animationFilter11.animateY = true;
            animationFilter11.animateZ = true;
            AnimationFilter animationFilter12 = new AnimationFilter();
            animationFilter12.animateHeight = true;
            animationFilter12.animateTopInset = true;
            animationFilter12.animateY = true;
            animationFilter12.animateZ = true;
            AnimationFilter animationFilter13 = new AnimationFilter();
            animationFilter13.animateHeight = true;
            animationFilter13.animateTopInset = true;
            animationFilter13.animateY = true;
            animationFilter13.animateZ = true;
            animationFilter13.hasDelays = true;
            AnimationFilter animationFilter14 = new AnimationFilter();
            animationFilter14.animateHeight = true;
            animationFilter14.animateTopInset = true;
            animationFilter14.animateY = true;
            animationFilter14.animateZ = true;
            animationFilter14.hasDelays = true;
            AnimationFilter animationFilter15 = new AnimationFilter();
            animationFilter15.animateHeight = true;
            animationFilter15.animateTopInset = true;
            animationFilter15.animateY = true;
            animationFilter15.animateZ = true;
            AnimationFilter animationFilter16 = new AnimationFilter();
            animationFilter16.animateAlpha = true;
            animationFilter16.animateHideSensitive = true;
            animationFilter16.animateHeight = true;
            animationFilter16.animateTopInset = true;
            animationFilter16.animateY = true;
            animationFilter16.animateZ = true;
            AnimationFilter animationFilter17 = new AnimationFilter();
            animationFilter17.animateHeight = true;
            animationFilter17.animateTopInset = true;
            animationFilter17.animateY = true;
            animationFilter17.animateZ = true;
            animationFilter17.hasDelays = true;
            AnimationFilter animationFilter18 = new AnimationFilter();
            animationFilter18.animateHeight = true;
            animationFilter18.animateTopInset = true;
            animationFilter18.animateY = true;
            animationFilter18.animateZ = true;
            animationFilter18.hasDelays = true;
            FILTERS = new AnimationFilter[]{animationFilter, animationFilter2, animationFilter3, animationFilter4, animationFilter5, animationFilter6, animationFilter7, animationFilter8, animationFilter9, animationFilter10, animationFilter11, animationFilter12, animationFilter13, animationFilter14, animationFilter15, animationFilter16, animationFilter17, animationFilter18};
            LENGTHS = new int[]{464, 464, 360, 360, 220, 220, 360, 448, 360, 360, 360, 400, 400, 400, 360, 360, 400, 400};
        }

        public AnimationEvent(ExpandableView expandableView, int i) {
            this(expandableView, i, LENGTHS[i]);
        }

        public AnimationEvent(ExpandableView expandableView, int i, long j) {
            AnimationFilter animationFilter = FILTERS[i];
            AnimationUtils.currentAnimationTimeMillis();
            this.mChangingView = expandableView;
            this.animationType = i;
            this.length = j;
            this.filter = animationFilter;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r10v15, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$7] */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$3] */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$5] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$1] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r5v10, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$1] */
    public NotificationStackScrollLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        final int i = 1;
        final int i2 = 0;
        this.mShadeNeedsToClose = false;
        this.mCurrentStackHeight = Integer.MAX_VALUE;
        this.mActivePointerId = -1;
        this.mImeInset = 0;
        ScrollViewFields scrollViewFields = new ScrollViewFields();
        scrollViewFields.isScrolledToTop = true;
        this.mScrollViewFields = scrollViewFields;
        this.mChildrenToAddAnimated = new HashSet();
        this.mAddedHeadsUpChildren = new ArrayList();
        this.mChildrenToRemoveAnimated = new ArrayList();
        this.mChildrenChangingPositions = new ArrayList();
        this.mFromMoreCardAdditions = new HashSet();
        this.mAnimationEvents = new ArrayList();
        this.mSwipedOutViews = new ArrayList();
        this.mAnimationsEnabled = false;
        this.mSpeedBumpIndexDirty = true;
        new CopyOnWriteArrayList();
        this.mHeadsUpHeightChangedListeners = new ListenerSet();
        this.mIsExpanded = true;
        this.mChildrenUpdater = new ViewTreeObserver.OnPreDrawListener(this) { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.1
            public final /* synthetic */ NotificationStackScrollLayout this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Code restructure failed: missing block: B:153:0x0282, code lost:
            
                if (r5 != false) goto L136;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:226:0x039e  */
            /* JADX WARN: Removed duplicated region for block: B:242:0x03d4  */
            /* JADX WARN: Removed duplicated region for block: B:279:0x048a  */
            /* JADX WARN: Removed duplicated region for block: B:349:0x05fb A[LOOP:11: B:348:0x05f9->B:349:0x05fb, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:353:0x0616  */
            /* JADX WARN: Removed duplicated region for block: B:356:0x0623  */
            /* JADX WARN: Removed duplicated region for block: B:399:0x069c A[LOOP:13: B:398:0x069a->B:399:0x069c, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:403:0x06b4  */
            /* JADX WARN: Removed duplicated region for block: B:450:0x079b  */
            /* JADX WARN: Removed duplicated region for block: B:510:0x085c  */
            /* JADX WARN: Removed duplicated region for block: B:590:0x0a11  */
            /* JADX WARN: Removed duplicated region for block: B:603:0x0a5b  */
            /* JADX WARN: Type inference failed for: r6v14, types: [android.view.View, android.widget.TextView] */
            /* JADX WARN: Type inference failed for: r6v20, types: [android.widget.TextView] */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final boolean onPreDraw() {
                /*
                    Method dump skipped, instructions count: 2716
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.AnonymousClass1.onPreDraw():boolean");
            }
        };
        this.mTempInt2 = new int[2];
        this.mAnimationFinishedRunnables = new HashSet();
        this.mClearTransientViewsWhenFinished = new HashSet();
        this.mHeadsUpChangeAnimations = new HashSet();
        this.mTmpList = new ArrayList();
        this.mRunningAnimationUpdater = new ViewTreeObserver.OnPreDrawListener(this) { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.1
            public final /* synthetic */ NotificationStackScrollLayout this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                /*
                    Method dump skipped, instructions count: 2716
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.AnonymousClass1.onPreDraw():boolean");
            }
        };
        this.mTmpSortedChildren = new ArrayList();
        this.mQsHeaderBound = new Rect();
        this.mShadowUpdater = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                int i3 = NotificationStackScrollLayout.$r8$clinit;
                notificationStackScrollLayout.updateViewShadows();
                return true;
            }
        };
        this.mViewPositionComparator = new NotificationStackScrollLayout$$ExternalSyntheticLambda1();
        this.mOutlineProvider = new ViewOutlineProvider() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.3
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                if (!NotificationStackScrollLayout.this.mAmbientState.isHiddenAtAll()) {
                    ViewOutlineProvider.BACKGROUND.getOutline(view, outline);
                    return;
                }
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                float interpolation = notificationStackScrollLayout.mHideXInterpolator.getInterpolation((1.0f - notificationStackScrollLayout.mLinearHideAmount) * notificationStackScrollLayout.mBackgroundXFactor);
                NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayout.this;
                Rect rect = notificationStackScrollLayout2.mBackgroundAnimationRect;
                int i3 = notificationStackScrollLayout2.mCornerRadius;
                outline.setRoundRect(rect, MathUtils.lerp(i3 / 2.0f, i3, interpolation));
                outline.setAlpha(1.0f - NotificationStackScrollLayout.this.mAmbientState.mHideAmount);
            }
        };
        new Callable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                int i3 = NotificationStackScrollLayout.$r8$clinit;
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                notificationStackScrollLayout.getClass();
                HashMap hashMap = new HashMap();
                int childCount = notificationStackScrollLayout.getChildCount();
                for (int i4 = 0; i4 < childCount; i4++) {
                    ExpandableView expandableView = (ExpandableView) notificationStackScrollLayout.getChildAt(i4);
                    if (expandableView instanceof ExpandableNotificationRow) {
                        ((ExpandableNotificationRow) expandableView).collectVisibleLocations(hashMap);
                    }
                }
                return hashMap;
            }
        };
        this.mInsetsCallback = new WindowInsetsAnimation.Callback() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.5
            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                NotificationStackScrollLayout.this.mIsInsetAnimationRunning = false;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                NotificationStackScrollLayout.this.mIsInsetAnimationRunning = true;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                int i3 = NotificationStackScrollLayout.$r8$clinit;
                notificationStackScrollLayout.updateImeInset(windowInsets);
                return windowInsets;
            }
        };
        this.mInterpolatedHideAmount = 0.0f;
        this.mLinearHideAmount = 0.0f;
        this.mBackgroundXFactor = 1.0f;
        this.mMaxDisplayedNotifications = -1;
        this.mClipRect = new Rect();
        this.mHeadsUpGoingAwayAnimationsAllowed = true;
        this.mReflingAndAnimateScroll = new NotificationStackScrollLayout$$ExternalSyntheticLambda2(this, 0);
        this.mBackgroundAnimationRect = new Rect();
        this.mExpandedHeightListeners = new ArrayList();
        this.mTmpRect = new Rect();
        this.mHideXInterpolator = Interpolators.FAST_OUT_SLOW_IN;
        this.mRoundedClipPath = new Path();
        this.mLaunchedNotificationClipPath = new Path();
        this.mShouldUseRoundedRectClipping = false;
        this.mBgCornerRadii = new float[8];
        this.mAnimateStackYForContentHeightChange = false;
        this.mLaunchedNotificationRadii = new float[8];
        this.mDismissUsingRowTranslationX = true;
        this.mShouldSkipTopPaddingAnimationAfterFold = false;
        this.mSplitShadeStateController = null;
        this.mOnChildHeightChangedListener = new AnonymousClass6();
        this.mOnChildSensitivityChangedListener = new NotificationEntry.OnSensitivityChangedListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.7
            @Override // com.android.systemui.statusbar.notification.collection.NotificationEntry.OnSensitivityChangedListener
            public final void onSensitivityChanged(NotificationEntry notificationEntry) {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                if (notificationStackScrollLayout.mAnimationsEnabled) {
                    notificationStackScrollLayout.mHideSensitiveNeedsAnimation = true;
                    notificationStackScrollLayout.requestChildrenUpdate();
                }
            }
        };
        this.mScrollAdapter = new AnonymousClass6();
        this.mSuppressChildrenMeasureAndLayout = false;
        this.mHeadsUpCallback = new AnonymousClass6();
        this.mExpandHelperCallback = new AnonymousClass6();
        Resources resources = getResources();
        FeatureFlags featureFlags = (FeatureFlags) Dependency.sDependency.getDependencyInner(FeatureFlags.class);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlags.getClass();
        NotificationSectionsManager notificationSectionsManager = (NotificationSectionsManager) Dependency.sDependency.getDependencyInner(NotificationSectionsManager.class);
        this.mSectionsManager = notificationSectionsManager;
        if (notificationSectionsManager.initialized) {
            throw new IllegalStateException("NotificationSectionsManager already initialized");
        }
        notificationSectionsManager.initialized = true;
        notificationSectionsManager.parent = this;
        notificationSectionsManager.reinflateViews();
        ((ConfigurationControllerImpl) notificationSectionsManager.configurationController).addCallback(notificationSectionsManager.configurationListener);
        notificationSectionsManager.sectionsFeatureManager.getClass();
        int[] iArr = {1, 8, 2, 9, 3, 7, 4, 5, 10, 11, 12, 13, 6};
        ArrayList arrayList = new ArrayList(13);
        for (int i3 = 0; i3 < 13; i3++) {
            arrayList.add(new NotificationSection(iArr[i3]));
        }
        this.mSections = (NotificationSection[]) arrayList.toArray(new NotificationSection[0]);
        this.mAmbientState = (AmbientState) Dependency.sDependency.getDependencyInner(AmbientState.class);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_min_height);
        resources.getDimensionPixelSize(R.dimen.notification_max_height);
        this.mSplitShadeMinContentHeight = resources.getDimensionPixelSize(R.dimen.nssl_split_shade_min_content_height);
        ExpandHelper expandHelper = new ExpandHelper(getContext(), this.mExpandHelperCallback, dimensionPixelSize);
        this.mExpandHelper = expandHelper;
        expandHelper.mEventSource = this;
        expandHelper.mScrollAdapter = this.mScrollAdapter;
        this.mStackScrollAlgorithm = new StackScrollAlgorithm(context, this);
        this.mStateAnimator = new StackStateAnimator(context, this);
        setOutlineProvider(this.mOutlineProvider);
        setWillNotDraw(true);
        this.mGroupMembershipManager = (GroupMembershipManagerImpl) Dependency.sDependency.getDependencyInner(GroupMembershipManagerImpl.class);
        this.mGroupExpansionManager = (GroupExpansionManagerImpl) Dependency.sDependency.getDependencyInner(GroupExpansionManagerImpl.class);
        setImportantForAccessibility(1);
        setWindowInsetsAnimationCallback(this.mInsetsCallback);
    }

    public static boolean includeChildInClearAll(ExpandableNotificationRow expandableNotificationRow, int i) {
        if (expandableNotificationRow != null && !expandableNotificationRow.areGutsExposed() && expandableNotificationRow.mEntry.hasFinishedInitialization() && expandableNotificationRow.mEntry.isClearable() && (!expandableNotificationRow.shouldShowPublic() || !expandableNotificationRow.mSensitiveHiddenInGeneral)) {
            if (i == 0) {
                return true;
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown selection: "));
                }
                if (expandableNotificationRow.mEntry.mBucket == 6) {
                    return true;
                }
            } else if (expandableNotificationRow.mEntry.mBucket < 6) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPinnedHeadsUp(View view) {
        if (!(view instanceof ExpandableNotificationRow)) {
            return false;
        }
        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
        return expandableNotificationRow.mIsHeadsUp && expandableNotificationRow.mIsPinned;
    }

    public final void addTransientView(View view, int i) {
        NotificationStackScrollLogger notificationStackScrollLogger = this.mLogger;
        if (notificationStackScrollLogger != null && (view instanceof ExpandableNotificationRow)) {
            NotificationEntry notificationEntry = ((ExpandableNotificationRow) view).mEntry;
            notificationStackScrollLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            NotificationStackScrollLogger$addTransientRow$2 notificationStackScrollLogger$addTransientRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$addTransientRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("addTransientRow to NSSL: childKey: ", logMessage.getStr1(), " -- index: ", logMessage.getInt1());
                }
            };
            LogBuffer logBuffer = notificationStackScrollLogger.notificationRenderBuffer;
            LogMessage obtain = logBuffer.obtain("NotificationStackScroll", logLevel, notificationStackScrollLogger$addTransientRow$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.int1 = i;
            logBuffer.commit(obtain);
        }
        super.addTransientView(view, i);
    }

    public final void animateScroll() {
        if (!this.mScroller.computeScrollOffset()) {
            Runnable runnable = this.mFinishScrollingCallback;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        int i = this.mOwnScrollY;
        int currY = this.mScroller.getCurrY();
        if (i != currY) {
            int scrollRange = getScrollRange();
            if ((currY < 0 && i >= 0) || (currY > scrollRange && i <= scrollRange)) {
                float currVelocity = this.mScroller.getCurrVelocity();
                if (currVelocity >= this.mMinimumVelocity) {
                    this.mMaxOverScroll = (Math.abs(currVelocity) / 1000.0f) * this.mOverflingDistance;
                }
            }
            customOverScrollBy(currY - i, i, scrollRange, (int) this.mMaxOverScroll);
        }
        postOnAnimation(this.mReflingAndAnimateScroll);
    }

    public final void applyCurrentState() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ExpandableView expandableView = (ExpandableView) getChildAt(i);
            ExpandableViewState expandableViewState = expandableView.mViewState;
            if (!expandableViewState.gone) {
                expandableViewState.applyToView(expandableView);
            }
        }
        NotificationLogger$$ExternalSyntheticLambda2 notificationLogger$$ExternalSyntheticLambda2 = this.mLegacyLocationsChangedListener;
        if (notificationLogger$$ExternalSyntheticLambda2 != null) {
            notificationLogger$$ExternalSyntheticLambda2.f$0.onChildLocationsChanged();
        }
        Iterator it = this.mAnimationFinishedRunnables.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.mAnimationFinishedRunnables.clear();
        setAnimationRunning(false);
        updateViewShadows();
    }

    public final float calculateAppearFraction(float f) {
        if (!isHeadsUpTransition()) {
            return this.mAmbientState.mExpansionFraction;
        }
        float appearEndPosition = getAppearEndPosition();
        float appearStartPosition = getAppearStartPosition();
        return MathUtils.constrain((f - appearStartPosition) / (appearEndPosition - appearStartPosition), -1.0f, 1.0f);
    }

    @Override // android.view.View
    public final void cancelLongPress() {
        this.mSwipeHelper.cancelLongPress();
    }

    public final void changeViewPosition(ExpandableView expandableView, int i) {
        Assert.isMainThread();
        if (this.mChangePositionInProgress) {
            throw new IllegalStateException("Reentrant call to changeViewPosition");
        }
        int indexOfChild = indexOfChild(expandableView);
        boolean z = false;
        if (indexOfChild == -1) {
            if ((expandableView instanceof ExpandableNotificationRow) && expandableView.mTransientContainer != null) {
                z = true;
            }
            StringBuilder sb = new StringBuilder("Attempting to re-position ");
            sb.append(z ? "transient" : "");
            sb.append(" view {");
            sb.append(expandableView);
            sb.append("}");
            Log.e("StackScroller", sb.toString());
            return;
        }
        if (expandableView == null || expandableView.getParent() != this || indexOfChild == i) {
            return;
        }
        this.mChangePositionInProgress = true;
        expandableView.mChangingPosition = true;
        removeView(expandableView);
        addView(expandableView, i);
        expandableView.mChangingPosition = false;
        this.mChangePositionInProgress = false;
        if (this.mIsExpanded && this.mAnimationsEnabled && expandableView.getVisibility() != 8) {
            this.mChildrenChangingPositions.add(expandableView);
            this.mNeedsAnimation = true;
        }
    }

    public final void clampScrollPosition() {
        int scrollRange = getScrollRange();
        if (scrollRange < this.mOwnScrollY) {
            AmbientState ambientState = this.mAmbientState;
            if (ambientState.mClearAllInProgress) {
                return;
            }
            setOwnScrollY(scrollRange, scrollRange < (this.mShouldUseSplitNotificationShade ? this.mSidePaddings : ambientState.mTopPadding - this.mQsScrollBoundaryPosition) && this.mAnimateStackYForContentHeightChange);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void clearChildFocus(View view) {
        super.clearChildFocus(view);
        if (this.mForcedScroll == view) {
            this.mForcedScroll = null;
        }
    }

    public final void clearNotifications(int i, boolean z, boolean z2) {
        int childCount = getChildCount();
        ArrayList arrayList = new ArrayList(childCount);
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((childAt instanceof SectionHeaderView) && z2) {
                arrayList.add(childAt);
            }
            if (childAt instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) childAt;
                if (isVisible(expandableNotificationRow) && includeChildInClearAll(expandableNotificationRow, i)) {
                    arrayList.add(expandableNotificationRow);
                }
                List attachedChildren = expandableNotificationRow.getAttachedChildren();
                if (isVisible(expandableNotificationRow) && attachedChildren != null && expandableNotificationRow.mChildrenExpanded) {
                    for (ExpandableNotificationRow expandableNotificationRow2 : expandableNotificationRow.getAttachedChildren()) {
                        if (isVisible(expandableNotificationRow2) && includeChildInClearAll(expandableNotificationRow2, i)) {
                            arrayList.add(expandableNotificationRow2);
                        }
                    }
                }
            }
        }
        int childCount2 = getChildCount();
        ArrayList arrayList2 = new ArrayList(childCount2);
        for (int i3 = 0; i3 < childCount2; i3++) {
            View childAt2 = getChildAt(i3);
            if (childAt2 instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) childAt2;
                if (includeChildInClearAll(expandableNotificationRow3, i)) {
                    arrayList2.add(expandableNotificationRow3);
                }
                List<ExpandableNotificationRow> attachedChildren2 = expandableNotificationRow3.getAttachedChildren();
                if (isVisible(expandableNotificationRow3) && attachedChildren2 != null) {
                    for (ExpandableNotificationRow expandableNotificationRow4 : attachedChildren2) {
                        if (includeChildInClearAll(expandableNotificationRow3, i)) {
                            arrayList2.add(expandableNotificationRow4);
                        }
                    }
                }
            }
        }
        NotificationStackScrollLayoutController$$ExternalSyntheticLambda5 notificationStackScrollLayoutController$$ExternalSyntheticLambda5 = this.mClearAllListener;
        if (notificationStackScrollLayoutController$$ExternalSyntheticLambda5 != null) {
            ((NotificationStackScrollLayoutController) notificationStackScrollLayoutController$$ExternalSyntheticLambda5.f$0).mUiEventLogger.log(i == 0 ? NotificationStackScrollLayoutController.NotificationPanelEvent.DISMISS_ALL_NOTIFICATIONS_PANEL : i == 2 ? NotificationStackScrollLayoutController.NotificationPanelEvent.DISMISS_SILENT_NOTIFICATIONS_PANEL : NotificationStackScrollLayoutController.NotificationPanelEvent.INVALID);
        }
        NotificationStackScrollLayout$$ExternalSyntheticLambda4 notificationStackScrollLayout$$ExternalSyntheticLambda4 = new NotificationStackScrollLayout$$ExternalSyntheticLambda4(this, arrayList2, i);
        if (arrayList.isEmpty()) {
            notificationStackScrollLayout$$ExternalSyntheticLambda4.accept(Boolean.TRUE);
            return;
        }
        setClearAllInProgress(true);
        this.mShadeNeedsToClose = z;
        InteractionJankMonitor.getInstance().begin(this, 62);
        int size = arrayList.size() - 1;
        int i4 = 60;
        int i5 = 0;
        while (size >= 0) {
            View view = (View) arrayList.get(size);
            NotificationStackScrollLayout$$ExternalSyntheticLambda4 notificationStackScrollLayout$$ExternalSyntheticLambda42 = size == 0 ? notificationStackScrollLayout$$ExternalSyntheticLambda4 : null;
            if (view instanceof SectionHeaderView) {
                ((StackScrollerDecorView) view).setContentVisible(false, true, notificationStackScrollLayout$$ExternalSyntheticLambda42);
            } else {
                this.mSwipeHelper.dismissChild(view, 0.0f, notificationStackScrollLayout$$ExternalSyntheticLambda42, i5, true, 200L, true);
            }
            i4 = Math.max(30, i4 - 5);
            i5 += i4;
            size--;
        }
    }

    public final void clearTemporaryViewsInGroup(ViewGroup viewGroup, String str) {
        while (viewGroup != null && viewGroup.getTransientViewCount() != 0) {
            View transientView = viewGroup.getTransientView(0);
            viewGroup.removeTransientView(transientView);
            if (transientView instanceof ExpandableView) {
                ((ExpandableView) transientView).mTransientContainer = null;
                if (transientView instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) transientView;
                    NotificationStackScrollLogger notificationStackScrollLogger = this.mLogger;
                    if (notificationStackScrollLogger != null) {
                        NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                        LogLevel logLevel = LogLevel.INFO;
                        NotificationStackScrollLogger$transientNotificationRowTraversalCleaned$2 notificationStackScrollLogger$transientNotificationRowTraversalCleaned$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$transientNotificationRowTraversalCleaned$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("transientNotificationRowTraversalCleaned: key: ", logMessage.getStr1(), " reason: ", logMessage.getStr2());
                            }
                        };
                        LogBuffer logBuffer = notificationStackScrollLogger.notificationRenderBuffer;
                        LogMessage obtain = logBuffer.obtain("NotificationStackScroll", logLevel, notificationStackScrollLogger$transientNotificationRowTraversalCleaned$2, null);
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                        logMessageImpl.str2 = str;
                        logBuffer.commit(obtain);
                    }
                }
            }
        }
    }

    public final void customOverScrollBy(int i, int i2, int i3, int i4) {
        boolean z;
        float f;
        boolean z2;
        int i5 = i2 + i;
        int i6 = -i4;
        int i7 = i4 + i3;
        if (i5 > i7) {
            z = true;
            i5 = i7;
        } else if (i5 < i6) {
            i5 = i6;
            z = true;
        } else {
            z = false;
        }
        if (this.mScroller.isFinished()) {
            setOwnScrollY(i5);
            return;
        }
        setOwnScrollY(i5);
        if (!z) {
            float currentOverScrollAmount = getCurrentOverScrollAmount(true);
            if (this.mOwnScrollY < 0) {
                notifyOverscrollTopListener(-r4, isRubberbanded(true));
                return;
            } else {
                notifyOverscrollTopListener(currentOverScrollAmount, isRubberbanded(true));
                return;
            }
        }
        int scrollRange = getScrollRange();
        int i8 = this.mOwnScrollY;
        boolean z3 = i8 <= 0;
        boolean z4 = i8 >= scrollRange;
        if (z3 || z4) {
            if (z3) {
                f = -i8;
                setOwnScrollY(0);
                this.mDontReportNextOverScroll = true;
                z2 = true;
            } else {
                setOwnScrollY(scrollRange);
                f = i8 - scrollRange;
                z2 = false;
            }
            setOverScrollAmount(f, z2, false, true);
            setOverScrollAmount(0.0f, z2, true, true);
            this.mScroller.forceFinished(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.mShouldUseRoundedRectClipping && !this.mLaunchingNotification) {
            canvas.clipPath(this.mRoundedClipPath);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        TouchLogger.Companion.logDispatchTouch("StackScroller", motionEvent, dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        if (!this.mShouldUseRoundedRectClipping || !this.mLaunchingNotification) {
            return super.drawChild(canvas, view, j);
        }
        canvas.save();
        ExpandableView expandableView = (ExpandableView) view;
        Path path = (expandableView.isExpandAnimationRunning() || expandableView.hasExpandingChild()) ? null : this.mRoundedClipPath;
        if (path != null) {
            canvas.clipPath(path);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, final String[] strArr) {
        final IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        asIndenting.println("Internal state:");
        DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable(asIndenting, elapsedRealtime, strArr) { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda5
            public final /* synthetic */ IndentingPrintWriter f$1;
            public final /* synthetic */ long f$2;

            @Override // java.lang.Runnable
            public final void run() {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                IndentingPrintWriter indentingPrintWriter = this.f$1;
                long j = this.f$2;
                DumpUtilsKt.println(indentingPrintWriter, "pulsing", Boolean.valueOf(notificationStackScrollLayout.mPulsing));
                DumpUtilsKt.println(indentingPrintWriter, "expanded", Boolean.valueOf(notificationStackScrollLayout.mIsExpanded));
                DumpUtilsKt.println(indentingPrintWriter, "headsUpPinned", Boolean.valueOf(notificationStackScrollLayout.mInHeadsUpPinnedMode));
                DumpUtilsKt.println(indentingPrintWriter, "qsClipping", Boolean.valueOf(notificationStackScrollLayout.mShouldUseRoundedRectClipping));
                DumpUtilsKt.println(indentingPrintWriter, "qsClipDismiss", Boolean.valueOf(notificationStackScrollLayout.mDismissUsingRowTranslationX));
                DumpUtilsKt.println(indentingPrintWriter, "visibility", DumpUtilsKt.visibilityString(notificationStackScrollLayout.getVisibility()));
                DumpUtilsKt.println(indentingPrintWriter, "alpha", Float.valueOf(notificationStackScrollLayout.getAlpha()));
                DumpUtilsKt.println(indentingPrintWriter, "suppressChildrenMeasureLayout", Boolean.valueOf(notificationStackScrollLayout.mSuppressChildrenMeasureAndLayout));
                DumpUtilsKt.println(indentingPrintWriter, "scrollY", Integer.valueOf(notificationStackScrollLayout.mAmbientState.mScrollY));
                DumpUtilsKt.println(indentingPrintWriter, "maxTopPadding", Integer.valueOf(notificationStackScrollLayout.mMaxTopPadding));
                DumpUtilsKt.println(indentingPrintWriter, "showShelfOnly", Boolean.valueOf(notificationStackScrollLayout.mShouldShowShelfOnly));
                DumpUtilsKt.println(indentingPrintWriter, "qsExpandFraction", Float.valueOf(notificationStackScrollLayout.mQsExpansionFraction));
                Boolean bool = Boolean.FALSE;
                DumpUtilsKt.println(indentingPrintWriter, "isCurrentUserSetup", bool);
                DumpUtilsKt.println(indentingPrintWriter, "hideAmount", Float.valueOf(notificationStackScrollLayout.mAmbientState.mHideAmount));
                DumpUtilsKt.println(indentingPrintWriter, "ambientStateSwipingUp", Boolean.valueOf(notificationStackScrollLayout.mAmbientState.mIsSwipingUp));
                DumpUtilsKt.println(indentingPrintWriter, "maxDisplayedNotifications", Integer.valueOf(notificationStackScrollLayout.mMaxDisplayedNotifications));
                DumpUtilsKt.println(indentingPrintWriter, "intrinsicContentHeight", Float.valueOf(notificationStackScrollLayout.mIntrinsicContentHeight));
                DumpUtilsKt.println(indentingPrintWriter, "contentHeight", Integer.valueOf(notificationStackScrollLayout.mContentHeight));
                DumpUtilsKt.println(indentingPrintWriter, "intrinsicPadding", Integer.valueOf(notificationStackScrollLayout.mIntrinsicPadding));
                DumpUtilsKt.println(indentingPrintWriter, "topPadding", Integer.valueOf(notificationStackScrollLayout.mAmbientState.mTopPadding));
                DumpUtilsKt.println(indentingPrintWriter, "bottomPadding", Integer.valueOf(notificationStackScrollLayout.mBottomPadding));
                indentingPrintWriter.append("roundedRectClipping{l=").print(notificationStackScrollLayout.mRoundedRectClippingLeft);
                indentingPrintWriter.append(" t=").print(notificationStackScrollLayout.mRoundedRectClippingTop);
                indentingPrintWriter.append(" r=").print(notificationStackScrollLayout.mRoundedRectClippingRight);
                indentingPrintWriter.append(" b=").print(notificationStackScrollLayout.mRoundedRectClippingBottom);
                indentingPrintWriter.append(" +y=").print(notificationStackScrollLayout.mRoundedRectClippingYTranslation);
                indentingPrintWriter.append("} topRadius=").print(notificationStackScrollLayout.mBgCornerRadii[0]);
                indentingPrintWriter.append(" bottomRadius=").println(notificationStackScrollLayout.mBgCornerRadii[4]);
                DumpUtilsKt.println(indentingPrintWriter, "requestedClipBounds", notificationStackScrollLayout.mRequestedClipBounds);
                DumpUtilsKt.println(indentingPrintWriter, "isClipped", Boolean.valueOf(notificationStackScrollLayout.mIsClipped));
                DumpUtilsKt.println(indentingPrintWriter, "translationX", Float.valueOf(notificationStackScrollLayout.getTranslationX()));
                DumpUtilsKt.println(indentingPrintWriter, "translationY", Float.valueOf(notificationStackScrollLayout.getTranslationY()));
                DumpUtilsKt.println(indentingPrintWriter, "translationZ", Float.valueOf(notificationStackScrollLayout.getTranslationZ()));
                DumpUtilsKt.println(indentingPrintWriter, "skinnyNotifsInLandscape", Boolean.valueOf(notificationStackScrollLayout.mSkinnyNotifsInLandscape));
                DumpUtilsKt.println(indentingPrintWriter, "minimumPaddings", Integer.valueOf(notificationStackScrollLayout.mMinimumPaddings));
                DumpUtilsKt.println(indentingPrintWriter, "qsTilePadding", Integer.valueOf(notificationStackScrollLayout.mQsTilePadding));
                DumpUtilsKt.println(indentingPrintWriter, "sidePaddings", Integer.valueOf(notificationStackScrollLayout.mSidePaddings));
                DumpUtilsKt.println(indentingPrintWriter, "elapsedRealtime", Long.valueOf(j));
                DumpUtilsKt.println(indentingPrintWriter, "lastInitView", notificationStackScrollLayout.mLastInitViewDumpString);
                DumpUtilsKt.println(indentingPrintWriter, "lastInitViewElapsedRealtime", Long.valueOf(notificationStackScrollLayout.mLastInitViewElapsedRealtime));
                DumpUtilsKt.println(indentingPrintWriter, "lastInitViewMillisAgo", Long.valueOf(j - notificationStackScrollLayout.mLastInitViewElapsedRealtime));
                DumpUtilsKt.println(indentingPrintWriter, "shouldUseSplitNotificationShade", Boolean.valueOf(notificationStackScrollLayout.mShouldUseSplitNotificationShade));
                DumpUtilsKt.println(indentingPrintWriter, "lastUpdateSidePadding", notificationStackScrollLayout.mLastUpdateSidePaddingDumpString);
                DumpUtilsKt.println(indentingPrintWriter, "lastUpdateSidePaddingElapsedRealtime", Long.valueOf(notificationStackScrollLayout.mLastUpdateSidePaddingElapsedRealtime));
                DumpUtilsKt.println(indentingPrintWriter, "lastUpdateSidePaddingMillisAgo", Long.valueOf(j - notificationStackScrollLayout.mLastUpdateSidePaddingElapsedRealtime));
                DumpUtilsKt.println(indentingPrintWriter, "isSmallLandscapeLockscreenEnabled", bool);
                indentingPrintWriter.println("NotificationStackSizeCalculator saveSpaceOnLockscreen=" + notificationStackScrollLayout.mNotificationStackSizeCalculator.saveSpaceOnLockscreen);
                ScrollViewFields scrollViewFields = notificationStackScrollLayout.mScrollViewFields;
                scrollViewFields.getClass();
                indentingPrintWriter.append("StackViewStates").println(":");
                indentingPrintWriter.increaseIndent();
                try {
                    DumpUtilsKt.println(indentingPrintWriter, "scrimClippingShape", null);
                    DumpUtilsKt.println(indentingPrintWriter, "isScrolledToTop", Boolean.valueOf(scrollViewFields.isScrolledToTop));
                } finally {
                    indentingPrintWriter.decreaseIndent();
                }
            }
        });
        asIndenting.println();
        asIndenting.println("Contents:");
        DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
                PrintWriter printWriter2 = asIndenting;
                String[] strArr2 = strArr;
                int i = NotificationStackScrollLayout.$r8$clinit;
                int childCount = notificationStackScrollLayout.getChildCount();
                printWriter2.println("Number of children: " + childCount);
                printWriter2.println();
                for (int i2 = 0; i2 < childCount; i2++) {
                    ((ExpandableView) notificationStackScrollLayout.getChildAt(i2)).dump(printWriter2, strArr2);
                    printWriter2.println();
                }
                int transientViewCount = notificationStackScrollLayout.getTransientViewCount();
                printWriter2.println("Transient Views: " + transientViewCount);
                for (int i3 = 0; i3 < transientViewCount; i3++) {
                    ((ExpandableView) notificationStackScrollLayout.getTransientView(i3)).dump(printWriter2, strArr2);
                }
                NotificationSwipeHelper notificationSwipeHelper = notificationStackScrollLayout.mSwipeHelper;
                ExpandableView expandableView = notificationSwipeHelper.mIsSwiping ? notificationSwipeHelper.mTouchedView : null;
                printWriter2.println("Swiped view: " + expandableView);
                if (expandableView != null) {
                    expandableView.dump(printWriter2, strArr2);
                }
            }
        });
    }

    public final void endDrag() {
        setIsBeingDragged(false);
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (getCurrentOverScrollAmount(true) > 0.0f) {
            setOverScrollAmount(0.0f, true, true, true);
        }
        if (getCurrentOverScrollAmount(false) > 0.0f) {
            setOverScrollAmount(0.0f, false, true, true);
        }
    }

    public final void finalizeClearAllAnimation() {
        if (this.mAmbientState.mClearAllInProgress) {
            setClearAllInProgress(false);
            if (this.mShadeNeedsToClose) {
                this.mShadeNeedsToClose = false;
                if (this.mIsExpanded) {
                    this.mClearAllFinishedWhilePanelExpandedRunnable.run();
                }
            }
        }
    }

    public final void generateAddAnimation(ExpandableView expandableView) {
        if (this.mIsExpanded && this.mAnimationsEnabled && !this.mChangePositionInProgress && !this.mAmbientState.isFullyHidden()) {
            this.mChildrenToAddAnimated.add(expandableView);
            this.mNeedsAnimation = true;
        }
        if (!(expandableView instanceof ExpandableNotificationRow ? ((ExpandableNotificationRow) expandableView).mIsHeadsUp : false) || !this.mAnimationsEnabled || this.mChangePositionInProgress || this.mAmbientState.isFullyHidden()) {
            return;
        }
        this.mAddedHeadsUpChildren.add(expandableView);
        this.mChildrenToAddAnimated.remove(expandableView);
    }

    public final void generateHeadsUpAnimation(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (this.mAnimationsEnabled) {
            if (z || this.mHeadsUpGoingAwayAnimationsAllowed) {
                if (!z && this.mHeadsUpChangeAnimations.remove(new Pair(expandableNotificationRow, Boolean.TRUE))) {
                    logHunAnimationSkipped(expandableNotificationRow, "previous hun appear animation cancelled");
                    return;
                }
                this.mHeadsUpChangeAnimations.add(new Pair(expandableNotificationRow, Boolean.valueOf(z)));
                this.mNeedsAnimation = true;
                if (!this.mIsExpanded && !this.mWillExpand && !z) {
                    expandableNotificationRow.setHeadsUpAnimatingAway(true);
                }
                requestChildrenUpdate();
            }
        }
    }

    public final boolean generateRemoveAnimation(ExpandableView expandableView) {
        Boolean bool;
        Iterator it = this.mHeadsUpChangeAnimations.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) pair.first;
            boolean booleanValue = ((Boolean) pair.second).booleanValue();
            if (expandableView == expandableNotificationRow) {
                this.mTmpList.add(pair);
                z |= booleanValue;
            }
        }
        if (z) {
            this.mHeadsUpChangeAnimations.removeAll(this.mTmpList);
            ((ExpandableNotificationRow) expandableView).setHeadsUpAnimatingAway(false);
        }
        this.mTmpList.clear();
        if (z && this.mAddedHeadsUpChildren.contains(expandableView)) {
            this.mAddedHeadsUpChildren.remove(expandableView);
            return false;
        }
        if (!this.mIsExpanded && (bool = (Boolean) expandableView.getTag(R.id.is_clicked_heads_up_tag)) != null && bool.booleanValue()) {
            this.mClearTransientViewsWhenFinished.add(expandableView);
            return expandableView.mInRemovalAnimation;
        }
        if (this.mIsExpanded && this.mAnimationsEnabled) {
            if (!this.mChildrenToAddAnimated.contains(expandableView)) {
                this.mChildrenToRemoveAnimated.add(expandableView);
                this.mNeedsAnimation = true;
                return true;
            }
            this.mChildrenToAddAnimated.remove(expandableView);
            this.mFromMoreCardAdditions.remove(expandableView);
        }
        return false;
    }

    public final float getAppearEndPosition() {
        int i = this.mAmbientState.mStackTopMargin;
        if (this.mEmptyShadeView.getVisibility() != 8) {
            i = this.mEmptyShadeView.getHeight();
        } else if (isHeadsUpTransition() || (this.mInHeadsUpPinnedMode && !this.mAmbientState.mDozing)) {
            if (this.mShelf.getVisibility() != 8) {
                i += this.mShelf.getHeight() + this.mPaddingBetweenElements;
            }
            i += getPositionInLinearLayout$1(this.mAmbientState.getTrackedHeadsUpRow()) + getTopHeadsUpPinnedHeight();
        } else if (this.mShelf.getVisibility() != 8) {
            i += this.mShelf.getHeight();
        }
        return i + (onKeyguard() ? this.mAmbientState.mTopPadding : this.mIntrinsicPadding);
    }

    public final float getAppearStartPosition() {
        NotificationSection notificationSection;
        if (!isHeadsUpTransition()) {
            return getMinExpansionHeight();
        }
        NotificationSection[] notificationSectionArr = this.mSections;
        int length = notificationSectionArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                notificationSection = null;
                break;
            }
            notificationSection = notificationSectionArr[i];
            if (notificationSection.mFirstVisibleChild != null) {
                break;
            }
            i++;
        }
        return (this.mHeadsUpInset - this.mAmbientState.mStackTopMargin) + (notificationSection != null ? notificationSection.mFirstVisibleChild.getPinnedHeadsUpHeight() : 0);
    }

    public final ExpandableView getChildAtPosition(float f, float f2, boolean z, boolean z2) {
        ExpandableNotificationRow expandableNotificationRow;
        float translationY;
        ExpandableNotificationRow expandableNotificationRow2;
        ExpandableNotificationRow expandableNotificationRow3;
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            expandableNotificationRow = null;
            if (i >= childCount) {
                return null;
            }
            ExpandableView expandableView = (ExpandableView) getChildAt(i);
            if (expandableView.getVisibility() == 0 && (!z2 || !(expandableView instanceof StackScrollerDecorView))) {
                translationY = expandableView.getTranslationY();
                float max = Math.max(0, expandableView.mClipTopAmount) + translationY;
                float f3 = (expandableView.mActualHeight + translationY) - expandableView.mClipBottomAmount;
                int width = getWidth();
                if ((f3 - max >= this.mMinInteractionHeight || !z) && f2 >= max && f2 <= f3 && f >= 0 && f <= width) {
                    if (!(expandableView instanceof ExpandableNotificationRow)) {
                        return expandableView;
                    }
                    expandableNotificationRow2 = (ExpandableNotificationRow) expandableView;
                    NotificationEntry notificationEntry = expandableNotificationRow2.mEntry;
                    if (this.mIsExpanded || !expandableNotificationRow2.mIsHeadsUp || !expandableNotificationRow2.mIsPinned || (expandableNotificationRow3 = this.mTopHeadsUpRow) == expandableNotificationRow2) {
                        break;
                    }
                    GroupMembershipManagerImpl groupMembershipManagerImpl = this.mGroupMembershipManager;
                    NotificationEntry notificationEntry2 = expandableNotificationRow3.mEntry;
                    groupMembershipManagerImpl.getClass();
                    if (GroupMembershipManagerImpl.getGroupSummary(notificationEntry2) == notificationEntry) {
                        break;
                    }
                }
            }
            i++;
        }
        float f4 = f2 - translationY;
        if (!expandableNotificationRow2.mIsSummaryWithChildren || !expandableNotificationRow2.mChildrenExpanded) {
            return expandableNotificationRow2;
        }
        NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow2.mChildrenContainer;
        int size = ((ArrayList) notificationChildrenContainer.mAttachedChildren).size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            ExpandableNotificationRow expandableNotificationRow4 = (ExpandableNotificationRow) ((ArrayList) notificationChildrenContainer.mAttachedChildren).get(i2);
            float translationY2 = expandableNotificationRow4.getTranslationY();
            float max2 = Math.max(0, expandableNotificationRow4.mClipTopAmount) + translationY2;
            float f5 = translationY2 + expandableNotificationRow4.mActualHeight;
            if (f4 >= max2 && f4 <= f5) {
                expandableNotificationRow = expandableNotificationRow4;
                break;
            }
            i2++;
        }
        return expandableNotificationRow == null ? expandableNotificationRow2 : expandableNotificationRow;
    }

    public final ExpandableView getChildAtRawPosition(float f, float f2) {
        getLocationOnScreen(this.mTempInt2);
        int[] iArr = this.mTempInt2;
        return getChildAtPosition(f - iArr[0], f2 - iArr[1], true, true);
    }

    public final List getChildrenWithBackground() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ExpandableView expandableView = (ExpandableView) getChildAt(i);
            if (expandableView.getVisibility() != 8 && !(expandableView instanceof StackScrollerDecorView) && expandableView != this.mShelf) {
                arrayList.add(expandableView);
            }
        }
        return arrayList;
    }

    public final float getCurrentOverScrollAmount(boolean z) {
        AmbientState ambientState = this.mAmbientState;
        return z ? ambientState.mOverScrollTopAmount : ambientState.mOverScrollBottomAmount;
    }

    public final int getEmptyBottomMarginInternal() {
        return Math.max(this.mMaxLayoutHeight - (this.mShouldUseSplitNotificationShade ? Math.max(this.mSplitShadeMinContentHeight, this.mContentHeight) : this.mContentHeight), 0);
    }

    public final View getFirstChildBelowTranlsationY(float f) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && childAt.getTranslationY() >= f) {
                return childAt;
            }
        }
        return null;
    }

    public final ExpandableView getFirstChildNotGoneInternal() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && childAt != this.mShelf) {
                return (ExpandableView) childAt;
            }
        }
        return null;
    }

    public boolean getIsBeingDragged() {
        return this.mIsBeingDragged;
    }

    public final int getLayoutMinHeightInternal() {
        if (!isHeadsUpTransition()) {
            if (this.mShelf.getVisibility() == 8) {
                return 0;
            }
            return this.mShelf.getHeight();
        }
        ExpandableNotificationRow trackedHeadsUpRow = this.mAmbientState.getTrackedHeadsUpRow();
        if (!trackedHeadsUpRow.isAboveShelf()) {
            return getTopHeadsUpPinnedHeight();
        }
        return getTopHeadsUpPinnedHeight() + ((int) MathUtils.lerp(0, getPositionInLinearLayout$1(trackedHeadsUpRow), this.mAmbientState.mAppearFraction));
    }

    public final int getMinExpansionHeight() {
        return (this.mShelf.getHeight() - Math.max(0, ((this.mShelf.getHeight() - this.mStatusBarHeight) + this.mWaterfallTopInset) / 2)) + this.mWaterfallTopInset;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int getPositionInLinearLayout$1(android.view.View r17) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.getPositionInLinearLayout$1(android.view.View):int");
    }

    public final float getRubberBandFactor(boolean z) {
        if (!z) {
            return RUBBER_BAND_FACTOR_NORMAL;
        }
        if (this.mExpandedInThisMotion) {
            return 0.15f;
        }
        if (this.mIsExpansionChanging || this.mPanelTracking) {
            return 0.21f;
        }
        if (!this.mScrolledToTopOnFirstDown || this.mShouldUseSplitNotificationShade) {
            return RUBBER_BAND_FACTOR_NORMAL;
        }
        return 1.0f;
    }

    public final int getScrollRange() {
        int i = this.mContentHeight;
        if (!this.mIsExpanded && this.mInHeadsUpPinnedMode) {
            i = this.mHeadsUpInset + getTopHeadsUpPinnedHeight();
        }
        int max = Math.max(0, i - this.mMaxLayoutHeight);
        int max2 = Math.max(0, this.mImeInset - ((getRootView().getHeight() - getHeight()) - getLocationOnScreen()[1]));
        int min = Math.min(max2, Math.max(0, i - (getHeight() - max2))) + max;
        if (min > 0) {
            return Math.max(this.mShouldUseSplitNotificationShade ? this.mSidePaddings : this.mAmbientState.mTopPadding - this.mQsScrollBoundaryPosition, min);
        }
        return min;
    }

    public final int getTopHeadsUpPinnedHeight() {
        ExpandableNotificationRow expandableNotificationRow = this.mTopHeadsUpRow;
        if (expandableNotificationRow == null) {
            return 0;
        }
        if (expandableNotificationRow.isChildInGroup()) {
            GroupMembershipManagerImpl groupMembershipManagerImpl = this.mGroupMembershipManager;
            NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
            groupMembershipManagerImpl.getClass();
            NotificationEntry groupSummary = GroupMembershipManagerImpl.getGroupSummary(notificationEntry);
            if (groupSummary != null) {
                expandableNotificationRow = groupSummary.row;
            }
        }
        return expandableNotificationRow.getPinnedHeadsUpHeight(true);
    }

    public final float getTouchSlop$2(MotionEvent motionEvent) {
        return motionEvent.getClassification() == 1 ? this.mTouchSlop * this.mSlopMultiplier : this.mTouchSlop;
    }

    public final void handleEmptySpaceClick(MotionEvent motionEvent) {
        boolean isBelowLastNotification = isBelowLastNotification(this.mInitialTouchX, this.mInitialTouchY);
        int i = this.mStatusBarState;
        boolean z = this.mTouchIsClick;
        NotificationStackScrollLogger notificationStackScrollLogger = this.mLogger;
        if (notificationStackScrollLogger != null) {
            String actionToString = MotionEvent.actionToString(motionEvent.getActionMasked());
            LogLevel logLevel = LogLevel.DEBUG;
            NotificationStackScrollLogger$logEmptySpaceClick$2 notificationStackScrollLogger$logEmptySpaceClick$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$logEmptySpaceClick$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "handleEmptySpaceClick: statusBarState: " + logMessage.getInt1() + " isTouchAClick: " + logMessage.getBool1() + " isTouchBelowNotification: " + logMessage.getBool2() + " motionEvent: " + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer = notificationStackScrollLogger.shadeLogBuffer;
            LogMessage obtain = logBuffer.obtain("NotificationStackScroll", logLevel, notificationStackScrollLogger$logEmptySpaceClick$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.int1 = i;
            logMessageImpl.bool1 = z;
            logMessageImpl.bool2 = isBelowLastNotification;
            logMessageImpl.str1 = actionToString;
            logBuffer.commit(obtain);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            if (this.mStatusBarState != 1 && this.mTouchIsClick && isBelowLastNotification(this.mInitialTouchX, this.mInitialTouchY)) {
                NotificationStackScrollLogger notificationStackScrollLogger2 = this.mLogger;
                if (notificationStackScrollLogger2 != null) {
                    notificationStackScrollLogger2.shadeLogBuffer.log("NotificationStackScroll", LogLevel.DEBUG, "handleEmptySpaceClick: touch event propagated further", null);
                }
                this.mOnEmptySpaceClickListener.f$0.onEmptySpaceClick();
                return;
            }
            return;
        }
        if (actionMasked != 2) {
            NotificationStackScrollLogger notificationStackScrollLogger3 = this.mLogger;
            if (notificationStackScrollLogger3 == null) {
                return;
            }
            notificationStackScrollLogger3.shadeLogBuffer.log("NotificationStackScroll", LogLevel.DEBUG, "handleEmptySpaceClick: MotionEvent ignored", null);
            return;
        }
        float touchSlop$2 = getTouchSlop$2(motionEvent);
        if (this.mTouchIsClick) {
            if (Math.abs(motionEvent.getY() - this.mInitialTouchY) > touchSlop$2 || Math.abs(motionEvent.getX() - this.mInitialTouchX) > touchSlop$2) {
                this.mTouchIsClick = false;
            }
        }
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return !this.mForceNoOverlappingRendering && super.hasOverlappingRendering();
    }

    public final void inflateEmptyShadeView() {
        int i;
        EmptyShadeView emptyShadeView = this.mEmptyShadeView;
        EmptyShadeView emptyShadeView2 = (EmptyShadeView) LayoutInflater.from(((ViewGroup) this).mContext).inflate(R.layout.status_bar_no_notifications, (ViewGroup) this, false);
        emptyShadeView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda3
            /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void onClick(android.view.View r6) {
                /*
                    r5 = this;
                    com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r5 = com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.this
                    com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController r6 = r5.mController
                    java.lang.Boolean r0 = r6.mHistoryEnabled
                    r1 = 1
                    if (r0 != 0) goto L30
                    com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r6.mView
                    r2 = 0
                    if (r0 == 0) goto L28
                    android.content.Context r0 = r0.getContext()
                    if (r0 != 0) goto L15
                    goto L28
                L15:
                    com.android.systemui.util.settings.SecureSettings r0 = r6.mSecureSettings
                    java.lang.String r3 = "notification_history_enabled"
                    r4 = -2
                    int r0 = r0.getIntForUser(r3, r2, r4)
                    if (r0 != r1) goto L21
                    r2 = r1
                L21:
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                    r6.mHistoryEnabled = r0
                    goto L30
                L28:
                    java.lang.String r6 = "StackScrollerController"
                    java.lang.String r0 = "isHistoryEnabled failed to initialize its value"
                    android.util.Log.wtf(r6, r0)
                    goto L34
                L30:
                    boolean r2 = r0.booleanValue()
                L34:
                    if (r2 == 0) goto L3e
                    android.content.Intent r6 = new android.content.Intent
                    java.lang.String r0 = "android.settings.NOTIFICATION_HISTORY"
                    r6.<init>(r0)
                    goto L45
                L3e:
                    android.content.Intent r6 = new android.content.Intent
                    java.lang.String r0 = "android.settings.NOTIFICATION_SETTINGS"
                    r6.<init>(r0)
                L45:
                    com.android.systemui.plugins.ActivityStarter r5 = r5.mActivityStarter
                    r0 = 536870912(0x20000000, float:1.0842022E-19)
                    r5.startActivity(r6, r1, r1, r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda3.onClick(android.view.View):void");
            }
        });
        View view = this.mEmptyShadeView;
        if (view != null) {
            i = indexOfChild(view);
            removeView(this.mEmptyShadeView);
        } else {
            i = -1;
        }
        this.mEmptyShadeView = emptyShadeView2;
        addView(emptyShadeView2, i);
        emptyShadeView2.setVisible(emptyShadeView != null && emptyShadeView.mIsVisible, false);
        updateEmptyShadeViewResources(emptyShadeView == null ? R.string.empty_shade_text : emptyShadeView.mText, emptyShadeView == null ? 0 : emptyShadeView.mFooterText, emptyShadeView != null ? emptyShadeView.mFooterIcon : 0);
    }

    public void inflateFooterView() {
        FooterViewRefactor.assertInLegacyMode();
        throw null;
    }

    public final void initView(Context context, NotificationSwipeHelper notificationSwipeHelper, NotificationStackSizeCalculator notificationStackSizeCalculator) {
        this.mScroller = new OverScroller(getContext());
        this.mSwipeHelper = notificationSwipeHelper;
        this.mNotificationStackSizeCalculator = notificationStackSizeCalculator;
        setDescendantFocusability(262144);
        setClipChildren(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mSlopMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mOverflingDistance = viewConfiguration.getScaledOverflingDistance();
        Resources resources = context.getResources();
        boolean z = resources.getBoolean(R.bool.is_small_screen_landscape);
        boolean z2 = resources.getBoolean(R.bool.config_skinnyNotifsInLandscape);
        this.mSkinnyNotifsInLandscape = z2;
        StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("mIsSmallLandscapeLockscreenEnabled=false isSmallScreenLandscape=", " useSmallLandscapeLockscreenResources=", " skinnyNotifsInLandscape=", z, false);
        m.append(z2);
        m.append(" mSkinnyNotifsInLandscape=");
        m.append(this.mSkinnyNotifsInLandscape);
        this.mLastInitViewDumpString = m.toString();
        this.mLastInitViewElapsedRealtime = SystemClock.elapsedRealtime();
        this.mGapHeight = resources.getDimensionPixelSize(R.dimen.notification_section_divider_height);
        this.mStackScrollAlgorithm.initView(context);
        this.mStateAnimator.initView(context);
        AmbientState ambientState = this.mAmbientState;
        ambientState.getClass();
        ambientState.mZDistanceBetweenElements = Math.max(1, context.getResources().getDimensionPixelSize(R.dimen.z_distance_between_notifications));
        this.mPaddingBetweenElements = Math.max(1, resources.getDimensionPixelSize(R.dimen.notification_divider_height));
        this.mMinTopOverScrollToEscape = resources.getDimensionPixelSize(R.dimen.min_top_overscroll_to_qs);
        this.mStatusBarHeight = SystemBarUtils.getStatusBarHeight(((ViewGroup) this).mContext);
        this.mBottomPadding = resources.getDimensionPixelSize(R.dimen.notification_panel_padding_bottom);
        this.mMinimumPaddings = resources.getDimensionPixelSize(R.dimen.notification_side_paddings);
        this.mQsTilePadding = resources.getDimensionPixelOffset(R.dimen.qs_tile_margin_horizontal);
        this.mSidePaddings = this.mMinimumPaddings;
        this.mMinInteractionHeight = resources.getDimensionPixelSize(R.dimen.notification_min_interaction_height);
        this.mCornerRadius = resources.getDimensionPixelSize(R.dimen.notification_corner_radius);
        this.mHeadsUpInset = resources.getDimensionPixelSize(R.dimen.heads_up_status_bar_padding) + this.mStatusBarHeight;
        this.mQsScrollBoundaryPosition = SystemBarUtils.getQuickQsOffsetHeight(((ViewGroup) this).mContext);
    }

    public final boolean isBelowLastNotification(float f, float f2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            ExpandableView expandableView = (ExpandableView) getChildAt(childCount);
            if (expandableView.getVisibility() != 8) {
                float y = expandableView.getY();
                if (y > f2) {
                    return false;
                }
                boolean z = f2 > (((float) expandableView.mActualHeight) + y) - ((float) expandableView.mClipBottomAmount);
                FooterView footerView = this.mFooterView;
                if (expandableView != footerView) {
                    if (expandableView == this.mEmptyShadeView) {
                        return true;
                    }
                    if (!z) {
                        return false;
                    }
                } else if (z) {
                    continue;
                } else {
                    float x = f - footerView.getX();
                    float f3 = f2 - y;
                    if (x >= footerView.mContent.getX() && x <= footerView.mContent.getX() + footerView.mContent.getWidth() && f3 >= footerView.mContent.getY() && f3 <= footerView.mContent.getY() + footerView.mContent.getHeight()) {
                        return false;
                    }
                }
            }
        }
        AmbientState ambientState = this.mAmbientState;
        return f2 > ((float) ambientState.mTopPadding) + ambientState.mStackTranslation;
    }

    public final boolean isFullySwipedOut(ExpandableView expandableView) {
        float f;
        float abs = Math.abs(expandableView.getTranslation());
        if (this.mDismissUsingRowTranslationX) {
            float measuredWidth = expandableView.getMeasuredWidth();
            float measuredWidth2 = getMeasuredWidth();
            f = measuredWidth2 - ((measuredWidth2 - measuredWidth) / 2.0f);
        } else {
            f = expandableView.getMeasuredWidth();
        }
        return abs >= Math.abs(f);
    }

    public final boolean isHeadsUpTransition() {
        return this.mAmbientState.getTrackedHeadsUpRow() != null;
    }

    public boolean isInScrollableRegion(MotionEvent motionEvent) {
        boolean contains;
        ViewGroup viewGroup = this.mQsHeader;
        if (viewGroup == null) {
            contains = false;
        } else {
            viewGroup.getBoundsOnScreen(this.mQsHeaderBound);
            this.mQsHeaderBound.offsetTo(Math.round((motionEvent.getRawX() - motionEvent.getX()) + this.mQsHeader.getLeft()), Math.round(motionEvent.getRawY() - motionEvent.getY()));
            contains = this.mQsHeaderBound.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
        return !contains;
    }

    public final boolean isRubberbanded(boolean z) {
        return !z || this.mExpandedInThisMotion || this.mIsExpansionChanging || this.mPanelTracking || !this.mScrolledToTopOnFirstDown;
    }

    public boolean isVisible(View view) {
        return view.getVisibility() == 0 && (!view.getClipBounds(this.mTmpRect) || this.mTmpRect.height() > 0);
    }

    public final void logHunAnimationSkipped(ExpandableNotificationRow expandableNotificationRow, String str) {
        NotificationStackScrollLogger notificationStackScrollLogger = this.mLogger;
        if (notificationStackScrollLogger == null) {
            return;
        }
        NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
        LogLevel logLevel = LogLevel.INFO;
        NotificationStackScrollLogger$hunAnimationSkipped$2 notificationStackScrollLogger$hunAnimationSkipped$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$hunAnimationSkipped$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("heads up animation skipped: key: ", logMessage.getStr1(), " reason: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = notificationStackScrollLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationStackScroll", logLevel, notificationStackScrollLogger$hunAnimationSkipped$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
    }

    public final void notifyAppearChangedListeners() {
        float saturate;
        float f;
        if (this.mKeyguardBypassEnabled && onKeyguard()) {
            float f2 = this.mAmbientState.mPulseHeight;
            f = 0.0f;
            if (f2 == 100000.0f) {
                f2 = 0.0f;
            }
            saturate = MathUtils.smoothStep(0.0f, this.mIntrinsicPadding, f2);
            float f3 = this.mAmbientState.mPulseHeight;
            if (f3 != 100000.0f) {
                f = f3;
            }
        } else {
            saturate = MathUtils.saturate(calculateAppearFraction(this.mExpandedHeight));
            f = this.mExpandedHeight;
        }
        if (saturate == this.mLastSentAppear && f == this.mLastSentExpandedHeight) {
            return;
        }
        this.mLastSentAppear = saturate;
        this.mLastSentExpandedHeight = f;
        for (int i = 0; i < this.mExpandedHeightListeners.size(); i++) {
            ((BiConsumer) this.mExpandedHeightListeners.get(i)).accept(Float.valueOf(f), Float.valueOf(saturate));
        }
    }

    public final void notifyHeightChangeListener(ExpandableView expandableView, boolean z) {
        NotificationPanelViewController.AnonymousClass10 anonymousClass10 = this.mOnHeightChangedListener;
        if (anonymousClass10 != null) {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            if (expandableView != null || !notificationPanelViewController.mQsController.getExpanded()) {
                if (z && notificationPanelViewController.mInterpolatedDarkAmount == 0.0f) {
                    notificationPanelViewController.mAnimateNextPositionUpdate = true;
                }
                ExpandableView firstChildNotGoneInternal = notificationPanelViewController.mNotificationStackScrollLayoutController.mView.getFirstChildNotGoneInternal();
                ExpandableNotificationRow expandableNotificationRow = firstChildNotGoneInternal instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) firstChildNotGoneInternal : null;
                if (expandableNotificationRow != null && (expandableView == expandableNotificationRow || expandableNotificationRow.mNotificationParent == expandableNotificationRow)) {
                    notificationPanelViewController.requestScrollerTopPaddingUpdate();
                }
                notificationPanelViewController.updateExpandedHeightToMaxHeight();
            }
        }
        SharedNotificationContainerBinder$bind$3 sharedNotificationContainerBinder$bind$3 = this.mOnHeightChangedRunnable;
        if (sharedNotificationContainerBinder$bind$3 != null) {
            sharedNotificationContainerBinder$bind$3.run();
        }
    }

    public final void notifyOverscrollTopListener(float f, boolean z) {
        this.mExpandHelper.mOnlyMovements = f > 1.0f;
        if (this.mDontReportNextOverScroll) {
            this.mDontReportNextOverScroll = false;
            return;
        }
        QuickSettingsControllerImpl.NsslOverscrollTopChangedListener nsslOverscrollTopChangedListener = this.mOverscrollTopChangedListener;
        if (nsslOverscrollTopChangedListener != null) {
            QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
            if (quickSettingsControllerImpl.mSplitShadeEnabled) {
                return;
            }
            ValueAnimator valueAnimator = quickSettingsControllerImpl.mExpansionAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            if (!quickSettingsControllerImpl.isExpansionEnabled()) {
                f = 0.0f;
            }
            if (f < 1.0f) {
                f = 0.0f;
            }
            boolean z2 = f != 0.0f && z;
            quickSettingsControllerImpl.mStackScrollerOverscrolling = z2;
            QS qs = quickSettingsControllerImpl.mQs;
            if (qs != null) {
                qs.setOverscrolling(z2);
            }
            quickSettingsControllerImpl.mExpansionFromOverscroll = f != 0.0f;
            quickSettingsControllerImpl.mLastOverscroll = f;
            quickSettingsControllerImpl.updateQsState$1();
            quickSettingsControllerImpl.setExpansionHeight(quickSettingsControllerImpl.mMinExpansionHeight + f);
        }
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.mWaterfallTopInset = 0;
        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        if (displayCutout != null) {
            this.mWaterfallTopInset = displayCutout.getWaterfallInsets().top;
        }
        if (!this.mIsInsetAnimationRunning) {
            updateImeInset(windowInsets);
        }
        return windowInsets;
    }

    public final void onChildHeightChanged(ExpandableView expandableView, boolean z) {
        NotificationSection notificationSection;
        NotificationSection notificationSection2;
        boolean z2 = this.mAnimateStackYForContentHeightChange;
        if (z) {
            this.mAnimateStackYForContentHeightChange = true;
        }
        updateContentHeight();
        boolean z3 = expandableView instanceof ExpandableNotificationRow;
        if (z3) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            if (!onKeyguard() && expandableNotificationRow.mUserLocked && expandableNotificationRow != getFirstChildNotGoneInternal() && !expandableNotificationRow.mIsSummaryWithChildren) {
                float translationY = expandableNotificationRow.getTranslationY() + expandableNotificationRow.mActualHeight;
                if (expandableNotificationRow.isChildInGroup()) {
                    translationY += expandableNotificationRow.mNotificationParent.getTranslationY();
                }
                int i = this.mMaxLayoutHeight + ((int) this.mAmbientState.mStackTranslation);
                int length = this.mSections.length - 1;
                while (true) {
                    if (length < 0) {
                        notificationSection2 = null;
                        break;
                    }
                    notificationSection2 = this.mSections[length];
                    if (notificationSection2.mLastVisibleChild != null) {
                        break;
                    } else {
                        length--;
                    }
                }
                if (expandableNotificationRow != (notificationSection2 == null ? null : notificationSection2.mLastVisibleChild) && this.mShelf.getVisibility() != 8) {
                    i -= this.mShelf.getHeight() + this.mPaddingBetweenElements;
                }
                float f = i;
                if (translationY > f) {
                    setOwnScrollY((int) ((this.mOwnScrollY + translationY) - f));
                    this.mDisallowScrollingInThisMotion = true;
                }
            }
        }
        clampScrollPosition();
        notifyHeightChangeListener(expandableView, z);
        ExpandableNotificationRow expandableNotificationRow2 = z3 ? (ExpandableNotificationRow) expandableView : null;
        NotificationSection[] notificationSectionArr = this.mSections;
        int length2 = notificationSectionArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length2) {
                notificationSection = null;
                break;
            }
            notificationSection = notificationSectionArr[i2];
            if (notificationSection.mFirstVisibleChild != null) {
                break;
            } else {
                i2++;
            }
        }
        ExpandableView expandableView2 = notificationSection != null ? notificationSection.mFirstVisibleChild : null;
        if (expandableNotificationRow2 != null && (expandableNotificationRow2 == expandableView2 || expandableNotificationRow2.mNotificationParent == expandableView2)) {
            updateAlgorithmLayoutMinHeight();
        }
        if (z && this.mAnimationsEnabled && (this.mIsExpanded || (expandableNotificationRow2 != null && expandableNotificationRow2.mIsPinned))) {
            this.mNeedViewResizeAnimation = true;
            this.mNeedsAnimation = true;
        }
        requestChildrenUpdate();
        if (this.mTopHeadsUpRow == expandableView) {
            Iterator it = this.mHeadsUpHeightChangedListeners.listeners.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
        this.mAnimateStackYForContentHeightChange = z2;
    }

    public final void onClearAllAnimationsEnd(int i, List list) {
        InteractionJankMonitor.getInstance().end(62);
        NotificationStackScrollLayoutController$$ExternalSyntheticLambda5 notificationStackScrollLayoutController$$ExternalSyntheticLambda5 = this.mClearAllAnimationListener;
        if (notificationStackScrollLayoutController$$ExternalSyntheticLambda5 != null) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) notificationStackScrollLayoutController$$ExternalSyntheticLambda5.f$0;
            NotifCollection notifCollection = notificationStackScrollLayoutController.mNotifCollection;
            if (i != 0) {
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    NotificationEntry notificationEntry = ((ExpandableNotificationRow) it.next()).mEntry;
                    arrayList.add(new Pair(notificationEntry, new DismissedByUserStats(3, ((NotificationVisibilityProviderImpl) notificationStackScrollLayoutController.mVisibilityProvider).obtain(notificationEntry))));
                }
                notifCollection.dismissNotifications(arrayList);
                return;
            }
            int i2 = ((NotificationLockscreenUserManagerImpl) notificationStackScrollLayoutController.mLockscreenUserManager).mCurrentUserId;
            notifCollection.getClass();
            Assert.isMainThread();
            notifCollection.checkForReentrantCall();
            NotifCollectionLogger notifCollectionLogger = notifCollection.mLogger;
            notifCollectionLogger.logDismissAll(i2);
            try {
                notifCollection.mStatusBarService.onClearAllNotifications(i2);
            } catch (RemoteException e) {
                notifCollectionLogger.logRemoteExceptionOnClearAllNotifications(e);
            }
            Assert.isMainThread();
            ArrayList arrayList2 = new ArrayList(notifCollection.mReadOnlyNotificationSet);
            int size = arrayList2.size();
            for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                NotificationEntry notificationEntry2 = (NotificationEntry) arrayList2.get(size2);
                if ((i2 != -1 && notificationEntry2.mSbn.getUser().getIdentifier() != -1 && notificationEntry2.mSbn.getUser().getIdentifier() != i2) || !notificationEntry2.isClearable() || NotifCollection.hasFlag(notificationEntry2, 4096) || notificationEntry2.mDismissState == NotificationEntry.DismissState.DISMISSED) {
                    notifCollection.updateDismissInterceptors(notificationEntry2);
                    if (((ArrayList) notificationEntry2.mDismissInterceptors).size() > 0) {
                        notifCollectionLogger.logNotifClearAllDismissalIntercepted(notificationEntry2, size2, size);
                    }
                    arrayList2.remove(size2);
                }
            }
            notifCollection.locallyDismissNotifications(arrayList2);
            notifCollection.dispatchEventsAndRebuildList("dismissAllNotifications");
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Resources resources = getResources();
        updateSplitNotificationShade();
        this.mStatusBarHeight = SystemBarUtils.getStatusBarHeight(((ViewGroup) this).mContext);
        this.mSwipeHelper.mDensityScale = resources.getDisplayMetrics().density;
        this.mSwipeHelper.mPagingTouchSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
        initView(getContext(), this.mSwipeHelper, this.mNotificationStackSizeCalculator);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        inflateEmptyShadeView();
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.mScrollingEnabled || !this.mIsExpanded || this.mSwipeHelper.mIsSwiping || this.mExpandingNotification || this.mDisallowScrollingInThisMotion) {
            return false;
        }
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.mIsBeingDragged) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactor = (int) (axisValue * getVerticalScrollFactor());
                int scrollRange = getScrollRange();
                int i = this.mOwnScrollY;
                int i2 = i - verticalScrollFactor;
                int i3 = i2 >= 0 ? i2 > scrollRange ? scrollRange : i2 : 0;
                if (i3 != i) {
                    setOwnScrollY(i3);
                    return true;
                }
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEventInternal(accessibilityEvent);
        accessibilityEvent.setScrollable(this.mScrollable);
        accessibilityEvent.setMaxScrollX(((ViewGroup) this).mScrollX);
        accessibilityEvent.setScrollY(this.mOwnScrollY);
        accessibilityEvent.setMaxScrollY(getScrollRange());
    }

    public final void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (this.mScrollable) {
            accessibilityNodeInfo.setScrollable(true);
            if (this.mBackwardScrollable) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP);
            }
            if (this.mForwardScrollable) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN);
            }
        }
        accessibilityNodeInfo.setClassName(ScrollView.class.getName());
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        NotificationStackScrollLayoutController.TouchHandler touchHandler = this.mTouchHandler;
        if (touchHandler == null || !touchHandler.onInterceptTouchEvent(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public final boolean onInterceptTouchEventScroll(MotionEvent motionEvent) {
        if (!this.mScrollingEnabled) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        int i = action & 255;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    int i2 = this.mActivePointerId;
                    if (i2 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i2);
                        if (findPointerIndex == -1) {
                            Log.e("StackScroller", "Invalid pointerId=" + i2 + " in onInterceptTouchEvent");
                        } else {
                            int y = (int) motionEvent.getY(findPointerIndex);
                            int x = (int) motionEvent.getX(findPointerIndex);
                            int abs = Math.abs(y - this.mLastMotionY);
                            int abs2 = Math.abs(x - this.mDownX);
                            if (abs > getTouchSlop$2(motionEvent) && abs > abs2) {
                                setIsBeingDragged(true);
                                this.mLastMotionY = y;
                                this.mDownX = x;
                                if (this.mVelocityTracker == null) {
                                    this.mVelocityTracker = VelocityTracker.obtain();
                                }
                                this.mVelocityTracker.addMovement(motionEvent);
                            }
                        }
                    }
                } else if (i != 3) {
                    if (i == 6) {
                        onSecondaryPointerUp(motionEvent);
                    }
                }
            }
            setIsBeingDragged(false);
            this.mActivePointerId = -1;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            if (this.mScroller.springBack(((ViewGroup) this).mScrollX, this.mOwnScrollY, 0, 0, 0, getScrollRange())) {
                animateScroll();
            }
        } else {
            int y2 = (int) motionEvent.getY();
            this.mScrolledToTopOnFirstDown = NotificationStackScrollLayout.this.mOwnScrollY == 0;
            if (getChildAtPosition(motionEvent.getX(), y2, false, false) == null) {
                setIsBeingDragged(false);
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.mVelocityTracker = null;
                }
            } else {
                this.mLastMotionY = y2;
                this.mDownX = (int) motionEvent.getX();
                this.mActivePointerId = motionEvent.getPointerId(0);
                VelocityTracker velocityTracker3 = this.mVelocityTracker;
                if (velocityTracker3 == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker3.clear();
                }
                this.mVelocityTracker.addMovement(motionEvent);
                setIsBeingDragged(!this.mScroller.isFinished());
            }
        }
        return this.mIsBeingDragged;
    }

    public final boolean onKeyguard() {
        return this.mStatusBarState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.mSuppressChildrenMeasureAndLayout) {
            float width = getWidth() / 2.0f;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                float measuredWidth = r8.getMeasuredWidth() / 2.0f;
                getChildAt(i5).layout((int) (width - measuredWidth), 0, (int) (measuredWidth + width), r8.getMeasuredHeight());
            }
        }
        this.mMaxLayoutHeight = getHeight();
        updateAlgorithmHeightAndPadding();
        updateContentHeight();
        clampScrollPosition();
        requestChildrenUpdate();
        updateFirstAndLastBackgroundViews();
        updateAlgorithmLayoutMinHeight();
        updateOwnTranslationZ();
        ViewGroup viewGroup = this.mQsHeader;
        if (viewGroup != null) {
            viewGroup.getHeight();
        }
        this.mStackScrollAlgorithm.getClass();
        this.mAnimateStackYForContentHeightChange = false;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        Trace.beginSection("NotificationStackScrollLayout#onMeasure");
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int i3 = getResources().getConfiguration().orientation;
        StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("viewWidth=", " skinnyNotifsInLandscape=", size);
        m.append(this.mSkinnyNotifsInLandscape);
        m.append(" orientation=");
        m.append(i3);
        this.mLastUpdateSidePaddingDumpString = m.toString();
        this.mLastUpdateSidePaddingElapsedRealtime = SystemClock.elapsedRealtime();
        if (size == 0) {
            Log.e("StackScroller", "updateSidePadding: viewWidth is zero");
            this.mSidePaddings = this.mMinimumPaddings;
        } else if (i3 == 1) {
            this.mSidePaddings = this.mMinimumPaddings;
        } else if (this.mShouldUseSplitNotificationShade) {
            if (this.mSkinnyNotifsInLandscape) {
                Log.e("StackScroller", "updateSidePadding: mSkinnyNotifsInLandscape has betrayed us!");
            }
            this.mSidePaddings = this.mMinimumPaddings;
        } else {
            int i4 = this.mMinimumPaddings;
            int i5 = this.mQsTilePadding;
            this.mSidePaddings = (((size - (i4 * 2)) - (i5 * 3)) / 4) + i4 + i5;
        }
        if (this.mSuppressChildrenMeasureAndLayout) {
            Trace.endSection();
            return;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size - (this.mSidePaddings * 2), View.MeasureSpec.getMode(i));
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 0);
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            measureChild(getChildAt(i6), makeMeasureSpec, makeMeasureSpec2);
        }
        Trace.endSection();
    }

    public final void onOverScrollFling(int i, boolean z) {
        QuickSettingsControllerImpl.NsslOverscrollTopChangedListener nsslOverscrollTopChangedListener = this.mOverscrollTopChangedListener;
        if (nsslOverscrollTopChangedListener != null) {
            float f = i;
            QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
            if (!quickSettingsControllerImpl.isSplitShadeAndTouchXOutsideQs(quickSettingsControllerImpl.mInitialTouchX)) {
                quickSettingsControllerImpl.mLastOverscroll = 0.0f;
                quickSettingsControllerImpl.mExpansionFromOverscroll = false;
                if (z) {
                    quickSettingsControllerImpl.mStackScrollerOverscrolling = false;
                    QS qs = quickSettingsControllerImpl.mQs;
                    if (qs != null) {
                        qs.setOverscrolling(false);
                    }
                }
                quickSettingsControllerImpl.setExpansionHeight(quickSettingsControllerImpl.mExpansionHeight);
                boolean isExpansionEnabled = quickSettingsControllerImpl.isExpansionEnabled();
                if (!isExpansionEnabled && z) {
                    f = 0.0f;
                }
                quickSettingsControllerImpl.flingQs(f, (z && isExpansionEnabled) ? 0 : 1, new QuickSettingsControllerImpl$$ExternalSyntheticLambda3(2, nsslOverscrollTopChangedListener), false);
            }
        }
        this.mDontReportNextOverScroll = true;
        setOverScrollAmount(0.0f, true, false, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onScrollTouch(android.view.MotionEvent r28) {
        /*
            Method dump skipped, instructions count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.onScrollTouch(android.view.MotionEvent):boolean");
    }

    public final void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (motionEvent.getPointerId(action) == this.mActivePointerId) {
            int i = action == 0 ? 1 : 0;
            this.mLastMotionY = (int) motionEvent.getY(i);
            this.mActivePointerId = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        NotificationStackScrollLayoutController.TouchHandler touchHandler = this.mTouchHandler;
        if (touchHandler == null || !touchHandler.onTouchEvent(motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof ExpandableView) {
            onViewAddedInternal((ExpandableView) view);
        }
    }

    public final void onViewAddedInternal(ExpandableView expandableView) {
        expandableView.setHideSensitiveForIntrinsicHeight(this.mAmbientState.mHideSensitive);
        expandableView.mOnHeightChangedListener = this.mOnChildHeightChangedListener;
        boolean z = expandableView instanceof ExpandableNotificationRow;
        if (z) {
            ((ExpandableNotificationRow) expandableView).mEntry.mOnSensitivityChangedListeners.addIfAbsent(this.mOnChildSensitivityChangedListener);
        }
        generateAddAnimation(expandableView);
        boolean z2 = (this.mAnimationsEnabled || this.mPulsing) && (this.mIsExpanded || isPinnedHeadsUp(expandableView));
        if (z) {
            ((ExpandableNotificationRow) expandableView).setAnimationRunning(z2);
        }
        if (z) {
            ((ExpandableNotificationRow) expandableView).setChronometerRunning(this.mIsExpanded);
        }
        if (z) {
            ((ExpandableNotificationRow) expandableView).setDismissUsingRowTranslationX(this.mDismissUsingRowTranslationX);
        }
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ExpandableView expandableView = (ExpandableView) view;
        if (!this.mChildTransferInProgress) {
            onViewRemovedInternal(expandableView, this);
        }
        this.mShelf.getClass();
        expandableView.requestRoundnessReset(NotificationShelf.SHELF_SCROLL);
    }

    public final void onViewRemovedInternal(ExpandableView expandableView, ViewGroup viewGroup) {
        if (this.mChangePositionInProgress) {
            return;
        }
        expandableView.mOnHeightChangedListener = null;
        boolean z = expandableView instanceof ExpandableNotificationRow;
        if (z) {
            ((ExpandableNotificationRow) expandableView).mEntry.mOnSensitivityChangedListeners.remove(this.mOnChildSensitivityChangedListener);
        }
        int positionInLinearLayout$1 = getPositionInLinearLayout$1(expandableView);
        int intrinsicHeight = expandableView.getIntrinsicHeight() + this.mPaddingBetweenElements;
        int i = positionInLinearLayout$1 + intrinsicHeight;
        int i2 = this.mShouldUseSplitNotificationShade ? this.mSidePaddings : this.mAmbientState.mTopPadding - this.mQsScrollBoundaryPosition;
        this.mAnimateStackYForContentHeightChange = true;
        int i3 = this.mOwnScrollY;
        int i4 = i3 - i2;
        if (i <= i4) {
            setOwnScrollY(i3 - intrinsicHeight);
        } else if (positionInLinearLayout$1 < i4) {
            setOwnScrollY(positionInLinearLayout$1 + i2);
        }
        if (viewGroup == null || !generateRemoveAnimation(expandableView)) {
            this.mSwipedOutViews.remove(expandableView);
            if (z) {
                ((ExpandableNotificationRow) expandableView).removeChildrenWithKeepInParent();
            }
        } else if (!this.mSwipedOutViews.contains(expandableView) || !isFullySwipedOut(expandableView)) {
            NotificationStackScrollLogger notificationStackScrollLogger = this.mLogger;
            if (notificationStackScrollLogger != null && z) {
                boolean z2 = viewGroup instanceof NotificationChildrenContainer;
                LogBuffer logBuffer = notificationStackScrollLogger.notificationRenderBuffer;
                if (z2) {
                    NotificationEntry notificationEntry = ((ExpandableNotificationRow) expandableView).mEntry;
                    NotificationEntry notificationEntry2 = ((NotificationChildrenContainer) viewGroup).mContainingNotification.mEntry;
                    LogMessage obtain = logBuffer.obtain("NotificationStackScroll", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$addTransientChildNotificationToChildContainer$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("addTransientChildToContainer from onViewRemovedInternal: childKey: ", logMessage.getStr1(), " -- containerKey: ", logMessage.getStr2());
                        }
                    }, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                    logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
                    logBuffer.commit(obtain);
                } else if (viewGroup instanceof NotificationStackScrollLayout) {
                    NotificationEntry notificationEntry3 = ((ExpandableNotificationRow) expandableView).mEntry;
                    LogMessage obtain2 = logBuffer.obtain("NotificationStackScroll", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$addTransientChildNotificationToNssl$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("addTransientRowToNssl from onViewRemovedInternal: childKey: ", ((LogMessage) obj).getStr1());
                        }
                    }, null);
                    ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry3);
                    logBuffer.commit(obtain2);
                } else {
                    NotificationEntry notificationEntry4 = ((ExpandableNotificationRow) expandableView).mEntry;
                    LogMessage obtain3 = logBuffer.obtain("NotificationStackScroll", LogLevel.ERROR, new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$addTransientChildNotificationToViewGroup$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("addTransientRowTo unhandled ViewGroup from onViewRemovedInternal: childKey: ", logMessage.getStr1(), " -- ViewGroup: ", logMessage.getStr2());
                        }
                    }, null);
                    LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain3;
                    logMessageImpl2.str1 = NotificationUtilsKt.getLogKey(notificationEntry4);
                    logMessageImpl2.str2 = viewGroup.toString();
                    logBuffer.commit(obtain3);
                }
            }
            viewGroup.addTransientView(expandableView, 0);
            expandableView.mTransientContainer = viewGroup;
        }
        if (z) {
            ((ExpandableNotificationRow) expandableView).setAnimationRunning(false);
        }
        if (z) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            if (expandableNotificationRow.mRefocusOnDismiss || expandableNotificationRow.isAccessibilityFocused()) {
                View view = expandableNotificationRow.mChildAfterViewWhenDismissed;
                if (view == null) {
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow.mGroupParentWhenDismissed;
                    view = getFirstChildBelowTranlsationY(expandableNotificationRow2 != null ? expandableNotificationRow2.getTranslationY() : expandableView.getTranslationY());
                }
                if (view != null) {
                    view.requestAccessibilityFocus();
                }
            }
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            return;
        }
        cancelLongPress();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0020, code lost:
    
        if (r5 != 16908346) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean performAccessibilityActionInternal(int r5, android.os.Bundle r6) {
        /*
            r4 = this;
            boolean r6 = super.performAccessibilityActionInternal(r5, r6)
            r0 = 1
            if (r6 == 0) goto L8
            return r0
        L8:
            boolean r6 = r4.isEnabled()
            r1 = 0
            if (r6 != 0) goto L10
            return r1
        L10:
            r6 = 4096(0x1000, float:5.74E-42)
            if (r5 == r6) goto L25
            r6 = 8192(0x2000, float:1.148E-41)
            if (r5 == r6) goto L23
            r6 = 16908344(0x1020038, float:2.3877386E-38)
            if (r5 == r6) goto L23
            r6 = 16908346(0x102003a, float:2.3877392E-38)
            if (r5 == r6) goto L25
            goto L5c
        L23:
            r5 = -1
            goto L26
        L25:
            r5 = r0
        L26:
            int r6 = r4.getHeight()
            int r2 = r4.mPaddingBottom
            int r6 = r6 - r2
            com.android.systemui.statusbar.notification.stack.AmbientState r2 = r4.mAmbientState
            int r2 = r2.mTopPadding
            int r6 = r6 - r2
            int r2 = r4.mPaddingTop
            int r6 = r6 - r2
            com.android.systemui.statusbar.NotificationShelf r2 = r4.mShelf
            int r2 = r2.getHeight()
            int r6 = r6 - r2
            int r2 = r4.mOwnScrollY
            int r5 = r5 * r6
            int r5 = r5 + r2
            int r6 = r4.getScrollRange()
            int r5 = java.lang.Math.min(r5, r6)
            int r5 = java.lang.Math.max(r1, r5)
            int r6 = r4.mOwnScrollY
            if (r5 == r6) goto L5c
            android.widget.OverScroller r2 = r4.mScroller
            int r3 = r4.mScrollX
            int r5 = r5 - r6
            r2.startScroll(r3, r6, r1, r5)
            r4.animateScroll()
            return r0
        L5c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.performAccessibilityActionInternal(int, android.os.Bundle):boolean");
    }

    public final void removeTransientView(View view) {
        NotificationStackScrollLogger notificationStackScrollLogger = this.mLogger;
        if (notificationStackScrollLogger != null && (view instanceof ExpandableNotificationRow)) {
            NotificationEntry notificationEntry = ((ExpandableNotificationRow) view).mEntry;
            notificationStackScrollLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            NotificationStackScrollLogger$removeTransientRow$2 notificationStackScrollLogger$removeTransientRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger$removeTransientRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("removeTransientRow from NSSL: childKey: ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = notificationStackScrollLogger.notificationRenderBuffer;
            LogMessage obtain = logBuffer.obtain("NotificationStackScroll", logLevel, notificationStackScrollLogger$removeTransientRow$2, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
        }
        super.removeTransientView(view);
    }

    public final void requestChildrenUpdate() {
        if (this.mChildrenUpdateRequested) {
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(this.mChildrenUpdater);
        this.mChildrenUpdateRequested = true;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            cancelLongPress();
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        Trace.instant(4096L, "NotificationStackScrollLayout#requestLayout");
        super.requestLayout();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resetAllSwipeState() {
        List<ExpandableNotificationRow> attachedChildren;
        Trace.beginSection("NSSL.resetAllSwipeState()");
        this.mSwipeHelper.resetSwipeStates(true);
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            NotificationSwipeHelper notificationSwipeHelper = this.mSwipeHelper;
            notificationSwipeHelper.getClass();
            if (childAt.getTranslationX() != 0.0f) {
                if (childAt instanceof SwipeableView) {
                    ((ExpandableNotificationRow) ((SwipeableView) childAt)).setTranslation(0.0f);
                }
                notificationSwipeHelper.updateSwipeProgressFromOffset(childAt, 0.0f, true);
            }
            if ((childAt instanceof ExpandableNotificationRow) && (attachedChildren = ((ExpandableNotificationRow) childAt).getAttachedChildren()) != null) {
                for (ExpandableNotificationRow expandableNotificationRow : attachedChildren) {
                    NotificationSwipeHelper notificationSwipeHelper2 = this.mSwipeHelper;
                    notificationSwipeHelper2.getClass();
                    if (expandableNotificationRow.getTranslationX() != 0.0f) {
                        expandableNotificationRow.setTranslation(0.0f);
                        notificationSwipeHelper2.updateSwipeProgressFromOffset(expandableNotificationRow, 0.0f, true);
                    }
                }
            }
        }
        updateContinuousShadowDrawing();
        Trace.endSection();
    }

    public final boolean scrollTo(View view) {
        ExpandableView expandableView = (ExpandableView) view;
        int positionInLinearLayout$1 = getPositionInLinearLayout$1(view);
        int targetScrollForView = targetScrollForView(expandableView, positionInLinearLayout$1);
        int intrinsicHeight = expandableView.getIntrinsicHeight() + positionInLinearLayout$1;
        int i = this.mOwnScrollY;
        if (i >= targetScrollForView && intrinsicHeight >= i) {
            return false;
        }
        this.mScroller.startScroll(((ViewGroup) this).mScrollX, i, 0, targetScrollForView - i);
        this.mDontReportNextOverScroll = true;
        animateScroll();
        return true;
    }

    public final void setAnimationRunning(boolean z) {
        if (z != this.mAnimationRunning) {
            if (z) {
                getViewTreeObserver().addOnPreDrawListener(this.mRunningAnimationUpdater);
            } else {
                getViewTreeObserver().removeOnPreDrawListener(this.mRunningAnimationUpdater);
            }
            this.mAnimationRunning = z;
            updateContinuousShadowDrawing();
        }
    }

    public void setClearAllInProgress(boolean z) {
        this.mClearAllInProgress = z;
        this.mAmbientState.mClearAllInProgress = z;
        this.mController.mNotificationRoundnessManager.mIsClearAllInProgress = z;
    }

    public final void setExpandedHeight(float f) {
        int i;
        boolean shouldSkipHeightUpdate = shouldSkipHeightUpdate();
        updateStackPosition(false);
        float f2 = 0.0f;
        if (!shouldSkipHeightUpdate) {
            this.mExpandedHeight = f;
            setIsExpanded(f > 0.0f);
            float minExpansionHeight = getMinExpansionHeight();
            if (f >= minExpansionHeight || this.mShouldUseSplitNotificationShade) {
                this.mRequestedClipBounds = null;
                updateClipping$1();
            } else {
                Rect rect = this.mClipRect;
                rect.left = 0;
                rect.right = getWidth();
                Rect rect2 = this.mClipRect;
                rect2.top = 0;
                rect2.bottom = (int) f;
                this.mRequestedClipBounds = rect2;
                updateClipping$1();
                f = minExpansionHeight;
            }
        }
        float f3 = 1.0f;
        if (calculateAppearFraction(f) < 1.0f) {
            f3 = calculateAppearFraction(f);
            float interpolate = f3 >= 0.0f ? NotificationUtils.interpolate((getMinExpansionHeight() + (-this.mAmbientState.mTopPadding)) - this.mShelf.getHeight(), 0.0f, f3) : (f - getAppearStartPosition()) + ((getMinExpansionHeight() + (-this.mAmbientState.mTopPadding)) - this.mShelf.getHeight());
            i = (int) (f - interpolate);
            if (!isHeadsUpTransition() || f3 < 0.0f) {
                f2 = interpolate;
            } else {
                f2 = MathUtils.lerp(this.mHeadsUpInset - (this.mShouldUseSplitNotificationShade ? this.mAmbientState.mStackTopMargin : this.mAmbientState.mTopPadding), 0.0f, f3);
            }
        } else if (this.mShouldShowShelfOnly) {
            i = this.mAmbientState.mTopPadding + this.mShelf.getHeight();
        } else {
            if (this.mQsFullScreen) {
                int i2 = (this.mContentHeight - this.mAmbientState.mTopPadding) + this.mIntrinsicPadding;
                int height = this.mShelf.getHeight() + this.mMaxTopPadding;
                if (i2 <= height) {
                    i = height;
                } else if (!this.mShouldUseSplitNotificationShade) {
                    f = NotificationUtils.interpolate(i2, height, this.mQsExpansionFraction);
                }
            } else if (shouldSkipHeightUpdate) {
                f = this.mExpandedHeight;
            }
            i = (int) f;
        }
        this.mAmbientState.mAppearFraction = f3;
        if (i != this.mCurrentStackHeight && !shouldSkipHeightUpdate) {
            this.mCurrentStackHeight = i;
            updateAlgorithmHeightAndPadding();
            requestChildrenUpdate();
        }
        AmbientState ambientState = this.mAmbientState;
        if (f2 != ambientState.mStackTranslation) {
            ambientState.mStackTranslation = f2;
            requestChildrenUpdate();
        }
        notifyAppearChangedListeners();
    }

    public void setExpandedInThisMotion(boolean z) {
        this.mExpandedInThisMotion = z;
    }

    public void setExpandingNotification(boolean z) {
        this.mExpandingNotification = z;
    }

    public final void setHeadsUpAnimatingAway(boolean z) {
        if (this.mHeadsUpAnimatingAway != z) {
            this.mHeadsUpAnimatingAway = z;
        }
        updateClipping$1();
    }

    public void setIsBeingDragged(boolean z) {
        this.mIsBeingDragged = z;
        if (z) {
            requestDisallowInterceptTouchEvent(true);
            cancelLongPress();
            this.mSwipeHelper.resetExposedMenuView(true, true);
        }
    }

    public void setIsExpanded(boolean z) {
        boolean z2 = z != this.mIsExpanded;
        this.mIsExpanded = z;
        this.mStackScrollAlgorithm.mIsExpanded = z;
        this.mAmbientState.mShadeExpanded = z;
        this.mStateAnimator.mShadeExpanded = z;
        this.mSwipeHelper.mIsExpanded = z;
        if (z2) {
            this.mWillExpand = false;
            if (!z) {
                GroupExpansionManagerImpl groupExpansionManagerImpl = this.mGroupExpansionManager;
                groupExpansionManagerImpl.getClass();
                Iterator it = new ArrayList(groupExpansionManagerImpl.mExpandedGroups).iterator();
                while (it.hasNext()) {
                    groupExpansionManagerImpl.setGroupExpanded((NotificationEntry) it.next(), false);
                }
                ExpandHelper expandHelper = this.mExpandHelper;
                expandHelper.finishExpanding(0.0f, true, false);
                expandHelper.mResizedView = null;
                expandHelper.mSGD = new ScaleGestureDetector(expandHelper.mContext, expandHelper.mScaleGestureListener);
                if (!this.mIsExpansionChanging) {
                    resetAllSwipeState();
                }
                finalizeClearAllAnimation();
            }
            updateNotificationAnimationStates();
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof ExpandableNotificationRow) {
                    ((ExpandableNotificationRow) childAt).setChronometerRunning(this.mIsExpanded);
                }
            }
            requestChildrenUpdate();
            updateUseRoundedRectClipping();
            updateDismissBehavior();
        }
    }

    public final void setOverScrollAmount(float f, boolean z, boolean z2, boolean z3) {
        setOverScrollAmount(f, z, z2, z3, isRubberbanded(z));
    }

    public void setOwnScrollY(int i) {
        setOwnScrollY(i, false);
    }

    public final float setPulseHeight(float f) {
        float max;
        this.mAmbientState.setPulseHeight(f);
        if (this.mKeyguardBypassEnabled) {
            notifyAppearChangedListeners();
            max = Math.max(0.0f, f - this.mIntrinsicPadding);
        } else {
            max = Math.max(0.0f, f - this.mAmbientState.getInnerHeight(true));
        }
        requestChildrenUpdate();
        return max;
    }

    public void setStatusBarState(int i) {
        this.mStatusBarState = i;
        AmbientState ambientState = this.mAmbientState;
        if (ambientState.mStatusBarState != 1) {
            ambientState.mIsFlingRequiredAfterLockScreenSwipeUp = false;
        }
        ambientState.mStatusBarState = i;
        this.mSpeedBumpIndexDirty = true;
        updateDismissBehavior();
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    public final boolean shouldSkipHeightUpdate() {
        if (this.mAmbientState.isOnKeyguard()) {
            AmbientState ambientState = this.mAmbientState;
            if (ambientState.mIsSwipingUp || (ambientState.mIsFlinging && ambientState.mIsFlingRequiredAfterLockScreenSwipeUp)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:285:0x0587, code lost:
    
        if (r1 == 13) goto L253;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startAnimationToState$1() {
        /*
            Method dump skipped, instructions count: 2274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.startAnimationToState$1():void");
    }

    public final int targetScrollForView(ExpandableView expandableView, int i) {
        return ((Math.max(0, this.mImeInset - ((getRootView().getHeight() - getHeight()) - getLocationOnScreen()[1])) + (expandableView.getIntrinsicHeight() + i)) - getHeight()) + ((this.mIsExpanded || !isPinnedHeadsUp(expandableView)) ? this.mAmbientState.mTopPadding : this.mHeadsUpInset);
    }

    public final void updateAlgorithmHeightAndPadding() {
        this.mAmbientState.mLayoutHeight = Math.min(this.mMaxLayoutHeight, this.mCurrentStackHeight);
        this.mAmbientState.mLayoutMaxHeight = this.mMaxLayoutHeight;
        updateAlgorithmLayoutMinHeight();
    }

    public final void updateAlgorithmLayoutMinHeight() {
        this.mAmbientState.mLayoutMinHeight = (this.mQsFullScreen || isHeadsUpTransition()) ? getLayoutMinHeightInternal() : 0;
    }

    public final void updateClipping$1() {
        boolean z = (this.mRequestedClipBounds == null || this.mInHeadsUpPinnedMode || this.mHeadsUpAnimatingAway) ? false : true;
        if (this.mIsClipped != z) {
            this.mIsClipped = z;
        }
        if (this.mAmbientState.isHiddenAtAll()) {
            invalidateOutline();
            if (this.mAmbientState.isFullyHidden()) {
                setClipBounds(null);
            }
        } else if (z) {
            setClipBounds(this.mRequestedClipBounds);
        } else {
            setClipBounds(null);
        }
        setClipToOutline(false);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [kotlin.coroutines.jvm.internal.RestrictedSuspendLambda, kotlin.jvm.functions.Function2] */
    public final void updateContentHeight() {
        Object invoke;
        float f = this.mAmbientState.isOnKeyguard() ? 0 : this.mMinimumPaddings;
        NotificationShelf notificationShelf = this.mShelf;
        int height = notificationShelf != null ? notificationShelf.getHeight() : 0;
        int i = (int) f;
        NotificationStackSizeCalculator notificationStackSizeCalculator = this.mNotificationStackSizeCalculator;
        int i2 = this.mMaxDisplayedNotifications;
        notificationStackSizeCalculator.getClass();
        final SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 = new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1(notificationStackSizeCalculator, this, height, null));
        Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeHeight$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Number) obj).intValue();
                Iterator it = sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1.iterator();
                if (!it.hasNext()) {
                    throw new NoSuchElementException("Sequence is empty.");
                }
                Object next = it.next();
                while (it.hasNext()) {
                    next = it.next();
                }
                return (NotificationStackSizeCalculator.StackHeight) next;
            }
        };
        if (i2 >= 0) {
            SequenceBuilderIterator it = SequencesKt__SequenceBuilderKt.iterator(sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1.$block$inlined);
            int i3 = 0;
            while (true) {
                if (!it.hasNext()) {
                    invoke = function1.invoke(Integer.valueOf(i2));
                    break;
                }
                Object next = it.next();
                int i4 = i3 + 1;
                if (i2 == i3) {
                    invoke = next;
                    break;
                }
                i3 = i4;
            }
        } else {
            invoke = function1.invoke(Integer.valueOf(i2));
        }
        NotificationStackSizeCalculator.StackHeight stackHeight = (NotificationStackSizeCalculator.StackHeight) invoke;
        float f2 = stackHeight.notifsHeight;
        boolean z = notificationStackSizeCalculator.saveSpaceOnLockscreen;
        float f3 = stackHeight.shelfHeightWithSpaceBefore;
        float f4 = i + ((int) (z ? stackHeight.notifsHeightSavingSpace + f3 : f2 + f3));
        this.mIntrinsicContentHeight = f4;
        this.mContentHeight = (int) (f4 + Math.max(this.mIntrinsicPadding, this.mAmbientState.mTopPadding) + this.mBottomPadding);
        boolean z2 = !this.mQsFullScreen && getScrollRange() > 0;
        if (z2 != this.mScrollable) {
            this.mScrollable = z2;
            setFocusable(z2);
            updateForwardAndBackwardScrollability();
        }
        clampScrollPosition();
        updateStackPosition(false);
        this.mAmbientState.mContentHeight = this.mContentHeight;
    }

    public final void updateContinuousShadowDrawing() {
        boolean z = this.mAnimationRunning || this.mSwipeHelper.mIsSwiping;
        if (z != this.mContinuousShadowUpdate) {
            if (z) {
                getViewTreeObserver().addOnPreDrawListener(this.mShadowUpdater);
            } else {
                getViewTreeObserver().removeOnPreDrawListener(this.mShadowUpdater);
            }
            this.mContinuousShadowUpdate = z;
        }
    }

    public final void updateDecorViews() {
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorOnSurface, 0, ((ViewGroup) this).mContext);
        int colorAttrDefaultColor2 = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorOnSurfaceVariant, 0, ((ViewGroup) this).mContext);
        int i = ColorUpdateLogger.$r8$clinit;
        NotificationSectionsManager notificationSectionsManager = this.mSectionsManager;
        SectionHeaderView sectionHeaderView = notificationSectionsManager.peopleHeaderController._view;
        if (sectionHeaderView != null) {
            sectionHeaderView.mLabelView.setTextColor(colorAttrDefaultColor);
            sectionHeaderView.mClearAllButton.setImageTintList(ColorStateList.valueOf(colorAttrDefaultColor2));
        }
        SectionHeaderView sectionHeaderView2 = notificationSectionsManager.silentHeaderController._view;
        if (sectionHeaderView2 != null) {
            sectionHeaderView2.mLabelView.setTextColor(colorAttrDefaultColor);
            sectionHeaderView2.mClearAllButton.setImageTintList(ColorStateList.valueOf(colorAttrDefaultColor2));
        }
        SectionHeaderView sectionHeaderView3 = notificationSectionsManager.alertingHeaderController._view;
        if (sectionHeaderView3 != null) {
            sectionHeaderView3.mLabelView.setTextColor(colorAttrDefaultColor);
            sectionHeaderView3.mClearAllButton.setImageTintList(ColorStateList.valueOf(colorAttrDefaultColor2));
        }
        FooterView footerView = this.mFooterView;
        if (footerView != null) {
            footerView.updateColors$2();
        }
        EmptyShadeView emptyShadeView = this.mEmptyShadeView;
        if (emptyShadeView != null) {
            emptyShadeView.mEmptyText.setTextColor(colorAttrDefaultColor2);
            emptyShadeView.mEmptyFooterText.setTextColor(colorAttrDefaultColor);
            emptyShadeView.mEmptyFooterText.setCompoundDrawableTintList(ColorStateList.valueOf(colorAttrDefaultColor));
        }
    }

    public final void updateDismissBehavior() {
        boolean z = true;
        if (this.mShouldUseSplitNotificationShade && (this.mStatusBarState == 1 || !this.mIsExpanded)) {
            z = false;
        }
        if (this.mDismissUsingRowTranslationX != z) {
            this.mDismissUsingRowTranslationX = z;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof ExpandableNotificationRow) {
                    ((ExpandableNotificationRow) childAt).setDismissUsingRowTranslationX(z);
                }
            }
        }
    }

    public final void updateEmptyShadeViewResources(int i, int i2, int i3) {
        Drawable drawable;
        EmptyShadeView emptyShadeView = this.mEmptyShadeView;
        if (emptyShadeView.mText != i) {
            emptyShadeView.mText = i;
            emptyShadeView.mEmptyText.setText(i);
        }
        EmptyShadeView emptyShadeView2 = this.mEmptyShadeView;
        if (emptyShadeView2.mFooterText != i2) {
            emptyShadeView2.mFooterText = i2;
            if (i2 != 0) {
                emptyShadeView2.mEmptyFooterText.setText(i2);
            } else {
                emptyShadeView2.mEmptyFooterText.setText((CharSequence) null);
            }
        }
        EmptyShadeView emptyShadeView3 = this.mEmptyShadeView;
        if (emptyShadeView3.mFooterIcon != i3) {
            emptyShadeView3.mFooterIcon = i3;
            if (i3 == 0) {
                drawable = null;
            } else {
                drawable = emptyShadeView3.getResources().getDrawable(i3);
                int i4 = emptyShadeView3.mSize;
                drawable.setBounds(0, 0, i4, i4);
            }
            emptyShadeView3.mEmptyFooterText.setCompoundDrawablesRelative(drawable, null, null, null);
        }
        if (i3 == 0 && i2 == 0) {
            EmptyShadeView emptyShadeView4 = this.mEmptyShadeView;
            emptyShadeView4.mFooterVisibility = 8;
            emptyShadeView4.setSecondaryVisible(false, false, null);
        } else {
            EmptyShadeView emptyShadeView5 = this.mEmptyShadeView;
            emptyShadeView5.mFooterVisibility = 0;
            emptyShadeView5.setSecondaryVisible(true, false, null);
        }
    }

    public final void updateFirstAndLastBackgroundViews() {
        ExpandableView expandableView;
        int childCount = getChildCount() - 1;
        while (true) {
            if (childCount < 0) {
                expandableView = null;
                break;
            }
            expandableView = (ExpandableView) getChildAt(childCount);
            if (expandableView.getVisibility() != 8 && !(expandableView instanceof StackScrollerDecorView) && expandableView != this.mShelf) {
                break;
            } else {
                childCount--;
            }
        }
        this.mSectionsManager.updateFirstAndLastViewsForAllSections(this.mSections, getChildrenWithBackground());
        this.mAmbientState.mLastVisibleBackgroundChild = expandableView;
        invalidate();
    }

    public void updateFooter() {
        FooterViewRefactor.assertInLegacyMode();
        throw null;
    }

    public final void updateForcedScroll() {
        View view = this.mForcedScroll;
        if (view != null && (!view.hasFocus() || !this.mForcedScroll.isAttachedToWindow())) {
            this.mForcedScroll = null;
        }
        View view2 = this.mForcedScroll;
        if (view2 != null) {
            ExpandableView expandableView = (ExpandableView) view2;
            int positionInLinearLayout$1 = getPositionInLinearLayout$1(expandableView);
            int targetScrollForView = targetScrollForView(expandableView, positionInLinearLayout$1);
            int intrinsicHeight = expandableView.getIntrinsicHeight() + positionInLinearLayout$1;
            int max = Math.max(0, Math.min(targetScrollForView, getScrollRange()));
            int i = this.mOwnScrollY;
            if (i < max || intrinsicHeight < i) {
                setOwnScrollY(max);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateForwardAndBackwardScrollability() {
        /*
            r5 = this;
            boolean r0 = r5.mScrollable
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L15
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$6 r0 = r5.mScrollAdapter
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.this
            int r3 = r0.mOwnScrollY
            int r0 = r0.getScrollRange()
            if (r3 < r0) goto L13
            goto L15
        L13:
            r0 = r2
            goto L16
        L15:
            r0 = r1
        L16:
            boolean r3 = r5.mScrollable
            if (r3 == 0) goto L25
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$6 r3 = r5.mScrollAdapter
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r3 = com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.this
            int r3 = r3.mOwnScrollY
            if (r3 != 0) goto L23
            goto L25
        L23:
            r3 = r2
            goto L26
        L25:
            r3 = r1
        L26:
            boolean r4 = r5.mForwardScrollable
            if (r0 != r4) goto L2e
            boolean r4 = r5.mBackwardScrollable
            if (r3 == r4) goto L2f
        L2e:
            r1 = r2
        L2f:
            r5.mForwardScrollable = r0
            r5.mBackwardScrollable = r3
            if (r1 == 0) goto L3a
            r0 = 2048(0x800, float:2.87E-42)
            r5.sendAccessibilityEvent(r0)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.updateForwardAndBackwardScrollability():void");
    }

    public final void updateImeInset(WindowInsets windowInsets) {
        ExpandableViewState expandableViewState;
        int i = windowInsets.getInsets(WindowInsets.Type.ime()).bottom;
        this.mImeInset = i;
        FooterView footerView = this.mFooterView;
        if (footerView != null && (expandableViewState = footerView.mViewState) != null) {
            FooterView.FooterViewState footerViewState = (FooterView.FooterViewState) expandableViewState;
            footerViewState.resetY = (i > 0) | footerViewState.resetY;
        }
        if (this.mForcedScroll != null) {
            updateForcedScroll();
        }
        int scrollRange = getScrollRange();
        if (this.mOwnScrollY > scrollRange) {
            setOwnScrollY(scrollRange);
        }
    }

    public void updateInterpolatedStackHeight(float f, float f2) {
        this.mAmbientState.mStackHeight = MathUtils.lerp(0.5f * f, f, f2);
    }

    public final void updateLaunchedNotificationClipPath() {
        if (this.mLaunchingNotificationNeedsToBeClipped && this.mLaunchingNotification && this.mExpandingNotificationRow != null) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            int min = Math.min(this.mLaunchAnimationParams.left - iArr[0], this.mRoundedRectClippingLeft);
            int max = Math.max(this.mLaunchAnimationParams.right - iArr[0], this.mRoundedRectClippingRight);
            int max2 = Math.max(this.mLaunchAnimationParams.bottom - iArr[1], this.mRoundedRectClippingBottom);
            Interpolator interpolator = Interpolators.FAST_OUT_SLOW_IN;
            LaunchAnimationParameters launchAnimationParameters = this.mLaunchAnimationParams;
            launchAnimationParameters.getClass();
            PorterDuffXfermode porterDuffXfermode = TransitionAnimator.SRC_MODE;
            int min2 = (int) Math.min(MathUtils.lerp(this.mRoundedRectClippingTop, this.mLaunchAnimationParams.top - iArr[1], ((PathInterpolator) interpolator).getInterpolation(TransitionAnimator.Companion.getProgress(ActivityTransitionAnimator.TIMINGS, launchAnimationParameters.linearProgress, 0L, 100L))), this.mRoundedRectClippingTop);
            LaunchAnimationParameters launchAnimationParameters2 = this.mLaunchAnimationParams;
            float f = launchAnimationParameters2.topCornerRadius;
            float f2 = launchAnimationParameters2.bottomCornerRadius;
            float[] fArr = this.mLaunchedNotificationRadii;
            fArr[0] = f;
            fArr[1] = f;
            fArr[2] = f;
            fArr[3] = f;
            fArr[4] = f2;
            fArr[5] = f2;
            fArr[6] = f2;
            fArr[7] = f2;
            this.mLaunchedNotificationClipPath.reset();
            this.mLaunchedNotificationClipPath.addRoundRect(min, min2, max, max2, this.mLaunchedNotificationRadii, Path.Direction.CW);
            ExpandableNotificationRow expandableNotificationRow = this.mExpandingNotificationRow;
            ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow.mNotificationParent;
            if (expandableNotificationRow2 != null) {
                expandableNotificationRow = expandableNotificationRow2;
            }
            this.mLaunchedNotificationClipPath.offset((-expandableNotificationRow.getLeft()) - expandableNotificationRow.getTranslationX(), (-expandableNotificationRow.getTop()) - expandableNotificationRow.getTranslationY());
            expandableNotificationRow.mExpandingClipPath = this.mLaunchedNotificationClipPath;
            expandableNotificationRow.invalidate();
            if (this.mShouldUseRoundedRectClipping) {
                invalidate();
            }
        }
    }

    public final void updateNotificationAnimationStates() {
        boolean z = this.mAnimationsEnabled || this.mPulsing;
        NotificationShelf notificationShelf = this.mShelf;
        notificationShelf.mAnimationsEnabled = z;
        if (!z) {
            notificationShelf.mShelfIcons.setAnimationsEnabled(false);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            z &= this.mIsExpanded || isPinnedHeadsUp(childAt);
            if (childAt instanceof ExpandableNotificationRow) {
                ((ExpandableNotificationRow) childAt).setAnimationRunning(z);
            }
        }
    }

    public final void updateOwnTranslationZ() {
        ExpandableView firstChildNotGoneInternal;
        setTranslationZ((this.mKeyguardBypassEnabled && this.mAmbientState.isHiddenAtAll() && (firstChildNotGoneInternal = getFirstChildNotGoneInternal()) != null && firstChildNotGoneInternal.showingPulsing()) ? firstChildNotGoneInternal.getTranslationZ() : 0.0f);
    }

    public final void updateRoundedClipPath() {
        this.mRoundedClipPath.reset();
        Path path = this.mRoundedClipPath;
        float f = this.mRoundedRectClippingLeft;
        int i = this.mRoundedRectClippingTop;
        int i2 = this.mRoundedRectClippingYTranslation;
        path.addRoundRect(f, i + i2, this.mRoundedRectClippingRight, this.mRoundedRectClippingBottom + i2, this.mBgCornerRadii, Path.Direction.CW);
        if (this.mShouldUseRoundedRectClipping) {
            invalidate();
        }
    }

    public void updateSplitNotificationShade() {
        boolean shouldUseSplitNotificationShade = this.mSplitShadeStateController.shouldUseSplitNotificationShade(getResources());
        if (shouldUseSplitNotificationShade != this.mShouldUseSplitNotificationShade) {
            this.mShouldUseSplitNotificationShade = shouldUseSplitNotificationShade;
            this.mShouldSkipTopPaddingAnimationAfterFold = true;
            this.mAmbientState.mUseSplitShade = shouldUseSplitNotificationShade;
            updateDismissBehavior();
            updateUseRoundedRectClipping();
            requestLayout();
        }
    }

    public void updateStackEndHeightAndStackHeight(float f) {
        float f2 = this.mAmbientState.mStackHeight;
        if (this.mQsExpansionFraction > 0.0f || shouldSkipHeightUpdate()) {
            updateInterpolatedStackHeight(this.mAmbientState.mStackEndHeight, f);
        } else {
            float max = this.mMaxDisplayedNotifications != -1 ? this.mIntrinsicContentHeight : Math.max(0.0f, (getHeight() - getEmptyBottomMarginInternal()) - this.mAmbientState.mTopPadding);
            this.mAmbientState.mStackEndHeight = max;
            updateInterpolatedStackHeight(max, f);
        }
        if (f2 != this.mAmbientState.mStackHeight) {
            requestChildrenUpdate();
        }
    }

    public void updateStackHeight() {
        SceneContainerFlag.isUnexpectedlyInLegacyMode();
    }

    public final void updateStackPosition(boolean z) {
        float currentOverScrollAmount = this.mShouldUseSplitNotificationShade ? getCurrentOverScrollAmount(true) : 0.0f;
        float currentOverScrollAmount2 = (((r2.mTopPadding + this.mExtraTopInsetForFullShadeTransition) + this.mAmbientState.mOverExpansion) + currentOverScrollAmount) - getCurrentOverScrollAmount(false);
        AmbientState ambientState = this.mAmbientState;
        float f = ambientState.mExpansionFraction;
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = ambientState.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null && statusBarKeyguardViewManager.mPrimaryBouncerInteractor.isInTransit() && this.mQsExpansionFraction > 0.0f) {
            f = BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(f);
        }
        this.mAmbientState.mStackY = MathUtils.lerp(0.0f, currentOverScrollAmount2, f);
        QuickSettingsControllerImpl$$ExternalSyntheticLambda6 quickSettingsControllerImpl$$ExternalSyntheticLambda6 = this.mOnStackYChanged;
        if (quickSettingsControllerImpl$$ExternalSyntheticLambda6 != null) {
            quickSettingsControllerImpl$$ExternalSyntheticLambda6.accept(Boolean.valueOf(z));
        }
        updateStackEndHeightAndStackHeight(f);
    }

    public final void updateUseRoundedRectClipping() {
        boolean z = this.mIsExpanded && ((this.mQsExpansionFraction > 0.5f ? 1 : (this.mQsExpansionFraction == 0.5f ? 0 : -1)) < 0 || this.mShouldUseSplitNotificationShade);
        if (z != this.mShouldUseRoundedRectClipping) {
            this.mShouldUseRoundedRectClipping = z;
            invalidate();
        }
    }

    public final void updateViewShadows() {
        for (int i = 0; i < getChildCount(); i++) {
            ExpandableView expandableView = (ExpandableView) getChildAt(i);
            if (expandableView.getVisibility() != 8) {
                this.mTmpSortedChildren.add(expandableView);
            }
        }
        Collections.sort(this.mTmpSortedChildren, this.mViewPositionComparator);
        ExpandableView expandableView2 = null;
        int i2 = 0;
        while (i2 < this.mTmpSortedChildren.size()) {
            ExpandableView expandableView3 = (ExpandableView) this.mTmpSortedChildren.get(i2);
            float translationZ = expandableView3.getTranslationZ();
            float translationZ2 = (expandableView2 == null ? translationZ : expandableView2.getTranslationZ()) - translationZ;
            if (translationZ2 <= 0.0f || translationZ2 >= RUBBER_BAND_FACTOR_NORMAL) {
                expandableView3.setFakeShadowIntensity(0.0f, 0.0f, 0, 0);
            } else {
                expandableView3.setFakeShadowIntensity(translationZ2 / RUBBER_BAND_FACTOR_NORMAL, expandableView2.getOutlineAlpha(), (int) ((expandableView2.getTranslationY() + expandableView2.mActualHeight) - expandableView3.getTranslationY()), (int) (expandableView2.getTranslation() + expandableView2.getOutlineTranslation()));
            }
            i2++;
            expandableView2 = expandableView3;
        }
        this.mTmpSortedChildren.clear();
    }

    public final void updateVisibility$7() {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mController;
        boolean z = (this.mAmbientState.isFullyHidden() && onKeyguard()) ? false : true;
        notificationStackScrollLayoutController.getClass();
        notificationStackScrollLayoutController.mView.setVisibility(z ? 0 : 4);
    }

    public final void setOverScrollAmount(float f, final boolean z, boolean z2, boolean z3, final boolean z4) {
        if (z3) {
            StackStateAnimator stackStateAnimator = this.mStateAnimator;
            ValueAnimator valueAnimator = z ? stackStateAnimator.mTopOverScrollAnimator : stackStateAnimator.mBottomOverScrollAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }
        float max = Math.max(0.0f, f);
        if (!z2) {
            float rubberBandFactor = max / getRubberBandFactor(z);
            if (z) {
                this.mOverScrolledTopPixels = rubberBandFactor;
            } else {
                this.mOverScrolledBottomPixels = rubberBandFactor;
            }
            AmbientState ambientState = this.mAmbientState;
            if (z) {
                ambientState.mOverScrollTopAmount = max;
            } else {
                ambientState.mOverScrollBottomAmount = max;
            }
            if (z) {
                notifyOverscrollTopListener(max, z4);
            }
            updateStackPosition(false);
            requestChildrenUpdate();
            return;
        }
        final StackStateAnimator stackStateAnimator2 = this.mStateAnimator;
        float currentOverScrollAmount = stackStateAnimator2.mHostLayout.getCurrentOverScrollAmount(z);
        if (max == currentOverScrollAmount) {
            return;
        }
        ValueAnimator valueAnimator2 = z ? stackStateAnimator2.mTopOverScrollAnimator : stackStateAnimator2.mBottomOverScrollAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(currentOverScrollAmount, max);
        ofFloat.setDuration(360L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.notification.stack.StackStateAnimator.3
            public final /* synthetic */ boolean val$isRubberbanded;
            public final /* synthetic */ boolean val$onTop;

            public AnonymousClass3(final boolean z5, final boolean z42) {
                r2 = z5;
                r3 = z42;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                StackStateAnimator.this.mHostLayout.setOverScrollAmount(((Float) valueAnimator3.getAnimatedValue()).floatValue(), r2, false, false, r3);
            }
        });
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofFloat.addListener(stackStateAnimator2.new AnonymousClass2(z5));
        ofFloat.start();
        if (z5) {
            stackStateAnimator2.mTopOverScrollAnimator = ofFloat;
        } else {
            stackStateAnimator2.mBottomOverScrollAnimator = ofFloat;
        }
    }

    public final void setOwnScrollY(int i, boolean z) {
        int i2;
        AmbientState ambientState = this.mAmbientState;
        if (ambientState.mIsClosing || ambientState.mClearAllInProgress || i == (i2 = this.mOwnScrollY)) {
            return;
        }
        int i3 = ((ViewGroup) this).mScrollX;
        onScrollChanged(i3, i, i3, i2);
        this.mOwnScrollY = i;
        AmbientState ambientState2 = this.mAmbientState;
        ambientState2.getClass();
        ambientState2.mScrollY = Math.max(i, 0);
        QuickSettingsControllerImpl$$ExternalSyntheticLambda6 quickSettingsControllerImpl$$ExternalSyntheticLambda6 = this.mScrollListener;
        if (quickSettingsControllerImpl$$ExternalSyntheticLambda6 != null) {
            quickSettingsControllerImpl$$ExternalSyntheticLambda6.accept(Integer.valueOf(this.mOwnScrollY));
        }
        updateForwardAndBackwardScrollability();
        requestChildrenUpdate();
        updateStackPosition(z);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }
}
