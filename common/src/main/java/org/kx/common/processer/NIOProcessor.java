package org.kx.common.processer;

import lombok.Data;
import org.kx.common.CommandCount;
import org.kx.common.buffer.BufferPool;
import org.kx.common.net.BackendConnection;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * create by sunkx on 2018/3/28
 */
@Data
public class NIOProcessor {
    private final String name;
    private final BufferPool bufferPool;
    private final NameableExecutor executor;
    private final ConcurrentMap<Long, BackendConnection> backends;
    private final CommandCount commands;

    private long netInBytes;
    private long netOutBytes;


    public NIOProcessor(String name, BufferPool bufferPool,
                        NameableExecutor executor) throws IOException {
        this.name = name;
        this.bufferPool = bufferPool;
        this.executor = executor;
        this.backends = new ConcurrentHashMap<Long, BackendConnection>();
        this.commands = new CommandCount();
    }

    public void addNetInBytes(long bytes) {
        this.netInBytes += bytes;
    }



}
