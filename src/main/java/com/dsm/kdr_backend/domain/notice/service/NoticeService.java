package com.dsm.kdr_backend.domain.notice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.notice.domain.Notice;
import com.dsm.kdr_backend.domain.notice.domain.repository.NoticeRepository;
import com.dsm.kdr_backend.domain.notice.exception.NotFoundNoticeException;
import com.dsm.kdr_backend.domain.notice.presentation.dto.request.NoticeRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;

	@Transactional
	public Long createNotice(NoticeRequest request) {
		return noticeRepository.save(
			Notice.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.build()
		).getId();
	}

	@Transactional
	public Long updateNotice(Long id, NoticeRequest request) {
		Notice notice = noticeRepository.findById(id)
			.orElseThrow(() -> NotFoundNoticeException.EXCEPTION);

		return noticeRepository.save(notice.update(request.getTitle(), request.getContent())).getId();
	}

	@Transactional
	public void deleteNotice(Long id) {
		Notice notice = noticeRepository.findById(id)
			.orElseThrow(() -> NotFoundNoticeException.EXCEPTION);
		noticeRepository.delete(notice);
	}

}
