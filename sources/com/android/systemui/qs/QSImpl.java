package com.android.systemui.qs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.IndentingPrintWriter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.app.animation.Interpolators;
import com.android.compose.theme.PlatformThemeKt;
import com.android.keyguard.BouncerPanelExpansionCalculator;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ShadeInterpolation;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.globalactions.GlobalActionsDialogLite;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSTileRevealController;
import com.android.systemui.qs.customize.QSCustomizer;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.footer.ui.compose.FooterActionsKt;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModelKt;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.brightness.BrightnessController;
import com.android.systemui.shade.transition.LargeScreenShadeInterpolator;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.util.Utils;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSImpl implements QS, CommandQueue.Callbacks, StatusBarStateController.StateListener, Dumpable {
    public final KeyguardBypassController mBypassController;
    public final CommandQueue mCommandQueue;
    public QSContainerImpl mContainer;
    public final DumpManager mDumpManager;
    public QSFooterViewController mFooter;
    public final FooterActionsController mFooterActionsController;
    public ComposeView mFooterActionsView;
    public final FooterActionsViewModel.Factory mFooterActionsViewModelFactory;
    public QuickStatusBarHeader mHeader;
    public boolean mHeaderAnimating;
    public boolean mInSplitShade;
    public boolean mIsSmallScreen;
    public final LargeScreenShadeInterpolator mLargeScreenShadeInterpolator;
    public float mLastHeaderTranslation;
    public boolean mLastKeyguardAndExpanded;
    public float mLastPanelFraction;
    public int mLastViewHeight;
    public int mLayoutDirection;
    public boolean mListening;
    public float mLockscreenToShadeProgress;
    public final QSLogger mLogger;
    public boolean mOverScrolling;
    public QS.HeightListener mPanelView;
    public QSAnimator mQSAnimator;
    public QSContainerImplController mQSContainerImplController;
    public QSCustomizerController mQSCustomizerController;
    public FooterActionsViewModel mQSFooterActionsViewModel;
    public QSPanelController mQSPanelController;
    public NonInterceptingScrollView mQSPanelScrollView;
    public QSSquishinessController mQSSquishinessController;
    public final MediaHost mQqsMediaHost;
    public final QSDisableFlagsLogger mQsDisableFlagsLogger;
    public boolean mQsDisabled;
    public boolean mQsExpanded;
    public final MediaHost mQsMediaHost;
    public boolean mQsVisible;
    public QuickQSPanelController mQuickQSPanelController;
    public final RemoteInputQuickSettingsDisabler mRemoteInputQuickSettingsDisabler;
    public View mRootView;
    public QS.ScrollListener mScrollListener;
    public boolean mShouldUpdateMediaSquishiness;
    public boolean mShowCollapsedOnKeyguard;
    public boolean mStackScrollerOverscrolling;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public boolean mTransitioningToFullShade;
    public final Rect mQsBounds = new Rect();
    public float mLastQSExpansion = -1.0f;
    public float mSquishinessFraction = 1.0f;
    public final int[] mLocationTemp = new int[2];
    public int mStatusBarState = -1;
    public final int[] mTmpLocation = new int[2];
    public final ListeningAndVisibilityLifecycleOwner mListeningAndVisibilityLifecycleOwner = new ListeningAndVisibilityLifecycleOwner();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class ListeningAndVisibilityLifecycleOwner implements LifecycleOwner {
        public final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
        public boolean mDestroyed = false;

        public ListeningAndVisibilityLifecycleOwner() {
            updateState$1();
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public final Lifecycle getLifecycle() {
            return this.mLifecycleRegistry;
        }

        public final void updateState$1() {
            boolean z = this.mDestroyed;
            LifecycleRegistry lifecycleRegistry = this.mLifecycleRegistry;
            if (z) {
                lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
                return;
            }
            QSImpl qSImpl = QSImpl.this;
            if (!qSImpl.mListening) {
                lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
            } else if (qSImpl.mQsVisible) {
                lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
            } else {
                lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
            }
        }
    }

    public QSImpl(RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler, SysuiStatusBarStateController sysuiStatusBarStateController, CommandQueue commandQueue, MediaHost mediaHost, MediaHost mediaHost2, KeyguardBypassController keyguardBypassController, QSDisableFlagsLogger qSDisableFlagsLogger, DumpManager dumpManager, QSLogger qSLogger, FooterActionsController footerActionsController, FooterActionsViewModel.Factory factory, LargeScreenShadeInterpolator largeScreenShadeInterpolator) {
        this.mRemoteInputQuickSettingsDisabler = remoteInputQuickSettingsDisabler;
        this.mQsMediaHost = mediaHost;
        this.mQqsMediaHost = mediaHost2;
        this.mQsDisableFlagsLogger = qSDisableFlagsLogger;
        this.mLogger = qSLogger;
        this.mLargeScreenShadeInterpolator = largeScreenShadeInterpolator;
        this.mCommandQueue = commandQueue;
        this.mBypassController = keyguardBypassController;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mDumpManager = dumpManager;
        this.mFooterActionsController = footerActionsController;
        this.mFooterActionsViewModelFactory = factory;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void animateHeaderSlidingOut() {
        if (this.mRootView.getY() == (-this.mHeader.getHeight())) {
            return;
        }
        this.mHeaderAnimating = true;
        this.mRootView.animate().y(-this.mHeader.getHeight()).setStartDelay(0L).setDuration(360L).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.qs.QSImpl.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                View view = QSImpl.this.mRootView;
                if (view != null) {
                    view.animate().setListener(null);
                }
                QSImpl qSImpl = QSImpl.this;
                qSImpl.mHeaderAnimating = false;
                qSImpl.updateQsState();
            }
        }).start();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void closeCustomizer() {
        this.mQSCustomizerController.hide(true);
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void closeDetail() {
        QSCustomizerController qSCustomizerController = ((QSPanelControllerBase) this.mQSPanelController).mQsCustomizerController;
        if (((QSCustomizer) qSCustomizerController.mView).isShown) {
            qSCustomizerController.hide(true);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        if (i != this.mRootView.getContext().getDisplayId()) {
            return;
        }
        int adjustDisableFlags = this.mRemoteInputQuickSettingsDisabler.adjustDisableFlags(i3);
        final QSDisableFlagsLogger qSDisableFlagsLogger = this.mQsDisableFlagsLogger;
        qSDisableFlagsLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        Function1 function1 = new Function1() { // from class: com.android.systemui.qs.QSDisableFlagsLogger$logDisableFlagChange$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return QSDisableFlagsLogger.this.disableFlagsLogger.getDisableFlagsString(new DisableFlagsLogger.DisableState(logMessage.getInt1(), logMessage.getInt2()), new DisableFlagsLogger.DisableState((int) logMessage.getLong1(), (int) logMessage.getLong2()));
            }
        };
        LogBuffer logBuffer = qSDisableFlagsLogger.buffer;
        LogMessage obtain = logBuffer.obtain("QSDisableFlagsLog", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i2;
        logMessageImpl.int2 = i3;
        logMessageImpl.long1 = i2;
        logMessageImpl.long2 = adjustDisableFlags;
        logBuffer.commit(obtain);
        int i4 = adjustDisableFlags & 1;
        boolean z2 = i4 != 0;
        if (z2 == this.mQsDisabled) {
            return;
        }
        this.mQsDisabled = z2;
        QSContainerImpl qSContainerImpl = this.mContainer;
        qSContainerImpl.getClass();
        boolean z3 = i4 != 0;
        if (z3 != qSContainerImpl.mQsDisabled) {
            qSContainerImpl.mQsDisabled = z3;
        }
        QuickStatusBarHeader quickStatusBarHeader = this.mHeader;
        quickStatusBarHeader.getClass();
        boolean z4 = i4 != 0;
        if (z4 != quickStatusBarHeader.mQsDisabled) {
            quickStatusBarHeader.mQsDisabled = z4;
            QuickQSPanel quickQSPanel = quickStatusBarHeader.mHeaderQsPanel;
            if (z4 != quickQSPanel.mDisabledByPolicy) {
                quickQSPanel.mDisabledByPolicy = z4;
                quickQSPanel.setVisibility(z4 ? 8 : 0);
            }
            quickStatusBarHeader.updateResources();
        }
        QSFooterView qSFooterView = (QSFooterView) this.mFooter.mView;
        qSFooterView.getClass();
        boolean z5 = i4 != 0;
        if (z5 != qSFooterView.mQsDisabled) {
            qSFooterView.mQsDisabled = z5;
            qSFooterView.post(new QSFooterView$$ExternalSyntheticLambda0(qSFooterView));
        }
        updateQsState();
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("QSImpl:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("mQsBounds: " + this.mQsBounds);
        indentingPrintWriter.println("mQsExpanded: " + this.mQsExpanded);
        indentingPrintWriter.println("mHeaderAnimating: " + this.mHeaderAnimating);
        indentingPrintWriter.println("mStackScrollerOverscrolling: " + this.mStackScrollerOverscrolling);
        indentingPrintWriter.println("mListening: " + this.mListening);
        indentingPrintWriter.println("mQsVisible: " + this.mQsVisible);
        indentingPrintWriter.println("mLayoutDirection: " + this.mLayoutDirection);
        indentingPrintWriter.println("mLastQSExpansion: " + this.mLastQSExpansion);
        indentingPrintWriter.println("mLastPanelFraction: " + this.mLastPanelFraction);
        indentingPrintWriter.println("mSquishinessFraction: " + this.mSquishinessFraction);
        indentingPrintWriter.println("mQsDisabled: " + this.mQsDisabled);
        indentingPrintWriter.println("mTemp: " + Arrays.toString(this.mLocationTemp));
        indentingPrintWriter.println("mShowCollapsedOnKeyguard: " + this.mShowCollapsedOnKeyguard);
        indentingPrintWriter.println("mLastKeyguardAndExpanded: " + this.mLastKeyguardAndExpanded);
        indentingPrintWriter.println("mStatusBarState: " + StatusBarState.toString(this.mStatusBarState));
        indentingPrintWriter.println("mTmpLocation: " + Arrays.toString(this.mTmpLocation));
        indentingPrintWriter.println("mLastViewHeight: " + this.mLastViewHeight);
        indentingPrintWriter.println("mLastHeaderTranslation: " + this.mLastHeaderTranslation);
        indentingPrintWriter.println("mInSplitShade: " + this.mInSplitShade);
        indentingPrintWriter.println("mTransitioningToFullShade: " + this.mTransitioningToFullShade);
        indentingPrintWriter.println("mLockscreenToShadeProgress: " + this.mLockscreenToShadeProgress);
        indentingPrintWriter.println("mOverScrolling: " + this.mOverScrolling);
        indentingPrintWriter.println("mShouldUpdateMediaSquishiness: " + this.mShouldUpdateMediaSquishiness);
        indentingPrintWriter.println("isCustomizing: " + this.mQSCustomizerController.isCustomizing());
        View view = this.mRootView;
        if (view != null) {
            indentingPrintWriter.println("top: " + view.getTop());
            indentingPrintWriter.println("y: " + view.getY());
            indentingPrintWriter.println("translationY: " + view.getTranslationY());
            indentingPrintWriter.println("alpha: " + view.getAlpha());
            indentingPrintWriter.println("height: " + view.getHeight());
            indentingPrintWriter.println("measuredHeight: " + view.getMeasuredHeight());
            indentingPrintWriter.println("clipBounds: " + view.getClipBounds());
        } else {
            indentingPrintWriter.println("getView(): null");
        }
        QuickStatusBarHeader quickStatusBarHeader = this.mHeader;
        if (quickStatusBarHeader == null) {
            indentingPrintWriter.println("mHeader: null");
            return;
        }
        indentingPrintWriter.println("headerHeight: " + quickStatusBarHeader.getHeight());
        int visibility = quickStatusBarHeader.getVisibility();
        indentingPrintWriter.println("Header visibility: ".concat(visibility == 0 ? "VISIBLE" : visibility == 4 ? "INVISIBLE" : "GONE"));
    }

    @Override // com.android.systemui.plugins.FragmentBase
    public final Context getContext() {
        return this.mRootView.getContext();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getDesiredHeight() {
        return this.mQSCustomizerController.isCustomizing() ? this.mRootView.getHeight() : this.mRootView.getMeasuredHeight();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final View getHeader() {
        return this.mHeader;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeaderBottom() {
        return this.mHeader.getBottom();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void getHeaderBoundsOnScreen(Rect rect) {
        this.mHeader.getBoundsOnScreen(rect);
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeaderLeft() {
        return this.mHeader.getLeft();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeaderTop() {
        return this.mHeader.getTop();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getHeightDiff() {
        return this.mHeader.getPaddingBottom() + (this.mQSPanelScrollView.getBottom() - this.mHeader.getBottom());
    }

    public ListeningAndVisibilityLifecycleOwner getListeningAndVisibilityLifecycleOwner() {
        return this.mListeningAndVisibilityLifecycleOwner;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final int getQsMinExpansionHeight() {
        if (!this.mInSplitShade) {
            return this.mHeader.getHeight();
        }
        this.mRootView.getLocationOnScreen(this.mLocationTemp);
        return this.mRootView.getHeight() + ((int) (r1[1] - this.mRootView.getTranslationY()));
    }

    public int getStatusBarState() {
        return this.mStatusBarState;
    }

    @Override // com.android.systemui.plugins.FragmentBase
    public final View getView() {
        return this.mRootView;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void hideImmediately() {
        this.mRootView.animate().cancel();
        this.mRootView.setY(-getQsMinExpansionHeight());
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isCustomizing() {
        return this.mQSCustomizerController.isCustomizing();
    }

    public boolean isExpanded() {
        return this.mQsExpanded;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isFullyCollapsed() {
        float f = this.mLastQSExpansion;
        return f == 0.0f || f == -1.0f;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isHeaderShown() {
        return this.mHeader.isShown();
    }

    public boolean isKeyguardState() {
        return ((StatusBarStateControllerImpl) this.mStatusBarStateController).mUpcomingState == 1;
    }

    public boolean isListening() {
        return this.mListening;
    }

    public boolean isQsVisible() {
        return this.mQsVisible;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final boolean isShowingDetail() {
        return this.mQSCustomizerController.isCustomizing();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void notifyCustomizeChanged() {
        this.mContainer.updateExpansion();
        boolean isCustomizing = this.mQSCustomizerController.isCustomizing();
        this.mQSPanelScrollView.setVisibility(!isCustomizing ? 0 : 4);
        this.mFooter.setVisibility(!isCustomizing ? 0 : 4);
        ComposeView composeView = this.mFooterActionsView;
        if (composeView != null) {
            composeView.setVisibility(!isCustomizing ? 0 : 4);
        }
        this.mHeader.setVisibility(isCustomizing ? 4 : 0);
        QS.HeightListener heightListener = this.mPanelView;
        if (heightListener != null) {
            heightListener.onQsHeightChanged();
        }
    }

    public final void onComponentCreated(DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl, Bundle bundle) {
        View view;
        QSPanelController qSPanelController;
        QuickQSPanelController quickQSPanelController;
        QSFooterViewController qSFooterViewController;
        QSContainerImplController qSContainerImplController;
        QSAnimator qSAnimator;
        QSSquishinessController qSSquishinessController;
        QSCustomizerController qSCustomizerController;
        final int i = 0;
        final int i2 = 1;
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                view = daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView;
                break;
            default:
                view = daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView;
                break;
        }
        this.mRootView = view;
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                qSPanelController = (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get();
                break;
            default:
                qSPanelController = (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get();
                break;
        }
        this.mQSPanelController = qSPanelController;
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                quickQSPanelController = (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get();
                break;
            default:
                quickQSPanelController = (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get();
                break;
        }
        this.mQuickQSPanelController = quickQSPanelController;
        this.mQSPanelController.init$9();
        this.mQuickQSPanelController.init$9();
        FooterActionsViewModel.Factory factory = this.mFooterActionsViewModelFactory;
        final ListeningAndVisibilityLifecycleOwner listeningAndVisibilityLifecycleOwner = this.mListeningAndVisibilityLifecycleOwner;
        final GlobalActionsDialogLite globalActionsDialogLite = (GlobalActionsDialogLite) factory.globalActionsDialogLiteProvider.get();
        if (((LifecycleRegistry) listeningAndVisibilityLifecycleOwner.getLifecycle()).state == Lifecycle.State.DESTROYED) {
            globalActionsDialogLite.destroy();
        } else {
            listeningAndVisibilityLifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() { // from class: com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModel$Factory$create$1
                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public final void onDestroy$1() {
                    GlobalActionsDialogLite.this.destroy();
                }
            });
        }
        Context context = factory.context;
        Intrinsics.checkNotNull(globalActionsDialogLite);
        this.mQSFooterActionsViewModel = FooterActionsViewModelKt.FooterActionsViewModel(context, factory.footerActionsInteractor, factory.falsingManager, globalActionsDialogLite, factory.activityStarter, factory.showPowerButton);
        ComposeView composeView = (ComposeView) this.mRootView.findViewById(R.id.qs_footer_actions);
        this.mFooterActionsView = composeView;
        final FooterActionsViewModel footerActionsViewModel = this.mQSFooterActionsViewModel;
        composeView.setContent$1(new ComposableLambdaImpl(-416510701, true, new Function2() { // from class: com.android.systemui.qs.QSUtils$setFooterActionsViewContent$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.qs.QSUtils$setFooterActionsViewContent$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                final FooterActionsViewModel footerActionsViewModel2 = FooterActionsViewModel.this;
                final LifecycleOwner lifecycleOwner = listeningAndVisibilityLifecycleOwner;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-1994740727, new Function2() { // from class: com.android.systemui.qs.QSUtils$setFooterActionsViewContent$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        Composer composer2 = (Composer) obj3;
                        if ((((Number) obj4).intValue() & 11) == 2) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        FooterActionsKt.FooterActions(FooterActionsViewModel.this, lifecycleOwner, null, composer2, 72, 4);
                        return Unit.INSTANCE;
                    }
                }, composer), composer, 48, 1);
                return Unit.INSTANCE;
            }
        }));
        this.mFooterActionsController.init();
        NonInterceptingScrollView nonInterceptingScrollView = (NonInterceptingScrollView) this.mRootView.findViewById(R.id.expanded_qs_scroll_view);
        this.mQSPanelScrollView = nonInterceptingScrollView;
        nonInterceptingScrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: com.android.systemui.qs.QSImpl$$ExternalSyntheticLambda0
            public final /* synthetic */ QSImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                int i11 = i;
                QSImpl qSImpl = this.f$0;
                switch (i11) {
                    case 0:
                        qSImpl.updateQsBounds();
                        break;
                    default:
                        if (i8 - i10 == i4 - i6) {
                            qSImpl.getClass();
                            break;
                        } else {
                            qSImpl.setQsExpansion(qSImpl.mLastQSExpansion, qSImpl.mLastPanelFraction, qSImpl.mLastHeaderTranslation, qSImpl.mSquishinessFraction);
                            break;
                        }
                }
            }
        });
        this.mQSPanelScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.android.systemui.qs.QSImpl$$ExternalSyntheticLambda1
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view2, int i3, int i4, int i5, int i6) {
                QSImpl qSImpl = QSImpl.this;
                qSImpl.mQSAnimator.mNeedsAnimatorUpdate = true;
                QS.ScrollListener scrollListener = qSImpl.mScrollListener;
                if (scrollListener != null) {
                    scrollListener.onQsPanelScrollChanged(i4);
                }
            }
        });
        this.mQSPanelScrollView.mScrollEnabled = true;
        this.mHeader = (QuickStatusBarHeader) this.mRootView.findViewById(R.id.header);
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                qSFooterViewController = (QSFooterViewController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.providesQSFooterProvider.get();
                break;
            default:
                qSFooterViewController = (QSFooterViewController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.providesQSFooterProvider.get();
                break;
        }
        this.mFooter = qSFooterViewController;
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                qSContainerImplController = (QSContainerImplController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSContainerImplControllerProvider.get();
                break;
            default:
                qSContainerImplController = (QSContainerImplController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSContainerImplControllerProvider.get();
                break;
        }
        this.mQSContainerImplController = qSContainerImplController;
        qSContainerImplController.init$9();
        QSContainerImpl qSContainerImpl = (QSContainerImpl) this.mQSContainerImplController.mView;
        this.mContainer = qSContainerImpl;
        String simpleName = qSContainerImpl.getClass().getSimpleName();
        QSContainerImpl qSContainerImpl2 = this.mContainer;
        DumpManager dumpManager = this.mDumpManager;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, simpleName, qSContainerImpl2);
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                qSAnimator = (QSAnimator) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSAnimatorProvider.get();
                break;
            default:
                qSAnimator = (QSAnimator) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSAnimatorProvider.get();
                break;
        }
        this.mQSAnimator = qSAnimator;
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                qSSquishinessController = (QSSquishinessController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSSquishinessControllerProvider.get();
                break;
            default:
                qSSquishinessController = (QSSquishinessController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSSquishinessControllerProvider.get();
                break;
        }
        this.mQSSquishinessController = qSSquishinessController;
        switch (daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.$r8$classId) {
            case 0:
                qSCustomizerController = (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get();
                break;
            default:
                qSCustomizerController = (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get();
                break;
        }
        this.mQSCustomizerController = qSCustomizerController;
        qSCustomizerController.init$9();
        ((QSCustomizer) this.mQSCustomizerController.mView).mQs = this;
        if (bundle != null) {
            setQsVisible(bundle.getBoolean("visible"));
            setExpanded(bundle.getBoolean("expanded"));
            setListening(bundle.getBoolean("listening"));
            setEditLocation(this.mRootView);
            final QSCustomizerController qSCustomizerController2 = this.mQSCustomizerController;
            qSCustomizerController2.getClass();
            if (bundle.getBoolean("qs_customizing")) {
                ((QSCustomizer) qSCustomizerController2.mView).setVisibility(0);
                ((QSCustomizer) qSCustomizerController2.mView).addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.qs.customize.QSCustomizerController.5
                    @Override // android.view.View.OnLayoutChangeListener
                    public final void onLayoutChange(View view2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                        ((QSCustomizer) QSCustomizerController.this.mView).removeOnLayoutChangeListener(this);
                        QSCustomizerController.this.show(0, 0, true);
                    }
                });
            }
            if (this.mQsExpanded) {
                this.mQSPanelController.getTileLayout().restoreInstanceState(bundle);
            }
        }
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
        statusBarStateControllerImpl.addCallback((StatusBarStateController.StateListener) this);
        onStateChanged(statusBarStateControllerImpl.mState);
        this.mRootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: com.android.systemui.qs.QSImpl$$ExternalSyntheticLambda0
            public final /* synthetic */ QSImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                int i11 = i2;
                QSImpl qSImpl = this.f$0;
                switch (i11) {
                    case 0:
                        qSImpl.updateQsBounds();
                        break;
                    default:
                        if (i8 - i10 == i4 - i6) {
                            qSImpl.getClass();
                            break;
                        } else {
                            qSImpl.setQsExpansion(qSImpl.mLastQSExpansion, qSImpl.mLastPanelFraction, qSImpl.mLastHeaderTranslation, qSImpl.mSquishinessFraction);
                            break;
                        }
                }
            }
        });
        this.mQSPanelController.mUsingHorizontalLayoutChangedListener = new QSImpl$$ExternalSyntheticLambda3(this);
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        setEditLocation(this.mRootView);
        if (configuration.getLayoutDirection() != this.mLayoutDirection) {
            this.mLayoutDirection = configuration.getLayoutDirection();
            QSAnimator qSAnimator = this.mQSAnimator;
            if (qSAnimator != null) {
                qSAnimator.updateAnimators();
                qSAnimator.setPosition(qSAnimator.mLastPosition);
            }
        }
        updateQsState();
    }

    public final void onDestroy() {
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this);
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) this);
        this.mQSPanelController.destroy();
        this.mQuickQSPanelController.destroy();
        if (this.mListening) {
            setListening(false);
        }
        QSCustomizerController qSCustomizerController = this.mQSCustomizerController;
        if (qSCustomizerController != null) {
            View view = qSCustomizerController.mView;
            ((QSCustomizer) view).mQs = null;
            ((QSCustomizer) view).mQsContainerController = null;
        }
        this.mScrollListener = null;
        QSContainerImpl qSContainerImpl = this.mContainer;
        DumpManager dumpManager = this.mDumpManager;
        if (qSContainerImpl != null) {
            dumpManager.unregisterDumpable(qSContainerImpl.getClass().getSimpleName());
        }
        dumpManager.unregisterDumpable("QSImpl");
        ListeningAndVisibilityLifecycleOwner listeningAndVisibilityLifecycleOwner = this.mListeningAndVisibilityLifecycleOwner;
        listeningAndVisibilityLifecycleOwner.mDestroyed = true;
        listeningAndVisibilityLifecycleOwner.updateState$1();
        ViewGroup viewGroup = (ViewGroup) this.mRootView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.mRootView);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("expanded", this.mQsExpanded);
        bundle.putBoolean("listening", this.mListening);
        bundle.putBoolean("visible", this.mQsVisible);
        QSCustomizerController qSCustomizerController = this.mQSCustomizerController;
        if (qSCustomizerController != null) {
            if (((QSCustomizer) qSCustomizerController.mView).isShown) {
                ((KeyguardStateControllerImpl) qSCustomizerController.mKeyguardStateController).removeCallback(qSCustomizerController.mKeyguardCallback);
            }
            bundle.putBoolean("qs_customizing", ((QSCustomizer) qSCustomizerController.mView).isCustomizing());
        }
        if (this.mQsExpanded) {
            this.mQSPanelController.getTileLayout().saveInstanceState(bundle);
        }
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
        if (i == this.mStatusBarState) {
            return;
        }
        this.mStatusBarState = i;
        boolean z = i == 1;
        this.mLastQSExpansion = -1.0f;
        QSAnimator qSAnimator = this.mQSAnimator;
        if (qSAnimator != null) {
            qSAnimator.mOnKeyguard = z;
            qSAnimator.updateQQSVisibility();
            if (qSAnimator.mOnKeyguard) {
                qSAnimator.clearAnimationState();
            }
        }
        QSFooterView qSFooterView = (QSFooterView) this.mFooter.mView;
        float f = qSFooterView.mExpansionAmount;
        qSFooterView.mExpansionAmount = f;
        TouchAnimator touchAnimator = qSFooterView.mFooterAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f);
        }
        updateQsState();
        updateShowCollapsedOnKeyguard();
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onUpcomingStateChanged(int i) {
        if (i == 1) {
            onStateChanged(i);
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setCollapseExpandAction(Runnable runnable) {
        ((QSPanel) this.mQSPanelController.mView).mCollapseExpandAction = runnable;
        ((QSPanel) this.mQuickQSPanelController.mView).mCollapseExpandAction = runnable;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setCollapsedMediaVisibilityChangedListener(Consumer consumer) {
        this.mQuickQSPanelController.mMediaVisibilityChangedListener = consumer;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setContainerController(QSContainerController qSContainerController) {
        ((QSCustomizer) this.mQSCustomizerController.mView).mQsContainerController = qSContainerController;
    }

    public final void setEditLocation(View view) {
        View findViewById = view.findViewById(android.R.id.edit);
        int[] locationOnScreen = findViewById.getLocationOnScreen();
        int width = (findViewById.getWidth() / 2) + locationOnScreen[0];
        int height = (findViewById.getHeight() / 2) + locationOnScreen[1];
        QSCustomizer qSCustomizer = (QSCustomizer) this.mQSCustomizerController.mView;
        int[] locationOnScreen2 = qSCustomizer.findViewById(R.id.customize_container).getLocationOnScreen();
        qSCustomizer.mX = width - locationOnScreen2[0];
        qSCustomizer.mY = height - locationOnScreen2[1];
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setExpanded(boolean z) {
        this.mQsExpanded = z;
        if (this.mInSplitShade && z) {
            setListening(true);
        } else {
            updateQsPanelControllerListening();
        }
        updateQsState();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setFancyClipping(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        View view = this.mRootView;
        if (view instanceof QSContainerImpl) {
            QSContainerImpl qSContainerImpl = (QSContainerImpl) view;
            float[] fArr = qSContainerImpl.mFancyClippingRadii;
            boolean z3 = false;
            float f = i5;
            boolean z4 = true;
            if (fArr[0] != f) {
                fArr[0] = f;
                fArr[1] = f;
                fArr[2] = f;
                fArr[3] = f;
                z3 = true;
            }
            if (qSContainerImpl.mFancyClippingLeftInset != i) {
                qSContainerImpl.mFancyClippingLeftInset = i;
                z3 = true;
            }
            if (qSContainerImpl.mFancyClippingTop != i2) {
                qSContainerImpl.mFancyClippingTop = i2;
                z3 = true;
            }
            if (qSContainerImpl.mFancyClippingRightInset != i3) {
                qSContainerImpl.mFancyClippingRightInset = i3;
                z3 = true;
            }
            if (qSContainerImpl.mFancyClippingBottom != i4) {
                qSContainerImpl.mFancyClippingBottom = i4;
                z3 = true;
            }
            if (qSContainerImpl.mClippingEnabled != z) {
                qSContainerImpl.mClippingEnabled = z;
                z3 = true;
            }
            if (qSContainerImpl.mIsFullWidth != z2) {
                qSContainerImpl.mIsFullWidth = z2;
            } else {
                z4 = z3;
            }
            if (z4) {
                qSContainerImpl.updateClippingPath();
            }
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setHeaderListening(boolean z) {
        QuickStatusBarHeaderController quickStatusBarHeaderController = this.mQSContainerImplController.mQuickStatusBarHeaderController;
        if (z == quickStatusBarHeaderController.mListening) {
            return;
        }
        quickStatusBarHeaderController.mListening = z;
        QuickQSPanelController quickQSPanelController = quickStatusBarHeaderController.mQuickQSPanelController;
        quickQSPanelController.setListening(z);
        if (quickQSPanelController.switchTileLayout(false)) {
            ((QuickStatusBarHeader) quickStatusBarHeaderController.mView).updateResources();
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setHeightOverride(int i) {
        QSContainerImpl qSContainerImpl = this.mContainer;
        qSContainerImpl.mHeightOverride = i;
        qSContainerImpl.updateExpansion();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setInSplitShade(boolean z) {
        this.mInSplitShade = z;
        updateShowCollapsedOnKeyguard();
        updateQsState();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setIsNotificationPanelFullWidth(boolean z) {
        this.mIsSmallScreen = z;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setListening(boolean z) {
        this.mListening = z;
        QSContainerImplController qSContainerImplController = this.mQSContainerImplController;
        boolean z2 = z && this.mQsVisible;
        QuickStatusBarHeaderController quickStatusBarHeaderController = qSContainerImplController.mQuickStatusBarHeaderController;
        if (z2 != quickStatusBarHeaderController.mListening) {
            quickStatusBarHeaderController.mListening = z2;
            QuickQSPanelController quickQSPanelController = quickStatusBarHeaderController.mQuickQSPanelController;
            quickQSPanelController.setListening(z2);
            if (quickQSPanelController.switchTileLayout(false)) {
                ((QuickStatusBarHeader) quickStatusBarHeaderController.mView).updateResources();
            }
        }
        this.mListeningAndVisibilityLifecycleOwner.updateState$1();
        updateQsPanelControllerListening();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setOverScrollAmount(int i) {
        this.mOverScrolling = i != 0;
        View view = this.mRootView;
        if (view != null) {
            view.setTranslationY(i);
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setOverscrolling(boolean z) {
        this.mStackScrollerOverscrolling = z;
        updateQsState();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setPanelView(QS.HeightListener heightListener) {
        this.mPanelView = heightListener;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setQsExpansion(float f, float f2, float f3, float f4) {
        float f5;
        int i;
        boolean z = this.mTransitioningToFullShade;
        float f6 = z ? 0.0f : f3;
        boolean z2 = this.mIsSmallScreen;
        SysuiStatusBarStateController sysuiStatusBarStateController = this.mStatusBarStateController;
        if (z2) {
            f5 = 1.0f;
        } else if (this.mInSplitShade) {
            if (z || ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mUpcomingState == 1) {
                f5 = this.mLockscreenToShadeProgress;
            }
            f5 = f2;
        } else {
            if (z) {
                f5 = this.mLockscreenToShadeProgress;
            }
            f5 = f2;
        }
        View view = this.mRootView;
        QSLogger qSLogger = this.mLogger;
        if (f5 == 0.0f && view.getVisibility() != 4) {
            qSLogger.logVisibility(4);
            view.setVisibility(4);
        } else if (f5 > 0.0f && view.getVisibility() != 0) {
            qSLogger.logVisibility(0);
            view.setVisibility(0);
        }
        view.setAlpha(this.mQSPanelController.mStatusBarKeyguardViewManager.mPrimaryBouncerInteractor.isInTransit() ? BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(f5) : isKeyguardState() ? f5 : this.mIsSmallScreen ? ShadeInterpolation.getContentAlpha(f5) : this.mLargeScreenShadeInterpolator.getQsAlpha(f5));
        QSContainerImpl qSContainerImpl = this.mContainer;
        qSContainerImpl.mQsExpansion = f;
        NonInterceptingScrollView nonInterceptingScrollView = qSContainerImpl.mQSPanelContainer;
        if (nonInterceptingScrollView != null) {
            nonInterceptingScrollView.mScrollEnabled = f > 0.0f;
        }
        qSContainerImpl.updateExpansion();
        float f7 = (f - 1.0f) * (this.mInSplitShade ? 1.0f : 0.1f);
        boolean isKeyguardState = isKeyguardState();
        boolean z3 = isKeyguardState && !this.mShowCollapsedOnKeyguard;
        if (!this.mHeaderAnimating && ((this.mStatusBarState != 1 || !this.mShowCollapsedOnKeyguard || isKeyguardState()) && !this.mOverScrolling)) {
            this.mRootView.setTranslationY(z3 ? this.mHeader.getHeight() * f7 : f6);
        }
        int height = this.mRootView.getHeight();
        if (f == this.mLastQSExpansion && this.mLastKeyguardAndExpanded == z3 && this.mLastViewHeight == height && this.mLastHeaderTranslation == f6 && this.mSquishinessFraction == f4 && this.mLastPanelFraction == f2) {
            return;
        }
        this.mLastHeaderTranslation = f6;
        this.mLastPanelFraction = f2;
        this.mSquishinessFraction = f4;
        this.mLastQSExpansion = f;
        this.mLastKeyguardAndExpanded = z3;
        this.mLastViewHeight = height;
        boolean z4 = f == 1.0f;
        boolean z5 = f == 0.0f;
        float heightDiff = f7 * getHeightDiff();
        if (f < 1.0f && f > 0.99d && this.mQuickQSPanelController.switchTileLayout(false)) {
            this.mHeader.updateResources();
        }
        QSPanelController qSPanelController = this.mQSPanelController;
        ((QSPanel) qSPanelController.mView).mShouldMoveMediaOnExpansion = !(qSPanelController.mShouldUseSplitNotificationShade && isKeyguardState);
        QSFooterViewController qSFooterViewController = this.mFooter;
        float f8 = z3 ? 1.0f : f;
        QSFooterView qSFooterView = (QSFooterView) qSFooterViewController.mView;
        qSFooterView.mExpansionAmount = f8;
        TouchAnimator touchAnimator = qSFooterView.mFooterAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f8);
        }
        if (z3) {
            f5 = 1.0f;
        } else if (!this.mInSplitShade) {
            f5 = f;
        }
        FooterActionsViewModel footerActionsViewModel = this.mQSFooterActionsViewModel;
        if (footerActionsViewModel != null) {
            boolean z6 = this.mInSplitShade;
            StateFlowImpl stateFlowImpl = footerActionsViewModel._backgroundAlpha;
            StateFlowImpl stateFlowImpl2 = footerActionsViewModel._alpha;
            if (z6) {
                Float valueOf = Float.valueOf(f5);
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, valueOf);
                Float valueOf2 = Float.valueOf(Math.max(0.0f, f5 - 0.15f) / 0.85f);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf2);
            } else {
                Float valueOf3 = Float.valueOf(Math.max(0.0f, f5 - 0.9f) / (1 - 0.9f));
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, valueOf3);
                Float valueOf4 = Float.valueOf(1.0f);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf4);
            }
        }
        QSPanelController qSPanelController2 = this.mQSPanelController;
        qSPanelController2.mRevealExpansion = f;
        QSTileRevealController qSTileRevealController = qSPanelController2.mQsTileRevealController;
        if (qSTileRevealController != null) {
            Handler handler = qSTileRevealController.mHandler;
            QSTileRevealController.AnonymousClass1 anonymousClass1 = qSTileRevealController.mRevealQsTiles;
            if (f == 1.0f) {
                handler.postDelayed(anonymousClass1, 500L);
            } else {
                handler.removeCallbacks(anonymousClass1);
            }
        }
        this.mQSPanelController.getTileLayout().setExpansion(f, f3);
        this.mQuickQSPanelController.getTileLayout().setExpansion(f, f3);
        if (!isKeyguardState || this.mShowCollapsedOnKeyguard) {
            heightDiff = 0.0f;
        }
        this.mQSPanelScrollView.setTranslationY(heightDiff);
        if (z5) {
            this.mQSPanelScrollView.setScrollY(0);
        }
        if (!z4) {
            this.mQsBounds.top = (int) (-this.mQSPanelScrollView.getTranslationY());
            this.mQsBounds.right = this.mQSPanelScrollView.getWidth();
            this.mQsBounds.bottom = this.mQSPanelScrollView.getHeight();
        }
        updateQsBounds();
        QSSquishinessController qSSquishinessController = this.mQSSquishinessController;
        if (qSSquishinessController != null) {
            float f9 = this.mSquishinessFraction;
            float f10 = qSSquishinessController.squishiness;
            if (f10 != f9) {
                if ((f10 != 1.0f && f9 == 1.0f) || (f10 != 0.0f && f9 == 0.0f)) {
                    qSSquishinessController.qsAnimator.mNeedsAnimatorUpdate = true;
                }
                qSSquishinessController.squishiness = f9;
                qSSquishinessController.qsPanelController.setSquishinessFraction(f9);
                qSSquishinessController.quickQSPanelController.setSquishinessFraction(qSSquishinessController.squishiness);
            }
        }
        QSAnimator qSAnimator = this.mQSAnimator;
        if (qSAnimator != null) {
            qSAnimator.setPosition(f);
        }
        boolean z7 = this.mShouldUpdateMediaSquishiness;
        MediaHost mediaHost = this.mQsMediaHost;
        if (z7 || !(!this.mInSplitShade || (i = ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState) == 1 || i == 2)) {
            mediaHost.setSquishFraction(this.mSquishinessFraction);
        } else {
            mediaHost.setSquishFraction(1.0f);
        }
        if (Utils.useQsMediaPlayer(this.mRootView.getContext())) {
            UniqueObjectHostView uniqueObjectHostView = mediaHost.hostView;
            UniqueObjectHostView uniqueObjectHostView2 = uniqueObjectHostView != null ? uniqueObjectHostView : null;
            if (this.mLastQSExpansion <= 0.0f || isKeyguardState() || this.mQqsMediaHost.state.visible || this.mQSPanelController.shouldUseHorizontalLayout() || this.mInSplitShade) {
                uniqueObjectHostView2.setTranslationY(0.0f);
            } else {
                uniqueObjectHostView2.setTranslationY((-uniqueObjectHostView2.getHeight()) * 1.3f * ((AccelerateInterpolator) Interpolators.ACCELERATE).getInterpolation(1.0f - this.mLastQSExpansion));
            }
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setQsVisible(boolean z) {
        this.mQsVisible = z;
        setListening(this.mListening);
        this.mListeningAndVisibilityLifecycleOwner.updateState$1();
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setScrollListener(QS.ScrollListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setShouldUpdateSquishinessOnMedia(boolean z) {
        this.mShouldUpdateMediaSquishiness = z;
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setTransitionToFullShadeProgress(boolean z, float f, float f2) {
        if (z != this.mTransitioningToFullShade) {
            this.mTransitioningToFullShade = z;
            updateShowCollapsedOnKeyguard();
        }
        this.mLockscreenToShadeProgress = f;
        float f3 = this.mLastQSExpansion;
        float f4 = this.mLastPanelFraction;
        float f5 = this.mLastHeaderTranslation;
        if (!z) {
            f2 = this.mSquishinessFraction;
        }
        setQsExpansion(f3, f4, f5, f2);
    }

    public void updateQsBounds() {
        if (this.mLastQSExpansion == 1.0f) {
            int dimensionPixelSize = this.mRootView.getContext().getResources().getDimensionPixelSize(R.dimen.qs_tiles_page_horizontal_margin) * 2;
            this.mQsBounds.set(-dimensionPixelSize, 0, this.mQSPanelScrollView.getWidth() + dimensionPixelSize, this.mQSPanelScrollView.getHeight());
        }
        this.mQSPanelScrollView.setClipBounds(this.mQsBounds);
        NonInterceptingScrollView nonInterceptingScrollView = this.mQSPanelScrollView;
        int[] iArr = this.mLocationTemp;
        nonInterceptingScrollView.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        this.mQsMediaHost.currentClipping.set(i, i2, this.mRootView.getMeasuredWidth() + i, (this.mQSPanelScrollView.getMeasuredHeight() + i2) - ((QSPanel) this.mQSPanelController.mView).getPaddingBottom());
    }

    public final void updateQsPanelControllerListening() {
        QSPanelController qSPanelController = this.mQSPanelController;
        boolean z = this.mListening && this.mQsVisible;
        boolean z2 = this.mQsExpanded;
        qSPanelController.getClass();
        qSPanelController.setListening(z && z2);
        if (z != qSPanelController.mListening) {
            qSPanelController.mListening = z;
            if (z) {
                BrightnessController brightnessController = qSPanelController.mBrightnessController;
                BrightnessController.AnonymousClass2 anonymousClass2 = brightnessController.mStartListeningRunnable;
                Handler handler = brightnessController.mBackgroundHandler;
                handler.removeCallbacks(anonymousClass2);
                handler.post(anonymousClass2);
                return;
            }
            BrightnessController brightnessController2 = qSPanelController.mBrightnessController;
            BrightnessController.AnonymousClass2 anonymousClass22 = brightnessController2.mStopListeningRunnable;
            Handler handler2 = brightnessController2.mBackgroundHandler;
            handler2.removeCallbacks(anonymousClass22);
            handler2.post(anonymousClass22);
            brightnessController2.mControlValueInitialized = false;
        }
    }

    public final void updateQsState() {
        boolean z = this.mQsExpanded;
        boolean z2 = true;
        boolean z3 = z || this.mStackScrollerOverscrolling || this.mHeaderAnimating;
        this.mQSPanelController.setExpanded(z);
        boolean isKeyguardState = isKeyguardState();
        this.mHeader.setVisibility((this.mQsExpanded || !isKeyguardState || this.mHeaderAnimating || this.mShowCollapsedOnKeyguard) ? 0 : 4);
        QuickStatusBarHeader quickStatusBarHeader = this.mHeader;
        boolean z4 = !(!isKeyguardState || this.mHeaderAnimating || this.mShowCollapsedOnKeyguard) || (this.mQsExpanded && !this.mStackScrollerOverscrolling);
        QuickQSPanelController quickQSPanelController = this.mQuickQSPanelController;
        if (quickStatusBarHeader.mExpanded != z4) {
            quickStatusBarHeader.mExpanded = z4;
            quickQSPanelController.setExpanded(z4);
        }
        boolean z5 = !this.mQsDisabled && z3;
        boolean z6 = z5 && (this.mQsExpanded || !isKeyguardState || this.mHeaderAnimating || this.mShowCollapsedOnKeyguard);
        this.mFooter.setVisibility(z6 ? 0 : 4);
        ComposeView composeView = this.mFooterActionsView;
        if (composeView != null) {
            composeView.setVisibility(z6 ? 0 : 4);
        }
        QSFooterViewController qSFooterViewController = this.mFooter;
        if ((!isKeyguardState || this.mHeaderAnimating || this.mShowCollapsedOnKeyguard) && (!this.mQsExpanded || this.mStackScrollerOverscrolling)) {
            z2 = false;
        }
        QSFooterView qSFooterView = (QSFooterView) qSFooterViewController.mView;
        if (qSFooterView.mExpanded != z2) {
            qSFooterView.mExpanded = z2;
            qSFooterView.post(new QSFooterView$$ExternalSyntheticLambda0(qSFooterView));
        }
        ((QSPanel) this.mQSPanelController.mView).setVisibility(z5 ? 0 : 4);
    }

    public final void updateShowCollapsedOnKeyguard() {
        boolean z = this.mBypassController.getBypassEnabled() || (this.mTransitioningToFullShade && !this.mInSplitShade);
        if (z != this.mShowCollapsedOnKeyguard) {
            this.mShowCollapsedOnKeyguard = z;
            updateQsState();
            QSAnimator qSAnimator = this.mQSAnimator;
            if (qSAnimator != null) {
                qSAnimator.mShowCollapsedOnKeyguard = z;
                qSAnimator.updateQQSVisibility();
                qSAnimator.setPosition(qSAnimator.mLastPosition);
            }
            if (z || !isKeyguardState()) {
                return;
            }
            setQsExpansion(this.mLastQSExpansion, this.mLastPanelFraction, 0.0f, this.mSquishinessFraction);
        }
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setHasNotifications(boolean z) {
    }

    @Override // com.android.systemui.plugins.qs.QS
    public final void setHeaderClickable(boolean z) {
    }
}
