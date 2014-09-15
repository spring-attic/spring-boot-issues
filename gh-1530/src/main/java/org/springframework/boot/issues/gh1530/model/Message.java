package org.springframework.boot.issues.gh1530.model;

/**
 * Message model for MessageLogger.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
public class Message {

    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";
    public static final String STATUS_EXCEPTION = "EXCEPTION";

    private final String transactionId;
    private String message;
    private String status;

    public Message(String transactionId, String message, String status) {
        this.transactionId = transactionId;
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" + "transactionId=" + transactionId + ", message=" + getMessage() + ", status=" + getStatus() + '}';
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
