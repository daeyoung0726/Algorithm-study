package baekjoon;

import java.util.concurrent.Semaphore;

import java.util.concurrent.*;

class ConnectionPool {
    private final Semaphore semaphore;

    public ConnectionPool(int maxConnections) {
        this.semaphore = new Semaphore(maxConnections); // 최대 N개의 연결 가능
    }

    public void acquireConnection(Thread otherThread) throws InterruptedException {
        semaphore.acquire(); // DB 연결 획득
        System.out.println(Thread.currentThread().getName() + " 연결 획득");

        // 일부러 다른 스레드에게 해제를 위임
        new Thread(() -> {
            try {
                Thread.sleep(3000); // 3초 후 다른 스레드가 해제
                releaseConnection(otherThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void releaseConnection(Thread thread) {
        System.out.println(thread.getName() + " 연결 해제");
        semaphore.release(); // DB 연결 반환
    }
}

public class SemaphoreConnectionPoolExample {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(2); // 최대 2개의 연결 허용

        Runnable task = () -> {
            try {
                Thread otherThread = new Thread();
                pool.acquireConnection(otherThread); // 현재 스레드가 획득하고
                Thread.sleep(1000); // 1초 동안 사용
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}

