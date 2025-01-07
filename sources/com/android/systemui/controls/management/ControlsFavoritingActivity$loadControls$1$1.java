package com.android.systemui.controls.management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.widget.TextView;
import androidx.viewpager2.widget.ScrollEventAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.android.systemui.controls.ControlStatus;
import com.android.systemui.controls.TooltipManager;
import com.android.systemui.controls.controller.ControlsControllerKt$createLoadDataObject$1;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsFavoritingActivity$loadControls$1$1 implements Consumer {
    public final /* synthetic */ CharSequence $emptyZoneString;
    public final /* synthetic */ ControlsFavoritingActivity this$0;

    public ControlsFavoritingActivity$loadControls$1$1(ControlsFavoritingActivity controlsFavoritingActivity, CharSequence charSequence) {
        this.this$0 = controlsFavoritingActivity;
        this.$emptyZoneString = charSequence;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ControlsControllerKt$createLoadDataObject$1 controlsControllerKt$createLoadDataObject$1 = (ControlsControllerKt$createLoadDataObject$1) obj;
        List list = controlsControllerKt$createLoadDataObject$1.allControls;
        List list2 = controlsControllerKt$createLoadDataObject$1.favoritesIds;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : list) {
            CharSequence structure = ((ControlStatus) obj2).control.getStructure();
            if (structure == null) {
                structure = "";
            }
            Object obj3 = linkedHashMap.get(structure);
            if (obj3 == null) {
                obj3 = new ArrayList();
                linkedHashMap.put(structure, obj3);
            }
            ((List) obj3).add(obj2);
        }
        ControlsFavoritingActivity controlsFavoritingActivity = this.this$0;
        CharSequence charSequence = this.$emptyZoneString;
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new StructureContainer((CharSequence) entry.getKey(), new AllModel((List) entry.getValue(), list2, charSequence, controlsFavoritingActivity.controlsModelCallback)));
        }
        ControlsFavoritingActivity$onCreate$$inlined$compareBy$1 controlsFavoritingActivity$onCreate$$inlined$compareBy$1 = this.this$0.comparator;
        if (controlsFavoritingActivity$onCreate$$inlined$compareBy$1 == null) {
            controlsFavoritingActivity$onCreate$$inlined$compareBy$1 = null;
        }
        controlsFavoritingActivity.listOfStructures = CollectionsKt.sortedWith(arrayList, controlsFavoritingActivity$onCreate$$inlined$compareBy$1);
        ControlsFavoritingActivity controlsFavoritingActivity2 = this.this$0;
        Iterator it = controlsFavoritingActivity2.listOfStructures.iterator();
        final int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(((StructureContainer) it.next()).structureName, controlsFavoritingActivity2.structureExtra)) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            i = 0;
        }
        if (this.this$0.getIntent().getBooleanExtra("extra_single_structure", false)) {
            ControlsFavoritingActivity controlsFavoritingActivity3 = this.this$0;
            controlsFavoritingActivity3.listOfStructures = Collections.singletonList(controlsFavoritingActivity3.listOfStructures.get(i));
        }
        final ControlsFavoritingActivity controlsFavoritingActivity4 = this.this$0;
        Executor executor = controlsFavoritingActivity4.executor;
        final boolean z = controlsControllerKt$createLoadDataObject$1.errorOnLoad;
        executor.execute(new Runnable() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$1.2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r6v2, types: [androidx.viewpager2.widget.ViewPager2] */
            @Override // java.lang.Runnable
            public final void run() {
                ControlsFavoritingActivity controlsFavoritingActivity5 = ControlsFavoritingActivity.this;
                ViewPager2 viewPager2 = controlsFavoritingActivity5.structurePager;
                if (viewPager2 == null) {
                    viewPager2 = null;
                }
                viewPager2.setAdapter(new StructureAdapter(((UserTrackerImpl) controlsFavoritingActivity5.userTracker).getUserId(), controlsFavoritingActivity5.listOfStructures));
                ViewPager2 viewPager22 = ControlsFavoritingActivity.this.structurePager;
                if (viewPager22 == null) {
                    viewPager22 = null;
                }
                int i2 = i;
                ScrollEventAdapter scrollEventAdapter = viewPager22.mFakeDragger.mScrollEventAdapter;
                viewPager22.setCurrentItemInternal(i2);
                if (z) {
                    ControlsFavoritingActivity controlsFavoritingActivity6 = ControlsFavoritingActivity.this;
                    TextView textView = controlsFavoritingActivity6.statusText;
                    if (textView == null) {
                        textView = null;
                    }
                    Resources resources = controlsFavoritingActivity6.getResources();
                    Object obj4 = ControlsFavoritingActivity.this.appName;
                    if (obj4 == null) {
                        obj4 = "";
                    }
                    textView.setText(resources.getString(R.string.controls_favorite_load_error, obj4));
                    TextView textView2 = ControlsFavoritingActivity.this.subtitleView;
                    (textView2 != null ? textView2 : null).setVisibility(8);
                    return;
                }
                if (ControlsFavoritingActivity.this.listOfStructures.isEmpty()) {
                    ControlsFavoritingActivity controlsFavoritingActivity7 = ControlsFavoritingActivity.this;
                    TextView textView3 = controlsFavoritingActivity7.statusText;
                    if (textView3 == null) {
                        textView3 = null;
                    }
                    textView3.setText(controlsFavoritingActivity7.getResources().getString(R.string.controls_favorite_load_none));
                    TextView textView4 = ControlsFavoritingActivity.this.subtitleView;
                    (textView4 != null ? textView4 : null).setVisibility(8);
                    return;
                }
                TextView textView5 = ControlsFavoritingActivity.this.statusText;
                if (textView5 == null) {
                    textView5 = null;
                }
                textView5.setVisibility(8);
                ControlsFavoritingActivity controlsFavoritingActivity8 = ControlsFavoritingActivity.this;
                ManagementPageIndicator managementPageIndicator = controlsFavoritingActivity8.pageIndicator;
                if (managementPageIndicator == null) {
                    managementPageIndicator = null;
                }
                managementPageIndicator.setNumPages(controlsFavoritingActivity8.listOfStructures.size());
                ManagementPageIndicator managementPageIndicator2 = ControlsFavoritingActivity.this.pageIndicator;
                if (managementPageIndicator2 == null) {
                    managementPageIndicator2 = null;
                }
                managementPageIndicator2.setLocation(0.0f);
                ControlsFavoritingActivity controlsFavoritingActivity9 = ControlsFavoritingActivity.this;
                ManagementPageIndicator managementPageIndicator3 = controlsFavoritingActivity9.pageIndicator;
                if (managementPageIndicator3 == null) {
                    managementPageIndicator3 = null;
                }
                managementPageIndicator3.setVisibility(controlsFavoritingActivity9.listOfStructures.size() > 1 ? 0 : 4);
                ManagementPageIndicator managementPageIndicator4 = ControlsFavoritingActivity.this.pageIndicator;
                if (managementPageIndicator4 == null) {
                    managementPageIndicator4 = null;
                }
                Animator enterAnimation = ControlsAnimations.enterAnimation(managementPageIndicator4);
                final ControlsFavoritingActivity controlsFavoritingActivity10 = ControlsFavoritingActivity.this;
                enterAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$1$2$1$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        ManagementPageIndicator managementPageIndicator5 = ControlsFavoritingActivity.this.pageIndicator;
                        if (managementPageIndicator5 == null) {
                            managementPageIndicator5 = null;
                        }
                        if (managementPageIndicator5.getVisibility() == 0) {
                            ControlsFavoritingActivity controlsFavoritingActivity11 = ControlsFavoritingActivity.this;
                            if (controlsFavoritingActivity11.mTooltipManager != null) {
                                int[] iArr = new int[2];
                                ManagementPageIndicator managementPageIndicator6 = controlsFavoritingActivity11.pageIndicator;
                                if (managementPageIndicator6 == null) {
                                    managementPageIndicator6 = null;
                                }
                                managementPageIndicator6.getLocationOnScreen(iArr);
                                int i3 = iArr[0];
                                ManagementPageIndicator managementPageIndicator7 = ControlsFavoritingActivity.this.pageIndicator;
                                if (managementPageIndicator7 == null) {
                                    managementPageIndicator7 = null;
                                }
                                int width = (managementPageIndicator7.getWidth() / 2) + i3;
                                int i4 = iArr[1];
                                ManagementPageIndicator managementPageIndicator8 = ControlsFavoritingActivity.this.pageIndicator;
                                int height = (managementPageIndicator8 != null ? managementPageIndicator8 : null).getHeight() + i4;
                                TooltipManager tooltipManager = ControlsFavoritingActivity.this.mTooltipManager;
                                if (tooltipManager != null) {
                                    tooltipManager.show(width, height);
                                }
                            }
                        }
                    }
                });
                enterAnimation.start();
                ?? r6 = ControlsFavoritingActivity.this.structurePager;
                ControlsAnimations.enterAnimation(r6 != 0 ? r6 : null).start();
            }
        });
    }
}
