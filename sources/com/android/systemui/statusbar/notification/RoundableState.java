package com.android.systemui.statusbar.notification;

import android.util.FloatProperty;
import android.view.View;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.wm.shell.R;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RoundableState {
    public static final AnimationProperties DURATION;
    public final AnimatableProperty.AnonymousClass7 bottomAnimatable;
    public float bottomRoundness;
    public final Map bottomRoundnessMap;
    public float maxRadius;
    public final float[] radiiBuffer;
    public final Roundable roundable;
    public final View targetView;
    public final AnimatableProperty.AnonymousClass7 topAnimatable;
    public float topRoundness;
    public final Map topRoundnessMap;

    static {
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = 200L;
        DURATION = animationProperties;
    }

    public RoundableState(View view, final Roundable roundable, float f) {
        this.targetView = view;
        this.roundable = roundable;
        this.maxRadius = f;
        final int i = 0;
        FloatProperty floatProperty = new FloatProperty(roundable, i) { // from class: com.android.systemui.statusbar.notification.RoundableState$Companion$topAnimatable$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ Roundable $roundable;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("topRoundness");
                this.$r8$classId = i;
                switch (i) {
                    case 1:
                        super("bottomRoundness");
                        break;
                    default:
                        break;
                }
            }

            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (this.$r8$classId) {
                    case 0:
                        return Float.valueOf(this.$roundable.getRoundableState().topRoundness);
                    default:
                        return Float.valueOf(this.$roundable.getRoundableState().bottomRoundness);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f2) {
                switch (this.$r8$classId) {
                    case 0:
                        this.$roundable.getRoundableState().topRoundness = f2;
                        this.$roundable.applyRoundnessAndInvalidate();
                        break;
                    default:
                        this.$roundable.getRoundableState().bottomRoundness = f2;
                        this.$roundable.applyRoundnessAndInvalidate();
                        break;
                }
            }
        };
        AnimatableProperty.AnonymousClass7 anonymousClass7 = AnimatableProperty.Y;
        this.topAnimatable = new AnimatableProperty.AnonymousClass7(R.id.top_roundess_animator_end_tag, R.id.top_roundess_animator_start_tag, R.id.top_roundess_animator_tag, floatProperty);
        final int i2 = 1;
        this.bottomAnimatable = new AnimatableProperty.AnonymousClass7(R.id.bottom_roundess_animator_end_tag, R.id.bottom_roundess_animator_start_tag, R.id.bottom_roundess_animator_tag, new FloatProperty(roundable, i2) { // from class: com.android.systemui.statusbar.notification.RoundableState$Companion$topAnimatable$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ Roundable $roundable;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("topRoundness");
                this.$r8$classId = i2;
                switch (i2) {
                    case 1:
                        super("bottomRoundness");
                        break;
                    default:
                        break;
                }
            }

            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (this.$r8$classId) {
                    case 0:
                        return Float.valueOf(this.$roundable.getRoundableState().topRoundness);
                    default:
                        return Float.valueOf(this.$roundable.getRoundableState().bottomRoundness);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f2) {
                switch (this.$r8$classId) {
                    case 0:
                        this.$roundable.getRoundableState().topRoundness = f2;
                        this.$roundable.applyRoundnessAndInvalidate();
                        break;
                    default:
                        this.$roundable.getRoundableState().bottomRoundness = f2;
                        this.$roundable.applyRoundnessAndInvalidate();
                        break;
                }
            }
        });
        this.topRoundnessMap = new LinkedHashMap();
        this.bottomRoundnessMap = new LinkedHashMap();
        this.radiiBuffer = new float[8];
    }

    public final String debugString() {
        StringBuilder sb = new StringBuilder("Roundable { ");
        sb.append("top: { value: " + this.topRoundness + ", requests: " + this.topRoundnessMap + "}");
        sb.append(", ");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, "bottom: { value: " + this.bottomRoundness + ", requests: " + this.bottomRoundnessMap + "}", "}");
    }
}
