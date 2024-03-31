package com.pentoryall.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;


public class PostDTO {
    private long code;
    private long seriesCode;
    private long userCode;
    private String title;
    private String content;
    private String confirmContent;
    private String thumbnailImage;
    private long views;
    private long price;
    private char isPaid;
    private char isAdult;
    private char isPublic;
    private char isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public PostDTO() {
    }

    public PostDTO(long code, long seriesCode, long userCode, String title, String content, String confirmContent, String thumbnailImage, long views, long price, char isPaid, char isAdult, char isPublic, char isDeleted, LocalDateTime createDate, LocalDateTime updateDate) {
        this.code = code;
        this.seriesCode = seriesCode;
        this.userCode = userCode;
        this.title = title;
        this.content = content;
        this.confirmContent = confirmContent;
        this.thumbnailImage = thumbnailImage;
        this.views = views;
        this.price = price;
        this.isPaid = isPaid;
        this.isAdult = isAdult;
        this.isPublic = isPublic;
        this.isDeleted = isDeleted;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(long seriesCode) {
        this.seriesCode = seriesCode;
    }

    public long getUserCode() {
        return userCode;
    }

    public void setUserCode(long userCode) {
        this.userCode = userCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConfirmContent() {
        return confirmContent;
    }

    public void setConfirmContent(String confirmContent) {
        this.confirmContent = confirmContent;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public char getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(char isPaid) {
        this.isPaid = isPaid;
    }

    public char getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(char isAdult) {
        this.isAdult = isAdult;
    }

    public char getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(char isPublic) {
        this.isPublic = isPublic;
    }

    public char getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "code=" + code +
                ", seriesCode=" + seriesCode +
                ", userCode=" + userCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", confirmContent='" + confirmContent + '\'' +
                ", thumbnailImage='" + thumbnailImage + '\'' +
                ", views=" + views +
                ", price=" + price +
                ", isPaid=" + isPaid +
                ", isAdult=" + isAdult +
                ", isPublic=" + isPublic +
                ", isDeleted=" + isDeleted +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
