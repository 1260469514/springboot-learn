package com.hackdog.springbootlearn.service;

import com.hackdog.springbootlearn.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserJPA extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>,Serializable { }
