
package com.example.kaimou.cashmoney.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String _id;
    private String name;
    private String email;
    private String password;
    private int _v;
    private List<Object> totalLoans = new ArrayList<Object>();
    private String updatedAt;
    private int currentCreditLine;
    private int totalCreditLine;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void set_id(String Id) {
        this._id = Id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *     The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return
     *     The _v
     */
    public int get_v() {
        return _v;
    }

    /**
     * 
     * @param V
     *     The _v
     */
    public void set_v(int V) {
        this._v = V;
    }

    /**
     * 
     * @return
     *     The totalLoans
     */
    public List<Object> getTotalLoans() {
        return totalLoans;
    }

    /**
     * 
     * @param totalLoans
     *     The total_loans
     */
    public void setTotalLoans(List<Object> totalLoans) {
        this.totalLoans = totalLoans;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The currentCreditLine
     */
    public int getCurrentCreditLine() {
        return currentCreditLine;
    }

    /**
     * 
     * @param currentCreditLine
     *     The current_credit_line
     */
    public void setCurrentCreditLine(int currentCreditLine) {
        this.currentCreditLine = currentCreditLine;
    }

    /**
     * 
     * @return
     *     The totalCreditLine
     */
    public int getTotalCreditLine() {
        return totalCreditLine;
    }

    /**
     * 
     * @param totalCreditLine
     *     The total_credit_line
     */
    public void setTotalCreditLine(int totalCreditLine) {
        this.totalCreditLine = totalCreditLine;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
