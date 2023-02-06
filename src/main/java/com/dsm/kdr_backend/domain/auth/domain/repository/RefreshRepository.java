package com.dsm.kdr_backend.domain.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.auth.domain.RefreshToken;

public interface RefreshRepository extends CrudRepository<RefreshToken, String> {
}
