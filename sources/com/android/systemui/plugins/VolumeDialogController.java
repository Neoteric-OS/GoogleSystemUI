package com.android.systemui.plugins;

import android.content.ComponentName;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.os.Handler;
import android.os.VibrationEffect;
import android.util.SparseArray;
import com.android.systemui.plugins.annotations.Dependencies;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@Dependencies({@DependsOn(target = StreamState.class), @DependsOn(target = State.class), @DependsOn(target = Callbacks.class)})
@ProvidesInterface(version = 1)
/* loaded from: classes.dex */
public interface VolumeDialogController {
    public static final int VERSION = 1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 2)
    public interface Callbacks {
        public static final int VERSION = 2;

        void onAccessibilityModeChanged(Boolean bool);

        void onCaptionComponentStateChanged(Boolean bool, Boolean bool2);

        void onCaptionEnabledStateChanged(Boolean bool, Boolean bool2);

        void onConfigurationChanged();

        void onDismissRequested(int i);

        void onLayoutDirectionChanged(int i);

        void onScreenOff();

        void onShowCsdWarning(int i, int i2);

        void onShowRequested(int i, boolean z, int i2);

        void onShowSafetyWarning(int i);

        void onShowSilentHint();

        void onShowVibrateHint();

        void onStateChanged(State state);

        void onVolumeChangedFromKey();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public final class State {
        public static int NO_ACTIVE_STREAM = -1;
        public static final int VERSION = 1;
        public boolean disallowAlarms;
        public boolean disallowMedia;
        public boolean disallowRinger;
        public boolean disallowSystem;
        public ComponentName effectsSuppressor;
        public String effectsSuppressorName;
        public int ringerModeExternal;
        public int ringerModeInternal;
        public int zenMode;
        public final SparseArray states = new SparseArray();
        public int activeStream = NO_ACTIVE_STREAM;

        private static void sep(StringBuilder sb, int i) {
            if (i <= 0) {
                sb.append(',');
                return;
            }
            sb.append('\n');
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(' ');
            }
        }

        public State copy() {
            State state = new State();
            for (int i = 0; i < this.states.size(); i++) {
                state.states.put(this.states.keyAt(i), ((StreamState) this.states.valueAt(i)).copy());
            }
            state.ringerModeExternal = this.ringerModeExternal;
            state.ringerModeInternal = this.ringerModeInternal;
            state.zenMode = this.zenMode;
            ComponentName componentName = this.effectsSuppressor;
            if (componentName != null) {
                state.effectsSuppressor = componentName.clone();
            }
            state.effectsSuppressorName = this.effectsSuppressorName;
            state.activeStream = this.activeStream;
            state.disallowAlarms = this.disallowAlarms;
            state.disallowMedia = this.disallowMedia;
            state.disallowSystem = this.disallowSystem;
            state.disallowRinger = this.disallowRinger;
            return state;
        }

        public String toString() {
            return toString(0);
        }

        public String toString(int i) {
            StringBuilder sb = new StringBuilder("{");
            if (i > 0) {
                sep(sb, i);
            }
            for (int i2 = 0; i2 < this.states.size(); i2++) {
                if (i2 > 0) {
                    sep(sb, i);
                }
                int keyAt = this.states.keyAt(i2);
                StreamState streamState = (StreamState) this.states.valueAt(i2);
                sb.append(AudioSystem.streamToString(keyAt));
                sb.append(":");
                sb.append(streamState.level);
                sb.append('[');
                sb.append(streamState.levelMin);
                sb.append("..");
                sb.append(streamState.levelMax);
                sb.append(']');
                if (streamState.muted) {
                    sb.append(" [MUTED]");
                }
                if (streamState.dynamic) {
                    sb.append(" [DYNAMIC]");
                }
            }
            sep(sb, i);
            sb.append("ringerModeExternal:");
            sb.append(this.ringerModeExternal);
            sep(sb, i);
            sb.append("ringerModeInternal:");
            sb.append(this.ringerModeInternal);
            sep(sb, i);
            sb.append("zenMode:");
            sb.append(this.zenMode);
            sep(sb, i);
            sb.append("effectsSuppressor:");
            sb.append(this.effectsSuppressor);
            sep(sb, i);
            sb.append("effectsSuppressorName:");
            sb.append(this.effectsSuppressorName);
            sep(sb, i);
            sb.append("activeStream:");
            sb.append(this.activeStream);
            sep(sb, i);
            sb.append("disallowAlarms:");
            sb.append(this.disallowAlarms);
            sep(sb, i);
            sb.append("disallowMedia:");
            sb.append(this.disallowMedia);
            sep(sb, i);
            sb.append("disallowSystem:");
            sb.append(this.disallowSystem);
            sep(sb, i);
            sb.append("disallowRinger:");
            sb.append(this.disallowRinger);
            if (i > 0) {
                sep(sb, i);
            }
            sb.append('}');
            return sb.toString();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public final class StreamState {
        public static final int VERSION = 1;
        public boolean dynamic;
        public int level;
        public int levelMax;
        public int levelMin;
        public boolean muteSupported;
        public boolean muted;
        public int name;
        public String remoteLabel;
        public boolean routedToBluetooth;

        public StreamState copy() {
            StreamState streamState = new StreamState();
            streamState.dynamic = this.dynamic;
            streamState.level = this.level;
            streamState.levelMin = this.levelMin;
            streamState.levelMax = this.levelMax;
            streamState.muted = this.muted;
            streamState.muteSupported = this.muteSupported;
            streamState.name = this.name;
            streamState.remoteLabel = this.remoteLabel;
            streamState.routedToBluetooth = this.routedToBluetooth;
            return streamState;
        }
    }

    void addCallback(Callbacks callbacks, Handler handler);

    AudioManager getAudioManager();

    void getCaptionsComponentState(boolean z);

    void getCaptionsEnabledState(boolean z);

    void getState();

    boolean hasVibrator();

    void notifyVisible(boolean z);

    void removeCallback(Callbacks callbacks);

    void scheduleTouchFeedback();

    void setActiveStream(int i);

    void setCaptionsEnabledState(boolean z);

    void setRingerMode(int i, boolean z);

    void setStreamVolume(int i, int i2);

    void userActivity();

    void vibrate(VibrationEffect vibrationEffect);
}
