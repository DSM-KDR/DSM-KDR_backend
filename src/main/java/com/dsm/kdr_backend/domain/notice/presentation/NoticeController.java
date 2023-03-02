package com.dsm.kdr_backend.domain.notice.presentation;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.notice.presentation.dto.request.NoticeRequest;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticesResponse;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticeResponse;
import com.dsm.kdr_backend.domain.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {

	private final NoticeService noticeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveNotice(@RequestPart(value = "noticeRequest") @Valid NoticeRequest request,
							@RequestPart(value = "file") MultipartFile file) {
		return noticeService.saveNotice(request, file);
	}

	@PutMapping("/{id}")
	public Long updateNotice(@PathVariable("id") Long id,
								@RequestPart(value = "noticeRequest") @Valid NoticeRequest request,
								@RequestPart(value = "file", required = false) MultipartFile file) {
		return noticeService.updateNotice(id, request, file);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNotice(@PathVariable("id") Long id) {
		noticeService.deleteNotice(id);
	}

	@GetMapping
	public NoticesResponse getNotices(Pageable page) {
		return noticeService.getNotices(page);
	}

	@GetMapping("/{id}")
	public NoticeResponse getNotice(@PathVariable("id") Long id) {
		return noticeService.getNotice(id);
	}

	@GetMapping("/search")
	public NoticesResponse getSearchNoticeTitle(@RequestParam("title") String title, Pageable page) {
		return noticeService.getSearchNoticeTitle(title, page);
	}

}
