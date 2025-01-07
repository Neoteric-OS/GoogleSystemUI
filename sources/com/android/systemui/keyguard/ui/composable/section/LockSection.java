package com.android.systemui.keyguard.ui.composable.section;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import com.android.compose.animation.scene.ContentScope;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder;
import com.android.systemui.keyguard.ui.composable.blueprint.BlueprintAlignmentLines$LockIcon;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryBackgroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LongPressHandlingViewLogger;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockSection {
    public final CoroutineScope applicationScope;
    public final AuthController authController;
    public final Lazy deviceEntryBackgroundViewModel;
    public final Lazy deviceEntryForegroundViewModel;
    public final Lazy deviceEntryIconViewModel;
    public final Lazy falsingManager;
    public final FeatureFlagsClassic featureFlags;
    public final LogBuffer logBuffer;
    public final Lazy vibratorHelper;
    public final WindowManager windowManager;

    public LockSection(CoroutineScope coroutineScope, WindowManager windowManager, AuthController authController, FeatureFlagsClassic featureFlagsClassic, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, LogBuffer logBuffer) {
        this.applicationScope = coroutineScope;
        this.windowManager = windowManager;
        this.authController = authController;
        this.featureFlags = featureFlagsClassic;
        this.deviceEntryIconViewModel = lazy2;
        this.deviceEntryForegroundViewModel = lazy3;
        this.deviceEntryBackgroundViewModel = lazy4;
        this.falsingManager = lazy5;
        this.vibratorHelper = lazy6;
        this.logBuffer = logBuffer;
    }

    /* renamed from: LockIcon-BAq54LU, reason: not valid java name */
    public final void m829LockIconBAq54LU(final ContentScope contentScope, final Color color, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(957501896);
        if ((i2 & 1) != 0) {
            color = null;
        }
        if ((i2 & 2) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        AndroidView_androidKt.AndroidView(new Function1() { // from class: com.android.systemui.keyguard.ui.composable.section.LockSection$LockIcon$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DeviceEntryIconView deviceEntryIconView = new DeviceEntryIconView((Context) obj, new LongPressHandlingViewLogger(LockSection.this.logBuffer, "LockSection"));
                LockSection lockSection = LockSection.this;
                Color color2 = color;
                deviceEntryIconView.setId(R.id.device_entry_icon_view);
                DeviceEntryIconViewBinder.m828bind9Oi015Q(lockSection.applicationScope, deviceEntryIconView, (DeviceEntryIconViewModel) lockSection.deviceEntryIconViewModel.get(), (DeviceEntryForegroundViewModel) lockSection.deviceEntryForegroundViewModel.get(), (DeviceEntryBackgroundViewModel) lockSection.deviceEntryBackgroundViewModel.get(), (FalsingManager) lockSection.falsingManager.get(), (VibratorHelper) lockSection.vibratorHelper.get(), color2);
                return deviceEntryIconView;
            }
        }, LayoutModifierKt.layout(contentScope.element(modifier, LockSectionKt.LockIconElementKey), new Function3() { // from class: com.android.systemui.keyguard.ui.composable.section.LockSection$LockIcon$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Pair pair;
                MeasureScope measureScope = (MeasureScope) obj;
                Measurable measurable = (Measurable) obj2;
                long j = ((Constraints) obj3).value;
                LockSection lockSection = LockSection.this;
                Context context2 = context;
                float f = lockSection.windowManager.getCurrentWindowMetrics().getBounds().right;
                UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                lockSection.featureFlags.getClass();
                int i3 = (int) ((DisplayMetrics.DENSITY_DEVICE_STABLE / 160.0f) * 36);
                AuthController authController = lockSection.authController;
                Point udfpsLocation = authController.getUdfpsLocation();
                if (!authController.isUdfpsSupported() || udfpsLocation == null) {
                    pair = new Pair(new IntOffset((((int) (f / 2)) << 32) | (((int) (r0.bottom - ((context2.getResources().getDimensionPixelSize(R.dimen.lock_icon_margin_bottom) + i3) * r13))) & 4294967295L)), Integer.valueOf((int) (i3 * authController.mScaleFactor)));
                } else {
                    pair = new Pair(new IntOffset((udfpsLocation.y & 4294967295L) | (udfpsLocation.x << 32)), Integer.valueOf((int) authController.getUdfpsRadius()));
                }
                long j2 = ((IntOffset) pair.component1()).packedValue;
                int intValue = ((Number) pair.component2()).intValue();
                int i4 = (int) (j2 >> 32);
                int i5 = i4 - intValue;
                int i6 = (int) (j2 & 4294967295L);
                int i7 = i6 - intValue;
                int i8 = i4 + intValue;
                int i9 = i6 + intValue;
                IntRect intRect = new IntRect(i5, i7, i8, i9);
                final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints.Companion.m661fixedJhjzzOo(intRect.getWidth(), intRect.getHeight()));
                return measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.mapOf(new Pair(BlueprintAlignmentLines$LockIcon.Left, Integer.valueOf(i5)), new Pair(BlueprintAlignmentLines$LockIcon.Top, Integer.valueOf(i7)), new Pair(BlueprintAlignmentLines$LockIcon.Right, Integer.valueOf(i8)), new Pair(BlueprintAlignmentLines$LockIcon.Bottom, Integer.valueOf(i9))), new Function1() { // from class: com.android.systemui.keyguard.ui.composable.section.LockSection$LockIcon$3.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        ((Placeable.PlacementScope) obj4).place(Placeable.this, 0, 0, 0.0f);
                        return Unit.INSTANCE;
                    }
                });
            }
        }), null, composerImpl, 0, 4);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Color color2 = color;
            final Modifier modifier2 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.keyguard.ui.composable.section.LockSection$LockIcon$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    LockSection.this.m829LockIconBAq54LU(contentScope, color2, modifier2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
