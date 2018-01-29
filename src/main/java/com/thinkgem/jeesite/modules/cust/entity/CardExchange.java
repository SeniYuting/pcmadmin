package com.thinkgem.jeesite.modules.cust.entity;

public class CardExchange {
    private Long id;
    private Long send_user_id;
    private Long receive_user_id;
    private String date;

    public CardExchange(){
        this.id = new Long(0);
    }

    public CardExchange(Long send_user_id, Long receive_user_id, String date){
        this.send_user_id = send_user_id;
        this.receive_user_id = receive_user_id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(Long send_user_id) {
        this.send_user_id = send_user_id;
    }

    public Long getReceive_user_id() {
        return receive_user_id;
    }

    public void setReceive_user_id(Long receive_user_id) {
        this.receive_user_id = receive_user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
