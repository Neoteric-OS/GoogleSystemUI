package com.android.systemui.clipboardoverlay;

import android.content.ClipData;
import android.net.Uri;
import android.view.textclassifier.TextLinks;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardModel {
    public final ClipData clipData;
    public final boolean isRemote;
    public final boolean isSensitive;
    public final String source;
    public final CharSequence text;
    public final TextLinks textLinks;
    public final Type type;
    public final Uri uri;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type IMAGE;
        public static final Type OTHER;
        public static final Type TEXT;
        public static final Type URI;

        static {
            Type type = new Type("TEXT", 0);
            TEXT = type;
            Type type2 = new Type("IMAGE", 1);
            IMAGE = type2;
            Type type3 = new Type("URI", 2);
            URI = type3;
            Type type4 = new Type("OTHER", 3);
            OTHER = type4;
            Type[] typeArr = {type, type2, type3, type4};
            $VALUES = typeArr;
            EnumEntriesKt.enumEntries(typeArr);
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public ClipboardModel(ClipData clipData, String str, Type type, CharSequence charSequence, TextLinks textLinks, Uri uri, boolean z, boolean z2) {
        this.clipData = clipData;
        this.source = str;
        this.type = type;
        this.text = charSequence;
        this.textLinks = textLinks;
        this.uri = uri;
        this.isSensitive = z;
        this.isRemote = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClipboardModel)) {
            return false;
        }
        ClipboardModel clipboardModel = (ClipboardModel) obj;
        return Intrinsics.areEqual(this.clipData, clipboardModel.clipData) && Intrinsics.areEqual(this.source, clipboardModel.source) && this.type == clipboardModel.type && Intrinsics.areEqual(this.text, clipboardModel.text) && Intrinsics.areEqual(this.textLinks, clipboardModel.textLinks) && Intrinsics.areEqual(this.uri, clipboardModel.uri) && this.isSensitive == clipboardModel.isSensitive && this.isRemote == clipboardModel.isRemote;
    }

    public final int hashCode() {
        int hashCode = (this.type.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.source, this.clipData.hashCode() * 31, 31)) * 31;
        CharSequence charSequence = this.text;
        int hashCode2 = (hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        TextLinks textLinks = this.textLinks;
        int hashCode3 = (hashCode2 + (textLinks == null ? 0 : textLinks.hashCode())) * 31;
        Uri uri = this.uri;
        return Boolean.hashCode(this.isRemote) + TransitionData$$ExternalSyntheticOutline0.m((hashCode3 + (uri != null ? uri.hashCode() : 0)) * 31, 31, this.isSensitive);
    }

    public final String toString() {
        ClipData clipData = this.clipData;
        CharSequence charSequence = this.text;
        return "ClipboardModel(clipData=" + clipData + ", source=" + this.source + ", type=" + this.type + ", text=" + ((Object) charSequence) + ", textLinks=" + this.textLinks + ", uri=" + this.uri + ", isSensitive=" + this.isSensitive + ", isRemote=" + this.isRemote + ")";
    }
}
