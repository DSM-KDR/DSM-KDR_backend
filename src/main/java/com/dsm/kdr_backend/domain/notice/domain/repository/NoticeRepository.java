package com.dsm.kdr_backend.domain.notice.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.notice.domain.Notice;

public interface NoticeRepository extends CrudRepository<Notice, Long> {
}
