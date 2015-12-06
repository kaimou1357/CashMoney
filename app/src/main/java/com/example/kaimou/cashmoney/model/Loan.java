
package com.example.kaimou.cashmoney.model;


import java.util.HashMap;
import java.util.Map;

public class Loan {

    private String Id;
    private String loanTo;
    private int amount;
    private int V;
    private boolean paid;
    private String deadline;
    private String createdAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The loanTo
     */
    public String getLoanTo() {
        return loanTo;
    }

    /**
     *
     * @param loanTo
     * The loan_to
     */
    public void setLoanTo(String loanTo) {
        this.loanTo = loanTo;
    }

    /**
     *
     * @return
     * The amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The V
     */
    public int getV() {
        return V;
    }

    /**
     *
     * @param V
     * The __v
     */
    public void setV(int V) {
        this.V = V;
    }

    /**
     *
     * @return
     * The paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     *
     * @param paid
     * The paid
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     *
     * @return
     * The deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     *
     * @param deadline
     * The deadline
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
