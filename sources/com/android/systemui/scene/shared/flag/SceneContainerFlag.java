package com.android.systemui.scene.shared.flag;

import android.os.Build;
import android.util.Log;
import com.android.systemui.flags.FlagToken;
import kotlin.sequences.FlatteningSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SceneContainerFlag {
    public static final void isUnexpectedlyInLegacyMode() {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
        }
    }

    public static final String requirementDescription() {
        StringBuilder sb = new StringBuilder();
        FlatteningSequence$iterator$1 flatteningSequence$iterator$1 = new FlatteningSequence$iterator$1(SequencesKt.flatten(SequencesKt.sequenceOf(SequencesKt.sequenceOf(new FlagToken("com.android.systemui.scene_container", false)), SequencesKt.sequenceOf(new FlagToken("com.android.systemui.keyguard_bottom_area_refactor", true), new FlagToken("com.android.systemui.keyguard_wm_state_refactor", false), new FlagToken("com.android.systemui.migrate_clocks_to_blueprint", true), new FlagToken("com.android.systemui.notification_avalanche_throttle_hun", false), new FlagToken("com.android.systemui.predictive_back_sysui", true), new FlagToken("com.android.systemui.device_entry_udfps_refactor", true)))));
        while (flatteningSequence$iterator$1.ensureItemIterator()) {
            FlagToken flagToken = (FlagToken) flatteningSequence$iterator$1.next();
            sb.append('\n');
            sb.append(flagToken.isEnabled ? "    [MET]" : "[NOT MET]");
            sb.append(" ".concat(flagToken.name));
        }
        return sb.toString();
    }
}
