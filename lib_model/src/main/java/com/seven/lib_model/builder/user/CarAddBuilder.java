package com.seven.lib_model.builder.user;

public class CarAddBuilder {
    private int number;
    private int cart_id;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public CarAddBuilder(Builder builder){
        this.number = builder.number;
        this.cart_id = builder.cart_id;
    }

    public static class Builder{
        private int number;
        private int cart_id;
        public Builder number(int number){
            this.number = number;
            return this;
        }
        public Builder cart_id(int cart_id){
            this.cart_id = cart_id;
            return this;
        }

        public CarAddBuilder build(){return new CarAddBuilder(this);}
    }
}
