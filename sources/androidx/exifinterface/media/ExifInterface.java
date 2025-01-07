package androidx.exifinterface.media;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExifInterface {
    public static final Charset ASCII;
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2;
    public static final int[] BITS_PER_SAMPLE_RGB;
    public static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN;
    public static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN;
    public static final boolean DEBUG = Log.isLoggable("ExifInterface", 3);
    public static final byte[] EXIF_ASCII_PREFIX;
    public static final ExifTag[] EXIF_POINTER_TAGS;
    public static final ExifTag[][] EXIF_TAGS;
    public static final Pattern GPS_TIMESTAMP_PATTERN;
    public static final byte[] HEIF_BRAND_HEIC;
    public static final byte[] HEIF_BRAND_MIF1;
    public static final byte[] HEIF_TYPE_FTYP;
    public static final byte[] IDENTIFIER_EXIF_APP1;
    public static final byte[] IDENTIFIER_XMP_APP1;
    public static final int[] IFD_FORMAT_BYTES_PER_FORMAT;
    public static final String[] IFD_FORMAT_NAMES;
    public static final byte[] JPEG_SIGNATURE;
    public static final byte[] ORF_MAKER_NOTE_HEADER_1;
    public static final byte[] ORF_MAKER_NOTE_HEADER_2;
    public static final int PNG_CHUNK_TYPE_EXIF;
    public static final int PNG_CHUNK_TYPE_IEND;
    public static final int PNG_CHUNK_TYPE_IHDR;
    public static final byte[] PNG_SIGNATURE;
    public static final Set RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY;
    public static final ExifTag TAG_RAF_IMAGE_SIZE;
    public static final byte[] WEBP_CHUNK_TYPE_ANIM;
    public static final byte[] WEBP_CHUNK_TYPE_ANMF;
    public static final byte[] WEBP_CHUNK_TYPE_EXIF;
    public static final byte[] WEBP_CHUNK_TYPE_VP8;
    public static final byte[] WEBP_CHUNK_TYPE_VP8L;
    public static final byte[] WEBP_CHUNK_TYPE_VP8X;
    public static final byte[] WEBP_SIGNATURE_1;
    public static final byte[] WEBP_SIGNATURE_2;
    public static final byte[] WEBP_VP8_SIGNATURE;
    public static final HashMap sExifPointerTagMap;
    public static final HashMap[] sExifTagMapsForReading;
    public static final HashMap[] sExifTagMapsForWriting;
    public boolean mAreThumbnailStripsConsecutive;
    public final HashMap[] mAttributes;
    public final Set mAttributesOffsets;
    public ByteOrder mExifByteOrder;
    public boolean mHasThumbnail;
    public boolean mHasThumbnailStrips;
    public int mMimeType;
    public int mOffsetToExifData;
    public int mOrfMakerNoteOffset;
    public int mOrfThumbnailLength;
    public int mOrfThumbnailOffset;
    public final FileDescriptor mSeekableFileDescriptor;
    public byte[] mThumbnailBytes;
    public int mThumbnailCompression;
    public int mThumbnailLength;
    public int mThumbnailOffset;
    public boolean mXmpIsFromSeparateMarker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ByteOrderedDataOutputStream extends FilterOutputStream {
        public ByteOrder mByteOrder;
        public final OutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.mOutputStream = outputStream;
            this.mByteOrder = byteOrder;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public final void write(byte[] bArr) {
            this.mOutputStream.write(bArr);
        }

        public final void writeByte(int i) {
            this.mOutputStream.write(i);
        }

        public final void writeInt(int i) {
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write(i & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 24) & 255);
                return;
            }
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((i >>> 24) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write(i & 255);
            }
        }

        public final void writeShort(short s) {
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write(s & 255);
                this.mOutputStream.write((s >>> 8) & 255);
            } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((s >>> 8) & 255);
                this.mOutputStream.write(s & 255);
            }
        }

        public final void writeUnsignedInt(long j) {
            if (j > 4294967295L) {
                throw new IllegalArgumentException("val is larger than the maximum value of a 32-bit unsigned integer");
            }
            writeInt((int) j);
        }

        public final void writeUnsignedShort(int i) {
            if (i > 65535) {
                throw new IllegalArgumentException("val is larger than the maximum value of a 16-bit unsigned integer");
            }
            writeShort((short) i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) {
            this.mOutputStream.write(bArr, i, i2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExifAttribute {
        public final byte[] bytes;
        public final long bytesOffset;
        public final int format;
        public final int numberOfComponents;

        public ExifAttribute(byte[] bArr, int i, int i2) {
            this(-1L, bArr, i, i2);
        }

        public static ExifAttribute createString(String str) {
            byte[] bytes = str.concat("\u0000").getBytes(ExifInterface.ASCII);
            return new ExifAttribute(bytes, 2, bytes.length);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length]);
            wrap.order(byteOrder);
            for (long j : jArr) {
                wrap.putInt((int) j);
            }
            return new ExifAttribute(wrap.array(), 4, jArr.length);
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(wrap.array(), 5, rationalArr.length);
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putShort((short) i);
            }
            return new ExifAttribute(wrap.array(), 3, iArr.length);
        }

        public final double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            }
            if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
            if (value instanceof long[]) {
                if (((long[]) value).length == 1) {
                    return r3[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof int[]) {
                if (((int[]) value).length == 1) {
                    return r3[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(value instanceof Rational[])) {
                throw new NumberFormatException("Couldn't find a double value");
            }
            Rational[] rationalArr = (Rational[]) value;
            if (rationalArr.length != 1) {
                throw new NumberFormatException("There are more than one component");
            }
            Rational rational = rationalArr[0];
            return rational.numerator / rational.denominator;
        }

        public final int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            }
            if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(value instanceof int[])) {
                throw new NumberFormatException("Couldn't find a integer value");
            }
            int[] iArr = (int[]) value;
            if (iArr.length == 1) {
                return iArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }

        public final String getStringValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (!(value instanceof Rational[])) {
                return null;
            }
            Rational[] rationalArr = (Rational[]) value;
            while (i < rationalArr.length) {
                sb.append(rationalArr[i].numerator);
                sb.append('/');
                sb.append(rationalArr[i].denominator);
                i++;
                if (i != rationalArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        /* JADX WARN: Can't wrap try/catch for region: R(9:89|(2:91|(2:92|(2:94|(2:97|98)(1:96))(2:99|100)))|101|(2:103|(6:112|113|114|115|116|117)(3:105|(2:107|108)(2:110|111)|109))|121|114|115|116|117) */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0128, code lost:
        
            r14 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:120:0x0129, code lost:
        
            android.util.Log.e("ExifInterface", "IOException occurred while closing InputStream", r14);
         */
        /* JADX WARN: Not initialized variable reg: 6, insn: 0x0032: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]) (LINE:51), block:B:158:0x0032 */
        /* JADX WARN: Removed duplicated region for block: B:161:0x0171 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object getValue(java.nio.ByteOrder r14) {
            /*
                Method dump skipped, instructions count: 406
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object");
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("(");
            sb.append(ExifInterface.IFD_FORMAT_NAMES[this.format]);
            sb.append(", data length:");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.bytes.length, ")");
        }

        public ExifAttribute(long j, byte[] bArr, int i, int i2) {
            this.format = i;
            this.numberOfComponents = i2;
            this.bytesOffset = j;
            this.bytes = bArr;
        }

        public static ExifAttribute createULong(long j, ByteOrder byteOrder) {
            return createULong(new long[]{j}, byteOrder);
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteOrder) {
            return createUShort(new int[]{i}, byteOrder);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Rational {
        public final long denominator;
        public final long numerator;

        public Rational(long j, long j2) {
            if (j2 == 0) {
                this.numerator = 0L;
                this.denominator = 1L;
            } else {
                this.numerator = j;
                this.denominator = j2;
            }
        }

        public final String toString() {
            return this.numerator + "/" + this.denominator;
        }
    }

    static {
        Arrays.asList(1, 6, 3, 8);
        Arrays.asList(2, 7, 4, 5);
        BITS_PER_SAMPLE_RGB = new int[]{8, 8, 8};
        BITS_PER_SAMPLE_GREYSCALE_2 = new int[]{8};
        JPEG_SIGNATURE = new byte[]{-1, -40, -1};
        HEIF_TYPE_FTYP = new byte[]{102, 116, 121, 112};
        HEIF_BRAND_MIF1 = new byte[]{109, 105, 102, 49};
        HEIF_BRAND_HEIC = new byte[]{104, 101, 105, 99};
        ORF_MAKER_NOTE_HEADER_1 = new byte[]{79, 76, 89, 77, 80, 0};
        ORF_MAKER_NOTE_HEADER_2 = new byte[]{79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
        PNG_SIGNATURE = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
        PNG_CHUNK_TYPE_EXIF = intFromBytes(101, 88, 73, 102);
        PNG_CHUNK_TYPE_IHDR = intFromBytes(73, 72, 68, 82);
        PNG_CHUNK_TYPE_IEND = intFromBytes(73, 69, 78, 68);
        WEBP_SIGNATURE_1 = new byte[]{82, 73, 70, 70};
        WEBP_SIGNATURE_2 = new byte[]{87, 69, 66, 80};
        WEBP_CHUNK_TYPE_EXIF = new byte[]{69, 88, 73, 70};
        WEBP_VP8_SIGNATURE = new byte[]{-99, 1, 42};
        WEBP_CHUNK_TYPE_VP8X = "VP8X".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_VP8L = "VP8L".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_VP8 = "VP8 ".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_ANIM = "ANIM".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_ANMF = "ANMF".getBytes(Charset.defaultCharset());
        IFD_FORMAT_NAMES = new String[]{"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        IFD_FORMAT_BYTES_PER_FORMAT = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        EXIF_ASCII_PREFIX = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
        ExifTag[] exifTagArr = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ImageWidth", 256, 3, 4), new ExifTag("ImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7), new ExifTag("Xmp", 700, 1)};
        ExifTag[] exifTagArr2 = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("PhotographicSensitivity", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("SensitivityType", 34864, 3), new ExifTag("StandardOutputSensitivity", 34865, 4), new ExifTag("RecommendedExposureIndex", 34866, 4), new ExifTag("ISOSpeed", 34867, 4), new ExifTag("ISOSpeedLatitudeyyy", 34868, 4), new ExifTag("ISOSpeedLatitudezzz", 34869, 4), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("OffsetTime", 36880, 2), new ExifTag("OffsetTimeOriginal", 36881, 2), new ExifTag("OffsetTimeDigitized", 36882, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("CameraOwnerName", 42032, 2), new ExifTag("BodySerialNumber", 42033, 2), new ExifTag("LensSpecification", 42034, 5), new ExifTag("LensMake", 42035, 2), new ExifTag("LensModel", 42036, 2), new ExifTag("Gamma", 42240, 5), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        ExifTag[] exifTagArr3 = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5, 10), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5, 10), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3), new ExifTag("GPSHPositioningError", 31, 5)};
        ExifTag[] exifTagArr4 = {new ExifTag("InteroperabilityIndex", 1, 2)};
        ExifTag[] exifTagArr5 = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ThumbnailImageWidth", 256, 3, 4), new ExifTag("ThumbnailImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("ThumbnailOrientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3);
        EXIF_TAGS = new ExifTag[][]{exifTagArr, exifTagArr2, exifTagArr3, exifTagArr4, exifTagArr5, exifTagArr, new ExifTag[]{new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, 4), new ExifTag("ImageProcessingIFDPointer", 8256, 4)}, new ExifTag[]{new ExifTag("PreviewImageStart", 257, 4), new ExifTag("PreviewImageLength", 258, 4)}, new ExifTag[]{new ExifTag("AspectFrame", 4371, 3)}, new ExifTag[]{new ExifTag("ColorSpace", 55, 3)}};
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("CameraSettingsIFDPointer", 8224, 1), new ExifTag("ImageProcessingIFDPointer", 8256, 1)};
        sExifTagMapsForReading = new HashMap[10];
        sExifTagMapsForWriting = new HashMap[10];
        RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY = Collections.unmodifiableSet(new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance")));
        sExifPointerTagMap = new HashMap();
        Charset forName = Charset.forName("US-ASCII");
        ASCII = forName;
        IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(forName);
        IDENTIFIER_XMP_APP1 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(forName);
        Locale locale = Locale.US;
        new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        int i = 0;
        while (true) {
            ExifTag[][] exifTagArr6 = EXIF_TAGS;
            if (i >= exifTagArr6.length) {
                HashMap hashMap = sExifPointerTagMap;
                ExifTag[] exifTagArr7 = EXIF_POINTER_TAGS;
                hashMap.put(Integer.valueOf(exifTagArr7[0].number), 5);
                hashMap.put(Integer.valueOf(exifTagArr7[1].number), 1);
                hashMap.put(Integer.valueOf(exifTagArr7[2].number), 2);
                hashMap.put(Integer.valueOf(exifTagArr7[3].number), 3);
                hashMap.put(Integer.valueOf(exifTagArr7[4].number), 7);
                hashMap.put(Integer.valueOf(exifTagArr7[5].number), 8);
                Pattern.compile(".*[1-9].*");
                GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
            sExifTagMapsForReading[i] = new HashMap();
            sExifTagMapsForWriting[i] = new HashMap();
            for (ExifTag exifTag : exifTagArr6[i]) {
                sExifTagMapsForReading[i].put(Integer.valueOf(exifTag.number), exifTag);
                sExifTagMapsForWriting[i].put(exifTag.name, exifTag);
            }
            i++;
        }
    }

    public ExifInterface(FileDescriptor fileDescriptor) {
        boolean z;
        ExifTag[][] exifTagArr = EXIF_TAGS;
        this.mAttributes = new HashMap[exifTagArr.length];
        this.mAttributesOffsets = new HashSet(exifTagArr.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (fileDescriptor == null) {
            throw new NullPointerException("fileDescriptor cannot be null");
        }
        FileInputStream fileInputStream = null;
        try {
            Os.lseek(fileDescriptor, 0L, OsConstants.SEEK_CUR);
            this.mSeekableFileDescriptor = fileDescriptor;
            try {
                fileDescriptor = Os.dup(fileDescriptor);
                z = true;
            } catch (Exception e) {
                throw new IOException("Failed to duplicate file descriptor", e);
            }
        } catch (Exception unused) {
            if (DEBUG) {
                Log.d("ExifInterface", "The file descriptor for the given input is not seekable");
            }
            this.mSeekableFileDescriptor = null;
            z = false;
        }
        try {
            FileInputStream fileInputStream2 = new FileInputStream(fileDescriptor);
            try {
                loadAttributes(fileInputStream2);
                ExifInterfaceUtils.closeQuietly(fileInputStream2);
                if (z) {
                    ExifInterfaceUtils.closeFileDescriptor(fileDescriptor);
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                ExifInterfaceUtils.closeQuietly(fileInputStream);
                if (z) {
                    ExifInterfaceUtils.closeFileDescriptor(fileDescriptor);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Pair guessDataFormat(String str) {
        if (str.contains(",")) {
            String[] split = str.split(",", -1);
            Pair guessDataFormat = guessDataFormat(split[0]);
            if (((Integer) guessDataFormat.first).intValue() == 2) {
                return guessDataFormat;
            }
            for (int i = 1; i < split.length; i++) {
                Pair guessDataFormat2 = guessDataFormat(split[i]);
                int intValue = (((Integer) guessDataFormat2.first).equals(guessDataFormat.first) || ((Integer) guessDataFormat2.second).equals(guessDataFormat.first)) ? ((Integer) guessDataFormat.first).intValue() : -1;
                int intValue2 = (((Integer) guessDataFormat.second).intValue() == -1 || !(((Integer) guessDataFormat2.first).equals(guessDataFormat.second) || ((Integer) guessDataFormat2.second).equals(guessDataFormat.second))) ? -1 : ((Integer) guessDataFormat.second).intValue();
                if (intValue == -1 && intValue2 == -1) {
                    return new Pair(2, -1);
                }
                if (intValue == -1) {
                    guessDataFormat = new Pair(Integer.valueOf(intValue2), -1);
                } else if (intValue2 == -1) {
                    guessDataFormat = new Pair(Integer.valueOf(intValue), -1);
                }
            }
            return guessDataFormat;
        }
        if (!str.contains("/")) {
            try {
                try {
                    long parseLong = Long.parseLong(str);
                    return (parseLong < 0 || parseLong > 65535) ? parseLong < 0 ? new Pair(9, -1) : new Pair(4, -1) : new Pair(3, 4);
                } catch (NumberFormatException unused) {
                    return new Pair(2, -1);
                }
            } catch (NumberFormatException unused2) {
                Double.parseDouble(str);
                return new Pair(12, -1);
            }
        }
        String[] split2 = str.split("/", -1);
        if (split2.length == 2) {
            try {
                long parseDouble = (long) Double.parseDouble(split2[0]);
                long parseDouble2 = (long) Double.parseDouble(split2[1]);
                if (parseDouble >= 0 && parseDouble2 >= 0) {
                    if (parseDouble <= 2147483647L && parseDouble2 <= 2147483647L) {
                        return new Pair(10, 5);
                    }
                    return new Pair(5, -1);
                }
                return new Pair(10, -1);
            } catch (NumberFormatException unused3) {
            }
        }
        return new Pair(2, -1);
    }

    public static int intFromBytes(int i, int i2, int i3, int i4) {
        return ((i & 255) << 24) | ((i2 & 255) << 16) | ((i3 & 255) << 8) | (i4 & 255);
    }

    public static ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        short readShort = byteOrderedDataInputStream.readShort();
        boolean z = DEBUG;
        if (readShort == 18761) {
            if (z) {
                Log.d("ExifInterface", "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (readShort == 19789) {
            if (z) {
                Log.d("ExifInterface", "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
    }

    public final void addDefaultValuesForCompatibility() {
        String attribute = getAttribute("DateTimeOriginal");
        if (attribute != null && getAttribute("DateTime") == null) {
            this.mAttributes[0].put("DateTime", ExifAttribute.createString(attribute));
        }
        if (getAttribute("ImageWidth") == null) {
            this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("ImageLength") == null) {
            this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("Orientation") == null) {
            this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("LightSource") == null) {
            this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
    }

    public final String getAttribute(String str) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return null;
        }
        if (!str.equals("GPSTimeStamp")) {
            if (!RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY.contains(str)) {
                return exifAttribute.getStringValue(this.mExifByteOrder);
            }
            try {
                return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        int i = exifAttribute.format;
        if (i != 5 && i != 10) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("GPS Timestamp format is not rational. format=", "ExifInterface", i);
            return null;
        }
        Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
        if (rationalArr == null || rationalArr.length != 3) {
            Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(rationalArr));
            return null;
        }
        Rational rational = rationalArr[0];
        Integer valueOf = Integer.valueOf((int) (rational.numerator / rational.denominator));
        Rational rational2 = rationalArr[1];
        Integer valueOf2 = Integer.valueOf((int) (rational2.numerator / rational2.denominator));
        Rational rational3 = rationalArr[2];
        return String.format("%02d:%02d:%02d", valueOf, valueOf2, Integer.valueOf((int) (rational3.numerator / rational3.denominator)));
    }

    public final byte[] getAttributeBytes() {
        ExifAttribute exifAttribute = getExifAttribute("Xmp");
        if (exifAttribute != null) {
            return exifAttribute.bytes;
        }
        return null;
    }

    public final ExifAttribute getExifAttribute(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            if (DEBUG) {
                Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            str = "PhotographicSensitivity";
        }
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(str);
            if (exifAttribute != null) {
                return exifAttribute;
            }
        }
        return null;
    }

    public final void getHeifAttributes(final SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        String str;
        String str2;
        String str3;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(new MediaDataSource() { // from class: androidx.exifinterface.media.ExifInterface.1
                    public long mPosition;

                    @Override // android.media.MediaDataSource
                    public final long getSize() {
                        return -1L;
                    }

                    @Override // android.media.MediaDataSource
                    public final int readAt(long j, byte[] bArr, int i, int i2) {
                        if (i2 == 0) {
                            return 0;
                        }
                        if (j < 0) {
                            return -1;
                        }
                        try {
                            long j2 = this.mPosition;
                            if (j2 != j) {
                                if (j2 >= 0 && j >= j2 + SeekableByteOrderedDataInputStream.this.mDataInputStream.available()) {
                                    return -1;
                                }
                                SeekableByteOrderedDataInputStream.this.seek(j);
                                this.mPosition = j;
                            }
                            if (i2 > SeekableByteOrderedDataInputStream.this.mDataInputStream.available()) {
                                i2 = SeekableByteOrderedDataInputStream.this.mDataInputStream.available();
                            }
                            int read = SeekableByteOrderedDataInputStream.this.read(bArr, i, i2);
                            if (read >= 0) {
                                this.mPosition += read;
                                return read;
                            }
                        } catch (IOException unused) {
                        }
                        this.mPosition = -1L;
                        return -1;
                    }

                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public final void close() {
                    }
                });
                String extractMetadata = mediaMetadataRetriever.extractMetadata(33);
                String extractMetadata2 = mediaMetadataRetriever.extractMetadata(34);
                String extractMetadata3 = mediaMetadataRetriever.extractMetadata(26);
                String extractMetadata4 = mediaMetadataRetriever.extractMetadata(17);
                if ("yes".equals(extractMetadata3)) {
                    str = mediaMetadataRetriever.extractMetadata(29);
                    str2 = mediaMetadataRetriever.extractMetadata(30);
                    str3 = mediaMetadataRetriever.extractMetadata(31);
                } else if ("yes".equals(extractMetadata4)) {
                    str = mediaMetadataRetriever.extractMetadata(18);
                    str2 = mediaMetadataRetriever.extractMetadata(19);
                    str3 = mediaMetadataRetriever.extractMetadata(24);
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                }
                if (str != null) {
                    this.mAttributes[0].put("ImageWidth", ExifAttribute.createUShort(Integer.parseInt(str), this.mExifByteOrder));
                }
                if (str2 != null) {
                    this.mAttributes[0].put("ImageLength", ExifAttribute.createUShort(Integer.parseInt(str2), this.mExifByteOrder));
                }
                if (str3 != null) {
                    int parseInt = Integer.parseInt(str3);
                    this.mAttributes[0].put("Orientation", ExifAttribute.createUShort(parseInt != 90 ? parseInt != 180 ? parseInt != 270 ? 1 : 8 : 3 : 6, this.mExifByteOrder));
                }
                if (extractMetadata != null && extractMetadata2 != null) {
                    int parseInt2 = Integer.parseInt(extractMetadata);
                    int parseInt3 = Integer.parseInt(extractMetadata2);
                    if (parseInt3 <= 6) {
                        throw new IOException("Invalid exif length");
                    }
                    seekableByteOrderedDataInputStream.seek(parseInt2);
                    byte[] bArr = new byte[6];
                    seekableByteOrderedDataInputStream.readFully(bArr);
                    int i = parseInt2 + 6;
                    int i2 = parseInt3 - 6;
                    if (!Arrays.equals(bArr, IDENTIFIER_EXIF_APP1)) {
                        throw new IOException("Invalid identifier");
                    }
                    byte[] bArr2 = new byte[i2];
                    seekableByteOrderedDataInputStream.readFully(bArr2);
                    this.mOffsetToExifData = i;
                    readExifSegment(0, bArr2);
                }
                String extractMetadata5 = mediaMetadataRetriever.extractMetadata(41);
                String extractMetadata6 = mediaMetadataRetriever.extractMetadata(42);
                if (extractMetadata5 != null && extractMetadata6 != null) {
                    int parseInt4 = Integer.parseInt(extractMetadata5);
                    int parseInt5 = Integer.parseInt(extractMetadata6);
                    long j = parseInt4;
                    seekableByteOrderedDataInputStream.seek(j);
                    byte[] bArr3 = new byte[parseInt5];
                    seekableByteOrderedDataInputStream.readFully(bArr3);
                    if (getAttribute("Xmp") == null) {
                        this.mAttributes[0].put("Xmp", new ExifAttribute(j, bArr3, 1, parseInt5));
                    }
                }
                if (DEBUG) {
                    Log.d("ExifInterface", "Heif meta: " + str + "x" + str2 + ", rotation " + str3);
                }
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (RuntimeException e) {
            throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x018c, code lost:
    
        r23.mByteOrder = r22.mExifByteOrder;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0190, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void getJpegAttributes(androidx.exifinterface.media.ExifInterface.ByteOrderedDataInputStream r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getJpegAttributes(androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:151:0x00bb, code lost:
    
        if (r8 != null) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x012b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x012e  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int getMimeType(java.io.BufferedInputStream r18) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getMimeType(java.io.BufferedInputStream):int");
    }

    public final void getOrfAttributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        int i;
        int i2;
        getRawAttributes(seekableByteOrderedDataInputStream);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get("MakerNote");
        if (exifAttribute != null) {
            SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream2 = new SeekableByteOrderedDataInputStream(exifAttribute.bytes);
            seekableByteOrderedDataInputStream2.mByteOrder = this.mExifByteOrder;
            byte[] bArr = ORF_MAKER_NOTE_HEADER_1;
            byte[] bArr2 = new byte[bArr.length];
            seekableByteOrderedDataInputStream2.readFully(bArr2);
            seekableByteOrderedDataInputStream2.seek(0L);
            byte[] bArr3 = ORF_MAKER_NOTE_HEADER_2;
            byte[] bArr4 = new byte[bArr3.length];
            seekableByteOrderedDataInputStream2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                seekableByteOrderedDataInputStream2.seek(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                seekableByteOrderedDataInputStream2.seek(12L);
            }
            readImageFileDirectory(seekableByteOrderedDataInputStream2, 6);
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[7].get("PreviewImageStart");
            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[7].get("PreviewImageLength");
            if (exifAttribute2 != null && exifAttribute3 != null) {
                this.mAttributes[5].put("JPEGInterchangeFormat", exifAttribute2);
                this.mAttributes[5].put("JPEGInterchangeFormatLength", exifAttribute3);
            }
            ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[8].get("AspectFrame");
            if (exifAttribute4 != null) {
                int[] iArr = (int[]) exifAttribute4.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                int i3 = iArr[2];
                int i4 = iArr[0];
                if (i3 <= i4 || (i = iArr[3]) <= (i2 = iArr[1])) {
                    return;
                }
                int i5 = (i3 - i4) + 1;
                int i6 = (i - i2) + 1;
                if (i5 < i6) {
                    int i7 = i5 + i6;
                    i6 = i7 - i6;
                    i5 = i7 - i6;
                }
                ExifAttribute createUShort = ExifAttribute.createUShort(i5, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort(i6, this.mExifByteOrder);
                this.mAttributes[0].put("ImageWidth", createUShort);
                this.mAttributes[0].put("ImageLength", createUShort2);
            }
        }
    }

    public final void getPngAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "getPngAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.mByteOrder = ByteOrder.BIG_ENDIAN;
        int i = byteOrderedDataInputStream.mPosition;
        byteOrderedDataInputStream.skipFully(PNG_SIGNATURE.length);
        while (true) {
            try {
                int readInt = byteOrderedDataInputStream.readInt();
                int readInt2 = byteOrderedDataInputStream.readInt();
                int i2 = byteOrderedDataInputStream.mPosition - i;
                if (i2 == 16 && readInt2 != PNG_CHUNK_TYPE_IHDR) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appear as the first chunk");
                }
                if (readInt2 == PNG_CHUNK_TYPE_IEND) {
                    return;
                }
                if (readInt2 == PNG_CHUNK_TYPE_EXIF) {
                    this.mOffsetToExifData = i2;
                    byte[] bArr = new byte[readInt];
                    byteOrderedDataInputStream.readFully(bArr);
                    int readInt3 = byteOrderedDataInputStream.readInt();
                    CRC32 crc32 = new CRC32();
                    crc32.update(readInt2 >>> 24);
                    crc32.update(readInt2 >>> 16);
                    crc32.update(readInt2 >>> 8);
                    crc32.update(readInt2);
                    crc32.update(bArr);
                    if (((int) crc32.getValue()) == readInt3) {
                        readExifSegment(0, bArr);
                        validateImages();
                        setThumbnailData(new ByteOrderedDataInputStream(bArr));
                        return;
                    } else {
                        throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + readInt3 + ", calculated CRC value: " + crc32.getValue());
                    }
                }
                byteOrderedDataInputStream.skipFully(readInt + 4);
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt PNG file.", e);
            }
        }
    }

    public final void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        boolean z = DEBUG;
        if (z) {
            Log.d("ExifInterface", "getRafAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.skipFully(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        byteOrderedDataInputStream.readFully(bArr);
        byteOrderedDataInputStream.readFully(bArr2);
        byteOrderedDataInputStream.readFully(bArr3);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        int i3 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i2];
        byteOrderedDataInputStream.skipFully(i - byteOrderedDataInputStream.mPosition);
        byteOrderedDataInputStream.readFully(bArr4);
        getJpegAttributes(new ByteOrderedDataInputStream(bArr4), i, 5);
        byteOrderedDataInputStream.skipFully(i3 - byteOrderedDataInputStream.mPosition);
        byteOrderedDataInputStream.mByteOrder = ByteOrder.BIG_ENDIAN;
        int readInt = byteOrderedDataInputStream.readInt();
        if (z) {
            ExifInterface$$ExternalSyntheticOutline0.m("numberOfDirectoryEntry: ", "ExifInterface", readInt);
        }
        for (int i4 = 0; i4 < readInt; i4++) {
            int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
            int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
            if (readUnsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                short readShort = byteOrderedDataInputStream.readShort();
                short readShort2 = byteOrderedDataInputStream.readShort();
                ExifAttribute createUShort = ExifAttribute.createUShort(readShort, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort(readShort2, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", createUShort);
                this.mAttributes[0].put("ImageWidth", createUShort2);
                if (z) {
                    Log.d("ExifInterface", "Updated to length: " + ((int) readShort) + ", width: " + ((int) readShort2));
                    return;
                }
                return;
            }
            byteOrderedDataInputStream.skipFully(readUnsignedShort2);
        }
    }

    public final void getRawAttributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        ExifAttribute exifAttribute;
        parseTiffHeaders(seekableByteOrderedDataInputStream);
        readImageFileDirectory(seekableByteOrderedDataInputStream, 0);
        updateImageSizeValues(seekableByteOrderedDataInputStream, 0);
        updateImageSizeValues(seekableByteOrderedDataInputStream, 5);
        updateImageSizeValues(seekableByteOrderedDataInputStream, 4);
        validateImages();
        if (this.mMimeType != 8 || (exifAttribute = (ExifAttribute) this.mAttributes[1].get("MakerNote")) == null) {
            return;
        }
        SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream2 = new SeekableByteOrderedDataInputStream(exifAttribute.bytes);
        seekableByteOrderedDataInputStream2.mByteOrder = this.mExifByteOrder;
        seekableByteOrderedDataInputStream2.skipFully(6);
        readImageFileDirectory(seekableByteOrderedDataInputStream2, 9);
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[9].get("ColorSpace");
        if (exifAttribute2 != null) {
            this.mAttributes[1].put("ColorSpace", exifAttribute2);
        }
    }

    public final void getRw2Attributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "getRw2Attributes starting with: " + seekableByteOrderedDataInputStream);
        }
        getRawAttributes(seekableByteOrderedDataInputStream);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[0].get("JpgFromRaw");
        if (exifAttribute != null) {
            getJpegAttributes(new ByteOrderedDataInputStream(exifAttribute.bytes), (int) exifAttribute.bytesOffset, 5);
        }
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[0].get("ISO");
        ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[1].get("PhotographicSensitivity");
        if (exifAttribute2 == null || exifAttribute3 != null) {
            return;
        }
        this.mAttributes[1].put("PhotographicSensitivity", exifAttribute2);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] getThumbnailBytes() {
        /*
            r6 = this;
            boolean r0 = r6.mHasThumbnail
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            byte[] r0 = r6.mThumbnailBytes
            if (r0 == 0) goto Lb
            return r0
        Lb:
            java.io.FileDescriptor r0 = r6.mSeekableFileDescriptor     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            java.io.FileDescriptor r0 = android.system.Os.dup(r0)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            int r2 = android.system.OsConstants.SEEK_SET     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r3 = 0
            android.system.Os.lseek(r0, r3, r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r3 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            int r4 = r6.mThumbnailOffset     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            int r5 = r6.mOffsetToExifData     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            int r4 = r4 + r5
            r3.skipFully(r4)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            int r4 = r6.mThumbnailLength     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r3.readFully(r4)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r6.mThumbnailBytes = r4     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)
            if (r0 == 0) goto L3b
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r0)
        L3b:
            return r4
        L3c:
            r6 = move-exception
            r1 = r2
            goto L5f
        L3f:
            r6 = move-exception
            goto L4f
        L41:
            r6 = move-exception
            goto L5f
        L43:
            r6 = move-exception
            r2 = r1
            goto L4f
        L46:
            r0 = r1
            goto L5f
        L48:
            r0 = r1
            r2 = r0
            goto L4f
        L4b:
            r6 = move-exception
            goto L46
        L4d:
            r6 = move-exception
            goto L48
        L4f:
            java.lang.String r3 = "ExifInterface"
            java.lang.String r4 = "Encountered exception while getting thumbnail"
            android.util.Log.d(r3, r4, r6)     // Catch: java.lang.Throwable -> L3c
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)
            if (r0 == 0) goto L5e
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r0)
        L5e:
            return r1
        L5f:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r1)
            if (r0 == 0) goto L67
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r0)
        L67:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getThumbnailBytes():byte[]");
    }

    public final void getWebpAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "getWebpAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.mByteOrder = ByteOrder.LITTLE_ENDIAN;
        byteOrderedDataInputStream.skipFully(WEBP_SIGNATURE_1.length);
        int readInt = byteOrderedDataInputStream.readInt() + 8;
        byte[] bArr = WEBP_SIGNATURE_2;
        byteOrderedDataInputStream.skipFully(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                byteOrderedDataInputStream.readFully(bArr2);
                int readInt2 = byteOrderedDataInputStream.readInt();
                int i = length + 8;
                if (Arrays.equals(WEBP_CHUNK_TYPE_EXIF, bArr2)) {
                    byte[] bArr3 = new byte[readInt2];
                    byteOrderedDataInputStream.readFully(bArr3);
                    byte[] bArr4 = IDENTIFIER_EXIF_APP1;
                    if (ExifInterfaceUtils.startsWith(bArr3, bArr4)) {
                        bArr3 = Arrays.copyOfRange(bArr3, bArr4.length, readInt2 - bArr4.length);
                    }
                    this.mOffsetToExifData = i;
                    readExifSegment(0, bArr3);
                    setThumbnailData(new ByteOrderedDataInputStream(bArr3));
                    return;
                }
                if (readInt2 % 2 == 1) {
                    readInt2++;
                }
                length = i + readInt2;
                if (length == readInt) {
                    return;
                }
                if (length > readInt) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                byteOrderedDataInputStream.skipFully(readInt2);
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt WebP file.", e);
            }
        }
    }

    public final void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("JPEGInterchangeFormat");
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("JPEGInterchangeFormatLength");
        if (exifAttribute == null || exifAttribute2 == null) {
            return;
        }
        int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
        int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
        if (this.mMimeType == 7) {
            intValue += this.mOrfMakerNoteOffset;
        }
        if (intValue > 0 && intValue2 > 0) {
            this.mHasThumbnail = true;
            if (this.mSeekableFileDescriptor == null) {
                byte[] bArr = new byte[intValue2];
                byteOrderedDataInputStream.skipFully(intValue);
                byteOrderedDataInputStream.readFully(bArr);
                this.mThumbnailBytes = bArr;
            }
            this.mThumbnailOffset = intValue;
            this.mThumbnailLength = intValue2;
        }
        if (DEBUG) {
            Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + intValue + ", length: " + intValue2);
        }
    }

    public final boolean isThumbnail(HashMap hashMap) {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("ImageLength");
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("ImageWidth");
        if (exifAttribute == null || exifAttribute2 == null) {
            return false;
        }
        return exifAttribute.getIntValue(this.mExifByteOrder) <= 512 && exifAttribute2.getIntValue(this.mExifByteOrder) <= 512;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void loadAttributes(java.io.InputStream r8) {
        /*
            r7 = this;
            boolean r0 = androidx.exifinterface.media.ExifInterface.DEBUG
            r1 = 0
            r2 = r1
        L4:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r3 = androidx.exifinterface.media.ExifInterface.EXIF_TAGS     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            int r3 = r3.length     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            if (r2 >= r3) goto L1b
            java.util.HashMap[] r3 = r7.mAttributes     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r4.<init>()     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r3[r2] = r4     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            int r2 = r2 + 1
            goto L4
        L15:
            r8 = move-exception
            goto L91
        L18:
            r8 = move-exception
            goto L87
        L1b:
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r3 = 5000(0x1388, float:7.006E-42)
            r2.<init>(r8, r3)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            int r8 = r7.getMimeType(r2)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r7.mMimeType = r8     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r3 = 14
            r4 = 13
            r5 = 9
            r6 = 4
            if (r8 == r6) goto L63
            if (r8 == r5) goto L63
            if (r8 == r4) goto L63
            if (r8 != r3) goto L38
            goto L63
        L38:
            androidx.exifinterface.media.ExifInterface$SeekableByteOrderedDataInputStream r8 = new androidx.exifinterface.media.ExifInterface$SeekableByteOrderedDataInputStream     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            int r1 = r7.mMimeType     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r2 = 12
            if (r1 != r2) goto L47
            r7.getHeifAttributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L59
        L47:
            r2 = 7
            if (r1 != r2) goto L4e
            r7.getOrfAttributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L59
        L4e:
            r2 = 10
            if (r1 != r2) goto L56
            r7.getRw2Attributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L59
        L56:
            r7.getRawAttributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
        L59:
            int r1 = r7.mOffsetToExifData     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r8.seek(r1)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r7.setThumbnailData(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L81
        L63:
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r8 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            int r2 = r7.mMimeType     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            if (r2 != r6) goto L70
            r7.getJpegAttributes(r8, r1, r1)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L81
        L70:
            if (r2 != r4) goto L76
            r7.getPngAttributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L81
        L76:
            if (r2 != r5) goto L7c
            r7.getRafAttributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
            goto L81
        L7c:
            if (r2 != r3) goto L81
            r7.getWebpAttributes(r8)     // Catch: java.lang.Throwable -> L15 java.lang.Throwable -> L18
        L81:
            r7.addDefaultValuesForCompatibility()
            if (r0 == 0) goto La2
            goto L9f
        L87:
            if (r0 == 0) goto L9a
            java.lang.String r1 = "ExifInterface"
            java.lang.String r2 = "Invalid image: ExifInterface got an unsupported image format file (ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface."
            android.util.Log.w(r1, r2, r8)     // Catch: java.lang.Throwable -> L15
            goto L9a
        L91:
            r7.addDefaultValuesForCompatibility()
            if (r0 == 0) goto L99
            r7.printAttributes()
        L99:
            throw r8
        L9a:
            r7.addDefaultValuesForCompatibility()
            if (r0 == 0) goto La2
        L9f:
            r7.printAttributes()
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.loadAttributes(java.io.InputStream):void");
    }

    public final void parseTiffHeaders(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        ByteOrder readByteOrder = readByteOrder(seekableByteOrderedDataInputStream);
        this.mExifByteOrder = readByteOrder;
        seekableByteOrderedDataInputStream.mByteOrder = readByteOrder;
        int readUnsignedShort = seekableByteOrderedDataInputStream.readUnsignedShort();
        int i = this.mMimeType;
        if (i != 7 && i != 10 && readUnsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
        }
        int readInt = seekableByteOrderedDataInputStream.readInt();
        if (readInt < 8) {
            throw new IOException(AnnotationValue$1$$ExternalSyntheticOutline0.m(readInt, "Invalid first Ifd offset: "));
        }
        int i2 = readInt - 8;
        if (i2 > 0) {
            seekableByteOrderedDataInputStream.skipFully(i2);
        }
    }

    public final void printAttributes() {
        for (int i = 0; i < this.mAttributes.length; i++) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("The size of tag group[", "]: ", i);
            m.append(this.mAttributes[i].size());
            Log.d("ExifInterface", m.toString());
            for (Map.Entry entry : this.mAttributes[i].entrySet()) {
                ExifAttribute exifAttribute = (ExifAttribute) entry.getValue();
                Log.d("ExifInterface", "tagName: " + ((String) entry.getKey()) + ", tagType: " + exifAttribute.toString() + ", tagValue: '" + exifAttribute.getStringValue(this.mExifByteOrder) + "'");
            }
        }
    }

    public final void readExifSegment(int i, byte[] bArr) {
        SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream = new SeekableByteOrderedDataInputStream(bArr);
        parseTiffHeaders(seekableByteOrderedDataInputStream);
        readImageFileDirectory(seekableByteOrderedDataInputStream, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x024a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readImageFileDirectory(androidx.exifinterface.media.ExifInterface.SeekableByteOrderedDataInputStream r25, int r26) {
        /*
            Method dump skipped, instructions count: 832
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.readImageFileDirectory(androidx.exifinterface.media.ExifInterface$SeekableByteOrderedDataInputStream, int):void");
    }

    public final void removeAttribute(String str) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            this.mAttributes[i].remove(str);
        }
    }

    public final void replaceInvalidTags(String str, String str2, int i) {
        if (this.mAttributes[i].isEmpty() || this.mAttributes[i].get(str) == null) {
            return;
        }
        HashMap hashMap = this.mAttributes[i];
        hashMap.put(str2, (ExifAttribute) hashMap.get(str));
        this.mAttributes[i].remove(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x00a8: MOVE (r6 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]) (LINE:169), block:B:97:0x00a8 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable, java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileOutputStream, java.io.OutputStream] */
    public final void saveAttributes() {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        File createTempFile;
        int i;
        FileInputStream fileInputStream2;
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream3;
        FileInputStream fileInputStream4;
        ?? fileOutputStream2;
        ?? r7;
        FileInputStream fileInputStream5;
        int i2 = this.mMimeType;
        if (i2 != 4 && i2 != 13 && i2 != 14) {
            throw new IOException("ExifInterface only supports saving attributes for JPEG, PNG, and WebP formats.");
        }
        if (this.mSeekableFileDescriptor == null) {
            throw new IOException("ExifInterface does not support saving attributes for the current input.");
        }
        if (this.mHasThumbnail && this.mHasThumbnailStrips && !this.mAreThumbnailStripsConsecutive) {
            throw new IOException("ExifInterface does not support saving attributes when the image file has non-consecutive thumbnail strips");
        }
        int i3 = this.mThumbnailCompression;
        FileInputStream fileInputStream6 = null;
        this.mThumbnailBytes = (i3 == 6 || i3 == 7) ? getThumbnailBytes() : null;
        try {
            createTempFile = File.createTempFile("temp", "tmp");
            FileDescriptor fileDescriptor = this.mSeekableFileDescriptor;
            i = OsConstants.SEEK_SET;
            Os.lseek(fileDescriptor, 0L, i);
            fileInputStream = new FileInputStream(this.mSeekableFileDescriptor);
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            fileOutputStream = new FileOutputStream(createTempFile);
            try {
                ExifInterfaceUtils.copy(fileInputStream, fileOutputStream);
                ExifInterfaceUtils.closeQuietly(fileInputStream);
                ExifInterfaceUtils.closeQuietly(fileOutputStream);
                try {
                    try {
                        try {
                            fileInputStream2 = new FileInputStream(createTempFile);
                            try {
                                Os.lseek(this.mSeekableFileDescriptor, 0L, i);
                                r7 = new FileOutputStream(this.mSeekableFileDescriptor);
                            } catch (Exception e2) {
                                e = e2;
                                r7 = 0;
                            }
                            try {
                                bufferedInputStream = new BufferedInputStream(fileInputStream2);
                            } catch (Exception e3) {
                                e = e3;
                                fileInputStream5 = r7;
                                fileInputStream6 = fileInputStream5;
                                try {
                                    try {
                                        fileInputStream4 = new FileInputStream(createTempFile);
                                        try {
                                            Os.lseek(this.mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
                                            fileOutputStream2 = new FileOutputStream(this.mSeekableFileDescriptor);
                                        } catch (Exception e4) {
                                            e = e4;
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    } catch (Exception e5) {
                                        e = e5;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    fileInputStream4 = fileInputStream2;
                                }
                                try {
                                    ExifInterfaceUtils.copy(fileInputStream4, fileOutputStream2);
                                    ExifInterfaceUtils.closeQuietly(fileInputStream4);
                                    ExifInterfaceUtils.closeQuietly(fileOutputStream2);
                                    throw new IOException("Failed to save new file", e);
                                } catch (Exception e6) {
                                    e = e6;
                                    fileInputStream6 = fileOutputStream2;
                                    fileInputStream2 = fileInputStream4;
                                    throw new IOException("Failed to save new file. Original file is stored in " + createTempFile.getAbsolutePath(), e);
                                } catch (Throwable th4) {
                                    th = th4;
                                    fileInputStream6 = fileOutputStream2;
                                    ExifInterfaceUtils.closeQuietly(fileInputStream4);
                                    ExifInterfaceUtils.closeQuietly(fileInputStream6);
                                    throw th;
                                }
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            fileInputStream6 = fileInputStream3;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        bufferedInputStream = null;
                    }
                    try {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(r7);
                        try {
                            int i4 = this.mMimeType;
                            if (i4 == 4) {
                                saveJpegAttributes(bufferedInputStream, bufferedOutputStream);
                            } else if (i4 == 13) {
                                savePngAttributes(bufferedInputStream, bufferedOutputStream);
                            } else if (i4 == 14) {
                                saveWebpAttributes(bufferedInputStream, bufferedOutputStream);
                            }
                            ExifInterfaceUtils.closeQuietly(bufferedInputStream);
                            ExifInterfaceUtils.closeQuietly(bufferedOutputStream);
                            createTempFile.delete();
                            this.mThumbnailBytes = null;
                        } catch (Exception e7) {
                            e = e7;
                            fileInputStream5 = r7;
                            fileInputStream6 = fileInputStream5;
                            fileInputStream4 = new FileInputStream(createTempFile);
                            Os.lseek(this.mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
                            fileOutputStream2 = new FileOutputStream(this.mSeekableFileDescriptor);
                            ExifInterfaceUtils.copy(fileInputStream4, fileOutputStream2);
                            ExifInterfaceUtils.closeQuietly(fileInputStream4);
                            ExifInterfaceUtils.closeQuietly(fileOutputStream2);
                            throw new IOException("Failed to save new file", e);
                        }
                    } catch (Exception e8) {
                        e = e8;
                        fileInputStream5 = r7;
                    } catch (Throwable th7) {
                        th = th7;
                        ExifInterfaceUtils.closeQuietly(bufferedInputStream);
                        ExifInterfaceUtils.closeQuietly(fileInputStream6);
                        if (0 == 0) {
                            createTempFile.delete();
                        }
                        throw th;
                    }
                } catch (Exception e9) {
                    e = e9;
                    fileInputStream2 = null;
                }
            } catch (Exception e10) {
                e = e10;
                fileInputStream6 = fileInputStream;
                try {
                    throw new IOException("Failed to copy original file to temp file", e);
                } catch (Throwable th8) {
                    th = th8;
                    fileInputStream = fileInputStream6;
                    ExifInterfaceUtils.closeQuietly(fileInputStream);
                    ExifInterfaceUtils.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th9) {
                th = th9;
                ExifInterfaceUtils.closeQuietly(fileInputStream);
                ExifInterfaceUtils.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Exception e11) {
            e = e11;
            fileOutputStream = null;
            fileInputStream6 = fileInputStream;
            throw new IOException("Failed to copy original file to temp file", e);
        } catch (Throwable th10) {
            th = th10;
            fileOutputStream = null;
            fileInputStream6 = fileInputStream;
            fileInputStream = fileInputStream6;
            ExifInterfaceUtils.closeQuietly(fileInputStream);
            ExifInterfaceUtils.closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public final void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "saveJpegAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        if (byteOrderedDataInputStream.readByte() != -1) {
            throw new IOException("Invalid marker");
        }
        byteOrderedDataOutputStream.writeByte(-1);
        if (byteOrderedDataInputStream.readByte() != -40) {
            throw new IOException("Invalid marker");
        }
        byteOrderedDataOutputStream.writeByte(-40);
        ExifAttribute exifAttribute = (getAttribute("Xmp") == null || !this.mXmpIsFromSeparateMarker) ? null : (ExifAttribute) this.mAttributes[0].remove("Xmp");
        byteOrderedDataOutputStream.writeByte(-1);
        byteOrderedDataOutputStream.writeByte(-31);
        writeExifSegment(byteOrderedDataOutputStream);
        byte[] bArr = IDENTIFIER_XMP_APP1;
        if (exifAttribute != null && this.mXmpIsFromSeparateMarker) {
            byteOrderedDataOutputStream.write(-1);
            byteOrderedDataOutputStream.writeByte(-31);
            int length = bArr.length + 2;
            byte[] bArr2 = exifAttribute.bytes;
            byteOrderedDataOutputStream.writeUnsignedShort(length + bArr2.length);
            byteOrderedDataOutputStream.write(bArr);
            byteOrderedDataOutputStream.write(bArr2);
        }
        if (exifAttribute != null) {
            this.mAttributes[0].put("Xmp", exifAttribute);
        }
        byte[] bArr3 = new byte[4096];
        while (byteOrderedDataInputStream.readByte() == -1) {
            byte readByte = byteOrderedDataInputStream.readByte();
            if (readByte == -39 || readByte == -38) {
                byteOrderedDataOutputStream.writeByte(-1);
                byteOrderedDataOutputStream.writeByte(readByte);
                ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream);
                return;
            }
            if (readByte != -31) {
                byteOrderedDataOutputStream.writeByte(-1);
                byteOrderedDataOutputStream.writeByte(readByte);
                int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
                byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort);
                int i = readUnsignedShort - 2;
                if (i < 0) {
                    throw new IOException("Invalid length");
                }
                while (i > 0) {
                    int read = byteOrderedDataInputStream.read(bArr3, 0, Math.min(i, 4096));
                    if (read >= 0) {
                        byteOrderedDataOutputStream.write(bArr3, 0, read);
                        i -= read;
                    }
                }
            } else {
                int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
                int i2 = readUnsignedShort2 - 2;
                if (i2 < 0) {
                    throw new IOException("Invalid length");
                }
                int length2 = bArr.length;
                byte[] bArr4 = IDENTIFIER_EXIF_APP1;
                byte[] bArr5 = i2 >= length2 ? new byte[bArr.length] : i2 >= bArr4.length ? new byte[bArr4.length] : null;
                if (bArr5 != null) {
                    byteOrderedDataInputStream.readFully(bArr5);
                    if (ExifInterfaceUtils.startsWith(bArr5, bArr4) || (ExifInterfaceUtils.startsWith(bArr5, bArr) && this.mXmpIsFromSeparateMarker)) {
                        byteOrderedDataInputStream.skipFully(i2 - bArr5.length);
                    }
                }
                byteOrderedDataOutputStream.writeByte(-1);
                byteOrderedDataOutputStream.writeByte(readByte);
                byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort2);
                if (bArr5 != null) {
                    i2 -= bArr5.length;
                    byteOrderedDataOutputStream.write(bArr5);
                }
                while (i2 > 0) {
                    int read2 = byteOrderedDataInputStream.read(bArr3, 0, Math.min(i2, 4096));
                    if (read2 >= 0) {
                        byteOrderedDataOutputStream.write(bArr3, 0, read2);
                        i2 -= read2;
                    }
                }
            }
        }
        throw new IOException("Invalid marker");
    }

    public final void savePngAttributes(InputStream inputStream, OutputStream outputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "savePngAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, byteOrder);
        ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream, PNG_SIGNATURE.length);
        if (this.mOffsetToExifData == 0) {
            int readInt = byteOrderedDataInputStream.readInt();
            byteOrderedDataOutputStream.writeInt(readInt);
            ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream, readInt + 8);
        } else {
            ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream, (r2 - r7.length) - 8);
            byteOrderedDataInputStream.skipFully(byteOrderedDataInputStream.readInt() + 8);
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                ByteOrderedDataOutputStream byteOrderedDataOutputStream2 = new ByteOrderedDataOutputStream(byteArrayOutputStream2, byteOrder);
                writeExifSegment(byteOrderedDataOutputStream2);
                byte[] byteArray = ((ByteArrayOutputStream) byteOrderedDataOutputStream2.mOutputStream).toByteArray();
                byteOrderedDataOutputStream.write(byteArray);
                CRC32 crc32 = new CRC32();
                crc32.update(byteArray, 4, byteArray.length - 4);
                byteOrderedDataOutputStream.writeInt((int) crc32.getValue());
                ExifInterfaceUtils.closeQuietly(byteArrayOutputStream2);
                ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream);
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream2;
                ExifInterfaceUtils.closeQuietly(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void saveWebpAttributes(InputStream inputStream, OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        int i2;
        boolean z;
        int i3;
        ByteOrderedDataOutputStream byteOrderedDataOutputStream;
        byte[] bArr;
        byte[] bArr2;
        boolean z2;
        if (DEBUG) {
            Log.d("ExifInterface", "saveWebpAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream, byteOrder);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream2 = new ByteOrderedDataOutputStream(outputStream, byteOrder);
        byte[] bArr3 = WEBP_SIGNATURE_1;
        ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream2, bArr3.length);
        byte[] bArr4 = WEBP_SIGNATURE_2;
        byteOrderedDataInputStream.skipFully(bArr4.length + 4);
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            ByteOrderedDataOutputStream byteOrderedDataOutputStream3 = new ByteOrderedDataOutputStream(byteArrayOutputStream, byteOrder);
            int i4 = this.mOffsetToExifData;
            if (i4 != 0) {
                ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3, (i4 - ((bArr3.length + 4) + bArr4.length)) - 8);
                byteOrderedDataInputStream.skipFully(4);
                int readInt = byteOrderedDataInputStream.readInt();
                if (readInt % 2 != 0) {
                    readInt++;
                }
                byteOrderedDataInputStream.skipFully(readInt);
                writeExifSegment(byteOrderedDataOutputStream3);
            } else {
                byte[] bArr5 = new byte[4];
                byteOrderedDataInputStream.readFully(bArr5);
                byte[] bArr6 = WEBP_CHUNK_TYPE_VP8X;
                boolean equals = Arrays.equals(bArr5, bArr6);
                byte[] bArr7 = WEBP_CHUNK_TYPE_VP8;
                byte[] bArr8 = WEBP_CHUNK_TYPE_VP8L;
                if (equals) {
                    int readInt2 = byteOrderedDataInputStream.readInt();
                    byte[] bArr9 = new byte[readInt2 % 2 == 1 ? readInt2 + 1 : readInt2];
                    byteOrderedDataInputStream.readFully(bArr9);
                    byte b = (byte) (8 | bArr9[0]);
                    bArr9[0] = b;
                    boolean z3 = ((b >> 1) & 1) == 1;
                    byteOrderedDataOutputStream3.write(bArr6);
                    byteOrderedDataOutputStream3.writeInt(readInt2);
                    byteOrderedDataOutputStream3.write(bArr9);
                    if (z3) {
                        byte[] bArr10 = WEBP_CHUNK_TYPE_ANIM;
                        do {
                            bArr2 = new byte[4];
                            byteOrderedDataInputStream.readFully(bArr2);
                            int readInt3 = byteOrderedDataInputStream.readInt();
                            byteOrderedDataOutputStream3.write(bArr2);
                            byteOrderedDataOutputStream3.writeInt(readInt3);
                            if (readInt3 % 2 == 1) {
                                readInt3++;
                            }
                            ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3, readInt3);
                        } while (!Arrays.equals(bArr2, bArr10));
                        while (true) {
                            byte[] bArr11 = new byte[4];
                            try {
                                byteOrderedDataInputStream.readFully(bArr11);
                                z2 = !Arrays.equals(bArr11, WEBP_CHUNK_TYPE_ANMF);
                            } catch (EOFException unused) {
                                z2 = true;
                            }
                            if (z2) {
                                break;
                            }
                            int readInt4 = byteOrderedDataInputStream.readInt();
                            byteOrderedDataOutputStream3.write(bArr11);
                            byteOrderedDataOutputStream3.writeInt(readInt4);
                            if (readInt4 % 2 == 1) {
                                readInt4++;
                            }
                            ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3, readInt4);
                        }
                        writeExifSegment(byteOrderedDataOutputStream3);
                    } else {
                        while (true) {
                            byte[] bArr12 = new byte[4];
                            byteOrderedDataInputStream.readFully(bArr12);
                            int readInt5 = byteOrderedDataInputStream.readInt();
                            byteOrderedDataOutputStream3.write(bArr12);
                            byteOrderedDataOutputStream3.writeInt(readInt5);
                            if (readInt5 % 2 == 1) {
                                readInt5++;
                            }
                            ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3, readInt5);
                            if (Arrays.equals(bArr12, bArr7) || (bArr8 != null && Arrays.equals(bArr12, bArr8))) {
                                break;
                            }
                        }
                        writeExifSegment(byteOrderedDataOutputStream3);
                    }
                } else if (Arrays.equals(bArr5, bArr7) || Arrays.equals(bArr5, bArr8)) {
                    int readInt6 = byteOrderedDataInputStream.readInt();
                    int i5 = readInt6 % 2 == 1 ? readInt6 + 1 : readInt6;
                    byte[] bArr13 = new byte[3];
                    boolean equals2 = Arrays.equals(bArr5, bArr7);
                    byte[] bArr14 = WEBP_VP8_SIGNATURE;
                    if (equals2) {
                        byteOrderedDataInputStream.readFully(bArr13);
                        byte[] bArr15 = new byte[3];
                        byteOrderedDataInputStream.readFully(bArr15);
                        if (!Arrays.equals(bArr14, bArr15)) {
                            throw new IOException("Error checking VP8 signature");
                        }
                        i = byteOrderedDataInputStream.readInt();
                        i5 -= 10;
                        i2 = (i << 18) >> 18;
                        i3 = (i << 2) >> 18;
                        z = false;
                    } else if (!Arrays.equals(bArr5, bArr8)) {
                        i = 0;
                        i2 = 0;
                        z = false;
                        i3 = 0;
                    } else {
                        if (byteOrderedDataInputStream.readByte() != 47) {
                            throw new IOException("Error checking VP8L signature");
                        }
                        i = byteOrderedDataInputStream.readInt();
                        z = true;
                        i2 = (i & 16383) + 1;
                        i3 = ((i & 268419072) >>> 14) + 1;
                        if ((i & 268435456) == 0) {
                            z = false;
                        }
                        i5 -= 5;
                    }
                    byteOrderedDataOutputStream3.write(bArr6);
                    byteOrderedDataOutputStream3.writeInt(10);
                    byte[] bArr16 = new byte[10];
                    if (z) {
                        byteOrderedDataOutputStream = byteOrderedDataOutputStream2;
                        bArr16[0] = (byte) (bArr16[0] | 16);
                    } else {
                        byteOrderedDataOutputStream = byteOrderedDataOutputStream2;
                    }
                    bArr = bArr4;
                    bArr16[0] = (byte) (bArr16[0] | 8);
                    int i6 = i2 - 1;
                    int i7 = i3 - 1;
                    bArr16[4] = (byte) i6;
                    bArr16[5] = (byte) (i6 >> 8);
                    bArr16[6] = (byte) (i6 >> 16);
                    bArr16[7] = (byte) i7;
                    bArr16[8] = (byte) (i7 >> 8);
                    bArr16[9] = (byte) (i7 >> 16);
                    byteOrderedDataOutputStream3.write(bArr16);
                    byteOrderedDataOutputStream3.write(bArr5);
                    byteOrderedDataOutputStream3.writeInt(readInt6);
                    if (Arrays.equals(bArr5, bArr7)) {
                        byteOrderedDataOutputStream3.write(bArr13);
                        byteOrderedDataOutputStream3.write(bArr14);
                        byteOrderedDataOutputStream3.writeInt(i);
                    } else if (Arrays.equals(bArr5, bArr8)) {
                        byteOrderedDataOutputStream3.write(47);
                        byteOrderedDataOutputStream3.writeInt(i);
                    }
                    ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3, i5);
                    writeExifSegment(byteOrderedDataOutputStream3);
                    ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3);
                    byte[] bArr17 = bArr;
                    ByteOrderedDataOutputStream byteOrderedDataOutputStream4 = byteOrderedDataOutputStream;
                    byteOrderedDataOutputStream4.writeInt(byteArrayOutputStream.size() + bArr17.length);
                    byteOrderedDataOutputStream4.write(bArr17);
                    byteArrayOutputStream.writeTo(byteOrderedDataOutputStream4);
                    ExifInterfaceUtils.closeQuietly(byteArrayOutputStream);
                }
            }
            byteOrderedDataOutputStream = byteOrderedDataOutputStream2;
            bArr = bArr4;
            ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream3);
            byte[] bArr172 = bArr;
            ByteOrderedDataOutputStream byteOrderedDataOutputStream42 = byteOrderedDataOutputStream;
            byteOrderedDataOutputStream42.writeInt(byteArrayOutputStream.size() + bArr172.length);
            byteOrderedDataOutputStream42.write(bArr172);
            byteArrayOutputStream.writeTo(byteOrderedDataOutputStream42);
            ExifInterfaceUtils.closeQuietly(byteArrayOutputStream);
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            throw new IOException("Failed to save WebP file", e);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            ExifInterfaceUtils.closeQuietly(byteArrayOutputStream2);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setAttribute(java.lang.String r35, java.lang.String r36) {
        /*
            Method dump skipped, instructions count: 1144
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.setAttribute(java.lang.String, java.lang.String):void");
    }

    public final void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        String str;
        ExifAttribute exifAttribute;
        int intValue;
        HashMap hashMap = this.mAttributes[4];
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("Compression");
        if (exifAttribute2 == null) {
            this.mThumbnailCompression = 6;
            handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
            return;
        }
        int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
        this.mThumbnailCompression = intValue2;
        int i = 1;
        if (intValue2 != 1) {
            if (intValue2 == 6) {
                handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                return;
            } else if (intValue2 != 7) {
                return;
            }
        }
        ExifAttribute exifAttribute3 = (ExifAttribute) hashMap.get("BitsPerSample");
        String str2 = "ExifInterface";
        if (exifAttribute3 != null) {
            int[] iArr = (int[]) exifAttribute3.getValue(this.mExifByteOrder);
            int[] iArr2 = BITS_PER_SAMPLE_RGB;
            if (Arrays.equals(iArr2, iArr) || (this.mMimeType == 3 && (exifAttribute = (ExifAttribute) hashMap.get("PhotometricInterpretation")) != null && (((intValue = exifAttribute.getIntValue(this.mExifByteOrder)) == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, iArr2))))) {
                ExifAttribute exifAttribute4 = (ExifAttribute) hashMap.get("StripOffsets");
                ExifAttribute exifAttribute5 = (ExifAttribute) hashMap.get("StripByteCounts");
                if (exifAttribute4 == null || exifAttribute5 == null) {
                    return;
                }
                long[] convertToLongArray = ExifInterfaceUtils.convertToLongArray(exifAttribute4.getValue(this.mExifByteOrder));
                long[] convertToLongArray2 = ExifInterfaceUtils.convertToLongArray(exifAttribute5.getValue(this.mExifByteOrder));
                if (convertToLongArray == null || convertToLongArray.length == 0) {
                    Log.w("ExifInterface", "stripOffsets should not be null or have zero length.");
                    return;
                }
                if (convertToLongArray2 == null || convertToLongArray2.length == 0) {
                    Log.w("ExifInterface", "stripByteCounts should not be null or have zero length.");
                    return;
                }
                if (convertToLongArray.length != convertToLongArray2.length) {
                    Log.w("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
                    return;
                }
                long j = 0;
                for (long j2 : convertToLongArray2) {
                    j += j2;
                }
                int i2 = (int) j;
                byte[] bArr = new byte[i2];
                this.mAreThumbnailStripsConsecutive = true;
                this.mHasThumbnailStrips = true;
                this.mHasThumbnail = true;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (i3 < convertToLongArray.length) {
                    int i6 = (int) convertToLongArray[i3];
                    int i7 = (int) convertToLongArray2[i3];
                    if (i3 < convertToLongArray.length - i) {
                        str = str2;
                        if (i6 + i7 != convertToLongArray[i3 + 1]) {
                            this.mAreThumbnailStripsConsecutive = false;
                        }
                    } else {
                        str = str2;
                    }
                    int i8 = i6 - i4;
                    if (i8 < 0) {
                        Log.d(str, "Invalid strip offset value");
                        return;
                    }
                    String str3 = str;
                    try {
                        byteOrderedDataInputStream.skipFully(i8);
                        int i9 = i4 + i8;
                        byte[] bArr2 = new byte[i7];
                        try {
                            byteOrderedDataInputStream.readFully(bArr2);
                            i4 = i9 + i7;
                            System.arraycopy(bArr2, 0, bArr, i5, i7);
                            i5 += i7;
                            i3++;
                            str2 = str3;
                            i = 1;
                        } catch (EOFException unused) {
                            Log.d(str3, "Failed to read " + i7 + " bytes.");
                            return;
                        }
                    } catch (EOFException unused2) {
                        Log.d(str3, "Failed to skip " + i8 + " bytes.");
                        return;
                    }
                }
                this.mThumbnailBytes = bArr;
                if (this.mAreThumbnailStripsConsecutive) {
                    this.mThumbnailOffset = (int) convertToLongArray[0];
                    this.mThumbnailLength = i2;
                    return;
                }
                return;
            }
        }
        if (DEBUG) {
            Log.d("ExifInterface", "Unsupported data type value");
        }
    }

    public final void swapBasedOnImageSize(int i, int i2) {
        boolean isEmpty = this.mAttributes[i].isEmpty();
        boolean z = DEBUG;
        if (isEmpty || this.mAttributes[i2].isEmpty()) {
            if (z) {
                Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
                return;
            }
            return;
        }
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get("ImageLength");
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get("ImageWidth");
        ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i2].get("ImageLength");
        ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i2].get("ImageWidth");
        if (exifAttribute == null || exifAttribute2 == null) {
            if (z) {
                Log.d("ExifInterface", "First image does not contain valid size information");
                return;
            }
            return;
        }
        if (exifAttribute3 == null || exifAttribute4 == null) {
            if (z) {
                Log.d("ExifInterface", "Second image does not contain valid size information");
                return;
            }
            return;
        }
        int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
        int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
        int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
        int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
        if (intValue >= intValue3 || intValue2 >= intValue4) {
            return;
        }
        HashMap[] hashMapArr = this.mAttributes;
        HashMap hashMap = hashMapArr[i];
        hashMapArr[i] = hashMapArr[i2];
        hashMapArr[i2] = hashMap;
    }

    public final void updateImageSizeValues(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream, int i) {
        ExifAttribute createUShort;
        ExifAttribute createUShort2;
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get("DefaultCropSize");
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get("SensorTopBorder");
        ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i].get("SensorLeftBorder");
        ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i].get("SensorBottomBorder");
        ExifAttribute exifAttribute5 = (ExifAttribute) this.mAttributes[i].get("SensorRightBorder");
        if (exifAttribute != null) {
            if (exifAttribute.format == 5) {
                Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                if (rationalArr == null || rationalArr.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(rationalArr));
                    return;
                } else {
                    createUShort = ExifAttribute.createURational(new Rational[]{rationalArr[0]}, this.mExifByteOrder);
                    createUShort2 = ExifAttribute.createURational(new Rational[]{rationalArr[1]}, this.mExifByteOrder);
                }
            } else {
                int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                }
                createUShort = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
                createUShort2 = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
            }
            this.mAttributes[i].put("ImageWidth", createUShort);
            this.mAttributes[i].put("ImageLength", createUShort2);
            return;
        }
        if (exifAttribute2 != null && exifAttribute3 != null && exifAttribute4 != null && exifAttribute5 != null) {
            int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute4.getIntValue(this.mExifByteOrder);
            int intValue3 = exifAttribute5.getIntValue(this.mExifByteOrder);
            int intValue4 = exifAttribute3.getIntValue(this.mExifByteOrder);
            if (intValue2 <= intValue || intValue3 <= intValue4) {
                return;
            }
            ExifAttribute createUShort3 = ExifAttribute.createUShort(intValue2 - intValue, this.mExifByteOrder);
            ExifAttribute createUShort4 = ExifAttribute.createUShort(intValue3 - intValue4, this.mExifByteOrder);
            this.mAttributes[i].put("ImageLength", createUShort3);
            this.mAttributes[i].put("ImageWidth", createUShort4);
            return;
        }
        ExifAttribute exifAttribute6 = (ExifAttribute) this.mAttributes[i].get("ImageLength");
        ExifAttribute exifAttribute7 = (ExifAttribute) this.mAttributes[i].get("ImageWidth");
        if (exifAttribute6 == null || exifAttribute7 == null) {
            ExifAttribute exifAttribute8 = (ExifAttribute) this.mAttributes[i].get("JPEGInterchangeFormat");
            ExifAttribute exifAttribute9 = (ExifAttribute) this.mAttributes[i].get("JPEGInterchangeFormatLength");
            if (exifAttribute8 == null || exifAttribute9 == null) {
                return;
            }
            int intValue5 = exifAttribute8.getIntValue(this.mExifByteOrder);
            int intValue6 = exifAttribute8.getIntValue(this.mExifByteOrder);
            seekableByteOrderedDataInputStream.seek(intValue5);
            byte[] bArr = new byte[intValue6];
            seekableByteOrderedDataInputStream.readFully(bArr);
            getJpegAttributes(new ByteOrderedDataInputStream(bArr), intValue5, i);
        }
    }

    public final void validateImages() {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get("PixelXDimension");
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get("PixelYDimension");
        if (exifAttribute != null && exifAttribute2 != null) {
            this.mAttributes[0].put("ImageWidth", exifAttribute);
            this.mAttributes[0].put("ImageLength", exifAttribute2);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            HashMap[] hashMapArr = this.mAttributes;
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
        replaceInvalidTags("ThumbnailOrientation", "Orientation", 0);
        replaceInvalidTags("ThumbnailImageLength", "ImageLength", 0);
        replaceInvalidTags("ThumbnailImageWidth", "ImageWidth", 0);
        replaceInvalidTags("ThumbnailOrientation", "Orientation", 5);
        replaceInvalidTags("ThumbnailImageLength", "ImageLength", 5);
        replaceInvalidTags("ThumbnailImageWidth", "ImageWidth", 5);
        replaceInvalidTags("Orientation", "ThumbnailOrientation", 4);
        replaceInvalidTags("ImageLength", "ThumbnailImageLength", 4);
        replaceInvalidTags("ImageWidth", "ThumbnailImageWidth", 4);
    }

    public final void writeExifSegment(ByteOrderedDataOutputStream byteOrderedDataOutputStream) {
        int[] iArr;
        ExifTag[][] exifTagArr = EXIF_TAGS;
        int[] iArr2 = new int[exifTagArr.length];
        int[] iArr3 = new int[exifTagArr.length];
        ExifTag[] exifTagArr2 = EXIF_POINTER_TAGS;
        for (ExifTag exifTag : exifTagArr2) {
            removeAttribute(exifTag.name);
        }
        if (this.mHasThumbnail) {
            if (this.mHasThumbnailStrips) {
                removeAttribute("StripOffsets");
                removeAttribute("StripByteCounts");
            } else {
                removeAttribute("JPEGInterchangeFormat");
                removeAttribute("JPEGInterchangeFormatLength");
            }
        }
        for (int i = 0; i < exifTagArr.length; i++) {
            Iterator it = this.mAttributes[i].entrySet().iterator();
            while (it.hasNext()) {
                if (((Map.Entry) it.next()).getValue() == null) {
                    it.remove();
                }
            }
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(exifTagArr2[1].name, ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(exifTagArr2[2].name, ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(exifTagArr2[3].name, ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (this.mHasThumbnail) {
            if (this.mHasThumbnailStrips) {
                this.mAttributes[4].put("StripOffsets", ExifAttribute.createUShort(0, this.mExifByteOrder));
                this.mAttributes[4].put("StripByteCounts", ExifAttribute.createUShort(this.mThumbnailLength, this.mExifByteOrder));
            } else {
                this.mAttributes[4].put("JPEGInterchangeFormat", ExifAttribute.createULong(0L, this.mExifByteOrder));
                this.mAttributes[4].put("JPEGInterchangeFormatLength", ExifAttribute.createULong(this.mThumbnailLength, this.mExifByteOrder));
            }
        }
        int i2 = 0;
        while (true) {
            int length = exifTagArr.length;
            iArr = IFD_FORMAT_BYTES_PER_FORMAT;
            if (i2 >= length) {
                break;
            }
            Iterator it2 = this.mAttributes[i2].entrySet().iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                ExifAttribute exifAttribute = (ExifAttribute) ((Map.Entry) it2.next()).getValue();
                exifAttribute.getClass();
                int i4 = iArr[exifAttribute.format] * exifAttribute.numberOfComponents;
                if (i4 > 4) {
                    i3 += i4;
                }
            }
            iArr3[i2] = iArr3[i2] + i3;
            i2++;
        }
        int i5 = 8;
        for (int i6 = 0; i6 < exifTagArr.length; i6++) {
            if (!this.mAttributes[i6].isEmpty()) {
                iArr2[i6] = i5;
                i5 = (this.mAttributes[i6].size() * 12) + 6 + iArr3[i6] + i5;
            }
        }
        if (this.mHasThumbnail) {
            if (this.mHasThumbnailStrips) {
                this.mAttributes[4].put("StripOffsets", ExifAttribute.createUShort(i5, this.mExifByteOrder));
            } else {
                this.mAttributes[4].put("JPEGInterchangeFormat", ExifAttribute.createULong(i5, this.mExifByteOrder));
            }
            this.mThumbnailOffset = i5;
            i5 += this.mThumbnailLength;
        }
        if (this.mMimeType == 4) {
            i5 += 8;
        }
        if (DEBUG) {
            for (int i7 = 0; i7 < exifTagArr.length; i7++) {
                Log.d("ExifInterface", String.format("index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d", Integer.valueOf(i7), Integer.valueOf(iArr2[i7]), Integer.valueOf(this.mAttributes[i7].size()), Integer.valueOf(iArr3[i7]), Integer.valueOf(i5)));
            }
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(exifTagArr2[1].name, ExifAttribute.createULong(iArr2[1], this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(exifTagArr2[2].name, ExifAttribute.createULong(iArr2[2], this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(exifTagArr2[3].name, ExifAttribute.createULong(iArr2[3], this.mExifByteOrder));
        }
        int i8 = this.mMimeType;
        if (i8 == 4) {
            if (i5 > 65535) {
                throw new IllegalStateException(GenericDocument$$ExternalSyntheticOutline0.m("Size of exif data (", " bytes) exceeds the max size of a JPEG APP1 segment (65536 bytes)", i5));
            }
            byteOrderedDataOutputStream.writeUnsignedShort(i5);
            byteOrderedDataOutputStream.write(IDENTIFIER_EXIF_APP1);
        } else if (i8 == 13) {
            byteOrderedDataOutputStream.writeInt(i5);
            byteOrderedDataOutputStream.writeInt(PNG_CHUNK_TYPE_EXIF);
        } else if (i8 == 14) {
            byteOrderedDataOutputStream.write(WEBP_CHUNK_TYPE_EXIF);
            byteOrderedDataOutputStream.writeInt(i5);
        }
        byteOrderedDataOutputStream.writeShort(this.mExifByteOrder == ByteOrder.BIG_ENDIAN ? (short) 19789 : (short) 18761);
        byteOrderedDataOutputStream.mByteOrder = this.mExifByteOrder;
        byteOrderedDataOutputStream.writeUnsignedShort(42);
        byteOrderedDataOutputStream.writeUnsignedInt(8L);
        for (int i9 = 0; i9 < exifTagArr.length; i9++) {
            if (!this.mAttributes[i9].isEmpty()) {
                byteOrderedDataOutputStream.writeUnsignedShort(this.mAttributes[i9].size());
                int size = (this.mAttributes[i9].size() * 12) + iArr2[i9] + 2 + 4;
                for (Map.Entry entry : this.mAttributes[i9].entrySet()) {
                    int i10 = ((ExifTag) sExifTagMapsForWriting[i9].get(entry.getKey())).number;
                    ExifAttribute exifAttribute2 = (ExifAttribute) entry.getValue();
                    exifAttribute2.getClass();
                    int i11 = exifAttribute2.format;
                    int i12 = iArr[i11];
                    int i13 = exifAttribute2.numberOfComponents;
                    int i14 = i12 * i13;
                    byteOrderedDataOutputStream.writeUnsignedShort(i10);
                    byteOrderedDataOutputStream.writeUnsignedShort(i11);
                    byteOrderedDataOutputStream.writeInt(i13);
                    if (i14 > 4) {
                        byteOrderedDataOutputStream.writeUnsignedInt(size);
                        size += i14;
                    } else {
                        byteOrderedDataOutputStream.write(exifAttribute2.bytes);
                        if (i14 < 4) {
                            while (i14 < 4) {
                                byteOrderedDataOutputStream.writeByte(0);
                                i14++;
                            }
                        }
                    }
                }
                if (i9 != 0 || this.mAttributes[4].isEmpty()) {
                    byteOrderedDataOutputStream.writeUnsignedInt(0L);
                } else {
                    byteOrderedDataOutputStream.writeUnsignedInt(iArr2[4]);
                }
                Iterator it3 = this.mAttributes[i9].entrySet().iterator();
                while (it3.hasNext()) {
                    byte[] bArr = ((ExifAttribute) ((Map.Entry) it3.next()).getValue()).bytes;
                    if (bArr.length > 4) {
                        byteOrderedDataOutputStream.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        if (this.mHasThumbnail) {
            byteOrderedDataOutputStream.write(getThumbnailBytes());
        }
        if (this.mMimeType == 14 && i5 % 2 == 1) {
            byteOrderedDataOutputStream.writeByte(0);
        }
        byteOrderedDataOutputStream.mByteOrder = ByteOrder.BIG_ENDIAN;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class ByteOrderedDataInputStream extends InputStream implements DataInput {
        public ByteOrder mByteOrder;
        public final DataInputStream mDataInputStream;
        public final int mLength;
        public int mPosition;
        public byte[] mSkipBuffer;

        public ByteOrderedDataInputStream(byte[] bArr) {
            this(new ByteArrayInputStream(bArr), ByteOrder.BIG_ENDIAN);
            this.mLength = bArr.length;
        }

        @Override // java.io.InputStream
        public final int available() {
            return this.mDataInputStream.available();
        }

        @Override // java.io.InputStream
        public final void mark(int i) {
            throw new UnsupportedOperationException("Mark is currently unsupported");
        }

        @Override // java.io.InputStream
        public final int read() {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        @Override // java.io.DataInput
        public final boolean readBoolean() {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        @Override // java.io.DataInput
        public final byte readByte() {
            this.mPosition++;
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public final char readChar() {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        @Override // java.io.DataInput
        public final double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public final float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public final void readFully(byte[] bArr, int i, int i2) {
            this.mPosition += i2;
            this.mDataInputStream.readFully(bArr, i, i2);
        }

        @Override // java.io.DataInput
        public final int readInt() {
            this.mPosition += 4;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
            }
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public final String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public final long readLong() {
            this.mPosition += 8;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                return (read8 << 56) + (read7 << 48) + (read6 << 40) + (read5 << 32) + (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
            }
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return (read << 56) + (read2 << 48) + (read3 << 40) + (read4 << 32) + (read5 << 24) + (read6 << 16) + (read7 << 8) + read8;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public final short readShort() {
            int i;
            this.mPosition += 2;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                i = (read2 << 8) + read;
            } else {
                if (byteOrder != ByteOrder.BIG_ENDIAN) {
                    throw new IOException("Invalid byte order: " + this.mByteOrder);
                }
                i = (read << 8) + read2;
            }
            return (short) i;
        }

        @Override // java.io.DataInput
        public final String readUTF() {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        @Override // java.io.DataInput
        public final int readUnsignedByte() {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public final int readUnsignedShort() {
            this.mPosition += 2;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                return (read2 << 8) + read;
            }
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return (read << 8) + read2;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.InputStream
        public final void reset() {
            throw new UnsupportedOperationException("Reset is currently unsupported");
        }

        @Override // java.io.DataInput
        public final int skipBytes(int i) {
            throw new UnsupportedOperationException("skipBytes is currently unsupported");
        }

        public final void skipFully(int i) {
            int i2 = 0;
            while (i2 < i) {
                int i3 = i - i2;
                int skip = (int) this.mDataInputStream.skip(i3);
                if (skip <= 0) {
                    if (this.mSkipBuffer == null) {
                        this.mSkipBuffer = new byte[8192];
                    }
                    skip = this.mDataInputStream.read(this.mSkipBuffer, 0, Math.min(8192, i3));
                    if (skip == -1) {
                        throw new EOFException(GenericDocument$$ExternalSyntheticOutline0.m("Reached EOF while skipping ", " bytes.", i));
                    }
                }
                i2 += skip;
            }
            this.mPosition += i2;
        }

        public ByteOrderedDataInputStream(InputStream inputStream) {
            this(inputStream, ByteOrder.BIG_ENDIAN);
        }

        @Override // java.io.InputStream
        public final int read(byte[] bArr, int i, int i2) {
            int read = this.mDataInputStream.read(bArr, i, i2);
            this.mPosition += read;
            return read;
        }

        @Override // java.io.DataInput
        public final void readFully(byte[] bArr) {
            this.mPosition += bArr.length;
            this.mDataInputStream.readFully(bArr);
        }

        public ByteOrderedDataInputStream(InputStream inputStream, ByteOrder byteOrder) {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.mDataInputStream = dataInputStream;
            dataInputStream.mark(0);
            this.mPosition = 0;
            this.mByteOrder = byteOrder;
            this.mLength = inputStream instanceof ByteOrderedDataInputStream ? ((ByteOrderedDataInputStream) inputStream).mLength : -1;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SeekableByteOrderedDataInputStream extends ByteOrderedDataInputStream {
        public SeekableByteOrderedDataInputStream(byte[] bArr) {
            super(bArr);
            this.mDataInputStream.mark(Integer.MAX_VALUE);
        }

        public final void seek(long j) {
            int i = this.mPosition;
            if (i > j) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
            } else {
                j -= i;
            }
            skipFully((int) j);
        }

        public SeekableByteOrderedDataInputStream(InputStream inputStream) {
            super(inputStream);
            if (inputStream.markSupported()) {
                this.mDataInputStream.mark(Integer.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        public ExifTag(String str, int i, int i2) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = -1;
        }

        public ExifTag(String str, int i, int i2, int i3) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = i3;
        }
    }
}
