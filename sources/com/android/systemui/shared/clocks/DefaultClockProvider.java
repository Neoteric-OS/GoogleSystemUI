package com.android.systemui.shared.clocks;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ClockMessageBuffers;
import com.android.systemui.plugins.clocks.ClockMetadata;
import com.android.systemui.plugins.clocks.ClockPickerConfig;
import com.android.systemui.plugins.clocks.ClockProvider;
import com.android.systemui.plugins.clocks.ClockSettings;
import com.android.wm.shell.R;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultClockProvider implements ClockProvider {
    public final Context ctx;
    public final boolean hasStepClockAnimation;
    public final LayoutInflater layoutInflater;
    public ClockMessageBuffers messageBuffers;
    public final Resources resources;

    public DefaultClockProvider(Context context, LayoutInflater layoutInflater, Resources resources, boolean z) {
        this.ctx = context;
        this.layoutInflater = layoutInflater;
        this.resources = resources;
        this.hasStepClockAnimation = z;
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public final ClockController createClock(ClockSettings clockSettings) {
        if (!Intrinsics.areEqual(clockSettings.getClockId(), "DEFAULT")) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(clockSettings.getClockId(), " is unsupported by ", DefaultClockProviderKt.TAG));
        }
        return new DefaultClockController(this.ctx, this.layoutInflater, this.resources, clockSettings, this.hasStepClockAnimation, this.messageBuffers);
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public final ClockPickerConfig getClockPickerConfig(String str) {
        if (str.equals("DEFAULT")) {
            return new ClockPickerConfig("DEFAULT", this.resources.getString(R.string.clock_default_name), this.resources.getString(R.string.clock_default_description), this.resources.getDrawable(R.drawable.clock_default_thumbnail, null), false, false, null, 112, null);
        }
        throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(str, " is unsupported by ", DefaultClockProviderKt.TAG));
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public final List getClocks() {
        return Collections.singletonList(new ClockMetadata("DEFAULT"));
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public final void initialize(ClockMessageBuffers clockMessageBuffers) {
        this.messageBuffers = clockMessageBuffers;
    }
}
