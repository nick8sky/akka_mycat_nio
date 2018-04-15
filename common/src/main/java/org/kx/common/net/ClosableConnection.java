package org.kx.common.net;

/**
 * create by sunkx on 2018/3/28
 */
public interface ClosableConnection {
    String getCharset();
    //关闭连接
    void close(String reason);
    boolean isClosed();
    public void idleCheck();
    long getStartupTime();
    String getHost();
    int getPort();
    int getLocalPort();
    long getNetInBytes();
    long getNetOutBytes();
}
