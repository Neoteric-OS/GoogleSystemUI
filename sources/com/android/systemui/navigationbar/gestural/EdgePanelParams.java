package com.android.systemui.navigationbar.gestural;

import android.content.res.Resources;
import android.util.TypedValue;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.core.animation.Interpolator;
import androidx.core.animation.PathInterpolator;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EdgePanelParams {
    public BackIndicatorDimens activeIndicator;
    public PathInterpolator activeWidthInterpolator;
    public Interpolator arrowAngleInterpolator;
    public float arrowThickness;
    public BackIndicatorDimens cancelledIndicator;
    public BackIndicatorDimens committedIndicator;
    public float deactivationTriggerThreshold;
    public ClosedFloatRange dynamicTriggerThresholdRange;
    public PathInterpolator edgeCornerInterpolator;
    public BackIndicatorDimens entryIndicator;
    public PathInterpolator entryWidthInterpolator;
    public PathInterpolator entryWidthTowardsEdgeInterpolator;
    public PathInterpolator farCornerInterpolator;
    public int fingerOffset;
    public BackIndicatorDimens flungIndicator;
    public BackIndicatorDimens fullyStretchedIndicator;
    public PathInterpolator heightInterpolator;
    public PathInterpolator horizontalTranslationInterpolator;
    public int minArrowYPosition;
    public BackIndicatorDimens preThresholdIndicator;
    public float reactivationTriggerThreshold;
    public Resources resources;
    public float staticTriggerThreshold;
    public float swipeProgressThreshold;
    public PathInterpolator verticalTranslationInterpolator;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ArrowDimens {
        public final float alpha;
        public final Step alphaInterpolator;
        public final Step alphaSpring;
        public final Float height;
        public final SpringForce heightSpring;
        public final Float length;
        public final SpringForce lengthSpring;

        public ArrowDimens(Float f, Float f2, float f3, SpringForce springForce, SpringForce springForce2, Step step, Step step2) {
            this.length = f;
            this.height = f2;
            this.alpha = f3;
            this.heightSpring = springForce;
            this.lengthSpring = springForce2;
            this.alphaSpring = step;
            this.alphaInterpolator = step2;
        }

        public static ArrowDimens copy$default(ArrowDimens arrowDimens, Float f, Float f2, SpringForce springForce, SpringForce springForce2) {
            float f3 = arrowDimens.alpha;
            Step step = arrowDimens.alphaSpring;
            Step step2 = arrowDimens.alphaInterpolator;
            arrowDimens.getClass();
            return new ArrowDimens(f, f2, f3, springForce, springForce2, step, step2);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ArrowDimens)) {
                return false;
            }
            ArrowDimens arrowDimens = (ArrowDimens) obj;
            return Intrinsics.areEqual(this.length, arrowDimens.length) && Intrinsics.areEqual(this.height, arrowDimens.height) && Float.compare(this.alpha, arrowDimens.alpha) == 0 && Intrinsics.areEqual(this.heightSpring, arrowDimens.heightSpring) && Intrinsics.areEqual(this.lengthSpring, arrowDimens.lengthSpring) && Intrinsics.areEqual(this.alphaSpring, arrowDimens.alphaSpring) && Intrinsics.areEqual(this.alphaInterpolator, arrowDimens.alphaInterpolator);
        }

        public final int hashCode() {
            Float f = this.length;
            int hashCode = (f == null ? 0 : f.hashCode()) * 31;
            Float f2 = this.height;
            int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((hashCode + (f2 == null ? 0 : f2.hashCode())) * 31, this.alpha, 31);
            SpringForce springForce = this.heightSpring;
            int hashCode2 = (m + (springForce == null ? 0 : springForce.hashCode())) * 31;
            SpringForce springForce2 = this.lengthSpring;
            int hashCode3 = (hashCode2 + (springForce2 == null ? 0 : springForce2.hashCode())) * 31;
            Step step = this.alphaSpring;
            int hashCode4 = (hashCode3 + (step == null ? 0 : step.hashCode())) * 31;
            Step step2 = this.alphaInterpolator;
            return hashCode4 + (step2 != null ? step2.hashCode() : 0);
        }

        public final String toString() {
            return "ArrowDimens(length=" + this.length + ", height=" + this.height + ", alpha=" + this.alpha + ", heightSpring=" + this.heightSpring + ", lengthSpring=" + this.lengthSpring + ", alphaSpring=" + this.alphaSpring + ", alphaInterpolator=" + this.alphaInterpolator + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BackIndicatorDimens {
        public final ArrowDimens arrowDimens;
        public final BackgroundDimens backgroundDimens;
        public final Float horizontalTranslation;
        public final SpringForce horizontalTranslationSpring;
        public final float scale;
        public final Float scalePivotX;
        public final SpringForce scaleSpring;
        public final SpringForce verticalTranslationSpring;

        public /* synthetic */ BackIndicatorDimens(Float f, float f2, Float f3, ArrowDimens arrowDimens, BackgroundDimens backgroundDimens, SpringForce springForce, SpringForce springForce2, int i) {
            this(f, f2, (i & 4) != 0 ? null : f3, arrowDimens, backgroundDimens, (SpringForce) null, springForce, springForce2);
        }

        public static BackIndicatorDimens copy$default(BackIndicatorDimens backIndicatorDimens, ArrowDimens arrowDimens, BackgroundDimens backgroundDimens, SpringForce springForce, int i) {
            Float f = (i & 1) != 0 ? backIndicatorDimens.horizontalTranslation : null;
            float f2 = (i & 2) != 0 ? backIndicatorDimens.scale : 0.86f;
            Float f3 = (i & 4) != 0 ? backIndicatorDimens.scalePivotX : null;
            if ((i & 8) != 0) {
                arrowDimens = backIndicatorDimens.arrowDimens;
            }
            ArrowDimens arrowDimens2 = arrowDimens;
            SpringForce springForce2 = backIndicatorDimens.verticalTranslationSpring;
            SpringForce springForce3 = backIndicatorDimens.horizontalTranslationSpring;
            if ((i & 128) != 0) {
                springForce = backIndicatorDimens.scaleSpring;
            }
            backIndicatorDimens.getClass();
            return new BackIndicatorDimens(f, f2, f3, arrowDimens2, backgroundDimens, springForce2, springForce3, springForce);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BackIndicatorDimens)) {
                return false;
            }
            BackIndicatorDimens backIndicatorDimens = (BackIndicatorDimens) obj;
            return Intrinsics.areEqual(this.horizontalTranslation, backIndicatorDimens.horizontalTranslation) && Float.compare(this.scale, backIndicatorDimens.scale) == 0 && Intrinsics.areEqual(this.scalePivotX, backIndicatorDimens.scalePivotX) && Intrinsics.areEqual(this.arrowDimens, backIndicatorDimens.arrowDimens) && Intrinsics.areEqual(this.backgroundDimens, backIndicatorDimens.backgroundDimens) && Intrinsics.areEqual(this.verticalTranslationSpring, backIndicatorDimens.verticalTranslationSpring) && Intrinsics.areEqual(this.horizontalTranslationSpring, backIndicatorDimens.horizontalTranslationSpring) && Intrinsics.areEqual(this.scaleSpring, backIndicatorDimens.scaleSpring);
        }

        public final int hashCode() {
            Float f = this.horizontalTranslation;
            int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((f == null ? 0 : f.hashCode()) * 31, this.scale, 31);
            Float f2 = this.scalePivotX;
            int hashCode = (this.backgroundDimens.hashCode() + ((this.arrowDimens.hashCode() + ((m + (f2 == null ? 0 : f2.hashCode())) * 31)) * 31)) * 31;
            SpringForce springForce = this.verticalTranslationSpring;
            int hashCode2 = (hashCode + (springForce == null ? 0 : springForce.hashCode())) * 31;
            SpringForce springForce2 = this.horizontalTranslationSpring;
            int hashCode3 = (hashCode2 + (springForce2 == null ? 0 : springForce2.hashCode())) * 31;
            SpringForce springForce3 = this.scaleSpring;
            return hashCode3 + (springForce3 != null ? springForce3.hashCode() : 0);
        }

        public final String toString() {
            return "BackIndicatorDimens(horizontalTranslation=" + this.horizontalTranslation + ", scale=" + this.scale + ", scalePivotX=" + this.scalePivotX + ", arrowDimens=" + this.arrowDimens + ", backgroundDimens=" + this.backgroundDimens + ", verticalTranslationSpring=" + this.verticalTranslationSpring + ", horizontalTranslationSpring=" + this.horizontalTranslationSpring + ", scaleSpring=" + this.scaleSpring + ")";
        }

        public BackIndicatorDimens(Float f, float f2, Float f3, ArrowDimens arrowDimens, BackgroundDimens backgroundDimens, SpringForce springForce, SpringForce springForce2, SpringForce springForce3) {
            this.horizontalTranslation = f;
            this.scale = f2;
            this.scalePivotX = f3;
            this.arrowDimens = arrowDimens;
            this.backgroundDimens = backgroundDimens;
            this.verticalTranslationSpring = springForce;
            this.horizontalTranslationSpring = springForce2;
            this.scaleSpring = springForce3;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof EdgePanelParams) && Intrinsics.areEqual(this.resources, ((EdgePanelParams) obj).resources);
    }

    public final float getDimenFloat(int i) {
        TypedValue typedValue = new TypedValue();
        this.resources.getValue(i, typedValue, true);
        return typedValue.getFloat();
    }

    public final int hashCode() {
        return this.resources.hashCode();
    }

    public final String toString() {
        return "EdgePanelParams(resources=" + this.resources + ")";
    }

    public final void update(Resources resources) {
        this.resources = resources;
        this.arrowThickness = resources.getDimension(R.dimen.navigation_edge_arrow_thickness);
        this.resources.getDimensionPixelSize(R.dimen.navigation_edge_panel_padding);
        this.minArrowYPosition = this.resources.getDimensionPixelSize(R.dimen.navigation_edge_arrow_min_y);
        this.fingerOffset = this.resources.getDimensionPixelSize(R.dimen.navigation_edge_finger_offset);
        this.staticTriggerThreshold = this.resources.getDimension(R.dimen.navigation_edge_action_drag_threshold);
        this.reactivationTriggerThreshold = this.resources.getDimension(R.dimen.navigation_edge_action_reactivation_drag_threshold);
        float dimension = this.resources.getDimension(R.dimen.navigation_edge_action_deactivation_drag_threshold);
        this.deactivationTriggerThreshold = dimension;
        this.dynamicTriggerThresholdRange = new ClosedFloatRange(this.reactivationTriggerThreshold, -dimension);
        this.swipeProgressThreshold = this.resources.getDimension(R.dimen.navigation_edge_action_progress_threshold);
        this.entryWidthInterpolator = new PathInterpolator(0.19f, 1.27f, 0.71f, 0.86f);
        this.entryWidthTowardsEdgeInterpolator = new PathInterpolator(1.0f, -3.0f, 1.0f, 1.2f);
        this.activeWidthInterpolator = new PathInterpolator(0.7f, -0.24f, 0.48f, 1.21f);
        PathInterpolator pathInterpolator = this.entryWidthInterpolator;
        if (pathInterpolator == null) {
            pathInterpolator = null;
        }
        this.arrowAngleInterpolator = pathInterpolator;
        this.horizontalTranslationInterpolator = new PathInterpolator(0.2f, 1.0f, 1.0f, 1.0f);
        this.verticalTranslationInterpolator = new PathInterpolator(0.5f, 1.15f, 0.41f, 0.94f);
        this.farCornerInterpolator = new PathInterpolator(0.03f, 0.19f, 0.14f, 1.09f);
        this.edgeCornerInterpolator = new PathInterpolator(0.0f, 1.11f, 0.85f, 0.84f);
        this.heightInterpolator = new PathInterpolator(1.0f, 0.05f, 0.9f, -0.29f);
        SpringForce createSpring = EdgePanelParamsKt.createSpring(1500.0f, 0.29f);
        SpringForce createSpring2 = EdgePanelParamsKt.createSpring(1500.0f, 0.29f);
        SpringForce createSpring3 = EdgePanelParamsKt.createSpring(10000.0f, 1.0f);
        SpringForce createSpring4 = EdgePanelParamsKt.createSpring(10000.0f, 1.0f);
        SpringForce createSpring5 = EdgePanelParamsKt.createSpring(10000.0f, 1.0f);
        SpringForce createSpring6 = EdgePanelParamsKt.createSpring(10000.0f, 1.0f);
        Step step = new Step(EdgePanelParamsKt.createSpring(180.0f, 0.9f), EdgePanelParamsKt.createSpring(2000.0f, 0.6f));
        Step step2 = new Step(Float.valueOf(1.0f), Float.valueOf(0.0f));
        float dimension2 = this.resources.getDimension(R.dimen.navigation_edge_entry_margin);
        float dimenFloat = getDimenFloat(R.dimen.navigation_edge_entry_scale);
        float dimension3 = this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_background_width);
        SpringForce createSpring7 = EdgePanelParamsKt.createSpring(800.0f, 0.76f);
        SpringForce createSpring8 = EdgePanelParamsKt.createSpring(30000.0f, 1.0f);
        SpringForce createSpring9 = EdgePanelParamsKt.createSpring(120.0f, 0.8f);
        float dimension4 = this.resources.getDimension(R.dimen.navigation_edge_entry_arrow_length);
        float dimension5 = this.resources.getDimension(R.dimen.navigation_edge_entry_arrow_height);
        this.entryIndicator = new BackIndicatorDimens(Float.valueOf(dimension2), dimenFloat, Float.valueOf(dimension3), new ArrowDimens(Float.valueOf(dimension4), Float.valueOf(dimension5), 0.0f, EdgePanelParamsKt.createSpring(600.0f, 0.4f), EdgePanelParamsKt.createSpring(600.0f, 0.4f), step, step2), new BackgroundDimens(Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_entry_background_width)), this.resources.getDimension(R.dimen.navigation_edge_entry_background_height), this.resources.getDimension(R.dimen.navigation_edge_entry_edge_corners), this.resources.getDimension(R.dimen.navigation_edge_entry_far_corners), EdgePanelParamsKt.createSpring(450.0f, 0.65f), EdgePanelParamsKt.createSpring(1500.0f, 0.45f), EdgePanelParamsKt.createSpring(300.0f, 0.5f), EdgePanelParamsKt.createSpring(150.0f, 0.5f)), createSpring8, createSpring7, createSpring9);
        float dimension6 = this.resources.getDimension(R.dimen.navigation_edge_active_margin);
        float dimenFloat2 = getDimenFloat(R.dimen.navigation_edge_active_scale);
        SpringForce createSpring10 = EdgePanelParamsKt.createSpring(1000.0f, 0.8f);
        SpringForce createSpring11 = EdgePanelParamsKt.createSpring(325.0f, 0.55f);
        float dimension7 = this.resources.getDimension(R.dimen.navigation_edge_active_background_width);
        ArrowDimens arrowDimens = new ArrowDimens(Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_active_arrow_length)), Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_active_arrow_height)), 1.0f, createSpring2, createSpring, step, step2);
        float dimension8 = this.resources.getDimension(R.dimen.navigation_edge_active_background_width);
        this.activeIndicator = new BackIndicatorDimens(Float.valueOf(dimension6), dimenFloat2, Float.valueOf(dimension7), arrowDimens, new BackgroundDimens(Float.valueOf(dimension8), this.resources.getDimension(R.dimen.navigation_edge_active_background_height), this.resources.getDimension(R.dimen.navigation_edge_active_edge_corners), this.resources.getDimension(R.dimen.navigation_edge_active_far_corners), EdgePanelParamsKt.createSpring(850.0f, 0.75f), EdgePanelParamsKt.createSpring(10000.0f, 1.0f), EdgePanelParamsKt.createSpring(1200.0f, 0.3f), EdgePanelParamsKt.createSpring(2600.0f, 0.855f)), createSpring10, createSpring11, 32);
        float dimension9 = this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_margin);
        float dimenFloat3 = getDimenFloat(R.dimen.navigation_edge_pre_threshold_scale);
        float dimension10 = this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_background_width);
        SpringForce createSpring12 = EdgePanelParamsKt.createSpring(120.0f, 0.8f);
        SpringForce createSpring13 = EdgePanelParamsKt.createSpring(6000.0f, 1.0f);
        float dimension11 = this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_arrow_length);
        float dimension12 = this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_arrow_height);
        this.preThresholdIndicator = new BackIndicatorDimens(Float.valueOf(dimension9), dimenFloat3, Float.valueOf(dimension10), new ArrowDimens(Float.valueOf(dimension11), Float.valueOf(dimension12), 1.0f, EdgePanelParamsKt.createSpring(100.0f, 0.6f), EdgePanelParamsKt.createSpring(100.0f, 0.6f), step, step2), new BackgroundDimens(Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_background_width)), this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_background_height), this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_edge_corners), this.resources.getDimension(R.dimen.navigation_edge_pre_threshold_far_corners), EdgePanelParamsKt.createSpring(650.0f, 1.0f), EdgePanelParamsKt.createSpring(1500.0f, 0.45f), EdgePanelParamsKt.createSpring(300.0f, 1.0f), EdgePanelParamsKt.createSpring(250.0f, 0.5f)), createSpring13, createSpring12, 32);
        BackIndicatorDimens backIndicatorDimens = this.activeIndicator;
        BackIndicatorDimens backIndicatorDimens2 = backIndicatorDimens != null ? backIndicatorDimens : null;
        if (backIndicatorDimens == null) {
            backIndicatorDimens = null;
        }
        ArrowDimens copy$default = ArrowDimens.copy$default(backIndicatorDimens.arrowDimens, null, null, createSpring2, createSpring);
        BackIndicatorDimens backIndicatorDimens3 = this.activeIndicator;
        if (backIndicatorDimens3 == null) {
            backIndicatorDimens3 = null;
        }
        BackIndicatorDimens copy$default2 = BackIndicatorDimens.copy$default(backIndicatorDimens2, copy$default, BackgroundDimens.copy$default(backIndicatorDimens3.backgroundDimens, null, createSpring5, createSpring6, createSpring4, createSpring3, EdgePanelParamsKt.createSpring(1400.0f, 1.0f), 14), EdgePanelParamsKt.createSpring(5700.0f, 1.0f), 96);
        this.committedIndicator = copy$default2;
        SpringForce createSpring14 = EdgePanelParamsKt.createSpring(850.0f, 0.46f);
        SpringForce createSpring15 = EdgePanelParamsKt.createSpring(850.0f, 0.46f);
        BackIndicatorDimens backIndicatorDimens4 = this.activeIndicator;
        Float f = (backIndicatorDimens4 != null ? backIndicatorDimens4 : null).arrowDimens.length;
        if (backIndicatorDimens4 == null) {
            backIndicatorDimens4 = null;
        }
        ArrowDimens copy$default3 = ArrowDimens.copy$default(copy$default2.arrowDimens, f, backIndicatorDimens4.arrowDimens.height, createSpring15, createSpring14);
        BackIndicatorDimens backIndicatorDimens5 = this.committedIndicator;
        if (backIndicatorDimens5 == null) {
            backIndicatorDimens5 = null;
        }
        this.flungIndicator = BackIndicatorDimens.copy$default(copy$default2, copy$default3, BackgroundDimens.copy$default(backIndicatorDimens5.backgroundDimens, null, createSpring5, createSpring6, createSpring4, createSpring3, null, 543), null, 231);
        BackIndicatorDimens backIndicatorDimens6 = this.entryIndicator;
        BackIndicatorDimens backIndicatorDimens7 = backIndicatorDimens6 != null ? backIndicatorDimens6 : null;
        if (backIndicatorDimens6 == null) {
            backIndicatorDimens6 = null;
        }
        this.cancelledIndicator = BackIndicatorDimens.copy$default(backIndicatorDimens7, null, BackgroundDimens.copy$default(backIndicatorDimens6.backgroundDimens, Float.valueOf(0.0f), null, null, null, null, EdgePanelParamsKt.createSpring(450.0f, 1.0f), 494), null, 239);
        float dimension13 = this.resources.getDimension(R.dimen.navigation_edge_stretch_margin);
        Float f2 = null;
        this.fullyStretchedIndicator = new BackIndicatorDimens(Float.valueOf(dimension13), getDimenFloat(R.dimen.navigation_edge_stretch_scale), f2, new ArrowDimens(Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_stretched_arrow_length)), Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_stretched_arrow_height)), 1.0f, null, null, null, null), new BackgroundDimens(Float.valueOf(this.resources.getDimension(R.dimen.navigation_edge_stretch_background_width)), this.resources.getDimension(R.dimen.navigation_edge_stretch_background_height), this.resources.getDimension(R.dimen.navigation_edge_stretch_edge_corners), this.resources.getDimension(R.dimen.navigation_edge_stretch_far_corners), 1.0f, null, null, null, null, null), (SpringForce) null, (SpringForce) null, 4);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BackgroundDimens {
        public final float alpha;
        public final SpringForce alphaSpring;
        public final float edgeCornerRadius;
        public final SpringForce edgeCornerRadiusSpring;
        public final float farCornerRadius;
        public final SpringForce farCornerRadiusSpring;
        public final float height;
        public final SpringForce heightSpring;
        public final Float width;
        public final SpringForce widthSpring;

        public BackgroundDimens(Float f, float f2, float f3, float f4, float f5, SpringForce springForce, SpringForce springForce2, SpringForce springForce3, SpringForce springForce4, SpringForce springForce5) {
            this.width = f;
            this.height = f2;
            this.edgeCornerRadius = f3;
            this.farCornerRadius = f4;
            this.alpha = f5;
            this.widthSpring = springForce;
            this.heightSpring = springForce2;
            this.farCornerRadiusSpring = springForce3;
            this.edgeCornerRadiusSpring = springForce4;
            this.alphaSpring = springForce5;
        }

        public static BackgroundDimens copy$default(BackgroundDimens backgroundDimens, Float f, SpringForce springForce, SpringForce springForce2, SpringForce springForce3, SpringForce springForce4, SpringForce springForce5, int i) {
            Float f2 = (i & 1) != 0 ? backgroundDimens.width : f;
            float f3 = backgroundDimens.height;
            float f4 = backgroundDimens.edgeCornerRadius;
            float f5 = backgroundDimens.farCornerRadius;
            float f6 = (i & 16) != 0 ? backgroundDimens.alpha : 0.0f;
            SpringForce springForce6 = (i & 32) != 0 ? backgroundDimens.widthSpring : springForce;
            SpringForce springForce7 = (i & 64) != 0 ? backgroundDimens.heightSpring : springForce2;
            SpringForce springForce8 = (i & 128) != 0 ? backgroundDimens.farCornerRadiusSpring : springForce3;
            SpringForce springForce9 = (i & 256) != 0 ? backgroundDimens.edgeCornerRadiusSpring : springForce4;
            SpringForce springForce10 = (i & 512) != 0 ? backgroundDimens.alphaSpring : springForce5;
            backgroundDimens.getClass();
            return new BackgroundDimens(f2, f3, f4, f5, f6, springForce6, springForce7, springForce8, springForce9, springForce10);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BackgroundDimens)) {
                return false;
            }
            BackgroundDimens backgroundDimens = (BackgroundDimens) obj;
            return Intrinsics.areEqual(this.width, backgroundDimens.width) && Float.compare(this.height, backgroundDimens.height) == 0 && Float.compare(this.edgeCornerRadius, backgroundDimens.edgeCornerRadius) == 0 && Float.compare(this.farCornerRadius, backgroundDimens.farCornerRadius) == 0 && Float.compare(this.alpha, backgroundDimens.alpha) == 0 && Intrinsics.areEqual(this.widthSpring, backgroundDimens.widthSpring) && Intrinsics.areEqual(this.heightSpring, backgroundDimens.heightSpring) && Intrinsics.areEqual(this.farCornerRadiusSpring, backgroundDimens.farCornerRadiusSpring) && Intrinsics.areEqual(this.edgeCornerRadiusSpring, backgroundDimens.edgeCornerRadiusSpring) && Intrinsics.areEqual(this.alphaSpring, backgroundDimens.alphaSpring);
        }

        public final int hashCode() {
            Float f = this.width;
            int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((f == null ? 0 : f.hashCode()) * 31, this.height, 31), this.edgeCornerRadius, 31), this.farCornerRadius, 31), this.alpha, 31);
            SpringForce springForce = this.widthSpring;
            int hashCode = (m + (springForce == null ? 0 : springForce.hashCode())) * 31;
            SpringForce springForce2 = this.heightSpring;
            int hashCode2 = (hashCode + (springForce2 == null ? 0 : springForce2.hashCode())) * 31;
            SpringForce springForce3 = this.farCornerRadiusSpring;
            int hashCode3 = (hashCode2 + (springForce3 == null ? 0 : springForce3.hashCode())) * 31;
            SpringForce springForce4 = this.edgeCornerRadiusSpring;
            int hashCode4 = (hashCode3 + (springForce4 == null ? 0 : springForce4.hashCode())) * 31;
            SpringForce springForce5 = this.alphaSpring;
            return hashCode4 + (springForce5 != null ? springForce5.hashCode() : 0);
        }

        public final String toString() {
            return "BackgroundDimens(width=" + this.width + ", height=" + this.height + ", edgeCornerRadius=" + this.edgeCornerRadius + ", farCornerRadius=" + this.farCornerRadius + ", alpha=" + this.alpha + ", widthSpring=" + this.widthSpring + ", heightSpring=" + this.heightSpring + ", farCornerRadiusSpring=" + this.farCornerRadiusSpring + ", edgeCornerRadiusSpring=" + this.edgeCornerRadiusSpring + ", alphaSpring=" + this.alphaSpring + ")";
        }

        public /* synthetic */ BackgroundDimens(Float f, float f2, float f3, float f4, SpringForce springForce, SpringForce springForce2, SpringForce springForce3, SpringForce springForce4) {
            this(f, f2, f3, f4, 1.0f, springForce, springForce2, springForce3, springForce4, null);
        }
    }
}
