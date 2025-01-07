package com.android.settingslib.notification.modes;

import android.graphics.drawable.Drawable;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZenIcon extends Record {
    public final Drawable drawable;
    public final Key key;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Key extends Record {
        public final int resId;
        public final String resPackage;

        public Key(String str, int i) {
            if (!(i != 0)) {
                throw new IllegalArgumentException("Resource id must be valid");
            }
            this.resPackage = str;
            this.resId = i;
        }

        public static Key forSystemResource(int i) {
            return new Key(null, i);
        }

        @Override // java.lang.Record
        public final boolean equals(Object obj) {
            if (obj == null || Key.class != obj.getClass()) {
                return false;
            }
            Key key = (Key) obj;
            return Arrays.equals(new Object[]{this.resPackage, Integer.valueOf(this.resId)}, new Object[]{key.resPackage, Integer.valueOf(key.resId)});
        }

        @Override // java.lang.Record
        public final int hashCode() {
            return Key.class.hashCode() + (Arrays.hashCode(new Object[]{this.resPackage, Integer.valueOf(this.resId)}) * 31);
        }

        @Override // java.lang.Record
        public final String toString() {
            Object[] objArr = {this.resPackage, Integer.valueOf(this.resId)};
            String[] split = "resPackage;resId".length() == 0 ? new String[0] : "resPackage;resId".split(";");
            StringBuilder sb = new StringBuilder();
            sb.append(Key.class.getSimpleName());
            sb.append("[");
            for (int i = 0; i < split.length; i++) {
                sb.append(split[i]);
                sb.append("=");
                sb.append(objArr[i]);
                if (i != split.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public ZenIcon(Key key, Drawable drawable) {
        this.key = key;
        this.drawable = drawable;
    }

    @Override // java.lang.Record
    public final boolean equals(Object obj) {
        if (obj == null || ZenIcon.class != obj.getClass()) {
            return false;
        }
        ZenIcon zenIcon = (ZenIcon) obj;
        return Arrays.equals(new Object[]{this.key, this.drawable}, new Object[]{zenIcon.key, zenIcon.drawable});
    }

    @Override // java.lang.Record
    public final int hashCode() {
        return ZenIcon.class.hashCode() + (Arrays.hashCode(new Object[]{this.key, this.drawable}) * 31);
    }

    @Override // java.lang.Record
    public final String toString() {
        Object[] objArr = {this.key, this.drawable};
        String[] split = "key;drawable".length() == 0 ? new String[0] : "key;drawable".split(";");
        StringBuilder sb = new StringBuilder("ZenIcon[");
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            sb.append("=");
            sb.append(objArr[i]);
            if (i != split.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
