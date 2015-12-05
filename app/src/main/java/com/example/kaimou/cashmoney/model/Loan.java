
package com.example.kaimou.cashmoney.model;

import java.util.HashMap;
import java.util.Map;

public class Loan {

    private int amount;
    private String loanTo;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The loanTo
     */
    public String getLoanTo() {
        return loanTo;
    }

    /**
     * 
     * @param loanTo
     *     The loan_to
     */
    public void setLoanTo(String loanTo) {
        this.loanTo = loanTo;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
