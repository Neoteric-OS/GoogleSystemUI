package com.android.settingslib.qrcode;

import android.graphics.Bitmap;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class QrCodeGenerator {
    public static final Bitmap encodeQrCode(int i, String str) {
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        if (!StandardCharsets.ISO_8859_1.newEncoder().canEncode(str)) {
            enumMap.put((EnumMap) EncodeHintType.CHARACTER_SET, (EncodeHintType) StandardCharsets.UTF_8.name());
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i < 0 || i < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i);
        }
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
        EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
        if (enumMap.containsKey(encodeHintType)) {
            errorCorrectionLevel = ErrorCorrectionLevel.valueOf(enumMap.get(encodeHintType).toString());
        }
        EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
        int parseInt = enumMap.containsKey(encodeHintType2) ? Integer.parseInt(enumMap.get(encodeHintType2).toString()) : 4;
        ByteMatrix byteMatrix = Encoder.encode(str, errorCorrectionLevel, enumMap).matrix;
        if (byteMatrix == null) {
            throw new IllegalStateException();
        }
        int i2 = parseInt * 2;
        int i3 = byteMatrix.width;
        int i4 = i3 + i2;
        int i5 = byteMatrix.height;
        int i6 = i2 + i5;
        int max = Math.max(i, i4);
        int max2 = Math.max(i, i6);
        int min = Math.min(max / i4, max2 / i6);
        int i7 = (max - (i3 * min)) / 2;
        int i8 = (max2 - (i5 * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix();
        if (max < 1 || max2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        bitMatrix.width = max;
        bitMatrix.height = max2;
        int i9 = (max + 31) / 32;
        bitMatrix.rowSize = i9;
        bitMatrix.bits = new int[i9 * max2];
        int i10 = 0;
        while (i10 < i5) {
            int i11 = i7;
            int i12 = 0;
            while (i12 < i3) {
                if (byteMatrix.get(i12, i10) == 1) {
                    if (i8 < 0 || i11 < 0) {
                        throw new IllegalArgumentException("Left and top must be nonnegative");
                    }
                    if (min < 1 || min < 1) {
                        throw new IllegalArgumentException("Height and width must be at least 1");
                    }
                    int i13 = i11 + min;
                    int i14 = i8 + min;
                    if (i14 > bitMatrix.height || i13 > bitMatrix.width) {
                        throw new IllegalArgumentException("The region must fit inside the matrix");
                    }
                    for (int i15 = i8; i15 < i14; i15++) {
                        int i16 = bitMatrix.rowSize * i15;
                        int i17 = i11;
                        while (i17 < i13) {
                            ByteMatrix byteMatrix2 = byteMatrix;
                            int[] iArr = bitMatrix.bits;
                            int i18 = (i17 / 32) + i16;
                            iArr[i18] = iArr[i18] | (1 << (i17 & 31));
                            i17++;
                            byteMatrix = byteMatrix2;
                        }
                    }
                }
                i12++;
                i11 += min;
                byteMatrix = byteMatrix;
            }
            i10++;
            i8 += min;
        }
        int[] iArr2 = new int[i * i];
        for (int i19 = 0; i19 < i; i19++) {
            for (int i20 = 0; i20 < i; i20++) {
                iArr2[(i19 * i) + i20] = bitMatrix.get(i19, i20) ? DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT : -1;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
        createBitmap.setPixels(iArr2, 0, i, 0, 0, i, i);
        return createBitmap;
    }
}
