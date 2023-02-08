package com.dsm.kdr_backend.domain.notice.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dsm.kdr_backend.domain.notice.presentation.dto.request.NoticeRequest;
import com.dsm.kdr_backend.domain.notice.presentation.dto.response.NoticeListResponse;
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
	public Long createNotice(@RequestBody @Valid NoticeRequest request) {
		return noticeService.saveNotice(request);
	}

	@PutMapping("/{id}")
	public Long updateNotice(@PathVariable("id") @NotBlank Long id,
								@RequestBody @Valid NoticeRequest request) {
		return noticeService.updateNotice(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNotice(@PathVariable("id") @NotBlank Long id) {
		noticeService.deleteNotice(id);
	}

	@GetMapping
	public NoticeListResponse getNotices(Pageable page) {
		return noticeService.getNotices(page);
	}

	@GetMapping("/{id}")
	public NoticeResponse getNotice(@PathVariable("id") @NotBlank Long id) {
		return noticeService.getNotice(id);
	}

	@GetMapping("/search")
	public NoticeListResponse get(@RequestParam("title") String title, Pageable page) {
		return noticeService.getSearchNoticeTitle(title, page);
	}

}
