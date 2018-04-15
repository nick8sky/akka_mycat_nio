package org.kx.common;

import lombok.Data;

/**
 * create by sunkx on 2018/3/28
 */
@Data
public class CommandCount {
    private long initDB;
    private long query;
    private long stmtPrepare;
    private long stmtSendLongData;
    private long stmtReset;
    private long stmtExecute;
    private long stmtClose;
    private long ping;
    private long kill;
    private long quit;
    private long heartbeat;
    private long other;
}
