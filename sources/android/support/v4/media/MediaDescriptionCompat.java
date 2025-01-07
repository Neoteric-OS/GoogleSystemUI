package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public final CharSequence mDescription;
    public MediaDescription mDescriptionFwk;
    public final Bundle mExtras;
    public final Bitmap mIcon;
    public final Uri mIconUri;
    public final String mMediaId;
    public final Uri mMediaUri;
    public final CharSequence mSubtitle;
    public final CharSequence mTitle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: android.support.v4.media.MediaDescriptionCompat$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
        @Override // android.os.Parcelable.Creator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object createFromParcel(android.os.Parcel r13) {
            /*
                r12 = this;
                android.os.Parcelable$Creator r12 = android.media.MediaDescription.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                android.os.Parcelable$Creator r13 = android.support.v4.media.MediaDescriptionCompat.CREATOR
                r13 = 0
                if (r12 == 0) goto L65
                android.media.MediaDescription r12 = (android.media.MediaDescription) r12
                java.lang.String r1 = r12.getMediaId()
                java.lang.CharSequence r2 = r12.getTitle()
                java.lang.CharSequence r3 = r12.getSubtitle()
                java.lang.CharSequence r4 = r12.getDescription()
                android.graphics.Bitmap r5 = r12.getIconBitmap()
                android.net.Uri r6 = r12.getIconUri()
                android.os.Bundle r0 = r12.getExtras()
                if (r0 == 0) goto L2f
                android.os.Bundle r0 = android.support.v4.media.session.MediaSessionCompat.unparcelWithClassLoader(r0)
            L2f:
                java.lang.String r7 = "android.support.v4.media.description.MEDIA_URI"
                if (r0 == 0) goto L3a
                android.os.Parcelable r8 = r0.getParcelable(r7)
                android.net.Uri r8 = (android.net.Uri) r8
                goto L3b
            L3a:
                r8 = r13
            L3b:
                if (r8 == 0) goto L54
                java.lang.String r9 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
                boolean r10 = r0.containsKey(r9)
                if (r10 == 0) goto L4e
                int r10 = r0.size()
                r11 = 2
                if (r10 != r11) goto L4e
                r7 = r13
                goto L55
            L4e:
                r0.remove(r7)
                r0.remove(r9)
            L54:
                r7 = r0
            L55:
                if (r8 == 0) goto L58
                goto L5d
            L58:
                android.net.Uri r13 = r12.getMediaUri()
                r8 = r13
            L5d:
                android.support.v4.media.MediaDescriptionCompat r13 = new android.support.v4.media.MediaDescriptionCompat
                r0 = r13
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
                r13.mDescriptionFwk = r12
            L65:
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.AnonymousClass1.createFromParcel(android.os.Parcel):java.lang.Object");
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.mMediaId = str;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.mIconUri = uri;
        this.mExtras = bundle;
        this.mMediaUri = uri2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.mTitle) + ", " + ((Object) this.mSubtitle) + ", " + ((Object) this.mDescription);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        MediaDescription mediaDescription = this.mDescriptionFwk;
        if (mediaDescription == null) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.mMediaId);
            builder.setTitle(this.mTitle);
            builder.setSubtitle(this.mSubtitle);
            builder.setDescription(this.mDescription);
            builder.setIconBitmap(this.mIcon);
            builder.setIconUri(this.mIconUri);
            builder.setExtras(this.mExtras);
            builder.setMediaUri(this.mMediaUri);
            mediaDescription = builder.build();
            this.mDescriptionFwk = mediaDescription;
        }
        mediaDescription.writeToParcel(parcel, i);
    }
}
