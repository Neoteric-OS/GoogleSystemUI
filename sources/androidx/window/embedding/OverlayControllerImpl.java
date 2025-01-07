package androidx.window.embedding;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.WindowMetrics;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import androidx.window.core.Bounds;
import androidx.window.core.ExtensionsUtil;
import androidx.window.embedding.EmbeddingBounds;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.ActivityStackAttributes;
import androidx.window.extensions.embedding.ActivityStackAttributesCalculatorParams;
import androidx.window.extensions.embedding.ParentContainerInfo;
import androidx.window.extensions.embedding.WindowAttributes;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OverlayControllerImpl {
    public final EmbeddingAdapter adapter;
    public final ActivityEmbeddingComponent embeddingExtension;
    public final ReentrantLock globalLock = new ReentrantLock();
    public final Map overlayTagToDefaultAttributesMap = new ArrayMap();
    public final ArrayMap overlayTagToCurrentAttributesMap = new ArrayMap();
    public final ArrayMap overlayTagToContainerMap = new ArrayMap();

    public OverlayControllerImpl(ActivityEmbeddingComponent activityEmbeddingComponent, EmbeddingAdapter embeddingAdapter) {
        this.embeddingExtension = activityEmbeddingComponent;
        new ArrayMap();
        int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
        if (safeVendorApiLevel < 8) {
            throw new UnsupportedOperationException(ListImplementation$$ExternalSyntheticOutline0.m("This API requires extension version ", 8, safeVendorApiLevel, ", but the device is on "));
        }
        activityEmbeddingComponent.setActivityStackAttributesCalculator(new Function() { // from class: androidx.window.embedding.OverlayControllerImpl$$ExternalSyntheticLambda0
            public final Object apply(Object obj) {
                OverlayControllerImpl overlayControllerImpl = OverlayControllerImpl.this;
                ActivityStackAttributesCalculatorParams activityStackAttributesCalculatorParams = (ActivityStackAttributesCalculatorParams) obj;
                ReentrantLock reentrantLock = overlayControllerImpl.globalLock;
                reentrantLock.lock();
                try {
                    ParentContainerInfo parentContainerInfo = activityStackAttributesCalculatorParams.getParentContainerInfo();
                    parentContainerInfo.getConfiguration();
                    parentContainerInfo.getWindowMetrics().getDensity();
                    WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.Companion;
                    WindowMetrics windowMetrics = parentContainerInfo.getWindowMetrics();
                    companion.getClass();
                    androidx.window.layout.WindowMetrics translateWindowMetrics$window_release = WindowMetricsCalculator.Companion.translateWindowMetrics$window_release(windowMetrics);
                    String activityStackTag = activityStackAttributesCalculatorParams.getActivityStackTag();
                    Bundle bundle = activityStackAttributesCalculatorParams.getLaunchOptions().getBundle("androidx.window.embedding.EmbeddingBounds");
                    OverlayAttributes overlayAttributes = null;
                    EmbeddingBounds embeddingBounds = bundle == null ? null : new EmbeddingBounds(new EmbeddingBounds.Alignment(bundle.getInt("androidx.window.embedding.EmbeddingBounds.alignment")), ActivityEmbeddingOptionsImpl.getDimension(bundle, "androidx.window.embedding.EmbeddingBounds.width"), ActivityEmbeddingOptionsImpl.getDimension(bundle, "androidx.window.embedding.EmbeddingBounds.height"));
                    if (embeddingBounds != null) {
                        overlayAttributes = new OverlayAttributes(embeddingBounds);
                    }
                    WindowMetricsCalculator.Companion.translateWindowMetrics$window_release(activityStackAttributesCalculatorParams.getParentContainerInfo().getWindowMetrics());
                    activityStackAttributesCalculatorParams.getParentContainerInfo().getConfiguration();
                    ExtensionsWindowLayoutInfoAdapter.translate$window_release(translateWindowMetrics$window_release, parentContainerInfo.getWindowLayoutInfo());
                    OverlayAttributes overlayAttributes2 = (OverlayAttributes) ((ArrayMap) overlayControllerImpl.overlayTagToDefaultAttributesMap).get(activityStackTag);
                    if (overlayAttributes2 != null) {
                        overlayAttributes = overlayAttributes2;
                    } else if (overlayAttributes == null) {
                        throw new IllegalArgumentException("Can't retrieve overlay attributes from launch options");
                    }
                    ReentrantLock reentrantLock2 = overlayControllerImpl.globalLock;
                    reentrantLock2.lock();
                    reentrantLock2.unlock();
                    overlayControllerImpl.overlayTagToCurrentAttributesMap.put(activityStackTag, overlayAttributes);
                    activityStackAttributesCalculatorParams.getLaunchOptions().putInt("androidx.window.embedding.ActivityStackAlignment", overlayAttributes.bounds.alignment.value);
                    return overlayControllerImpl.toActivityStackAttributes(overlayAttributes, parentContainerInfo);
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        activityEmbeddingComponent.registerActivityStackCallback(new ProfileInstallReceiver$$ExternalSyntheticLambda0(), new Consumer() { // from class: androidx.window.embedding.OverlayControllerImpl$$ExternalSyntheticLambda1
            public final void accept(Object obj) {
                OverlayControllerImpl overlayControllerImpl = OverlayControllerImpl.this;
                List list = (List) obj;
                ReentrantLock reentrantLock = overlayControllerImpl.globalLock;
                reentrantLock.lock();
                try {
                    Set keySet = overlayControllerImpl.overlayTagToContainerMap.keySet();
                    overlayControllerImpl.overlayTagToContainerMap.clear();
                    ArrayMap arrayMap = overlayControllerImpl.overlayTagToContainerMap;
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : list) {
                        if (((androidx.window.extensions.embedding.ActivityStack) obj2).getTag() != null) {
                            arrayList.add(obj2);
                        }
                    }
                    List<androidx.window.extensions.embedding.ActivityStack> list2 = CollectionsKt.toList(arrayList);
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                    for (androidx.window.extensions.embedding.ActivityStack activityStack : list2) {
                        String tag = activityStack.getTag();
                        Intrinsics.checkNotNull(tag);
                        arrayList2.add(new Pair(tag, activityStack));
                    }
                    MapsKt.putAll(arrayMap, arrayList2);
                    overlayControllerImpl.cleanUpDismissedOverlayContainerRecords(keySet);
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
        });
    }

    public final void cleanUpDismissedOverlayContainerRecords(Set set) {
        if (set.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Set keySet = this.overlayTagToContainerMap.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!keySet.contains(str) && this.embeddingExtension.getActivityStackToken(str) == null) {
                arrayList.add(str);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            ((ArrayMap) this.overlayTagToDefaultAttributesMap).remove(str2);
            this.overlayTagToCurrentAttributesMap.remove(str2);
        }
    }

    public final ActivityStackAttributes toActivityStackAttributes(OverlayAttributes overlayAttributes, ParentContainerInfo parentContainerInfo) {
        int i;
        int i2;
        Bounds offset;
        ActivityStackAttributes.Builder builder = new ActivityStackAttributes.Builder();
        int i3 = EmbeddingBounds.$r8$clinit;
        EmbeddingBounds embeddingBounds = overlayAttributes.bounds;
        parentContainerInfo.getConfiguration();
        parentContainerInfo.getConfiguration();
        parentContainerInfo.getWindowMetrics().getDensity();
        WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.Companion;
        WindowMetrics windowMetrics = parentContainerInfo.getWindowMetrics();
        companion.getClass();
        androidx.window.layout.WindowMetrics translateWindowMetrics$window_release = WindowMetricsCalculator.Companion.translateWindowMetrics$window_release(windowMetrics);
        Bounds bounds = new Bounds(translateWindowMetrics$window_release._bounds.toRect());
        WindowLayoutInfo translate$window_release = ExtensionsWindowLayoutInfoAdapter.translate$window_release(translateWindowMetrics$window_release, parentContainerInfo.getWindowLayoutInfo());
        EmbeddingBounds.Dimension.Ratio ratio = EmbeddingBounds.Dimension.DIMENSION_EXPANDED;
        EmbeddingBounds.Dimension dimension = embeddingBounds.width;
        boolean areEqual = Intrinsics.areEqual(dimension, ratio);
        EmbeddingBounds.Dimension dimension2 = embeddingBounds.height;
        if (areEqual && Intrinsics.areEqual(dimension2, ratio)) {
            offset = Bounds.EMPTY_BOUNDS;
        } else {
            if (embeddingBounds.shouldUseFallbackDimensionForWidth$window_release(translate$window_release)) {
                dimension = new EmbeddingBounds.Dimension.Ratio(0.5f);
            }
            if (embeddingBounds.shouldUseFallbackDimensionForHeight$window_release(translate$window_release)) {
                dimension2 = new EmbeddingBounds.Dimension.Ratio(0.5f);
            }
            EmbeddingBounds.Alignment alignment = embeddingBounds.alignment;
            EmbeddingBounds embeddingBounds2 = new EmbeddingBounds(alignment, dimension, dimension2);
            int width = bounds.getWidth();
            EmbeddingBounds.Dimension ratio2 = embeddingBounds2.shouldUseFallbackDimensionForWidth$window_release(translate$window_release) ? new EmbeddingBounds.Dimension.Ratio(0.5f) : dimension;
            if (ratio2 instanceof EmbeddingBounds.Dimension.Ratio) {
                i = (int) (((EmbeddingBounds.Dimension.Ratio) ratio2).value * width);
            } else if (ratio2 instanceof EmbeddingBounds.Dimension.Pixel) {
                i = Math.min(width, ((EmbeddingBounds.Dimension.Pixel) ratio2).value);
            } else {
                if (!Intrinsics.areEqual(ratio2, EmbeddingBounds.Dimension.DIMENSION_HINGE)) {
                    throw new IllegalArgumentException("Unhandled width dimension=" + dimension);
                }
                HardwareFoldingFeature onlyFoldingFeatureOrNull = EmbeddingBounds.getOnlyFoldingFeatureOrNull(translate$window_release);
                Intrinsics.checkNotNull(onlyFoldingFeatureOrNull);
                Rect rect = onlyFoldingFeatureOrNull.featureBounds.toRect();
                if (alignment.equals(EmbeddingBounds.Alignment.ALIGN_LEFT)) {
                    i = rect.left - bounds.left;
                } else {
                    if (!alignment.equals(EmbeddingBounds.Alignment.ALIGN_RIGHT)) {
                        throw new IllegalStateException("Unhandled condition to get height in pixel! embeddingBounds=" + embeddingBounds2 + " taskBounds=" + bounds + " windowLayoutInfo=" + translate$window_release);
                    }
                    i = bounds.right - rect.right;
                }
            }
            int height = bounds.getHeight();
            if (embeddingBounds2.shouldUseFallbackDimensionForHeight$window_release(translate$window_release)) {
                dimension2 = new EmbeddingBounds.Dimension.Ratio(0.5f);
            }
            if (dimension2 instanceof EmbeddingBounds.Dimension.Ratio) {
                i2 = (int) (((EmbeddingBounds.Dimension.Ratio) dimension2).value * height);
            } else if (dimension2 instanceof EmbeddingBounds.Dimension.Pixel) {
                i2 = Math.min(height, ((EmbeddingBounds.Dimension.Pixel) dimension2).value);
            } else {
                if (!Intrinsics.areEqual(dimension2, EmbeddingBounds.Dimension.DIMENSION_HINGE)) {
                    throw new IllegalArgumentException("Unhandled width dimension=" + dimension);
                }
                HardwareFoldingFeature onlyFoldingFeatureOrNull2 = EmbeddingBounds.getOnlyFoldingFeatureOrNull(translate$window_release);
                Intrinsics.checkNotNull(onlyFoldingFeatureOrNull2);
                Rect rect2 = onlyFoldingFeatureOrNull2.featureBounds.toRect();
                if (alignment.equals(EmbeddingBounds.Alignment.ALIGN_TOP)) {
                    i2 = rect2.top - bounds.top;
                } else {
                    if (!alignment.equals(EmbeddingBounds.Alignment.ALIGN_BOTTOM)) {
                        throw new IllegalStateException("Unhandled condition to get height in pixel! embeddingBounds=" + embeddingBounds2 + " taskBounds=" + bounds + " windowLayoutInfo=" + translate$window_release);
                    }
                    i2 = bounds.bottom - rect2.bottom;
                }
            }
            int width2 = bounds.getWidth();
            int height2 = bounds.getHeight();
            if (i == width2 && i2 == height2) {
                offset = Bounds.EMPTY_BOUNDS;
            } else {
                Bounds bounds2 = new Bounds(0, 0, i, i2);
                if (alignment.equals(EmbeddingBounds.Alignment.ALIGN_TOP)) {
                    offset = EmbeddingBounds.Companion.offset(bounds2, (width2 - i) / 2, 0);
                } else if (alignment.equals(EmbeddingBounds.Alignment.ALIGN_LEFT)) {
                    offset = EmbeddingBounds.Companion.offset(bounds2, 0, (height2 - i2) / 2);
                } else if (alignment.equals(EmbeddingBounds.Alignment.ALIGN_BOTTOM)) {
                    offset = EmbeddingBounds.Companion.offset(bounds2, (width2 - i) / 2, height2 - i2);
                } else {
                    if (!alignment.equals(EmbeddingBounds.Alignment.ALIGN_RIGHT)) {
                        throw new IllegalArgumentException("Unknown alignment: " + alignment);
                    }
                    offset = EmbeddingBounds.Companion.offset(bounds2, width2 - i, (height2 - i2) / 2);
                }
            }
        }
        ActivityStackAttributes.Builder relativeBounds = builder.setRelativeBounds(offset.toRect());
        int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
        if (safeVendorApiLevel >= 5) {
            return relativeBounds.setWindowAttributes(new WindowAttributes(Intrinsics.areEqual((Object) null, EmbeddingConfiguration$DimAreaBehavior.ON_ACTIVITY_STACK) ? 1 : 2)).build();
        }
        throw new UnsupportedOperationException(ListImplementation$$ExternalSyntheticOutline0.m("This API requires extension version ", 5, safeVendorApiLevel, ", but the device is on "));
    }
}
