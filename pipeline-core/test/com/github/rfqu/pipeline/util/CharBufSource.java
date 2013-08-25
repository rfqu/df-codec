package com.github.rfqu.pipeline.util;

import java.nio.CharBuffer;

import com.github.rfqu.df4j.core.Callback;
import com.github.rfqu.df4j.core.StreamPort;
import com.github.rfqu.pipeline.core.Bolt;
import com.github.rfqu.pipeline.core.Source;

public class CharBufSource implements Bolt, Source<CharBuffer>
{
    //------------------ Bolt part
    protected Callback<Object> context;


    @Override
    public void setContext(Callback<Object> context) {
        this.context = context;
    }

    public void start() {
    }

    public void stop() {
    }

    //------------------ Source part

    /** there output messages go */
    protected StreamPort<CharBuffer> sinkPort;
    
    /** here output messages return */
    protected StreamPort<CharBuffer> myOutput=new StreamPort<CharBuffer>(){

        @Override
        public void post(CharBuffer m) {
        }

        @Override
        public void close() {
        }

        @Override
        public boolean isClosed() {
            return false;
        }
    };

    public void setSinkPort(StreamPort<CharBuffer> sinkPort) {
        this.sinkPort=sinkPort;
    }
    
    public StreamPort<CharBuffer> getReturnPort() {
        return myOutput;
    }

	public void post(String s) {
		CharBuffer buf=CharBuffer.wrap(s);
		sinkPort.post(buf);
	}

	public void close() {
        sinkPort.close();
    }

    public void postFailure(Throwable exc) {
        context.postFailure(exc);
    }
}