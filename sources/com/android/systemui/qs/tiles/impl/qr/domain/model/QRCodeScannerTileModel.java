package com.android.systemui.qs.tiles.impl.qr.domain.model;

import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QRCodeScannerTileModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Available implements QRCodeScannerTileModel {
        public final Intent intent;

        public Available(Intent intent) {
            this.intent = intent;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Available) && Intrinsics.areEqual(this.intent, ((Available) obj).intent);
        }

        public final int hashCode() {
            return this.intent.hashCode();
        }

        public final String toString() {
            return "Available(intent=" + this.intent + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TemporarilyUnavailable implements QRCodeScannerTileModel {
        public static final TemporarilyUnavailable INSTANCE = new TemporarilyUnavailable();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TemporarilyUnavailable);
        }

        public final int hashCode() {
            return -684068981;
        }

        public final String toString() {
            return "TemporarilyUnavailable";
        }
    }
}
