package com.example.fanficapi.service;

import com.example.fanficapi.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<T, I, S, D> { // T - Object type, I - id type, S - simpleDto, D - Dto

    @Autowired
    protected Mapper mapper;

    public abstract void saveToDB(T object);

    public abstract T update(T object);

    public abstract void deleteById(I id);

    public abstract List<T> findAll();

    public abstract T findById(I id);

    public abstract List<D> getAllDto();

    public abstract S getSimpleDtoById(I id);

    public abstract D getDtoById(I id);

    public abstract T findByUsername(String name);
}