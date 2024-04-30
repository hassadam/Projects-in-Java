import java.util.Random;

public class TwoSum {

	public static void main(String[] args) {
		Random rand = new Random();
		int target = rand.nextInt(9, 100) + 1;
		int arrSize = rand.nextInt(4, 15);
		int arr[] = new int[arrSize];
		int temp, first, last, sum;

		// Populate array
		for (int i = 0; i < arrSize; i++) {
			arr[i] = rand.nextInt(0, 100) + 1;
		}
		System.out.print("Before: ");
		for (int i = 0; i < arrSize; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
				i = -1;
			}
		}
		System.out.print("After: ");
		for (int i = 0; i < arrSize; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

		System.out.println("The target is: " + target);
		first = 0;
		last = arr[arr.length - 1];
		sum = arr[first] + last;
		System.out.println("Sum: " + sum + " | Pair: " + arr[first] + " | " + last);

		for (int i = 1; i < arr.length - 1; i++) {
			if (sum > target) {
				last = arr[arr.length - 1 - i];
				sum = arr[first] + last;
			} else if (sum < target) {
				first++;
				sum = arr[first] + last;
			}

			System.out.println("Sum: " + sum + " | Pair: " + arr[first] + " | " + last);

			if (sum == target) {
				System.out.println("Got it!");
				System.out.println("First: " + arr[first] + " Last: " + last);
				break;
			}
		}

		if (sum != target) {
			System.out.println("Couldn't find a pair!");
		}
	}
}