package com.megabankcorp.records;

public class Account {
    /**
     * Unique ID of this account.
     */
    private final String id;

    /**
     * Name of this account's holder.
     */
    private final String name;

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
