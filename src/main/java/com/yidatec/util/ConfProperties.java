package com.yidatec.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author QuShengWen
 */
@Configuration
@ConfigurationProperties(ignoreUnknownFields = true)
public class ConfProperties {
    private String systemLocale;
    private String wrapexceptionhtml;
    private String weixinexceptionhtml;
    private String webiframeexceptionhtml;
    private String weChatHost;
    private String weChatHostNoPrefix;
    private String weChatContextPath;
    private String weChatAppId;
    private String weChatAppSecret;
    private String weChatToken;
    private String weChatCorpId;
    private String weChatCorpSecret;
    private String weChatAgentId;
    private String weChatAesKey;
    private String weChatEnterpriseToken;

    private String resRoot;
    private String securitySettingId;
    private String adminRoleId;

    private String imagePath;

    private String adminId;

    public String getSystemLocale() {
        return systemLocale;
    }

    public void setSystemLocale(String systemLocale) {
        this.systemLocale = systemLocale;
    }

    public String getWrapexceptionhtml() {
        return wrapexceptionhtml;
    }

    public void setWrapexceptionhtml(String wrapexceptionhtml) {
        this.wrapexceptionhtml = wrapexceptionhtml;
    }

    public String getWeixinexceptionhtml() {
        return weixinexceptionhtml;
    }

    public void setWeixinexceptionhtml(String weixinexceptionhtml) {
        this.weixinexceptionhtml = weixinexceptionhtml;
    }

    public String getWebiframeexceptionhtml() {
        return webiframeexceptionhtml;
    }

    public void setWebiframeexceptionhtml(String webiframeexceptionhtml) {
        this.webiframeexceptionhtml = webiframeexceptionhtml;
    }

    public String getWeChatHost() {
        return weChatHost;
    }

    public void setWeChatHost(String weChatHost) {
        this.weChatHost = weChatHost;
    }

    public String getWeChatHostNoPrefix() {
        return weChatHostNoPrefix;
    }

    public void setWeChatHostNoPrefix(String weChatHostNoPrefix) {
        this.weChatHostNoPrefix = weChatHostNoPrefix;
    }

    public String getWeChatContextPath() {
        return weChatContextPath;
    }

    public void setWeChatContextPath(String weChatContextPath) {
        this.weChatContextPath = weChatContextPath;
    }

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getWeChatAppSecret() {
        return weChatAppSecret;
    }

    public void setWeChatAppSecret(String weChatAppSecret) {
        this.weChatAppSecret = weChatAppSecret;
    }

    public String getWeChatToken() {
        return weChatToken;
    }

    public void setWeChatToken(String weChatToken) {
        this.weChatToken = weChatToken;
    }

    public String getWeChatCorpId() {
        return weChatCorpId;
    }

    public void setWeChatCorpId(String weChatCorpId) {
        this.weChatCorpId = weChatCorpId;
    }

    public String getWeChatCorpSecret() {
        return weChatCorpSecret;
    }

    public void setWeChatCorpSecret(String weChatCorpSecret) {
        this.weChatCorpSecret = weChatCorpSecret;
    }

    public String getWeChatAgentId() {
        return weChatAgentId;
    }

    public void setWeChatAgentId(String weChatAgentId) {
        this.weChatAgentId = weChatAgentId;
    }

    public String getWeChatAesKey() {
        return weChatAesKey;
    }

    public void setWeChatAesKey(String weChatAesKey) {
        this.weChatAesKey = weChatAesKey;
    }

    public String getWeChatEnterpriseToken() {
        return weChatEnterpriseToken;
    }

    public void setWeChatEnterpriseToken(String weChatEnterpriseToken) {
        this.weChatEnterpriseToken = weChatEnterpriseToken;
    }

    public String getResRoot() {
        return resRoot;
    }

    public void setResRoot(String resRoot) {
        this.resRoot = resRoot;
    }

    public String getSecuritySettingId() {
        return securitySettingId;
    }

    public void setSecuritySettingId(String securitySettingId) {
        this.securitySettingId = securitySettingId;
    }

    public String getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(String adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public String getWeixinShellException(String txt){
        return weixinexceptionhtml.replace("$$$", txt);
    }

    public String getWebIframeExceptionHtml(String txt) {
        return webiframeexceptionhtml.replace("$$$", txt);
    }

    public String getWrapExceptionHtml(String txt) {
        return wrapexceptionhtml.replace("$$$", txt);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String path) {
        this.imagePath = path;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
