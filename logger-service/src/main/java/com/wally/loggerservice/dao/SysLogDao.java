package com.wally.loggerservice.dao;

import com.wally.loggerservice.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogDao extends JpaRepository<SysLog,Long> {

}
