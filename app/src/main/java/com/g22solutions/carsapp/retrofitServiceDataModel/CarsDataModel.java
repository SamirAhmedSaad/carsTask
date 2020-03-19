package com.g22solutions.carsapp.retrofitServiceDataModel;

import java.util.List;

public class CarsDataModel {

    /**
     * status : 1
     * error : {"code":1,"message":"page not found"}
     * data : [{"id":1,"brand":"AUDI_1","constructionYear":"01.2015","isUsed":true,"imageUrl":"http://i.imgur.com/FG2eSjW.jpg"},{"id":2,"brand":"BMW_1","constructionYear":"05.2018","isUsed":false,"imageUrl":"http://i.imgur.com/pXbxn6j.jpg"},{"id":3,"brand":"FORD_1","constructionYear":"01.2018","isUsed":false,"imageUrl":"http://i.imgur.com/LmbOcoM.jpg"},{"id":4,"brand":"HYUNDAI_1","constructionYear":"10.2000","isUsed":true,"imageUrl":"http://i.imgur.com/eR8ldBd.jpg"},{"id":5,"brand":"SEAT_1","constructionYear":"12.1999","isUsed":true,"imageUrl":"http://i.imgur.com/P228Dbv.jpg"},{"id":6,"brand":"MERCEDES-BENZ_1","constructionYear":"05.2017","isUsed":false,"imageUrl":"http://i.imgur.com/BVG4tRn.jpg"},{"id":7,"brand":"OPEL_1","constructionYear":"09.2016","isUsed":false,"imageUrl":"http://i.imgur.com/MQLMK7Z.jpg"},{"id":8,"brand":"SKODA_1","constructionYear":"08.2017","isUsed":false,"imageUrl":"http://i.imgur.com/nS5yK4Y.jpg"},{"id":9,"brand":"TOYOTA_1","constructionYear":"12.2005","isUsed":true,"imageUrl":"http://i.imgur.com/LJWEf9f.jpg"},{"id":10,"brand":"VW_1","constructionYear":"10.2011","isUsed":false,"imageUrl":"http://i.imgur.com/Joyg9r1.jpg"}]
     */

    private int status;
    private ErrorBean error;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ErrorBean {
        /**
         * code : 1
         * message : page not found
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * brand : AUDI_1
         * constructionYear : 01.2015
         * isUsed : true
         * imageUrl : http://i.imgur.com/FG2eSjW.jpg
         */

        private int id;
        private String brand;
        private String constructionYear;
        private boolean isUsed;
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getConstructionYear() {
            return constructionYear;
        }

        public void setConstructionYear(String constructionYear) {
            this.constructionYear = constructionYear;
        }

        public boolean isIsUsed() {
            return isUsed;
        }

        public void setIsUsed(boolean isUsed) {
            this.isUsed = isUsed;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
