package androidx.datastore.preferences.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class UnsafeUtil {
    public static final long BYTE_ARRAY_BASE_OFFSET;
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS;
    public static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    public static final boolean IS_BIG_ENDIAN;
    public static final JvmMemoryAccessor MEMORY_ACCESSOR;
    public static final Class MEMORY_CLASS;
    public static final Unsafe UNSAFE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.datastore.preferences.protobuf.UnsafeUtil$1, reason: invalid class name */
    public final class AnonymousClass1 implements PrivilegedExceptionAction {
        @Override // java.security.PrivilegedExceptionAction
        public final Object run() {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            return null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class JvmMemoryAccessor {
        public final /* synthetic */ int $r8$classId;
        public final Unsafe unsafe;

        public JvmMemoryAccessor(Unsafe unsafe, int i) {
            this.$r8$classId = i;
            this.unsafe = unsafe;
        }

        public final boolean getBoolean(long j, Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    break;
                case 1:
                    if (UnsafeUtil.IS_BIG_ENDIAN) {
                        if (UnsafeUtil.getByteBigEndian(j, obj) == 0) {
                        }
                    } else if (UnsafeUtil.getByteLittleEndian(j, obj) == 0) {
                    }
                    break;
                default:
                    if (UnsafeUtil.IS_BIG_ENDIAN) {
                        if (UnsafeUtil.getByteBigEndian(j, obj) == 0) {
                        }
                    } else if (UnsafeUtil.getByteLittleEndian(j, obj) == 0) {
                    }
                    break;
            }
            return false;
        }

        public final double getDouble(long j, Object obj) {
            switch (this.$r8$classId) {
            }
            return Double.longBitsToDouble(this.unsafe.getLong(obj, j));
        }

        public final float getFloat(long j, Object obj) {
            switch (this.$r8$classId) {
            }
            return Float.intBitsToFloat(this.unsafe.getInt(obj, j));
        }

        public final void putBoolean(Object obj, long j, boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    this.unsafe.putBoolean(obj, j, z);
                    break;
                case 1:
                    if (!UnsafeUtil.IS_BIG_ENDIAN) {
                        UnsafeUtil.putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
                        break;
                    } else {
                        UnsafeUtil.putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
                        break;
                    }
                default:
                    if (!UnsafeUtil.IS_BIG_ENDIAN) {
                        UnsafeUtil.putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
                        break;
                    } else {
                        UnsafeUtil.putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
                        break;
                    }
            }
        }

        public final void putDouble(Object obj, long j, double d) {
            switch (this.$r8$classId) {
                case 0:
                    this.unsafe.putDouble(obj, j, d);
                    break;
                case 1:
                    this.unsafe.putLong(obj, j, Double.doubleToLongBits(d));
                    break;
                default:
                    this.unsafe.putLong(obj, j, Double.doubleToLongBits(d));
                    break;
            }
        }

        public final void putFloat(Object obj, long j, float f) {
            switch (this.$r8$classId) {
                case 0:
                    this.unsafe.putFloat(obj, j, f);
                    break;
                case 1:
                    this.unsafe.putInt(obj, j, Float.floatToIntBits(f));
                    break;
                default:
                    this.unsafe.putInt(obj, j, Float.floatToIntBits(f));
                    break;
            }
        }

        public final boolean supportsUnsafeArrayOperations$androidx$datastore$preferences$protobuf$UnsafeUtil$MemoryAccessor() {
            Unsafe unsafe = this.unsafe;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                Class<?> cls2 = Long.TYPE;
                cls.getMethod("getInt", Object.class, cls2);
                cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
                cls.getMethod("getLong", Object.class, cls2);
                cls.getMethod("putLong", Object.class, cls2, cls2);
                cls.getMethod("getObject", Object.class, cls2);
                cls.getMethod("putObject", Object.class, cls2, Object.class);
                return true;
            } catch (Throwable th) {
                UnsafeUtil.access$000(th);
                return false;
            }
        }
    }

    static {
        Unsafe unsafe;
        boolean z;
        boolean z2;
        JvmMemoryAccessor jvmMemoryAccessor = null;
        try {
            unsafe = (Unsafe) AccessController.doPrivileged(new AnonymousClass1());
        } catch (Throwable unused) {
            unsafe = null;
        }
        UNSAFE = unsafe;
        MEMORY_CLASS = Android.MEMORY_CLASS;
        boolean determineAndroidSupportByAddressSize = determineAndroidSupportByAddressSize(Long.TYPE);
        boolean determineAndroidSupportByAddressSize2 = determineAndroidSupportByAddressSize(Integer.TYPE);
        if (unsafe != null) {
            if (!Android.isOnAndroidDevice()) {
                jvmMemoryAccessor = new JvmMemoryAccessor(unsafe, 0);
            } else if (determineAndroidSupportByAddressSize) {
                jvmMemoryAccessor = new JvmMemoryAccessor(unsafe, 2);
            } else if (determineAndroidSupportByAddressSize2) {
                jvmMemoryAccessor = new JvmMemoryAccessor(unsafe, 1);
            }
        }
        MEMORY_ACCESSOR = jvmMemoryAccessor;
        if (jvmMemoryAccessor != null) {
            switch (jvmMemoryAccessor.$r8$classId) {
                case 0:
                    Unsafe unsafe2 = jvmMemoryAccessor.unsafe;
                    if (unsafe2 != null) {
                        try {
                            Class<?> cls = unsafe2.getClass();
                            cls.getMethod("objectFieldOffset", Field.class);
                            Class<?> cls2 = Long.TYPE;
                            cls.getMethod("getLong", Object.class, cls2);
                            if (bufferAddressField() != null) {
                                try {
                                    Class<?> cls3 = jvmMemoryAccessor.unsafe.getClass();
                                    cls3.getMethod("getByte", cls2);
                                    cls3.getMethod("putByte", cls2, Byte.TYPE);
                                    cls3.getMethod("getInt", cls2);
                                    cls3.getMethod("putInt", cls2, Integer.TYPE);
                                    cls3.getMethod("getLong", cls2);
                                    cls3.getMethod("putLong", cls2, cls2);
                                    cls3.getMethod("copyMemory", cls2, cls2, cls2);
                                    cls3.getMethod("copyMemory", Object.class, cls2, Object.class, cls2, cls2);
                                    z = true;
                                    break;
                                } catch (Throwable th) {
                                    access$000(th);
                                }
                            }
                        } catch (Throwable th2) {
                            access$000(th2);
                        }
                    }
                    z = false;
                    break;
                case 1:
                default:
                    z = false;
                    break;
            }
        } else {
            z = false;
        }
        HAS_UNSAFE_BYTEBUFFER_OPERATIONS = z;
        if (jvmMemoryAccessor != null) {
            switch (jvmMemoryAccessor.$r8$classId) {
                case 0:
                    if (jvmMemoryAccessor.supportsUnsafeArrayOperations$androidx$datastore$preferences$protobuf$UnsafeUtil$MemoryAccessor()) {
                        try {
                            Class<?> cls4 = jvmMemoryAccessor.unsafe.getClass();
                            Class<?> cls5 = Long.TYPE;
                            cls4.getMethod("getByte", Object.class, cls5);
                            cls4.getMethod("putByte", Object.class, cls5, Byte.TYPE);
                            cls4.getMethod("getBoolean", Object.class, cls5);
                            cls4.getMethod("putBoolean", Object.class, cls5, Boolean.TYPE);
                            cls4.getMethod("getFloat", Object.class, cls5);
                            cls4.getMethod("putFloat", Object.class, cls5, Float.TYPE);
                            cls4.getMethod("getDouble", Object.class, cls5);
                            cls4.getMethod("putDouble", Object.class, cls5, Double.TYPE);
                            z2 = true;
                            break;
                        } catch (Throwable th3) {
                            access$000(th3);
                        }
                    }
                    z2 = false;
                    break;
                default:
                    z2 = jvmMemoryAccessor.supportsUnsafeArrayOperations$androidx$datastore$preferences$protobuf$UnsafeUtil$MemoryAccessor();
                    break;
            }
        } else {
            z2 = false;
        }
        HAS_UNSAFE_ARRAY_OPERATIONS = z2;
        BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset(byte[].class);
        arrayBaseOffset(boolean[].class);
        arrayIndexScale(boolean[].class);
        arrayBaseOffset(int[].class);
        arrayIndexScale(int[].class);
        arrayBaseOffset(long[].class);
        arrayIndexScale(long[].class);
        arrayBaseOffset(float[].class);
        arrayIndexScale(float[].class);
        arrayBaseOffset(double[].class);
        arrayIndexScale(double[].class);
        arrayBaseOffset(Object[].class);
        arrayIndexScale(Object[].class);
        Field bufferAddressField = bufferAddressField();
        if (bufferAddressField != null && jvmMemoryAccessor != null) {
            jvmMemoryAccessor.unsafe.objectFieldOffset(bufferAddressField);
        }
        IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static void access$000(Throwable th) {
        Logger.getLogger(UnsafeUtil.class.getName()).log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static Object allocateInstance(Class cls) {
        try {
            return UNSAFE.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    public static int arrayBaseOffset(Class cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.unsafe.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static void arrayIndexScale(Class cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale(cls);
        }
    }

    public static Field bufferAddressField() {
        Field field;
        Field field2;
        if (Android.isOnAndroidDevice()) {
            try {
                field2 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            } catch (Throwable unused) {
                field2 = null;
            }
            if (field2 != null) {
                return field2;
            }
        }
        try {
            field = Buffer.class.getDeclaredField("address");
        } catch (Throwable unused2) {
            field = null;
        }
        if (field == null || field.getType() != Long.TYPE) {
            return null;
        }
        return field;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean determineAndroidSupportByAddressSize(Class cls) {
        if (!Android.isOnAndroidDevice()) {
            return false;
        }
        try {
            Class cls2 = MEMORY_CLASS;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static byte getByte(byte[] bArr, long j) {
        long j2 = BYTE_ARRAY_BASE_OFFSET + j;
        JvmMemoryAccessor jvmMemoryAccessor = MEMORY_ACCESSOR;
        switch (jvmMemoryAccessor.$r8$classId) {
            case 0:
                return jvmMemoryAccessor.unsafe.getByte(bArr, j2);
            case 1:
                return IS_BIG_ENDIAN ? getByteBigEndian(j2, bArr) : getByteLittleEndian(j2, bArr);
            default:
                return IS_BIG_ENDIAN ? getByteBigEndian(j2, bArr) : getByteLittleEndian(j2, bArr);
        }
    }

    public static byte getByteBigEndian(long j, Object obj) {
        return (byte) ((getInt((-4) & j, obj) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    public static byte getByteLittleEndian(long j, Object obj) {
        return (byte) ((getInt((-4) & j, obj) >>> ((int) ((j & 3) << 3))) & 255);
    }

    public static int getInt(long j, Object obj) {
        return MEMORY_ACCESSOR.unsafe.getInt(obj, j);
    }

    public static long getLong(long j, Object obj) {
        return MEMORY_ACCESSOR.unsafe.getLong(obj, j);
    }

    public static Object getObject(long j, Object obj) {
        return MEMORY_ACCESSOR.unsafe.getObject(obj, j);
    }

    public static void putByte(byte[] bArr, long j, byte b) {
        long j2 = BYTE_ARRAY_BASE_OFFSET + j;
        JvmMemoryAccessor jvmMemoryAccessor = MEMORY_ACCESSOR;
        switch (jvmMemoryAccessor.$r8$classId) {
            case 0:
                jvmMemoryAccessor.unsafe.putByte(bArr, j2, b);
                break;
            case 1:
                if (!IS_BIG_ENDIAN) {
                    putByteLittleEndian(bArr, j2, b);
                    break;
                } else {
                    putByteBigEndian(bArr, j2, b);
                    break;
                }
            default:
                if (!IS_BIG_ENDIAN) {
                    putByteLittleEndian(bArr, j2, b);
                    break;
                } else {
                    putByteBigEndian(bArr, j2, b);
                    break;
                }
        }
    }

    public static void putByteBigEndian(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int i = getInt(j2, obj);
        int i2 = ((~((int) j)) & 3) << 3;
        putInt(obj, j2, ((255 & b) << i2) | (i & (~(255 << i2))));
    }

    public static void putByteLittleEndian(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        putInt(obj, j2, ((255 & b) << i) | (getInt(j2, obj) & (~(255 << i))));
    }

    public static void putInt(Object obj, long j, int i) {
        MEMORY_ACCESSOR.unsafe.putInt(obj, j, i);
    }

    public static void putLong(Object obj, long j, long j2) {
        MEMORY_ACCESSOR.unsafe.putLong(obj, j, j2);
    }

    public static void putObject(long j, Object obj, Object obj2) {
        MEMORY_ACCESSOR.unsafe.putObject(obj, j, obj2);
    }
}
