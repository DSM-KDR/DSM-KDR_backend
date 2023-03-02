package com.dsm.kdr_backend.domain.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.notice.domain.Notice;
import com.dsm.kdr_backend.domain.notice.domain.repository.NoticeRepository;
import com.dsm.kdr_backend.domain.notice.exception.NotFoundNoticeException;
import com.dsm.kdr_backend.domain.notice.presentation.dto.request.NoticeRequest;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticesResponse;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticeResponse;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final S3Util s3Util;

	@Transactional
	public Long saveNotice(NoticeRequest request, MultipartFile file) {
		return noticeRepository.save(
			Notice.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.path(s3Util.uploadImage(file, "notice"))
				.build()
		).getId();
	}

	@Transactional
	public Long updateNotice(Long id, NoticeRequest request, MultipartFile file) {
		Notice notice = noticeRepository.findById(id).orElseThrow(() -> NotFoundNoticeException.EXCEPTION);

		if(file != null) {
			s3Util.delete(notice.getPath());
			notice.updatePath(s3Util.uploadImage(file, "notice"));
		}

		return notice.update(request.getTitle(), request.getContent());
	}

	@Transactional
	public void deleteNotice(Long id) {
		Notice notice = noticeRepository.findById(id)
			.orElseThrow(() -> NotFoundNoticeException.EXCEPTION);
		noticeRepository.delete(notice);
	}

	@Transactional(readOnly = true)
	public NoticesResponse getNotices(Pageable page) {
		Page<Notice> notices = noticeRepository.findAllByOrderByIdDesc(page);

		return new NoticesResponse(notices.getTotalPages(), notices.map(this::ofNoticeResponse).toList());
	}

	@Transactional(readOnly = true)
	public NoticeResponse getNotice(Long id) {
		return noticeRepository.findById(id)
			.map(notice -> {
				return NoticeResponse.builder()
					.preview(s3Util.getS3ObjectUrl(notice.getPath()))
					.title(notice.getTitle())
					.content(notice.getContent())
					.date(notice.getCreatedDate())
					.build();
			})
			.orElseThrow(() -> NotFoundNoticeException.EXCEPTION);
	}

	@Transactional(readOnly = true)
	public NoticesResponse getSearchNoticeTitle(String title, Pageable page) {
		Page<Notice> notices = noticeRepository.findAllByTitleContainingOrderByIdDesc(title, page);
		return new NoticesResponse(notices.getTotalPages(), notices.map(this::ofNoticeResponse).toList());
	}

	private NoticesResponse.NoticeResponse ofNoticeResponse(Notice notice) {
		return NoticesResponse.NoticeResponse.builder()
			.id(notice.getId())
			.preview(s3Util.getS3ObjectUrl(notice.getPath()))
			.title(notice.getTitle())
			.date(notice.getCreatedDate())
			.build();
	}

}
