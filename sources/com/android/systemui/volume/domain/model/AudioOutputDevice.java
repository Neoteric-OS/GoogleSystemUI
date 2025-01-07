package com.android.systemui.volume.domain.model;

import android.graphics.drawable.Drawable;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface AudioOutputDevice {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Bluetooth implements AudioOutputDevice {
        public final CachedBluetoothDevice cachedBluetoothDevice;
        public final Drawable icon;
        public final String name;

        public Bluetooth(String str, Drawable drawable, CachedBluetoothDevice cachedBluetoothDevice) {
            this.name = str;
            this.icon = drawable;
            this.cachedBluetoothDevice = cachedBluetoothDevice;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Bluetooth)) {
                return false;
            }
            Bluetooth bluetooth = (Bluetooth) obj;
            return Intrinsics.areEqual(this.name, bluetooth.name) && Intrinsics.areEqual(this.icon, bluetooth.icon) && Intrinsics.areEqual(this.cachedBluetoothDevice, bluetooth.cachedBluetoothDevice);
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final Drawable getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final String getName() {
            return this.name;
        }

        public final int hashCode() {
            int hashCode = this.name.hashCode() * 31;
            Drawable drawable = this.icon;
            return this.cachedBluetoothDevice.hashCode() + ((hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31);
        }

        public final String toString() {
            return "Bluetooth(name=" + this.name + ", icon=" + this.icon + ", cachedBluetoothDevice=" + this.cachedBluetoothDevice + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BuiltIn implements AudioOutputDevice {
        public final Drawable icon;
        public final String name;

        public BuiltIn(Drawable drawable, String str) {
            this.name = str;
            this.icon = drawable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BuiltIn)) {
                return false;
            }
            BuiltIn builtIn = (BuiltIn) obj;
            return Intrinsics.areEqual(this.name, builtIn.name) && Intrinsics.areEqual(this.icon, builtIn.icon);
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final Drawable getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final String getName() {
            return this.name;
        }

        public final int hashCode() {
            int hashCode = this.name.hashCode() * 31;
            Drawable drawable = this.icon;
            return hashCode + (drawable == null ? 0 : drawable.hashCode());
        }

        public final String toString() {
            return "BuiltIn(name=" + this.name + ", icon=" + this.icon + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Remote implements AudioOutputDevice {
        public final Drawable icon;
        public final String name;

        public Remote(Drawable drawable, String str) {
            this.name = str;
            this.icon = drawable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Remote)) {
                return false;
            }
            Remote remote = (Remote) obj;
            return Intrinsics.areEqual(this.name, remote.name) && Intrinsics.areEqual(this.icon, remote.icon);
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final Drawable getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final String getName() {
            return this.name;
        }

        public final int hashCode() {
            int hashCode = this.name.hashCode() * 31;
            Drawable drawable = this.icon;
            return hashCode + (drawable == null ? 0 : drawable.hashCode());
        }

        public final String toString() {
            return "Remote(name=" + this.name + ", icon=" + this.icon + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unavailable implements AudioOutputDevice {
        public static final Unavailable INSTANCE = new Unavailable();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Unavailable);
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final Drawable getIcon() {
            throw new IllegalStateException("Unsupported for unavailable device");
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final String getName() {
            throw new IllegalStateException("Unsupported for unavailable device");
        }

        public final int hashCode() {
            return -465251659;
        }

        public final String toString() {
            return "Unavailable";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unknown implements AudioOutputDevice {
        public static final Unknown INSTANCE = new Unknown();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Unknown);
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final Drawable getIcon() {
            throw new IllegalStateException("Unsupported for unknown device");
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final String getName() {
            throw new IllegalStateException("Unsupported for unknown device");
        }

        public final int hashCode() {
            return -25976529;
        }

        public final String toString() {
            return "Unknown";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Wired implements AudioOutputDevice {
        public final Drawable icon;
        public final String name;

        public Wired(Drawable drawable, String str) {
            this.name = str;
            this.icon = drawable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Wired)) {
                return false;
            }
            Wired wired = (Wired) obj;
            return Intrinsics.areEqual(this.name, wired.name) && Intrinsics.areEqual(this.icon, wired.icon);
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final Drawable getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.domain.model.AudioOutputDevice
        public final String getName() {
            return this.name;
        }

        public final int hashCode() {
            int hashCode = this.name.hashCode() * 31;
            Drawable drawable = this.icon;
            return hashCode + (drawable == null ? 0 : drawable.hashCode());
        }

        public final String toString() {
            return "Wired(name=" + this.name + ", icon=" + this.icon + ")";
        }
    }

    Drawable getIcon();

    String getName();
}
