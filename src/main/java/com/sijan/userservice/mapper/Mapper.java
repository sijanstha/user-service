package com.sijan.userservice.mapper;

import com.sijan.userservice.entity.AbstractEntity;
import com.sijan.userservice.model.AbstractDomain;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Mapper<E extends AbstractEntity, D extends AbstractDomain> {

    private E entity;
    private D domain;

    public E toEntity(D domain) {
        entity.setId(domain.getId());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setActive(domain.getActive() == null ? true : domain.getActive());
        entity.setDeleted(domain.getDeleted() == null ? false : domain.getDeleted());
        return entity;
    }

    public D toDomain(E entity) {
        domain.setId(entity.getId());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setActive(entity.getActive());
        domain.setDeleted(entity.getDeleted());
        return domain;
    }

}
