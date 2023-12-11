package com.example.demo.dao;

import com.example.demo.entity.ChiTietDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "chi-tiet-don-hang")
public interface ChiTietDonHangRepositry extends JpaRepository<ChiTietDonHang, Long> {
}
