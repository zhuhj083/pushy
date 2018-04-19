package com.turo.pushy.apns;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class ClientChannelClassUtilTest {

    @Test
    public void testGetCoreSocketChannelClass() {
        final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        final OioEventLoopGroup oioEventLoopGroup = new OioEventLoopGroup(1);

        try {
            assertEquals(NioSocketChannel.class, ClientChannelClassUtil.getSocketChannelClass(nioEventLoopGroup));
            assertEquals(OioSocketChannel.class, ClientChannelClassUtil.getSocketChannelClass(oioEventLoopGroup));
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
            assertEquals(KQueueSocketChannel.class, ClientChannelClassUtil.getSocketChannelClass(kQueueEventLoopGroup));
        } finally {
            kQueueEventLoopGroup.shutdownGracefully();
        }
    }

    @Test
    public void testGetEpollSocketChannelClass() {
        assumeTrue("Epoll not available: " + Epoll.unavailabilityCause().getMessage(), Epoll.isAvailable());

        final EpollEventLoopGroup epollEventLoopGroup = new EpollEventLoopGroup(1);

        try {
            assertEquals(EpollSocketChannel.class, ClientChannelClassUtil.getSocketChannelClass(epollEventLoopGroup));
        } finally {
            epollEventLoopGroup.shutdownGracefully();
        }
    }

    @Test
    public void testGetCoreDatagramChannelClass() {
        final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        final OioEventLoopGroup oioEventLoopGroup = new OioEventLoopGroup(1);

        try {
            assertEquals(NioDatagramChannel.class, ClientChannelClassUtil.getDatagramChannelClass(nioEventLoopGroup));
            assertEquals(OioDatagramChannel.class, ClientChannelClassUtil.getDatagramChannelClass(oioEventLoopGroup));
        } finally {
            nioEventLoopGroup.shutdownGracefully();
            oioEventLoopGroup.shutdownGracefully();
        }
    }

    @Test
    public void testGetKqueueDatagramChannelClass() {
        assumeTrue("KQueue not available: " + KQueue.unavailabilityCause().getMessage(), KQueue.isAvailable());

        final KQueueEventLoopGroup kQueueEventLoopGroup = new KQueueEventLoopGroup(1);

        try {
            assertEquals(KQueueDatagramChannel.class, ClientChannelClassUtil.getDatagramChannelClass(kQueueEventLoopGroup));
        } finally {
            kQueueEventLoopGroup.shutdownGracefully();
        }
    }

    @Test
    public void testGetEpollDatagramChannelClass() {
        assumeTrue("Epoll not available: " + Epoll.unavailabilityCause().getMessage(), Epoll.isAvailable());

        final EpollEventLoopGroup epollEventLoopGroup = new EpollEventLoopGroup(1);

        try {
            assertEquals(EpollDatagramChannel.class, ClientChannelClassUtil.getDatagramChannelClass(epollEventLoopGroup));
        } finally {
            epollEventLoopGroup.shutdownGracefully();
        }
    }
}
