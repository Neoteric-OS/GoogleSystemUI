package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Trace;
import android.util.LruCache;
import android.view.Display;
import android.view.DisplayCutout;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.StatusBarInsetsCommand;
import com.android.systemui.SysUICutoutInformation;
import com.android.systemui.SysUICutoutProvider;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.leak.RotationUtils;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarContentInsetsProvider implements CallbackController, ConfigurationController.ConfigurationListener, Dumpable {
    public final ConfigurationController configurationController;
    public final Context context;
    public final Map marginBottomOverrides;
    public final SysUICutoutProvider sysUICutoutProvider;
    public final LruCache insetsCache = new LruCache(16);
    public final Set listeners = new LinkedHashSet();
    public final Lazy isPrivacyDotEnabled$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider$isPrivacyDotEnabled$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(StatusBarContentInsetsProvider.this.context.getResources().getBoolean(R.bool.config_enablePrivacyDot));
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CacheKey {
        public final DisplayCutout displayCutout;
        public final Rect displaySize;
        public final int rotation;

        public CacheKey(int i, Rect rect, DisplayCutout displayCutout) {
            this.rotation = i;
            this.displaySize = rect;
            this.displayCutout = displayCutout;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) obj;
            return this.rotation == cacheKey.rotation && Intrinsics.areEqual(this.displaySize, cacheKey.displaySize) && Intrinsics.areEqual(this.displayCutout, cacheKey.displayCutout);
        }

        public final int hashCode() {
            int hashCode = (this.displaySize.hashCode() + (Integer.hashCode(this.rotation) * 31)) * 31;
            DisplayCutout displayCutout = this.displayCutout;
            return hashCode + (displayCutout == null ? 0 : displayCutout.hashCode());
        }

        public final String toString() {
            return "CacheKey(rotation=" + this.rotation + ", displaySize=" + this.displaySize + ", displayCutout=" + this.displayCutout + ")";
        }
    }

    public StatusBarContentInsetsProvider(Context context, ConfigurationController configurationController, DumpManager dumpManager, CommandRegistry commandRegistry, SysUICutoutProvider sysUICutoutProvider) {
        this.context = context;
        this.configurationController = configurationController;
        this.sysUICutoutProvider = sysUICutoutProvider;
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
        DumpManager.registerDumpable$default(dumpManager, "StatusBarInsetsProvider", this);
        commandRegistry.registerCommand("status-bar-insets", new Function0() { // from class: com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider.1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider$1$1, reason: invalid class name and collision with other inner class name */
            public final class C02121 {
                public final /* synthetic */ StatusBarContentInsetsProvider this$0;

                public C02121(StatusBarContentInsetsProvider statusBarContentInsetsProvider) {
                    this.this$0 = statusBarContentInsetsProvider;
                }
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new StatusBarInsetsCommand(new C02121(StatusBarContentInsetsProvider.this));
            }
        });
        this.marginBottomOverrides = new LinkedHashMap();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.listeners.add((StatusBarContentInsetsChangedListener) obj);
    }

    public final boolean currentRotationHasCornerCutout() {
        Display display = this.context.getDisplay();
        if (display == null) {
            throw new IllegalStateException("Required value was null.");
        }
        DisplayCutout cutout = display.getCutout();
        if (cutout == null) {
            return false;
        }
        Rect boundingRectTop = cutout.getBoundingRectTop();
        Point point = new Point();
        Display display2 = this.context.getDisplay();
        if (display2 == null) {
            throw new IllegalStateException("Required value was null.");
        }
        display2.getRealSize(point);
        return boundingRectTop.left <= 0 || boundingRectTop.right >= point.x;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        for (Map.Entry entry : this.insetsCache.snapshot().entrySet()) {
            printWriter.println(((CacheKey) entry.getKey()) + " -> " + ((Rect) entry.getValue()));
        }
        printWriter.println(this.insetsCache);
        printWriter.println("Bottom margin overrides: " + this.marginBottomOverrides);
    }

    public final Rect getAndSetCalculatedAreaForRotation(int i, SysUICutoutInformation sysUICutoutInformation, Resources resources, CacheKey cacheKey) {
        int max;
        int i2;
        int i3;
        int dimensionPixelSize;
        int exactRotation = RotationUtils.getExactRotation(this.context);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.rounded_corner_content_padding);
        Lazy lazy = this.isPrivacyDotEnabled$delegate;
        int dimensionPixelSize3 = ((Boolean) lazy.getValue()).booleanValue() ? resources.getDimensionPixelSize(R.dimen.ongoing_appops_dot_min_padding) : 0;
        int dimensionPixelSize4 = ((Boolean) lazy.getValue()).booleanValue() ? resources.getDimensionPixelSize(R.dimen.ongoing_appops_dot_diameter) : 0;
        ConfigurationControllerImpl configurationControllerImpl = (ConfigurationControllerImpl) this.configurationController;
        if (configurationControllerImpl.layoutDirection == 1) {
            i2 = Math.max(dimensionPixelSize3, dimensionPixelSize2);
            max = dimensionPixelSize2;
        } else {
            max = Math.max(dimensionPixelSize3, dimensionPixelSize2);
            i2 = dimensionPixelSize2;
        }
        Integer num = (Integer) this.marginBottomOverrides.get(Integer.valueOf(i));
        if (num != null) {
            dimensionPixelSize = num.intValue();
        } else {
            if (i == 0) {
                i3 = R.dimen.status_bar_bottom_aligned_margin_rotation_0;
            } else if (i == 1) {
                i3 = R.dimen.status_bar_bottom_aligned_margin_rotation_90;
            } else if (i == 2) {
                i3 = R.dimen.status_bar_bottom_aligned_margin_rotation_180;
            } else {
                if (i != 3) {
                    throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown rotation: "));
                }
                i3 = R.dimen.status_bar_bottom_aligned_margin_rotation_270;
            }
            dimensionPixelSize = resources.getDimensionPixelSize(i3);
        }
        Rect calculateInsetsForRotationWithRotatedResources = StatusBarContentInsetsProviderKt.calculateInsetsForRotationWithRotatedResources(exactRotation, i, sysUICutoutInformation, this.context.getResources().getConfiguration().windowConfiguration.getMaxBounds(), SystemBarUtils.getStatusBarHeightForRotation(this.context, i), i2, max, configurationControllerImpl.layoutDirection == 1, dimensionPixelSize4, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.status_bar_icon_size_sp));
        this.insetsCache.put(cacheKey, calculateInsetsForRotationWithRotatedResources);
        return calculateInsetsForRotationWithRotatedResources;
    }

    public final Rect getBoundingRectForPrivacyChipForRotation(int i, DisplayCutout displayCutout) {
        Rect rect = (Rect) this.insetsCache.get(getCacheKey(i, displayCutout));
        if (rect == null) {
            rect = getStatusBarContentAreaForRotation(i);
        }
        Resources resourcesForRotation = RotationUtils.getResourcesForRotation(i, this.context);
        int dimensionPixelSize = resourcesForRotation.getDimensionPixelSize(R.dimen.ongoing_appops_dot_diameter);
        int dimensionPixelSize2 = resourcesForRotation.getDimensionPixelSize(R.dimen.ongoing_appops_chip_max_width);
        boolean z = ((ConfigurationControllerImpl) this.configurationController).layoutDirection == 1;
        Intrinsics.checkNotNull(rect);
        return StatusBarContentInsetsProviderKt.getPrivacyChipBoundingRectForInsets(rect, dimensionPixelSize, dimensionPixelSize2, z);
    }

    public final CacheKey getCacheKey(int i, DisplayCutout displayCutout) {
        return new CacheKey(i, new Rect(this.context.getResources().getConfiguration().windowConfiguration.getMaxBounds()), displayCutout);
    }

    public final Rect getStatusBarContentAreaForRotation(int i) {
        SysUICutoutInformation cutoutInfoForCurrentDisplayAndRotation = this.sysUICutoutProvider.cutoutInfoForCurrentDisplayAndRotation();
        CacheKey cacheKey = getCacheKey(i, cutoutInfoForCurrentDisplayAndRotation != null ? cutoutInfoForCurrentDisplayAndRotation.cutout : null);
        Rect rect = (Rect) this.insetsCache.get(cacheKey);
        return rect == null ? getAndSetCalculatedAreaForRotation(i, cutoutInfoForCurrentDisplayAndRotation, RotationUtils.getResourcesForRotation(i, this.context), cacheKey) : rect;
    }

    public final Insets getStatusBarContentInsetsForCurrentRotation() {
        int exactRotation = RotationUtils.getExactRotation(this.context);
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("StatusBarContentInsetsProvider.getStatusBarContentInsetsForRotation");
        }
        try {
            SysUICutoutInformation cutoutInfoForCurrentDisplayAndRotation = this.sysUICutoutProvider.cutoutInfoForCurrentDisplayAndRotation();
            CacheKey cacheKey = getCacheKey(exactRotation, cutoutInfoForCurrentDisplayAndRotation != null ? cutoutInfoForCurrentDisplayAndRotation.cutout : null);
            Rect maxBounds = this.context.getResources().getConfiguration().windowConfiguration.getMaxBounds();
            Point point = new Point(maxBounds.width(), maxBounds.height());
            int exactRotation2 = RotationUtils.getExactRotation(this.context);
            if (exactRotation2 != 0 && exactRotation2 != 2) {
                int i = point.y;
                point.y = point.x;
                point.x = i;
            }
            int i2 = (exactRotation == 0 || exactRotation == 2) ? point.x : point.y;
            Rect rect = (Rect) this.insetsCache.get(cacheKey);
            if (rect == null) {
                rect = getAndSetCalculatedAreaForRotation(exactRotation, cutoutInfoForCurrentDisplayAndRotation, RotationUtils.getResourcesForRotation(exactRotation, this.context), cacheKey);
            }
            Insets of = Insets.of(rect.left, rect.top, i2 - rect.right, 0);
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            return of;
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final void notifyInsetsChanged() {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((StatusBarContentInsetsChangedListener) it.next()).onStatusBarContentInsetsChanged();
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onDensityOrFontScaleChanged() {
        this.insetsCache.evictAll();
        notifyInsetsChanged();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onMaxBoundsChanged() {
        notifyInsetsChanged();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onThemeChanged() {
        this.insetsCache.evictAll();
        notifyInsetsChanged();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.listeners.remove((StatusBarContentInsetsChangedListener) obj);
    }
}
