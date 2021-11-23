package rewards.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import rewards.Dining;
import rewards.RewardNetwork;


public class DiningBatchProcessorImpl implements DiningBatchProcessor {

	private final RewardNetwork rewardNetwork;

	public DiningBatchProcessorImpl(RewardNetwork rewardNetwork) {
		this.rewardNetwork = rewardNetwork;
	}

	@Override
	public int processBatch(Resource csvInput) throws IOException {
		BufferedReader inputReader =
			new BufferedReader(new InputStreamReader(csvInput.getInputStream()));

		// TODO 01: replace the array list with a new ExecutorService that uses a
		//          cached thread pool
		
		//ArrayList<Thread> threads = new ArrayList<Thread>();
		ArrayList<ExecutorService> threads = new ArrayList<ExecutorService>();

		int count = 0;
		String csvRecord;
		while ((csvRecord = inputReader.readLine()) != null) {
			final Dining dining = createDiningFromCsv(csvRecord);

			Runnable task = new Runnable() {
				@Override
				public void run() {
					rewardNetwork.rewardAccountFor(dining);
				}
			};

			// TODO 02: eliminate the creation and starting of new threads and instead
			//          pass the Runnable task to the ExecutorService for execution
			
			Thread thread = new Thread(task);
			//threads.add(thread);
			//thread.start();
			
			ExecutorService vServiceElt = Executors.newCachedThreadPool();
			threads.add(vServiceElt);
			vServiceElt.execute(thread);
			
			count++;
		}

		// TODO 03: eliminate looping and joining logic and replace it with shutdown() and
		//          awaitTermination calls on the ExecutorService
		for (ExecutorService executors : threads) {
			executors.shutdown();
			TimeUnit unit= TimeUnit.MILLISECONDS;
			long timeout=  5000;
			
			try {
				executors.awaitTermination(timeout, unit);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		for (Thread thread : threads) {
//			try {
//				thread.join();
//			} catch (InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
//		}

		return count;
	}

	private Dining createDiningFromCsv(String csvRecord) {
		String[] fields = StringUtils.commaDelimitedListToStringArray(csvRecord);
		String amount = fields[0];
		String creditCardNumber = fields[1];
		String merchantNumber = fields[2];
		String[] dateTokens = StringUtils.delimitedListToStringArray(fields[3], "/");
		int year = Integer.valueOf(dateTokens[0]);
		int month = Integer.valueOf(dateTokens[1]);
		int day = Integer.valueOf(dateTokens[2]);

		return Dining.createDining(amount, creditCardNumber, merchantNumber, month, day, year);
	}

}
