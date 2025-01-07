package androidx.media;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {
    public int mUsage = 0;
    public int mContentType = 0;
    public int mFlags = 0;
    public int mLegacyStream = -1;

    public final boolean equals(Object obj) {
        int i;
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.mContentType == audioAttributesImplBase.mContentType) {
            int i2 = this.mFlags;
            int i3 = audioAttributesImplBase.mFlags;
            int i4 = audioAttributesImplBase.mLegacyStream;
            if (i4 == -1) {
                int i5 = audioAttributesImplBase.mUsage;
                int i6 = AudioAttributesCompat.$r8$clinit;
                if ((i3 & 1) != 1) {
                    if ((i3 & 4) != 4) {
                        switch (i5) {
                            case 2:
                                i = 0;
                                break;
                            case 3:
                                i = 8;
                                break;
                            case 4:
                                i = 4;
                                break;
                            case 5:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                                i = 5;
                                break;
                            case 6:
                                i = 2;
                                break;
                            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                                i = 10;
                                break;
                            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            default:
                                i = 3;
                                break;
                            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                                i = 1;
                                break;
                        }
                    } else {
                        i = 6;
                    }
                } else {
                    i = 7;
                }
            } else {
                i = i4;
            }
            if (i == 6) {
                i3 |= 4;
            } else if (i == 7) {
                i3 |= 1;
            }
            if (i2 == (i3 & 273) && this.mUsage == audioAttributesImplBase.mUsage && this.mLegacyStream == i4) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream != -1) {
            sb.append(" stream=");
            sb.append(this.mLegacyStream);
            sb.append(" derived");
        }
        sb.append(" usage=");
        int i = this.mUsage;
        int i2 = AudioAttributesCompat.$r8$clinit;
        switch (i) {
            case 0:
                str = "USAGE_UNKNOWN";
                break;
            case 1:
                str = "USAGE_MEDIA";
                break;
            case 2:
                str = "USAGE_VOICE_COMMUNICATION";
                break;
            case 3:
                str = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                break;
            case 4:
                str = "USAGE_ALARM";
                break;
            case 5:
                str = "USAGE_NOTIFICATION";
                break;
            case 6:
                str = "USAGE_NOTIFICATION_RINGTONE";
                break;
            case 7:
                str = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                break;
            case 8:
                str = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                break;
            case 9:
                str = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                break;
            case 10:
                str = "USAGE_NOTIFICATION_EVENT";
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                str = "USAGE_ASSISTANCE_ACCESSIBILITY";
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                str = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                str = "USAGE_ASSISTANCE_SONIFICATION";
                break;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                str = "USAGE_GAME";
                break;
            case 15:
            default:
                str = AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown usage ");
                break;
            case 16:
                str = "USAGE_ASSISTANT";
                break;
        }
        sb.append(str);
        sb.append(" content=");
        sb.append(this.mContentType);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb.toString();
    }
}
