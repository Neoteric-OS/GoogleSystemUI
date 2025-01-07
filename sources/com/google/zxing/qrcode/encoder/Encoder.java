package com.google.zxing.qrcode.encoder;

import com.google.zxing.common.StringUtils;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Encoder {
    public static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    public static final Charset DEFAULT_BYTE_MODE_ENCODING = StandardCharsets.ISO_8859_1;

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b A[LOOP:0: B:11:0x0020->B:18:0x004b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void appendBytes(java.lang.String r8, com.google.zxing.qrcode.decoder.Mode r9, com.google.zxing.common.BitArray r10, java.nio.charset.Charset r11) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.appendBytes(java.lang.String, com.google.zxing.qrcode.decoder.Mode, com.google.zxing.common.BitArray, java.nio.charset.Charset):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:307:0x06c0, code lost:
    
        if ((r2 >= 0 && r2 < 8) != false) goto L310;
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x0785, code lost:
    
        if (r1 == false) goto L376;
     */
    /* JADX WARN: Removed duplicated region for block: B:365:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x07de  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x07fb A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.zxing.qrcode.encoder.QRCode encode(java.lang.String r38, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r39, java.util.Map r40) {
        /*
            Method dump skipped, instructions count: 2288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.encode(java.lang.String, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.qrcode.encoder.QRCode");
    }

    public static boolean isOnlyDoubleByteKanji(String str) {
        byte[] bytes = str.getBytes(StringUtils.SHIFT_JIS_CHARSET);
        int length = bytes.length;
        if (length % 2 != 0) {
            return false;
        }
        for (int i = 0; i < length; i += 2) {
            int i2 = bytes[i] & 255;
            if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                return false;
            }
        }
        return true;
    }

    public static boolean willFit(int i, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        int i2 = version.totalCodewords;
        Version.ECBlocks eCBlocks = version.ecBlocks[errorCorrectionLevel.ordinal()];
        int i3 = 0;
        for (Version.ECB ecb : eCBlocks.ecBlocks) {
            i3 += ecb.count;
        }
        return i2 - (i3 * eCBlocks.ecCodewordsPerBlock) >= (i + 7) / 8;
    }
}
