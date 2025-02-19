/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.util;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author HP
 */
public class Message implements Serializable{
    private String userId = null;    //用户id
    private String password = null;  //密码
    //消息类型：M_LOGIN：用户登录消息；M_SUCCESS：登录成功；M_FAULURE：登录失败
    //M_ACK：服务器对登录用户的回应消息；M_MSG：会话消息；M_QUIT：用户退出消息
    private String type = null;
    private String text = null;             //消息体
    private InetAddress toAddr = null;      //目标用户地址
    private int toPort;                     //目标用户端口
    private String targetId = null;         //目标用户id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InetAddress getToAddr() {
        return toAddr;
    }

    public void setToAddr(InetAddress toAddr) {
        this.toAddr = toAddr;
    }

    public int getToPort() {
        return toPort;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    
}
