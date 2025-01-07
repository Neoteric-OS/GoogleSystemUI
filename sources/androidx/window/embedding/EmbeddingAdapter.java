package androidx.window.embedding;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Binder;
import android.util.Log;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.PredicateAdapter;
import androidx.window.embedding.DividerAttributes;
import androidx.window.embedding.EmbeddingAnimationBackground;
import androidx.window.embedding.EmbeddingAnimationParams;
import androidx.window.embedding.SplitAttributes;
import androidx.window.extensions.embedding.AnimationBackground;
import androidx.window.extensions.embedding.SplitAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmbeddingAdapter {
    public static final String TAG = Reflection.getOrCreateKotlinClass(EmbeddingAdapter.class).getSimpleName();
    public final VendorApiLevel1Impl api1Impl = new VendorApiLevel1Impl();
    public final VendorApiLevel2Impl api2Impl = new VendorApiLevel2Impl();
    public final VendorApiLevel2Impl api3Impl = new VendorApiLevel2Impl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VendorApiLevel1Impl {
        public static ActivityStack translateCompat(androidx.window.extensions.embedding.ActivityStack activityStack) {
            return new ActivityStack(activityStack.getActivities(), activityStack.isEmpty(), null);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VendorApiLevel2Impl {
        public /* synthetic */ VendorApiLevel2Impl() {
        }
    }

    static {
        new Binder();
    }

    public EmbeddingAdapter(PredicateAdapter predicateAdapter) {
    }

    public static EmbeddingAnimationParams.AnimationSpec translateToJetpackAnimationSpec(int i) {
        int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
        if (safeVendorApiLevel >= 7) {
            return i == 0 ? EmbeddingAnimationParams.AnimationSpec.JUMP_CUT : EmbeddingAnimationParams.AnimationSpec.DEFAULT;
        }
        throw new UnsupportedOperationException(ListImplementation$$ExternalSyntheticOutline0.m("This API requires extension version ", 7, safeVendorApiLevel, ", but the device is on "));
    }

    public final List translate(List list) {
        SplitInfo splitInfo;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            androidx.window.extensions.embedding.SplitInfo splitInfo2 = (androidx.window.extensions.embedding.SplitInfo) it.next();
            int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
            if (safeVendorApiLevel == 1) {
                ActivityStack translateCompat = VendorApiLevel1Impl.translateCompat(splitInfo2.getPrimaryActivityStack());
                ActivityStack translateCompat2 = VendorApiLevel1Impl.translateCompat(splitInfo2.getSecondaryActivityStack());
                SplitAttributes.SplitType splitType = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
                SplitAttributes.LayoutDirection layoutDirection = SplitAttributes.LayoutDirection.LOCALE;
                EmbeddingAnimationBackground.DefaultBackground defaultBackground = EmbeddingAnimationBackground.DEFAULT;
                EmbeddingAnimationParams.AnimationSpec animationSpec = EmbeddingAnimationParams.AnimationSpec.DEFAULT;
                EmbeddingAnimationParams embeddingAnimationParams = new EmbeddingAnimationParams(defaultBackground, animationSpec, animationSpec, animationSpec);
                DividerAttributes$Companion$NO_DIVIDER$1 dividerAttributes$Companion$NO_DIVIDER$1 = DividerAttributes.NO_DIVIDER;
                float splitRatio = splitInfo2.getSplitRatio();
                SplitAttributes.SplitType splitType2 = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
                if (splitRatio != 0.0f) {
                    splitType2 = SplitAttributes.SplitType.Companion.ratio(splitRatio);
                }
                splitInfo = new SplitInfo(translateCompat, translateCompat2, new SplitAttributes(splitType2, layoutDirection, embeddingAnimationParams, dividerAttributes$Companion$NO_DIVIDER$1));
            } else if (safeVendorApiLevel == 2) {
                VendorApiLevel1Impl vendorApiLevel1Impl = EmbeddingAdapter.this.api1Impl;
                splitInfo = new SplitInfo(VendorApiLevel1Impl.translateCompat(splitInfo2.getPrimaryActivityStack()), VendorApiLevel1Impl.translateCompat(splitInfo2.getSecondaryActivityStack()), translate$window_release(splitInfo2.getSplitAttributes()));
            } else if (3 > safeVendorApiLevel || safeVendorApiLevel >= 5) {
                splitInfo = new SplitInfo(translate$window_release(splitInfo2.getPrimaryActivityStack()), translate$window_release(splitInfo2.getSecondaryActivityStack()), translate$window_release(splitInfo2.getSplitAttributes()), null, splitInfo2.getSplitInfoToken());
            } else {
                VendorApiLevel1Impl vendorApiLevel1Impl2 = EmbeddingAdapter.this.api1Impl;
                splitInfo = new SplitInfo(VendorApiLevel1Impl.translateCompat(splitInfo2.getPrimaryActivityStack()), VendorApiLevel1Impl.translateCompat(splitInfo2.getSecondaryActivityStack()), translate$window_release(splitInfo2.getSplitAttributes()), splitInfo2.getToken(), null);
                int safeVendorApiLevel2 = ExtensionsUtil.getSafeVendorApiLevel();
                IntRange intRange = new IntRange(3, 4, 1);
                int i = intRange.last;
                if (3 > safeVendorApiLevel2 || safeVendorApiLevel2 > i) {
                    throw new UnsupportedOperationException("This API requires extension version " + intRange + ", but the device is on " + safeVendorApiLevel2);
                }
            }
            arrayList.add(splitInfo);
        }
        return arrayList;
    }

    public final ActivityStack translate$window_release(androidx.window.extensions.embedding.ActivityStack activityStack) {
        int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
        return (1 > safeVendorApiLevel || safeVendorApiLevel >= 5) ? new ActivityStack(activityStack.getActivities(), activityStack.isEmpty(), activityStack.getActivityStackToken()) : VendorApiLevel1Impl.translateCompat(activityStack);
    }

    public static SplitAttributes translate$window_release(androidx.window.extensions.embedding.SplitAttributes splitAttributes) {
        SplitAttributes.SplitType ratio;
        DividerAttributes fixedDividerAttributes;
        SplitAttributes.SplitType splitType = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
        SplitAttributes.LayoutDirection layoutDirection = SplitAttributes.LayoutDirection.LOCALE;
        EmbeddingAnimationBackground embeddingAnimationBackground = EmbeddingAnimationBackground.DEFAULT;
        EmbeddingAnimationParams.AnimationSpec animationSpec = EmbeddingAnimationParams.AnimationSpec.DEFAULT;
        EmbeddingAnimationParams embeddingAnimationParams = new EmbeddingAnimationParams(embeddingAnimationBackground, animationSpec, animationSpec, animationSpec);
        DividerAttributes dividerAttributes = DividerAttributes.NO_DIVIDER;
        SplitAttributes.SplitType.RatioSplitType splitType2 = splitAttributes.getSplitType();
        if (splitType2 instanceof SplitAttributes.SplitType.HingeSplitType) {
            ratio = SplitAttributes.SplitType.SPLIT_TYPE_HINGE;
        } else if (splitType2 instanceof SplitAttributes.SplitType.ExpandContainersSplitType) {
            ratio = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
        } else {
            if (!(splitType2 instanceof SplitAttributes.SplitType.RatioSplitType)) {
                throw new IllegalArgumentException("Unknown split type: " + splitType2);
            }
            ratio = SplitAttributes.SplitType.Companion.ratio(splitType2.getRatio());
        }
        int layoutDirection2 = splitAttributes.getLayoutDirection();
        if (layoutDirection2 == 0) {
            layoutDirection = SplitAttributes.LayoutDirection.LEFT_TO_RIGHT;
        } else if (layoutDirection2 == 1) {
            layoutDirection = SplitAttributes.LayoutDirection.RIGHT_TO_LEFT;
        } else if (layoutDirection2 != 3) {
            if (layoutDirection2 == 4) {
                layoutDirection = SplitAttributes.LayoutDirection.TOP_TO_BOTTOM;
            } else if (layoutDirection2 == 5) {
                layoutDirection = SplitAttributes.LayoutDirection.BOTTOM_TO_TOP;
            } else {
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(layoutDirection2, "Unknown layout direction: "));
            }
        }
        int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
        if (5 <= safeVendorApiLevel && safeVendorApiLevel < 7) {
            AnimationBackground.ColorBackground animationBackground = splitAttributes.getAnimationBackground();
            int safeVendorApiLevel2 = ExtensionsUtil.getSafeVendorApiLevel();
            if (safeVendorApiLevel2 >= 5) {
                embeddingAnimationParams = new EmbeddingAnimationParams(animationBackground instanceof AnimationBackground.ColorBackground ? new EmbeddingAnimationBackground.ColorBackground(animationBackground.getColor()) : embeddingAnimationBackground, animationSpec, animationSpec, animationSpec);
            } else {
                throw new UnsupportedOperationException(AnnotationValue$1$$ExternalSyntheticOutline0.m(safeVendorApiLevel2, "This API requires extension version 5, but the device is on "));
            }
        }
        if (ExtensionsUtil.getSafeVendorApiLevel() >= 7) {
            AnimationBackground.ColorBackground animationBackground2 = splitAttributes.getAnimationParams().getAnimationBackground();
            int safeVendorApiLevel3 = ExtensionsUtil.getSafeVendorApiLevel();
            if (safeVendorApiLevel3 >= 5) {
                if (animationBackground2 instanceof AnimationBackground.ColorBackground) {
                    embeddingAnimationBackground = new EmbeddingAnimationBackground.ColorBackground(animationBackground2.getColor());
                }
                embeddingAnimationParams = new EmbeddingAnimationParams(embeddingAnimationBackground, translateToJetpackAnimationSpec(splitAttributes.getAnimationParams().getOpenAnimationResId()), translateToJetpackAnimationSpec(splitAttributes.getAnimationParams().getCloseAnimationResId()), translateToJetpackAnimationSpec(splitAttributes.getAnimationParams().getChangeAnimationResId()));
            } else {
                throw new UnsupportedOperationException(AnnotationValue$1$$ExternalSyntheticOutline0.m(safeVendorApiLevel3, "This API requires extension version 5, but the device is on "));
            }
        }
        if (ExtensionsUtil.getSafeVendorApiLevel() >= 6) {
            androidx.window.extensions.embedding.DividerAttributes dividerAttributes2 = splitAttributes.getDividerAttributes();
            int safeVendorApiLevel4 = ExtensionsUtil.getSafeVendorApiLevel();
            if (safeVendorApiLevel4 < 6) {
                throw new UnsupportedOperationException(ListImplementation$$ExternalSyntheticOutline0.m("This API requires extension version ", 6, safeVendorApiLevel4, ", but the device is on "));
            }
            if (dividerAttributes2 != null) {
                int dividerType = dividerAttributes2.getDividerType();
                if (dividerType == 1) {
                    int widthDp = dividerAttributes2.getWidthDp();
                    DividerAttributes.Companion.access$validateWidth(widthDp);
                    int dividerColor = dividerAttributes2.getDividerColor();
                    DividerAttributes.Companion.access$validateColor(dividerColor);
                    fixedDividerAttributes = new DividerAttributes.FixedDividerAttributes(widthDp, dividerColor);
                } else if (dividerType != 2) {
                    Log.w(TAG, "Unknown divider type " + dividerAttributes2 + ".dividerType, default to fixed divider type");
                    int widthDp2 = dividerAttributes2.getWidthDp();
                    DividerAttributes.Companion.access$validateWidth(widthDp2);
                    int dividerColor2 = dividerAttributes2.getDividerColor();
                    DividerAttributes.Companion.access$validateColor(dividerColor2);
                    fixedDividerAttributes = new DividerAttributes.FixedDividerAttributes(widthDp2, dividerColor2);
                } else {
                    DividerAttributes.DragRange dragRange = DividerAttributes.DragRange.DRAG_RANGE_SYSTEM_DEFAULT;
                    int widthDp3 = dividerAttributes2.getWidthDp();
                    DividerAttributes.Companion.access$validateWidth(widthDp3);
                    int dividerColor3 = dividerAttributes2.getDividerColor();
                    DividerAttributes.Companion.access$validateColor(dividerColor3);
                    if (dividerAttributes2.getPrimaryMinRatio() != -1.0f || dividerAttributes2.getPrimaryMaxRatio() != -1.0f) {
                        dragRange = new DividerAttributes.DragRange.SplitRatioDragRange(dividerAttributes2.getPrimaryMinRatio(), dividerAttributes2.getPrimaryMaxRatio());
                    }
                    dividerAttributes = new DividerAttributes.DraggableDividerAttributes(widthDp3, dividerColor3, dragRange, ExtensionsUtil.getSafeVendorApiLevel() >= 7 && dividerAttributes2.isDraggingToFullscreenAllowed());
                }
                dividerAttributes = fixedDividerAttributes;
            }
        }
        return new SplitAttributes(ratio, layoutDirection, embeddingAnimationParams, dividerAttributes);
    }
}
