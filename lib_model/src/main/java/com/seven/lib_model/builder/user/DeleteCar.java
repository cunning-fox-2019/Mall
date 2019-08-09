package com.seven.lib_model.builder.user;

public class DeleteCar {
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public DeleteCar(Builder builder){
        this.ids = builder.ids;
    }

    public static class Builder{
        private String ids;
        public Builder ids(String ids){
            this.ids = ids;
            return this;
        }
        public DeleteCar build(){
            return new DeleteCar(this);
        }
    }
}
