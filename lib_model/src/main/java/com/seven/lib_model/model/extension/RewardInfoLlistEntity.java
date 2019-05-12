package com.seven.lib_model.model.extension;

/**
 * Created by xxxxxxH on 2019/5/12.
 */
public class RewardInfoLlistEntity {

    /**
     * id : 1.0
     * reward_name : 直推奖励
     * reward_number : 60.0
     * is_processed : 1.0
     * processed_at : 2019-05-11 23:31:31
     * reward_desc : 立即发放
     * reward_at : 2019-05-11 23:31:31
     * datetime : 2019-05-11
     * can_processed : 1.0
     */
//1.已领取, 2.待领取, 3.时间未到
    private int id;
    private String reward_name;
    private int reward_number;
    private int is_processed;
    private String processed_at;
    private String reward_desc;
    private String reward_at;
    private String datetime;
    private int can_processed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public int getReward_number() {
        return reward_number;
    }

    public void setReward_number(int reward_number) {
        this.reward_number = reward_number;
    }

    public int getIs_processed() {
        return is_processed;
    }

    public void setIs_processed(int is_processed) {
        this.is_processed = is_processed;
    }

    public String getProcessed_at() {
        return processed_at;
    }

    public void setProcessed_at(String processed_at) {
        this.processed_at = processed_at;
    }

    public String getReward_desc() {
        return reward_desc;
    }

    public void setReward_desc(String reward_desc) {
        this.reward_desc = reward_desc;
    }

    public String getReward_at() {
        return reward_at;
    }

    public void setReward_at(String reward_at) {
        this.reward_at = reward_at;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getCan_processed() {
        return can_processed;
    }

    public void setCan_processed(int can_processed) {
        this.can_processed = can_processed;
    }
}
