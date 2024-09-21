/*
 * Copyright (C) 2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package vendor.oplus.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOplusRadio extends IInterface {

    public static final String DESCRIPTOR = "vendor$oplus$hardware$radio$IOplusRadio".replace('$', '.');
    public static final String HASH = "627e0c69b42a1964009a4de9ee4477b417e63215";
    public static final int VERSION = 2;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void setCallback(IOplusRadioResponse responseCallback, IOplusRadioIndication indicationCallback)
            throws RemoteException;

    void setNrMode(int serial, int mode) throws RemoteException;

    public static class Default implements IOplusRadio {
        @Override
        public void setNrMode(int serial, int mode) throws RemoteException {
        }

        @Override
        public void setCallback(IOplusRadioResponse responseCallback,
                IOplusRadioIndication indicationCallback) throws RemoteException {
        }

        @Override
        public int getInterfaceVersion() {
            return 0;
        }

        @Override
        public String getInterfaceHash() {
            return "";
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusRadio {

        static final int TRANSACTION_setNrMode = 1;
        static final int TRANSACTION_setCallback = 17;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusRadio asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            final IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IOplusRadio)) {
                return (IOplusRadio) iin;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case TRANSACTION_setNrMode:
                    return "setNrMode";
                case TRANSACTION_setCallback:
                    return "setCallback";
                case TRANSACTION_getInterfaceHash:
                    return "getInterfaceHash";
                case TRANSACTION_getInterfaceVersion:
                    return "getInterfaceVersion";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            final String descriptor = DESCRIPTOR;
            if (code >= 1 && code <= TRANSACTION_getInterfaceVersion) {
                data.enforceInterface(descriptor);
            }
            switch (code) {
                case TRANSACTION_getInterfaceHash:
                    reply.writeNoException();
                    reply.writeString(getInterfaceHash());
                    return true;
                case TRANSACTION_getInterfaceVersion:
                    reply.writeNoException();
                    reply.writeInt(getInterfaceVersion());
                    return true;
                case IBinder.INTERFACE_TRANSACTION:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case TRANSACTION_setNrMode:
                            final int serial = data.readInt();
                            final int mode = data.readInt();
                            data.enforceNoDataAvail();
                            setNrMode(serial, mode);
                            return true;
                        case TRANSACTION_setCallback:
                            final IOplusRadioResponse responseCallback =
                                    IOplusRadioResponse.Stub.asInterface(data.readStrongBinder());
                            final IOplusRadioIndication indicationCallback =
                                    IOplusRadioIndication.Stub.asInterface(data.readStrongBinder());
                            data.enforceNoDataAvail();
                            setCallback(responseCallback, indicationCallback);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        private static class Proxy implements IOplusRadio {

            private final IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void setNrMode(int serial, int mode) throws RemoteException {
                final Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(mode);
                    final boolean _status = mRemote.transact(TRANSACTION_setNrMode, _data, null, 1);
                    if (!_status) {
                        throw new RemoteException("Method setNrMode is unimplemented.");
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void setCallback(IOplusRadioResponse responseCallback, IOplusRadioIndication indicationCallback)
                    throws RemoteException {
                final Parcel _data = Parcel.obtain(asBinder());
                final Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongInterface(responseCallback);
                    _data.writeStrongInterface(indicationCallback);
                    final boolean _status = mRemote.transact(TRANSACTION_setCallback, _data, _reply, 0);
                    if (!_status) {
                        throw new RemoteException("Method setCallback is unimplemented.");
                    }
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public int getInterfaceVersion() throws RemoteException {
                if (mCachedVersion == -1) {
                    final Parcel data = Parcel.obtain(asBinder());
                    final Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        mRemote.transact(Stub.TRANSACTION_getInterfaceVersion, data, reply, 0);
                        reply.readException();
                        mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return mCachedVersion;
            }

            @Override
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(mCachedHash)) {
                    final Parcel data = Parcel.obtain(asBinder());
                    final Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        mRemote.transact(Stub.TRANSACTION_getInterfaceHash, data, reply, 0);
                        reply.readException();
                        mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } catch (Throwable th) {
                        reply.recycle();
                        data.recycle();
                        throw th;
                    }
                }
                return mCachedHash;
            }
        }

        public int getMaxTransactionId() {
            return TRANSACTION_getInterfaceHash;
        }
    }
}
