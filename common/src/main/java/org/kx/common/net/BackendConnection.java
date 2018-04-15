package org.kx.common.net;

import org.omg.CORBA.portable.ResponseHandler;

import java.io.UnsupportedEncodingException;

/**
 * create by sunkx on 2018/3/28
 */
public interface BackendConnection extends ClosableConnection{
    public boolean isModifiedSQLExecuted();

    public boolean isFromSlaveDB();

    public String getSchema();

    public void setSchema(String newSchema);

    public long getLastTime();

    public boolean isClosedOrQuit();

    public void setAttachment(Object attachment);

    public void quit();

    public void setLastTime(long currentTimeMillis);

    public void release();

    public boolean setResponseHandler(ResponseHandler commandHandler);

    public void commit();

    public void query(String sql) throws UnsupportedEncodingException;

    public Object getAttachment();

    // public long getThreadId();





    public void recordSql(String host, String schema, String statement);

    public boolean syncAndExcute();

    public void rollback();

    public boolean isBorrowed();

    public void setBorrowed(boolean borrowed);

    public int getTxIsolation();

    public boolean isAutocommit();

    public long getId();

    public void discardClose(String reason);
}
