package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    public byte[] mData;
    public int mInt1;
    public int mInt2;
    public Object mObj1;
    public Parcelable mParcelable;
    public String mString1;
    public ColorStateList mTintList;
    public PorterDuff.Mode mTintMode;
    public String mTintModeStr;
    public int mType;

    public IconCompat() {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
    }

    public static IconCompat createFromIcon(Context context, Icon icon) {
        IconCompat iconCompat;
        icon.getClass();
        int type = icon.getType();
        if (type == 2) {
            String resPackage = icon.getResPackage();
            try {
                return createWithResource(icon.getResId(), getResources(context, resPackage), resPackage);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        }
        if (type == 4) {
            Uri uri = icon.getUri();
            uri.getClass();
            String uri2 = uri.toString();
            uri2.getClass();
            iconCompat = new IconCompat(4);
            iconCompat.mObj1 = uri2;
        } else {
            if (type != 6) {
                IconCompat iconCompat2 = new IconCompat(-1);
                iconCompat2.mObj1 = icon;
                return iconCompat2;
            }
            Uri uri3 = icon.getUri();
            uri3.getClass();
            String uri4 = uri3.toString();
            uri4.getClass();
            iconCompat = new IconCompat(6);
            iconCompat.mObj1 = uri4;
        }
        return iconCompat;
    }

    public static IconCompat createWithResource(int i, Context context) {
        context.getClass();
        return createWithResource(i, context.getResources(), context.getPackageName());
    }

    public static Resources getResources(Context context, String str) {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return packageManager.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("IconCompat", "Unable to find pkg=" + str + " for icon", e);
            return null;
        }
    }

    public final int getResId() {
        int i = this.mType;
        if (i == -1) {
            return ((Icon) this.mObj1).getResId();
        }
        if (i == 2) {
            return this.mInt1;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public final String getResPackage() {
        int i = this.mType;
        if (i == -1) {
            return ((Icon) this.mObj1).getResPackage();
        }
        if (i == 2) {
            String str = this.mString1;
            return (str == null || TextUtils.isEmpty(str)) ? ((String) this.mObj1).split(":", -1)[0] : this.mString1;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public final Drawable loadDrawable(Context context) {
        Object obj;
        if (this.mType == 2 && (obj = this.mObj1) != null) {
            String str = (String) obj;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split("/", -1)[0];
                String str4 = str2.split("/", -1)[1];
                String str5 = str.split(":", -1)[0];
                if ("0_resource_name_obfuscated".equals(str4)) {
                    Log.i("IconCompat", "Found obfuscated resource, not trying to update resource id for it");
                } else {
                    String resPackage = getResPackage();
                    int identifier = getResources(context, resPackage).getIdentifier(str4, str3, str5);
                    if (this.mInt1 != identifier) {
                        Log.i("IconCompat", "Id has changed for " + resPackage + " " + str);
                        this.mInt1 = identifier;
                    }
                }
            }
        }
        return toIcon$1().loadDrawable(context);
    }

    public final Icon toIcon$1() {
        Icon createWithBitmap;
        Uri parse;
        int i = this.mType;
        switch (i) {
            case -1:
                return (Icon) this.mObj1;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                createWithBitmap = Icon.createWithBitmap((Bitmap) this.mObj1);
                break;
            case 2:
                createWithBitmap = Icon.createWithResource(getResPackage(), this.mInt1);
                break;
            case 3:
                createWithBitmap = Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
                break;
            case 4:
                createWithBitmap = Icon.createWithContentUri((String) this.mObj1);
                break;
            case 5:
                createWithBitmap = Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1);
                break;
            case 6:
                if (i == -1) {
                    parse = ((Icon) this.mObj1).getUri();
                } else {
                    if (i != 4 && i != 6) {
                        throw new IllegalStateException("called getUri() on " + this);
                    }
                    parse = Uri.parse((String) this.mObj1);
                }
                createWithBitmap = Icon.createWithAdaptiveBitmapContentUri(parse);
                break;
        }
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            createWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode == DEFAULT_TINT_MODE) {
            return createWithBitmap;
        }
        createWithBitmap.setTintMode(mode);
        return createWithBitmap;
    }

    public final String toString() {
        String str;
        if (this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.mType) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.mType) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.mObj1).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.mObj1).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.mString1);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(getResId())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.mInt1);
                if (this.mInt2 != 0) {
                    sb.append(" off=");
                    sb.append(this.mInt2);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.mObj1);
                break;
        }
        if (this.mTintList != null) {
            sb.append(" tint=");
            sb.append(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            sb.append(" mode=");
            sb.append(this.mTintMode);
        }
        sb.append(")");
        return sb.toString();
    }

    public static IconCompat createWithResource(int i, Resources resources, String str) {
        str.getClass();
        if (i != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.mInt1 = i;
            if (resources != null) {
                try {
                    iconCompat.mObj1 = resources.getResourceName(i);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.mObj1 = str;
            }
            iconCompat.mString1 = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Drawable resource ID must not be 0");
    }

    public IconCompat(int i) {
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
        this.mType = i;
    }

    public static IconCompat createFromIcon(Icon icon) {
        int type = icon.getType();
        if (type == 2) {
            return createWithResource(icon.getResId(), null, icon.getResPackage());
        }
        if (type == 4) {
            Uri uri = icon.getUri();
            uri.getClass();
            String uri2 = uri.toString();
            uri2.getClass();
            IconCompat iconCompat = new IconCompat(4);
            iconCompat.mObj1 = uri2;
            return iconCompat;
        }
        if (type != 6) {
            IconCompat iconCompat2 = new IconCompat(-1);
            iconCompat2.mObj1 = icon;
            return iconCompat2;
        }
        Uri uri3 = icon.getUri();
        uri3.getClass();
        String uri4 = uri3.toString();
        uri4.getClass();
        IconCompat iconCompat3 = new IconCompat(6);
        iconCompat3.mObj1 = uri4;
        return iconCompat3;
    }
}
