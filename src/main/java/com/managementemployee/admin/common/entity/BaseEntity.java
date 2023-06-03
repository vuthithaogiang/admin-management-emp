package com.managementemployee.admin.common.entity;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
public abstract class BaseEntity implements Cloneable, Serializable {
    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
    public BaseEntity() {
        super();
    }


    public BaseEntity(LocalDateTime createAt, LocalDateTime updateAt) {
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//    }
}
