package com.android.systemui.statusbar.notification.row;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.IndentingPrintWriter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.ContrastColorUtil;
import com.android.internal.widget.CallLayout;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationGroupingUtil;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.AboveShelfObserver;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.ConversationNotificationManager$onEntryViewBound$1;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationClicker$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.NotificationFadeAware;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.RoundableState;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.OnUserInteractionCallbackImpl;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerLogger;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.stack.SwipeableView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.InflatedSmartReplyState;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.statusbar.policy.SmartReplyView;
import com.android.systemui.util.DumpUtilsKt;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ExpandableNotificationRow extends ActivatableNotificationView implements PluginListener, SwipeableView, NotificationFadeAware.FadeOptimizedNotification {
    public static final AnonymousClass2 TRANSLATE_CONTENT;
    public boolean mAboveShelf;
    public AboveShelfObserver mAboveShelfChangedListener;
    public boolean mAnimationRunning;
    public String mAppName;
    public NotificationClicker$$ExternalSyntheticLambda0 mBubbleClickListener;
    public KeyguardBypassController mBypassController;
    public View mChildAfterViewWhenDismissed;
    public boolean mChildIsExpanding;
    public NotificationChildrenContainer mChildrenContainer;
    public NotificationChildrenContainerLogger mChildrenContainerLogger;
    public ViewStub mChildrenContainerStub;
    public boolean mChildrenExpanded;
    public ColorUpdateLogger mColorUpdateLogger;
    public NotificationDismissibilityProviderImpl mDismissibilityProvider;
    public ExpandableNotificationRowDragController mDragController;
    public boolean mEnableNonGroupedNotificationExpand;
    public NotificationEntry mEntry;
    public boolean mExpandAnimationRunning;
    public final AnonymousClass1 mExpandClickListener;
    public boolean mExpandable;
    public boolean mExpandedWhenPinned;
    public Path mExpandingClipPath;
    public ConversationNotificationManager$onEntryViewBound$1 mExpansionChangedListener;
    public final ExpandableNotificationRow$$ExternalSyntheticLambda0 mExpireRecentlyAlertedFlag;
    public FalsingManager mFalsingManager;
    public boolean mGroupExpansionChanging;
    public GroupExpansionManagerImpl mGroupExpansionManager;
    public GroupMembershipManagerImpl mGroupMembershipManager;
    public ExpandableNotificationRow mGroupParentWhenDismissed;
    public NotificationGuts mGuts;
    public ViewStub mGutsStub;
    public boolean mHasUserChangedExpansion;
    public float mHeaderVisibleAmount;
    public NotificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0 mHeadsUpAnimatingAwayListener;
    public HeadsUpManager mHeadsUpManager;
    public boolean mHeadsupDisappearRunning;
    public boolean mHideSensitiveForIntrinsicHeight;
    public boolean mIgnoreLockscreenConstraints;
    public final NotificationInlineImageResolver mImageResolver;
    public boolean mIsFaded;
    public boolean mIsHeadsUp;
    public boolean mIsMinimized;
    public boolean mIsPinned;
    public boolean mIsSnoozed;
    public boolean mIsSummaryWithChildren;
    public boolean mIsSystemChildExpanded;
    public boolean mIsSystemExpanded;
    public boolean mJustClicked;
    public boolean mKeepInParentForDismissAnimation;
    public boolean mLastChronometerRunning;
    public NotificationMenuRow mLayoutListener;
    public NotificationContentView[] mLayouts;
    public ExpandableNotificationRowController.AnonymousClass2 mLogger;
    public String mLoggingKey;
    public ExpandableNotificationRowController$$ExternalSyntheticLambda0 mLongPressListener;
    public int mMaxExpandedHeight;
    public int mMaxHeadsUpHeight;
    public int mMaxHeadsUpHeightBeforeN;
    public int mMaxHeadsUpHeightBeforeP;
    public int mMaxHeadsUpHeightBeforeS;
    public int mMaxHeadsUpHeightIncreased;
    public int mMaxSmallHeight;
    public int mMaxSmallHeightBeforeN;
    public int mMaxSmallHeightBeforeP;
    public int mMaxSmallHeightBeforeS;
    public int mMaxSmallHeightLarge;
    public NotificationMenuRowPlugin mMenuRow;
    public MetricsLogger mMetricsLogger;
    public boolean mMustStayOnScreen;
    public int mNotificationColor;
    public NotificationGutsManager mNotificationGutsManager;
    public int mNotificationLaunchHeight;
    public ExpandableNotificationRow mNotificationParent;
    public View.OnClickListener mOnClickListener;
    public NotificationClicker.AnonymousClass1 mOnDragSuccessListener;
    public StatusBarNotificationPresenter mOnExpandClickListener;
    public ExpandableNotificationRow$$ExternalSyntheticLambda3 mOnFeedbackClickListener;
    public ConversationNotificationManager$onEntryViewBound$1.AnonymousClass1 mOnIntrinsicHeightReachedRunnable;
    public boolean mOnKeyguard;
    public OnUserInteractionCallbackImpl mOnUserInteractionCallback;
    public PeopleNotificationIdentifierImpl mPeopleNotificationIdentifier;
    public NotificationContentView mPrivateLayout;
    public NotificationContentView mPublicLayout;
    public RowContentBindStage mRowContentBindStage;
    public boolean mSaveSpaceOnLockscreen;
    public StatusBarNotificationPresenter$$ExternalSyntheticLambda0 mSecureStateProvider;
    public boolean mSensitive;
    public boolean mSensitiveHiddenInGeneral;
    public boolean mShowGroupBackgroundWhenExpanded;
    public boolean mShowNoBackground;
    public boolean mShowPublicExpander;
    public boolean mShowSnooze;
    public boolean mShowingPublic;
    public boolean mShowingPublicInitialized;
    public final float mSmallRoundness;
    public StatusBarStateController mStatusBarStateController;
    public Animator mTranslateAnim;
    public ArrayList mTranslateableViews;
    public boolean mUpdateSelfBackgroundOnUpdate;
    public boolean mUseIncreasedCollapsedHeight;
    public boolean mUseIncreasedHeadsUpHeight;
    public boolean mUserExpanded;
    public boolean mUserLocked;
    public static final long RECENTLY_ALERTED_THRESHOLD_MS = TimeUnit.SECONDS.toMillis(30);
    public static final SourceType$Companion$from$1 BASE_VALUE = new SourceType$Companion$from$1("BaseValue");
    public static final SourceType$Companion$from$1 FROM_PARENT = new SourceType$Companion$from$1("FromParent(ENR)");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$1, reason: invalid class name */
    public final class AnonymousClass1 implements View.OnClickListener {
        public AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            boolean z;
            ExpandableNotificationRow expandableNotificationRow = ExpandableNotificationRow.this;
            SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
            if (!expandableNotificationRow.shouldShowPublic() && (!expandableNotificationRow.mIsMinimized || expandableNotificationRow.isExpanded(false))) {
                GroupMembershipManagerImpl groupMembershipManagerImpl = expandableNotificationRow.mGroupMembershipManager;
                NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                groupMembershipManagerImpl.getClass();
                if (GroupMembershipManagerImpl.isGroupSummary(notificationEntry)) {
                    expandableNotificationRow.mGroupExpansionChanging = true;
                    boolean isGroupExpanded = expandableNotificationRow.mGroupExpansionManager.isGroupExpanded(expandableNotificationRow.mEntry);
                    GroupExpansionManagerImpl groupExpansionManagerImpl = expandableNotificationRow.mGroupExpansionManager;
                    NotificationEntry notificationEntry2 = expandableNotificationRow.mEntry;
                    groupExpansionManagerImpl.setGroupExpanded(notificationEntry2, !groupExpansionManagerImpl.isGroupExpanded(notificationEntry2));
                    boolean isGroupExpanded2 = groupExpansionManagerImpl.isGroupExpanded(notificationEntry2);
                    expandableNotificationRow.mOnExpandClickListener.onExpandClicked(expandableNotificationRow.mEntry, isGroupExpanded2);
                    expandableNotificationRow.mMetricsLogger.action(408, isGroupExpanded2);
                    expandableNotificationRow.onExpansionChanged(true, isGroupExpanded);
                    return;
                }
            }
            if (expandableNotificationRow.mEnableNonGroupedNotificationExpand) {
                if (view.isAccessibilityFocused()) {
                    expandableNotificationRow.mPrivateLayout.mFocusOnVisibilityChange = true;
                }
                if (expandableNotificationRow.mIsPinned) {
                    z = !expandableNotificationRow.mExpandedWhenPinned;
                    expandableNotificationRow.mExpandedWhenPinned = z;
                    ConversationNotificationManager$onEntryViewBound$1 conversationNotificationManager$onEntryViewBound$1 = expandableNotificationRow.mExpansionChangedListener;
                    if (conversationNotificationManager$onEntryViewBound$1 != null) {
                        conversationNotificationManager$onEntryViewBound$1.onExpansionChanged(z);
                    }
                } else {
                    z = !expandableNotificationRow.isExpanded(false);
                    expandableNotificationRow.setUserExpanded(z, false);
                }
                expandableNotificationRow.notifyHeightChanged(true);
                expandableNotificationRow.mOnExpandClickListener.onExpandClicked(expandableNotificationRow.mEntry, z);
                expandableNotificationRow.mMetricsLogger.action(407, z);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$2, reason: invalid class name */
    public final class AnonymousClass2 extends FloatProperty {
        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((ExpandableNotificationRow) obj).getTranslation());
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            ((ExpandableNotificationRow) obj).setTranslation(f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotificationViewState extends ExpandableViewState {
        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void animateTo(View view, AnimationProperties animationProperties) {
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (expandableNotificationRow.mExpandAnimationRunning) {
                    return;
                }
                if (expandableNotificationRow.mChildIsExpanding) {
                    setZTranslation(expandableNotificationRow.getTranslationZ());
                    this.clipTopAmount = expandableNotificationRow.mClipTopAmount;
                }
                super.animateTo(view, animationProperties);
                if (expandableNotificationRow.mIsSummaryWithChildren) {
                    NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                    int size = ((ArrayList) notificationChildrenContainer.mAttachedChildren).size();
                    ViewState viewState = new ViewState();
                    float groupExpandFraction = notificationChildrenContainer.getGroupExpandFraction();
                    boolean z = (notificationChildrenContainer.mChildrenExpanded && notificationChildrenContainer.mShowDividersWhenExpanded) || ((!notificationChildrenContainer.showingAsLowPriority() && (notificationChildrenContainer.mUserLocked || notificationChildrenContainer.mContainingNotification.isGroupExpansionChanging())) && !notificationChildrenContainer.mHideDividersDuringExpand);
                    for (int i = size - 1; i >= 0; i--) {
                        ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) ((ArrayList) notificationChildrenContainer.mAttachedChildren).get(i);
                        ExpandableViewState expandableViewState = expandableNotificationRow2.mViewState;
                        expandableViewState.animateTo(expandableNotificationRow2, animationProperties);
                        View view2 = (View) ((ArrayList) notificationChildrenContainer.mDividers).get(i);
                        viewState.initFrom(view2);
                        viewState.setYTranslation(expandableViewState.mYTranslation - notificationChildrenContainer.mDividerHeight);
                        float f = (!notificationChildrenContainer.mChildrenExpanded || expandableViewState.mAlpha == 0.0f) ? 0.0f : notificationChildrenContainer.mDividerAlpha;
                        if (notificationChildrenContainer.mUserLocked && !notificationChildrenContainer.showingAsLowPriority()) {
                            float f2 = expandableViewState.mAlpha;
                            if (f2 != 0.0f) {
                                f = NotificationUtils.interpolate(0.0f, notificationChildrenContainer.mDividerAlpha, Math.min(f2, groupExpandFraction));
                            }
                        }
                        viewState.hidden = !z;
                        viewState.setAlpha(f);
                        viewState.animateTo(view2, animationProperties);
                        expandableNotificationRow2.setFakeShadowIntensity(0.0f, 0.0f, 0, 0);
                    }
                    if (notificationChildrenContainer.mOverflowNumber != null) {
                        if (notificationChildrenContainer.mNeverAppliedGroupState) {
                            ViewState viewState2 = notificationChildrenContainer.mGroupOverFlowState;
                            float f3 = viewState2.mAlpha;
                            viewState2.setAlpha(0.0f);
                            notificationChildrenContainer.mGroupOverFlowState.applyToView(notificationChildrenContainer.mOverflowNumber);
                            notificationChildrenContainer.mGroupOverFlowState.setAlpha(f3);
                            notificationChildrenContainer.mNeverAppliedGroupState = false;
                        }
                        notificationChildrenContainer.mGroupOverFlowState.animateTo(notificationChildrenContainer.mOverflowNumber, animationProperties);
                    }
                    View view3 = notificationChildrenContainer.mGroupHeader;
                    if (view3 != null) {
                        notificationChildrenContainer.mHeaderViewState.applyToView(view3);
                    }
                    notificationChildrenContainer.updateChildrenClipping();
                }
            }
        }

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void applyToView(View view) {
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (expandableNotificationRow.mExpandAnimationRunning) {
                    return;
                }
                if (expandableNotificationRow.mChildIsExpanding) {
                    setZTranslation(expandableNotificationRow.getTranslationZ());
                    this.clipTopAmount = expandableNotificationRow.mClipTopAmount;
                }
                super.applyToView(view);
                if (expandableNotificationRow.mIsSummaryWithChildren) {
                    NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                    int size = ((ArrayList) notificationChildrenContainer.mAttachedChildren).size();
                    ViewState viewState = new ViewState();
                    float groupExpandFraction = notificationChildrenContainer.mUserLocked ? notificationChildrenContainer.getGroupExpandFraction() : 0.0f;
                    boolean z = (notificationChildrenContainer.mChildrenExpanded && notificationChildrenContainer.mShowDividersWhenExpanded) || ((!notificationChildrenContainer.showingAsLowPriority() && (notificationChildrenContainer.mUserLocked || notificationChildrenContainer.mContainingNotification.isGroupExpansionChanging())) && !notificationChildrenContainer.mHideDividersDuringExpand);
                    for (int i = 0; i < size; i++) {
                        ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) ((ArrayList) notificationChildrenContainer.mAttachedChildren).get(i);
                        ExpandableViewState expandableViewState = expandableNotificationRow2.mViewState;
                        expandableViewState.applyToView(expandableNotificationRow2);
                        View view2 = (View) ((ArrayList) notificationChildrenContainer.mDividers).get(i);
                        viewState.initFrom(view2);
                        viewState.setYTranslation(expandableViewState.mYTranslation - notificationChildrenContainer.mDividerHeight);
                        float f = (!notificationChildrenContainer.mChildrenExpanded || expandableViewState.mAlpha == 0.0f) ? 0.0f : notificationChildrenContainer.mDividerAlpha;
                        if (notificationChildrenContainer.mUserLocked && !notificationChildrenContainer.showingAsLowPriority()) {
                            float f2 = expandableViewState.mAlpha;
                            if (f2 != 0.0f) {
                                f = NotificationUtils.interpolate(0.0f, notificationChildrenContainer.mDividerAlpha, Math.min(f2, groupExpandFraction));
                            }
                        }
                        viewState.hidden = !z;
                        viewState.setAlpha(f);
                        viewState.applyToView(view2);
                        expandableNotificationRow2.setFakeShadowIntensity(0.0f, 0.0f, 0, 0);
                    }
                    ViewState viewState2 = notificationChildrenContainer.mGroupOverFlowState;
                    if (viewState2 != null) {
                        viewState2.applyToView(notificationChildrenContainer.mOverflowNumber);
                        notificationChildrenContainer.mNeverAppliedGroupState = false;
                    }
                    ViewState viewState3 = notificationChildrenContainer.mHeaderViewState;
                    if (viewState3 != null) {
                        viewState3.applyToView(notificationChildrenContainer.mGroupHeader);
                    }
                    notificationChildrenContainer.updateChildrenClipping();
                }
            }
        }

        @Override // com.android.systemui.statusbar.notification.stack.ViewState
        public final void onYTranslationAnimationFinished(View view) {
            super.onYTranslationAnimationFinished(view);
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (expandableNotificationRow.mHeadsupDisappearRunning) {
                    expandableNotificationRow.setHeadsUpAnimatingAway(false);
                }
            }
        }
    }

    /* renamed from: $r8$lambda$-m9ShXoKTwBLcNnosIeqOTeZZvE, reason: not valid java name */
    public static void m878$r8$lambda$m9ShXoKTwBLcNnosIeqOTeZZvE(ExpandableNotificationRow expandableNotificationRow, ExpandableNotificationRowController$$ExternalSyntheticLambda0 expandableNotificationRowController$$ExternalSyntheticLambda0, View view) {
        NotificationMenuRowPlugin.MenuItem feedbackMenuItem;
        expandableNotificationRow.createMenu();
        NotificationMenuRowPlugin notificationMenuRowPlugin = expandableNotificationRow.mMenuRow;
        if (notificationMenuRowPlugin == null || (feedbackMenuItem = notificationMenuRowPlugin.getFeedbackMenuItem(((FrameLayout) expandableNotificationRow).mContext)) == null) {
            return;
        }
        ((NotificationGutsManager) expandableNotificationRowController$$ExternalSyntheticLambda0.f$0).openGuts(expandableNotificationRow, view.getWidth() / 2, view.getHeight() / 2, feedbackMenuItem);
    }

    static {
        SystemProperties.getInt("persist.notifications.extra_measure_delay_ms", 150);
        TRANSLATE_CONTENT = new AnonymousClass2("translate");
    }

    public ExpandableNotificationRow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, context);
        throw new UnsupportedOperationException("Insecure constructor");
    }

    public static /* synthetic */ void access$001(ExpandableNotificationRow expandableNotificationRow, long j, float f, boolean z, Runnable runnable, AnimatorListenerAdapter animatorListenerAdapter) {
        super.performRemoveAnimation(j, f, z, null, runnable, animatorListenerAdapter, ExpandableView.ClipSide.BOTTOM);
    }

    public static void setChronometerRunningForChild(View view, boolean z) {
        if (view != null) {
            View findViewById = view.findViewById(R.id.columnWidth);
            if (findViewById instanceof Chronometer) {
                ((Chronometer) findViewById).setStarted(z);
            }
        }
    }

    public static void setIconAnimationRunningForChild(View view, boolean z) {
        if (view != null) {
            setImageViewAnimationRunning((ImageView) view.findViewById(R.id.icon), z);
            setImageViewAnimationRunning((ImageView) view.findViewById(R.id.scrollView), z);
        }
    }

    public static void setImageViewAnimationRunning(ImageView imageView, boolean z) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
                if (z) {
                    animationDrawable.start();
                    return;
                } else {
                    animationDrawable.stop();
                    return;
                }
            }
            if (drawable instanceof AnimatedVectorDrawable) {
                AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                if (z) {
                    animatedVectorDrawable.start();
                } else {
                    animatedVectorDrawable.stop();
                }
            }
        }
    }

    public final void addChildNotification(ExpandableNotificationRow expandableNotificationRow, int i) {
        if (this.mChildrenContainer == null) {
            this.mChildrenContainerStub.inflate();
        }
        if (expandableNotificationRow.mKeepInParentForDismissAnimation) {
            ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
            if (anonymousClass2 != null) {
                NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                NotificationEntry notificationEntry2 = this.mEntry;
                NotificationRowLogger notificationRowLogger = ExpandableNotificationRowController.this.mLogBufferLogger;
                notificationRowLogger.getClass();
                LogLevel logLevel = LogLevel.WARNING;
                NotificationRowLogger$logSkipAttachingKeepInParentChild$2 notificationRowLogger$logSkipAttachingKeepInParentChild$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logSkipAttachingKeepInParentChild$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return MotionLayout$$ExternalSyntheticOutline0.m("Skipping to attach ", logMessage.getStr1(), " to ", logMessage.getStr2(), ", because it still flagged to keep in parent");
                    }
                };
                LogBuffer logBuffer = notificationRowLogger.buffer;
                LogMessage obtain = logBuffer.obtain("NotifRow", logLevel, notificationRowLogger$logSkipAttachingKeepInParentChild$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
                logBuffer.commit(obtain);
                return;
            }
            return;
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        notificationChildrenContainer.getClass();
        if (expandableNotificationRow.getParent() != null) {
            expandableNotificationRow.removeFromTransientContainerForAdditionTo(notificationChildrenContainer);
        }
        if (i < 0) {
            i = ((ArrayList) notificationChildrenContainer.mAttachedChildren).size();
        }
        notificationChildrenContainer.mAttachedChildren.add(i, expandableNotificationRow);
        notificationChildrenContainer.addView(expandableNotificationRow);
        expandableNotificationRow.setUserLocked(notificationChildrenContainer.mUserLocked);
        View inflateDivider = notificationChildrenContainer.inflateDivider();
        notificationChildrenContainer.addView(inflateDivider);
        notificationChildrenContainer.mDividers.add(i, inflateDivider);
        expandableNotificationRow.setNotificationFaded(notificationChildrenContainer.mContainingNotificationIsFaded);
        ExpandableViewState expandableViewState = expandableNotificationRow.mViewState;
        if (expandableViewState != null) {
            expandableViewState.cancelAnimations(expandableNotificationRow);
            ValueAnimator valueAnimator = expandableNotificationRow.mAppearAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                expandableNotificationRow.mAppearAnimator = null;
            }
            expandableNotificationRow.enableAppearDrawing(false);
        }
        notificationChildrenContainer.applyRoundnessAndInvalidate();
        onAttachedChildrenCountChanged();
        expandableNotificationRow.setIsChildInGroup(this, true);
    }

    public final void addTransientView(View view, int i) {
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
            if (anonymousClass2 != null) {
                NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                NotificationEntry notificationEntry2 = this.mEntry;
                NotificationRowLogger notificationRowLogger = ExpandableNotificationRowController.this.mLogBufferLogger;
                notificationRowLogger.getClass();
                LogLevel logLevel = LogLevel.ERROR;
                NotificationRowLogger$logAddTransientRow$2 notificationRowLogger$logAddTransientRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logAddTransientRow$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        String str1 = logMessage.getStr1();
                        String str2 = logMessage.getStr2();
                        int int1 = logMessage.getInt1();
                        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("addTransientRow to row: childKey: ", str1, " -- containerKey: ", str2, " -- index: ");
                        m.append(int1);
                        return m.toString();
                    }
                };
                LogBuffer logBuffer = notificationRowLogger.notificationRenderBuffer;
                LogMessage obtain = logBuffer.obtain("NotifRow", logLevel, notificationRowLogger$logAddTransientRow$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
                logMessageImpl.int1 = i;
                logBuffer.commit(obtain);
            }
        }
        super.addTransientView(view, i);
    }

    public final String appendTraceStyleTag(String str) {
        if (!Trace.isEnabled()) {
            return str;
        }
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "(");
        m.append(this.mEntry.getNotificationStyle());
        m.append(")");
        return m.toString();
    }

    public final void applyAudiblyAlertedRecently(boolean z) {
        if (this.mIsSummaryWithChildren) {
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = notificationChildrenContainer.mGroupHeaderWrapper;
            if (notificationHeaderViewWrapper != null) {
                notificationHeaderViewWrapper.setRecentlyAudiblyAlerted(z);
            }
            NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = notificationChildrenContainer.mMinimizedGroupHeaderWrapper;
            if (notificationHeaderViewWrapper2 != null) {
                notificationHeaderViewWrapper2.setRecentlyAudiblyAlerted(z);
            }
        }
        NotificationContentView notificationContentView = this.mPrivateLayout;
        if (notificationContentView.mContractedChild != null) {
            notificationContentView.mContractedWrapper.setRecentlyAudiblyAlerted(z);
        }
        if (notificationContentView.mExpandedChild != null) {
            notificationContentView.mExpandedWrapper.setRecentlyAudiblyAlerted(z);
        }
        if (notificationContentView.mHeadsUpChild != null) {
            notificationContentView.mHeadsUpWrapper.setRecentlyAudiblyAlerted(z);
        }
        NotificationContentView notificationContentView2 = this.mPublicLayout;
        if (notificationContentView2.mContractedChild != null) {
            notificationContentView2.mContractedWrapper.setRecentlyAudiblyAlerted(z);
        }
        if (notificationContentView2.mExpandedChild != null) {
            notificationContentView2.mExpandedWrapper.setRecentlyAudiblyAlerted(z);
        }
        if (notificationContentView2.mHeadsUpChild != null) {
            notificationContentView2.mHeadsUpWrapper.setRecentlyAudiblyAlerted(z);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.Roundable
    public final void applyRoundnessAndInvalidate() {
        if (this.mIsSummaryWithChildren) {
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            RoundableState roundableState = ((ActivatableNotificationView) this).mRoundableState;
            notificationChildrenContainer.requestRoundness(roundableState.topRoundness, roundableState.bottomRoundness, FROM_PARENT, false);
        }
        super.applyRoundnessAndInvalidate();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean areChildrenExpanded() {
        return this.mChildrenExpanded;
    }

    public final boolean areGutsExposed() {
        NotificationGuts notificationGuts = this.mGuts;
        return notificationGuts != null && notificationGuts.mExposed;
    }

    public final boolean canShowHeadsUp() {
        KeyguardBypassController keyguardBypassController;
        if (!this.mOnKeyguard) {
            return true;
        }
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        if ((statusBarStateController != null && statusBarStateController.isDozing()) || (keyguardBypassController = this.mBypassController) == null || keyguardBypassController.getBypassEnabled()) {
            return true;
        }
        if (this.mEntry.isStickyAndNotDemoted()) {
            return this.mIgnoreLockscreenConstraints || !this.mSaveSpaceOnLockscreen;
        }
        return false;
    }

    public final boolean canViewBeDismissed() {
        return (this.mDismissibilityProvider.nonDismissableEntryKeys.contains(this.mEntry.mKey) || (shouldShowPublic() && this.mSensitiveHiddenInGeneral)) ? false : true;
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final boolean childNeedsClipping(View view) {
        if (view instanceof NotificationContentView) {
            NotificationContentView notificationContentView = (NotificationContentView) view;
            if (isClippingNeeded()) {
                return true;
            }
            if (hasRoundedCorner()) {
                RoundableState roundableState = ((ActivatableNotificationView) this).mRoundableState;
                float f = roundableState.topRoundness;
                boolean z = roundableState.bottomRoundness != 0.0f;
                NotificationViewWrapper visibleWrapper = notificationContentView.getVisibleWrapper(notificationContentView.mVisibleType);
                boolean shouldClipToRounding = visibleWrapper == null ? false : visibleWrapper.shouldClipToRounding(z);
                if (notificationContentView.mUserExpanding) {
                    NotificationViewWrapper visibleWrapper2 = notificationContentView.getVisibleWrapper(notificationContentView.mTransformationStartVisibleType);
                    shouldClipToRounding |= visibleWrapper2 != null ? visibleWrapper2.shouldClipToRounding(z) : false;
                }
                if (shouldClipToRounding) {
                    return true;
                }
            }
        } else if (view == this.mChildrenContainer) {
            if (isClippingNeeded() || hasRoundedCorner()) {
                return true;
            }
        } else if (view instanceof NotificationGuts) {
            return hasRoundedCorner();
        }
        return super.childNeedsClipping(view);
    }

    public final boolean childrenRequireOverlappingRendering() {
        if (this.mEntry.mSbn.getNotification().isColorized()) {
            return true;
        }
        NotificationContentView showingLayout = getShowingLayout();
        if (showingLayout != null) {
            RemoteInputView remoteInputView = showingLayout.mHeadsUpRemoteInput;
            if (remoteInputView != null && remoteInputView.isActive()) {
                return true;
            }
            RemoteInputView remoteInputView2 = showingLayout.mExpandedRemoteInput;
            if (remoteInputView2 != null && remoteInputView2.isActive()) {
                return true;
            }
        }
        return false;
    }

    public final void collectVisibleLocations(Map map) {
        if (getVisibility() != 0) {
            return;
        }
        map.put(this.mEntry.mKey, Integer.valueOf(this.mViewState.location));
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer == null) {
            return;
        }
        List list = notificationChildrenContainer.mAttachedChildren;
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i >= arrayList.size()) {
                return;
            }
            ((ExpandableNotificationRow) arrayList.get(i)).collectVisibleLocations(map);
            i++;
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final ExpandableViewState createExpandableViewState() {
        return new NotificationViewState();
    }

    public final NotificationMenuRowPlugin createMenu() {
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin == null) {
            return null;
        }
        if (notificationMenuRowPlugin.getMenuView() == null) {
            this.mMenuRow.createMenu(this, this.mEntry.mSbn);
            this.mMenuRow.setAppName(this.mAppName);
            addView(this.mMenuRow.getMenuView(), 0, new FrameLayout.LayoutParams(-1, -1));
        }
        return this.mMenuRow;
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        canvas.save();
        Path path = this.mExpandingClipPath;
        if (path != null && (this.mExpandAnimationRunning || this.mChildIsExpanding)) {
            canvas.clipPath(path);
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public final void doLongClickCallback(int i, int i2) {
        createMenu();
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        doLongClickCallback(i, i2, notificationMenuRowPlugin != null ? notificationMenuRowPlugin.getLongpressMenuItem(((FrameLayout) this).mContext) : null);
    }

    public final void doSmartActionClick(int i, int i2) {
        createMenu();
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        NotificationMenuRowPlugin.MenuItem longpressMenuItem = notificationMenuRowPlugin != null ? notificationMenuRowPlugin.getLongpressMenuItem(((FrameLayout) this).mContext) : null;
        if (longpressMenuItem.getGutsView() instanceof NotificationConversationInfo) {
            ((NotificationConversationInfo) longpressMenuItem.getGutsView()).setSelectedAction(2);
        }
        doLongClickCallback(i, i2, longpressMenuItem);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, final String[] strArr) {
        final IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("Notification: " + this.mEntry.mKey);
        DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                ExpandableNotificationRow expandableNotificationRow = ExpandableNotificationRow.this;
                IndentingPrintWriter indentingPrintWriter = asIndenting;
                String[] strArr2 = strArr;
                SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
                indentingPrintWriter.println(expandableNotificationRow);
                indentingPrintWriter.print("visibility: " + expandableNotificationRow.getVisibility());
                indentingPrintWriter.print(", alpha: " + expandableNotificationRow.getAlpha());
                indentingPrintWriter.print(", translation: " + expandableNotificationRow.getTranslation());
                indentingPrintWriter.print(", entry dismissable: " + (expandableNotificationRow.mDismissibilityProvider.nonDismissableEntryKeys.contains(expandableNotificationRow.mEntry.mKey) ^ true));
                StringBuilder sb = new StringBuilder(", mOnUserInteractionCallback null: ");
                sb.append(expandableNotificationRow.mOnUserInteractionCallback == null);
                indentingPrintWriter.print(sb.toString());
                indentingPrintWriter.print(", removed: false");
                indentingPrintWriter.print(", expandAnimationRunning: " + expandableNotificationRow.mExpandAnimationRunning);
                indentingPrintWriter.print(", mShowingPublic: " + expandableNotificationRow.mShowingPublic);
                indentingPrintWriter.print(", mShowingPublicInitialized: " + expandableNotificationRow.mShowingPublicInitialized);
                NotificationContentView showingLayout = expandableNotificationRow.getShowingLayout();
                StringBuilder sb2 = new StringBuilder(", privateShowing: ");
                sb2.append(showingLayout == expandableNotificationRow.mPrivateLayout);
                indentingPrintWriter.print(sb2.toString());
                indentingPrintWriter.print(", mShowNoBackground: " + expandableNotificationRow.mShowNoBackground);
                indentingPrintWriter.println();
                indentingPrintWriter.print("Heights: ");
                indentingPrintWriter.print("intrinsic", Integer.valueOf(expandableNotificationRow.getIntrinsicHeight()));
                indentingPrintWriter.print("actual", Integer.valueOf(expandableNotificationRow.mActualHeight));
                indentingPrintWriter.print("maxContent", Integer.valueOf(expandableNotificationRow.getMaxContentHeight()));
                indentingPrintWriter.print("maxExpanded", Integer.valueOf(expandableNotificationRow.getMaxExpandHeight()));
                indentingPrintWriter.print("collapsed", Integer.valueOf(expandableNotificationRow.getCollapsedHeight()));
                indentingPrintWriter.print("headsup", Integer.valueOf(expandableNotificationRow.getHeadsUpHeight()));
                indentingPrintWriter.print("headsup  without header", Integer.valueOf(expandableNotificationRow.getHeadsUpHeightWithoutHeader()));
                indentingPrintWriter.print("minHeight", Integer.valueOf(expandableNotificationRow.getMinHeight(false)));
                indentingPrintWriter.print("pinned headsup", Integer.valueOf(expandableNotificationRow.getPinnedHeadsUpHeight(true)));
                indentingPrintWriter.println();
                indentingPrintWriter.print("Intrinsic Height Factors: ");
                indentingPrintWriter.print("isUserLocked()", Boolean.valueOf(expandableNotificationRow.mUserLocked));
                indentingPrintWriter.print("isChildInGroup()", Boolean.valueOf(expandableNotificationRow.isChildInGroup()));
                indentingPrintWriter.print("isGroupExpanded()", Boolean.valueOf(expandableNotificationRow.isGroupExpanded()));
                indentingPrintWriter.print("sensitive", Boolean.valueOf(expandableNotificationRow.mSensitive));
                indentingPrintWriter.print("hideSensitiveForIntrinsicHeight", Boolean.valueOf(expandableNotificationRow.mHideSensitiveForIntrinsicHeight));
                indentingPrintWriter.print("isSummaryWithChildren", Boolean.valueOf(expandableNotificationRow.mIsSummaryWithChildren));
                indentingPrintWriter.print("canShowHeadsUp()", Boolean.valueOf(expandableNotificationRow.canShowHeadsUp()));
                indentingPrintWriter.print("isHeadsUpState()", Boolean.valueOf(expandableNotificationRow.isHeadsUpState()));
                indentingPrintWriter.print("isPinned()", Boolean.valueOf(expandableNotificationRow.mIsPinned));
                indentingPrintWriter.print("headsupDisappearRunning", Boolean.valueOf(expandableNotificationRow.mHeadsupDisappearRunning));
                indentingPrintWriter.print("isExpanded()", Boolean.valueOf(expandableNotificationRow.isExpanded(false)));
                indentingPrintWriter.println();
                indentingPrintWriter.print("contentView visibility: " + showingLayout.getVisibility());
                indentingPrintWriter.print(", alpha: " + showingLayout.getAlpha());
                indentingPrintWriter.print(", clipBounds: " + showingLayout.getClipBounds());
                indentingPrintWriter.print(", contentHeight: " + showingLayout.mContentHeight);
                indentingPrintWriter.print(", visibleType: " + showingLayout.mVisibleType);
                View viewForVisibleType = showingLayout.getViewForVisibleType(showingLayout.mVisibleType);
                indentingPrintWriter.print(", visibleView ");
                if (viewForVisibleType != null) {
                    indentingPrintWriter.print(" visibility: " + viewForVisibleType.getVisibility());
                    indentingPrintWriter.print(", alpha: " + viewForVisibleType.getAlpha());
                    indentingPrintWriter.print(", clipBounds: " + viewForVisibleType.getClipBounds());
                } else {
                    indentingPrintWriter.print("null");
                }
                indentingPrintWriter.println();
                IndentingPrintWriter asIndenting2 = DumpUtilsKt.asIndenting(indentingPrintWriter);
                asIndenting2.print("ContentDimensions: ");
                int i = showingLayout.mVisibleType;
                asIndenting2.print("visibleType(String)", i != 0 ? i != 1 ? i != 2 ? i != 3 ? "NONE" : "SINGLELINE" : "HEADSUP" : "EXPANDED" : "CONTRACTED");
                asIndenting2.print("measured width", Integer.valueOf(showingLayout.getMeasuredWidth()));
                asIndenting2.print("measured height", Integer.valueOf(showingLayout.getMeasuredHeight()));
                asIndenting2.print("maxHeight", Integer.valueOf(showingLayout.getMaxHeight()));
                asIndenting2.print("minHeight", Integer.valueOf(showingLayout.getMinHeight(false)));
                asIndenting2.println();
                asIndenting2.println("ChildViews:");
                DumpUtilsKt.withIncreasedIndent(asIndenting2, new NotificationContentView$$ExternalSyntheticLambda1(showingLayout, asIndenting2));
                int extraRemoteInputHeight = showingLayout.getExtraRemoteInputHeight(showingLayout.mExpandedRemoteInput);
                int extraRemoteInputHeight2 = showingLayout.getExtraRemoteInputHeight(showingLayout.mHeadsUpRemoteInput);
                asIndenting2.print("expandedRemoteInputHeight", Integer.valueOf(extraRemoteInputHeight));
                asIndenting2.print("headsUpRemoteInputHeight", Integer.valueOf(extraRemoteInputHeight2));
                asIndenting2.println();
                indentingPrintWriter.println("mBubblesEnabledForUser: " + showingLayout.mBubblesEnabledForUser);
                indentingPrintWriter.print("RemoteInputViews { ");
                indentingPrintWriter.print(" visibleType: " + showingLayout.mVisibleType);
                if (showingLayout.mHeadsUpRemoteInputController != null) {
                    indentingPrintWriter.print(", headsUpRemoteInputController.isActive: " + showingLayout.mHeadsUpRemoteInputController.view.isActive());
                } else {
                    indentingPrintWriter.print(", headsUpRemoteInputController: null");
                }
                if (showingLayout.mExpandedRemoteInputController != null) {
                    indentingPrintWriter.print(", expandedRemoteInputController.isActive: " + showingLayout.mExpandedRemoteInputController.view.isActive());
                } else {
                    indentingPrintWriter.print(", expandedRemoteInputController: null");
                }
                indentingPrintWriter.println(" }");
                ExpandableViewState expandableViewState = expandableNotificationRow.mViewState;
                if (expandableViewState != null) {
                    expandableViewState.dump(indentingPrintWriter, strArr2);
                    indentingPrintWriter.println();
                } else {
                    indentingPrintWriter.println("no viewState!!!");
                }
                indentingPrintWriter.println(((ActivatableNotificationView) expandableNotificationRow).mRoundableState.debugString());
                indentingPrintWriter.println("Background View: " + expandableNotificationRow.mBackgroundNormal);
                NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                int transientViewCount = notificationChildrenContainer == null ? 0 : notificationChildrenContainer.getTransientViewCount();
                if (!expandableNotificationRow.mIsSummaryWithChildren && transientViewCount <= 0) {
                    NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                    if (notificationContentView != null) {
                        if (notificationContentView.mHeadsUpSmartReplyView != null) {
                            indentingPrintWriter.println("HeadsUp SmartReplyView:");
                            indentingPrintWriter.increaseIndent();
                            notificationContentView.mHeadsUpSmartReplyView.dump(indentingPrintWriter);
                            indentingPrintWriter.decreaseIndent();
                        }
                        if (notificationContentView.mExpandedSmartReplyView != null) {
                            indentingPrintWriter.println("Expanded SmartReplyView:");
                            indentingPrintWriter.increaseIndent();
                            notificationContentView.mExpandedSmartReplyView.dump(indentingPrintWriter);
                            indentingPrintWriter.decreaseIndent();
                            return;
                        }
                        return;
                    }
                    return;
                }
                NotificationChildrenContainer notificationChildrenContainer2 = expandableNotificationRow.mChildrenContainer;
                indentingPrintWriter.println("NotificationChildrenContainer { visibility: " + notificationChildrenContainer2.getVisibility() + ", alpha: " + notificationChildrenContainer2.getAlpha() + ", translationY: " + notificationChildrenContainer2.getTranslationY() + ", roundableState: " + notificationChildrenContainer2.mRoundableState.debugString() + "}");
                indentingPrintWriter.println();
                List<ExpandableNotificationRow> attachedChildren = expandableNotificationRow.getAttachedChildren();
                StringBuilder sb3 = new StringBuilder("Children: ");
                sb3.append(attachedChildren.size());
                sb3.append(" {");
                indentingPrintWriter.print(sb3.toString());
                indentingPrintWriter.increaseIndent();
                for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                    indentingPrintWriter.println();
                    expandableNotificationRow2.dump(indentingPrintWriter, strArr2);
                }
                indentingPrintWriter.decreaseIndent();
                indentingPrintWriter.println("}");
                indentingPrintWriter.print("Transient Views: " + transientViewCount + " {");
                indentingPrintWriter.increaseIndent();
                for (int i2 = 0; i2 < transientViewCount; i2++) {
                    indentingPrintWriter.println();
                    ((ExpandableView) expandableNotificationRow.mChildrenContainer.getTransientView(i2)).dump(indentingPrintWriter, strArr2);
                }
                indentingPrintWriter.decreaseIndent();
                indentingPrintWriter.println("}");
            }
        });
    }

    public final List getAttachedChildren() {
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer == null) {
            return null;
        }
        return notificationChildrenContainer.mAttachedChildren;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getCollapsedHeight() {
        if (!this.mIsSummaryWithChildren || shouldShowPublic()) {
            return getMinHeight(false);
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        return notificationChildrenContainer.getMinHeight(notificationChildrenContainer.getMaxAllowedVisibleChildren(true), notificationChildrenContainer.mCurrentHeaderTranslation, false);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final View getContentView() {
        return (!this.mIsSummaryWithChildren || shouldShowPublic()) ? getShowingLayout() : this.mChildrenContainer;
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final Path getCustomClipPath(View view) {
        if (view instanceof NotificationGuts) {
            return getClipPath(true);
        }
        return null;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final float getHeaderVisibleAmount() {
        return this.mHeaderVisibleAmount;
    }

    public final int getHeadsUpHeight() {
        return getShowingLayout().getHeadsUpHeight(false);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getHeadsUpHeightWithoutHeader() {
        if (!canShowHeadsUp() || !this.mIsHeadsUp) {
            return getCollapsedHeight();
        }
        if (!this.mIsSummaryWithChildren || shouldShowPublic()) {
            return getShowingLayout().getHeadsUpHeight(true);
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        return notificationChildrenContainer.getMinHeight(notificationChildrenContainer.getMaxAllowedVisibleChildren(true), 0, false);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getHeightWithoutLockscreenConstraints() {
        this.mIgnoreLockscreenConstraints = true;
        int intrinsicHeight = getIntrinsicHeight();
        this.mIgnoreLockscreenConstraints = false;
        return intrinsicHeight;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getIntrinsicHeight() {
        boolean z;
        if (this.mUserLocked) {
            return this.mActualHeight;
        }
        NotificationGuts notificationGuts = this.mGuts;
        if (notificationGuts == null || !(z = notificationGuts.mExposed)) {
            return (!isChildInGroup() || isGroupExpanded()) ? (this.mSensitive && this.mHideSensitiveForIntrinsicHeight) ? getMinHeight(false) : this.mIsSummaryWithChildren ? this.mChildrenContainer.getIntrinsicHeight() : (canShowHeadsUp() && isHeadsUpState()) ? (this.mIsPinned || this.mHeadsupDisappearRunning) ? getPinnedHeadsUpHeight(true) : isExpanded(false) ? Math.max(getMaxExpandHeight(), getHeadsUpHeight()) : Math.max(getCollapsedHeight(), getHeadsUpHeight()) : isExpanded(false) ? getMaxExpandHeight() : getCollapsedHeight() : this.mPrivateLayout.getMinHeight(false);
        }
        NotificationGuts.GutsContent gutsContent = notificationGuts.mGutsContent;
        return (gutsContent == null || !z) ? notificationGuts.getHeight() : gutsContent.getActualHeight();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getMaxContentHeight() {
        return (!this.mIsSummaryWithChildren || shouldShowPublic()) ? getShowingLayout().getMaxHeight() : this.mChildrenContainer.getMaxContentHeight();
    }

    public final int getMaxExpandHeight() {
        int i;
        NotificationContentView notificationContentView = this.mPrivateLayout;
        if (notificationContentView.mExpandedChild != null) {
            i = 1;
        } else {
            if (notificationContentView.mContractedChild == null) {
                return notificationContentView.getMinHeight(false);
            }
            i = 0;
        }
        return notificationContentView.getExtraRemoteInputHeight(notificationContentView.mExpandedRemoteInput) + notificationContentView.getViewHeight(i, false);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getMinHeight(boolean z) {
        NotificationGuts notificationGuts;
        boolean z2;
        if (!z && (notificationGuts = this.mGuts) != null && (z2 = notificationGuts.mExposed)) {
            NotificationGuts.GutsContent gutsContent = notificationGuts.mGutsContent;
            return (gutsContent == null || !z2) ? notificationGuts.getHeight() : gutsContent.getActualHeight();
        }
        if (!z && canShowHeadsUp() && this.mIsHeadsUp && ((HeadsUpManagerPhone) this.mHeadsUpManager).mTrackingHeadsUp) {
            return getPinnedHeadsUpHeight(false);
        }
        if (!this.mIsSummaryWithChildren || isGroupExpanded() || shouldShowPublic()) {
            return (!z && canShowHeadsUp() && this.mIsHeadsUp) ? getHeadsUpHeight() : getShowingLayout().getMinHeight(false);
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        return notificationChildrenContainer.getMinHeight(2, notificationChildrenContainer.mCurrentHeaderTranslation, false);
    }

    public final int getOriginalIconColor() {
        int i;
        NotificationContentView showingLayout = getShowingLayout();
        NotificationViewWrapper visibleWrapper = showingLayout.getVisibleWrapper(showingLayout.mVisibleType);
        int originalIconColor = visibleWrapper != null ? visibleWrapper.getOriginalIconColor() : 1;
        if (originalIconColor != 1) {
            return originalIconColor;
        }
        NotificationEntry notificationEntry = this.mEntry;
        Context context = ((FrameLayout) this).mContext;
        boolean z = this.mIsMinimized && !isExpanded(false);
        int i2 = this.mNormalColor;
        int i3 = z ? 0 : notificationEntry.mSbn.getNotification().color;
        if (notificationEntry.mCachedContrastColorIsFor == i3 && (i = notificationEntry.mCachedContrastColor) != 1) {
            return i;
        }
        int resolveContrastColor = ContrastColorUtil.resolveContrastColor(context, i3, i2);
        notificationEntry.mCachedContrastColorIsFor = i3;
        notificationEntry.mCachedContrastColor = resolveContrastColor;
        return resolveContrastColor;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getPinnedHeadsUpHeight() {
        return getPinnedHeadsUpHeight(true);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final StatusBarIconView getShelfIcon() {
        return this.mEntry.mIcons.mShelfIcon;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final View getShelfTransformationTarget() {
        if (!this.mIsSummaryWithChildren || shouldShowPublic()) {
            NotificationContentView showingLayout = getShowingLayout();
            NotificationViewWrapper visibleWrapper = showingLayout.getVisibleWrapper(showingLayout.mVisibleType);
            if (visibleWrapper != null) {
                return visibleWrapper.getShelfTransformationTarget();
            }
            return null;
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        NotificationHeaderViewWrapper notificationHeaderViewWrapper = notificationChildrenContainer.showingAsLowPriority() ? notificationChildrenContainer.mMinimizedGroupHeaderWrapper : notificationChildrenContainer.mGroupHeaderWrapper;
        if (notificationHeaderViewWrapper == null) {
            return null;
        }
        return notificationHeaderViewWrapper.getShelfTransformationTarget();
    }

    public final NotificationContentView getShowingLayout() {
        return shouldShowPublic() ? this.mPublicLayout : this.mPrivateLayout;
    }

    public final HybridNotificationView getSingleLineView() {
        return this.mPrivateLayout.mSingleLineView;
    }

    public final Animator getTranslateViewAnimator(final float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        Animator animator = this.mTranslateAnim;
        if (animator != null) {
            animator.cancel();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, TRANSLATE_CONTENT, f);
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow.3
            public boolean cancelled = false;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator2) {
                this.cancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                NotificationMenuRowPlugin notificationMenuRowPlugin;
                if (!this.cancelled && f == 0.0f && (notificationMenuRowPlugin = ExpandableNotificationRow.this.mMenuRow) != null) {
                    notificationMenuRowPlugin.resetMenu();
                }
                ExpandableNotificationRow.this.mTranslateAnim = null;
            }
        });
        this.mTranslateAnim = ofFloat;
        return ofFloat;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final float getTranslation() {
        if (this.mDismissUsingRowTranslationX) {
            return getTranslationX();
        }
        ArrayList arrayList = this.mTranslateableViews;
        if (arrayList == null || arrayList.size() <= 0) {
            return 0.0f;
        }
        return ((View) this.mTranslateableViews.get(0)).getTranslationX();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean hasExpandingChild() {
        return this.mChildIsExpanding;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering() && childrenRequireOverlappingRendering();
    }

    public final void initDimens$1() {
        this.mMaxSmallHeightBeforeN = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_min_height_legacy, ((FrameLayout) this).mContext);
        this.mMaxSmallHeightBeforeP = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_min_height_before_p, ((FrameLayout) this).mContext);
        this.mMaxSmallHeightBeforeS = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_min_height_before_s, ((FrameLayout) this).mContext);
        this.mMaxSmallHeight = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_min_height, ((FrameLayout) this).mContext);
        this.mMaxSmallHeightLarge = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_min_height_increased, ((FrameLayout) this).mContext);
        this.mMaxExpandedHeight = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_max_height, ((FrameLayout) this).mContext);
        this.mMaxHeadsUpHeightBeforeN = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_max_heads_up_height_legacy, ((FrameLayout) this).mContext);
        this.mMaxHeadsUpHeightBeforeP = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_max_heads_up_height_before_p, ((FrameLayout) this).mContext);
        this.mMaxHeadsUpHeightBeforeS = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_max_heads_up_height_before_s, ((FrameLayout) this).mContext);
        this.mMaxHeadsUpHeight = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_max_heads_up_height, ((FrameLayout) this).mContext);
        this.mMaxHeadsUpHeightIncreased = NotificationUtils.getFontScaledHeight(com.android.wm.shell.R.dimen.notification_max_heads_up_height_increased, ((FrameLayout) this).mContext);
        Resources resources = getResources();
        this.mEnableNonGroupedNotificationExpand = resources.getBoolean(com.android.wm.shell.R.bool.config_enableNonGroupedNotificationExpand);
        this.mShowGroupBackgroundWhenExpanded = resources.getBoolean(com.android.wm.shell.R.bool.config_showGroupNotificationBgWhenExpanded);
    }

    public final void initialize(NotificationEntry notificationEntry, DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl, String str, String str2, ExpandableNotificationRowController.AnonymousClass2 anonymousClass2, KeyguardBypassController keyguardBypassController, GroupMembershipManagerImpl groupMembershipManagerImpl, GroupExpansionManagerImpl groupExpansionManagerImpl, HeadsUpManager headsUpManager, RowContentBindStage rowContentBindStage, StatusBarNotificationPresenter statusBarNotificationPresenter, ExpandableNotificationRowController$$ExternalSyntheticLambda0 expandableNotificationRowController$$ExternalSyntheticLambda0, FalsingManager falsingManager, StatusBarStateController statusBarStateController, PeopleNotificationIdentifierImpl peopleNotificationIdentifierImpl, OnUserInteractionCallbackImpl onUserInteractionCallbackImpl, NotificationGutsManager notificationGutsManager, NotificationDismissibilityProviderImpl notificationDismissibilityProviderImpl, MetricsLogger metricsLogger, NotificationChildrenContainerLogger notificationChildrenContainerLogger, ColorUpdateLogger colorUpdateLogger, SmartReplyConstants smartReplyConstants, SmartReplyController smartReplyController, IStatusBarService iStatusBarService, UiEventLogger uiEventLogger) {
        this.mEntry = notificationEntry;
        this.mAppName = str;
        if (this.mMenuRow == null) {
            this.mMenuRow = new NotificationMenuRow(((FrameLayout) this).mContext, peopleNotificationIdentifierImpl);
        }
        if (this.mMenuRow.getMenuView() != null) {
            this.mMenuRow.setAppName(this.mAppName);
        }
        this.mLogger = anonymousClass2;
        this.mLoggingKey = str2;
        this.mBypassController = keyguardBypassController;
        this.mGroupMembershipManager = groupMembershipManagerImpl;
        this.mGroupExpansionManager = groupExpansionManagerImpl;
        this.mPrivateLayout.getClass();
        this.mHeadsUpManager = headsUpManager;
        this.mRowContentBindStage = rowContentBindStage;
        this.mOnExpandClickListener = statusBarNotificationPresenter;
        this.mOnFeedbackClickListener = new ExpandableNotificationRow$$ExternalSyntheticLambda3(this, expandableNotificationRowController$$ExternalSyntheticLambda0, 0);
        this.mFalsingManager = falsingManager;
        this.mStatusBarStateController = statusBarStateController;
        this.mPeopleNotificationIdentifier = peopleNotificationIdentifierImpl;
        for (NotificationContentView notificationContentView : this.mLayouts) {
            notificationContentView.mPeopleIdentifier = this.mPeopleNotificationIdentifier;
            notificationContentView.mRemoteInputSubcomponentFactory = daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl;
            notificationContentView.mSmartReplyConstants = smartReplyConstants;
            notificationContentView.mSmartReplyController = smartReplyController;
            notificationContentView.mStatusBarService = iStatusBarService;
            notificationContentView.mUiEventLogger = uiEventLogger;
            notificationContentView.setIsRootNamespace(true);
        }
        this.mOnUserInteractionCallback = onUserInteractionCallbackImpl;
        this.mNotificationGutsManager = notificationGutsManager;
        this.mMetricsLogger = metricsLogger;
        this.mChildrenContainerLogger = notificationChildrenContainerLogger;
        this.mColorUpdateLogger = colorUpdateLogger;
        this.mDismissibilityProvider = notificationDismissibilityProviderImpl;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isAboveShelf() {
        return canShowHeadsUp() && (this.mIsPinned || this.mHeadsupDisappearRunning || ((this.mIsHeadsUp && this.mAboveShelf) || this.mExpandAnimationRunning || this.mChildIsExpanding));
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isChildInGroup() {
        return this.mNotificationParent != null;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isContentExpandable() {
        if (!this.mIsSummaryWithChildren || shouldShowPublic()) {
            return getShowingLayout().mIsContentExpandable;
        }
        return true;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isExpandAnimationRunning() {
        return this.mExpandAnimationRunning;
    }

    public final boolean isExpandable$1() {
        return (!this.mIsSummaryWithChildren || shouldShowPublic()) ? this.mEnableNonGroupedNotificationExpand && this.mExpandable : !this.mChildrenExpanded;
    }

    public final boolean isExpanded(boolean z) {
        return !shouldShowPublic() && (!this.mOnKeyguard || z) && ((!this.mHasUserChangedExpansion && (this.mIsSystemExpanded || this.mIsSystemChildExpanded)) || this.mUserExpanded);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isGroupExpanded() {
        return this.mGroupExpansionManager.isGroupExpanded(this.mEntry);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isGroupExpansionChanging() {
        return isChildInGroup() ? this.mNotificationParent.isGroupExpansionChanging() : this.mGroupExpansionChanging;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isHeadsUpAnimatingAway$1() {
        return this.mHeadsupDisappearRunning;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isHeadsUpState() {
        return this.mIsHeadsUp || this.mHeadsupDisappearRunning;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isPinned() {
        return this.mIsPinned;
    }

    @Override // android.view.View
    public final boolean isSoundEffectsEnabled() {
        StatusBarNotificationPresenter$$ExternalSyntheticLambda0 statusBarNotificationPresenter$$ExternalSyntheticLambda0;
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        return (statusBarStateController == null || !statusBarStateController.isDozing() || (statusBarNotificationPresenter$$ExternalSyntheticLambda0 = this.mSecureStateProvider) == null || statusBarNotificationPresenter$$ExternalSyntheticLambda0.getAsBoolean()) && super.isSoundEffectsEnabled();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean isSummaryWithChildren() {
        return this.mIsSummaryWithChildren;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void markHeadsUpSeen() {
        this.mMustStayOnScreen = false;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean mustStayOnScreen() {
        return this.mIsHeadsUp && this.mMustStayOnScreen;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void notifyHeightChanged(boolean z) {
        super.notifyHeightChanged(z);
        getShowingLayout().selectLayout(z || this.mUserLocked, false);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final void onAppearAnimationFinished(boolean z) {
        if (!z) {
            setHeadsUpAnimatingAway(false);
        } else {
            resetAllContentAlphas();
            setNotificationFaded(false);
        }
    }

    public final void onAttachedChildrenCountChanged() {
        NotificationViewWrapper notificationViewWrapper;
        boolean z = this.mIsSummaryWithChildren;
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        boolean z2 = notificationChildrenContainer != null && ((ArrayList) notificationChildrenContainer.mAttachedChildren).size() > 0;
        this.mIsSummaryWithChildren = z2;
        if (z2) {
            Trace.beginSection("ExpNotRow#onChildCountChanged (summary)");
        }
        if (!this.mIsSummaryWithChildren && z) {
            NotificationContentView notificationContentView = this.mPublicLayout;
            long when = this.mEntry.mSbn.getNotification().getWhen();
            if ((notificationContentView.mContractedChild == null || (notificationViewWrapper = notificationContentView.mContractedWrapper) == null) && ((notificationContentView.mExpandedChild == null || (notificationViewWrapper = notificationContentView.mExpandedWrapper) == null) && (notificationContentView.mHeadsUpChild == null || (notificationViewWrapper = notificationContentView.mHeadsUpWrapper) == null))) {
                notificationViewWrapper = null;
            }
            if (notificationViewWrapper instanceof NotificationHeaderViewWrapper) {
                ((NotificationHeaderViewWrapper) notificationViewWrapper).setNotificationWhen(when);
            }
        }
        getShowingLayout().updateBackgroundColor(false);
        this.mPrivateLayout.updateExpandButtonsDuringLayout(isExpandable$1(), false);
        if (this.mIsSummaryWithChildren) {
            this.mChildrenContainer.updateChildrenAppearance();
        }
        updateChildrenVisibility();
        if (this.mIsSummaryWithChildren) {
            NotificationChildrenContainer notificationChildrenContainer2 = this.mChildrenContainer;
            RoundableState roundableState = ((ActivatableNotificationView) this).mRoundableState;
            notificationChildrenContainer2.requestRoundness(roundableState.topRoundness, roundableState.bottomRoundness, FROM_PARENT, false);
        }
        if (this.mIsSummaryWithChildren) {
            Trace.endSection();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isChildInGroup()) {
            requestRoundnessReset(BASE_VALUE);
        } else {
            float f = this.mSmallRoundness;
            requestRoundness(f, f, BASE_VALUE);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null && notificationMenuRowPlugin.getMenuView() != null) {
            this.mMenuRow.onConfigurationChanged();
        }
        NotificationInlineImageResolver notificationInlineImageResolver = this.mImageResolver;
        if (notificationInlineImageResolver != null) {
            notificationInlineImageResolver.mMaxImageWidth = notificationInlineImageResolver.getMaxImageWidth();
            notificationInlineImageResolver.mMaxImageHeight = notificationInlineImageResolver.getMaxImageHeight();
        }
    }

    public final void onExpandedByGesture(boolean z) {
        GroupMembershipManagerImpl groupMembershipManagerImpl = this.mGroupMembershipManager;
        NotificationEntry notificationEntry = this.mEntry;
        groupMembershipManagerImpl.getClass();
        this.mMetricsLogger.action(GroupMembershipManagerImpl.isGroupSummary(notificationEntry) ? 410 : 409, z);
    }

    public final void onExpansionChanged(boolean z, boolean z2) {
        boolean isExpanded = isExpanded(false);
        if (this.mIsSummaryWithChildren && (!this.mIsMinimized || z2)) {
            isExpanded = this.mGroupExpansionManager.isGroupExpanded(this.mEntry);
        }
        if (isExpanded != z2) {
            updateShelfIconColor();
            ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
            if (anonymousClass2 != null) {
                String str = this.mLoggingKey;
                ExpandableNotificationRowController.this.mStatsLogger.onNotificationExpansionChanged(this.mViewState.location, str, isExpanded, z);
            }
            if (this.mIsSummaryWithChildren) {
                NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
                if (notificationChildrenContainer.mIsMinimized) {
                    boolean z3 = notificationChildrenContainer.mUserLocked;
                    if (z3) {
                        notificationChildrenContainer.setUserLocked(z3);
                    }
                    notificationChildrenContainer.updateHeaderVisibility(true);
                }
            }
            ConversationNotificationManager$onEntryViewBound$1 conversationNotificationManager$onEntryViewBound$1 = this.mExpansionChangedListener;
            if (conversationNotificationManager$onEntryViewBound$1 != null) {
                conversationNotificationManager$onEntryViewBound$1.onExpansionChanged(isExpanded);
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, android.view.View
    public final void onFinishInflate() {
        final int i = 1;
        super.onFinishInflate();
        this.mPublicLayout = (NotificationContentView) findViewById(com.android.wm.shell.R.id.expandedPublic);
        NotificationContentView notificationContentView = (NotificationContentView) findViewById(com.android.wm.shell.R.id.expanded);
        this.mPrivateLayout = notificationContentView;
        NotificationContentView[] notificationContentViewArr = {notificationContentView, this.mPublicLayout};
        this.mLayouts = notificationContentViewArr;
        final int i2 = 0;
        for (NotificationContentView notificationContentView2 : notificationContentViewArr) {
            notificationContentView2.mExpandClickListener = this.mExpandClickListener;
            notificationContentView2.mContainingNotification = this;
        }
        ViewStub viewStub = (ViewStub) findViewById(com.android.wm.shell.R.id.notification_guts_stub);
        this.mGutsStub = viewStub;
        viewStub.setOnInflateListener(new ViewStub.OnInflateListener(this) { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda1
            public final /* synthetic */ ExpandableNotificationRow f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.ViewStub.OnInflateListener
            public final void onInflate(ViewStub viewStub2, View view) {
                int i3 = i2;
                ExpandableNotificationRow expandableNotificationRow = this.f$0;
                switch (i3) {
                    case 0:
                        SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
                        NotificationGuts notificationGuts = (NotificationGuts) view;
                        expandableNotificationRow.mGuts = notificationGuts;
                        notificationGuts.mClipTopAmount = expandableNotificationRow.mClipTopAmount;
                        notificationGuts.invalidate();
                        NotificationGuts notificationGuts2 = expandableNotificationRow.mGuts;
                        notificationGuts2.mActualHeight = expandableNotificationRow.mActualHeight;
                        notificationGuts2.invalidate();
                        expandableNotificationRow.mGutsStub = null;
                        break;
                    default:
                        SourceType$Companion$from$1 sourceType$Companion$from$12 = ExpandableNotificationRow.BASE_VALUE;
                        NotificationChildrenContainer notificationChildrenContainer = (NotificationChildrenContainer) view;
                        expandableNotificationRow.mChildrenContainer = notificationChildrenContainer;
                        notificationChildrenContainer.mIsMinimized = expandableNotificationRow.mIsMinimized;
                        if (notificationChildrenContainer.mContainingNotification != null) {
                            notificationChildrenContainer.updateHeaderVisibility(false);
                        }
                        boolean z = notificationChildrenContainer.mUserLocked;
                        if (z) {
                            notificationChildrenContainer.setUserLocked(z);
                        }
                        NotificationChildrenContainer notificationChildrenContainer2 = expandableNotificationRow.mChildrenContainer;
                        notificationChildrenContainer2.mContainingNotification = expandableNotificationRow;
                        notificationChildrenContainer2.mGroupingUtil = new NotificationGroupingUtil(expandableNotificationRow);
                        expandableNotificationRow.mChildrenContainer.onNotificationUpdated();
                        NotificationChildrenContainer notificationChildrenContainer3 = expandableNotificationRow.mChildrenContainer;
                        notificationChildrenContainer3.mLogger = expandableNotificationRow.mChildrenContainerLogger;
                        expandableNotificationRow.mTranslateableViews.add(notificationChildrenContainer3);
                        break;
                }
            }
        });
        ViewStub viewStub2 = (ViewStub) findViewById(com.android.wm.shell.R.id.child_container_stub);
        this.mChildrenContainerStub = viewStub2;
        viewStub2.setOnInflateListener(new ViewStub.OnInflateListener(this) { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda1
            public final /* synthetic */ ExpandableNotificationRow f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.ViewStub.OnInflateListener
            public final void onInflate(ViewStub viewStub22, View view) {
                int i3 = i;
                ExpandableNotificationRow expandableNotificationRow = this.f$0;
                switch (i3) {
                    case 0:
                        SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
                        NotificationGuts notificationGuts = (NotificationGuts) view;
                        expandableNotificationRow.mGuts = notificationGuts;
                        notificationGuts.mClipTopAmount = expandableNotificationRow.mClipTopAmount;
                        notificationGuts.invalidate();
                        NotificationGuts notificationGuts2 = expandableNotificationRow.mGuts;
                        notificationGuts2.mActualHeight = expandableNotificationRow.mActualHeight;
                        notificationGuts2.invalidate();
                        expandableNotificationRow.mGutsStub = null;
                        break;
                    default:
                        SourceType$Companion$from$1 sourceType$Companion$from$12 = ExpandableNotificationRow.BASE_VALUE;
                        NotificationChildrenContainer notificationChildrenContainer = (NotificationChildrenContainer) view;
                        expandableNotificationRow.mChildrenContainer = notificationChildrenContainer;
                        notificationChildrenContainer.mIsMinimized = expandableNotificationRow.mIsMinimized;
                        if (notificationChildrenContainer.mContainingNotification != null) {
                            notificationChildrenContainer.updateHeaderVisibility(false);
                        }
                        boolean z = notificationChildrenContainer.mUserLocked;
                        if (z) {
                            notificationChildrenContainer.setUserLocked(z);
                        }
                        NotificationChildrenContainer notificationChildrenContainer2 = expandableNotificationRow.mChildrenContainer;
                        notificationChildrenContainer2.mContainingNotification = expandableNotificationRow;
                        notificationChildrenContainer2.mGroupingUtil = new NotificationGroupingUtil(expandableNotificationRow);
                        expandableNotificationRow.mChildrenContainer.onNotificationUpdated();
                        NotificationChildrenContainer notificationChildrenContainer3 = expandableNotificationRow.mChildrenContainer;
                        notificationChildrenContainer3.mLogger = expandableNotificationRow.mChildrenContainerLogger;
                        expandableNotificationRow.mTranslateableViews.add(notificationChildrenContainer3);
                        break;
                }
            }
        });
        this.mTranslateableViews = new ArrayList();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            this.mTranslateableViews.add(getChildAt(i3));
        }
        this.mTranslateableViews.remove(this.mChildrenContainerStub);
        this.mTranslateableViews.remove(this.mGutsStub);
        setDefaultFocusHighlightEnabled(false);
    }

    public final void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        NotificationGuts.GutsContent gutsContent;
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        boolean z2 = true;
        boolean z3 = false;
        if (this.mLongPressListener == null) {
            z = false;
        } else if (areGutsExposed()) {
            NotificationGuts notificationGuts = this.mGuts;
            z = !((notificationGuts == null || (gutsContent = notificationGuts.mGutsContent) == null || !gutsContent.isLeavebehind()) ? false : true);
        } else {
            z = true;
        }
        if (z) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK);
        }
        accessibilityNodeInfo.setLongClickable(z);
        if (canViewBeDismissed() && !this.mIsSnoozed) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_DISMISS);
        }
        boolean shouldShowPublic = shouldShowPublic();
        if (shouldShowPublic) {
            z2 = shouldShowPublic;
        } else if (!this.mIsSummaryWithChildren) {
            z2 = this.mPrivateLayout.mIsContentExpandable;
            z3 = isExpanded(false);
        } else if (!this.mIsMinimized || isExpanded(false)) {
            z3 = isGroupExpanded();
        }
        if (z2 && !this.mIsSnoozed) {
            if (z3) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
            } else {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
            }
        }
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin == null || notificationMenuRowPlugin.getSnoozeMenuItem(getContext()) == null) {
            return;
        }
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(com.android.wm.shell.R.id.action_snooze, getContext().getResources().getString(com.android.wm.shell.R.string.notification_menu_snooze_action)));
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            this.mFalsingManager.isFalseTap(2);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!KeyEvent.isConfirmKey(i)) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (!KeyEvent.isConfirmKey(i)) {
            return false;
        }
        doLongClickCallback(getWidth() / 2, getHeight() / 2);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (!KeyEvent.isConfirmKey(i)) {
            return super.onKeyUp(i, keyEvent);
        }
        if (keyEvent.isCanceled()) {
            return true;
        }
        performClick();
        return true;
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        NotificationViewWrapper visibleWrapper;
        Trace.beginSection(appendTraceStyleTag("ExpNotRow#onLayout"));
        int intrinsicHeight = getIntrinsicHeight();
        super.onLayout(z, i, i2, i3, i4);
        if (intrinsicHeight != getIntrinsicHeight() && (intrinsicHeight != 0 || this.mActualHeight > 0)) {
            notifyHeightChanged(true);
        }
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null && notificationMenuRowPlugin.getMenuView() != null) {
            this.mMenuRow.onParentHeightUpdate();
        }
        if (!this.mIsSummaryWithChildren || shouldShowPublic()) {
            NotificationContentView showingLayout = getShowingLayout();
            visibleWrapper = showingLayout.getVisibleWrapper(showingLayout.mVisibleType);
        } else {
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            visibleWrapper = notificationChildrenContainer.showingAsLowPriority() ? notificationChildrenContainer.mMinimizedGroupHeaderWrapper : notificationChildrenContainer.mGroupHeaderWrapper;
        }
        View icon = visibleWrapper == null ? null : visibleWrapper.getIcon();
        if (icon != null) {
            getRelativeTopPadding(icon);
            icon.getHeight();
        }
        NotificationMenuRow notificationMenuRow = this.mLayoutListener;
        if (notificationMenuRow != null) {
            notificationMenuRow.mIconsPlaced = false;
            notificationMenuRow.setMenuLocation();
            notificationMenuRow.mParent.mLayoutListener = null;
        }
        Trace.endSection();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Trace.beginSection(appendTraceStyleTag("ExpNotRow#onMeasure"));
        super.onMeasure(i, i2);
        Trace.endSection();
    }

    /* JADX WARN: Type inference failed for: r3v10, types: [com.android.systemui.statusbar.policy.RemoteInputView, com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final void onNotificationUpdated() {
        PendingIntent pendingIntent;
        boolean z;
        ?? r3;
        if (this.mIsSummaryWithChildren) {
            Trace.beginSection("ExpNotRow#onNotifUpdated (summary)");
        } else {
            Trace.beginSection("ExpNotRow#onNotifUpdated (leaf)");
        }
        for (NotificationContentView notificationContentView : this.mLayouts) {
            NotificationEntry notificationEntry = this.mEntry;
            notificationContentView.mNotificationEntry = notificationEntry;
            notificationContentView.mBeforeN = notificationEntry.targetSdk < 24;
            NotificationContentView.updateAllSingleLineViews();
            ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
            if (notificationContentView.mContractedChild != null) {
                notificationContentView.mContractedWrapper.onContentUpdated(expandableNotificationRow);
            }
            if (notificationContentView.mExpandedChild != null) {
                notificationContentView.mExpandedWrapper.onContentUpdated(expandableNotificationRow);
            }
            if (notificationContentView.mHeadsUpChild != null) {
                notificationContentView.mHeadsUpWrapper.onContentUpdated(expandableNotificationRow);
            }
            if (notificationContentView.mRemoteInputController != null) {
                boolean z2 = notificationContentView.mNotificationEntry.mSbn.getNotification().findRemoteInputActionPair(true) != null;
                View view = notificationContentView.mExpandedChild;
                if (view != null) {
                    Object obj = null;
                    NotificationContentView.RemoteInputViewData applyRemoteInput = notificationContentView.applyRemoteInput(view, notificationContentView.mNotificationEntry, z2, notificationContentView.mPreviousExpandedRemoteInputIntent, notificationContentView.mCachedExpandedRemoteInput, notificationContentView.mCachedExpandedRemoteInputViewController, notificationContentView.mExpandedWrapper);
                    notificationContentView.mExpandedRemoteInput = applyRemoteInput.mView;
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl = applyRemoteInput.mController;
                    notificationContentView.mExpandedRemoteInputController = remoteInputViewControllerImpl;
                    r3 = obj;
                    if (remoteInputViewControllerImpl != null) {
                        remoteInputViewControllerImpl.bind();
                        r3 = obj;
                    }
                } else {
                    r3 = 0;
                    notificationContentView.mExpandedRemoteInput = null;
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl2 = notificationContentView.mExpandedRemoteInputController;
                    if (remoteInputViewControllerImpl2 != null) {
                        remoteInputViewControllerImpl2.unbind();
                    }
                    notificationContentView.mExpandedRemoteInputController = null;
                }
                RemoteInputView remoteInputView = notificationContentView.mCachedExpandedRemoteInput;
                if (remoteInputView != null && remoteInputView != notificationContentView.mExpandedRemoteInput) {
                    remoteInputView.dispatchFinishTemporaryDetach();
                }
                notificationContentView.mCachedExpandedRemoteInput = r3;
                notificationContentView.mCachedExpandedRemoteInputViewController = r3;
                View view2 = notificationContentView.mHeadsUpChild;
                if (view2 != null) {
                    NotificationContentView.RemoteInputViewData applyRemoteInput2 = notificationContentView.applyRemoteInput(view2, notificationContentView.mNotificationEntry, z2, notificationContentView.mPreviousHeadsUpRemoteInputIntent, notificationContentView.mCachedHeadsUpRemoteInput, notificationContentView.mCachedHeadsUpRemoteInputViewController, notificationContentView.mHeadsUpWrapper);
                    notificationContentView.mHeadsUpRemoteInput = applyRemoteInput2.mView;
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl3 = applyRemoteInput2.mController;
                    notificationContentView.mHeadsUpRemoteInputController = remoteInputViewControllerImpl3;
                    if (remoteInputViewControllerImpl3 != null) {
                        remoteInputViewControllerImpl3.bind();
                    }
                } else {
                    notificationContentView.mHeadsUpRemoteInput = r3;
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl4 = notificationContentView.mHeadsUpRemoteInputController;
                    if (remoteInputViewControllerImpl4 != null) {
                        remoteInputViewControllerImpl4.unbind();
                    }
                    notificationContentView.mHeadsUpRemoteInputController = r3;
                }
                RemoteInputView remoteInputView2 = notificationContentView.mCachedHeadsUpRemoteInput;
                if (remoteInputView2 != null && remoteInputView2 != notificationContentView.mHeadsUpRemoteInput) {
                    remoteInputView2.dispatchFinishTemporaryDetach();
                }
                notificationContentView.mCachedHeadsUpRemoteInput = r3;
                notificationContentView.mCachedHeadsUpRemoteInputViewController = r3;
                pendingIntent = r3;
            } else {
                pendingIntent = null;
            }
            InflatedSmartReplyState inflatedSmartReplyState = notificationContentView.mCurrentSmartReplyState;
            if (inflatedSmartReplyState != null) {
                View view3 = notificationContentView.mContractedChild;
                if (view3 != null) {
                    NotificationContentView.applyExternalSmartReplyState(view3, inflatedSmartReplyState);
                }
                View view4 = notificationContentView.mExpandedChild;
                if (view4 != null) {
                    NotificationContentView.applyExternalSmartReplyState(view4, notificationContentView.mCurrentSmartReplyState);
                    SmartReplyView applySmartReplyView = NotificationContentView.applySmartReplyView(notificationContentView.mExpandedChild, notificationContentView.mCurrentSmartReplyState, notificationContentView.mNotificationEntry, notificationContentView.mExpandedInflatedSmartReplies);
                    notificationContentView.mExpandedSmartReplyView = applySmartReplyView;
                    if (applySmartReplyView != null) {
                        InflatedSmartReplyState inflatedSmartReplyState2 = notificationContentView.mCurrentSmartReplyState;
                        SmartReplyView.SmartReplies smartReplies = inflatedSmartReplyState2.smartReplies;
                        SmartReplyView.SmartActions smartActions = inflatedSmartReplyState2.smartActions;
                        if (smartReplies != null || smartActions != null) {
                            int size = smartReplies == null ? 0 : smartReplies.choices.size();
                            int size2 = smartActions == null ? 0 : smartActions.actions.size();
                            boolean z3 = smartReplies == null ? smartActions.fromAssistant : smartReplies.fromAssistant;
                            try {
                                if (smartReplies != null) {
                                    SmartReplyConstants smartReplyConstants = notificationContentView.mSmartReplyConstants;
                                    int editChoicesBeforeSending = smartReplies.remoteInput.getEditChoicesBeforeSending();
                                    smartReplyConstants.getClass();
                                    if (editChoicesBeforeSending != 1 ? editChoicesBeforeSending != 2 ? smartReplyConstants.mEditChoicesBeforeSending : true : false) {
                                        z = true;
                                        SmartReplyController smartReplyController = notificationContentView.mSmartReplyController;
                                        NotificationEntry notificationEntry2 = notificationContentView.mNotificationEntry;
                                        smartReplyController.getClass();
                                        smartReplyController.mBarService.onNotificationSmartSuggestionsAdded(notificationEntry2.mSbn.getKey(), size, size2, z3, z);
                                    }
                                }
                                smartReplyController.mBarService.onNotificationSmartSuggestionsAdded(notificationEntry2.mSbn.getKey(), size, size2, z3, z);
                            } catch (RemoteException unused) {
                            }
                            z = false;
                            SmartReplyController smartReplyController2 = notificationContentView.mSmartReplyController;
                            NotificationEntry notificationEntry22 = notificationContentView.mNotificationEntry;
                            smartReplyController2.getClass();
                        }
                    }
                }
                View view5 = notificationContentView.mHeadsUpChild;
                if (view5 != null) {
                    NotificationContentView.applyExternalSmartReplyState(view5, notificationContentView.mCurrentSmartReplyState);
                    if (notificationContentView.mSmartReplyConstants.mShowInHeadsUp) {
                        notificationContentView.mHeadsUpSmartReplyView = NotificationContentView.applySmartReplyView(notificationContentView.mHeadsUpChild, notificationContentView.mCurrentSmartReplyState, notificationContentView.mNotificationEntry, notificationContentView.mHeadsUpInflatedSmartReplies);
                    }
                }
            }
            notificationContentView.updateLegacy();
            notificationContentView.mForceSelectNextLayout = true;
            notificationContentView.mPreviousExpandedRemoteInputIntent = pendingIntent;
            notificationContentView.mPreviousHeadsUpRemoteInputIntent = pendingIntent;
            notificationContentView.applySystemActions(notificationContentView.mExpandedChild, notificationEntry);
            notificationContentView.applySystemActions(notificationContentView.mHeadsUpChild, notificationEntry);
        }
        this.mShowingPublicInitialized = false;
        this.mNotificationColor = ContrastColorUtil.resolveContrastColor(((FrameLayout) this).mContext, this.mEntry.mSbn.getNotification().color, this.mNormalColor, (getResources().getConfiguration().uiMode & 48) == 32);
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null) {
            notificationMenuRowPlugin.onNotificationUpdated(this.mEntry.mSbn);
            this.mMenuRow.setAppName(this.mAppName);
        }
        if (this.mIsSummaryWithChildren) {
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = notificationChildrenContainer.mGroupHeaderWrapper;
            if (notificationHeaderViewWrapper != null) {
                notificationHeaderViewWrapper.mExpandButton.setExpanded(notificationChildrenContainer.mChildrenExpanded);
            }
            this.mChildrenContainer.onNotificationUpdated();
        }
        if (this.mAnimationRunning) {
            setAnimationRunning(true);
        }
        if (this.mLastChronometerRunning) {
            setChronometerRunning(true);
        }
        ExpandableNotificationRow expandableNotificationRow2 = this.mNotificationParent;
        if (expandableNotificationRow2 != null && expandableNotificationRow2.mIsSummaryWithChildren) {
            expandableNotificationRow2.mChildrenContainer.updateChildrenAppearance();
        }
        onAttachedChildrenCountChanged();
        this.mPublicLayout.updateExpandButtonsDuringLayout(this.mShowPublicExpander, false);
        updateLimits();
        updateShelfIconColor();
        if (this.mUpdateSelfBackgroundOnUpdate) {
            this.mUpdateSelfBackgroundOnUpdate = false;
            updateColors$1();
            this.mBackgroundNormal.setCustomBackground$1();
            updateBackgroundTint();
            this.mColorUpdateLogger.getClass();
        }
        Trace.endSection();
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginConnected(Plugin plugin, Context context) {
        NotificationMenuRowPlugin notificationMenuRowPlugin = (NotificationMenuRowPlugin) plugin;
        NotificationMenuRowPlugin notificationMenuRowPlugin2 = this.mMenuRow;
        boolean z = (notificationMenuRowPlugin2 == null || notificationMenuRowPlugin2.getMenuView() == null) ? false : true;
        if (z) {
            removeView(this.mMenuRow.getMenuView());
        }
        if (notificationMenuRowPlugin == null) {
            return;
        }
        this.mMenuRow = notificationMenuRowPlugin;
        if (notificationMenuRowPlugin.shouldUseDefaultMenuItems()) {
            ArrayList arrayList = new ArrayList();
            Context context2 = ((FrameLayout) this).mContext;
            arrayList.add(new NotificationMenuRow.NotificationMenuItem(context2, context2.getResources().getString(com.android.wm.shell.R.string.notification_menu_gear_description), (NotificationConversationInfo) LayoutInflater.from(context2).inflate(com.android.wm.shell.R.layout.notification_conversation_info, (ViewGroup) null, false), com.android.wm.shell.R.drawable.ic_settings));
            Context context3 = ((FrameLayout) this).mContext;
            arrayList.add(new NotificationMenuRow.NotificationMenuItem(context3, context3.getResources().getString(com.android.wm.shell.R.string.notification_menu_gear_description), (PartialConversationInfo) LayoutInflater.from(context3).inflate(com.android.wm.shell.R.layout.partial_conversation_info, (ViewGroup) null, false), com.android.wm.shell.R.drawable.ic_settings));
            Context context4 = ((FrameLayout) this).mContext;
            arrayList.add(new NotificationMenuRow.NotificationMenuItem(context4, context4.getResources().getString(com.android.wm.shell.R.string.notification_menu_gear_description), (NotificationInfo) LayoutInflater.from(context4).inflate(com.android.wm.shell.R.layout.notification_info, (ViewGroup) null, false), com.android.wm.shell.R.drawable.ic_settings));
            Context context5 = ((FrameLayout) this).mContext;
            arrayList.add(new NotificationMenuRow.NotificationMenuItem(context5, context5.getResources().getString(com.android.wm.shell.R.string.notification_menu_snooze_description), (NotificationSnooze) LayoutInflater.from(context5).inflate(com.android.wm.shell.R.layout.notification_snooze, (ViewGroup) null, false), com.android.wm.shell.R.drawable.ic_snooze));
            this.mMenuRow.setMenuItems(arrayList);
        }
        if (z) {
            createMenu();
        }
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginDisconnected(Plugin plugin) {
        boolean z = this.mMenuRow.getMenuView() != null;
        this.mMenuRow = new NotificationMenuRow(((FrameLayout) this).mContext, this.mPeopleNotificationIdentifier);
        if (z) {
            createMenu();
        }
    }

    public final boolean onRequestSendAccessibilityEventInternal(View view, AccessibilityEvent accessibilityEvent) {
        if (!super.onRequestSendAccessibilityEventInternal(view, accessibilityEvent)) {
            return false;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        onInitializeAccessibilityEvent(obtain);
        dispatchPopulateAccessibilityEvent(obtain);
        accessibilityEvent.appendRecord(obtain);
        return true;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && isChildInGroup() && !isGroupExpanded()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void onUiModeChanged() {
        this.mUpdateSelfBackgroundOnUpdate = true;
        reInflateViews$1();
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            Iterator it = notificationChildrenContainer.mAttachedChildren.iterator();
            while (it.hasNext()) {
                ((ExpandableNotificationRow) it.next()).onUiModeChanged();
            }
        }
    }

    public final boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        NotificationMenuRowPlugin notificationMenuRowPlugin;
        if (super.performAccessibilityActionInternal(i, bundle)) {
            return true;
        }
        if (i == 32) {
            doLongClickCallback(getWidth() / 2, getHeight() / 2);
            return true;
        }
        if (i == 262144 || i == 524288) {
            this.mExpandClickListener.onClick(this);
            return true;
        }
        if (i == 1048576) {
            performDismiss(true);
            return true;
        }
        if (i != com.android.wm.shell.R.id.action_snooze || (notificationMenuRowPlugin = this.mMenuRow) == null) {
            return false;
        }
        NotificationMenuRowPlugin.MenuItem snoozeMenuItem = notificationMenuRowPlugin.getSnoozeMenuItem(getContext());
        if (snoozeMenuItem != null) {
            doLongClickCallback(getWidth() / 2, getHeight() / 2, snoozeMenuItem);
        }
        return true;
    }

    public final void performDismiss(boolean z) {
        OnUserInteractionCallbackImpl onUserInteractionCallbackImpl;
        List attachedChildren;
        int indexOf;
        this.mMetricsLogger.count("notification_dismissed", 1);
        this.mDismissed = true;
        this.mRefocusOnDismiss = z;
        this.mLongPressListener = null;
        this.mDragController = null;
        this.mGroupParentWhenDismissed = this.mNotificationParent;
        this.mChildAfterViewWhenDismissed = null;
        this.mEntry.mIcons.mStatusBarIcon.getClass();
        if (isChildInGroup() && (indexOf = (attachedChildren = this.mNotificationParent.getAttachedChildren()).indexOf(this)) != -1 && indexOf < attachedChildren.size() - 1) {
            this.mChildAfterViewWhenDismissed = (View) attachedChildren.get(indexOf + 1);
        }
        if (this.mDismissibilityProvider.nonDismissableEntryKeys.contains(this.mEntry.mKey) || (onUserInteractionCallbackImpl = this.mOnUserInteractionCallback) == null) {
            return;
        }
        onUserInteractionCallbackImpl.registerFutureDismissal(this.mEntry, 2).run();
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView
    public final long performRemoveAnimation(final long j, final float f, final boolean z, final Runnable runnable, final Runnable runnable2, final AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin == null || !notificationMenuRowPlugin.isMenuVisible()) {
            super.performRemoveAnimation(j, f, z, runnable, runnable2, animatorListenerAdapter, clipSide);
            return 0L;
        }
        Animator translateViewAnimator = getTranslateViewAnimator(0.0f, null);
        translateViewAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ExpandableNotificationRow.access$001(ExpandableNotificationRow.this, j, f, z, runnable2, animatorListenerAdapter);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                Runnable runnable3 = runnable;
                if (runnable3 != null) {
                    runnable3.run();
                }
            }
        });
        translateViewAnimator.start();
        return translateViewAnimator.getDuration();
    }

    public final void reInflateViews$1() {
        HybridNotificationView hybridNotificationView;
        Trace.beginSection("ExpandableNotificationRow#reInflateViews");
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            StatusBarNotification statusBarNotification = this.mEntry.mSbn;
            notificationChildrenContainer.initDimens$2();
            for (int i = 0; i < ((ArrayList) notificationChildrenContainer.mDividers).size(); i++) {
                View view = (View) ((ArrayList) notificationChildrenContainer.mDividers).get(i);
                int indexOfChild = notificationChildrenContainer.indexOfChild(view);
                notificationChildrenContainer.removeView(view);
                View inflateDivider = notificationChildrenContainer.inflateDivider();
                notificationChildrenContainer.addView(inflateDivider, indexOfChild);
                notificationChildrenContainer.mDividers.set(i, inflateDivider);
            }
            notificationChildrenContainer.removeView(notificationChildrenContainer.mOverflowNumber);
            notificationChildrenContainer.mOverflowNumber = null;
            notificationChildrenContainer.mGroupOverFlowState = null;
            notificationChildrenContainer.updateGroupOverflow();
        }
        NotificationGuts notificationGuts = this.mGuts;
        if (notificationGuts != null) {
            int indexOfChild2 = indexOfChild(notificationGuts);
            removeView(notificationGuts);
            NotificationGuts notificationGuts2 = (NotificationGuts) LayoutInflater.from(((FrameLayout) this).mContext).inflate(com.android.wm.shell.R.layout.notification_guts, (ViewGroup) this, false);
            this.mGuts = notificationGuts2;
            notificationGuts2.setVisibility(notificationGuts.mExposed ? 0 : 8);
            addView(this.mGuts, indexOfChild2);
        }
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        View menuView = notificationMenuRowPlugin == null ? null : notificationMenuRowPlugin.getMenuView();
        if (menuView != null) {
            int indexOfChild3 = indexOfChild(menuView);
            removeView(menuView);
            this.mMenuRow.createMenu(this, this.mEntry.mSbn);
            this.mMenuRow.setAppName(this.mAppName);
            addView(this.mMenuRow.getMenuView(), indexOfChild3);
        }
        for (NotificationContentView notificationContentView : this.mLayouts) {
            notificationContentView.mMinContractedHeight = notificationContentView.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.min_notification_layout_height);
            notificationContentView.mMinSingleLineHeight = notificationContentView.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.conversation_single_line_face_pile_size);
            if (notificationContentView.mIsChildInGroup && (hybridNotificationView = notificationContentView.mSingleLineView) != null) {
                notificationContentView.removeView(hybridNotificationView);
                notificationContentView.mSingleLineView = null;
                NotificationContentView.updateAllSingleLineViews();
            }
        }
        this.mEntry.mSbn.clearPackageContext();
        RowContentBindParams rowContentBindParams = (RowContentBindParams) this.mRowContentBindStage.getStageParams(this.mEntry);
        rowContentBindParams.mViewsNeedReinflation = true;
        rowContentBindParams.mDirtyContentViews = rowContentBindParams.mContentViews | rowContentBindParams.mDirtyContentViews;
        this.mRowContentBindStage.requestRebind(this.mEntry, null);
        Trace.endSection();
    }

    public final void removeChildNotification(ExpandableNotificationRow expandableNotificationRow) {
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.removeNotification(expandableNotificationRow);
            expandableNotificationRow.mKeepInParentForDismissAnimation = false;
        }
        onAttachedChildrenCountChanged();
        expandableNotificationRow.setIsChildInGroup(null, false);
    }

    public final void removeChildrenWithKeepInParent() {
        if (this.mChildrenContainer == null) {
            return;
        }
        boolean z = false;
        for (ExpandableNotificationRow expandableNotificationRow : new ArrayList(this.mChildrenContainer.mAttachedChildren)) {
            if (expandableNotificationRow.mKeepInParentForDismissAnimation) {
                this.mChildrenContainer.removeNotification(expandableNotificationRow);
                expandableNotificationRow.setIsChildInGroup(null, false);
                expandableNotificationRow.mKeepInParentForDismissAnimation = false;
                ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
                if (anonymousClass2 != null) {
                    NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                    NotificationEntry notificationEntry2 = this.mEntry;
                    NotificationRowLogger notificationRowLogger = ExpandableNotificationRowController.this.mLogBufferLogger;
                    notificationRowLogger.getClass();
                    LogLevel logLevel = LogLevel.DEBUG;
                    NotificationRowLogger$logKeepInParentChildDetached$2 notificationRowLogger$logKeepInParentChildDetached$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logKeepInParentChildDetached$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Detach child ", logMessage.getStr1(), " kept in parent ", logMessage.getStr2());
                        }
                    };
                    LogBuffer logBuffer = notificationRowLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("NotifRow", logLevel, notificationRowLogger$logKeepInParentChildDetached$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                    logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
                    logBuffer.commit(obtain);
                }
                z = true;
            }
        }
        if (z) {
            onAttachedChildrenCountChanged();
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void removeFromTransientContainer() {
        ViewGroup viewGroup = this.mTransientContainer;
        ViewParent parent = getParent();
        if (viewGroup == null || viewGroup != parent) {
            super.removeFromTransientContainer();
            return;
        }
        ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
        if (anonymousClass2 != null) {
            boolean z = viewGroup instanceof NotificationChildrenContainer;
            ExpandableNotificationRowController expandableNotificationRowController = ExpandableNotificationRowController.this;
            if (z) {
                NotificationEntry notificationEntry = this.mEntry;
                NotificationEntry notificationEntry2 = ((NotificationChildrenContainer) viewGroup).mContainingNotification.mEntry;
                NotificationRowLogger notificationRowLogger = expandableNotificationRowController.mLogBufferLogger;
                notificationRowLogger.getClass();
                LogLevel logLevel = LogLevel.INFO;
                NotificationRowLogger$logRemoveTransientFromContainer$2 notificationRowLogger$logRemoveTransientFromContainer$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logRemoveTransientFromContainer$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("RemoveTransientRow from ChildrenContainer: childKey: ", logMessage.getStr1(), " -- containerKey: ", logMessage.getStr2());
                    }
                };
                LogBuffer logBuffer = notificationRowLogger.notificationRenderBuffer;
                LogMessage obtain = logBuffer.obtain("NotifRow", logLevel, notificationRowLogger$logRemoveTransientFromContainer$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
                logBuffer.commit(obtain);
            } else if (viewGroup instanceof NotificationStackScrollLayout) {
                NotificationEntry notificationEntry3 = this.mEntry;
                NotificationRowLogger notificationRowLogger2 = expandableNotificationRowController.mLogBufferLogger;
                notificationRowLogger2.getClass();
                LogLevel logLevel2 = LogLevel.INFO;
                NotificationRowLogger$logRemoveTransientFromNssl$2 notificationRowLogger$logRemoveTransientFromNssl$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logRemoveTransientFromNssl$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("RemoveTransientRow from Nssl: childKey: ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer2 = notificationRowLogger2.notificationRenderBuffer;
                LogMessage obtain2 = logBuffer2.obtain("NotifRow", logLevel2, notificationRowLogger$logRemoveTransientFromNssl$2, null);
                ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry3);
                logBuffer2.commit(obtain2);
            } else {
                NotificationEntry notificationEntry4 = this.mEntry;
                NotificationRowLogger notificationRowLogger3 = expandableNotificationRowController.mLogBufferLogger;
                notificationRowLogger3.getClass();
                LogLevel logLevel3 = LogLevel.WARNING;
                NotificationRowLogger$logRemoveTransientFromViewGroup$2 notificationRowLogger$logRemoveTransientFromViewGroup$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logRemoveTransientFromViewGroup$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("RemoveTransientRow from other ViewGroup: childKey: ", logMessage.getStr1(), " -- ViewGroup: ", logMessage.getStr2());
                    }
                };
                LogBuffer logBuffer3 = notificationRowLogger3.notificationRenderBuffer;
                LogMessage obtain3 = logBuffer3.obtain("NotifRow", logLevel3, notificationRowLogger$logRemoveTransientFromViewGroup$2, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain3;
                logMessageImpl2.str1 = NotificationUtilsKt.getLogKey(notificationEntry4);
                logMessageImpl2.str2 = viewGroup.toString();
                logBuffer3.commit(obtain3);
            }
        }
        super.removeFromTransientContainer();
    }

    public final void removeTransientView(View view) {
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
            if (anonymousClass2 != null) {
                NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                NotificationEntry notificationEntry2 = this.mEntry;
                NotificationRowLogger notificationRowLogger = ExpandableNotificationRowController.this.mLogBufferLogger;
                notificationRowLogger.getClass();
                LogLevel logLevel = LogLevel.ERROR;
                NotificationRowLogger$logRemoveTransientRow$2 notificationRowLogger$logRemoveTransientRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logRemoveTransientRow$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("removeTransientRow from row: childKey: ", logMessage.getStr1(), " -- containerKey: ", logMessage.getStr2());
                    }
                };
                LogBuffer logBuffer = notificationRowLogger.notificationRenderBuffer;
                LogMessage obtain = logBuffer.obtain("NotifRow", logLevel, notificationRowLogger$logRemoveTransientRow$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
                logBuffer.commit(obtain);
            }
        }
        super.removeTransientView(view);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final void resetAllContentAlphas() {
        ExpandableNotificationRowController.AnonymousClass2 anonymousClass2 = this.mLogger;
        NotificationEntry notificationEntry = this.mEntry;
        NotificationRowLogger notificationRowLogger = ExpandableNotificationRowController.this.mLogBufferLogger;
        notificationRowLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        NotificationRowLogger$logResetAllContentAlphas$2 notificationRowLogger$logResetAllContentAlphas$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowLogger$logResetAllContentAlphas$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("resetAllContentAlphas: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = notificationRowLogger.notificationRenderBuffer;
        LogMessage obtain = logBuffer.obtain("NotifRow", logLevel, notificationRowLogger$logResetAllContentAlphas$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        this.mPrivateLayout.setAlpha(1.0f);
        this.mPrivateLayout.setLayerType(0, null);
        this.mPublicLayout.setAlpha(1.0f);
        this.mPublicLayout.setLayerType(0, null);
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.setAlpha(1.0f);
            this.mChildrenContainer.setLayerType(0, null);
        }
    }

    public final void resetTranslation() {
        Animator animator = this.mTranslateAnim;
        if (animator != null) {
            animator.cancel();
        }
        if (this.mDismissUsingRowTranslationX) {
            setTranslationX(0.0f);
        } else if (this.mTranslateableViews != null) {
            for (int i = 0; i < this.mTranslateableViews.size(); i++) {
                ((View) this.mTranslateableViews.get(i)).setTranslationX(0.0f);
            }
            invalidateOutline();
            this.mEntry.mIcons.mShelfIcon.setScrollX(0);
        }
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null) {
            notificationMenuRowPlugin.resetMenu();
        }
    }

    public final void setAboveShelf(boolean z) {
        boolean isAboveShelf = isAboveShelf();
        this.mAboveShelf = z;
        if (isAboveShelf() != isAboveShelf) {
            this.mAboveShelfChangedListener.onAboveShelfStateChanged(!isAboveShelf);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setActualHeight(int i, boolean z) {
        super.setActualHeight(i, z);
        NotificationGuts notificationGuts = this.mGuts;
        if (notificationGuts != null && notificationGuts.mExposed) {
            notificationGuts.mActualHeight = i;
            notificationGuts.invalidate();
            return;
        }
        for (NotificationContentView notificationContentView : this.mLayouts) {
            notificationContentView.mUnrestrictedContentHeight = Math.max(i, notificationContentView.getMinHeight(false));
            notificationContentView.mContentHeight = Math.min(notificationContentView.mUnrestrictedContentHeight, (notificationContentView.mContainingNotification.getIntrinsicHeight() - notificationContentView.getExtraRemoteInputHeight(notificationContentView.mExpandedRemoteInput)) - notificationContentView.getExtraRemoteInputHeight(notificationContentView.mHeadsUpRemoteInput));
            notificationContentView.selectLayout(notificationContentView.mAnimate, false);
            if (notificationContentView.mContractedChild != null) {
                int minContentHeightHint = notificationContentView.getMinContentHeightHint();
                NotificationViewWrapper visibleWrapper = notificationContentView.getVisibleWrapper(notificationContentView.mVisibleType);
                if (visibleWrapper != null) {
                    visibleWrapper.setContentHeight(notificationContentView.mUnrestrictedContentHeight, minContentHeightHint);
                }
                NotificationViewWrapper visibleWrapper2 = notificationContentView.getVisibleWrapper(notificationContentView.mTransformationStartVisibleType);
                if (visibleWrapper2 != null) {
                    visibleWrapper2.setContentHeight(notificationContentView.mUnrestrictedContentHeight, minContentHeightHint);
                }
                notificationContentView.updateClipping();
                notificationContentView.invalidateOutline();
            }
        }
        if (this.mIsSummaryWithChildren) {
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            if (notificationChildrenContainer.mUserLocked) {
                notificationChildrenContainer.mActualHeight = i;
                float groupExpandFraction = notificationChildrenContainer.getGroupExpandFraction();
                boolean showingAsLowPriority = notificationChildrenContainer.showingAsLowPriority();
                if (notificationChildrenContainer.mUserLocked && notificationChildrenContainer.showingAsLowPriority()) {
                    float groupExpandFraction2 = notificationChildrenContainer.getGroupExpandFraction();
                    notificationChildrenContainer.mGroupHeaderWrapper.transformFrom(groupExpandFraction2, notificationChildrenContainer.mMinimizedGroupHeaderWrapper);
                    notificationChildrenContainer.mGroupHeader.setVisibility(0);
                    notificationChildrenContainer.mMinimizedGroupHeaderWrapper.transformTo(groupExpandFraction2, notificationChildrenContainer.mGroupHeaderWrapper);
                }
                int maxAllowedVisibleChildren = notificationChildrenContainer.getMaxAllowedVisibleChildren(true);
                int size = ((ArrayList) notificationChildrenContainer.mAttachedChildren).size();
                for (int i2 = 0; i2 < size; i2++) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) notificationChildrenContainer.mAttachedChildren).get(i2);
                    float minHeight = showingAsLowPriority ? expandableNotificationRow.getShowingLayout().getMinHeight(false) : expandableNotificationRow.isExpanded(true) ? expandableNotificationRow.getMaxExpandHeight() : expandableNotificationRow.getShowingLayout().getMinHeight(true);
                    if (i2 < maxAllowedVisibleChildren) {
                        expandableNotificationRow.setActualHeight((int) NotificationUtils.interpolate(expandableNotificationRow.getShowingLayout().getMinHeight(false), minHeight, groupExpandFraction), false);
                    } else {
                        expandableNotificationRow.setActualHeight((int) minHeight, false);
                    }
                }
            }
        }
        NotificationGuts notificationGuts2 = this.mGuts;
        if (notificationGuts2 != null) {
            notificationGuts2.mActualHeight = i;
            notificationGuts2.invalidate();
        }
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null && notificationMenuRowPlugin.getMenuView() != null) {
            this.mMenuRow.onParentHeightUpdate();
        }
        if (this.mOnIntrinsicHeightReachedRunnable == null || this.mActualHeight != getIntrinsicHeight()) {
            return;
        }
        this.mOnIntrinsicHeightReachedRunnable.run();
        this.mOnIntrinsicHeightReachedRunnable = null;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setActualHeightAnimating(boolean z) {
        NotificationContentView notificationContentView = this.mPrivateLayout;
        if (notificationContentView == null || z) {
            return;
        }
        notificationContentView.mContentHeightAtAnimationStart = -1;
    }

    public final void setAnimationRunning(boolean z) {
        int i = 0;
        for (NotificationContentView notificationContentView : this.mLayouts) {
            if (notificationContentView != null) {
                if (z != notificationContentView.mContentAnimating) {
                    NotificationViewWrapper notificationViewWrapper = notificationContentView.mContractedWrapper;
                    if (notificationViewWrapper != null) {
                        notificationViewWrapper.setAnimationsRunning(z);
                    }
                    NotificationViewWrapper notificationViewWrapper2 = notificationContentView.mExpandedWrapper;
                    if (notificationViewWrapper2 != null) {
                        notificationViewWrapper2.setAnimationsRunning(z);
                    }
                    NotificationViewWrapper notificationViewWrapper3 = notificationContentView.mHeadsUpWrapper;
                    if (notificationViewWrapper3 != null) {
                        notificationViewWrapper3.setAnimationsRunning(z);
                    }
                    notificationContentView.mContentAnimating = z;
                }
                View view = notificationContentView.mContractedChild;
                View view2 = notificationContentView.mExpandedChild;
                View view3 = notificationContentView.mHeadsUpChild;
                setIconAnimationRunningForChild(view, z);
                setIconAnimationRunningForChild(view2, z);
                setIconAnimationRunningForChild(view3, z);
            }
        }
        if (this.mIsSummaryWithChildren) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.mChildrenContainer.mGroupHeaderWrapper;
            if (notificationHeaderViewWrapper != null) {
                setIconAnimationRunningForChild(notificationHeaderViewWrapper.mIcon, z);
            }
            NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = this.mChildrenContainer.mMinimizedGroupHeaderWrapper;
            if (notificationHeaderViewWrapper2 != null) {
                setIconAnimationRunningForChild(notificationHeaderViewWrapper2.mIcon, z);
            }
            List list = this.mChildrenContainer.mAttachedChildren;
            while (true) {
                ArrayList arrayList = (ArrayList) list;
                if (i >= arrayList.size()) {
                    break;
                }
                ((ExpandableNotificationRow) arrayList.get(i)).setAnimationRunning(z);
                i++;
            }
        }
        this.mAnimationRunning = z;
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final void setBackgroundTintColor(int i) {
        super.setBackgroundTintColor(i);
        NotificationContentView showingLayout = getShowingLayout();
        if (showingLayout != null) {
            boolean isColorized = showingLayout.mNotificationEntry.mSbn.getNotification().isColorized();
            SmartReplyView smartReplyView = showingLayout.mExpandedSmartReplyView;
            if (smartReplyView != null) {
                smartReplyView.setBackgroundTintColor(i, isColorized);
            }
            SmartReplyView smartReplyView2 = showingLayout.mHeadsUpSmartReplyView;
            if (smartReplyView2 != null) {
                smartReplyView2.setBackgroundTintColor(i, isColorized);
            }
            RemoteInputView remoteInputView = showingLayout.mExpandedRemoteInput;
            if (remoteInputView != null) {
                remoteInputView.setBackgroundTintColor(i, isColorized);
            }
            RemoteInputView remoteInputView2 = showingLayout.mHeadsUpRemoteInput;
            if (remoteInputView2 != null) {
                remoteInputView2.setBackgroundTintColor(i, isColorized);
            }
        }
    }

    public void setChildrenContainer(NotificationChildrenContainer notificationChildrenContainer) {
        this.mChildrenContainer = notificationChildrenContainer;
    }

    public final void setChildrenExpanded(boolean z) {
        this.mChildrenExpanded = z;
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.mChildrenExpanded = z;
            notificationChildrenContainer.updateExpansionStates();
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = notificationChildrenContainer.mGroupHeaderWrapper;
            if (notificationHeaderViewWrapper != null) {
                notificationHeaderViewWrapper.mExpandButton.setExpanded(z);
            }
            int size = ((ArrayList) notificationChildrenContainer.mAttachedChildren).size();
            for (int i = 0; i < size; i++) {
                ((ExpandableNotificationRow) ((ArrayList) notificationChildrenContainer.mAttachedChildren).get(i)).setChildrenExpanded(z);
            }
            NotificationHeaderView notificationHeaderView = notificationChildrenContainer.mGroupHeader;
            if (notificationHeaderView != null) {
                notificationHeaderView.setAcceptAllTouches(notificationChildrenContainer.mChildrenExpanded || notificationChildrenContainer.mUserLocked);
            }
        }
        updateBackgroundForGroupState();
        updateClickAndFocus();
    }

    public final void setChronometerRunning(boolean z, NotificationContentView notificationContentView) {
        if (notificationContentView != null) {
            boolean z2 = z || this.mIsPinned;
            View view = notificationContentView.mContractedChild;
            View view2 = notificationContentView.mExpandedChild;
            View view3 = notificationContentView.mHeadsUpChild;
            setChronometerRunningForChild(view, z2);
            setChronometerRunningForChild(view2, z2);
            setChronometerRunningForChild(view3, z2);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setClipBottomAmount(int i) {
        if (this.mExpandAnimationRunning) {
            return;
        }
        if (i != this.mClipBottomAmount) {
            super.setClipBottomAmount(i);
            for (NotificationContentView notificationContentView : this.mLayouts) {
                notificationContentView.mClipBottomAmount = i;
                notificationContentView.updateClipping();
            }
            NotificationGuts notificationGuts = this.mGuts;
            if (notificationGuts != null) {
                notificationGuts.mClipBottomAmount = i;
                notificationGuts.invalidate();
            }
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer == null || this.mChildIsExpanding) {
            return;
        }
        notificationChildrenContainer.mClipBottomAmount = i;
        notificationChildrenContainer.updateChildrenClipping();
    }

    public final void setClipToActualHeight(boolean z) {
        boolean z2 = true;
        this.mClipToActualHeight = z || this.mUserLocked;
        updateClipping$1();
        NotificationContentView showingLayout = getShowingLayout();
        if (!z && !this.mUserLocked) {
            z2 = false;
        }
        showingLayout.mClipToActualHeight = z2;
        showingLayout.updateClipping();
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setClipTopAmount(int i) {
        super.setClipTopAmount(i);
        for (NotificationContentView notificationContentView : this.mLayouts) {
            notificationContentView.mClipTopAmount = i;
            notificationContentView.updateClipping();
        }
        NotificationGuts notificationGuts = this.mGuts;
        if (notificationGuts != null) {
            notificationGuts.mClipTopAmount = i;
            notificationGuts.invalidate();
        }
    }

    public final void setContentAlpha(float f) {
        for (NotificationContentView notificationContentView : this.mLayouts) {
            notificationContentView.setAlpha(f);
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            if (notificationChildrenContainer.mGroupHeader != null) {
                for (int i = 0; i < notificationChildrenContainer.mGroupHeader.getChildCount(); i++) {
                    notificationChildrenContainer.mGroupHeader.getChildAt(i).setAlpha(f);
                }
            }
            Iterator it = notificationChildrenContainer.mAttachedChildren.iterator();
            while (it.hasNext()) {
                ((ExpandableNotificationRow) it.next()).setContentAlpha(f);
            }
        }
    }

    public final void setDismissUsingRowTranslationX(boolean z) {
        if (z == this.mDismissUsingRowTranslationX) {
            return;
        }
        float translation = getTranslation();
        if (translation != 0.0f) {
            setTranslation(0.0f);
        }
        this.mDismissUsingRowTranslationX = z;
        if (translation != 0.0f) {
            setTranslation(translation);
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer == null) {
            return;
        }
        List list = notificationChildrenContainer.mAttachedChildren;
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i >= arrayList.size()) {
                return;
            }
            ((ExpandableNotificationRow) arrayList.get(i)).setDismissUsingRowTranslationX(z);
            i++;
        }
    }

    public void setEntry(NotificationEntry notificationEntry) {
        this.mEntry = notificationEntry;
    }

    public final void setExpandAnimationRunning(boolean z) {
        if (z) {
            setAboveShelf(true);
            this.mExpandAnimationRunning = true;
            this.mViewState.cancelAnimations(this);
            this.mNotificationLaunchHeight = Math.max(1, getContext().getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.z_distance_between_notifications)) * 4;
        } else {
            this.mExpandAnimationRunning = false;
            setAboveShelf(isAboveShelf());
            setVisibility(0);
            NotificationGuts notificationGuts = this.mGuts;
            if (notificationGuts != null) {
                notificationGuts.setAlpha(1.0f);
            }
            resetAllContentAlphas();
            this.mExtraWidthForClipping = 0.0f;
            invalidate();
            ExpandableNotificationRow expandableNotificationRow = this.mNotificationParent;
            if (expandableNotificationRow != null) {
                expandableNotificationRow.mExtraWidthForClipping = 0.0f;
                expandableNotificationRow.invalidate();
                ExpandableNotificationRow expandableNotificationRow2 = this.mNotificationParent;
                expandableNotificationRow2.mMinimumHeightForClipping = 0;
                expandableNotificationRow2.updateClipping$1();
                expandableNotificationRow2.invalidate();
            }
        }
        ExpandableNotificationRow expandableNotificationRow3 = this.mNotificationParent;
        if (expandableNotificationRow3 != null) {
            expandableNotificationRow3.mChildIsExpanding = this.mExpandAnimationRunning;
            expandableNotificationRow3.updateClipping$1();
            expandableNotificationRow3.invalidate();
        }
        updateChildrenVisibility();
        updateClipping$1();
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        notificationBackgroundView.mExpandAnimationRunning = z;
        Drawable drawable = notificationBackgroundView.mBackground;
        if (drawable instanceof LayerDrawable) {
            ((GradientDrawable) ((LayerDrawable) drawable).getDrawable(0)).setAntiAlias(!z);
        }
        boolean z2 = notificationBackgroundView.mExpandAnimationRunning;
        if (!z2) {
            int i = notificationBackgroundView.mDrawableAlpha;
            notificationBackgroundView.mDrawableAlpha = i;
            if (!z2) {
                notificationBackgroundView.mBackground.setAlpha(i);
            }
        }
        notificationBackgroundView.invalidate();
    }

    public final void setGroupHeader(NotificationHeaderView notificationHeaderView) {
        if (this.mChildrenContainer == null) {
            this.mChildrenContainerStub.inflate();
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        notificationChildrenContainer.mHeaderClickListener = this.mExpandClickListener;
        NotificationHeaderView notificationHeaderView2 = notificationChildrenContainer.mGroupHeader;
        if (notificationHeaderView2 != null) {
            notificationChildrenContainer.removeView(notificationHeaderView2);
            notificationChildrenContainer.mGroupHeader = null;
            notificationChildrenContainer.mGroupHeaderWrapper = null;
        }
        if (notificationHeaderView != null) {
            notificationChildrenContainer.mGroupHeader = notificationHeaderView;
            notificationHeaderView.findViewById(R.id.feedbackAllMask).setVisibility(0);
            notificationChildrenContainer.mGroupHeader.setOnClickListener(notificationChildrenContainer.mHeaderClickListener);
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = (NotificationHeaderViewWrapper) NotificationViewWrapper.wrap(notificationChildrenContainer.getContext(), notificationChildrenContainer.mGroupHeader, notificationChildrenContainer.mContainingNotification);
            notificationChildrenContainer.mGroupHeaderWrapper = notificationHeaderViewWrapper;
            notificationHeaderViewWrapper.mRoundnessChangedListener = new NotificationChildrenContainer$$ExternalSyntheticLambda0(notificationChildrenContainer);
            notificationChildrenContainer.addView((View) notificationChildrenContainer.mGroupHeader, 0);
            notificationChildrenContainer.invalidate();
            NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = notificationChildrenContainer.mGroupHeaderWrapper;
            notificationHeaderViewWrapper2.mExpandButton.setExpanded(notificationChildrenContainer.mChildrenExpanded);
            notificationChildrenContainer.mGroupHeaderWrapper.onContentUpdated(notificationChildrenContainer.mContainingNotification);
            notificationChildrenContainer.updateHeaderVisibility(false);
            notificationChildrenContainer.updateChildrenAppearance();
            Trace.endSection();
        }
        updateBackgroundForGroupState();
    }

    public final void setHeadsUpAnimatingAway(boolean z) {
        NotificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0 notificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0;
        boolean isAboveShelf = isAboveShelf();
        boolean z2 = z != this.mHeadsupDisappearRunning;
        this.mHeadsupDisappearRunning = z;
        NotificationContentView notificationContentView = this.mPrivateLayout;
        notificationContentView.mHeadsUpAnimatingAway = z;
        notificationContentView.selectLayout(false, true);
        if (z2 && (notificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0 = this.mHeadsUpAnimatingAwayListener) != null) {
            notificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0.accept(Boolean.valueOf(z));
        }
        if (isAboveShelf() != isAboveShelf) {
            this.mAboveShelfChangedListener.onAboveShelfStateChanged(!isAboveShelf);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setHideSensitive(boolean z, boolean z2, long j, long j2) {
        if (getVisibility() == 8) {
            return;
        }
        boolean z3 = this.mShowingPublic;
        boolean z4 = this.mSensitive && z;
        this.mShowingPublic = z4;
        if (this.mShowingPublicInitialized && z4 == z3) {
            return;
        }
        if (z2) {
            View[] viewArr = this.mIsSummaryWithChildren ? new View[]{this.mChildrenContainer} : new View[]{this.mPrivateLayout};
            View[] viewArr2 = {this.mPublicLayout};
            View[] viewArr3 = z4 ? viewArr : viewArr2;
            if (z4) {
                viewArr = viewArr2;
            }
            long j3 = (j2 / 10) / 2;
            long j4 = (j2 / 3) + j3;
            long j5 = (j2 - j4) + j3;
            for (final View view : viewArr3) {
                view.setVisibility(0);
                view.animate().cancel();
                view.animate().alpha(0.0f).setStartDelay(j).setDuration(j4).withEndAction(new Runnable() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        ExpandableNotificationRow expandableNotificationRow = this;
                        View view2 = view;
                        SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
                        expandableNotificationRow.getClass();
                        view2.setVisibility(4);
                        expandableNotificationRow.resetAllContentAlphas();
                    }
                });
            }
            for (View view2 : viewArr) {
                view2.setVisibility(0);
                view2.setAlpha(0.0f);
                view2.animate().cancel();
                view2.animate().alpha(1.0f).setStartDelay((j + j2) - j5).setDuration(j5);
            }
        } else {
            this.mPublicLayout.animate().cancel();
            this.mPrivateLayout.animate().cancel();
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            if (notificationChildrenContainer != null) {
                notificationChildrenContainer.animate().cancel();
            }
            resetAllContentAlphas();
            this.mPublicLayout.setVisibility(this.mShowingPublic ? 0 : 4);
            updateChildrenVisibility();
        }
        getShowingLayout().updateBackgroundColor(z2);
        this.mPrivateLayout.updateExpandButtonsDuringLayout(isExpandable$1(), false);
        updateShelfIconColor();
        this.mShowingPublicInitialized = true;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setHideSensitiveForIntrinsicHeight(boolean z) {
        this.mHideSensitiveForIntrinsicHeight = z;
        if (!this.mIsSummaryWithChildren) {
            return;
        }
        List list = this.mChildrenContainer.mAttachedChildren;
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i >= arrayList.size()) {
                return;
            }
            ((ExpandableNotificationRow) arrayList.get(i)).setHideSensitiveForIntrinsicHeight(z);
            i++;
        }
    }

    public final void setIsChildInGroup(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        ExpandableNotificationRow expandableNotificationRow2;
        if (this.mExpandAnimationRunning && !z && (expandableNotificationRow2 = this.mNotificationParent) != null) {
            expandableNotificationRow2.mChildIsExpanding = false;
            expandableNotificationRow2.updateClipping$1();
            expandableNotificationRow2.invalidate();
            ExpandableNotificationRow expandableNotificationRow3 = this.mNotificationParent;
            expandableNotificationRow3.mExpandingClipPath = null;
            expandableNotificationRow3.invalidate();
            ExpandableNotificationRow expandableNotificationRow4 = this.mNotificationParent;
            expandableNotificationRow4.mExtraWidthForClipping = 0.0f;
            expandableNotificationRow4.invalidate();
            ExpandableNotificationRow expandableNotificationRow5 = this.mNotificationParent;
            expandableNotificationRow5.mMinimumHeightForClipping = 0;
            expandableNotificationRow5.updateClipping$1();
            expandableNotificationRow5.invalidate();
        }
        if (!z) {
            expandableNotificationRow = null;
        }
        this.mNotificationParent = expandableNotificationRow;
        NotificationContentView notificationContentView = this.mPrivateLayout;
        notificationContentView.mIsChildInGroup = z;
        if (notificationContentView.mContractedChild != null) {
            notificationContentView.mContractedWrapper.setIsChildInGroup(z);
        }
        if (notificationContentView.mExpandedChild != null) {
            notificationContentView.mExpandedWrapper.setIsChildInGroup(notificationContentView.mIsChildInGroup);
        }
        if (notificationContentView.mHeadsUpChild != null) {
            notificationContentView.mHeadsUpWrapper.setIsChildInGroup(notificationContentView.mIsChildInGroup);
        }
        NotificationContentView.updateAllSingleLineViews();
        updateBackgroundForGroupState();
        updateClickAndFocus();
        if (this.mNotificationParent != null) {
            this.mOverrideTint = 0;
            this.mOverrideAmount = 0.0f;
            setBackgroundTintColor(calculateBgColor(true));
            this.mNotificationParent.updateBackgroundForGroupState();
        }
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        boolean isChildInGroup = true ^ isChildInGroup();
        if (isChildInGroup != notificationBackgroundView.mBottomAmountClips) {
            notificationBackgroundView.mBottomAmountClips = isChildInGroup;
            notificationBackgroundView.invalidate();
        }
        if (isChildInGroup()) {
            requestRoundnessReset(BASE_VALUE);
        } else {
            float f = this.mSmallRoundness;
            requestRoundness(f, f, BASE_VALUE);
        }
    }

    public final void setIsMinimized(boolean z) {
        this.mIsMinimized = z;
        this.mPrivateLayout.getClass();
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.mIsMinimized = z;
            if (notificationChildrenContainer.mContainingNotification != null) {
                notificationChildrenContainer.updateHeaderVisibility(false);
            }
            boolean z2 = notificationChildrenContainer.mUserLocked;
            if (z2) {
                notificationChildrenContainer.setUserLocked(z2);
            }
        }
    }

    public final void setMinimizedGroupHeader(NotificationHeaderView notificationHeaderView) {
        if (this.mChildrenContainer == null) {
            this.mChildrenContainerStub.inflate();
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        AnonymousClass1 anonymousClass1 = this.mExpandClickListener;
        NotificationHeaderView notificationHeaderView2 = notificationChildrenContainer.mMinimizedGroupHeader;
        if (notificationHeaderView2 != null) {
            notificationChildrenContainer.removeView(notificationHeaderView2);
            notificationChildrenContainer.mMinimizedGroupHeader = null;
            notificationChildrenContainer.mMinimizedGroupHeaderWrapper = null;
        }
        if (notificationHeaderView == null) {
            return;
        }
        notificationChildrenContainer.mMinimizedGroupHeader = notificationHeaderView;
        notificationHeaderView.findViewById(R.id.feedbackAllMask).setVisibility(0);
        notificationChildrenContainer.mMinimizedGroupHeader.setOnClickListener(anonymousClass1);
        NotificationHeaderViewWrapper notificationHeaderViewWrapper = (NotificationHeaderViewWrapper) NotificationViewWrapper.wrap(notificationChildrenContainer.getContext(), notificationChildrenContainer.mMinimizedGroupHeader, notificationChildrenContainer.mContainingNotification);
        notificationChildrenContainer.mMinimizedGroupHeaderWrapper = notificationHeaderViewWrapper;
        notificationHeaderViewWrapper.mRoundnessChangedListener = new NotificationChildrenContainer$$ExternalSyntheticLambda0(notificationChildrenContainer);
        notificationChildrenContainer.addView((View) notificationChildrenContainer.mMinimizedGroupHeader, 0);
        notificationChildrenContainer.invalidate();
        notificationChildrenContainer.mMinimizedGroupHeaderWrapper.onContentUpdated(notificationChildrenContainer.mContainingNotification);
        notificationChildrenContainer.updateHeaderVisibility(false);
        notificationChildrenContainer.updateChildrenAppearance();
    }

    public final void setNotificationFaded(boolean z) {
        this.mIsFaded = z;
        if (!childrenRequireOverlappingRendering()) {
            NotificationFadeAware.setLayerTypeForFaded(this, false);
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            if (notificationChildrenContainer != null) {
                notificationChildrenContainer.setNotificationFaded(z);
            } else {
                NotificationFadeAware.setLayerTypeForFaded(notificationChildrenContainer, z);
            }
            for (NotificationContentView notificationContentView : this.mLayouts) {
                if (notificationContentView != null) {
                    notificationContentView.setNotificationFaded(z);
                } else {
                    NotificationFadeAware.setLayerTypeForFaded(notificationContentView, z);
                }
            }
            return;
        }
        NotificationFadeAware.setLayerTypeForFaded(this, z);
        NotificationChildrenContainer notificationChildrenContainer2 = this.mChildrenContainer;
        if (notificationChildrenContainer2 != null) {
            notificationChildrenContainer2.setNotificationFaded(false);
        } else {
            NotificationFadeAware.setLayerTypeForFaded(notificationChildrenContainer2, false);
        }
        for (NotificationContentView notificationContentView2 : this.mLayouts) {
            if (notificationContentView2 != null) {
                notificationContentView2.setNotificationFaded(false);
            } else {
                NotificationFadeAware.setLayerTypeForFaded(notificationContentView2, false);
            }
        }
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.mOnClickListener = onClickListener;
        updateClickAndFocus();
    }

    public final void setOnKeyguard(boolean z) {
        if (z != this.mOnKeyguard) {
            boolean isAboveShelf = isAboveShelf();
            boolean isExpanded = isExpanded(false);
            this.mOnKeyguard = z;
            onExpansionChanged(false, isExpanded);
            if (isExpanded != isExpanded(false)) {
                if (this.mIsSummaryWithChildren) {
                    this.mChildrenContainer.updateGroupOverflow();
                }
                notifyHeightChanged(false);
            }
            if (isAboveShelf() != isAboveShelf) {
                this.mAboveShelfChangedListener.onAboveShelfStateChanged(!isAboveShelf);
            }
        }
    }

    public void setPrivateLayout(NotificationContentView notificationContentView) {
        this.mPrivateLayout = notificationContentView;
        this.mLayouts = new NotificationContentView[]{notificationContentView, this.mPublicLayout};
    }

    public void setPublicLayout(NotificationContentView notificationContentView) {
        this.mPublicLayout = notificationContentView;
        this.mLayouts = new NotificationContentView[]{this.mPrivateLayout, notificationContentView};
    }

    public final void setTranslation(float f) {
        invalidate();
        if (this.mDismissUsingRowTranslationX) {
            setTranslationX(f);
        } else if (this.mTranslateableViews != null) {
            for (int i = 0; i < this.mTranslateableViews.size(); i++) {
                if (this.mTranslateableViews.get(i) != null) {
                    ((View) this.mTranslateableViews.get(i)).setTranslationX(f);
                }
            }
            invalidateOutline();
            this.mEntry.mIcons.mShelfIcon.setScrollX((int) (-f));
        }
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin == null || notificationMenuRowPlugin.getMenuView() == null) {
            return;
        }
        this.mMenuRow.onParentTranslationUpdate(f);
    }

    public final void setUserExpanded(boolean z, boolean z2) {
        if (this.mIsSummaryWithChildren && !shouldShowPublic() && z2 && !this.mChildrenContainer.showingAsLowPriority()) {
            boolean isGroupExpanded = this.mGroupExpansionManager.isGroupExpanded(this.mEntry);
            this.mGroupExpansionManager.setGroupExpanded(this.mEntry, z);
            onExpansionChanged(true, isGroupExpanded);
        } else if (!z || this.mExpandable) {
            boolean isExpanded = isExpanded(false);
            this.mHasUserChangedExpansion = true;
            this.mUserExpanded = z;
            onExpansionChanged(true, isExpanded);
            if (isExpanded || !isExpanded(false) || this.mActualHeight == getIntrinsicHeight()) {
                return;
            }
            notifyHeightChanged(true);
        }
    }

    public final void setUserLocked(boolean z) {
        this.mUserLocked = z;
        NotificationContentView notificationContentView = this.mPrivateLayout;
        notificationContentView.mUserExpanding = z;
        if (z) {
            notificationContentView.mTransformationStartVisibleType = notificationContentView.mVisibleType;
        } else {
            notificationContentView.mTransformationStartVisibleType = -1;
            int calculateVisibleType = notificationContentView.calculateVisibleType();
            notificationContentView.mVisibleType = calculateVisibleType;
            notificationContentView.updateViewVisibilities(calculateVisibleType);
            notificationContentView.updateBackgroundColor(false);
        }
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.setUserLocked(z);
            if (this.mIsSummaryWithChildren) {
                if (z || !isGroupExpanded()) {
                    updateBackgroundForGroupState();
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean shouldClipToActualHeight() {
        return !this.mExpandAnimationRunning;
    }

    public final boolean shouldShowPublic() {
        return this.mSensitive && this.mHideSensitiveForIntrinsicHeight;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean showingPulsing() {
        StatusBarStateController statusBarStateController;
        KeyguardBypassController keyguardBypassController;
        return isHeadsUpState() && (((statusBarStateController = this.mStatusBarStateController) != null && statusBarStateController.isDozing()) || (this.mOnKeyguard && ((keyguardBypassController = this.mBypassController) == null || keyguardBypassController.getBypassEnabled())));
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final void updateBackgroundColors() {
        updateColors$1();
        this.mBackgroundNormal.setCustomBackground$1();
        updateBackgroundTint();
        this.mColorUpdateLogger.getClass();
        if (this.mIsSummaryWithChildren) {
            Iterator it = this.mChildrenContainer.mAttachedChildren.iterator();
            while (it.hasNext()) {
                ((ExpandableNotificationRow) it.next()).updateBackgroundColors();
            }
        }
    }

    public final void updateBackgroundForGroupState() {
        if (this.mIsSummaryWithChildren) {
            boolean z = (this.mShowGroupBackgroundWhenExpanded || !isGroupExpanded() || isGroupExpansionChanging() || this.mUserLocked) ? false : true;
            this.mShowNoBackground = z;
            NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
            NotificationHeaderView notificationHeaderView = notificationChildrenContainer.mGroupHeader;
            if (notificationHeaderView != null) {
                if (z) {
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setColor(notificationChildrenContainer.mContainingNotification.calculateBgColor(true));
                    notificationChildrenContainer.mGroupHeader.setHeaderBackgroundDrawable(colorDrawable);
                } else {
                    notificationHeaderView.setHeaderBackgroundDrawable((Drawable) null);
                }
            }
            List list = this.mChildrenContainer.mAttachedChildren;
            int i = 0;
            while (true) {
                ArrayList arrayList = (ArrayList) list;
                if (i >= arrayList.size()) {
                    break;
                }
                ((ExpandableNotificationRow) arrayList.get(i)).updateBackgroundForGroupState();
                i++;
            }
        } else if (isChildInGroup()) {
            NotificationContentView showingLayout = getShowingLayout();
            NotificationViewWrapper visibleWrapper = showingLayout.getVisibleWrapper((showingLayout.mContainingNotification.isGroupExpanded() || showingLayout.mContainingNotification.mUserLocked) ? showingLayout.calculateVisibleType() : showingLayout.mVisibleType);
            this.mShowNoBackground = !(isGroupExpanded() || ((this.mNotificationParent.isGroupExpansionChanging() || this.mNotificationParent.mUserLocked) && (visibleWrapper != null ? visibleWrapper.getCustomBackgroundColor() : 0) != 0));
        } else {
            this.mShowNoBackground = false;
        }
        if (!this.mCustomOutline) {
            setOutlineProvider(needsOutline() ? this.mProvider : null);
        }
        this.mBackgroundNormal.setVisibility(this.mShowNoBackground ? 4 : 0);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final void updateBackgroundTint() {
        int i = 0;
        updateBackgroundTint(false);
        updateBackgroundForGroupState();
        if (!this.mIsSummaryWithChildren) {
            return;
        }
        List list = this.mChildrenContainer.mAttachedChildren;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i >= arrayList.size()) {
                return;
            }
            ((ExpandableNotificationRow) arrayList.get(i)).updateBackgroundForGroupState();
            i++;
        }
    }

    public final void updateBubbleButton() {
        for (NotificationContentView notificationContentView : this.mLayouts) {
            notificationContentView.applyBubbleAction(notificationContentView.mExpandedChild, this.mEntry);
        }
    }

    public final void updateChildrenVisibility() {
        NotificationGuts notificationGuts;
        boolean z = this.mExpandAnimationRunning && (notificationGuts = this.mGuts) != null && notificationGuts.mExposed;
        this.mPrivateLayout.setVisibility((this.mShowingPublic || this.mIsSummaryWithChildren || z) ? 4 : 0);
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.setVisibility((this.mShowingPublic || !this.mIsSummaryWithChildren || z) ? 4 : 0);
        }
        updateLimits();
    }

    public final void updateClickAndFocus() {
        boolean z = !isChildInGroup() || isGroupExpanded();
        boolean z2 = this.mOnClickListener != null && z;
        if (isFocusable() != z) {
            setFocusable(z);
        }
        if (isClickable() != z2) {
            setClickable(z2);
        }
    }

    public final void updateContentAccessibilityImportanceForGuts(boolean z) {
        setImportantForAccessibility(z ? 0 : 2);
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer != null) {
            notificationChildrenContainer.setImportantForAccessibility(z ? 0 : 4);
        }
        NotificationContentView[] notificationContentViewArr = this.mLayouts;
        if (notificationContentViewArr != null) {
            for (NotificationContentView notificationContentView : notificationContentViewArr) {
                notificationContentView.setImportantForAccessibility(z ? 0 : 4);
            }
        }
        if (z) {
            requestAccessibilityFocus();
        }
    }

    public final void updateLimits() {
        NotificationContentView[] notificationContentViewArr = this.mLayouts;
        int length = notificationContentViewArr.length;
        for (int i = 0; i < length; i++) {
            NotificationContentView notificationContentView = notificationContentViewArr[i];
            View view = notificationContentView.mContractedChild;
            boolean z = (view == null || view.getId() == 16909611) ? false : true;
            int i2 = this.mEntry.targetSdk;
            boolean z2 = i2 < 24;
            boolean z3 = i2 < 28;
            boolean z4 = i2 < 31;
            int i3 = (z && z4 && !this.mIsSummaryWithChildren) ? z2 ? this.mMaxSmallHeightBeforeN : z3 ? this.mMaxSmallHeightBeforeP : this.mMaxSmallHeightBeforeS : view instanceof CallLayout ? this.mMaxExpandedHeight : (this.mUseIncreasedCollapsedHeight && notificationContentView == this.mPrivateLayout) ? this.mMaxSmallHeightLarge : this.mMaxSmallHeight;
            View view2 = notificationContentView.mHeadsUpChild;
            int i4 = (view2 == null || view2.getId() == 16909611 || !z4) ? (this.mUseIncreasedHeadsUpHeight && notificationContentView == this.mPrivateLayout) ? this.mMaxHeadsUpHeightIncreased : this.mMaxHeadsUpHeight : z2 ? this.mMaxHeadsUpHeightBeforeN : z3 ? this.mMaxHeadsUpHeightBeforeP : this.mMaxHeadsUpHeightBeforeS;
            NotificationViewWrapper visibleWrapper = notificationContentView.getVisibleWrapper(2);
            if (visibleWrapper != null) {
                i4 = Math.max(i4, visibleWrapper.getMinLayoutHeight());
            }
            int i5 = this.mMaxExpandedHeight;
            notificationContentView.mSmallHeight = i3;
            notificationContentView.mHeadsUpHeight = i4;
            notificationContentView.mNotificationMaxHeight = i5;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateShelfIconColor() {
        /*
            r6 = this;
            com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r6.mEntry
            com.android.systemui.statusbar.notification.icon.IconPack r0 = r0.mIcons
            com.android.systemui.statusbar.StatusBarIconView r0 = r0.mShelfIcon
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r2 = 2131362736(0x7f0a03b0, float:1.834526E38)
            java.lang.Object r2 = r0.getTag(r2)
            boolean r2 = r1.equals(r2)
            r3 = 0
            if (r2 == 0) goto L3e
            android.content.Context r2 = r6.mContext
            com.android.internal.util.ContrastColorUtil r2 = com.android.internal.util.ContrastColorUtil.getInstance(r2)
            r4 = 2131362735(0x7f0a03af, float:1.8345259E38)
            java.lang.Object r5 = r0.getTag(r4)
            if (r5 == 0) goto L2a
            boolean r1 = r1.equals(r5)
            goto L39
        L2a:
            android.graphics.drawable.Drawable r1 = r0.getDrawable()
            boolean r1 = r2.isGrayscaleIcon(r1)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            r0.setTag(r4, r2)
        L39:
            if (r1 == 0) goto L3c
            goto L3e
        L3c:
            r1 = r3
            goto L3f
        L3e:
            r1 = 1
        L3f:
            if (r1 == 0) goto L45
            int r3 = r6.getOriginalIconColor()
        L45:
            r0.setStaticDrawableColor(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow.updateShelfIconColor():void");
    }

    public final int getPinnedHeadsUpHeight(boolean z) {
        return this.mIsSummaryWithChildren ? this.mChildrenContainer.getIntrinsicHeight() : this.mExpandedWhenPinned ? Math.max(getMaxExpandHeight(), getHeadsUpHeight()) : z ? Math.max(getCollapsedHeight(), getHeadsUpHeight()) : getHeadsUpHeight();
    }

    public ExpandableNotificationRow(Context context, AttributeSet attributeSet, NotificationEntry notificationEntry) {
        this(context, attributeSet, context.getUserId() == notificationEntry.mSbn.getNormalizedUserId() ? context : context.createContextAsUser(UserHandle.of(notificationEntry.mSbn.getNormalizedUserId()), 0));
    }

    public final void doLongClickCallback(int i, int i2, NotificationMenuRowPlugin.MenuItem menuItem) {
        ExpandableNotificationRowController$$ExternalSyntheticLambda0 expandableNotificationRowController$$ExternalSyntheticLambda0 = this.mLongPressListener;
        if (expandableNotificationRowController$$ExternalSyntheticLambda0 == null || menuItem == null) {
            return;
        }
        ExpandableNotificationRowController expandableNotificationRowController = (ExpandableNotificationRowController) expandableNotificationRowController$$ExternalSyntheticLambda0.f$0;
        ExpandableNotificationRow expandableNotificationRow = expandableNotificationRowController.mView;
        if (expandableNotificationRow.mIsSummaryWithChildren) {
            expandableNotificationRow.mExpandClickListener.onClick(expandableNotificationRow);
        } else {
            expandableNotificationRowController.mNotificationGutsManager.openGuts(this, i, i2, menuItem);
        }
    }

    public final void setChronometerRunning(boolean z) {
        this.mLastChronometerRunning = z;
        setChronometerRunning(z, this.mPrivateLayout);
        setChronometerRunning(z, this.mPublicLayout);
        NotificationChildrenContainer notificationChildrenContainer = this.mChildrenContainer;
        if (notificationChildrenContainer == null) {
            return;
        }
        List list = notificationChildrenContainer.mAttachedChildren;
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i >= arrayList.size()) {
                return;
            }
            ((ExpandableNotificationRow) arrayList.get(i)).setChronometerRunning(z);
            i++;
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda0] */
    public ExpandableNotificationRow(Context context, AttributeSet attributeSet, Context context2) {
        super(context, attributeSet);
        this.mUpdateSelfBackgroundOnUpdate = true;
        this.mShowSnooze = false;
        this.mShowPublicExpander = true;
        this.mHeaderVisibleAmount = 1.0f;
        this.mLastChronometerRunning = true;
        this.mExpandClickListener = new AnonymousClass1();
        this.mExpireRecentlyAlertedFlag = new Runnable() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExpandableNotificationRow expandableNotificationRow = ExpandableNotificationRow.this;
                SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
                expandableNotificationRow.applyAudiblyAlertedRecently(false);
            }
        };
        this.mImageResolver = new NotificationInlineImageResolver(context2, new NotificationInlineImageCache());
        this.mSmallRoundness = getResources().getDimension(com.android.wm.shell.R.dimen.notification_corner_radius_small) / ((ActivatableNotificationView) this).mRoundableState.maxRadius;
        initDimens$1();
    }
}
