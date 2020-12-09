package com.jt.lux.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * id生成器，基于snowflake算法衍生，返回值为字符串，包括可读时间内容<br/>
 * 格式为：yyyyMMddHHMMssSSS+数据中心编号+机器ID+重复计数
 *
 */
@Component
public class IdGenerator {
    private static final Logger LOG=LoggerFactory.getLogger(IdGenerator.class);
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    // private long twepoch = 1288834974657L; // Thu, 04 Nov 2010 01:42:54 GMT
    private long workerIdBits = 5L; // 节点ID长度
    private long datacenterIdBits = 5L; // 数据中心ID长度
    private long maxWorkerId = -1L ^ (-1L << workerIdBits); // 最大支持机器节点数0~31，一共32个
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 最大支持数据中心节点数0~31，一共32个
    private long sequenceBits = 12L; // 序列号12位
    private long workerIdShift = sequenceBits; // 机器节点左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits; // 数据中心节点左移17位
    // private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //
    // 时间毫秒数左移22位
    private long sequenceMask = -1L ^ (-1L << sequenceBits); // 4095
    private long lastTimestamp = -1L;

    public IdGenerator(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public IdGenerator() {
        this.workerId = 0;
        this.datacenterId = 0;
    }

    public synchronized String nextId() {
        LOG.debug("idgenerator:{}",this);
        long timestamp = timeGen(); // 获取当前毫秒数
        // 如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            // sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            // 判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp); // 自旋等待到下一毫秒
            }
        } else {
            sequence = 0L; // 如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;

        StringBuilder idStr = new StringBuilder();
        String datePrefix = DateFormatUtils.format(timestamp, "yyyyMMddHHmmssSSS");
        idStr.append(datePrefix);

        Long suffix = (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        int suffixLen = suffix.toString().length();
        if (suffixLen < 4) {
            idStr.append(StringUtils.repeat("0", 4 - suffixLen));
        }
        idStr.append(suffix);

        return idStr.toString();
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public static void main(String[] args) throws Exception {
        IdGenerator id = new IdGenerator();
        System.out.println(id.nextId() + " ");
//        final Set<String> set = Collections.synchronizedSet(new HashSet<>());
//
//        final IdGenerator w1 = new IdGenerator(0, 0);
//        final IdGenerator w2 = new IdGenerator(1, 0);
//        final CyclicBarrier cdl = new CyclicBarrier(100);
//        AtomicInteger count = new AtomicInteger(0);
//
//        for (int i = 0; i < 500; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        cdl.await();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    // id
//                    String id = w1.nextId();
//                    if (set.contains(id)) {
//                        count.incrementAndGet();
//                        System.out.println(id + " exists");
//                    }
//                    set.add(id);
//                    System.out.println(id);
//
//                    // id2
//                    String id2 = w2.nextId();
//                    if (set.contains(id2)) {
//                        count.incrementAndGet();
//                        System.out.println(id2 + " exists");
//                    }
//                    set.add(id2);
//                    System.out.println(id2);
//                }
//            }).start();
//        }
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        System.out.println(set.size() + " " + count.get());
    }
}