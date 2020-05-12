package ru.deft.testmssqlmerge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.deft.testmssqlmerge.service.OutboundService;

import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class TestMssqlMergeApplication implements CommandLineRunner {

    private final OutboundService outboundService;
    //    final CyclicBarrier barrier = new CyclicBarrier(Runtime.getRuntime().availableProcessors() + 1);
    final CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestMssqlMergeApplication.class);
        // disable spring banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("EXECUTING : command line runner");
        Scanner scanner = new Scanner(System.in);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        int countThreads = scanner.nextInt();
        int countGroups = 300;
        scanner.close();
        AtomicInteger count = new AtomicInteger();
        while (countGroups > 0) {
            System.out.println("EXECUTING : countThreads - " + countGroups);
            for (int i = 0; i < 5; i++) {
                int finalCountGroups = countGroups;
                executorService.execute(() -> {
                    try {
                        outboundService.mergeByQueryWithThreadPool(finalCountGroups, (int) (Math.random() * 11));
//                    outboundService.mergeByQueryWithNewConnection(1, (int) (Math.random() * 11));
                        barrier.await();
                        System.out.println("Count threads: " + count.getAndIncrement());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                });
            }
            countGroups--;
        }
        executorService.shutdown();
    }
}
