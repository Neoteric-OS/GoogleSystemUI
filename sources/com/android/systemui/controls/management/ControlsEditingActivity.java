package com.android.systemui.controls.management;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedCallback;
import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.controls.CustomIconCache;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsControllerImpl$removeFavorites$1;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.controller.StructureInfo;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ControlsEditingActivity extends ComponentActivity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ComponentName component;
    public final ControlsControllerImpl controller;
    public final CustomIconCache customIconCache;
    public boolean isFromFavoriting;
    public final Executor mainExecutor;
    public FavoritesModel model;
    public View saveButton;
    public CharSequence structure;
    public TextView subtitle;
    public final UserTracker userTracker;
    public final ControlsEditingActivity$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$userTrackerCallback$1
        public final int startingUser;

        {
            this.startingUser = ControlsEditingActivity.this.controller.currentUser.getIdentifier();
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            if (i != this.startingUser) {
                ControlsEditingActivity controlsEditingActivity = ControlsEditingActivity.this;
                ((UserTrackerImpl) controlsEditingActivity.userTracker).removeCallback(this);
                controlsEditingActivity.finish();
            }
        }
    };
    public final ControlsEditingActivity$mOnBackInvokedCallback$1 mOnBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$mOnBackInvokedCallback$1
        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            ControlsEditingActivity.this.animateExitAndFinish();
        }
    };
    public final ControlsEditingActivity$favoritesModelCallback$1 favoritesModelCallback = new ControlsEditingActivity$favoritesModelCallback$1(this);

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.controls.management.ControlsEditingActivity$userTrackerCallback$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.controls.management.ControlsEditingActivity$mOnBackInvokedCallback$1] */
    public ControlsEditingActivity(Executor executor, ControlsControllerImpl controlsControllerImpl, UserTracker userTracker, CustomIconCache customIconCache) {
        this.mainExecutor = executor;
        this.controller = controlsControllerImpl;
        this.userTracker = userTracker;
        this.customIconCache = customIconCache;
    }

    public final void animateExitAndFinish() {
        ControlsAnimations.exitAnimation((ViewGroup) requireViewById(R.id.controls_management_root), new Runnable() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$animateExitAndFinish$1
            @Override // java.lang.Runnable
            public final void run() {
                ControlsEditingActivity.this.finish();
            }
        }).start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        animateExitAndFinish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        Unit unit;
        super.onCreate(bundle);
        ComponentName componentName = (ComponentName) getIntent().getParcelableExtra("android.intent.extra.COMPONENT_NAME");
        Unit unit2 = Unit.INSTANCE;
        if (componentName != null) {
            this.component = componentName;
            unit = unit2;
        } else {
            unit = null;
        }
        if (unit == null) {
            finish();
        }
        this.isFromFavoriting = getIntent().getBooleanExtra("extra_from_favoriting", false);
        CharSequence charSequenceExtra = getIntent().getCharSequenceExtra("extra_structure");
        if (charSequenceExtra != null) {
            this.structure = charSequenceExtra;
        } else {
            unit2 = null;
        }
        if (unit2 == null) {
            finish();
        }
        setContentView(R.layout.controls_management);
        this.lifecycleRegistry.addObserver(ControlsAnimations.observerForAnimations$default((ViewGroup) requireViewById(R.id.controls_management_root), getWindow(), getIntent()));
        ViewStub viewStub = (ViewStub) requireViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.controls_management_editing);
        viewStub.inflate();
        TextView textView = (TextView) requireViewById(R.id.title);
        CharSequence charSequence = this.structure;
        if (charSequence == null) {
            charSequence = null;
        }
        textView.setText(charSequence);
        CharSequence charSequence2 = this.structure;
        setTitle(charSequence2 != null ? charSequence2 : null);
        TextView textView2 = (TextView) requireViewById(R.id.subtitle);
        textView2.setText(R.string.controls_favorite_rearrange);
        this.subtitle = textView2;
        final Button button = (Button) requireViewById(R.id.addControls);
        button.setEnabled(true);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$bindButtons$1$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                View view2 = ControlsEditingActivity.this.saveButton;
                if (view2 == null) {
                    view2 = null;
                }
                if (view2.isEnabled()) {
                    Toast.makeText(ControlsEditingActivity.this.getApplicationContext(), R.string.controls_favorite_toast_no_changes, 0).show();
                }
                ControlsEditingActivity controlsEditingActivity = ControlsEditingActivity.this;
                if (controlsEditingActivity.isFromFavoriting) {
                    controlsEditingActivity.animateExitAndFinish();
                    return;
                }
                Intent intent = new Intent(button.getContext(), (Class<?>) ControlsFavoritingActivity.class);
                ControlsEditingActivity controlsEditingActivity2 = ControlsEditingActivity.this;
                CharSequence charSequence3 = controlsEditingActivity2.structure;
                if (charSequence3 == null) {
                    charSequence3 = null;
                }
                intent.putExtra("extra_structure", charSequence3);
                ComponentName componentName2 = controlsEditingActivity2.component;
                intent.putExtra("android.intent.extra.COMPONENT_NAME", componentName2 != null ? componentName2 : null);
                intent.putExtra("extra_app_label", controlsEditingActivity2.getIntent().getCharSequenceExtra("extra_app_label"));
                intent.putExtra("extra_source", (byte) 2);
                controlsEditingActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ControlsEditingActivity.this, new Pair[0]).toBundle());
            }
        });
        View requireViewById = requireViewById(R.id.done);
        Button button2 = (Button) requireViewById;
        button2.setEnabled(this.isFromFavoriting);
        button2.setText(R.string.save);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$bindButtons$2$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlsEditingActivity controlsEditingActivity = ControlsEditingActivity.this;
                int i = ControlsEditingActivity.$r8$clinit;
                ComponentName componentName2 = controlsEditingActivity.component;
                if (componentName2 == null) {
                    componentName2 = null;
                }
                CharSequence charSequence3 = controlsEditingActivity.structure;
                if (charSequence3 == null) {
                    charSequence3 = null;
                }
                FavoritesModel favoritesModel = controlsEditingActivity.model;
                FavoritesModel favoritesModel2 = favoritesModel != null ? favoritesModel : null;
                List take = CollectionsKt.take(favoritesModel2.elements, favoritesModel2.dividerPosition);
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(take, 10));
                Iterator it = take.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ControlInfoWrapper) ((ElementWrapper) it.next())).controlInfo);
                }
                StructureInfo structureInfo = new StructureInfo(componentName2, charSequence3, arrayList);
                ControlsControllerImpl controlsControllerImpl = controlsEditingActivity.controller;
                if (controlsControllerImpl.confirmAvailability()) {
                    ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$removeFavorites$1(structureInfo, controlsControllerImpl, 3));
                }
                ControlsEditingActivity.this.startActivity(new Intent(ControlsEditingActivity.this.getApplicationContext(), (Class<?>) ControlsActivity.class), ActivityOptions.makeSceneTransitionAnimation(ControlsEditingActivity.this, new Pair[0]).toBundle());
                ControlsEditingActivity.this.animateExitAndFinish();
            }
        });
        this.saveButton = requireViewById;
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onStart() {
        Object obj;
        List list;
        int i;
        super.onStart();
        ComponentName componentName = this.component;
        if (componentName == null) {
            componentName = null;
        }
        CharSequence charSequence = this.structure;
        if (charSequence == null) {
            charSequence = null;
        }
        this.controller.getClass();
        Map map = Favorites.favMap;
        StructureInfo structureInfo = new StructureInfo(componentName, charSequence, EmptyList.INSTANCE);
        Iterator it = Favorites.getStructuresForComponent(componentName).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((StructureInfo) obj).structure, structureInfo.structure)) {
                    break;
                }
            }
        }
        StructureInfo structureInfo2 = (StructureInfo) obj;
        if (structureInfo2 == null || (list = structureInfo2.controls) == null) {
            list = EmptyList.INSTANCE;
        }
        ComponentName componentName2 = this.component;
        if (componentName2 == null) {
            componentName2 = null;
        }
        this.model = new FavoritesModel(this.customIconCache, componentName2, list, this.favoritesModelCallback);
        float f = getResources().getFloat(R.dimen.control_card_elevation);
        final RecyclerView recyclerView = (RecyclerView) requireViewById(R.id.list);
        recyclerView.setAlpha(0.0f);
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.userTracker;
        final ControlAdapter controlAdapter = new ControlAdapter(userTrackerImpl.getUserId(), f);
        controlAdapter.mObservable.registerObserver(new RecyclerView.AdapterDataObserver() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$setUpList$adapter$1$1
            public boolean hasAnimated;

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public final void onChanged() {
                if (this.hasAnimated) {
                    return;
                }
                this.hasAnimated = true;
                ControlsAnimations.enterAnimation(RecyclerView.this).start();
            }
        });
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.controls_card_margin);
        MarginItemDecorator marginItemDecorator = new MarginItemDecorator(dimensionPixelSize, dimensionPixelSize);
        Resources resources = getResources();
        final int integer = resources.getInteger(R.integer.controls_max_columns);
        int integer2 = resources.getInteger(R.integer.controls_max_columns_adjust_below_width_dp);
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.controls_max_columns_adjust_above_font_scale, typedValue, true);
        float f2 = typedValue.getFloat();
        Configuration configuration = resources.getConfiguration();
        if (configuration.orientation == 1 && (i = configuration.screenWidthDp) != 0 && i <= integer2 && configuration.fontScale >= f2) {
            integer--;
        }
        recyclerView.setAdapter(controlAdapter);
        recyclerView.getContext();
        ControlsEditingActivity$setUpList$1$1 controlsEditingActivity$setUpList$1$1 = new ControlsEditingActivity$setUpList$1$1(integer);
        controlsEditingActivity$setUpList$1$1.mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: com.android.systemui.controls.management.ControlsEditingActivity$setUpList$1$2$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public final int getSpanSize(int i2) {
                ControlAdapter controlAdapter2 = ControlAdapter.this;
                if (controlAdapter2 == null || controlAdapter2.getItemViewType(i2) != 1) {
                    return integer;
                }
                return 1;
            }
        };
        recyclerView.setLayoutManager(controlsEditingActivity$setUpList$1$1);
        recyclerView.addItemDecoration(marginItemDecorator);
        FavoritesModel favoritesModel = this.model;
        if (favoritesModel == null) {
            favoritesModel = null;
        }
        controlAdapter.model = favoritesModel;
        controlAdapter.notifyDataSetChanged();
        FavoritesModel favoritesModel2 = this.model;
        (favoritesModel2 == null ? null : favoritesModel2).adapter = controlAdapter;
        new ItemTouchHelper((favoritesModel2 != null ? favoritesModel2 : null).itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        userTrackerImpl.addCallback(this.userTrackerCallback, this.mainExecutor);
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.mOnBackInvokedCallback);
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mOnBackInvokedCallback);
    }
}
