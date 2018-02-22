package concurrenncy;

class ImmutableValue {
	private int value;

	ImmutableValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public ImmutableValue add(int valueToAdd) {
		ImmutableValue immutableValue = new ImmutableValue(this.value + valueToAdd);
		System.out.println("Value=" + immutableValue.getValue() + "Thread:" + Thread.currentThread().getName());
		return immutableValue;
	}
}

public class ThreadSafeAndImmutability {

	public static void main(String[] args) {
		ImmutableValue immutableValue = new ImmutableValue(0);
		for (int i = 1; i <= 5; i++) {
			final int j = i;
			new Thread("Thread:" + i) {
				public void run() {
					immutableValue.add(j);
				}
			}.start();
		}

		Calculator calculator = new Calculator();
		for (int k = 1; k <= 5; k++) {
			final int l = k;
			new Thread("ThreadPool:" + l) {
				public void run() {
					calculator.add(l);
				}
			}.start();
		}
	}
}
