package androidx.appsearch.builtintypes;

import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ImageObject extends Thing {
    public final List mKeywords;
    public final String mSha256;
    public final String mThumbnailSha256;

    public ImageObject(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, List list3, String str7, String str8) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        list3.getClass();
        this.mKeywords = list3;
        this.mSha256 = str7;
        this.mThumbnailSha256 = str8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageObject.class != obj.getClass()) {
            return false;
        }
        ImageObject imageObject = (ImageObject) obj;
        return this.mKeywords.equals(imageObject.mKeywords) && Objects.equals(this.mSha256, imageObject.mSha256) && Objects.equals(this.mThumbnailSha256, imageObject.mThumbnailSha256);
    }

    public final int hashCode() {
        return Objects.hash(this.mKeywords, this.mSha256, this.mThumbnailSha256);
    }
}
