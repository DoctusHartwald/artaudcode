package rewards.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

		// TODO 09: create a new list of threads

		int count = 0;
		String csvRecord;
		while ((csvRecord = inputReader.readLine()) != null) {
			final Dining dining = createDiningFromCsv(csvRecord);

			// TODO 10: create a new Runnable from the following line
			rewardNetwork.rewardAccountFor(dining);

			// TODO 11: create a new Thread and pass the Runnable as a constructor argument

			// TODO 12: add the thread to the list of threads

			// TODO 13: start the thread

			count++;
		}

		// TODO 14: iterate over the list of threads and join each thread

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
