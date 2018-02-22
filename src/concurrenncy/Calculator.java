package concurrenncy;

public class Calculator {
	private ImmutableValue currentValue = new ImmutableValue(0);

	public ImmutableValue getValue() {
		return currentValue;
	}

	public void setValue(ImmutableValue newValue) {
		this.currentValue = newValue;
	}

	// Adding synchronized in the method would make it thread safe.
	public /* synchronized */ void add(int newValue) {
		this.currentValue = this.currentValue.add(newValue);
	}
}