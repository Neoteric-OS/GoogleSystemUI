package com.android.systemui.controls.management;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.panels.AuthorizedPanelsRepository;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ControlsProviderSelectorActivity extends ComponentActivity {
    public final AuthorizedPanelsRepository authorizedPanelsRepository;
    public final Executor backExecutor;
    public boolean backShouldExit;
    public final ControlsController controlsController;
    public SystemUIDialog dialog;
    public final Executor executor;
    public final ControlsListingController listingController;
    public final PanelConfirmationDialogFactory panelConfirmationDialogFactory;
    public RecyclerView recyclerView;
    public final UserTracker userTracker;
    public final ControlsProviderSelectorActivity$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.controls.management.ControlsProviderSelectorActivity$userTrackerCallback$1
        public final int startingUser;

        {
            this.startingUser = ((ControlsListingControllerImpl) ControlsProviderSelectorActivity.this.listingController).currentUserId;
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            if (i != this.startingUser) {
                ControlsProviderSelectorActivity controlsProviderSelectorActivity = ControlsProviderSelectorActivity.this;
                ((UserTrackerImpl) controlsProviderSelectorActivity.userTracker).removeCallback(this);
                controlsProviderSelectorActivity.finish();
            }
        }
    };
    public final ControlsProviderSelectorActivity$mOnBackInvokedCallback$1 mOnBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.controls.management.ControlsProviderSelectorActivity$mOnBackInvokedCallback$1
        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            ControlsProviderSelectorActivity.this.onBackPressed();
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.controls.management.ControlsProviderSelectorActivity$userTrackerCallback$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.controls.management.ControlsProviderSelectorActivity$mOnBackInvokedCallback$1] */
    public ControlsProviderSelectorActivity(Executor executor, Executor executor2, ControlsListingController controlsListingController, ControlsController controlsController, UserTracker userTracker, AuthorizedPanelsRepository authorizedPanelsRepository, PanelConfirmationDialogFactory panelConfirmationDialogFactory) {
        this.executor = executor;
        this.backExecutor = executor2;
        this.listingController = controlsListingController;
        this.controlsController = controlsController;
        this.userTracker = userTracker;
        this.authorizedPanelsRepository = authorizedPanelsRepository;
        this.panelConfirmationDialogFactory = panelConfirmationDialogFactory;
    }

    public void animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        ControlsAnimations.exitAnimation((ViewGroup) requireViewById(R.id.controls_management_root), new Runnable() { // from class: com.android.systemui.controls.management.ControlsProviderSelectorActivity$animateExitAndFinish$1
            @Override // java.lang.Runnable
            public final void run() {
                ControlsProviderSelectorActivity.this.finish();
            }
        }).start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        if (!this.backShouldExit) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(getApplicationContext(), (Class<?>) ControlsActivity.class));
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, new Pair[0]).toBundle());
        }
        animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.controls_management);
        this.lifecycleRegistry.addObserver(ControlsAnimations.observerForAnimations$default((ViewGroup) requireViewById(R.id.controls_management_root), getWindow(), getIntent()));
        ViewStub viewStub = (ViewStub) requireViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.controls_management_apps);
        viewStub.inflate();
        RecyclerView recyclerView = (RecyclerView) requireViewById(R.id.list);
        this.recyclerView = recyclerView;
        getApplicationContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        TextView textView = (TextView) requireViewById(R.id.title);
        textView.setText(textView.getResources().getText(R.string.controls_providers_title));
        Button button = (Button) requireViewById(R.id.other_apps);
        button.setVisibility(0);
        button.setText(android.R.string.cancel);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.ControlsProviderSelectorActivity$onCreate$3$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlsProviderSelectorActivity.this.onBackPressed();
            }
        });
        requireViewById(R.id.done).setVisibility(8);
        this.backShouldExit = getIntent().getBooleanExtra("back_should_exit", false);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        ((UserTrackerImpl) this.userTracker).addCallback(this.userTrackerCallback, this.executor);
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            recyclerView = null;
        }
        recyclerView.setAlpha(0.0f);
        RecyclerView recyclerView2 = this.recyclerView;
        RecyclerView recyclerView3 = recyclerView2 == null ? null : recyclerView2;
        Executor executor = this.backExecutor;
        Executor executor2 = this.executor;
        LayoutInflater from = LayoutInflater.from(this);
        ControlsProviderSelectorActivity$onStart$1 controlsProviderSelectorActivity$onStart$1 = new ControlsProviderSelectorActivity$onStart$1(1, this, ControlsProviderSelectorActivity.class, "onAppSelected", "onAppSelected(Lcom/android/systemui/controls/ControlsServiceInfo;)V", 0);
        FavoritesRenderer favoritesRenderer = new FavoritesRenderer(getResources(), new ControlsProviderSelectorActivity$onStart$2(1, this.controlsController, ControlsController.class, "countFavoritesForComponent", "countFavoritesForComponent(Landroid/content/ComponentName;)I", 0));
        Resources resources = getResources();
        AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl = (AuthorizedPanelsRepositoryImpl) this.authorizedPanelsRepository;
        Set<String> stringSet = authorizedPanelsRepositoryImpl.instantiateSharedPrefs(((UserTrackerImpl) authorizedPanelsRepositoryImpl.userTracker).getUserHandle()).getStringSet("authorized_panels", EmptySet.INSTANCE);
        Intrinsics.checkNotNull(stringSet);
        AppAdapter appAdapter = new AppAdapter(executor, executor2, this.lifecycleRegistry, this.listingController, from, controlsProviderSelectorActivity$onStart$1, favoritesRenderer, resources, stringSet);
        appAdapter.mObservable.registerObserver(new RecyclerView.AdapterDataObserver() { // from class: com.android.systemui.controls.management.ControlsProviderSelectorActivity$onStart$3$1
            public boolean hasAnimated;

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public final void onChanged() {
                if (this.hasAnimated) {
                    return;
                }
                this.hasAnimated = true;
                RecyclerView recyclerView4 = ControlsProviderSelectorActivity.this.recyclerView;
                if (recyclerView4 == null) {
                    recyclerView4 = null;
                }
                ControlsAnimations.enterAnimation(recyclerView4).start();
            }
        });
        recyclerView3.setAdapter(appAdapter);
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.mOnBackInvokedCallback);
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mOnBackInvokedCallback);
        SystemUIDialog systemUIDialog = this.dialog;
        if (systemUIDialog != null) {
            systemUIDialog.cancel();
        }
    }
}
