package concurrency.executors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.execute(new Runnable() {
			public void run() {
				System.out.println("Asynchronous task");
			}
		});

		executorService.shutdown();
		executorService = Executors.newSingleThreadExecutor();

		Future future = executorService.submit(new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Asynchronous task");
			}
		});

		System.out.println(future.get());

		executorService.shutdown();

		executorService = Executors.newSingleThreadExecutor();
		Future future2 = executorService.submit(new Callable() {
			public String call() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				System.out.println("In Callable Method..");
				return "Cool";
			}
		});

		System.out.println(future2.get());

		Set<Callable<String>> callables = new HashSet<>();

		callables.add(new Callable() {
			public String call() {
				return "Task-1";
			}
		});

		callables.add(new Callable() {
			public String call() {
				return "Task-2";
			}
		});

		callables.add(new Callable() {
			public String call() {
				return "Task-3";
			}
		});
		executorService.shutdown();
		executorService = Executors.newSingleThreadExecutor();
		List<Future<String>> futures = executorService.invokeAll(callables);

		for (Future<String> future3 : futures) {
			System.out.println("" + future3.get());

		}

		executorService.shutdown();

		ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(10);
		ScheduledFuture scheduledFuture = executorService1.schedule(new Callable() {
			public String call() {
				System.out.println("Scheduled Cool");
				return "Scheduled Return..";
			}
		}, 5000, TimeUnit.MILLISECONDS);

		System.out.println(scheduledFuture.get());
		executorService1.shutdown();
		System.out.println("All done!");
	}
}
