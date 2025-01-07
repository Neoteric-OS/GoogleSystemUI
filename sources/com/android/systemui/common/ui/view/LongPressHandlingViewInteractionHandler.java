package com.android.systemui.common.ui.view;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.log.LongPressHandlingViewLogger;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LongPressHandlingViewInteractionHandler {
    public final int allowedTouchSlop;
    public final Function0 isAttachedToWindow;
    public boolean isLongPressHandlingEnabled;
    public final LongPressHandlingViewLogger logger;
    public Function0 longPressDuration;
    public final Function2 onLongPressDetected;
    public final Function0 onSingleTapDetected;
    public final Function2 postDelayed;
    public DisposableHandle scheduledLongPressHandle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class MotionEventModel {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Cancel extends MotionEventModel {
            public static final Cancel INSTANCE = new Cancel();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Down extends MotionEventModel {
            public final int x;
            public final int y;

            public Down(int i, int i2) {
                this.x = i;
                this.y = i2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Down)) {
                    return false;
                }
                Down down = (Down) obj;
                return this.x == down.x && this.y == down.y;
            }

            public final int hashCode() {
                return Integer.hashCode(this.y) + (Integer.hashCode(this.x) * 31);
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("Down(x=");
                sb.append(this.x);
                sb.append(", y=");
                return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.y, ")");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Move extends MotionEventModel {
            public final float distanceMoved;

            public Move(float f) {
                this.distanceMoved = f;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Move) && Float.compare(this.distanceMoved, ((Move) obj).distanceMoved) == 0;
            }

            public final int hashCode() {
                return Float.hashCode(this.distanceMoved);
            }

            public final String toString() {
                return "Move(distanceMoved=" + this.distanceMoved + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Other extends MotionEventModel {
            public static final Other INSTANCE = new Other();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Up extends MotionEventModel {
            public final float distanceMoved;
            public final long gestureDuration;

            public Up(float f, long j) {
                this.distanceMoved = f;
                this.gestureDuration = j;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Up)) {
                    return false;
                }
                Up up = (Up) obj;
                return Float.compare(this.distanceMoved, up.distanceMoved) == 0 && this.gestureDuration == up.gestureDuration;
            }

            public final int hashCode() {
                return Long.hashCode(this.gestureDuration) + (Float.hashCode(this.distanceMoved) * 31);
            }

            public final String toString() {
                return "Up(distanceMoved=" + this.distanceMoved + ", gestureDuration=" + this.gestureDuration + ")";
            }
        }
    }

    public LongPressHandlingViewInteractionHandler(Function2 function2, Function0 function0, Function2 function22, Function0 function02, Function0 function03, int i, LongPressHandlingViewLogger longPressHandlingViewLogger) {
        this.postDelayed = function2;
        this.isAttachedToWindow = function0;
        this.onLongPressDetected = function22;
        this.onSingleTapDetected = function02;
        this.longPressDuration = function03;
        this.allowedTouchSlop = i;
        this.logger = longPressHandlingViewLogger;
    }
}
