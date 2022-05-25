package sva.tbot.exception;

public final class JuegoException extends RuntimeException {
	public JuegoException() {
		super("Unexpected Exception");
	}

	public JuegoException(String str) {
		super(str);
	}
}
