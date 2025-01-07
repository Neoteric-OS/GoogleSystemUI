package com.android.systemui.mediaprojection.appselector;

import android.app.ActivityOptions;
import android.app.IApplicationThread;
import android.app.ProfilerInfo;
import android.app.WaitResult;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.projection.IMediaProjection;
import android.media.projection.IMediaProjectionManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;
import com.android.internal.app.ChooserActivity;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.ResolverListController;
import com.android.internal.app.chooser.NotSelectableTargetInfo;
import com.android.internal.app.chooser.TargetInfo;
import com.android.internal.widget.RecyclerView;
import com.android.internal.widget.RecyclerViewAccessibilityDelegate;
import com.android.internal.widget.ResolverDrawerLayout;
import com.android.systemui.mediaprojection.MediaProjectionCaptureTarget;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.MediaProjectionServiceHelper;
import com.android.systemui.mediaprojection.appselector.view.MediaProjectionRecentsViewController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.AsyncActivityLauncher;
import com.android.systemui.util.recycler.HorizontalSpacerItemDecoration;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionAppSelectorActivity extends ChooserActivity implements LifecycleOwner {
    public final AsyncActivityLauncher activityLauncher;
    public DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl component;
    public final MediaProjectionAppSelectorComponent$Factory componentFactory;
    public ConfigurationController configurationController;
    public MediaProjectionAppSelectorController controller;
    public final LifecycleRegistry lifecycle;
    public final LifecycleRegistry lifecycleRegistry;
    public final Function1 listControllerFactory;
    public MediaProjectionRecentsViewController recentsViewController;
    public boolean reviewGrantedConsentRequired;
    public boolean taskSelected;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RecyclerViewExpandingAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
        public final ResolverActivity.AppListAccessibilityDelegate delegate;

        public RecyclerViewExpandingAccessibilityDelegate(ResolverDrawerLayout resolverDrawerLayout, RecyclerView recyclerView) {
            super(recyclerView);
            this.delegate = new ResolverActivity.AppListAccessibilityDelegate(resolverDrawerLayout);
        }

        public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            return this.delegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScreenShareType {
        public static final /* synthetic */ ScreenShareType[] $VALUES;

        static {
            ScreenShareType[] screenShareTypeArr = {new ScreenShareType("SystemCast", 0), new ScreenShareType("ShareToApp", 1), new ScreenShareType("ScreenRecord", 2)};
            $VALUES = screenShareTypeArr;
            EnumEntriesKt.enumEntries(screenShareTypeArr);
        }

        public static ScreenShareType valueOf(String str) {
            return (ScreenShareType) Enum.valueOf(ScreenShareType.class, str);
        }

        public static ScreenShareType[] values() {
            return (ScreenShareType[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenShareType.values().length];
            try {
                ScreenShareType[] screenShareTypeArr = ScreenShareType.$VALUES;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                ScreenShareType[] screenShareTypeArr2 = ScreenShareType.$VALUES;
                iArr[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                ScreenShareType[] screenShareTypeArr3 = ScreenShareType.$VALUES;
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MediaProjectionAppSelectorActivity(MediaProjectionAppSelectorComponent$Factory mediaProjectionAppSelectorComponent$Factory, AsyncActivityLauncher asyncActivityLauncher, Function1 function1) {
        this.componentFactory = mediaProjectionAppSelectorComponent$Factory;
        this.activityLauncher = asyncActivityLauncher;
        this.listControllerFactory = function1;
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        this.lifecycle = lifecycleRegistry;
    }

    public final int appliedThemeResId() {
        return R.style.Theme_SystemUI_MediaProjectionAppSelector;
    }

    public final void bind(List list) {
        MediaProjectionRecentsViewController mediaProjectionRecentsViewController = this.recentsViewController;
        if (mediaProjectionRecentsViewController == null) {
            mediaProjectionRecentsViewController = null;
        }
        mediaProjectionRecentsViewController.bind(list);
        if (((ChooserActivity) this).mMultiProfilePagerAdapter.getCount() > 1) {
            return;
        }
        ((ChooserActivity) this).mMultiProfilePagerAdapter.getPersonalListAdapter().notifyDataSetChanged();
    }

    public final AbstractMultiProfilePagerAdapter.EmptyStateProvider createBlockerEmptyStateProvider() {
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = this.component;
        if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl == null) {
            daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = null;
        }
        return (MediaProjectionBlockerEmptyStateProvider) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.mediaProjectionBlockerEmptyStateProvider.get();
    }

    public final ViewGroup createContentPreviewView(ViewGroup viewGroup) {
        MediaProjectionRecentsViewController mediaProjectionRecentsViewController = this.recentsViewController;
        if (mediaProjectionRecentsViewController == null) {
            mediaProjectionRecentsViewController = null;
        }
        MediaProjectionRecentsViewController.Views views = mediaProjectionRecentsViewController.views;
        if (views != null) {
            return views.root;
        }
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.media_projection_recent_tasks, viewGroup, false);
        View requireViewById = viewGroup2.requireViewById(R.id.media_projection_recent_tasks_container);
        mediaProjectionRecentsViewController.setTaskHeightSize(requireViewById);
        View requireViewById2 = viewGroup2.requireViewById(R.id.media_projection_recent_tasks_loader);
        androidx.recyclerview.widget.RecyclerView recyclerView = (androidx.recyclerview.widget.RecyclerView) viewGroup2.requireViewById(R.id.media_projection_recent_tasks_recycler);
        viewGroup.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(0));
        recyclerView.addItemDecoration(new HorizontalSpacerItemDecoration(viewGroup.getResources().getDimensionPixelOffset(R.dimen.media_projection_app_selector_recents_padding)));
        mediaProjectionRecentsViewController.views = new MediaProjectionRecentsViewController.Views(viewGroup2, requireViewById, requireViewById2, recyclerView);
        List list = mediaProjectionRecentsViewController.lastBoundData;
        if (list != null) {
            mediaProjectionRecentsViewController.bind(list);
        }
        return viewGroup2;
    }

    public final ResolverListController createListController(UserHandle userHandle) {
        ResolverListController resolverListController;
        Function1 function1 = this.listControllerFactory;
        return (function1 == null || (resolverListController = (ResolverListController) function1.invoke(userHandle)) == null) ? super.createListController(userHandle) : resolverListController;
    }

    public final AbstractMultiProfilePagerAdapter.MyUserIdProvider createMyUserIdProvider() {
        return new AbstractMultiProfilePagerAdapter.MyUserIdProvider() { // from class: com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity$createMyUserIdProvider$1
            public final int getMyUserId() {
                DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = MediaProjectionAppSelectorActivity.this.component;
                if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl == null) {
                    daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = null;
                }
                return daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUserHandle.getIdentifier();
            }
        };
    }

    public final int getLayoutResource() {
        return R.layout.media_projection_app_selector;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public final ScreenShareType getScreenShareType() {
        String stringExtra;
        if (!getIntent().hasExtra("screen_share_type") || (stringExtra = getIntent().getStringExtra("screen_share_type")) == null) {
            return null;
        }
        try {
            return ScreenShareType.valueOf(stringExtra);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ConfigurationController configurationController = this.configurationController;
        if (configurationController == null) {
            configurationController = null;
        }
        ((ConfigurationControllerImpl) configurationController).onConfigurationChanged(configuration);
    }

    public final void onCreate(Bundle bundle) {
        int i;
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        MediaProjectionAppSelectorComponent$Factory mediaProjectionAppSelectorComponent$Factory = this.componentFactory;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException("MediaProjectionAppSelectorActivity should be launched with extras");
        }
        UserHandle userHandle = (UserHandle) extras.getParcelable("launched_from_user_handle");
        if (userHandle == null) {
            throw new IllegalStateException("MediaProjectionAppSelectorActivity should be provided with launched_from_user_handle extra");
        }
        if (!getIntent().hasExtra("launched_from_host_uid")) {
            throw new IllegalStateException("MediaProjectionAppSelectorActivity should be provided with launched_from_host_uid extra");
        }
        int intExtra = getIntent().getIntExtra("launched_from_host_uid", -1);
        String callingPackage = getCallingPackage();
        boolean z = bundle == null;
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) mediaProjectionAppSelectorComponent$Factory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, userHandle, Integer.valueOf(intExtra), callingPackage, this, this, Boolean.valueOf(z));
        this.component = daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl;
        Iterator it = Collections.singleton((DefaultLifecycleObserver) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.taskPreviewSizeProvider.get()).iterator();
        while (it.hasNext()) {
            this.lifecycle.addObserver((DefaultLifecycleObserver) it.next());
        }
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl2 = this.component;
        if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl2 == null) {
            daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl2 = null;
        }
        this.configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl2.sysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl3 = this.component;
        if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl3 == null) {
            daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl3 = null;
        }
        this.controller = (MediaProjectionAppSelectorController) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl3.mediaProjectionAppSelectorControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl4 = this.component;
        if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl4 == null) {
            daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl4 = null;
        }
        this.recentsViewController = (MediaProjectionRecentsViewController) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl4.mediaProjectionRecentsViewControllerProvider.get();
        Intent intent = getIntent();
        Resources resources = getResources();
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl5 = this.component;
        UserHandle userHandle2 = (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl5 == null ? null : daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl5).hostUserHandle;
        if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl5 == null) {
            daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl5 = null;
        }
        UserHandle m1640$$Nest$mpersonalProfileUserHandle = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1640$$Nest$mpersonalProfileUserHandle(daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl5.sysUIGoogleSysUIComponentImpl);
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent.putExtra("android.intent.extra.INTENT", intent2);
        ScreenShareType screenShareType = getScreenShareType();
        int i2 = screenShareType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[screenShareType.ordinal()];
        if (i2 == -1) {
            i = R.string.screen_share_generic_app_selector_title;
        } else if (i2 == 1) {
            i = R.string.media_projection_entry_share_app_selector_title;
        } else if (i2 == 2) {
            i = R.string.media_projection_entry_cast_app_selector_title;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.string.screenrecord_app_selector_title;
        }
        intent.putExtra("android.intent.extra.TITLE", resources.getString(i));
        intent.putExtra("com.android.internal.app.ResolverActivity.EXTRA_SELECTED_PROFILE", !userHandle2.equals(m1640$$Nest$mpersonalProfileUserHandle) ? 1 : 0);
        this.reviewGrantedConsentRequired = getIntent().getBooleanExtra("extra_media_projection_user_consent_required", false);
        super.onCreate(bundle);
        MediaProjectionAppSelectorController mediaProjectionAppSelectorController = this.controller;
        MediaProjectionAppSelectorController mediaProjectionAppSelectorController2 = mediaProjectionAppSelectorController == null ? null : mediaProjectionAppSelectorController;
        if (mediaProjectionAppSelectorController2.isFirstStart) {
            int i3 = mediaProjectionAppSelectorController2.hostUid;
            MediaProjectionMetricsLogger mediaProjectionMetricsLogger = mediaProjectionAppSelectorController2.logger;
            mediaProjectionMetricsLogger.getClass();
            try {
                mediaProjectionMetricsLogger.service.notifyAppSelectorDisplayed(i3);
            } catch (RemoteException e) {
                Log.e("MediaProjectionMetricsLogger", "Error notifying server of app selector displayed", e);
            }
        }
        BuildersKt.launch$default(mediaProjectionAppSelectorController2.scope, null, null, new MediaProjectionAppSelectorController$init$1(mediaProjectionAppSelectorController2, null), 3);
        ImageView imageView = (ImageView) findViewById(R.id.media_projection_app_selector_icon);
        if (imageView != null) {
            ScreenShareType screenShareType2 = getScreenShareType();
            int i4 = screenShareType2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[screenShareType2.ordinal()];
            int i5 = R.drawable.ic_present_to_all;
            if (i4 != -1 && i4 != 1) {
                if (i4 == 2) {
                    i5 = R.drawable.ic_cast_connected;
                } else {
                    if (i4 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i5 = R.drawable.ic_screenrecord;
                }
            }
            imageView.setImageResource(i5);
            ScreenShareType screenShareType3 = getScreenShareType();
            Integer valueOf = (screenShareType3 != null ? WhenMappings.$EnumSwitchMapping$0[screenShareType3.ordinal()] : -1) == 3 ? Integer.valueOf(R.color.screenrecord_icon_color) : null;
            if (valueOf != null) {
                imageView.setColorFilter(getResources().getColor(valueOf.intValue(), getTheme()));
            }
        }
        ResolverDrawerLayout requireViewById = requireViewById(android.R.id.content_preview_title_layout);
        int count = ((ChooserActivity) this).mMultiProfilePagerAdapter.getCount();
        for (int i6 = 0; i6 < count; i6++) {
            RecyclerView findViewById = ((ChooserActivity) this).mMultiProfilePagerAdapter.getItem(i6).rootView.findViewById(android.R.id.ringtone);
            if (findViewById == null || !(findViewById instanceof RecyclerView)) {
                Log.wtf("MediaProjectionAppSelectorActivity", "MediaProjection only supports RecyclerView");
            } else {
                RecyclerView recyclerView = findViewById;
                recyclerView.setAccessibilityDelegate(new RecyclerViewExpandingAccessibilityDelegate(requireViewById, recyclerView));
            }
        }
    }

    public final void onDestroy() {
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = this.component;
        if (daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl == null) {
            daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = null;
        }
        Iterator it = Collections.singleton((DefaultLifecycleObserver) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.taskPreviewSizeProvider.get()).iterator();
        while (it.hasNext()) {
            this.lifecycle.removeObserver((DefaultLifecycleObserver) it.next());
        }
        if (!this.taskSelected) {
            IMediaProjectionManager iMediaProjectionManager = MediaProjectionServiceHelper.service;
            MediaProjectionServiceHelper.Companion.setReviewedConsentIfNeeded(0, this.reviewGrantedConsentRequired, null);
            if (isFinishing()) {
                MediaProjectionAppSelectorController mediaProjectionAppSelectorController = this.controller;
                if (mediaProjectionAppSelectorController == null) {
                    mediaProjectionAppSelectorController = null;
                }
                MediaProjectionMetricsLogger mediaProjectionMetricsLogger = mediaProjectionAppSelectorController.logger;
                int i = mediaProjectionAppSelectorController.hostUid;
                mediaProjectionMetricsLogger.getClass();
                try {
                    mediaProjectionMetricsLogger.service.notifyPermissionRequestCancelled(i);
                } catch (RemoteException e) {
                    Log.e("MediaProjectionMetricsLogger", "Error notifying server of projection cancelled", e);
                }
            }
        }
        this.activityLauncher.pendingCallback = null;
        MediaProjectionAppSelectorController mediaProjectionAppSelectorController2 = this.controller;
        if (mediaProjectionAppSelectorController2 == null) {
            mediaProjectionAppSelectorController2 = null;
        }
        CoroutineScopeKt.cancel(mediaProjectionAppSelectorController2.scope, null);
        super.onDestroy();
    }

    public final void onPause() {
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        super.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    public final void onStart() {
        super.onStart();
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    public final void onStop() {
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        super.onStop();
    }

    public final void returnSelectedApp(ActivityOptions.LaunchCookie launchCookie, int i) {
        this.taskSelected = true;
        if (getIntent().hasExtra("capture_region_result_receiver")) {
            ResultReceiver resultReceiver = (ResultReceiver) getIntent().getParcelableExtra("capture_region_result_receiver", ResultReceiver.class);
            MediaProjectionCaptureTarget mediaProjectionCaptureTarget = new MediaProjectionCaptureTarget(launchCookie, i);
            Bundle bundle = new Bundle();
            bundle.putParcelable("capture_region", mediaProjectionCaptureTarget);
            resultReceiver.send(-1, bundle);
        } else {
            IMediaProjection asInterface = IMediaProjection.Stub.asInterface(getIntent().getIBinderExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION"));
            asInterface.setLaunchCookie(launchCookie);
            asInterface.setTaskId(i);
            Intent intent = new Intent();
            intent.putExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION", asInterface.asBinder());
            setResult(-1, intent);
            setForceSendResultForMediaProjection();
            IMediaProjectionManager iMediaProjectionManager = MediaProjectionServiceHelper.service;
            MediaProjectionServiceHelper.Companion.setReviewedConsentIfNeeded(2, this.reviewGrantedConsentRequired, asInterface);
        }
        finish();
    }

    public final boolean shouldGetOnlyDefaultActivities() {
        return false;
    }

    public final boolean shouldShowContentPreview() {
        if (((ChooserActivity) this).mMultiProfilePagerAdapter.getCount() > 1) {
            return true;
        }
        MediaProjectionRecentsViewController mediaProjectionRecentsViewController = this.recentsViewController;
        if (mediaProjectionRecentsViewController == null) {
            mediaProjectionRecentsViewController = null;
        }
        return mediaProjectionRecentsViewController.lastBoundData != null ? !r3.isEmpty() : false;
    }

    public final boolean shouldShowServiceTargets() {
        return false;
    }

    public final boolean shouldShowStickyContentPreviewWhenEmpty() {
        return shouldShowContentPreview();
    }

    public final void startSelected(int i, boolean z, boolean z2) {
        TargetInfo targetInfoForPosition = ((ChooserActivity) this).mChooserMultiProfilePagerAdapter.getActiveListAdapter().targetInfoForPosition(i, z2);
        if (targetInfoForPosition == null || (targetInfoForPosition instanceof NotSelectableTargetInfo)) {
            return;
        }
        final Intent intent = new Intent(targetInfoForPosition.getResolvedIntent());
        intent.setFlags(intent.getFlags() | 268435456);
        intent.setFlags(intent.getFlags() & (-33554433));
        final ActivityOptions.LaunchCookie launchCookie = new ActivityOptions.LaunchCookie("media_projection_launch_token");
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchCookie(launchCookie);
        final UserHandle userHandle = ((ChooserActivity) this).mMultiProfilePagerAdapter.getActiveListAdapter().getUserHandle();
        final AsyncActivityLauncher asyncActivityLauncher = this.activityLauncher;
        Intrinsics.checkNotNull(userHandle);
        final Bundle bundle = makeBasic.toBundle();
        Function1 function1 = new Function1() { // from class: com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity$startSelected$activityStarted$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                MediaProjectionAppSelectorActivity.this.returnSelectedApp(launchCookie, -1);
                return Unit.INSTANCE;
            }
        };
        if (asyncActivityLauncher.pendingCallback == null) {
            asyncActivityLauncher.pendingCallback = function1;
            intent.setFlags(intent.getFlags() | 268435456);
            asyncActivityLauncher.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.util.AsyncActivityLauncher$startActivityAsUser$1
                @Override // java.lang.Runnable
                public final void run() {
                    AsyncActivityLauncher asyncActivityLauncher2 = AsyncActivityLauncher.this;
                    final WaitResult startActivityAndWait = asyncActivityLauncher2.activityTaskManager.startActivityAndWait((IApplicationThread) null, asyncActivityLauncher2.context.getPackageName(), AsyncActivityLauncher.this.context.getAttributionTag(), intent, (String) null, (IBinder) null, (String) null, 0, 0, (ProfilerInfo) null, bundle, userHandle.getIdentifier());
                    final AsyncActivityLauncher asyncActivityLauncher3 = AsyncActivityLauncher.this;
                    asyncActivityLauncher3.mainExecutor.execute(new Runnable() { // from class: com.android.systemui.util.AsyncActivityLauncher$startActivityAsUser$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Function1 function12 = AsyncActivityLauncher.this.pendingCallback;
                            if (function12 != null) {
                                function12.invoke(startActivityAndWait);
                            }
                        }
                    });
                }
            });
        }
        targetInfoForPosition.isSuspended();
    }

    public MediaProjectionAppSelectorActivity(MediaProjectionAppSelectorComponent$Factory mediaProjectionAppSelectorComponent$Factory, AsyncActivityLauncher asyncActivityLauncher) {
        this(mediaProjectionAppSelectorComponent$Factory, asyncActivityLauncher, null);
    }

    public final void onActivityStarted(TargetInfo targetInfo) {
    }
}
