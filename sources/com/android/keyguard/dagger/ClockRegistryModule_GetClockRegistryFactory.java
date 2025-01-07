package com.android.keyguard.dagger;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.clocks.ClockMessageBuffers;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.shared.clocks.DefaultClockProvider;
import com.android.systemui.util.ThreadAssert;
import com.android.wm.shell.R;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ClockRegistryModule_GetClockRegistryFactory implements Provider {
    public static ClockRegistry getClockRegistry(Context context, PluginManager pluginManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, FeatureFlags featureFlags, Resources resources, LayoutInflater layoutInflater, ClockMessageBuffers clockMessageBuffers) {
        FeatureFlagsClassicRelease featureFlagsClassicRelease = (FeatureFlagsClassicRelease) featureFlags;
        ClockRegistry clockRegistry = new ClockRegistry(context, pluginManager, coroutineScope, coroutineDispatcher, coroutineDispatcher2, featureFlagsClassicRelease.isEnabled(Flags.LOCKSCREEN_CUSTOM_CLOCKS), new DefaultClockProvider(context, layoutInflater, resources, featureFlagsClassicRelease.isEnabled(Flags.STEP_CLOCK_ANIMATION)), context.getString(R.string.lockscreen_clock_id_fallback), clockMessageBuffers, new ThreadAssert());
        clockRegistry.registerListeners();
        return clockRegistry;
    }
}
