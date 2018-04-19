package com.turo.pushy.apns.server;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class ServerChannelClassUtilTest {

    @Test
    public void testGetCoreSocketChannelClass() {
        final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        final OioEventLoopGroup oioEventLoopGroup = new OioEventLoopGroup(1);

        try {
            assertEquals(NioServerSocketChannel.class, ServerChannelClassUtil.getServerSocketChannelClass(nioEventLoopGroup));
            assertEquals(OioServerSocketChannel.class, ServerChannelClassUtil.getServerSocketChannelClass(oioEventLoopGroup));
        } finally {
            nioEventLoopGroup.shutdownGracefully();
            oioEventLoopGroup.shutdownGracefully();
        }
    }

    @Test
    public void testGetKqueueSocketChannelClass() {
        assumeTrue("KQueue not available: " + KQueue.unavailabilityCause().getMessage(), KQueue.isAvailable());

        final KQueueEventLoopGroup kQueueEventLoopGroup = new KQueueEventLoopGroup(1);

        try {
            assertEquals(KQueueServerSocketChannel.class, ServerChannelClassUtil.getServerSocketChannelClass(kQueueEventLoopGroup));
        } finally {
            kQueueEventLoopGroup.shutdownGracefully();
        }
    }

    @Test
    public void testGetEpollSocketChannelClass() {
        assumeTrue("Epoll not available: " + Epoll.unavailabilityCause().getMessage(), Epoll.isAvailable());

        final EpollEventLoopGroup epollEventLoopGroup = new EpollEventLoopGroup(1);

        try {
            assertEquals(EpollServerSocketChannel.class, ServerChannelClassUtil.getServerSocketChannelClass(epollEventLoopGroup));
        } finally {
            epollEventLoopGroup.shutdownGracefully();
        }
    }
}
