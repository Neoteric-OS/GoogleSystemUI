package com.android.systemui.statusbar.phone;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.ArrayMap;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DarkIconDispatcherImpl implements LightBarTransitionsController.DarkIntensityApplier, DarkIconDispatcher, Dumpable {
    public float mDarkIntensity;
    public final int mDarkModeIconColorSingleTone;
    public final int mLightModeIconColorSingleTone;
    public final LightBarTransitionsController mTransitionsController;
    public final ArrayList mTintAreas = new ArrayList();
    public final ArrayMap mReceivers = new ArrayMap();
    public int mIconTint = -1;
    public int mContrastTint = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
    public final int mDarkModeContrastColor = -1;
    public final int mLightModeContrastColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
    public final StateFlowImpl mDarkChangeFlow = StateFlowKt.MutableStateFlow(SysuiDarkIconDispatcher$DarkChange.EMPTY);

    public DarkIconDispatcherImpl(Context context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15, DumpManager dumpManager) {
        this.mDarkModeIconColorSingleTone = context.getColor(R.color.dark_mode_icon_color_single_tone);
        this.mLightModeIconColorSingleTone = context.getColor(R.color.light_mode_icon_color_single_tone);
        this.mTransitionsController = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15.create(this);
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "DarkIconDispatcherImpl", this);
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher
    public final void addDarkReceiver(DarkIconDispatcher.DarkReceiver darkReceiver) {
        this.mReceivers.put(darkReceiver, darkReceiver);
        darkReceiver.onDarkChanged(this.mTintAreas, this.mDarkIntensity, this.mIconTint);
        darkReceiver.onDarkChangedWithContrast(this.mTintAreas, this.mIconTint, this.mContrastTint);
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher
    public final void applyDark(DarkIconDispatcher.DarkReceiver darkReceiver) {
        ((DarkIconDispatcher.DarkReceiver) this.mReceivers.get(darkReceiver)).onDarkChanged(this.mTintAreas, this.mDarkIntensity, this.mIconTint);
        ((DarkIconDispatcher.DarkReceiver) this.mReceivers.get(darkReceiver)).onDarkChangedWithContrast(this.mTintAreas, this.mIconTint, this.mContrastTint);
    }

    @Override // com.android.systemui.statusbar.phone.LightBarTransitionsController.DarkIntensityApplier
    public final void applyDarkIntensity(float f) {
        this.mDarkIntensity = f;
        ArgbEvaluator argbEvaluator = ArgbEvaluator.getInstance();
        this.mIconTint = ((Integer) argbEvaluator.evaluate(f, Integer.valueOf(this.mLightModeIconColorSingleTone), Integer.valueOf(this.mDarkModeIconColorSingleTone))).intValue();
        this.mContrastTint = ((Integer) argbEvaluator.evaluate(f, Integer.valueOf(this.mLightModeContrastColor), Integer.valueOf(this.mDarkModeContrastColor))).intValue();
        applyIconTint();
    }

    public final void applyIconTint() {
        SysuiDarkIconDispatcher$DarkChange sysuiDarkIconDispatcher$DarkChange = new SysuiDarkIconDispatcher$DarkChange(this.mIconTint, this.mTintAreas);
        StateFlowImpl stateFlowImpl = this.mDarkChangeFlow;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, sysuiDarkIconDispatcher$DarkChange);
        for (int i = 0; i < this.mReceivers.size(); i++) {
            ((DarkIconDispatcher.DarkReceiver) this.mReceivers.valueAt(i)).onDarkChanged(this.mTintAreas, this.mDarkIntensity, this.mIconTint);
            ((DarkIconDispatcher.DarkReceiver) this.mReceivers.valueAt(i)).onDarkChangedWithContrast(this.mTintAreas, this.mIconTint, this.mContrastTint);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "DarkIconDispatcher: ", "  mIconTint: 0x");
        m.append(Integer.toHexString(this.mIconTint));
        printWriter.println(m.toString());
        printWriter.println("  mContrastTint: 0x" + Integer.toHexString(this.mContrastTint));
        printWriter.println("  mDarkModeIconColorSingleTone: 0x" + Integer.toHexString(this.mDarkModeIconColorSingleTone));
        printWriter.println("  mLightModeIconColorSingleTone: 0x" + Integer.toHexString(this.mLightModeIconColorSingleTone));
        printWriter.println("  mDarkModeContrastColor: 0x" + Integer.toHexString(this.mDarkModeContrastColor));
        printWriter.println("  mLightModeContrastColor: 0x" + Integer.toHexString(this.mLightModeContrastColor));
        printWriter.println("  mDarkIntensity: " + this.mDarkIntensity + "f");
        StringBuilder sb = new StringBuilder("  mTintAreas: ");
        sb.append(this.mTintAreas);
        printWriter.println(sb.toString());
    }

    @Override // com.android.systemui.statusbar.phone.LightBarTransitionsController.DarkIntensityApplier
    public final int getTintAnimationDuration() {
        return 120;
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher
    public final void removeDarkReceiver(DarkIconDispatcher.DarkReceiver darkReceiver) {
        this.mReceivers.remove(darkReceiver);
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher
    public final void setIconsDarkArea(ArrayList arrayList) {
        if (arrayList == null && this.mTintAreas.isEmpty()) {
            return;
        }
        this.mTintAreas.clear();
        if (arrayList != null) {
            this.mTintAreas.addAll(arrayList);
        }
        applyIconTint();
    }
}
