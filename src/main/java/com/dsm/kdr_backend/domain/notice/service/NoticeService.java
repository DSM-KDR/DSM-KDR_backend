package com.dsm.kdr_backend.domain.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.notice.domain.Notice;
import com.dsm.kdr_backend.domain.notice.domain.repository.NoticeRepository;
import com.dsm.kdr_backend.domain.notice.exception.NotFoundNoticeException;
import com.dsm.kdr_backend.domain.notice.presentation.dto.request.NoticeRequest;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticeListResponse;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticeResponse;

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

	@Transactional(readOnly = true)
	public NoticeListResponse getNotices(Pageable page) {
		Page<Notice> notices = noticeRepository.findAllByOrderByIdDesc(page);

		return new NoticeListResponse(notices.getTotalPages(),
			notices.map( notice -> {
					return NoticeListResponse.NoticeResponse.builder()
						.id(notice.getId())
						.title(notice.getTitle())
						.content(notice.getContent())
						.build();
				}
			).toList());
	}

	@Transactional(readOnly = true)
	public NoticeResponse getNotice(Long id) {
		return noticeRepository.findById(id)
			.map(notice -> {
				return NoticeResponse.builder()
					.title(notice.getTitle())
					.content(notice.getContent())
					.date(notice.getCreatedDate())
					.build();
			})
			.orElseThrow(() -> NotFoundNoticeException.EXCEPTION);
	}

	@Transactional(readOnly = true)
	public NoticeListResponse getSearchNoticeTitle(String title, Pageable page) {
		Page<Notice> notices = noticeRepository.findAllByTitleContaining(title, page);
		return new NoticeListResponse(notices.getTotalPages(),
			notices.map(notice -> {
				return NoticeListResponse.NoticeResponse.builder()
					.id(notice.getId())
					.title(notice.getTitle())
					.content(notice.getContent())
					.build();
			}).toList());
	}

}
