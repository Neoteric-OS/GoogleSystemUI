package com.android.systemui.controls.management;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import androidx.activity.ComponentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.android.systemui.Prefs;
import com.android.systemui.controls.TooltipManager;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsControllerImpl$removeFavorites$1;
import com.android.systemui.controls.controller.StructureInfo;
import com.android.systemui.controls.management.ControlsModel;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ControlsFavoritingActivity extends ComponentActivity {
    public CharSequence appName;
    public Runnable cancelLoadRunnable;
    public ControlsFavoritingActivity$onCreate$$inlined$compareBy$1 comparator;
    public ComponentName component;
    public final ControlsControllerImpl controller;
    public View doneButton;
    public final Executor executor;
    public boolean isPagerLoaded;
    public TooltipManager mTooltipManager;
    public byte openSource;
    public ManagementPageIndicator pageIndicator;
    public Button rearrangeButton;
    public TextView statusText;
    public CharSequence structureExtra;
    public ViewPager2 structurePager;
    public TextView subtitleView;
    public TextView titleView;
    public final UserTracker userTracker;
    public List listOfStructures = EmptyList.INSTANCE;
    public final ControlsFavoritingActivity$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$userTrackerCallback$1
        public final int startingUser;

        {
            this.startingUser = ControlsFavoritingActivity.this.controller.currentUser.getIdentifier();
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            if (i != this.startingUser) {
                ControlsFavoritingActivity controlsFavoritingActivity = ControlsFavoritingActivity.this;
                ((UserTrackerImpl) controlsFavoritingActivity.userTracker).removeCallback(this);
                controlsFavoritingActivity.finish();
            }
        }
    };
    public final ControlsFavoritingActivity$mOnBackInvokedCallback$1 mOnBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$mOnBackInvokedCallback$1
        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            ControlsFavoritingActivity.this.onBackPressed();
        }
    };
    public final ControlsFavoritingActivity$controlsModelCallback$1 controlsModelCallback = new ControlsModel.ControlsModelCallback() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$controlsModelCallback$1
        @Override // com.android.systemui.controls.management.ControlsModel.ControlsModelCallback
        public final void onChange() {
            ControlsFavoritingActivity controlsFavoritingActivity = ControlsFavoritingActivity.this;
            List list = controlsFavoritingActivity.listOfStructures;
            ViewPager2 viewPager2 = controlsFavoritingActivity.structurePager;
            if (viewPager2 == null) {
                viewPager2 = null;
            }
            StructureContainer structureContainer = (StructureContainer) list.get(viewPager2.mCurrentItem);
            Button button = controlsFavoritingActivity.rearrangeButton;
            (button != null ? button : null).setEnabled(!structureContainer.model.getFavorites().isEmpty());
        }

        @Override // com.android.systemui.controls.management.ControlsModel.ControlsModelCallback
        public final void onFirstChange() {
            View view = ControlsFavoritingActivity.this.doneButton;
            if (view == null) {
                view = null;
            }
            view.setEnabled(true);
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.controls.management.ControlsFavoritingActivity$userTrackerCallback$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.controls.management.ControlsFavoritingActivity$mOnBackInvokedCallback$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.controls.management.ControlsFavoritingActivity$controlsModelCallback$1] */
    public ControlsFavoritingActivity(Executor executor, ControlsControllerImpl controlsControllerImpl, UserTracker userTracker) {
        this.executor = executor;
        this.controller = controlsControllerImpl;
        this.userTracker = userTracker;
    }

    public static final void access$saveFavorites(ControlsFavoritingActivity controlsFavoritingActivity) {
        for (StructureContainer structureContainer : controlsFavoritingActivity.listOfStructures) {
            List favorites = structureContainer.model.getFavorites();
            ComponentName componentName = controlsFavoritingActivity.component;
            Intrinsics.checkNotNull(componentName);
            StructureInfo structureInfo = new StructureInfo(componentName, structureContainer.structureName, favorites);
            ControlsControllerImpl controlsControllerImpl = controlsFavoritingActivity.controller;
            if (controlsControllerImpl.confirmAvailability()) {
                ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$removeFavorites$1(structureInfo, controlsControllerImpl, 3));
            }
        }
    }

    public void animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        ControlsAnimations.exitAnimation((ViewGroup) requireViewById(R.id.controls_management_root), new Runnable() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$animateExitAndFinish$1
            @Override // java.lang.Runnable
            public final void run() {
                ControlsFavoritingActivity.this.finish();
            }
        }).start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        if (this.openSource == 2) {
            animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
        }
        if (this.openSource != 1) {
            startActivity(new Intent(getApplicationContext(), (Class<?>) ControlsActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, new Pair[0]).toBundle());
        }
        animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TooltipManager tooltipManager = this.mTooltipManager;
        if (tooltipManager != null) {
            tooltipManager.hide(false);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.controls.management.ControlsFavoritingActivity$onCreate$$inlined$compareBy$1] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.controls.management.ControlsFavoritingActivity$bindViews$2$1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = 0;
        final Collator collator = Collator.getInstance(getResources().getConfiguration().getLocales().get(0));
        Intrinsics.checkNotNull(collator);
        this.comparator = new Comparator() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$onCreate$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return collator.compare(((StructureContainer) obj).structureName, ((StructureContainer) obj2).structureName);
            }
        };
        this.appName = getIntent().getCharSequenceExtra("extra_app_label");
        this.structureExtra = getIntent().getCharSequenceExtra("extra_structure");
        this.component = (ComponentName) getIntent().getParcelableExtra("android.intent.extra.COMPONENT_NAME");
        this.openSource = getIntent().getByteExtra("extra_source", (byte) 0);
        setContentView(R.layout.controls_management);
        this.lifecycleRegistry.addObserver(ControlsAnimations.observerForAnimations$default((ViewGroup) requireViewById(R.id.controls_management_root), getWindow(), getIntent()));
        ViewStub viewStub = (ViewStub) requireViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.controls_management_favorites);
        viewStub.inflate();
        this.statusText = (TextView) requireViewById(R.id.status_message);
        if (Prefs.get(getApplicationContext()).getInt("ControlsStructureSwipeTooltipCount", 0) < 2) {
            TextView textView = this.statusText;
            if (textView == null) {
                textView = null;
            }
            TooltipManager tooltipManager = new TooltipManager(textView.getContext());
            this.mTooltipManager = tooltipManager;
            addContentView(tooltipManager.layout, new FrameLayout.LayoutParams(-2, -2, 51));
        }
        ManagementPageIndicator managementPageIndicator = (ManagementPageIndicator) requireViewById(R.id.structure_page_indicator);
        managementPageIndicator.visibilityListener = new Function1() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$bindViews$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TooltipManager tooltipManager2;
                if (((Number) obj).intValue() != 0 && (tooltipManager2 = ControlsFavoritingActivity.this.mTooltipManager) != null) {
                    tooltipManager2.hide(true);
                }
                return Unit.INSTANCE;
            }
        };
        this.pageIndicator = managementPageIndicator;
        CharSequence charSequence = this.structureExtra;
        if (charSequence == null && (charSequence = this.appName) == null) {
            charSequence = getResources().getText(R.string.controls_favorite_default_title);
        }
        TextView textView2 = (TextView) requireViewById(R.id.title);
        textView2.setText(charSequence);
        this.titleView = textView2;
        TextView textView3 = (TextView) requireViewById(R.id.subtitle);
        textView3.setText(textView3.getResources().getText(R.string.controls_favorite_subtitle));
        this.subtitleView = textView3;
        ViewPager2 viewPager2 = (ViewPager2) requireViewById(R.id.structure_pager);
        this.structurePager = viewPager2;
        viewPager2.mExternalPageChangeCallbacks.mCallbacks.add(new ControlsFavoritingActivity$bindViews$5(this, i));
        final Button button = (Button) requireViewById(R.id.rearrange);
        button.setText(this.openSource == 2 ? getString(R.string.controls_favorite_back_to_editing) : getString(R.string.controls_favorite_rearrange_button));
        button.setEnabled(false);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$bindButtons$1$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlsFavoritingActivity controlsFavoritingActivity = ControlsFavoritingActivity.this;
                if (controlsFavoritingActivity.component == null) {
                    return;
                }
                ControlsFavoritingActivity.access$saveFavorites(controlsFavoritingActivity);
                ControlsFavoritingActivity controlsFavoritingActivity2 = ControlsFavoritingActivity.this;
                Intent intent = new Intent(button.getContext(), (Class<?>) ControlsEditingActivity.class);
                ControlsFavoritingActivity controlsFavoritingActivity3 = ControlsFavoritingActivity.this;
                intent.putExtra("android.intent.extra.COMPONENT_NAME", controlsFavoritingActivity3.component);
                intent.putExtra("extra_app_label", controlsFavoritingActivity3.appName);
                intent.putExtra("extra_from_favoriting", true);
                List list = controlsFavoritingActivity3.listOfStructures;
                ViewPager2 viewPager22 = controlsFavoritingActivity3.structurePager;
                if (viewPager22 == null) {
                    viewPager22 = null;
                }
                intent.putExtra("extra_structure", ((StructureContainer) list.get(viewPager22.mCurrentItem)).structureName);
                controlsFavoritingActivity2.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ControlsFavoritingActivity.this, new Pair[0]).toBundle());
            }
        });
        this.rearrangeButton = button;
        View requireViewById = requireViewById(R.id.done);
        Button button2 = (Button) requireViewById;
        button2.setEnabled(false);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$bindButtons$2$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlsFavoritingActivity controlsFavoritingActivity = ControlsFavoritingActivity.this;
                if (controlsFavoritingActivity.component == null) {
                    return;
                }
                ControlsFavoritingActivity.access$saveFavorites(controlsFavoritingActivity);
                ControlsFavoritingActivity.this.animateExitAndFinish$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
                ControlsFavoritingActivity controlsFavoritingActivity2 = ControlsFavoritingActivity.this;
                controlsFavoritingActivity2.startActivity(new Intent(controlsFavoritingActivity2.getApplicationContext(), (Class<?>) ControlsActivity.class), ActivityOptions.makeSceneTransitionAnimation(controlsFavoritingActivity2, new Pair[0]).toBundle());
            }
        });
        this.doneButton = requireViewById;
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        Runnable runnable = this.cancelLoadRunnable;
        if (runnable != null) {
            runnable.run();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        TooltipManager tooltipManager = this.mTooltipManager;
        if (tooltipManager != null) {
            tooltipManager.hide(false);
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        int i = 1;
        super.onResume();
        if (this.isPagerLoaded) {
            return;
        }
        ViewPager2 viewPager2 = this.structurePager;
        if (viewPager2 == null) {
            viewPager2 = null;
        }
        viewPager2.setAlpha(0.0f);
        ManagementPageIndicator managementPageIndicator = this.pageIndicator;
        if (managementPageIndicator == null) {
            managementPageIndicator = null;
        }
        managementPageIndicator.setAlpha(0.0f);
        ViewPager2 viewPager22 = this.structurePager;
        if (viewPager22 == null) {
            viewPager22 = null;
        }
        viewPager22.setAdapter(new StructureAdapter(((UserTrackerImpl) this.userTracker).getUserId(), EmptyList.INSTANCE));
        viewPager22.mExternalPageChangeCallbacks.mCallbacks.add(new ControlsFavoritingActivity$bindViews$5(this, i));
        ComponentName componentName = this.component;
        if (componentName != null) {
            TextView textView = this.statusText;
            (textView != null ? textView : null).setText(getResources().getText(android.R.string.lockscreen_glogin_instructions));
            this.controller.loadForComponent(componentName, new ControlsFavoritingActivity$loadControls$1$1(this, getResources().getText(R.string.controls_favorite_other_zone_header)), new ControlsFavoritingActivity$loadControls$1$2(this));
        }
        this.isPagerLoaded = true;
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        ((UserTrackerImpl) this.userTracker).addCallback(this.userTrackerCallback, this.executor);
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.mOnBackInvokedCallback);
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mOnBackInvokedCallback);
    }
}
