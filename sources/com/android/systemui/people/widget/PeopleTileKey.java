package com.android.systemui.people.widget;

import android.app.people.PeopleSpaceTile;
import android.text.TextUtils;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PeopleTileKey {
    public static final Pattern KEY_PATTERN = Pattern.compile("(.+)/(-?\\d+)/(\\p{L}.*)");
    public String mPackageName;
    public String mShortcutId;
    public int mUserId;

    public PeopleTileKey(String str, String str2, int i) {
        this.mShortcutId = str;
        this.mUserId = i;
        this.mPackageName = str2;
    }

    public static PeopleTileKey fromString(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = KEY_PATTERN.matcher(str);
        if (matcher.find()) {
            try {
                return new PeopleTileKey(matcher.group(1), matcher.group(3), Integer.parseInt(matcher.group(2)));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static boolean isValid(PeopleTileKey peopleTileKey) {
        return (peopleTileKey == null || TextUtils.isEmpty(peopleTileKey.mShortcutId) || TextUtils.isEmpty(peopleTileKey.mPackageName) || peopleTileKey.mUserId < 0) ? false : true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PeopleTileKey) {
            return Objects.equals(((PeopleTileKey) obj).toString(), toString());
        }
        return false;
    }

    public final int hashCode() {
        return this.mShortcutId.hashCode() + Integer.valueOf(this.mUserId).hashCode() + this.mPackageName.hashCode();
    }

    public final String toString() {
        return this.mShortcutId + "/" + this.mUserId + "/" + this.mPackageName;
    }

    public PeopleTileKey(PeopleSpaceTile peopleSpaceTile) {
        this.mShortcutId = peopleSpaceTile.getId();
        this.mUserId = peopleSpaceTile.getUserHandle().getIdentifier();
        this.mPackageName = peopleSpaceTile.getPackageName();
    }
}
