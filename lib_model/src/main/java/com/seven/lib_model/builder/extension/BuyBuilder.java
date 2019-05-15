package com.seven.lib_model.builder.extension;

public class BuyBuilder {
    public BuyBuilder(Builder builder) {
        this.contact_id = builder.contact_id;
    }

    private int contact_id;

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public static class Builder {
        private int contact_id;

        public Builder contact_id(int contact_id) {
            this.contact_id = contact_id;
            return this;
        }

        public BuyBuilder build() {
            return new BuyBuilder(this);
        }
    }
}
