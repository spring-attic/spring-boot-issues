package org.springframework.boot.issues.gh1530.model;

/**
 * Transaction model for TransactionLogger.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
public class Transaction {
    
    public static final String TYPE_MY_TRANSACTION = "MY_TRANSACTION";
    
    public static final String STATUS_RUNNING = "RUNNING";
    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";
    public static final String STATUS_EXCEPTION = "EXCEPTION";

    private final String transactionId;
    private final String type;
    private String status;

    public Transaction(String transactionId, String type, String status) {
        this.transactionId = transactionId;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", type=" + type + ", status=" + getStatus() + '}';
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
