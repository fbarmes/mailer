package fr.tricotain.mailer.service.mail;


public class MailSenderException extends Exception {
	public static final long serialVersionUID = 1L;

	public MailSenderException() {
		super();
	}

	public MailSenderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MailSenderException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailSenderException(String message) {
		super(message);
	}

	public MailSenderException(Throwable cause) {
		super(cause);
	}
	
}
