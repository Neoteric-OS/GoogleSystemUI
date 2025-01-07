package androidx.versionedparcelable;

import android.os.IBinder;
import android.os.Parcelable;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.collection.SimpleArrayMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class VersionedParcel {
    public final SimpleArrayMap mParcelizerCache;
    public final SimpleArrayMap mReadCache;
    public final SimpleArrayMap mWriteCache;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.versionedparcelable.VersionedParcel$1, reason: invalid class name */
    public final class AnonymousClass1 extends ObjectInputStream {
        @Override // java.io.ObjectInputStream
        public final Class resolveClass(ObjectStreamClass objectStreamClass) {
            Class<?> cls = Class.forName(objectStreamClass.getName(), false, AnonymousClass1.class.getClassLoader());
            return cls != null ? cls : super.resolveClass(objectStreamClass);
        }
    }

    public VersionedParcel(SimpleArrayMap simpleArrayMap, SimpleArrayMap simpleArrayMap2, SimpleArrayMap simpleArrayMap3) {
        this.mReadCache = simpleArrayMap;
        this.mWriteCache = simpleArrayMap2;
        this.mParcelizerCache = simpleArrayMap3;
    }

    public abstract VersionedParcelParcel createSubParcel();

    public final Class findParcelClass(Class cls) {
        String name = cls.getName();
        SimpleArrayMap simpleArrayMap = this.mParcelizerCache;
        Class cls2 = (Class) simpleArrayMap.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        simpleArrayMap.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method getReadMethod(String str) {
        SimpleArrayMap simpleArrayMap = this.mReadCache;
        Method method = (Method) simpleArrayMap.get(str);
        if (method != null) {
            return method;
        }
        Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
        simpleArrayMap.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method getWriteMethod(Class cls) {
        String name = cls.getName();
        SimpleArrayMap simpleArrayMap = this.mWriteCache;
        Method method = (Method) simpleArrayMap.get(name);
        if (method != null) {
            return method;
        }
        Method declaredMethod = findParcelClass(cls).getDeclaredMethod("write", cls, VersionedParcel.class);
        simpleArrayMap.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public final Object[] readArray(int i, Object[] objArr) {
        byte[] bArr;
        Serializable serializable;
        if (!readField(i)) {
            return objArr;
        }
        VersionedParcelParcel versionedParcelParcel = (VersionedParcelParcel) this;
        int readInt = versionedParcelParcel.mParcel.readInt();
        if (readInt < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(readInt);
        if (readInt != 0) {
            int readInt2 = versionedParcelParcel.mParcel.readInt();
            if (readInt < 0) {
                return null;
            }
            if (readInt2 == 1) {
                while (readInt > 0) {
                    arrayList.add(readVersionedParcelable());
                    readInt--;
                }
            } else if (readInt2 == 2) {
                while (readInt > 0) {
                    arrayList.add(versionedParcelParcel.mParcel.readParcelable(VersionedParcelParcel.class.getClassLoader()));
                    readInt--;
                }
            } else if (readInt2 == 3) {
                while (readInt > 0) {
                    String readString = versionedParcelParcel.mParcel.readString();
                    if (readString == null) {
                        serializable = null;
                    } else {
                        int readInt3 = versionedParcelParcel.mParcel.readInt();
                        if (readInt3 < 0) {
                            bArr = null;
                        } else {
                            bArr = new byte[readInt3];
                            versionedParcelParcel.mParcel.readByteArray(bArr);
                        }
                        try {
                            serializable = (Serializable) new AnonymousClass1(new ByteArrayInputStream(bArr)).readObject();
                        } catch (IOException e) {
                            throw new RuntimeException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Unable to read Serializable object (name = ", readString, ")"), e);
                        } catch (ClassNotFoundException e2) {
                            throw new RuntimeException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Unable to read Serializable object (name = ", readString, ")"), e2);
                        }
                    }
                    arrayList.add(serializable);
                    readInt--;
                }
            } else if (readInt2 == 4) {
                while (readInt > 0) {
                    arrayList.add(versionedParcelParcel.mParcel.readString());
                    readInt--;
                }
            } else if (readInt2 == 5) {
                while (readInt > 0) {
                    arrayList.add(versionedParcelParcel.mParcel.readStrongBinder());
                    readInt--;
                }
            }
        }
        return arrayList.toArray(objArr);
    }

    public abstract boolean readField(int i);

    public final int readInt(int i, int i2) {
        return !readField(i2) ? i : ((VersionedParcelParcel) this).mParcel.readInt();
    }

    public final Parcelable readParcelable(Parcelable parcelable, int i) {
        return !readField(i) ? parcelable : ((VersionedParcelParcel) this).mParcel.readParcelable(VersionedParcelParcel.class.getClassLoader());
    }

    public final String readString(int i, String str) {
        return !readField(i) ? str : ((VersionedParcelParcel) this).mParcel.readString();
    }

    public final VersionedParcelable readVersionedParcelable() {
        String readString = ((VersionedParcelParcel) this).mParcel.readString();
        if (readString == null) {
            return null;
        }
        try {
            return (VersionedParcelable) getReadMethod(readString).invoke(null, createSubParcel());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException(e4);
        }
    }

    public abstract void setOutputField(int i);

    public final void writeArray(int i, Object[] objArr) {
        int i2;
        setOutputField(i);
        if (objArr == null) {
            writeInt(-1);
            return;
        }
        int length = objArr.length;
        writeInt(length);
        if (length > 0) {
            int i3 = 0;
            Object obj = objArr[0];
            if (obj instanceof String) {
                i2 = 4;
            } else if (obj instanceof Parcelable) {
                i2 = 2;
            } else if (obj instanceof VersionedParcelable) {
                i2 = 1;
            } else if (obj instanceof Serializable) {
                i2 = 3;
            } else if (obj instanceof IBinder) {
                i2 = 5;
            } else if (obj instanceof Integer) {
                i2 = 7;
            } else {
                if (!(obj instanceof Float)) {
                    throw new IllegalArgumentException(obj.getClass().getName().concat(" cannot be VersionedParcelled"));
                }
                i2 = 8;
            }
            writeInt(i2);
            if (i2 == 1) {
                while (i3 < length) {
                    writeVersionedParcelable((VersionedParcelable) objArr[i3]);
                    i3++;
                }
                return;
            }
            if (i2 == 2) {
                for (Object obj2 : objArr) {
                    ((VersionedParcelParcel) this).mParcel.writeParcelable((Parcelable) obj2, 0);
                }
                return;
            }
            if (i2 != 3) {
                if (i2 == 4) {
                    while (i3 < length) {
                        writeString((String) objArr[i3]);
                        i3++;
                    }
                    return;
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    while (i3 < length) {
                        ((VersionedParcelParcel) this).mParcel.writeStrongBinder((IBinder) objArr[i3]);
                        i3++;
                    }
                    return;
                }
            }
            while (i3 < length) {
                Serializable serializable = (Serializable) objArr[i3];
                if (serializable == null) {
                    writeString(null);
                } else {
                    String name = serializable.getClass().getName();
                    writeString(name);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(serializable);
                        objectOutputStream.close();
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        VersionedParcelParcel versionedParcelParcel = (VersionedParcelParcel) this;
                        if (byteArray != null) {
                            versionedParcelParcel.mParcel.writeInt(byteArray.length);
                            versionedParcelParcel.mParcel.writeByteArray(byteArray);
                        } else {
                            versionedParcelParcel.mParcel.writeInt(-1);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("VersionedParcelable encountered IOException writing serializable object (name = ", name, ")"), e);
                    }
                }
                i3++;
            }
        }
    }

    public abstract void writeInt(int i);

    public final void writeInt(int i, int i2) {
        setOutputField(i2);
        writeInt(i);
    }

    public final void writeParcelable(Parcelable parcelable, int i) {
        setOutputField(i);
        ((VersionedParcelParcel) this).mParcel.writeParcelable(parcelable, 0);
    }

    public final void writeString(int i, String str) {
        setOutputField(i);
        writeString(str);
    }

    public abstract void writeString(String str);

    public final void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString(null);
            return;
        }
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
            VersionedParcelParcel createSubParcel = createSubParcel();
            try {
                getWriteMethod(versionedParcelable.getClass()).invoke(null, versionedParcelable, createSubParcel);
                createSubParcel.closeField();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                Throwable cause = e4.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                if (!(cause instanceof Error)) {
                    throw new RuntimeException(e4);
                }
                throw ((Error) cause);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
