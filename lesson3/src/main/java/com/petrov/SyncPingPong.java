package com.petrov;

public class SyncPingPong {

    private String data;
    private int count = 0;
    private final int MAX_VALUE = 10;
    private final String PING = "Ping";
    private final String PONG = "Pong";

    public synchronized void put(String data) {
        while (this.data != null || Thread.interrupted()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data = data;
        this.notifyAll();
    }

    public synchronized String get() {
        while (this.data == null || Thread.interrupted()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String tmp = data;
        if (this.data.equals(PING)) {
            this.data = PONG;
        } else {
            this.data = PING;
        }
        count++;
        if (count >= MAX_VALUE) {
            this.data = null;
        }
        this.notifyAll();
        return tmp;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncPingPong syncPingPong = new SyncPingPong();

        new Thread(() -> {
            for (int i = 0; i < syncPingPong.MAX_VALUE; i++) {
                System.out.println(syncPingPong.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        syncPingPong.put(syncPingPong.PING);
        syncPingPong.put(syncPingPong.PONG);

    }
}
