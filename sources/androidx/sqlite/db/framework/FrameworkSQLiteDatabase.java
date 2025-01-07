package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import androidx.room.driver.SupportSQLiteStatement;
import androidx.room.driver.SupportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    public static final Companion Companion = null;
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Lazy beginTransactionMethod$delegate;
    public static final Lazy getThreadSessionMethod$delegate;
    public final SQLiteDatabase delegate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        getThreadSessionMethod$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteDatabase$Companion$getThreadSessionMethod$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                try {
                    Class[] clsArr = new Class[0];
                    Method declaredMethod = SQLiteDatabase.class.getDeclaredMethod("getThreadSession", null);
                    declaredMethod.setAccessible(true);
                    return declaredMethod;
                } catch (Throwable unused) {
                    return null;
                }
            }
        });
        beginTransactionMethod$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteDatabase$Companion$beginTransactionMethod$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class<?> returnType;
                try {
                    Method method = (Method) FrameworkSQLiteDatabase.getThreadSessionMethod$delegate.getValue();
                    if (method == null || (returnType = method.getReturnType()) == null) {
                        return null;
                    }
                    Class<?> cls = Integer.TYPE;
                    return returnType.getDeclaredMethod("beginTransaction", cls, SQLiteTransactionListener.class, cls, CancellationSignal.class);
                } catch (Throwable unused) {
                    return null;
                }
            }
        });
    }

    public FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        this.delegate = sQLiteDatabase;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void beginTransaction() {
        this.delegate.beginTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void beginTransactionNonExclusive() {
        this.delegate.beginTransactionNonExclusive();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void beginTransactionReadOnly() {
        Lazy lazy = beginTransactionMethod$delegate;
        if (((Method) lazy.getValue()) != null) {
            Lazy lazy2 = getThreadSessionMethod$delegate;
            if (((Method) lazy2.getValue()) != null) {
                Method method = (Method) lazy.getValue();
                Intrinsics.checkNotNull(method);
                Method method2 = (Method) lazy2.getValue();
                Intrinsics.checkNotNull(method2);
                Object invoke = method2.invoke(this.delegate, null);
                if (invoke == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                method.invoke(invoke, 0, null, 0, null);
                return;
            }
        }
        beginTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.delegate.close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final FrameworkSQLiteStatement compileStatement(String str) {
        return new FrameworkSQLiteStatement(this.delegate.compileStatement(str));
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void endTransaction() {
        this.delegate.endTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void execSQL(String str) {
        this.delegate.execSQL(str);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final boolean isOpen() {
        return this.delegate.isOpen();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final Cursor query(final SupportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1 supportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1) {
        final Function4 function4 = new Function4() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteDatabase$query$cursorFactory$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                SQLiteCursorDriver sQLiteCursorDriver = (SQLiteCursorDriver) obj2;
                String str = (String) obj3;
                SQLiteQuery sQLiteQuery = (SQLiteQuery) obj4;
                SupportSQLiteQuery supportSQLiteQuery = supportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1;
                Intrinsics.checkNotNull(sQLiteQuery);
                FrameworkSQLiteProgram frameworkSQLiteProgram = new FrameworkSQLiteProgram(sQLiteQuery);
                SupportSQLiteStatement.SupportAndroidSQLiteStatement supportAndroidSQLiteStatement = ((SupportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1) supportSQLiteQuery).this$0;
                int length = supportAndroidSQLiteStatement.bindingTypes.length;
                for (int i = 1; i < length; i++) {
                    int i2 = supportAndroidSQLiteStatement.bindingTypes[i];
                    if (i2 == 1) {
                        frameworkSQLiteProgram.bindLong(supportAndroidSQLiteStatement.longBindings[i], i);
                    } else if (i2 == 2) {
                        frameworkSQLiteProgram.bindDouble(supportAndroidSQLiteStatement.doubleBindings[i], i);
                    } else if (i2 == 3) {
                        String str2 = supportAndroidSQLiteStatement.stringBindings[i];
                        Intrinsics.checkNotNull(str2);
                        frameworkSQLiteProgram.bindString(i, str2);
                    } else if (i2 == 4) {
                        byte[] bArr = supportAndroidSQLiteStatement.blobBindings[i];
                        Intrinsics.checkNotNull(bArr);
                        frameworkSQLiteProgram.bindBlob(i, bArr);
                    } else if (i2 == 5) {
                        frameworkSQLiteProgram.bindNull(i);
                    }
                }
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            }
        };
        return this.delegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteDatabase$$ExternalSyntheticLambda0
            @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
            public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                return (Cursor) Function4.this.invoke(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, supportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1.this$0.sql, EMPTY_STRING_ARRAY, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }
}
