package com.luijp;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet(){
        ThreadLocal tl = new ThreadLocal();

        new Thread(()->{
            tl.set("萧炎");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"蓝色").start();

        new Thread(()->{
            tl.set("药尘");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"绿色").start();
    }
}
