package com.android.systemui.dreams;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ServiceLifecycleDispatcher;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.ViewModelProviderImpl;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.policy.PhoneWindow;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.ambient.touch.dagger.AmbientTouchComponent$Factory;
import com.android.systemui.ambient.touch.scrim.ScrimManager;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.log.CommunalUiEvent;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.complication.ComplicationCollectionLiveData;
import com.android.systemui.complication.ComplicationCollectionViewModel;
import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.complication.ComplicationLayoutEngine;
import com.android.systemui.complication.ComplicationViewModelTransformer;
import com.android.systemui.complication.dagger.ComplicationComponent$Factory;
import com.android.systemui.complication.dagger.ComplicationModule$$ExternalSyntheticLambda0;
import com.android.systemui.complication.dagger.DaggerViewModelProviderFactory;
import com.android.systemui.dreams.DreamOverlayService;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.complication.HideComplicationTouchHandler;
import com.android.systemui.dreams.dagger.DreamModule$$ExternalSyntheticLambda0;
import com.android.systemui.dreams.dagger.DreamOverlayComponent$Factory;
import com.android.systemui.dreams.touch.CommunalTouchHandler;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor;
import com.android.systemui.navigationbar.gestural.domain.TaskMatcher;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.flow.ReadonlySharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class DreamOverlayService extends android.service.dreams.DreamOverlayService implements LifecycleOwner {
    public static final boolean DEBUG = Log.isLoggable("DreamOverlayService", 3);
    public static final TaskMatcher.TopActivityType DREAM_TYPE_MATCHER = new TaskMatcher.TopActivityType();
    public final AmbientTouchComponent$Factory mAmbientTouchComponentFactory;
    public boolean mBouncerShowing;
    public final AnonymousClass2 mBouncerShowingConsumer;
    public boolean mCommunalAvailable;
    public final CommunalInteractor mCommunalInteractor;
    public boolean mCommunalVisible;
    public final AnonymousClass2 mCommunalVisibleConsumer;
    public final ComplicationComponent$Factory mComplicationComponentFactory;
    public final Context mContext;
    public boolean mDestroyed;
    public final ServiceLifecycleDispatcher mDispatcher;
    public final com.android.systemui.dreams.complication.dagger.ComplicationComponent$Factory mDreamComplicationComponentFactory;
    public final DreamOverlayCallbackController mDreamOverlayCallbackController;
    public final DreamOverlayComponent$Factory mDreamOverlayComponentFactory;
    public DreamOverlayContainerViewController mDreamOverlayContainerViewController;
    public final DelayableExecutor mExecutor;
    public final ArrayList mFlows;
    public final GestureInteractor mGestureInteractor;
    public final ComponentName mHomeControlPanelDreamComponent;
    public final DreamOverlayService$$ExternalSyntheticLambda0 mIsCommunalAvailableCallback;
    public final KeyguardUpdateMonitorCallback mKeyguardCallback;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final DreamOverlayLifecycleOwner mLifecycleOwner;
    public final LifecycleRegistry mLifecycleRegistry;
    public final ComponentName mLowLightDreamComponent;
    public final ResetHandler mResetHandler;
    public final ScrimManager mScrimManager;
    public boolean mShadeExpanded;
    public boolean mStarted;
    public final DreamOverlayStateController mStateController;
    public final SystemDialogsCloser mSystemDialogsCloser;
    public final TouchInsetManager mTouchInsetManager;
    public TouchMonitor mTouchMonitor;
    public final UiEventLogger mUiEventLogger;
    public Window mWindow;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public final String mWindowTitle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.dreams.DreamOverlayService$1, reason: invalid class name */
    class AnonymousClass1 extends KeyguardUpdateMonitorCallback {
        public AnonymousClass1() {
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onShadeExpandedChanged(final boolean z) {
            ((ExecutorImpl) DreamOverlayService.this.mExecutor).execute(new Runnable() { // from class: com.android.systemui.dreams.DreamOverlayService$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DreamOverlayService.AnonymousClass1 anonymousClass1 = DreamOverlayService.AnonymousClass1.this;
                    boolean z2 = z;
                    DreamOverlayService dreamOverlayService = DreamOverlayService.this;
                    if (dreamOverlayService.mShadeExpanded == z2) {
                        return;
                    }
                    dreamOverlayService.mShadeExpanded = z2;
                    dreamOverlayService.updateLifecycleStateLocked();
                    dreamOverlayService.updateGestureBlockingLocked();
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.dreams.DreamOverlayService$2, reason: invalid class name */
    public final class AnonymousClass2 implements Consumer {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ DreamOverlayService this$0;

        public /* synthetic */ AnonymousClass2(DreamOverlayService dreamOverlayService, int i) {
            this.$r8$classId = i;
            this.this$0 = dreamOverlayService;
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    ((ExecutorImpl) this.this$0.mExecutor).execute(new DreamOverlayService$2$$ExternalSyntheticLambda0(this, (Boolean) obj));
                    break;
                default:
                    ((ExecutorImpl) this.this$0.mExecutor).execute(new DreamOverlayService$2$$ExternalSyntheticLambda0(this, (Boolean) obj, (byte) 0));
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum DreamOverlayEvent implements UiEventLogger.UiEventEnum {
        DREAM_OVERLAY_ENTER_START(989),
        DREAM_OVERLAY_COMPLETE_START(990);

        private final int mId;

        DreamOverlayEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResetHandler {
        public final ArrayList mPendingCallbacks = new ArrayList();
        public final DreamOverlayStateController.Callback mStateCallback = new DreamOverlayStateController.Callback() { // from class: com.android.systemui.dreams.DreamOverlayService.ResetHandler.1
            @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
            public final void onStateChanged() {
                ResetHandler.this.process(true);
            }
        };

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public interface Callback {
            void onComplete();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Info extends Record {
            public final Callback callback;
            public final String source;

            public Info(Callback callback, String str) {
                this.callback = callback;
                this.source = str;
            }

            @Override // java.lang.Record
            public final boolean equals(Object obj) {
                if (obj == null || Info.class != obj.getClass()) {
                    return false;
                }
                Info info = (Info) obj;
                return Arrays.equals(new Object[]{this.callback, this.source}, new Object[]{info.callback, info.source});
            }

            @Override // java.lang.Record
            public final int hashCode() {
                return Info.class.hashCode() + (Arrays.hashCode(new Object[]{this.callback, this.source}) * 31);
            }

            @Override // java.lang.Record
            public final String toString() {
                Object[] objArr = {this.callback, this.source};
                String[] split = "callback;source".length() == 0 ? new String[0] : "callback;source".split(";");
                StringBuilder sb = new StringBuilder();
                sb.append(Info.class.getSimpleName());
                sb.append("[");
                for (int i = 0; i < split.length; i++) {
                    sb.append(split[i]);
                    sb.append("=");
                    sb.append(objArr[i]);
                    if (i != split.length - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("]");
                return sb.toString();
            }
        }

        public ResetHandler() {
        }

        public final void process(boolean z) {
            DreamOverlayService dreamOverlayService;
            Window window;
            View view;
            ViewGroup viewGroup;
            while (true) {
                dreamOverlayService = DreamOverlayService.this;
                if (dreamOverlayService.mStateController.containsState(8) || this.mPendingCallbacks.isEmpty()) {
                    break;
                }
                Info info = (Info) this.mPendingCallbacks.removeFirst();
                boolean z2 = DreamOverlayService.DEBUG;
                DreamOverlayContainerViewController dreamOverlayContainerViewController = dreamOverlayService.mDreamOverlayContainerViewController;
                if (dreamOverlayContainerViewController != null && (view = dreamOverlayContainerViewController.mView) != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                    Log.w("DreamOverlayService", "Removing dream overlay container view parent!");
                    viewGroup.removeView(view);
                }
                if (dreamOverlayService.mStarted && (window = dreamOverlayService.mWindow) != null) {
                    try {
                        window.clearContentView();
                        dreamOverlayService.mWindowManager.removeView(dreamOverlayService.mWindow.getDecorView());
                    } catch (IllegalArgumentException e) {
                        Log.e("DreamOverlayService", "Error removing decor view when resetting overlay", e);
                    }
                }
                dreamOverlayService.mStateController.setOverlayActive(false);
                dreamOverlayService.mStateController.setLowLightActive(false);
                DreamOverlayStateController dreamOverlayStateController = dreamOverlayService.mStateController;
                dreamOverlayStateController.getClass();
                dreamOverlayStateController.modifyState(1, 4);
                dreamOverlayService.mDreamOverlayCallbackController.onWakeUp();
                DreamOverlayContainerViewController dreamOverlayContainerViewController2 = dreamOverlayService.mDreamOverlayContainerViewController;
                if (dreamOverlayContainerViewController2 != null) {
                    dreamOverlayContainerViewController2.mStateController.removeCallback(dreamOverlayContainerViewController2.mDreamOverlayStateCallback);
                    AmbientStatusBarViewController ambientStatusBarViewController = dreamOverlayContainerViewController2.mStatusBarViewController;
                    ambientStatusBarViewController.mPrivacyItemController.removeCallback(ambientStatusBarViewController.mPrivacyItemControllerCallback);
                    ambientStatusBarViewController.mStatusBarWindowStateController.listeners.remove(ambientStatusBarViewController.mStatusBarWindowStateListener);
                    ambientStatusBarViewController.mView.removeOnAttachStateChangeListener(ambientStatusBarViewController.mOnAttachStateListener);
                    ComplicationHostViewController complicationHostViewController = dreamOverlayContainerViewController2.mComplicationHostViewController;
                    complicationHostViewController.mView.removeOnAttachStateChangeListener(complicationHostViewController.mOnAttachStateListener);
                    RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = dreamOverlayContainerViewController2.mDreamOverlayAnimationsController.mLifecycleFlowHandle;
                    if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
                        repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
                    }
                    dreamOverlayContainerViewController2.mLowLightTransitionCoordinator.getClass();
                    dreamOverlayContainerViewController2.mView.removeOnAttachStateChangeListener(dreamOverlayContainerViewController2.mOnAttachStateListener);
                    dreamOverlayService.mDreamOverlayContainerViewController = null;
                }
                TouchMonitor touchMonitor = dreamOverlayService.mTouchMonitor;
                if (touchMonitor != null) {
                    touchMonitor.destroy();
                    dreamOverlayService.mTouchMonitor = null;
                }
                dreamOverlayService.mWindow = null;
                GestureInteractor gestureInteractor = dreamOverlayService.mGestureInteractor;
                TaskMatcher.TopActivityType topActivityType = DreamOverlayService.DREAM_TYPE_MATCHER;
                GestureInteractor.Scope scope = GestureInteractor.Scope.Local;
                gestureInteractor.removeGestureBlockedMatcher(topActivityType);
                dreamOverlayService.mStarted = false;
                info.callback.onComplete();
                if (z) {
                    Log.d("DreamOverlayService", "reset overlay (delayed) for ".concat(info.source));
                }
            }
            if (this.mPendingCallbacks.isEmpty()) {
                dreamOverlayService.mStateController.removeCallback(this.mStateCallback);
            }
        }

        public final boolean reset(Callback callback, String str) {
            if (this.mPendingCallbacks.isEmpty()) {
                DreamOverlayService.this.mStateController.addCallback(this.mStateCallback);
            }
            Info info = new Info(callback, str);
            this.mPendingCallbacks.add(info);
            process(false);
            boolean contains = this.mPendingCallbacks.contains(info);
            boolean z = !contains;
            if (contains) {
                Log.d("DreamOverlayService", "delayed resetting from: ".concat(str));
            }
            return z;
        }
    }

    public DreamOverlayService(Context context, DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner, DelayableExecutor delayableExecutor, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, ComplicationComponent$Factory complicationComponent$Factory, com.android.systemui.dreams.complication.dagger.ComplicationComponent$Factory complicationComponent$Factory2, DreamOverlayComponent$Factory dreamOverlayComponent$Factory, AmbientTouchComponent$Factory ambientTouchComponent$Factory, DreamOverlayStateController dreamOverlayStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, ScrimManager scrimManager, CommunalInteractor communalInteractor, SystemDialogsCloser systemDialogsCloser, UiEventLogger uiEventLogger, TouchInsetManager touchInsetManager, ComponentName componentName, ComponentName componentName2, DreamOverlayCallbackController dreamOverlayCallbackController, KeyguardInteractor keyguardInteractor, GestureInteractor gestureInteractor, String str) {
        super(delayableExecutor);
        this.mStarted = false;
        this.mDestroyed = false;
        this.mShadeExpanded = false;
        this.mCommunalVisible = false;
        this.mBouncerShowing = false;
        ArrayList arrayList = new ArrayList();
        this.mFlows = arrayList;
        ServiceLifecycleDispatcher serviceLifecycleDispatcher = new ServiceLifecycleDispatcher(this);
        this.mDispatcher = serviceLifecycleDispatcher;
        Consumer consumer = new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayService$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DreamOverlayService dreamOverlayService = DreamOverlayService.this;
                boolean z = DreamOverlayService.DEBUG;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                dreamOverlayService.mCommunalAvailable = booleanValue;
                if (dreamOverlayService.mStarted) {
                    dreamOverlayService.redirectWake(booleanValue);
                }
            }
        };
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mKeyguardCallback = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this, 0);
        AnonymousClass2 anonymousClass22 = new AnonymousClass2(this, 1);
        this.mResetHandler = new ResetHandler();
        this.mContext = context;
        this.mExecutor = delayableExecutor;
        this.mWindowManager = viewCaptureAwareWindowManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mScrimManager = scrimManager;
        this.mLowLightDreamComponent = componentName;
        this.mHomeControlPanelDreamComponent = componentName2;
        keyguardUpdateMonitor.registerCallback(anonymousClass1);
        this.mStateController = dreamOverlayStateController;
        this.mUiEventLogger = uiEventLogger;
        this.mComplicationComponentFactory = complicationComponent$Factory;
        this.mDreamComplicationComponentFactory = complicationComponent$Factory2;
        this.mDreamOverlayCallbackController = dreamOverlayCallbackController;
        this.mWindowTitle = str;
        this.mCommunalInteractor = communalInteractor;
        this.mSystemDialogsCloser = systemDialogsCloser;
        this.mGestureInteractor = gestureInteractor;
        this.mDreamOverlayComponentFactory = dreamOverlayComponent$Factory;
        this.mAmbientTouchComponentFactory = ambientTouchComponent$Factory;
        this.mTouchInsetManager = touchInsetManager;
        this.mLifecycleOwner = dreamOverlayLifecycleOwner;
        this.mLifecycleRegistry = dreamOverlayLifecycleOwner.registry;
        ((ExecutorImpl) delayableExecutor).execute(new DreamOverlayService$$ExternalSyntheticLambda1(this, 0));
        ReadonlySharedFlow readonlySharedFlow = communalInteractor.isCommunalAvailable;
        LifecycleRegistry lifecycleRegistry = serviceLifecycleDispatcher.registry;
        arrayList.add(JavaAdapterKt.collectFlow(lifecycleRegistry, readonlySharedFlow, consumer));
        arrayList.add(JavaAdapterKt.collectFlow(lifecycleRegistry, communalInteractor.isCommunalVisible, anonymousClass2));
        arrayList.add(JavaAdapterKt.collectFlow(lifecycleRegistry, keyguardInteractor.primaryBouncerShowing, anonymousClass22));
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mDispatcher.registry;
    }

    public final void onComeToFront() {
        DreamOverlayContainerViewController dreamOverlayContainerViewController = this.mDreamOverlayContainerViewController;
        if (dreamOverlayContainerViewController != null && dreamOverlayContainerViewController.mAnyBouncerShowing) {
            this.mScrimManager.mCurrentController.expand(new ShadeExpansionChangeEvent(1.0f, false, true));
        }
        ((DreamModule$$ExternalSyntheticLambda0) this.mSystemDialogsCloser).f$0.closeSystemDialogs();
        CommunalInteractor communalInteractor = this.mCommunalInteractor;
        CommunalSceneInteractor.changeScene$default(communalInteractor.communalSceneInteractor, CommunalScenes.Blank, "dream come to front", null, 8);
    }

    public final void onCreate() {
        this.mDispatcher.postDispatchRunnable(Lifecycle.Event.ON_CREATE);
        super.onCreate();
    }

    public final void onDestroy() {
        this.mKeyguardUpdateMonitor.removeCallback(this.mKeyguardCallback);
        Iterator it = this.mFlows.iterator();
        while (it.hasNext()) {
            ((Job) it.next()).cancel(new CancellationException());
        }
        this.mFlows.clear();
        ((ExecutorImpl) this.mExecutor).execute(new DreamOverlayService$$ExternalSyntheticLambda1(this, 1));
        ServiceLifecycleDispatcher serviceLifecycleDispatcher = this.mDispatcher;
        serviceLifecycleDispatcher.postDispatchRunnable(Lifecycle.Event.ON_STOP);
        serviceLifecycleDispatcher.postDispatchRunnable(Lifecycle.Event.ON_DESTROY);
        super.onDestroy();
    }

    public final void onEndDream() {
        this.mResetHandler.reset(new DreamOverlayService$$ExternalSyntheticLambda2(), "ending dream");
    }

    public final void onStart(Intent intent, int i) {
        this.mDispatcher.postDispatchRunnable(Lifecycle.Event.ON_START);
        super.onStart(intent, i);
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory, java.lang.Object] */
    public final void onStartDream(final WindowManager.LayoutParams layoutParams) {
        View view;
        ViewGroup viewGroup;
        ComplicationComponent$Factory complicationComponent$Factory = this.mComplicationComponentFactory;
        DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner = this.mLifecycleOwner;
        DreamOverlayService$$ExternalSyntheticLambda2 dreamOverlayService$$ExternalSyntheticLambda2 = new DreamOverlayService$$ExternalSyntheticLambda2();
        ViewModelStore viewModelStore = new ViewModelStore();
        TouchInsetManager touchInsetManager = this.mTouchInsetManager;
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) complicationComponent$Factory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
        dreamOverlayLifecycleOwner.getClass();
        touchInsetManager.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, dreamOverlayLifecycleOwner, dreamOverlayService$$ExternalSyntheticLambda2, viewModelStore, touchInsetManager);
        Object obj = daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl.sysUIGoogleSysUIComponentImpl;
        com.android.systemui.dreams.complication.dagger.ComplicationComponent$Factory complicationComponent$Factory2 = this.mDreamComplicationComponentFactory;
        ComplicationLayoutEngine complicationLayoutEngine = (ComplicationLayoutEngine) ((Provider) obj).get();
        TouchInsetManager touchInsetManager2 = this.mTouchInsetManager;
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2 = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) complicationComponent$Factory2;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2.getClass();
        complicationLayoutEngine.getClass();
        touchInsetManager2.getClass();
        DreamOverlayComponent$Factory dreamOverlayComponent$Factory = this.mDreamOverlayComponentFactory;
        DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner2 = this.mLifecycleOwner;
        ConstraintLayout constraintLayout = (ConstraintLayout) ((Provider) daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl.loggingName).get();
        ComplicationLayoutEngine complicationLayoutEngine2 = (ComplicationLayoutEngine) ((Provider) obj).get();
        DreamOverlayStateController dreamOverlayStateController = (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get();
        ViewModelProviderImpl viewModelProviderImpl = new ViewModelProviderImpl(viewModelStore, new DaggerViewModelProviderFactory(new ComplicationModule$$ExternalSyntheticLambda0(new ComplicationCollectionViewModel(new ComplicationCollectionLiveData((DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get()), new ComplicationViewModelTransformer(new Object() { // from class: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory
        })))), CreationExtras.Empty.INSTANCE);
        ClassReference orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ComplicationCollectionViewModel.class);
        String qualifiedName = orCreateKotlinClass.getQualifiedName();
        if (qualifiedName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        ComplicationHostViewController complicationHostViewController = new ComplicationHostViewController(constraintLayout, complicationLayoutEngine2, dreamOverlayStateController, dreamOverlayLifecycleOwner, (ComplicationCollectionViewModel) viewModelProviderImpl.getViewModel$lifecycle_viewmodel_release(orCreateKotlinClass, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(qualifiedName)), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get());
        TouchInsetManager touchInsetManager3 = this.mTouchInsetManager;
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory3 = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) dreamOverlayComponent$Factory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory3.getClass();
        dreamOverlayLifecycleOwner2.getClass();
        touchInsetManager3.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory3.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory3.sysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, dreamOverlayLifecycleOwner2, complicationHostViewController, touchInsetManager3);
        AmbientTouchComponent$Factory ambientTouchComponent$Factory = this.mAmbientTouchComponentFactory;
        DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner3 = this.mLifecycleOwner;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2.sysUIGoogleGlobalRootComponentImpl;
        int integer = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3).getInteger(R.integer.complicationRestoreMs);
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        int integer2 = resources.getInteger(R.integer.complicationFadeOutDelayMs);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2.sysUIGoogleSysUIComponentImpl;
        HashSet hashSet = new HashSet(Arrays.asList(new HideComplicationTouchHandler(complicationLayoutEngine, integer, integer2, touchInsetManager2, (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.statusBarKeyguardViewManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideMainDelayableExecutorProvider.get(), (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.dreamOverlayStateControllerProvider.get()), new CommunalTouchHandler((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.optionalOfCentralSurfacesProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2).getDimensionPixelSize(R.dimen.communal_gesture_initiation_width), (CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.communalInteractorProvider.get(), (ConfigurationInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.configurationInteractorProvider.get(), (Lifecycle) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesLifecycleProvider.get())));
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory4 = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) ambientTouchComponent$Factory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory4.getClass();
        dreamOverlayLifecycleOwner3.getClass();
        DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl2 = new DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory4.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory4.sysUIGoogleSysUIComponentImpl, dreamOverlayLifecycleOwner3, hashSet, "DreamOverlayService");
        this.mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        this.mUiEventLogger.log(DreamOverlayEvent.DREAM_OVERLAY_ENTER_START);
        if (this.mDestroyed) {
            return;
        }
        if (!this.mStarted || this.mResetHandler.reset(new ResetHandler.Callback() { // from class: com.android.systemui.dreams.DreamOverlayService$$ExternalSyntheticLambda3
            @Override // com.android.systemui.dreams.DreamOverlayService.ResetHandler.Callback
            public final void onComplete() {
                WindowManager.LayoutParams layoutParams2 = layoutParams;
                boolean z = DreamOverlayService.DEBUG;
                DreamOverlayService.this.onStartDream(layoutParams2);
            }
        }, "starting with dream already started")) {
            this.mDreamOverlayContainerViewController = (DreamOverlayContainerViewController) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.dreamOverlayContainerViewControllerProvider.get();
            TouchMonitor touchMonitor = daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl2.getTouchMonitor();
            this.mTouchMonitor = touchMonitor;
            touchMonitor.init();
            final DreamOverlayStateController dreamOverlayStateController2 = this.mStateController;
            final boolean shouldShowComplications = shouldShowComplications();
            dreamOverlayStateController2.mExecutor.execute(new Runnable() { // from class: com.android.systemui.dreams.DreamOverlayStateController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DreamOverlayStateController dreamOverlayStateController3 = DreamOverlayStateController.this;
                    boolean z = shouldShowComplications;
                    DreamLogger dreamLogger = dreamOverlayStateController3.mLogger;
                    dreamLogger.getClass();
                    DreamLogger$logShouldShowComplications$1 dreamLogger$logShouldShowComplications$1 = new Function1() { // from class: com.android.systemui.dreams.DreamLogger$logShouldShowComplications$1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Dream overlay should show complications: ", ((LogMessage) obj2).getBool1());
                        }
                    };
                    LogMessage obtain = dreamLogger.getBuffer().obtain(dreamLogger.getTag(), LogLevel.DEBUG, dreamLogger$logShouldShowComplications$1, null);
                    obtain.setBool1(z);
                    dreamLogger.getBuffer().commit(obtain);
                    dreamOverlayStateController3.notifyCallbacksLocked(new DreamOverlayStateController$$ExternalSyntheticLambda0(2));
                }
            });
            PhoneWindow phoneWindow = new PhoneWindow(this.mContext);
            this.mWindow = phoneWindow;
            phoneWindow.setTitle(this.mWindowTitle);
            this.mWindow.setAttributes(layoutParams);
            this.mWindow.setWindowManager(null, layoutParams.token, "DreamOverlay", true);
            boolean z = false;
            this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mWindow.clearFlags(Integer.MIN_VALUE);
            this.mWindow.addFlags(8);
            this.mWindow.addPrivateFlags(16);
            this.mWindow.requestFeature(1);
            this.mWindow.getDecorView().getWindowInsetsController().hide(WindowInsets.Type.systemBars());
            this.mWindow.setDecorFitsSystemWindows(false);
            if (DEBUG) {
                Log.d("DreamOverlayService", "adding overlay window to dream");
            }
            this.mDreamOverlayContainerViewController.init$9();
            DreamOverlayContainerViewController dreamOverlayContainerViewController = this.mDreamOverlayContainerViewController;
            if (dreamOverlayContainerViewController != null && (view = dreamOverlayContainerViewController.mView) != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                Log.w("DreamOverlayService", "Removing dream overlay container view parent!");
                viewGroup.removeView(view);
            }
            this.mWindow.setContentView(this.mDreamOverlayContainerViewController.mView);
            try {
                this.mWindowManager.addView(this.mWindow.getDecorView(), this.mWindow.getAttributes());
                updateLifecycleStateLocked();
                this.mStateController.setOverlayActive(true);
                ComponentName dreamComponent = getDreamComponent();
                this.mStateController.setLowLightActive(dreamComponent != null && dreamComponent.equals(this.mLowLightDreamComponent));
                DreamOverlayStateController dreamOverlayStateController3 = this.mStateController;
                if (dreamComponent != null && dreamComponent.equals(this.mHomeControlPanelDreamComponent)) {
                    z = true;
                }
                dreamOverlayStateController3.getClass();
                dreamOverlayStateController3.modifyState(z ? 2 : 1, 64);
                this.mUiEventLogger.log(DreamOverlayEvent.DREAM_OVERLAY_COMPLETE_START);
                DreamOverlayCallbackController dreamOverlayCallbackController = this.mDreamOverlayCallbackController;
                dreamOverlayCallbackController.isDreaming = true;
                Iterator it = dreamOverlayCallbackController.callbacks.iterator();
                while (it.hasNext()) {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ((KeyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1) it.next()).$$this$conflatedCallbackFlow)._channel.mo1790trySendJP2dKIU(Boolean.TRUE);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("KeyguardRepositoryImpl", "Failed to send updated isDreamingWithOverlay - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
                this.mStarted = true;
                redirectWake(this.mCommunalAvailable);
                updateGestureBlockingLocked();
            } catch (WindowManager.BadTokenException e) {
                Log.e("DreamOverlayService", "Dream activity window invalid: " + layoutParams.packageName, e);
                this.mResetHandler.reset(new DreamOverlayService$$ExternalSyntheticLambda2(), "couldn't add window while starting");
            }
        }
    }

    public final void onWakeRequested() {
        this.mUiEventLogger.log(CommunalUiEvent.DREAM_TO_COMMUNAL_HUB_DREAM_AWAKE_START);
        CommunalInteractor communalInteractor = this.mCommunalInteractor;
        CommunalSceneInteractor.changeScene$default(communalInteractor.communalSceneInteractor, CommunalScenes.Communal, "dream wake requested", null, 8);
    }

    public final void onWakeUp() {
        if (this.mDreamOverlayContainerViewController != null) {
            this.mDreamOverlayCallbackController.onWakeUp();
            DreamOverlayContainerViewController dreamOverlayContainerViewController = this.mDreamOverlayContainerViewController;
            if (dreamOverlayContainerViewController.mWakingUpFromSwipe) {
                return;
            }
            dreamOverlayContainerViewController.mDreamOverlayAnimationsController.cancelAnimations();
        }
    }

    public final void updateGestureBlockingLocked() {
        if (this.mStarted && !this.mShadeExpanded && !this.mBouncerShowing && !isDreamInPreviewMode()) {
            this.mGestureInteractor.addGestureBlockedMatcher(DREAM_TYPE_MATCHER, GestureInteractor.Scope.Global);
            return;
        }
        GestureInteractor gestureInteractor = this.mGestureInteractor;
        TaskMatcher.TopActivityType topActivityType = DREAM_TYPE_MATCHER;
        GestureInteractor.Scope scope = GestureInteractor.Scope.Local;
        gestureInteractor.removeGestureBlockedMatcher(topActivityType);
    }

    public final void updateLifecycleStateLocked() {
        LifecycleRegistry lifecycleRegistry = this.mLifecycleRegistry;
        Lifecycle.State state = lifecycleRegistry.state;
        Lifecycle.State state2 = Lifecycle.State.RESUMED;
        Lifecycle.State state3 = Lifecycle.State.STARTED;
        if (state == state2 || state == state3) {
            if (this.mShadeExpanded || this.mCommunalVisible || this.mBouncerShowing) {
                state2 = state3;
            }
            lifecycleRegistry.setCurrentState(state2);
        }
    }
}
