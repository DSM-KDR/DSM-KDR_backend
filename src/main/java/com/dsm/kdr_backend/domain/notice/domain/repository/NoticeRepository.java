package com.dsm.kdr_backend.domain.notice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.notice.domain.Notice;

public interface NoticeRepository extends CrudRepository<Notice, Long> {
	Page<Notice> findAllByOrderByIdDesc(Pageable page);
	Page<Notice> findAllByTitleContaining(String title, Pageable page);
}
