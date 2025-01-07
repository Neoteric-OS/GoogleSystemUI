package com.android.systemui.controls.ui;

import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlViewHolder$findBehaviorClass$1 implements Supplier {
    public final /* synthetic */ int $r8$classId;
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$1 = new ControlViewHolder$findBehaviorClass$1(1);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE = new ControlViewHolder$findBehaviorClass$1(0);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$2 = new ControlViewHolder$findBehaviorClass$1(2);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$3 = new ControlViewHolder$findBehaviorClass$1(3);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$4 = new ControlViewHolder$findBehaviorClass$1(4);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$5 = new ControlViewHolder$findBehaviorClass$1(5);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$6 = new ControlViewHolder$findBehaviorClass$1(6);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$7 = new ControlViewHolder$findBehaviorClass$1(7);
    public static final ControlViewHolder$findBehaviorClass$1 INSTANCE$8 = new ControlViewHolder$findBehaviorClass$1(8);

    public /* synthetic */ ControlViewHolder$findBehaviorClass$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.$r8$classId) {
            case 0:
                return new StatusBehavior();
            case 1:
                return new DefaultBehavior();
            case 2:
                return new TouchBehavior();
            case 3:
                return new TouchBehavior();
            case 4:
                return new ToggleBehavior();
            case 5:
                return new TouchBehavior();
            case 6:
                return new ToggleRangeBehavior();
            case 7:
                return new ToggleRangeBehavior();
            default:
                return new TemperatureControlBehavior();
        }
    }
}
