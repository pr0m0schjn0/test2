/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsoap;

/**
 *
 * @author lehoa
 */
public class accumulateData {
    public int birdirVolume;
    public String expiryDate;
    public String previousExpiryDate_time;
    public String previousExpiryDate_volume;
    public double reservedQuota;
    public String resetPeriod;
    public String name;
    public String selected;
    public String subscriberGroupName;
    public String subscriptionDate;
    public int validityTime;

    public void setBirdirVolume(int birdirVolume) {
        this.birdirVolume = birdirVolume;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPreviousExpiryDate_time(String previousExpiryDate_time) {
        this.previousExpiryDate_time = previousExpiryDate_time;
    }

    public void setPreviousExpiryDate_volume(String previousExpiryDate_volume) {
        this.previousExpiryDate_volume = previousExpiryDate_volume;
    }

    public void setReservedQuota(double reservedQuota) {
        this.reservedQuota = reservedQuota;
    }

    public void setResetPeriod(String resetPeriod) {
        this.resetPeriod = resetPeriod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public void setSubscriberGroupName(String subscriberGroupName) {
        this.subscriberGroupName = subscriberGroupName;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void setValidityTime(int validityTime) {
        this.validityTime = validityTime;
    }

    public int getBirdirVolume() {
        return birdirVolume;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getPreviousExpiryDate_time() {
        return previousExpiryDate_time;
    }

    public String getPreviousExpiryDate_volume() {
        return previousExpiryDate_volume;
    }

    public double getReservedQuota() {
        return reservedQuota;
    }

    public String getResetPeriod() {
        return resetPeriod;
    }

    public String getName() {
        return name;
    }

    public String getSelected() {
        return selected;
    }

    public String getSubscriberGroupName() {
        return subscriberGroupName;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public int getValidityTime() {
        return validityTime;
    }
}
