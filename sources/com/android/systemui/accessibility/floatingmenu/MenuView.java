package com.android.systemui.accessibility.floatingmenu;

import android.animation.ValueAnimator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.accessibility.dialog.AccessibilityTarget;
import com.android.internal.util.Preconditions;
import com.android.systemui.Prefs;
import com.android.systemui.util.PluralMessageFormaterKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuView extends FrameLayout implements ViewTreeObserver.OnComputeInternalInsetsListener, ComponentCallbacks {
    public final AccessibilityTargetAdapter mAdapter;
    public final Rect mBoundsInParent;
    public final MenuView$$ExternalSyntheticLambda1 mFadeEffectInfoObserver;
    public MenuViewLayer$$ExternalSyntheticLambda8 mFeaturesChangeListener;
    public boolean mIsMoveToTucked;
    public final MenuAnimationController mMenuAnimationController;
    public final MenuViewAppearance mMenuViewAppearance;
    public final MenuViewModel mMenuViewModel;
    public MenuViewLayer mMoveToTuckedListener;
    public final MenuView$$ExternalSyntheticLambda1 mMoveToTuckedObserver;
    public final MenuView$$ExternalSyntheticLambda1 mPercentagePositionObserver;
    public final MenuView$$ExternalSyntheticLambda1 mSizeTypeObserver;
    public final MenuView$$ExternalSyntheticLambda0 mSystemGestureExcludeUpdater;
    public final List mTargetFeatures;
    public final MenuView$$ExternalSyntheticLambda1 mTargetFeaturesObserver;
    public final RecyclerView mTargetFeaturesView;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1] */
    public MenuView(Context context, MenuViewModel menuViewModel, MenuViewAppearance menuViewAppearance) {
        super(context);
        ArrayList arrayList = new ArrayList();
        this.mTargetFeatures = arrayList;
        this.mBoundsInParent = new Rect();
        this.mSystemGestureExcludeUpdater = new ViewTreeObserver.OnDrawListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public final void onDraw() {
                MenuView menuView = MenuView.this;
                ((ViewGroup) menuView.getParent()).setSystemGestureExclusionRects(Collections.singletonList(menuView.mBoundsInParent));
            }
        };
        final int i = 0;
        this.mFadeEffectInfoObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ MenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenuView menuView = this.f$0;
                switch (i) {
                    case 0:
                        MenuFadeEffectInfo menuFadeEffectInfo = (MenuFadeEffectInfo) obj;
                        final MenuAnimationController menuAnimationController = menuView.mMenuAnimationController;
                        menuAnimationController.mIsFadeEffectEnabled = menuFadeEffectInfo.isFadeEffectEnabled;
                        Handler handler = menuAnimationController.mHandler;
                        handler.removeCallbacksAndMessages(null);
                        menuAnimationController.mFadeOutAnimator.cancel();
                        ValueAnimator valueAnimator = menuAnimationController.mFadeOutAnimator;
                        final float f = menuFadeEffectInfo.opacity;
                        valueAnimator.setFloatValues(1.0f, f);
                        handler.post(new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                MenuAnimationController menuAnimationController2 = MenuAnimationController.this;
                                float f2 = f;
                                if (!menuAnimationController2.mIsFadeEffectEnabled) {
                                    f2 = 1.0f;
                                }
                                menuAnimationController2.mMenuView.setAlpha(f2);
                            }
                        });
                        break;
                    case 1:
                        menuView.mIsMoveToTucked = ((Boolean) obj).booleanValue();
                        menuView.onPositionChanged(false);
                        break;
                    case 2:
                        Position position = (Position) obj;
                        MenuViewAppearance menuViewAppearance2 = menuView.mMenuViewAppearance;
                        Position position2 = menuViewAppearance2.mPercentagePosition;
                        position2.getClass();
                        float f2 = position.mPercentageX;
                        float f3 = position.mPercentageY;
                        position2.mPercentageX = f2;
                        position2.mPercentageY = f3;
                        menuViewAppearance2.mRadii = MenuViewAppearance.createRadii(menuViewAppearance2.getMenuRadius(menuViewAppearance2.mTargetFeaturesSize), menuViewAppearance2.isMenuOnLeftSide());
                        menuView.onPositionChanged(false);
                        break;
                    case 3:
                        int intValue = ((Integer) obj).intValue();
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        MenuViewAppearance menuViewAppearance3 = menuView.mMenuViewAppearance;
                        menuViewAppearance3.mSizeType = intValue;
                        menuViewAppearance3.mRadii = MenuViewAppearance.createRadii(menuViewAppearance3.getMenuRadius(menuViewAppearance3.mTargetFeaturesSize), menuViewAppearance3.isMenuOnLeftSide());
                        AccessibilityTargetAdapter accessibilityTargetAdapter = menuView.mAdapter;
                        MenuViewAppearance menuViewAppearance4 = menuView.mMenuViewAppearance;
                        int i2 = menuViewAppearance4.mSizeType;
                        accessibilityTargetAdapter.mItemPadding = i2 == 0 ? menuViewAppearance4.mSmallPadding : menuViewAppearance4.mLargePadding;
                        accessibilityTargetAdapter.mIconWidthHeight = i2 == 0 ? menuViewAppearance4.mSmallIconSize : menuViewAppearance4.mLargeIconSize;
                        accessibilityTargetAdapter.notifyDataSetChanged();
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                    default:
                        List list = (List) obj;
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        List unmodifiableList = Collections.unmodifiableList(menuView.mTargetFeatures.stream().toList());
                        menuView.mTargetFeatures.clear();
                        menuView.mTargetFeatures.addAll(list);
                        MenuViewAppearance menuViewAppearance5 = menuView.mMenuViewAppearance;
                        menuViewAppearance5.mTargetFeaturesSize = list.size();
                        menuViewAppearance5.mRadii = MenuViewAppearance.createRadii(menuViewAppearance5.getMenuRadius(r5), menuViewAppearance5.isMenuOnLeftSide());
                        RecyclerView recyclerView = menuView.mTargetFeaturesView;
                        MenuViewAppearance menuViewAppearance6 = menuView.mMenuViewAppearance;
                        int i3 = menuViewAppearance6.mSizeType;
                        int i4 = i3 == 0 ? menuViewAppearance6.mSmallPadding : menuViewAppearance6.mLargePadding;
                        recyclerView.setOverScrollMode((((i3 == 0 ? menuViewAppearance6.mSmallIconSize : menuViewAppearance6.mLargeIconSize) + i4) * menuViewAppearance6.mTargetFeaturesSize) + i4 > menuViewAppearance6.getWindowAvailableBounds().height() ? 0 : 2);
                        DiffUtil.calculateDiff(new MenuTargetsCallback(unmodifiableList, list)).dispatchUpdatesTo(new AdapterListUpdateCallback(menuView.mAdapter));
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        MenuViewLayer$$ExternalSyntheticLambda8 menuViewLayer$$ExternalSyntheticLambda8 = menuView.mFeaturesChangeListener;
                        if (menuViewLayer$$ExternalSyntheticLambda8 != null) {
                            MenuViewLayer menuViewLayer = menuViewLayer$$ExternalSyntheticLambda8.f$0;
                            menuViewLayer.getClass();
                            if (list.size() >= 1) {
                                if (menuViewLayer.mMessageView.getVisibility() == 0) {
                                    menuViewLayer.undo();
                                }
                                TextView textView = (TextView) menuViewLayer.mMessageView.getChildAt(0);
                                Preconditions.checkArgument(list.size() > 0, "The list should at least have one feature.");
                                int size = list.size();
                                Resources resources = menuViewLayer.getResources();
                                textView.setText(size == 1 ? resources.getString(R.string.accessibility_floating_button_undo_message_label_text, ((AccessibilityTarget) list.get(0)).getLabel()) : PluralMessageFormaterKt.icuMessageFormat(resources, R.string.accessibility_floating_button_undo_message_number_text, size));
                            }
                        }
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mMoveToTuckedObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ MenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenuView menuView = this.f$0;
                switch (i2) {
                    case 0:
                        MenuFadeEffectInfo menuFadeEffectInfo = (MenuFadeEffectInfo) obj;
                        final MenuAnimationController menuAnimationController = menuView.mMenuAnimationController;
                        menuAnimationController.mIsFadeEffectEnabled = menuFadeEffectInfo.isFadeEffectEnabled;
                        Handler handler = menuAnimationController.mHandler;
                        handler.removeCallbacksAndMessages(null);
                        menuAnimationController.mFadeOutAnimator.cancel();
                        ValueAnimator valueAnimator = menuAnimationController.mFadeOutAnimator;
                        final float f = menuFadeEffectInfo.opacity;
                        valueAnimator.setFloatValues(1.0f, f);
                        handler.post(new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                MenuAnimationController menuAnimationController2 = MenuAnimationController.this;
                                float f2 = f;
                                if (!menuAnimationController2.mIsFadeEffectEnabled) {
                                    f2 = 1.0f;
                                }
                                menuAnimationController2.mMenuView.setAlpha(f2);
                            }
                        });
                        break;
                    case 1:
                        menuView.mIsMoveToTucked = ((Boolean) obj).booleanValue();
                        menuView.onPositionChanged(false);
                        break;
                    case 2:
                        Position position = (Position) obj;
                        MenuViewAppearance menuViewAppearance2 = menuView.mMenuViewAppearance;
                        Position position2 = menuViewAppearance2.mPercentagePosition;
                        position2.getClass();
                        float f2 = position.mPercentageX;
                        float f3 = position.mPercentageY;
                        position2.mPercentageX = f2;
                        position2.mPercentageY = f3;
                        menuViewAppearance2.mRadii = MenuViewAppearance.createRadii(menuViewAppearance2.getMenuRadius(menuViewAppearance2.mTargetFeaturesSize), menuViewAppearance2.isMenuOnLeftSide());
                        menuView.onPositionChanged(false);
                        break;
                    case 3:
                        int intValue = ((Integer) obj).intValue();
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        MenuViewAppearance menuViewAppearance3 = menuView.mMenuViewAppearance;
                        menuViewAppearance3.mSizeType = intValue;
                        menuViewAppearance3.mRadii = MenuViewAppearance.createRadii(menuViewAppearance3.getMenuRadius(menuViewAppearance3.mTargetFeaturesSize), menuViewAppearance3.isMenuOnLeftSide());
                        AccessibilityTargetAdapter accessibilityTargetAdapter = menuView.mAdapter;
                        MenuViewAppearance menuViewAppearance4 = menuView.mMenuViewAppearance;
                        int i22 = menuViewAppearance4.mSizeType;
                        accessibilityTargetAdapter.mItemPadding = i22 == 0 ? menuViewAppearance4.mSmallPadding : menuViewAppearance4.mLargePadding;
                        accessibilityTargetAdapter.mIconWidthHeight = i22 == 0 ? menuViewAppearance4.mSmallIconSize : menuViewAppearance4.mLargeIconSize;
                        accessibilityTargetAdapter.notifyDataSetChanged();
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                    default:
                        List list = (List) obj;
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        List unmodifiableList = Collections.unmodifiableList(menuView.mTargetFeatures.stream().toList());
                        menuView.mTargetFeatures.clear();
                        menuView.mTargetFeatures.addAll(list);
                        MenuViewAppearance menuViewAppearance5 = menuView.mMenuViewAppearance;
                        menuViewAppearance5.mTargetFeaturesSize = list.size();
                        menuViewAppearance5.mRadii = MenuViewAppearance.createRadii(menuViewAppearance5.getMenuRadius(r5), menuViewAppearance5.isMenuOnLeftSide());
                        RecyclerView recyclerView = menuView.mTargetFeaturesView;
                        MenuViewAppearance menuViewAppearance6 = menuView.mMenuViewAppearance;
                        int i3 = menuViewAppearance6.mSizeType;
                        int i4 = i3 == 0 ? menuViewAppearance6.mSmallPadding : menuViewAppearance6.mLargePadding;
                        recyclerView.setOverScrollMode((((i3 == 0 ? menuViewAppearance6.mSmallIconSize : menuViewAppearance6.mLargeIconSize) + i4) * menuViewAppearance6.mTargetFeaturesSize) + i4 > menuViewAppearance6.getWindowAvailableBounds().height() ? 0 : 2);
                        DiffUtil.calculateDiff(new MenuTargetsCallback(unmodifiableList, list)).dispatchUpdatesTo(new AdapterListUpdateCallback(menuView.mAdapter));
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        MenuViewLayer$$ExternalSyntheticLambda8 menuViewLayer$$ExternalSyntheticLambda8 = menuView.mFeaturesChangeListener;
                        if (menuViewLayer$$ExternalSyntheticLambda8 != null) {
                            MenuViewLayer menuViewLayer = menuViewLayer$$ExternalSyntheticLambda8.f$0;
                            menuViewLayer.getClass();
                            if (list.size() >= 1) {
                                if (menuViewLayer.mMessageView.getVisibility() == 0) {
                                    menuViewLayer.undo();
                                }
                                TextView textView = (TextView) menuViewLayer.mMessageView.getChildAt(0);
                                Preconditions.checkArgument(list.size() > 0, "The list should at least have one feature.");
                                int size = list.size();
                                Resources resources = menuViewLayer.getResources();
                                textView.setText(size == 1 ? resources.getString(R.string.accessibility_floating_button_undo_message_label_text, ((AccessibilityTarget) list.get(0)).getLabel()) : PluralMessageFormaterKt.icuMessageFormat(resources, R.string.accessibility_floating_button_undo_message_number_text, size));
                            }
                        }
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mPercentagePositionObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ MenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenuView menuView = this.f$0;
                switch (i3) {
                    case 0:
                        MenuFadeEffectInfo menuFadeEffectInfo = (MenuFadeEffectInfo) obj;
                        final MenuAnimationController menuAnimationController = menuView.mMenuAnimationController;
                        menuAnimationController.mIsFadeEffectEnabled = menuFadeEffectInfo.isFadeEffectEnabled;
                        Handler handler = menuAnimationController.mHandler;
                        handler.removeCallbacksAndMessages(null);
                        menuAnimationController.mFadeOutAnimator.cancel();
                        ValueAnimator valueAnimator = menuAnimationController.mFadeOutAnimator;
                        final float f = menuFadeEffectInfo.opacity;
                        valueAnimator.setFloatValues(1.0f, f);
                        handler.post(new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                MenuAnimationController menuAnimationController2 = MenuAnimationController.this;
                                float f2 = f;
                                if (!menuAnimationController2.mIsFadeEffectEnabled) {
                                    f2 = 1.0f;
                                }
                                menuAnimationController2.mMenuView.setAlpha(f2);
                            }
                        });
                        break;
                    case 1:
                        menuView.mIsMoveToTucked = ((Boolean) obj).booleanValue();
                        menuView.onPositionChanged(false);
                        break;
                    case 2:
                        Position position = (Position) obj;
                        MenuViewAppearance menuViewAppearance2 = menuView.mMenuViewAppearance;
                        Position position2 = menuViewAppearance2.mPercentagePosition;
                        position2.getClass();
                        float f2 = position.mPercentageX;
                        float f3 = position.mPercentageY;
                        position2.mPercentageX = f2;
                        position2.mPercentageY = f3;
                        menuViewAppearance2.mRadii = MenuViewAppearance.createRadii(menuViewAppearance2.getMenuRadius(menuViewAppearance2.mTargetFeaturesSize), menuViewAppearance2.isMenuOnLeftSide());
                        menuView.onPositionChanged(false);
                        break;
                    case 3:
                        int intValue = ((Integer) obj).intValue();
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        MenuViewAppearance menuViewAppearance3 = menuView.mMenuViewAppearance;
                        menuViewAppearance3.mSizeType = intValue;
                        menuViewAppearance3.mRadii = MenuViewAppearance.createRadii(menuViewAppearance3.getMenuRadius(menuViewAppearance3.mTargetFeaturesSize), menuViewAppearance3.isMenuOnLeftSide());
                        AccessibilityTargetAdapter accessibilityTargetAdapter = menuView.mAdapter;
                        MenuViewAppearance menuViewAppearance4 = menuView.mMenuViewAppearance;
                        int i22 = menuViewAppearance4.mSizeType;
                        accessibilityTargetAdapter.mItemPadding = i22 == 0 ? menuViewAppearance4.mSmallPadding : menuViewAppearance4.mLargePadding;
                        accessibilityTargetAdapter.mIconWidthHeight = i22 == 0 ? menuViewAppearance4.mSmallIconSize : menuViewAppearance4.mLargeIconSize;
                        accessibilityTargetAdapter.notifyDataSetChanged();
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                    default:
                        List list = (List) obj;
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        List unmodifiableList = Collections.unmodifiableList(menuView.mTargetFeatures.stream().toList());
                        menuView.mTargetFeatures.clear();
                        menuView.mTargetFeatures.addAll(list);
                        MenuViewAppearance menuViewAppearance5 = menuView.mMenuViewAppearance;
                        menuViewAppearance5.mTargetFeaturesSize = list.size();
                        menuViewAppearance5.mRadii = MenuViewAppearance.createRadii(menuViewAppearance5.getMenuRadius(r5), menuViewAppearance5.isMenuOnLeftSide());
                        RecyclerView recyclerView = menuView.mTargetFeaturesView;
                        MenuViewAppearance menuViewAppearance6 = menuView.mMenuViewAppearance;
                        int i32 = menuViewAppearance6.mSizeType;
                        int i4 = i32 == 0 ? menuViewAppearance6.mSmallPadding : menuViewAppearance6.mLargePadding;
                        recyclerView.setOverScrollMode((((i32 == 0 ? menuViewAppearance6.mSmallIconSize : menuViewAppearance6.mLargeIconSize) + i4) * menuViewAppearance6.mTargetFeaturesSize) + i4 > menuViewAppearance6.getWindowAvailableBounds().height() ? 0 : 2);
                        DiffUtil.calculateDiff(new MenuTargetsCallback(unmodifiableList, list)).dispatchUpdatesTo(new AdapterListUpdateCallback(menuView.mAdapter));
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        MenuViewLayer$$ExternalSyntheticLambda8 menuViewLayer$$ExternalSyntheticLambda8 = menuView.mFeaturesChangeListener;
                        if (menuViewLayer$$ExternalSyntheticLambda8 != null) {
                            MenuViewLayer menuViewLayer = menuViewLayer$$ExternalSyntheticLambda8.f$0;
                            menuViewLayer.getClass();
                            if (list.size() >= 1) {
                                if (menuViewLayer.mMessageView.getVisibility() == 0) {
                                    menuViewLayer.undo();
                                }
                                TextView textView = (TextView) menuViewLayer.mMessageView.getChildAt(0);
                                Preconditions.checkArgument(list.size() > 0, "The list should at least have one feature.");
                                int size = list.size();
                                Resources resources = menuViewLayer.getResources();
                                textView.setText(size == 1 ? resources.getString(R.string.accessibility_floating_button_undo_message_label_text, ((AccessibilityTarget) list.get(0)).getLabel()) : PluralMessageFormaterKt.icuMessageFormat(resources, R.string.accessibility_floating_button_undo_message_number_text, size));
                            }
                        }
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                }
            }
        };
        final int i4 = 3;
        this.mSizeTypeObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ MenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenuView menuView = this.f$0;
                switch (i4) {
                    case 0:
                        MenuFadeEffectInfo menuFadeEffectInfo = (MenuFadeEffectInfo) obj;
                        final MenuAnimationController menuAnimationController = menuView.mMenuAnimationController;
                        menuAnimationController.mIsFadeEffectEnabled = menuFadeEffectInfo.isFadeEffectEnabled;
                        Handler handler = menuAnimationController.mHandler;
                        handler.removeCallbacksAndMessages(null);
                        menuAnimationController.mFadeOutAnimator.cancel();
                        ValueAnimator valueAnimator = menuAnimationController.mFadeOutAnimator;
                        final float f = menuFadeEffectInfo.opacity;
                        valueAnimator.setFloatValues(1.0f, f);
                        handler.post(new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                MenuAnimationController menuAnimationController2 = MenuAnimationController.this;
                                float f2 = f;
                                if (!menuAnimationController2.mIsFadeEffectEnabled) {
                                    f2 = 1.0f;
                                }
                                menuAnimationController2.mMenuView.setAlpha(f2);
                            }
                        });
                        break;
                    case 1:
                        menuView.mIsMoveToTucked = ((Boolean) obj).booleanValue();
                        menuView.onPositionChanged(false);
                        break;
                    case 2:
                        Position position = (Position) obj;
                        MenuViewAppearance menuViewAppearance2 = menuView.mMenuViewAppearance;
                        Position position2 = menuViewAppearance2.mPercentagePosition;
                        position2.getClass();
                        float f2 = position.mPercentageX;
                        float f3 = position.mPercentageY;
                        position2.mPercentageX = f2;
                        position2.mPercentageY = f3;
                        menuViewAppearance2.mRadii = MenuViewAppearance.createRadii(menuViewAppearance2.getMenuRadius(menuViewAppearance2.mTargetFeaturesSize), menuViewAppearance2.isMenuOnLeftSide());
                        menuView.onPositionChanged(false);
                        break;
                    case 3:
                        int intValue = ((Integer) obj).intValue();
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        MenuViewAppearance menuViewAppearance3 = menuView.mMenuViewAppearance;
                        menuViewAppearance3.mSizeType = intValue;
                        menuViewAppearance3.mRadii = MenuViewAppearance.createRadii(menuViewAppearance3.getMenuRadius(menuViewAppearance3.mTargetFeaturesSize), menuViewAppearance3.isMenuOnLeftSide());
                        AccessibilityTargetAdapter accessibilityTargetAdapter = menuView.mAdapter;
                        MenuViewAppearance menuViewAppearance4 = menuView.mMenuViewAppearance;
                        int i22 = menuViewAppearance4.mSizeType;
                        accessibilityTargetAdapter.mItemPadding = i22 == 0 ? menuViewAppearance4.mSmallPadding : menuViewAppearance4.mLargePadding;
                        accessibilityTargetAdapter.mIconWidthHeight = i22 == 0 ? menuViewAppearance4.mSmallIconSize : menuViewAppearance4.mLargeIconSize;
                        accessibilityTargetAdapter.notifyDataSetChanged();
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                    default:
                        List list = (List) obj;
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        List unmodifiableList = Collections.unmodifiableList(menuView.mTargetFeatures.stream().toList());
                        menuView.mTargetFeatures.clear();
                        menuView.mTargetFeatures.addAll(list);
                        MenuViewAppearance menuViewAppearance5 = menuView.mMenuViewAppearance;
                        menuViewAppearance5.mTargetFeaturesSize = list.size();
                        menuViewAppearance5.mRadii = MenuViewAppearance.createRadii(menuViewAppearance5.getMenuRadius(r5), menuViewAppearance5.isMenuOnLeftSide());
                        RecyclerView recyclerView = menuView.mTargetFeaturesView;
                        MenuViewAppearance menuViewAppearance6 = menuView.mMenuViewAppearance;
                        int i32 = menuViewAppearance6.mSizeType;
                        int i42 = i32 == 0 ? menuViewAppearance6.mSmallPadding : menuViewAppearance6.mLargePadding;
                        recyclerView.setOverScrollMode((((i32 == 0 ? menuViewAppearance6.mSmallIconSize : menuViewAppearance6.mLargeIconSize) + i42) * menuViewAppearance6.mTargetFeaturesSize) + i42 > menuViewAppearance6.getWindowAvailableBounds().height() ? 0 : 2);
                        DiffUtil.calculateDiff(new MenuTargetsCallback(unmodifiableList, list)).dispatchUpdatesTo(new AdapterListUpdateCallback(menuView.mAdapter));
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        MenuViewLayer$$ExternalSyntheticLambda8 menuViewLayer$$ExternalSyntheticLambda8 = menuView.mFeaturesChangeListener;
                        if (menuViewLayer$$ExternalSyntheticLambda8 != null) {
                            MenuViewLayer menuViewLayer = menuViewLayer$$ExternalSyntheticLambda8.f$0;
                            menuViewLayer.getClass();
                            if (list.size() >= 1) {
                                if (menuViewLayer.mMessageView.getVisibility() == 0) {
                                    menuViewLayer.undo();
                                }
                                TextView textView = (TextView) menuViewLayer.mMessageView.getChildAt(0);
                                Preconditions.checkArgument(list.size() > 0, "The list should at least have one feature.");
                                int size = list.size();
                                Resources resources = menuViewLayer.getResources();
                                textView.setText(size == 1 ? resources.getString(R.string.accessibility_floating_button_undo_message_label_text, ((AccessibilityTarget) list.get(0)).getLabel()) : PluralMessageFormaterKt.icuMessageFormat(resources, R.string.accessibility_floating_button_undo_message_number_text, size));
                            }
                        }
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                }
            }
        };
        final int i5 = 4;
        this.mTargetFeaturesObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ MenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MenuView menuView = this.f$0;
                switch (i5) {
                    case 0:
                        MenuFadeEffectInfo menuFadeEffectInfo = (MenuFadeEffectInfo) obj;
                        final MenuAnimationController menuAnimationController = menuView.mMenuAnimationController;
                        menuAnimationController.mIsFadeEffectEnabled = menuFadeEffectInfo.isFadeEffectEnabled;
                        Handler handler = menuAnimationController.mHandler;
                        handler.removeCallbacksAndMessages(null);
                        menuAnimationController.mFadeOutAnimator.cancel();
                        ValueAnimator valueAnimator = menuAnimationController.mFadeOutAnimator;
                        final float f = menuFadeEffectInfo.opacity;
                        valueAnimator.setFloatValues(1.0f, f);
                        handler.post(new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                MenuAnimationController menuAnimationController2 = MenuAnimationController.this;
                                float f2 = f;
                                if (!menuAnimationController2.mIsFadeEffectEnabled) {
                                    f2 = 1.0f;
                                }
                                menuAnimationController2.mMenuView.setAlpha(f2);
                            }
                        });
                        break;
                    case 1:
                        menuView.mIsMoveToTucked = ((Boolean) obj).booleanValue();
                        menuView.onPositionChanged(false);
                        break;
                    case 2:
                        Position position = (Position) obj;
                        MenuViewAppearance menuViewAppearance2 = menuView.mMenuViewAppearance;
                        Position position2 = menuViewAppearance2.mPercentagePosition;
                        position2.getClass();
                        float f2 = position.mPercentageX;
                        float f3 = position.mPercentageY;
                        position2.mPercentageX = f2;
                        position2.mPercentageY = f3;
                        menuViewAppearance2.mRadii = MenuViewAppearance.createRadii(menuViewAppearance2.getMenuRadius(menuViewAppearance2.mTargetFeaturesSize), menuViewAppearance2.isMenuOnLeftSide());
                        menuView.onPositionChanged(false);
                        break;
                    case 3:
                        int intValue = ((Integer) obj).intValue();
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        MenuViewAppearance menuViewAppearance3 = menuView.mMenuViewAppearance;
                        menuViewAppearance3.mSizeType = intValue;
                        menuViewAppearance3.mRadii = MenuViewAppearance.createRadii(menuViewAppearance3.getMenuRadius(menuViewAppearance3.mTargetFeaturesSize), menuViewAppearance3.isMenuOnLeftSide());
                        AccessibilityTargetAdapter accessibilityTargetAdapter = menuView.mAdapter;
                        MenuViewAppearance menuViewAppearance4 = menuView.mMenuViewAppearance;
                        int i22 = menuViewAppearance4.mSizeType;
                        accessibilityTargetAdapter.mItemPadding = i22 == 0 ? menuViewAppearance4.mSmallPadding : menuViewAppearance4.mLargePadding;
                        accessibilityTargetAdapter.mIconWidthHeight = i22 == 0 ? menuViewAppearance4.mSmallIconSize : menuViewAppearance4.mLargeIconSize;
                        accessibilityTargetAdapter.notifyDataSetChanged();
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                    default:
                        List list = (List) obj;
                        menuView.mMenuAnimationController.fadeInNowIfEnabled();
                        List unmodifiableList = Collections.unmodifiableList(menuView.mTargetFeatures.stream().toList());
                        menuView.mTargetFeatures.clear();
                        menuView.mTargetFeatures.addAll(list);
                        MenuViewAppearance menuViewAppearance5 = menuView.mMenuViewAppearance;
                        menuViewAppearance5.mTargetFeaturesSize = list.size();
                        menuViewAppearance5.mRadii = MenuViewAppearance.createRadii(menuViewAppearance5.getMenuRadius(r5), menuViewAppearance5.isMenuOnLeftSide());
                        RecyclerView recyclerView = menuView.mTargetFeaturesView;
                        MenuViewAppearance menuViewAppearance6 = menuView.mMenuViewAppearance;
                        int i32 = menuViewAppearance6.mSizeType;
                        int i42 = i32 == 0 ? menuViewAppearance6.mSmallPadding : menuViewAppearance6.mLargePadding;
                        recyclerView.setOverScrollMode((((i32 == 0 ? menuViewAppearance6.mSmallIconSize : menuViewAppearance6.mLargeIconSize) + i42) * menuViewAppearance6.mTargetFeaturesSize) + i42 > menuViewAppearance6.getWindowAvailableBounds().height() ? 0 : 2);
                        DiffUtil.calculateDiff(new MenuTargetsCallback(unmodifiableList, list)).dispatchUpdatesTo(new AdapterListUpdateCallback(menuView.mAdapter));
                        menuView.onSizeChanged();
                        menuView.onEdgeChanged();
                        menuView.onPositionChanged(false);
                        MenuViewLayer$$ExternalSyntheticLambda8 menuViewLayer$$ExternalSyntheticLambda8 = menuView.mFeaturesChangeListener;
                        if (menuViewLayer$$ExternalSyntheticLambda8 != null) {
                            MenuViewLayer menuViewLayer = menuViewLayer$$ExternalSyntheticLambda8.f$0;
                            menuViewLayer.getClass();
                            if (list.size() >= 1) {
                                if (menuViewLayer.mMessageView.getVisibility() == 0) {
                                    menuViewLayer.undo();
                                }
                                TextView textView = (TextView) menuViewLayer.mMessageView.getChildAt(0);
                                Preconditions.checkArgument(list.size() > 0, "The list should at least have one feature.");
                                int size = list.size();
                                Resources resources = menuViewLayer.getResources();
                                textView.setText(size == 1 ? resources.getString(R.string.accessibility_floating_button_undo_message_label_text, ((AccessibilityTarget) list.get(0)).getLabel()) : PluralMessageFormaterKt.icuMessageFormat(resources, R.string.accessibility_floating_button_undo_message_number_text, size));
                            }
                        }
                        menuView.mMenuAnimationController.fadeOutIfEnabled();
                        break;
                }
            }
        };
        this.mMenuViewModel = menuViewModel;
        this.mMenuViewAppearance = menuViewAppearance;
        this.mMenuAnimationController = new MenuAnimationController(this, menuViewAppearance);
        AccessibilityTargetAdapter accessibilityTargetAdapter = new AccessibilityTargetAdapter(arrayList);
        this.mAdapter = accessibilityTargetAdapter;
        RecyclerView recyclerView = new RecyclerView(context);
        this.mTargetFeaturesView = recyclerView;
        recyclerView.setAdapter(accessibilityTargetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        setClipToOutline(true);
        loadLayoutResources();
        addView(recyclerView);
        setClickable(false);
        setFocusable(false);
        setImportantForAccessibility(2);
    }

    public final Rect getMenuDraggableBounds() {
        return this.mMenuViewAppearance.getMenuDraggableBoundsWith(true);
    }

    public final void loadLayoutResources() {
        this.mMenuViewAppearance.update();
        this.mTargetFeaturesView.setContentDescription(this.mMenuViewAppearance.mContentDescription);
        setBackground(this.mMenuViewAppearance.mBackgroundDrawable);
        setElevation(this.mMenuViewAppearance.mElevation);
        AccessibilityTargetAdapter accessibilityTargetAdapter = this.mAdapter;
        MenuViewAppearance menuViewAppearance = this.mMenuViewAppearance;
        int i = menuViewAppearance.mSizeType;
        accessibilityTargetAdapter.mItemPadding = i == 0 ? menuViewAppearance.mSmallPadding : menuViewAppearance.mLargePadding;
        accessibilityTargetAdapter.mIconWidthHeight = i == 0 ? menuViewAppearance.mSmallIconSize : menuViewAppearance.mLargeIconSize;
        accessibilityTargetAdapter.notifyDataSetChanged();
        onSizeChanged();
        onEdgeChanged();
        onPositionChanged(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getContext().registerComponentCallbacks(this);
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.setTouchableInsets(3);
        if (getVisibility() == 0) {
            internalInsetsInfo.touchableRegion.union(this.mBoundsInParent);
        }
    }

    @Override // android.view.View, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        loadLayoutResources();
        RecyclerView recyclerView = this.mTargetFeaturesView;
        MenuViewAppearance menuViewAppearance = this.mMenuViewAppearance;
        int i = menuViewAppearance.mSizeType;
        int i2 = i == 0 ? menuViewAppearance.mSmallPadding : menuViewAppearance.mLargePadding;
        recyclerView.setOverScrollMode((((i == 0 ? menuViewAppearance.mSmallIconSize : menuViewAppearance.mLargeIconSize) + i2) * menuViewAppearance.mTargetFeaturesSize) + i2 > menuViewAppearance.getWindowAvailableBounds().height() ? 0 : 2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterComponentCallbacks(this);
    }

    public final void onEdgeChanged() {
        MenuViewAppearance menuViewAppearance = this.mMenuViewAppearance;
        int[] iArr = {menuViewAppearance.isMenuOnLeftSide() ? menuViewAppearance.mInset : 0, 0, menuViewAppearance.isMenuOnLeftSide() ? 0 : menuViewAppearance.mInset, 0};
        ((InstantInsetLayerDrawable) getBackground()).setLayerInset(0, iArr[0], iArr[1], iArr[2], iArr[3]);
        GradientDrawable gradientDrawable = (GradientDrawable) ((InstantInsetLayerDrawable) getBackground()).getDrawable(0);
        MenuViewAppearance menuViewAppearance2 = this.mMenuViewAppearance;
        gradientDrawable.setStroke(menuViewAppearance2.mStrokeWidth, menuViewAppearance2.mStrokeColor);
        this.mMenuAnimationController.startRadiiAnimation(this.mMenuViewAppearance.mRadii);
    }

    public final void onEdgeChangedIfNeeded() {
        Rect menuDraggableBoundsWith = this.mMenuViewAppearance.getMenuDraggableBoundsWith(true);
        if (getTranslationX() == menuDraggableBoundsWith.left || getTranslationX() == menuDraggableBoundsWith.right) {
            onEdgeChanged();
        }
    }

    public final void onPositionChanged(boolean z) {
        PointF tuckedMenuPosition = this.mIsMoveToTucked ? this.mMenuAnimationController.getTuckedMenuPosition() : this.mMenuViewAppearance.getMenuPosition();
        if (z && getVisibility() == 0) {
            MenuAnimationController menuAnimationController = this.mMenuAnimationController;
            menuAnimationController.getClass();
            float f = tuckedMenuPosition.x;
            menuAnimationController.springMenuWith(DynamicAnimation.TRANSLATION_X, MenuAnimationController.createSpringForce(), 0.0f, f, false);
            menuAnimationController.mAnimationEndPosition.x = f;
            float f2 = tuckedMenuPosition.y;
            menuAnimationController.springMenuWith(DynamicAnimation.TRANSLATION_Y, MenuAnimationController.createSpringForce(), 0.0f, f2, false);
            menuAnimationController.mAnimationEndPosition.y = f2;
            return;
        }
        MenuAnimationController menuAnimationController2 = this.mMenuAnimationController;
        menuAnimationController2.getClass();
        float f3 = tuckedMenuPosition.x;
        MenuView menuView = menuAnimationController2.mMenuView;
        menuView.setTranslationX(f3);
        menuAnimationController2.mAnimationEndPosition.x = f3;
        float f4 = tuckedMenuPosition.y;
        menuView.setTranslationY(f4);
        menuAnimationController2.mAnimationEndPosition.y = f4;
        menuAnimationController2.mAnimationEndPosition = tuckedMenuPosition;
        PointF menuPosition = this.mMenuViewAppearance.getMenuPosition();
        this.mBoundsInParent.offsetTo((int) menuPosition.x, (int) menuPosition.y);
        if (this.mIsMoveToTucked) {
            this.mMenuAnimationController.moveToEdgeAndHide();
        }
    }

    public final void onSizeChanged() {
        Rect rect = this.mBoundsInParent;
        int i = rect.left;
        int i2 = rect.top;
        MenuViewAppearance menuViewAppearance = this.mMenuViewAppearance;
        int i3 = menuViewAppearance.mSizeType;
        rect.set(i, i2, (i3 == 0 ? menuViewAppearance.mSmallIconSize : menuViewAppearance.mLargeIconSize) + ((i3 == 0 ? menuViewAppearance.mSmallPadding : menuViewAppearance.mLargePadding) * 2) + i, menuViewAppearance.getMenuHeight() + i2);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.height = this.mMenuViewAppearance.getMenuHeight();
        setLayoutParams(layoutParams);
    }

    public final void updateMenuMoveToTucked(boolean z) {
        this.mIsMoveToTucked = z;
        Prefs.putBoolean(this.mMenuViewModel.mInfoRepository.mContext, "HasAccessibilityFloatingMenuTucked", z);
        MenuViewLayer menuViewLayer = this.mMoveToTuckedListener;
        if (menuViewLayer == null || !z) {
            return;
        }
        Rect windowAvailableBounds = menuViewLayer.mMenuViewAppearance.getWindowAvailableBounds();
        int[] locationOnScreen = menuViewLayer.getLocationOnScreen();
        windowAvailableBounds.offset(locationOnScreen[0], locationOnScreen[1]);
        menuViewLayer.setClipBounds(windowAvailableBounds);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
