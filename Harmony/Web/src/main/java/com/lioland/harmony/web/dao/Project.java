/*
 * Copyright (C) 2014 Chandima
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.lioland.harmony.web.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Chandima
 */
public class Project extends ODBClass {

    public static final String INITIATED = "Initiated";
    public static final String COMMENCED = "Commenced";
    public static final String ACCOMPLISHED = "Accomplished";

    private String title;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date implementationDate;
    private List<User> users = new ArrayList<>();
    private String description;
    private Need need;
    private boolean donorsAllowed;
    private boolean participantsAllowed;
    private boolean coordinatorsAllowed;
    private String accountName;
    private String accountNumber;
    private String bank;
    private String branch;
    private List<CashFlow> expenses = new ArrayList<>();
    private String status;
    private List<CashFlow> contributions = new ArrayList<>();
    private List<Event> agenda = new ArrayList<>();
    private List<Appreciation> appreciations = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private String currencyType;

    public void addImage(String image) {
        images.add(image);
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addAppreciation(Appreciation appreciation) {
        appreciations.add(appreciation);
    }

    public int getMemberCount() {
        return users.size();
    }

    public void addEvent(Event event) {
        agenda.add(event);
    }

    public void addContribution(CashFlow cashFlow) {
        contributions.add(cashFlow);
    }

    public void addExpense(CashFlow cashFlow) {
        expenses.add(cashFlow);
    }

    public Need getNeed() {
        return need;
    }

    public double getTotalContributions() {
        double sum = 0;
        for (CashFlow contribution : contributions) {
            sum += contribution.getAmount();
        }
        return sum;
    }

    public double getTotalExpenses() {
        double sum = 0;
        for (CashFlow expense : expenses) {
            sum += expense.getAmount();
        }
        return sum;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    @Override
    public String toString() {
        return "Project{" + "title=" + title + ", implementationDate=" + implementationDate + ", users=" + users + ", description=" + description + ", need=" + need + ", donorsAllowed=" + donorsAllowed + ", participantsAllowed=" + participantsAllowed + ", coordinatorsAllowed=" + coordinatorsAllowed + ", accountName=" + accountName + ", accountNumber=" + accountNumber + ", bank=" + bank + ", branch=" + branch + ", expenses=" + expenses + ", status=" + status + ", contributions=" + contributions + ", agenda=" + agenda + ", appreciations=" + appreciations + ", currencyType=" + currencyType + '}';
    }

    public boolean isDonorsAllowed() {
        return donorsAllowed;
    }

    public void setDonorsAllowed(boolean donorsAllowed) {
        this.donorsAllowed = donorsAllowed;
    }

    public boolean isParticipantsAllowed() {
        return participantsAllowed;
    }

    public void setParticipantsAllowed(boolean participantsAllowed) {
        this.participantsAllowed = participantsAllowed;
    }

    public boolean isCoordinatorsAllowed() {
        return coordinatorsAllowed;
    }

    public void setCoordinatorsAllowed(boolean coordinatorsAllowed) {
        this.coordinatorsAllowed = coordinatorsAllowed;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getImplementationDate() {
        return implementationDate;
    }

    public void setImplementationDate(Date implementationDate) {
        this.implementationDate = implementationDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormattedImplementationDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        return df.format(implementationDate);
    }

    @Override
    public String getUniqueFieldName() {
        return "title";
    }

    public List<CashFlow> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<CashFlow> expenses) {
        this.expenses = expenses;
    }

    public List<CashFlow> getContributions() {
        return contributions;
    }

    public void setContributions(List<CashFlow> contributions) {
        this.contributions = contributions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Event> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Event> agenda) {
        this.agenda = agenda;
    }

    public List<Appreciation> getAppreciations() {
        return appreciations;
    }

    public void setAppreciations(List<Appreciation> appreciations) {
        this.appreciations = appreciations;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

}
