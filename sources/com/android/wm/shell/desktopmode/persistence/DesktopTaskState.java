package com.android.wm.shell.desktopmode.persistence;

import com.google.protobuf.Internal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public enum DesktopTaskState implements Internal.EnumLite {
    VISIBLE(0),
    MINIMIZED(1);

    private final int value;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DesktopTaskStateVerifier implements Internal.EnumVerifier {
        public static final DesktopTaskStateVerifier INSTANCE = new DesktopTaskStateVerifier();

        @Override // com.google.protobuf.Internal.EnumVerifier
        public final boolean isInRange(int i) {
            return (i != 0 ? i != 1 ? null : DesktopTaskState.MINIMIZED : DesktopTaskState.VISIBLE) != null;
        }
    }

    DesktopTaskState(int i) {
        this.value = i;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }
}
