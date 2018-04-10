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
public class reportingGroups {
    public String name;
    public String description;
    public int sliceVolume;
    public String subscriptionDate;
    public String subscriptionType;
    public String resetPeriod;
    public int birdirVolume;

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSliceVolume() {
        return sliceVolume;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public String getResetPeriod() {
        return resetPeriod;
    }

    public int getBirdirVolume() {
        return birdirVolume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSliceVolume(int sliceVolume) {
        this.sliceVolume = sliceVolume;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void setResetPeriod(String resetPeriod) {
        this.resetPeriod = resetPeriod;
    }

    public void setBirdirVolume(int birdirVolume) {
        this.birdirVolume = birdirVolume;
    }
    
}
